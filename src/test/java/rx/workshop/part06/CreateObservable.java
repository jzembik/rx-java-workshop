package rx.workshop.part06;

import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;

public class CreateObservable {

    @Test
    public void testNever() {
        final Observable<Integer> observable = Observable.empty();

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);

        testSubscriber.assertNotCompleted();
    }

    @Test
    public void testEmpty() {
        final Observable<Integer> observable = Observable.empty();

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);

        testSubscriber.assertNoValues();
    }

    @Test
    public void testRepeat() {
        final Observable<Integer> observable = Observable.just(1, 2, 3, 4);

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);

        testSubscriber.assertValues(1, 2, 3, 4, 1, 2, 3, 4);
    }

    @Test
    public void testCreate() {
        final Observable<Integer> observable = Observable.create(subscriber -> {
            if(!subscriber.isUnsubscribed()) {
                subscriber.onCompleted();
            }
        });

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);

        testSubscriber.assertValues(1);
    }

    @Test
    public void testDefer() {
        final Observable<Integer> observable = Observable.defer(() -> Observable.empty());

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);

        testSubscriber.assertValues(1);
    }


}
