package sunshine.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherDomainService {

    private final WeatherDomainClient weatherDomainClient;

    public WeatherResponseDto getWeather(GeocodingResponse geo) {
        String current = "temperature_2m,relative_humidity_2m,apparent_temperature,precipitation,rain,showers,snowfall,weather_code";
        return weatherDomainClient.getWeather(geo.getLat(), geo.getLon(), current);
    }
}
