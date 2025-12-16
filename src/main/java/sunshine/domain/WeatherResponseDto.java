package sunshine.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherResponseDto {

    @JsonProperty("latitude")
    private String latitude;

    @JsonProperty("longitude")
    private String longitude;

    @JsonProperty("generationtime_ms")
    private String generationTimeMs;

    @JsonProperty("utc_offset_seconds")
    private String utcOffsetSeconds;

    @JsonProperty("timezone")
    private String timezone;

    @JsonProperty("timezone_abbreviation")
    private String timezoneAbbreviation;

    @JsonProperty("elevation")
    private String elevation;

    @JsonProperty("current_units")
    private CurrentUnits currentUnits;

    @JsonProperty("current")
    private CurrentUnits current;

    @Data
    public static class CurrentUnits {

        @JsonProperty("time")
        private String time;

        @JsonProperty("interval")
        private String interval;

        @JsonProperty("temperature_2m")
        private String temperature2m;

        @JsonProperty("relative_humidity_2m")
        private String relativeHumidity2m;

        @JsonProperty("apparent_temperature")
        private String apparentTemperature;

        @JsonProperty("precipitation")
        private String precipitation;

        @JsonProperty("rain")
        private String rain;

        @JsonProperty("showers")
        private String showers;

        @JsonProperty("snowfall")
        private String snowfall;

        @JsonProperty("weather_code")
        private String weatherCode;
    }
}