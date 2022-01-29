#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
  // Run the first program.
  system("./z_creator &");

  // Print initial list.
  printf("\n\nInitial list of processes:\n\n");
  system("ps -l");
  sleep(2);

  // Kill parent process.
  system("kill -9 $(ps -l|grep -w Z|tr -s ' '|cut -d ' ' -f 5)");
  sleep(2);

  printf("\nThe updated list of processes and their status is:\n\n");
  system("ps -l");

  return 0;
}