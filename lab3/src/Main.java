import java.util.*;

public class Main {
    public static void main(String[] args) {

        /*
         * // ----- Part 1: Resolution -----
         * 
         * // Creating test clauses A and B
         * Set<String> positiveA = new HashSet<>(Arrays.asList("c", "t"));
         * Set<String> negativeA = new HashSet<>(Arrays.asList("b"));
         * CNF.Clause A = new CNF.Clause(positiveA, negativeA);
         * 
         * Set<String> positiveB = new HashSet<>(Arrays.asList("z", "b"));
         * Set<String> negativeB = new HashSet<>(Arrays.asList("c"));
         * CNF.Clause B = new CNF.Clause(positiveB, negativeB);
         * 
         * // Applying resolution to derive
         * CNF.Clause resultTest = CNF.resolution(A, B);
         * 
         * // Printing the result
         * System.out.println("Resulting Clause:");
         * 
         * if (resultTest == null) {
         * System.out.println("Is false");
         * } else {
         * System.out.println("Positive literals: " + resultTest.positive);
         * System.out.println("Negative literals: " + resultTest.negative);
         * }
         * 
         */

        /* ----- Part 2: Solver ----- */

        // Creating clauses representing the statements of the three people
        Set<String> positive1 = new HashSet<>(Arrays.asList("ice"));
        Set<String> negative1 = new HashSet<>(Arrays.asList("sun", "money"));
        CNF.Clause clause1 = new CNF.Clause(positive1, negative1);

        Set<String> positive2 = new HashSet<>(Arrays.asList("ice", "movie"));
        Set<String> negative2 = new HashSet<>(Arrays.asList("money"));
        CNF.Clause clause2 = new CNF.Clause(positive2, negative2);

        Set<String> positive3 = new HashSet<>(Arrays.asList("money"));
        Set<String> negative3 = new HashSet<>(Arrays.asList("movie"));
        CNF.Clause clause3 = new CNF.Clause(positive3, negative3);

        Set<String> positive4 = new HashSet<>(Arrays.asList());
        Set<String> negative4 = new HashSet<>(Arrays.asList("movie", "ice"));
        CNF.Clause clause4 = new CNF.Clause(positive4, negative4);

        Set<String> positive5 = new HashSet<>(Arrays.asList("sun", "money", "cry"));
        Set<String> negative5 = new HashSet<>(Arrays.asList());
        CNF.Clause clause5 = new CNF.Clause(positive5, negative5);

        // Creating the initial knowledge base
        Set<CNF.Clause> KB = new HashSet<>();
        KB.add(clause1);
        KB.add(clause2);
        KB.add(clause3);
        KB.add(clause4);
        KB.add(clause5);

        // Solving
        Set<CNF.Clause> result = CNF.solver(KB);

        // Printing the result
        System.out.println("Resulting KB:");
        for (CNF.Clause clause : result) {
            System.out.println("Positive literals: " + clause.positive);
            System.out.println("Negative literals: " + clause.negative + "\n");
        }
    }
}
