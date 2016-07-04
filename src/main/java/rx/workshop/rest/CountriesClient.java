package rx.workshop.rest;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import rx.Observable;

public class CountriesClient implements CountriesOperations {

    private final CountriesResource resource;

    public CountriesClient() {
        this.resource = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .logger(new Slf4jLogger(CountriesClient.class))
                .logLevel(Logger.Level.BASIC)
                .target(CountriesResource.class, "http://restcountries.eu");
    }


    @Override
    public Observable<Country> getCountries() {
        return Observable.from(resource.getCountries());
    }

    @Override
    public Observable<Country> getCountries(String region) {
        return Observable.from(resource.getCountries(region));
    }
}
