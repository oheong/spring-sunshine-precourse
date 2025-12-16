package sunshine.infrastructure;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sunshine.domain.WeatherResponseDto;

// https://api.open-meteo.com/v1/forecast?latitude=37.566&longitude=126.9784&current=temperature_2m,relative_humidity_2m,apparent_temperature,precipitation,rain,showers,snowfall,weather_code
@FeignClient(name = "weather", url = "https://api.open-meteo.com/v1")
public interface WeatherFeignClient {

    @GetMapping("/forecast")
    WeatherResponseDto getWeather(@RequestParam("latitude") double latitude,
                                  @RequestParam("longitude") double longitude,
                                  @RequestParam("current") String current);

}
