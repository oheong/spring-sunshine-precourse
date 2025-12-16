package sunshine.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import sunshine.domain.WeatherDomainClient;
import sunshine.domain.WeatherResponseDto;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherDomainFeignClient implements WeatherDomainClient {

    private final WeatherFeignClient weatherFeignClient;

    @Override
    public WeatherResponseDto getWeather(double latitude, double longitude, String current) {
        WeatherResponseDto response = null;
        try {
            response = weatherFeignClient.getWeather(latitude, longitude, current);
        } catch (HttpClientErrorException e) {
            // 4xx 클라이언트 에러 (잘못된 요청, 권한 없음 등)
            log.error("HttpClientErrorException: {}", e.getMessage());
        } catch (HttpServerErrorException e) {
            // 5xx 서버 에러
            log.error("HttpServerErrorException: {}", e.getMessage());
        } catch (ResourceAccessException e) {
            // 네트워크 연결 실패, 타임아웃
            log.error("ResourceAccessException: {}", e.getMessage());
        } catch (RestClientException e) {
            // 기타 REST 클라이언트 예외
            log.error("RestClientException: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            // 잘못된 파라미터
            log.error("IllegalArgumentException: {}", e.getMessage());
        } catch (Exception e) {
            // 기타 모든 예외
            log.error("Exception: {}", e.getMessage());
        }
        return response;
    }
}
