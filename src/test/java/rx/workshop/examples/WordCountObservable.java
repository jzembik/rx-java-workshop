package rx.workshop.examples;

import org.junit.Test;
import rx.Observable;
import rx.workshop.data.Tuple2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class WordCountObservable {

    @Test
    public void testWordCountFromMemory() {
        Observable.from(Arrays.asList("one", "two", "one", "two", "three", "four", "five", "one"))
                .groupBy(word -> word)
                .flatMap(group -> group.count().map(count -> new Tuple2<>(group.getKey(), count)))
                .toSortedList((t1, t2) -> Integer.compare(t2.getSecond(), t1.getSecond()))
                .subscribe(System.out::println);
    }

    @Test
    public void testWordCountFromFile() {
        Observable<String> lines = Observable.using(
                // resource
                () -> {
                    try {
                        return Files.lines(Paths.get(getClass().getClassLoader().getResource("words.txt").toURI()));
                    } catch (IOException | URISyntaxException e) {
                        throw new IllegalStateException(e);
                    }
                },
                // resource -> observable
                stream -> Observable.from(() -> stream.iterator()),
                // dispose action
                stream -> stream.close());

        lines.flatMapIterable(line -> Arrays.asList(line.split(" ")))
                .groupBy(word -> word)
                .flatMap(group -> group.count().map(count -> new Tuple2<>(group.getKey(), count)))
                .toSortedList((t1, t2) -> Integer.compare(t2.getSecond(), t1.getSecond()))
                .subscribe(System.out::println);
    }

}
