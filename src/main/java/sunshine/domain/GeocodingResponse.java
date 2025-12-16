package sunshine.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GeocodingResponse {
    private double lat;
    private double lon;
    private String displayName;
}