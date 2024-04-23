import java.util.*;

public class CNF {

    // Applies the resolution inference rule to two input clauses, A and B,
    // in an attempt to derive a new clause, C
    public static Clause resolution(Clause A, Clause B) {

        // Create the intersection: A.p intersect B.n
        Set<String> intersection1 = new HashSet<>(A.positive);
        intersection1.retainAll(B.negative);

        // Create the intersection: A.n intersect B.p
        Set<String> intersection2 = new HashSet<>(A.negative);
        intersection2.retainAll(B.positive);

        // if A.p intersect B.n = {} and A.n intersect B.p = {}
        if (intersection1.isEmpty() && intersection2.isEmpty()) {
            return null;
        }

        String literalToRemove;

        // if (A.p interect B.n) != {}
        if (!intersection1.isEmpty()) {
            literalToRemove = intersection1.iterator().next(); // Retrieves the first element (order is not guaranteed)
            A.positive.remove(literalToRemove);
            B.negative.remove(literalToRemove);
        } else {
            literalToRemove = intersection2.iterator().next(); // Retrieves the first element (order is not guaranteed)
            A.negative.remove(literalToRemove);
            B.positive.remove(literalToRemove);
        }

        // C.p <- A.p Union B.p
        Set<String> positiveUnion = new HashSet<>(A.positive);
        positiveUnion.addAll(B.positive);

        // C.n <- A.n Union B.n
        Set<String> negativeUnion = new HashSet<>(A.negative);
        negativeUnion.addAll(B.negative);

        // C.p intersect C.n != {}
        // Collections.disjoint() returns true of no elements are in common
        if (!Collections.disjoint(positiveUnion, negativeUnion)) {
            return null; // Tautology (upprepning)
        }

        // return C
        return new Clause(positiveUnion, negativeUnion);
    }

    // The function that tries to solve the CNF
    public static Set<Clause> solver(Set<Clause> KB) {

        // incorporate (sv integrera)
        KB = incorporate(KB, new HashSet<>());

        Set<Clause> S;
        Set<Clause> KBPrime;

        do {
            S = new HashSet<>();

            KBPrime = new HashSet<>(KB);

            for (Clause A : KB) {
                for (Clause B : KB) {
                    if (A != B) {
                        Clause C = resolution(A, B);
                        if (C != null) {
                            S.add(C);
                        }
                    }
                }
            }

            if (S.isEmpty()) {
                return KB;
            }

            KB = incorporate(S, KB);
        } while (!KBPrime.equals(KB));

        // Return KB
        return KB;
    }

    // Incorporates a set of clauses S into the knowledge base KB
    public static Set<Clause> incorporate(Set<Clause> S, Set<Clause> KB) {
        for (Clause A : S) {
            KB = incorporateClause(A, KB);
        }
        return KB;
    }

    // Incorporates (sv integrerar) a single clause A into the knowledge base KB
    // If A subsumes any clause in KB, the function returns KB unchanged
    // If any clause in KB subsumes A, those clauses are removed from KB
    public static Set<Clause> incorporateClause(Clause A, Set<Clause> KB) {
        Set<Clause> toRemove = new HashSet<>();
        for (Clause B : KB) {
            // If B is a subset of A. B <= A
            if (isSubsumedBy(A, B)) {
                return KB;
            }
            // If A is a subset of B. A <= B
            else if (isSubsumedBy(B, A)) {
                toRemove.add(B);
            }
        }
        KB.removeAll(toRemove);
        KB.add(A);
        return KB;
    }

    // A function to check if B is a subset of A, B <= A
    public static boolean isSubsumedBy(Clause A, Clause B) {
        // Returns true if B.p is a subset of A.p and B.n is a subset of A.n
        return B.positive.containsAll(A.positive) && B.negative.containsAll(A.negative);
    }

    // Display function
    public static void displayCombinedKB(Set<Clause> KB) {
        Set<String> combinedPositive = new HashSet<>();
        Set<String> combinedNegative = new HashSet<>();

        for (Clause clause : KB) {
            combinedPositive.addAll(clause.positive);
            combinedNegative.addAll(clause.negative);
        }

        System.out.println("Combined KB:");
        System.out.println("Positive literals: " + combinedPositive);
        System.out.println("Negative literals: " + combinedNegative + "\n");
    }
}
