import { solve } from "./a-star.js";

const test = [
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

solve(hard2, final);
