import { solve } from "./a-star.js";

const initial = [
  [1, 2, 3],
  [4, 5, 6],
  [7, 0, 8],
];

const final = [
  [1, 2, 3],
  [4, 5, 6],
  [7, 8, 0],
];

solve(initial, final);
