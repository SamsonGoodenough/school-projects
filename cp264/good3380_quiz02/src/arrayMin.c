#include <stdio.h>
#include <stdlib.h>

int * arrayMin(int * array, int n) {
  if(n <= 0){
    return NULL; // if n is not valid return null
  }              // I'm curious to know if this kind of permissions check is allowed 
                 // to short circuit the function as I see it as the more clear solution

  int min = 0; // use the first element as your "assumed minimum"
  for(int i = 1 ; i < n; i++){ // check all elements other than first
    if(*(array + i) < *(array + min)){ // check if the current element is smaller than the minimum
      min = i;
    }
  }
  
  return array + min;
}

void doTest(int * array, int n) {
  printf("arrayMin(");
  if (array == NULL) {
    printf("NULL");
  }
  else {
    printf("{");
    for (int i =0; i < n; i++) {
      printf("%d", array[i]);
      if (i < n -1) {
        printf(", ");
      }
    }
    printf("}");
  }
  printf(", %d) is \n", n);
  int * p = arrayMin(array, n);
  if (p == NULL) {
    printf("NULL\n");
  }
  else {
    printf("%d\n", *p);
  }
}

int main(void) {
  int array1[] = { 77, 33, 19, 99, 42, 6, 27, 4};
  int array2[] = { -3, -42, -99, -1000, -999, -88, -77};
  int array3[] = { 425, 59, -3, 77, 0, 36};

  doTest (array1, 8);
  doTest (array2, 7);
  doTest (array3, 6);
  doTest (NULL, 0);
  doTest (array1, 0);

  return EXIT_SUCCESS;
}
