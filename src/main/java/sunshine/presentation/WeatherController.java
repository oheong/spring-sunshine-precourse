package sunshine.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sunshine.application.WeatherService;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/getWeather")
    public String getWeather(@RequestParam("city") String cityName) {

        ChatResponse response = weatherService.getWeather(cityName);

        return response.getResult().getOutput().getText();
    }

}
