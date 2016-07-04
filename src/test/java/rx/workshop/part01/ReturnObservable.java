package rx.workshop.part01;

import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;

public class ReturnObservable {

    @Test
    public void testJust() {
        final Observable<Integer> integers = Observable.empty();

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        integers.subscribe(testSubscriber);

        testSubscriber.assertValues(1, 2, 3);
    }

    @Test
    public void testFrom() {
        final Observable<Integer> integers = Observable.empty();

        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        integers.subscribe(testSubscriber);

        testSubscriber.assertValues(1, 2, 3);
    }

}
