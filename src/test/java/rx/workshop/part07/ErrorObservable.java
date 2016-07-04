package rx.workshop.part07;

import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;

import java.util.concurrent.atomic.AtomicInteger;

public class ErrorObservable {

    @Test
    public void testError() {
        final Throwable exception = new IllegalStateException();
        final Observable<Integer> observable = Observable.empty();

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);

        testSubscriber.assertError(exception);
    }

    @Test
    public void testOnErrorResumeNext() {
        final Observable<Integer> observable = Observable.<Integer>error(new IllegalStateException());

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);

        testSubscriber.assertValues(1, 2, 3);
    }

    @Test
    public void testOnErrorReturn() {
        final Observable<Integer> observable = Observable.<Integer>error(new IllegalStateException());

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);

        testSubscriber.assertValues(1);
    }

    @Test
    public void testRetry() {
        final AtomicInteger counter = new AtomicInteger();
        final Observable<Integer> observable = Observable.<Integer>create(subscriber -> {
            if(!subscriber.isUnsubscribed()) {
                if(counter.get() == 0) {
                    counter.incrementAndGet();
                    subscriber.onError(new IllegalStateException());
                } else {
                    subscriber.onNext(1);
                    subscriber.onCompleted();
                }
            }
        });

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);

        testSubscriber.assertValues(1);
    }
}
