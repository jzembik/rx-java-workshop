package rx.workshop.examples;

import lombok.Data;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.Random;

/**
 * <img width="231" height="231" src="http://mathfaculty.fullerton.edu/mathews/n2003/montecarlopi/MonteCarloPiMod/Images/MonteCarloPiMod_gr_25.gif" alt="">
 *
 */

public class PiObservable {
    private static final Logger LOG = LoggerFactory.getLogger(PiObservable.class);

    @Test
    public void testPi() {
        pi(1000).subscribe(pi -> LOG.info("pi: {}", pi));
    }

    public Observable<Double> pi(int n) {
        return points(n).filter(point -> point.x * point.x + point.y * point.y <= 1)
                .count()
                .doOnNext(count -> LOG.info("count: {}, total: {}", count, n))
                .map(count -> (double) count / n)
                .map(result -> result * 4D);
    }

    private Observable<Point> points(int n) {
        return Observable.create(subscriber -> {
            final Random random = new Random(7 * 13 * 17);

            for(int i = 0; i < n && !subscriber.isUnsubscribed(); i++) {
                subscriber.onNext(new Point(random.nextDouble(), random.nextDouble()));
            }

            if(!subscriber.isUnsubscribed()) {
                subscriber.onCompleted();
            }

        });
    }

    @Data
    private static class Point {
        private final double x;
        private final double y;
    }
}
