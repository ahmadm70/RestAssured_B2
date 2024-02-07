package io.loopcamp.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.*;

@Data
public class ZippopotamusSearch {
    private List<ZippopotamusPlaces> places;
    private String country;
    @JsonProperty("country abbreviation")
    private String countryAbbreviation;
    @JsonProperty("post code")
    private String postCode;
    private String state;
    @JsonProperty("state abbreviation")
    private String stateAbbreviation;
    @JsonProperty("place name")
    private String placeName;
}