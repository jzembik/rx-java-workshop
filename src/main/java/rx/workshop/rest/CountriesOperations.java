package rx.workshop.rest;

import rx.Observable;

public interface CountriesOperations {

    Observable<Country> getCountries();

    Observable<Country> getCountries(String region);

}
