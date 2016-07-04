package rx.workshop.part04;


import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;
import rx.workshop.data.Tuple2;

public class CombineObservable {

    @Test
    public void testStartWith() {
        final Observable<Integer> combined = Observable.just(5, 6, 7, 8);

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        combined.subscribe(testSubscriber);

        testSubscriber.assertValues(1, 2, 3, 4, 5, 6, 7, 8);
    }

    @Test
    public void testMerge() {
        final Observable<Integer> combined = Observable.just(1, 2, 3, 4);

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        combined.subscribe(testSubscriber);

        testSubscriber.assertValues(1, 2, 3, 4, 5, 6, 7, 8);
    }

    @Test
    public void testConcat() {
        final Observable<Integer> first = Observable.just(1, 2, 3, 4);
        final Observable<Integer> second = Observable.just(5, 6, 7, 8);
        final Observable<Integer> combined = Observable.empty();

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        combined.subscribe(testSubscriber);

        testSubscriber.assertValues(1, 2, 3, 4, 5, 6, 7, 8);
    }

    @Test
    public void testZip() {
        final Observable<Integer> keys = Observable.just(1, 2, 3, 4);
        final Observable<String> values = Observable.just("one", "two", "three", "four");
        final Observable<Tuple2<Integer, String>> zipped = Observable.empty();

        final TestSubscriber<Tuple2<Integer, String>> testSubscriber = new TestSubscriber<>();
        zipped.subscribe(testSubscriber);

        testSubscriber.assertValues(
                new Tuple2<>(1, "one"),
                new Tuple2<>(2, "two"),
                new Tuple2<>(3, "three"),
                new Tuple2<>(4, "four")
        );
    }


}
