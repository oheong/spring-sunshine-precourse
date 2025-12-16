package sunshine.domain;

public interface WeatherDomainClient {

    WeatherResponseDto getWeather(double latitude, double longitude, String current);

}
