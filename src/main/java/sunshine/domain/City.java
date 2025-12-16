package sunshine.domain;

import lombok.Getter;

@Getter
public enum City {

    Seoul("서울", "37.5665", "126.9780"),
    Tokyo("도쿄", "35.6762", "139.6503"),
    NewYork("뉴욕", "40.7128", "-74.0060"),
    Paris("파리", "48.8566", "2.3522"),
    London("런던", "51.5074", "-0.1278");

    private final String cityKorName;
    private final String latitude;
    private final String longitude;

    City(String cityKorName, String latitude, String longitude) {
        this.cityKorName = cityKorName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static City getCityInfo(String cityName) {
        for (City city : City.values()) {
            if (city.name().equalsIgnoreCase(cityName)) {
                return city;
            }
        }
        throw new IllegalArgumentException("Invalid city name: " + cityName);
    }

    public static String getCityKorName(String cityName) {
        for (City city : City.values()) {
            if (city.name().equalsIgnoreCase(cityName)) {
                return city.cityKorName;
            }
        }
        throw new IllegalArgumentException("Invalid city name: " + cityName);
    }

}
