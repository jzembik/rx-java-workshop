package rx.workshop.data;

import lombok.Data;

@Data
public class Tuple2<FIRST, SECOND> {
    private final FIRST first;
    private final SECOND second;
}
