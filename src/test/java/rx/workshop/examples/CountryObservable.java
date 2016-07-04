package rx.workshop.examples;

import com.google.common.base.Strings;
import org.junit.Test;
import rx.Observable;
import rx.workshop.data.Tuple2;
import rx.workshop.rest.CountriesClient;
import rx.workshop.rest.CountriesOperations;
import rx.workshop.rest.Country;

public class CountryObservable {
    private static final CountriesOperations REST = new CountriesClient();

    @Test
    public void testCapitalsOfEurope() {
        REST.getCountries("europe")
                .map(country -> country.getCapital())
                .forEach(System.out::println);
    }

    @Test
    public void testLargestEuropeanCountries() {
        REST.getCountries("europe")
                .toSortedList((c1, c2) -> Long.compare(c2.getArea(), c1.getArea()))
                .flatMapIterable(countries -> countries)
                .limit(10)
                .forEach(System.out::println);
    }

    @Test
    public void testCurrenciesUsedInEurope() {
        REST.getCountries("europe")
                .flatMapIterable(country -> country.getCurrencies())
                .distinct()
                .toSortedList()
                .forEach(System.out::println);
    }

    @Test
    public void testCountriesInRegion() {
        REST.getCountries()
                .filter(country -> !Strings.isNullOrEmpty(country.getRegion()))
                .groupBy(country -> country.getRegion())
                .flatMap(region -> region
                        .toList()
                        .map(countries -> new Tuple2<>(region.getKey(), countries)))
                .toSortedList((r1, r2) -> Long.compare(r2.getSecond().size(), r1.getSecond().size()))
                .flatMapIterable(e -> e)
                .subscribe(region -> System.out.println(region.getFirst() + " " + region.getSecond().size()));
    }

    @Test
    public void testRegionPopulation() {
        REST.getCountries()
                .filter(country -> !Strings.isNullOrEmpty(country.getRegion()))
                .groupBy(country -> country.getRegion())
                .flatMap(region -> region
                        .map(country -> country.getPopulation())
                        .reduce(Long::sum)
                        .map(population -> new Tuple2(region.getKey(), population)))
                .forEach(System.out::println);
    }

    @Test
    public void testCountriesUsingMostPopularLanguagesInEurope() {
        final Observable<Country> countries = REST.getCountries("europe").cache();

        countries.flatMapIterable(country -> country.getLanguages())
                .groupBy(language -> language)
                .flatMap(group -> group.count().map(count -> new Tuple2<>(group.getKey(), count)))
                .toSortedList((t1, t2) -> Integer.compare(t2.getSecond(), t1.getSecond()))
                .flatMapIterable(sorted -> sorted)
                .flatMap(t -> countries
                        .filter(country -> country.getLanguages().contains(t.getFirst()))
                        .map(c -> c.getName())
                        .toList()
                        .map(c -> new Tuple2<>(t.getFirst(), c))
                ).forEach(System.out::println);
    }
}
