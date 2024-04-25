import java.util.*;

public class Main {
    public static void main(String[] args) {

        /*
         * // ----- Part 1: Resolution -----
         * 
         * // Creating test clauses A and B
         * Set<String> positiveA = new HashSet<>(Arrays.asList("c", "t"));
         * Set<String> negativeA = new HashSet<>(Arrays.asList("b"));
         * Clause A = new Clause(positiveA, negativeA);
         * 
         * Set<String> positiveB = new HashSet<>(Arrays.asList("z", "b"));
         * Set<String> negativeB = new HashSet<>(Arrays.asList("c"));
         * Clause B = new Clause(positiveB, negativeB);
         * 
         * // Applying resolution to derive
         * Clause resultTest = resolution(A, B);
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

        /* ----- Part Middle ----- */

        Set<String> p1 = new HashSet<>(Arrays.asList("a", "b", "c"));
        Set<String> n1 = new HashSet<>(Arrays.asList());
        Clause clause1 = new Clause(p1, n1);

        Set<String> p2 = new HashSet<>(Arrays.asList("a"));
        Set<String> n2 = new HashSet<>(Arrays.asList("c"));
        Clause clause2 = new Clause(p2, n2);

        Set<String> p3 = new HashSet<>(Arrays.asList("a", "c"));
        Set<String> n3 = new HashSet<>(Arrays.asList("b"));
        Clause clause3 = new Clause(p3, n3);

        // Creating the initial knowledge base
        Set<Clause> KB = new HashSet<>();
        KB.add(clause1);
        KB.add(clause2);
        KB.add(clause3);

        /* ----- Part 2: Solver ----- */

        // //Creating clauses representing the statements
        // Set<String> p1 = new HashSet<>(Arrays.asList("ice"));
        // Set<String> n1 = new HashSet<>(Arrays.asList("sun", "money"));
        // Clause clause1 = new Clause(p1, n1);

        // Set<String> p2 = new HashSet<>(Arrays.asList("ice", "movie"));
        // Set<String> n2 = new HashSet<>(Arrays.asList("money"));
        // Clause clause2 = new Clause(p2, n2);

        // Set<String> p3 = new HashSet<>(Arrays.asList("money"));
        // Set<String> n3 = new HashSet<>(Arrays.asList("movie"));
        // Clause clause3 = new Clause(p3, n3);

        // Set<String> p4 = new HashSet<>(Arrays.asList());
        // Set<String> n4 = new HashSet<>(Arrays.asList("movie", "ice"));
        // Clause clause4 = new Clause(p4, n4);

        // Set<String> p5 = new HashSet<>(Arrays.asList("sun", "money", "cry"));
        // Set<String> n5 = new HashSet<>(Arrays.asList());
        // Clause clause5 = new Clause(p5, n5);

        // Set<String> p6 = new HashSet<>(Arrays.asList("movie"));
        // Set<String> n6 = new HashSet<>(Arrays.asList());
        // Clause clause6 = new Clause(p6, n6);

        // Creating the initial knowledge base
        // Set<Clause> KB = new HashSet<>();
        // KB.add(clause1);
        // KB.add(clause2);
        // KB.add(clause3);
        // KB.add(clause4);
        // KB.add(clause5);
        // KB.add(clause6);

        // Solving
        Set<Clause> result = CNF.solver(KB);

        // Printing the result
        CNF.displayCombinedKB(result);
    }
}
