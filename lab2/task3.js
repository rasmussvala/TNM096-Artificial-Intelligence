class CSP {
  constructor(times, classrooms, classes) {
    this.times = times;
    this.classrooms = classrooms;
    this.classes = classes;

    // Size of the schedule
    this.rows = this.times.length;
    this.cols = this.classrooms.length;

    this.schedule = this.createEmptySchedule(); // IMPORTANT: TO ACCESS A TIMESLOT USE schedule[col][row]
  }

  // Initialize a empty schedule
  createEmptySchedule() {
    const col = [];

    // Fills the schedule with blank strings
    for (let i = 0; i < this.cols; i++) {
      let row = [];
      for (let j = 0; j < this.rows; j++) {
        row.push("---");
      }
      col.push(row);
    }

    const schedule = col;
    return schedule;
  }

  displaySchedule() {
    console.log("\n" + "Classroom Schedule:");
    console.log("\t" + this.classrooms.join("\t| "));
    console.log("-------------------------------");
    for (let i = 0; i < this.rows; i++) {
      let scheduleRow = [];
      for (let j = 0; j < this.cols; j++) {
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
    this.shuffleArray(this.classes);

    let counter = 0;

    for (let col = 0; col < this.cols; col++) {
      for (let row = 0; row < this.rows; row++) {
        if (counter < this.classes.length) {
          this.schedule[col][row] = this.classes[counter];
        } else {
          this.schedule[col][row] = "---";
        }
        counter++;
      }
    }
  }

  // Main algorithm to solve the CSP
  minConflicts(maxSteps) {
    let conflicts = [];

    for (let step = 0; step < maxSteps; step++) {
      // Check for conflicts
      for (let col = 0; col < this.cols; col++) {
        for (let row = 0; row < this.rows; row++) {
          if (this.hasConflict(row, col)) {
            conflicts.push({
              row: row,
              col: col,
              name: this.schedule[col][row],
            });
          }
        }
      }

      if (conflicts.length === 0) {
        return this.schedule;
      }
    }

    const randomIndex = Math.floor(Math.random() * conflicts.length);
    const randomClass = conflicts[randomIndex];

    // @TODO: check if randomClass has a better place (also check it's current place again)
    //        in the schedule by looking potential conflics
    // @TODO: move the randomClass the the "best" place in the schedule
    // @TODO: if randomclass has no conflict, remove it from the conflicts array
  }

  // Functions that checks whether or not a class has an conflict in the current place
  hasConflict(row, col) {
    const currentClass = this.schedule[col][row];

    for (let i = 0; i < this.cols; i++) {
      let otherClass = this.schedule[i][col];

      if (
        i !== row &&
        otherClass[2] === currentClass[2] &&
        currentClass[2] !== "-"
      ) {
        return true;
      }
    }
  }
}

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
