import java.util.*;

public class Clause {
    Set<String> positive;
    Set<String> negative;

    public Clause(Set<String> positive, Set<String> negative) {
        this.positive = positive;
        this.negative = negative;
    }
}
