package sunshine.domain;

import java.util.Map;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClothingRecommendationService {

    private final ChatClient client;

    public ClothingRecommendationService(ChatClient.Builder builder) {
        this.client = builder.build();
    }

    public ChatResponse recommendClothing(WeatherResponseDto weather) {
        var systemPrompt = new SystemPromptTemplate("""
                You are a helpful fashion advisor AI assistant.
                You provide clothing recommendations based on weather conditions.
                Your name is {name}.
                You should reply in Korean and be friendly and practical.
                """);
        var system = systemPrompt.createMessage(Map.of("name", "StyleHelper"));

        var userMessage = new UserMessage(String.format("""
                        현재 날씨 정보를 바탕으로 어떤 옷을 입으면 좋을지 추천해주세요.
                        
                        - 기온: %s°C
                        - 체감온도: %s°C
                        - 습도: %s%%
                        - 날씨 상태: %s
                        - 강수량: %smm
                        - 비: %smm
                        - 눈: %smm
                        
                        위 날씨 정보를 고려하여 상의, 하의, 신발, 필요한 액세서리를 추천해주세요.
                        """,
                weather.getCurrent().getTemperature2m(),
                weather.getCurrent().getApparentTemperature(),
                weather.getCurrent().getRelativeHumidity2m(),
                WeatherCode.getDescription(weather.getCurrent().getWeatherCode()),
                weather.getCurrent().getPrecipitation(),
                weather.getCurrent().getRain(),
                weather.getCurrent().getSnowfall()
        ));
        var prompt = new Prompt(userMessage, system);

        return client.prompt(prompt)
                .call()
                .chatResponse();
    }
}
