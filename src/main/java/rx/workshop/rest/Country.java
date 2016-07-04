package rx.workshop.rest;

import lombok.Data;

import java.util.Collection;

@Data
public class Country {
    private String name;
    private String capital;
    private String region;

    private long area;
    private long population;

    private String alpha2Code;
    private String alpha3Code;

    private Collection<String> borders;
    private Collection<String> currencies;
    private Collection<String> callingCodes;
    private Collection<String> languages;
}
