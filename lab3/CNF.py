import os
os.system("cls")

import random;
import copy;

class Clause:

    def __init__(self, clause_string = ""):
        # Initialize sets to store positive and negative literals
        self.p = set()
        self.n = set()

        if clause_string != "":
            clause_string = clause_string.replace(' ', '').replace('\t', '').replace('\n', '')
            clause_array = clause_string.split('V')
            
            for literal in clause_array:
                if '-' in literal:
                    literal = literal.replace('-', '')
                    self.n.add(literal)
                else:
                    self.p.add(literal)

    def __hash__(self):
        return hash((frozenset(self.p), frozenset(self.n)))

    def __eq__(self, other):
        if not isinstance(other, type(self)):
            return NotImplemented
        return self.p == other.p and self.n == other.n
                    
    def is_subset(self, other):
        return self.p.issubset(other.p) and self.n.issubset(other.n)

    def __str__(self):
        disp = "{ "
        for p in self.p:
            disp += p + " "
        for n in self.n:
            disp += "-" + n + " "
        disp += "}"
        return disp
         

def displaySet(KB, prefix = "set"):
    string = prefix + " = ["
    counter = 0
    for c in KB:
        if counter < len(KB) - 1:
            string += str(c) + ", "
        else:
            string += str(c)
        counter += 1
    string += "]"
    print(string)


def Resolution(A, B):
    # return resolvent of A and B, or false
    # input: clauses A and B; A and B are local variables
    A_copy = copy.deepcopy(A)
    B_copy = copy.deepcopy(B)

    Ap_intersection_Bn = A_copy.p.intersection(B_copy.n)
    An_intersection_Bp = A_copy.n.intersection(B_copy.p)

    if not Ap_intersection_Bn and not An_intersection_Bp:
         return False
    if Ap_intersection_Bn:
        a = random.choice(list(Ap_intersection_Bn))
        A_copy.p.remove(a)
        B_copy.n.remove(a)
    else:
        a = random.choice(list(An_intersection_Bp))
        A_copy.n.remove(a)
        B_copy.p.remove(a)
    C = Clause()
    C.p = A_copy.p.union(B_copy.p)
    C.n = A_copy.n.union(B_copy.n)
    if C.p.intersection(C.n): # C is a tautology
        return False
    return C


def Solver(KB):
    # Return: set of clauses
    # Input: set of clauses KB
    KB = Incorporate(KB, set())
    displaySet(KB, "KB")

    while True:
        S = set()
        KB_prim = copy.deepcopy(KB)

        KB_list = list(KB)

        for i in range(0, len(KB_list) - 1):
            for j in range(i + 1, len(KB_list)):
                C = Resolution(KB_list[i],KB_list[j])
                if C is not False:
                    S = S | set({C})                    
        if not S:
            return KB

        KB = Incorporate(S, KB)

        if KB_prim == KB:
            break

    return KB
    
def Incorporate(S,KB):
    # return: set of clauses 
    # Input: set of clauses S, set of clauses KB
    for A in copy.deepcopy(S):
        KB = Incorporate_clause(A, KB)
    return KB

def Incorporate_clause(A, KB):
    # return: set of clauses
    # Input: clause A, set of clauses KB
    for B in copy.deepcopy(KB):
        if B.is_subset(A):
            return KB

    for B in copy.deepcopy(KB):
        if A.is_subset(B):
            KB.discard(B)

    KB = KB.union(set({A}))

    return KB

# Task A
KB = set({Clause("-sun V -money V ice"), Clause("-money V ice V movie"), Clause("-movie V money"), Clause("-movie V -ice"), Clause("movie"), Clause("sun V money V cry")})
# returns: [{ movie }, { -ice }, { -sun }, { money }]

# Task B: Robbery puzzle
# KB = set({ Clause("A V B V C"), Clause("A V -C"), Clause("A V C V -B")})
# returns: [{ A }]


displaySet(KB, "Start")
KB = Solver(KB)
displaySet(KB, "Done")