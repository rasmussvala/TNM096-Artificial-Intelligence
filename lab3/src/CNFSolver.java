import java.util.*;

public class CNFSolver {
    
    static class Clause {
        Set<String> positive;
        Set<String> negative;
        
        public Clause(Set<String> positive, Set<String> negative) {
            this.positive = positive;
            this.negative = negative;
        }
    }
    
    public static Clause resolution(Clause A, Clause B) {
        Set<String> intersectionPos = new HashSet<>(A.positive);
        intersectionPos.retainAll(B.negative);
        
        Set<String> intersectionNeg = new HashSet<>(A.negative);
        intersectionNeg.retainAll(B.positive);
        
        if (intersectionPos.isEmpty() && intersectionNeg.isEmpty()) {
            return null;
        }
        
        String literalToRemove;
        if (!intersectionPos.isEmpty()) {
            literalToRemove = intersectionPos.iterator().next();
            A.positive.remove(literalToRemove);
            B.negative.remove(literalToRemove);
        } else {
            literalToRemove = intersectionNeg.iterator().next();
            A.negative.remove(literalToRemove);
            B.positive.remove(literalToRemove);
        }
        
        Set<String> positiveUnion = new HashSet<>(A.positive);
        positiveUnion.addAll(B.positive);
        
        Set<String> negativeUnion = new HashSet<>(A.negative);
        negativeUnion.addAll(B.negative);
        
        if (!Collections.disjoint(positiveUnion, negativeUnion)) {
            return null; // Tautology
        }
        
        return new Clause(positiveUnion, negativeUnion);
    }
    
    public static Set<Clause> solver(Set<Clause> KB) {
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
        
        return KB;
    }
    
    public static Set<Clause> incorporate(Set<Clause> S, Set<Clause> KB) {
        for (Clause A : S) {
            KB = incorporateClause(A, KB);
        }
        return KB;
    }
    
    public static Set<Clause> incorporateClause(Clause A, Set<Clause> KB) {
        Set<Clause> toRemove = new HashSet<>();
        for (Clause B : KB) {
            if (isSubsumedBy(A, B)) {
                return KB;
            } else if (isSubsumedBy(B, A)) {
                toRemove.add(B);
            }
        }
        KB.removeAll(toRemove);
        KB.add(A);
        return KB;
    }
    
    public static boolean isSubsumedBy(Clause A, Clause B) {
        return B.positive.containsAll(A.positive) && B.negative.containsAll(A.negative);
    }
    
    public static void main(String[] args) {
        // Creating clauses representing the statements of the three people
        Set<String> positive1 = new HashSet<>(Arrays.asList("B", "B"));
        Set<String> negative1 = new HashSet<>(Arrays.asList("R"));
        Clause clause1 = new Clause(positive1, negative1);
        
        Set<String> positive2 = new HashSet<>(Arrays.asList("B", "B"));
        Set<String> negative2 = new HashSet<>(Arrays.asList("R"));
        Clause clause2 = new Clause(positive2, negative2);
        
        Set<String> positive3 = new HashSet<>(Arrays.asList("B"));
        Set<String> negative3 = new HashSet<>(Arrays.asList("R", "R"));
        Clause clause3 = new Clause(positive3, negative3);
        
        // Creating the initial knowledge base
        Set<Clause> KB = new HashSet<>();
        KB.add(clause1);
        KB.add(clause2);
        KB.add(clause3);
        
        // Solving
        Set<Clause> result = solver(KB);
        
        // Printing the result
        System.out.println("Resulting KB:");
        for (Clause clause : result) {
            System.out.println("Positive literals: " + clause.positive);
            System.out.println("Negative literals: " + clause.negative);
        }
        
        // Inferring hat colors
        String color = inferHatColor(result);
        System.out.println("Inferred hat color: " + color);
    }
    
    public static String inferHatColor(Set<Clause> KB) {
        // Since there are only two possible colors (B for blue and R for red),
        // we can simply check if a color appears as a positive literal in any clause.
        for (Clause clause : KB) {
            if (clause.positive.contains("B")) {
                return "Blue";
            } else if (clause.positive.contains("R")) {
                return "Red";
            }
        }
        return "Unknown"; // Should not reach here in a valid solution
    }
}
