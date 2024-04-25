import java.util.*;

public class CNF {

    // Applies the resolution inference rule to two input clauses, A and B,
    // in an attempt to derive a new clause, C
    public static Clause resolution(Clause Atemp, Clause Btemp) {

        Clause A = new Clause(Atemp);
        Clause B = new Clause(Btemp);

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
        // Incorporate clauses into the KB
        KB = incorporate(KB, new HashSet<>());
    
        Set<Clause> S;
        Set<Clause> KBPrime;
    
        do {
            S = new HashSet<>();
    
            // Deep copy of KB for systematic iteration
            KBPrime = copySet(KB);

            for (Clause A : KB) {
                for (Clause B : KB) {
                    if (A != B) {
                        // Apply resolution to derive new clause
                        Clause C = resolution(A, B);
                        if (C != null) {
                            // Add unique clause to S
                            addUniqueClause(S, C);
                        }
                    }
                }
            }
    
            if (S.isEmpty()) {
                return KB;
            }
            
            KB = incorporate(S, KB);

            displayCombinedKB(KB);

        } while (!KBPrime.equals(KB));
        return KB;
    }

    // very temp much wow
    public static void addUniqueClause(Set<Clause> S, Clause newClause) {
        boolean alreadyExists = false;
        for (Clause clause : S) {
            if (clause.positive.equals(newClause.positive) && clause.negative.equals(newClause.negative)) {
                alreadyExists = true;
                break;
            }
        }
        if (!alreadyExists) {
            S.add(newClause);
        }
    }

    // Incorporates a set of clauses S into the knowledge base KB
    public static Set<Clause> incorporate(Set<Clause> Stemp, Set<Clause> KB) {

        //Set<Clause> S = copySet(Stemp);
        for (Clause A : Stemp) {
            KB = incorporateClause(A, KB);
        }
        return KB;
    }

    // Incorporates (sv integrerar) a single clause A into the knowledge base KB
    // If A subsumes any clause in KB, the function returns KB unchanged
    // If any clause in KB subsumes A, those clauses are removed from KB
    public static Set<Clause> incorporateClause(Clause A, Set<Clause> KB) {
        Set<Clause> toRemove = new HashSet<>();
        for (Clause B : copySet(KB)) {
            // If B is a subset of A. B <= A
            if (isSubsumedBy(B, A)) {
                return KB;
            }
        }

        for (Clause B : copySet(KB)) {
            // If A is a subset of B. A <= B
            if (isSubsumedBy(A, B)) {
                //toRemove.add(B);
                addUniqueClause(toRemove, B);
            }
        }

        if (toRemove.size() > 0) {
            fun(KB, toRemove);
        }
        // KB.removeAll(toRemove);
        // KB.add(A);
        addUniqueClause(KB, A);
        return KB;
    }

    public static void fun(Set<Clause> KB, Set<Clause> remove) {
        // Remove common elements from KB
        for (Clause clause : remove) {
            for (Clause otherClause : KB) {
                if (clause.positive.equals(otherClause.positive) && clause.negative.equals(otherClause.negative)) {
                    KB.remove(otherClause);
                    break;
                }
            }
        }
    }

    // A function to check if B is a subset of A, B <= A
    public static boolean isSubsumedBy(Clause A, Clause B) {
        // Returns true if B.p is a subset of A.p and B.n is a
        boolean positive = B.positive.containsAll(A.positive);
        boolean negative = B.negative.containsAll(A.negative);
        return positive && negative;
    }

    // A function to create a deep copy of a Set<Clause>
    public static Set<Clause> copySet(Set<Clause> originalSet) {
        Set<Clause> copiedSet = new HashSet<>();
        for (Clause clause : originalSet) {
            copiedSet.add(new Clause(clause));
        }
        return copiedSet;
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
