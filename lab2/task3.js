class csp {
  constructor(times, classrooms, classes) {
    this.times = times;
    this.classrooms = classrooms;
    this.classes = classes;
  }
}

function minConflicts(csp, maxSteps) {
  for (i = 0; i < maxSteps; i++) {}
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

  const csp = new csp(times, classrooms, classes);
  const maxSteps = 100;

  minConflicts(csp, maxSteps);
}

main();
