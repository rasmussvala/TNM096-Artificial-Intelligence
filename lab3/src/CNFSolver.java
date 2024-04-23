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
}
