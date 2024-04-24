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

    public boolean equals(Clause other) {
        // Check if positive and negative sets are equal
        return this.positive.equals(other.positive) && this.negative.equals(other.negative);
    }
}
