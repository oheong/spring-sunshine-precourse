package sunshine.domain;

public enum WeatherCode {
    CLEAR_SKY(0, "맑음"),
    MAINLY_CLEAR(1, "대체로 맑음"),
    PARTLY_CLOUDY(2, "부분적으로 흐림"),
    OVERCAST(3, "흐림"),
    FOG(45, "안개"),
    DEPOSITING_RIME_FOG(48, "서리 안개"),
    DRIZZLE_LIGHT(51, "가벼운 이슬비"),
    DRIZZLE_MODERATE(53, "이슬비"),
    DRIZZLE_DENSE(55, "강한 이슬비"),
    FREEZING_DRIZZLE_LIGHT(56, "가벼운 어는 이슬비"),
    FREEZING_DRIZZLE_DENSE(57, "강한 어는 이슬비"),
    RAIN_SLIGHT(61, "약한 비"),
    RAIN_MODERATE(63, "비"),
    RAIN_HEAVY(65, "강한 비"),
    FREEZING_RAIN_LIGHT(66, "약한 어는 비"),
    FREEZING_RAIN_HEAVY(67, "강한 어는 비"),
    SNOW_SLIGHT(71, "약한 눈"),
    SNOW_MODERATE(73, "눈"),
    SNOW_HEAVY(75, "강한 눈"),
    SNOW_GRAINS(77, "눈알갱이"),
    RAIN_SHOWERS_SLIGHT(80, "약한 소나기"),
    RAIN_SHOWERS_MODERATE(81, "소나기"),
    RAIN_SHOWERS_VIOLENT(82, "강한 소나기"),
    SNOW_SHOWERS_SLIGHT(85, "약한 눈 소나기"),
    SNOW_SHOWERS_HEAVY(86, "강한 눈 소나기"),
    THUNDERSTORM(95, "천둥번개"),
    THUNDERSTORM_SLIGHT_HAIL(96, "약한 우박을 동반한 천둥번개"),
    THUNDERSTORM_HEAVY_HAIL(99, "강한 우박을 동반한 천둥번개");

    private final int code;
    private final String description;

    WeatherCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static String getDescription(String codeStr) {
        try {
            int code = Integer.parseInt(codeStr);
            for (WeatherCode weatherCode : WeatherCode.values()) {
                if (weatherCode.code == code) {
                    return weatherCode.description;
                }
            }
            return "알 수 없는 날씨";
        } catch (NumberFormatException e) {
            return "알 수 없는 날씨";
        }
    }
}
