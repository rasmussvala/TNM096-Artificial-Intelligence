const { solve } = require("./a-star.js");

const test1 = [
  [3, 1, 6],
  [2, 7, 5],
  [4, 8, 0],
];

const test2 = [
  [1, 2, 3],
  [4, 5, 6],
  [7, 0, 8],
  [0, 7, 8],
];

// Try these below!
const hard1 = [
  [6, 4, 7],
  [8, 5, 0],
  [3, 2, 1],
];

const hard2 = [
  [8, 6, 7],
  [2, 5, 4],
  [3, 0, 1],
];

const final = [
  [1, 2, 3],
  [4, 5, 6],
  [7, 8, 0],
];

// Solve using h1 heuristic
const startTimeH1 = performance.now();
const movesH1 = solve(hard2, final, "h1");
const endTimeH1 = performance.now();
console.log("DONE! \n");

// Solve using h2 heuristic
const startTimeH2 = performance.now();
const movesH2 = solve(hard2, final, "h2");
const endTimeH2 = performance.now();
console.log("DONE! \n");

console.log(
  "Time taken by h1: " +
    (endTimeH1 - startTimeH1).toFixed(1) +
    " ms with " +
    movesH1 +
    " moves."
);
console.log(
  "Time taken by h2: " +
    (endTimeH2 - startTimeH2).toFixed(1) +
    " ms with " +
    movesH2 +
    " moves."
);
