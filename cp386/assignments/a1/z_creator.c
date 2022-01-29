#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
  pid_t pid = fork();

  if (pid == 0) { // catch child
    exit(0);
  } else if (pid > 0) { // catch parent
    sleep(100);
  } else {
    return -1;
  }

  return 0;
}