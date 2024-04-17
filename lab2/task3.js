class CSP {
  constructor(times, classrooms, classes) {
    this.times = times;
    this.classrooms = classrooms;
    this.classes = classes;
    this.schedule = this.createEmptySchedule(); // Call the method to initialize schedule
  }

  createEmptySchedule() {
    const schedule = [];

    // Fills the schedule with blank strings
    for (let i = 0; i < this.classrooms.length; i++) {
      let row = [];
      for (let j = 0; j < this.times.length; j++) {
        row.push("---");
      }
      schedule.push(row);
    }

    return schedule;
  }

  displaySchedule() {
    console.log("\n" + "Classroom Schedule:");
    console.log("\t" + this.classrooms.join("\t| "));
    console.log("-------------------------------");
    for (let i = 0; i < this.times.length; i++) {
      let scheduleRow = [];
      for (let j = 0; j < this.classrooms.length; j++) {
        scheduleRow.push(this.schedule[j][i]);
      }
      console.log(this.times[i] + "\t| " + scheduleRow.join("\t| "));
    }
    console.log("-------------------------------");
    console.log("\n");
  }

  shuffleArray(array) {
    for (let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
  }

  addClassesRandomly() {
    // May be reversed
    const rows = this.schedule.length;
    const cols = this.schedule[0].length;

    this.shuffleArray(this.classes);

    let counter = 0;

    for (let row = 0; row < rows; row++) {
      for (let col = 0; col < cols; col++) {
        if (counter < this.classes.length) {
          this.schedule[row][col] = this.classes[counter];
        } else {
          this.schedule[row][col] = "---";
        }
        counter++;
      }
    }
  }

  minConflicts(maxSteps) {
    let conflicts = [];
    const cols = this.schedule.length;
    const rows = this.schedule[0].length;

    for (let step = 0; step < maxSteps; step++) {
      // Check for conflicts
      for (let i = 0; i < cols; i++) {
        for (let j = 0; j < rows; j++) {
          if (this.hasConflict(i, j)) {
            conflicts.push({ row: j, col: i, name: this.schedule[i][j] });
          }
        }
      }

      // if (conflicts.length === 0) {
      //   return this.schedule;
      // }
    }

    // console.log(conflicts);

    const randomIndex = Math.floor(Math.random() * conflicts.length);
    const randomClass = conflicts[randomIndex];
    console.log(conflicts);
  }

  countConflics(currentClass) {
    let counter = 0;

    const cols = this.schedule.length;
    const rows = this.schedule[0].length;

    for (let i = 0; i < cols; i++) {
      for (let j = 0; j < rows; j++) {}
    }
  }

  hasConflict(row, col) {
    const currentClass = this.schedule[row][col];

    // Consaint 1: Classes with first digit can not have the same
    const rows = this.schedule.length;
    for (let i = 0; i < rows; i++) {
      let otherClass = this.schedule[i][col];

      if (
        i !== row &&
        otherClass[2] === currentClass[2] &&
        currentClass[2] !== "-"
      ) {
        return true;
      }
    }

    // // Check constraint 2: Classes with the same first digit cannot be scheduled at the same time
    // for (let i = 0; i < this.schedule[row].length; i++) {
    //     if (i !== col && this.schedule[row][i]) {
    //         const otherClass = this.schedule[row][i];
    //         const otherFirstDigit = otherClass[2];
    //         if (firstDigit === otherFirstDigit && classToCheck !== otherClass) {
    //             // Check if it's not the same class (exception: MT501 and MT502)
    //             return true; // Conflict found
    //         }
    //     }
    // }

    return false; // No conflict found
  }
}

// function scheduleClasses(csp) {
//   // Implement class scheduling logic to satisfy constraints

// }

function main() {
  const times = [9, 10, 11, 12, 13, 14, 15, 16];
  const classrooms = ["TP51", "SP34", "K3"];
  const classes = [
    "MT101",
    "MT102",
    "MT103",
    "MT104",
    "MT105",
    "MT106",
    "MT107",
    "MT201",
    "MT202",
    "MT203",
    "MT204",
    "MT205",
    "MT206",
    "MT301",
    "MT302",
    "MT303",
    "MT304",
    "MT401",
    "MT402",
    "MT403",
    "MT501",
    "MT502",
  ];

  const csp = new CSP(times, classrooms, classes);
  const maxSteps = 1;

  csp.addClassesRandomly();

  csp.minConflicts(maxSteps);
  // scheduleClasses(csp);

  csp.displaySchedule();
}

main();
