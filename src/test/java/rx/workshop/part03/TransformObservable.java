package rx.workshop.part03;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import lombok.Data;
import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;

import java.util.List;
import java.util.Map;

public class TransformObservable {

    @Test
    public void testMap() {
        final Observable<Integer> transformed = Observable.just(1, 2, 3, 4);

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        transformed.subscribe(testSubscriber);

        testSubscriber.assertValues(2, 4, 6, 8);
    }

    @Test
    public void testFlatMap() {
        final Observable<Integer> transformed = Observable.just(1, 2);

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        transformed.subscribe(testSubscriber);

        testSubscriber.assertValues(1, 1, 2, 2);
    }

    @Test
    public void testFlatMapIterable() {
        final Map<String, List<Integer>> thresholds = ImmutableMap.<String, List<Integer>>builder()
                .put("first", ImmutableList.of(1, 2))
                .put("second", ImmutableList.of(3, 4))
                .put("third", ImmutableList.of(5, 6))
                .build();

        final Observable<Integer> transformed = Observable.empty();

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        transformed.subscribe(testSubscriber);

        testSubscriber.assertValues(1, 2, 3, 4, 5, 6);
    }

    @Test
    public void testBuffer() {
        final Observable<List<Integer>> transformed = Observable.empty();

        final TestSubscriber<List<Integer>> testSubscriber = new TestSubscriber<>();
        transformed.subscribe(testSubscriber);

        testSubscriber.assertValues(ImmutableList.of(1, 2), ImmutableList.of(3, 4));
    }

    @Test
    public void testGroupBy() {
        final Observable<Pair> pairs = Observable.just("a", "b", "c", "a", "a", "b")
                .groupBy(key -> key)
                .flatMap(group -> group.count().map(count -> new Pair(group.getKey(), count)));

        final TestSubscriber<Pair> testSubscriber = new TestSubscriber<>();
        pairs.subscribe(testSubscriber);

        testSubscriber.assertValues(new Pair("a", 3), new Pair("b", 2), new Pair("c", 1));
    }

    @Test
    public void testReduce() {
        final Observable<Integer> transformed = Observable.just(1, 2, 3, 4);

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        transformed.subscribe(testSubscriber);

        testSubscriber.assertValues(10);
    }

    @Data
    private static class Pair {
        private final String key;
        private final int value;
    }
}
