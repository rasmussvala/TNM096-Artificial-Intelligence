import java.util.*;

public class ProblemSolver {
    
    public static void main(String[] args) {

        // // Creating test clauses A and B
        // Set<String> positiveA = new HashSet<>(Arrays.asList("c", "t"));
        // Set<String> negativeA = new HashSet<>(Arrays.asList("b"));
        // CNFSolver.Clause A = new CNFSolver.Clause(positiveA, negativeA);
        
        // Set<String> positiveB = new HashSet<>(Arrays.asList("z", "b"));
        // Set<String> negativeB = new HashSet<>(Arrays.asList("c"));
        // CNFSolver.Clause B = new CNFSolver.Clause(positiveB, negativeB);
        
        // // Applying resolution to derive
        // CNFSolver.Clause resultTest = CNFSolver.resolution(A, B);
        
        // // Printing the result
        // System.out.println("Resulting Clause:");
        // System.out.println("Positive literals: " + resultTest.positive);
        // System.out.println("Negative literals: " + resultTest.negative);

        // Creating clauses representing the statements of the three people
        Set<String> positive1 = new HashSet<>(Arrays.asList("A", "B","C"));
        Set<String> negative1 = new HashSet<>(Arrays.asList());
        CNFSolver.Clause clause1 = new CNFSolver.Clause(positive1, negative1);
        
        Set<String> positive2 = new HashSet<>(Arrays.asList("A"));
        Set<String> negative2 = new HashSet<>(Arrays.asList("C"));
        CNFSolver.Clause clause2 = new CNFSolver.Clause(positive2, negative2);
        
        Set<String> positive3 = new HashSet<>(Arrays.asList());
        Set<String> negative3 = new HashSet<>(Arrays.asList("B"));
        CNFSolver.Clause clause3 = new CNFSolver.Clause(positive3, negative3);
        
        // Creating the initial knowledge base
        Set<CNFSolver.Clause> KB = new HashSet<>();
        KB.add(clause1);
        KB.add(clause2);
        KB.add(clause3);
        
        // Solving
        Set<CNFSolver.Clause> result = CNFSolver.solver(KB);
        
        // Printing the result
        System.out.println("Resulting KB:");
        for (CNFSolver.Clause clause : result) {
            System.out.println("Positive literals: " + clause.positive);
            System.out.println("Negative literals: " + clause.negative);
        }
        
        // Inferring hat colors
        //String color = inferHatColor(result);
        //System.out.println("Inferred hat color: " + color);
    }
    
    public static String inferHatColor(Set<CNFSolver.Clause> KB) {
        // Since there are only two possible colors (B for blue and R for red),
        // we can simply check if a color appears as a positive literal in any clause.
        for (CNFSolver.Clause clause : KB) {
            if (clause.positive.contains("B")) {
                return "Blue";
            } else if (clause.positive.contains("R")) {
                return "Red";
            }
        }
        return "Unknown"; // Should not reach here in a valid solution
    }
}
