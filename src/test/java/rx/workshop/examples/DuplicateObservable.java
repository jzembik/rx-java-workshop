package rx.workshop.examples;

import lombok.Data;
import org.junit.Test;
import rx.Observable;

import java.util.*;

public class DuplicateObservable {
    private static final List<String> INPUT = Arrays.asList("one", "two", "three", "three", "one", "four", "five", "one");

    @Test
    public void testDuplicates() {
        final Map<String, Integer> occurrences = new HashMap<>();
        for(String word : INPUT) {
            if(!occurrences.containsKey(word)) {
                occurrences.put(word, 1);
            } else {
                occurrences.put(word, occurrences.get(word) + 1);
            }
        }

        final List<Duplicate> duplicates = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : occurrences.entrySet()) {
            if(entry.getValue() > 1) {
                duplicates.add(new Duplicate(entry.getKey(), entry.getValue()));
            }
        }

        System.out.println(duplicates);
    }

    @Test
    public void testRxDuplicates() {
        Observable.from(INPUT)
                .groupBy(word -> word)
                .flatMap(group -> group.count()
                        .filter(count -> count > 1)
                        .map(count -> new Duplicate(group.getKey(), count)))
                .toList()
                .subscribe(System.out::println);
    }

    @Data
    private static class Duplicate {
        private final String word;
        private final int occurrences;

        @Override
        public String toString() {
            return word + ":" + occurrences;
        }
    }
}
