package sunshine.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;
import sunshine.domain.ClothingRecommendationService;
import sunshine.domain.GeocodingResponse;
import sunshine.domain.GeocodingService;
import sunshine.domain.WeatherDomainService;
import sunshine.domain.WeatherResponseDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherService {

    private final GeocodingService geocodingService;
    private final WeatherDomainService weatherDomainService;
    private final ClothingRecommendationService clothingRecommendationService;

    public ChatResponse getWeather(String cityName) {
        // 1. 권역 조회
        GeocodingResponse geocode = geocodingService.geocode(cityName);

        // 2. 날씨 조회
        WeatherResponseDto weather = weatherDomainService.getWeather(geocode);

        // 3. 복장 추천 (llm)
        ChatResponse response = clothingRecommendationService.recommendClothing(weather);

        // 토큰 사용량 로그 출력
        log.info("Gemini API 토큰 사용량 - 입력 토큰: {}, 출력 토큰: {}, 총 토큰: {}",
                response.getMetadata().getUsage().getPromptTokens(),
                response.getMetadata().getUsage().getCompletionTokens(),
                response.getMetadata().getUsage().getTotalTokens());

        return response;
    }
}
