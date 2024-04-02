// Define the NxN size
const N = 3;

class Node {
  constructor(matrix, x, y, moves, parent) {
    this.parent = parent;

    this.matrix = matrix.map((row) => [...row]);

    this.x = x;
    this.y = y;

    // Stores the number of misplaced tiles
    this.cost = Infinity;

    this.moves = moves;
  }
}

class Tile {
  constructor(number, x, y) {
    this.number = number;
    this.x = x;
    this.y = y;
  }
}

// Function to calculate the number of misplaced tiles
function calculateCost(initial, final) {
  let count = 0;
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      if (initial[i][j] && initial[i][j] !== final[i][j]) {
        count++;
      }
    }
  }

  return count;
}

// Function to find the coordinates of the zero in the matrix
function findZeroCoordinate(matrix) {
  for (let i = 0; i < matrix.length; i++) {
    for (let j = 0; j < matrix[i].length; j++) {
      if (matrix[i][j] === 0) {
        return [i, j];
      }
    }
  }
  // If zero is not found, return null or any other appropriate value
  return null;
}

export function solve(initial, final) {
  let x, y;

  [x, y] = findZeroCoordinate(initial);

  const pq = [];

  const root = new Node(initial, x, y, 0, null);
  root.cost = calculateCost(root.matrix, final);

  pq.push(root);

  while (pq.length > 0) {
    pq.sort(compareNodes);

    // Removes the node with the lowest cost
    const min = pq.shift();

    if (min.cost === 0) {
      console.log(min);
      console.log(printMatrix(min.matrix));
      return;
    }

    let arrayWithMoves = validMoves(min, final);
    pq.push(...arrayWithMoves);
  }
}

function compareNodes(lhs, rhs) {
  const lhsTotal = lhs.cost + lhs.moves;
  const rhsTotal = rhs.cost + rhs.moves;

  if (lhsTotal < rhsTotal) {
    return -1;
  } else if (lhsTotal > rhsTotal) {
    return 1;
  } else {
    return 0;
  }
}

function newNode(matrix, x, y, newX, newY, moves, parent) {
  const node = new Node(matrix, x, y, moves, parent);

  // Move tile by 1 position
  [node.matrix[x][y], node.matrix[newX][newY]] = [
    node.matrix[newX][newY],
    node.matrix[x][y],
  ];

  // Update new blank tile coordinates
  node.x = newX;
  node.y = newY;

  return node;
}

function validMoves(node, final) {
  const moves = [
    { dx: -1, dy: 0 }, // move to the left
    { dx: 1, dy: 0 }, // move to the right
    { dx: 0, dy: -1 }, // move up
    { dx: 0, dy: 1 }, // move down
  ];

  const arrayWithMoves = [];

  for (const move of moves) {
    const newX = node.x + move.dx;
    const newY = node.y + move.dy;

    if (isSafe(newX, newY)) {
      const child = newNode(
        node.matrix,
        node.x,
        node.y,
        newX,
        newY,
        node.moves + 1,
        node
      );
      child.cost = calculateCost(child.matrix, final);
      arrayWithMoves.push(child);
    }
  }

  return arrayWithMoves;
}

// Function to check if (x, y) is a valid matrix coordinate
function isSafe(x, y) {
  return x >= 0 && x < N && y >= 0 && y < N;
}

function printMatrix(matrix) {
  for (let i = 0; i < 3; i++) {
    console.log(matrix[i].join(" "));
  }
}
