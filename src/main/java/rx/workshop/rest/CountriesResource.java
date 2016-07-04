package rx.workshop.rest;

import feign.Param;
import feign.RequestLine;

import java.util.Collection;

public interface CountriesResource {

    @RequestLine("GET /rest/v1/all")
    Collection<Country> getCountries();

    @RequestLine("GET /rest/v1/region/{region}")
    Collection<Country> getCountries(@Param("region") String region);

}
