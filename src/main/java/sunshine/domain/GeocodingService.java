package sunshine.domain;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import sunshine.domain.exception.ExternalApiException;
import sunshine.domain.exception.InvalidLocationException;

@Service
@RequiredArgsConstructor
public class GeocodingService {
    private final RestTemplate restTemplate;

    public GeocodingResponse geocode(String location) {
        if (location == null || location.trim().isEmpty()) {
            throw new InvalidLocationException("지역명이 비어있습니다.");
        }

        String url = "https://nominatim.openstreetmap.org/search?q=" + location + "&format=json&limit=1";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "Spring-Sunshine-Weather-App");
            HttpEntity<String> entity = new HttpEntity<>(headers);

            JsonNode[] response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    JsonNode[].class
            ).getBody();

            // 응답이 null이거나 비어있는 경우
            if (response == null || response.length == 0) {
                throw new InvalidLocationException("지역을 찾을 수 없습니다: " + location);
            }

            JsonNode firstResult = response[0];

            // 필수 필드 검증
            if (!firstResult.has("lat") || !firstResult.has("lon") || !firstResult.has("display_name")) {
                throw new ExternalApiException("Geocoding API 응답에 필수 필드가 없습니다.");
            }

            double lat = firstResult.get("lat").asDouble();
            double lon = firstResult.get("lon").asDouble();
            String displayName = firstResult.get("display_name").asText();

            return new GeocodingResponse(lat, lon, displayName);

        } catch (HttpClientErrorException e) {
            // 4xx 에러: 클라이언트 오류 (재시도 불필요)
            throw new ExternalApiException("Geocoding API 클라이언트 오류: " + e.getStatusCode(), e);
        } catch (HttpServerErrorException e) {
            // 5xx 에러: 서버 오류 (재시도 가능)
            throw new ExternalApiException("Geocoding API 서버 오류: " + e.getStatusCode(), e);
        } catch (ResourceAccessException e) {
            // 네트워크 오류, 타임아웃
            throw new ExternalApiException("Geocoding API 네트워크 오류 또는 타임아웃", e);
        } catch (RestClientException e) {
            // 기타 RestTemplate 예외
            throw new ExternalApiException("Geocoding API 호출 실패", e);
        } catch (Exception e) {
            // JSON 파싱 오류 등 기타 예외
            throw new ExternalApiException("Geocoding API 응답 처리 실패", e);
        }
    }
}


