import java.util.*;

public class Clause {
    Set<String> positive;
    Set<String> negative;

    public Clause(Set<String> positive, Set<String> negative) {
        this.positive = positive;
        this.negative = negative;
    }

    // Copy constructor for deep copy
    public Clause(Clause other) {
        this.positive = new HashSet<>(other.positive);
        this.negative = new HashSet<>(other.negative);
    }
}
