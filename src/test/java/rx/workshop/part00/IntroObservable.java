package rx.workshop.part00;

import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;

public class IntroObservable {

    @Test
    public void testSubscribe() {
        final Observable<Integer> integers = Observable.just(1);

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        // integers.subscribe(testSubscriber);

        testSubscriber.assertValues(1);
    }

    @Test
    public void testLazyEvaluation() {
        final RuntimeException exception = new IllegalStateException();
        Observable<Integer> integers = null;

        try {
            integers = Observable.just(1).map(integer -> {
                throw exception;
            });
        } catch (Throwable e) {
            System.out.println("Suppress exception");
        }

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        integers.subscribe(testSubscriber);

        testSubscriber.assertValue(1); //testSubscriber.assertError(exception);
    }

}
