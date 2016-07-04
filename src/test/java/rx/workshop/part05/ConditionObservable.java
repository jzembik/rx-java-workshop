package rx.workshop.part05;


import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;

public class ConditionObservable {

    @Test
    public void testFirstOrDefault() {
        final Observable<Integer> observable = Observable.<Integer>empty();

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);

        testSubscriber.assertValues(0);
    }

    @Test
    public void testSwitchIfEmpty() {
        final Observable<Integer> observable = Observable.<Integer>empty();

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);

        testSubscriber.assertValues(-1, -2, -3);
    }

    @Test
    public void testSkipWhile() {
        final Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8);

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);

        testSubscriber.assertValues(4, 5, 6, 7, 8);
    }

    @Test
    public void testTakeWhile() {
        final Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8);

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);

        testSubscriber.assertValues(4, 5, 6, 7, 8);
    }

    @Test
    public void testAll() {
        final Observable<Boolean> observable = Observable.just(2, 4, 6, 8).all(e -> false);

        final TestSubscriber<Boolean> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);

        testSubscriber.assertValues(true);
    }

    @Test
    public void testContains() {
        final Observable<Boolean> observable = Observable.just(2, 4, 6, 8).contains(3);

        final TestSubscriber<Boolean> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);

        testSubscriber.assertValues(true);
    }

    @Test
    public void testExists() {
        final Observable<Boolean> observable = Observable.just(2, 4, 6, 8).exists(e -> e == 3);

        final TestSubscriber<Boolean> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);

        testSubscriber.assertValues(true);
    }

}
