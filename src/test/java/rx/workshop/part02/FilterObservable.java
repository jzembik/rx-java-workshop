package rx.workshop.part02;

import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;

public class FilterObservable {

    @Test
    public void testFilter() {
        final Observable<Integer> filtered = Observable.just(1, 2, 3, 4, 5, 6, 7, 8);

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        filtered.subscribe(testSubscriber);

        testSubscriber.assertValues(2, 4, 6, 8);
    }

    @Test
    public void testFirst() {
        final Observable<Integer> filtered = Observable.just(1, 2, 3, 4, 5, 6, 7, 8);

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        filtered.subscribe(testSubscriber);

        testSubscriber.assertValues(1);
    }

    @Test
    public void testLast() {
        final Observable<Integer> filtered = Observable.just(1, 2, 3, 4, 5, 6, 7, 8);

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        filtered.subscribe(testSubscriber);

        testSubscriber.assertValues(8);
    }

    @Test
    public void testSkip() {
        final Observable<Integer> filtered = Observable.just(1, 2, 3, 4, 5, 6, 7, 8);

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        filtered.subscribe(testSubscriber);

        testSubscriber.assertValues(5, 6, 7, 8);
    }

    @Test
    public void testSkipLast() {
        final Observable<Integer> filtered = Observable.just(1, 2, 3, 4, 5, 6, 7, 8);

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        filtered.subscribe(testSubscriber);

        testSubscriber.assertValues(1, 2, 3, 4);
    }

    @Test
    public void testTake() {
        final Observable<Integer> filtered = Observable.just(1, 2, 3, 4, 5, 6, 7, 8);

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        filtered.subscribe(testSubscriber);

        testSubscriber.assertValues(1, 2, 3, 4);
    }

    @Test
    public void testTakeFirst() {
        final Observable<Integer> filtered = Observable.just(1, 2, 3, 4, 5, 6, 7, 8);

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        filtered.subscribe(testSubscriber);

        testSubscriber.assertValues(3);
    }

    @Test
    public void testElementAt() {
        final Observable<Integer> filtered = Observable.just(1, 2, 3, 4, 5, 6, 7, 8);

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        filtered.subscribe(testSubscriber);

        testSubscriber.assertValues(3);
    }

    @Test
    public void testLimit() {
        final Observable<Integer> filtered = Observable.just(1, 2, 3, 4, 5, 6, 7, 8);

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        filtered.subscribe(testSubscriber);

        testSubscriber.assertValues(1, 2, 3, 4);
    }

    @Test
    public void testDistinct() {
        final Observable<Integer> filtered = Observable.just(1, 1, 2, 2, 3, 3, 3, 4, 1, 1);

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        filtered.subscribe(testSubscriber);

        testSubscriber.assertValues(1, 2, 3, 4);
    }

    @Test
    public void testDistinctUntilChanged() {
        final Observable<Integer> filtered = Observable.just(1, 1, 2, 2, 3, 3, 4, 1, 1);

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        filtered.subscribe(testSubscriber);

        testSubscriber.assertValues(1, 2, 3, 4, 1);
    }


}
