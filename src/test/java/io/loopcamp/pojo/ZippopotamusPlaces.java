package io.loopcamp.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ZippopotamusPlaces {
    private String longitude;
    private String latitude;
    private String state;
    @JsonProperty("state abbreviation")
    private String stateAbbreviation;
    @JsonProperty("place name")
    private String placeName;
    @JsonProperty("post code")
    private String postCode;
}