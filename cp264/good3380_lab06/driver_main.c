/*
-------------------------------------
File:    driver_main.c
Project: stack_queue
file description
-------------------------------------
Author:  Heider Ali
ID:      9999999999
Email:   heali@wlu.ca
Version  2020-10-29
-------------------------------------
 */
/* This lab give you a practice on how to build and test stacks
 * and queues. You have to give or complete the given code to
 * get the required output
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <assert.h>
#include <limits.h>

#include "llstack.h"
#include "llqueue.h"
//----- TEST DRIVER -----
int main( )
{

  ItemType v;
  // Node_t* tmp;

  // Stack testing
  printf("Stack\n=====\n");
  Stack_t stack = stackCreate();
  assert(stackIsEmpty(stack));
  assert(stackSize(stack) == 0);

  Stack_t *ps = &stack;
  
  v = "4";
  stackPush(ps, v);
  v = "6";
  stackPush(ps, v);
  v = "2";
  stackPush(ps, v);
  v = "P";
  stackPush(ps, v);
  v = "C";
  stackPush(ps, v);

  printf("Stack: ");
  stackPrint(stack);
  
  v = "6";
  stackPush(ps, v);
  v = "B";
  stackPush(ps, v);
  v = "A";
  stackPush(ps, v);
  v = "L";
  stackPush(ps, v);

  printf("Stack: ");
  stackPrint(stack);

  stackPop(ps);
  stackPop(ps);
  stackPop(ps);
  stackPop(ps);

  printf("Stack: ");
  stackPrint(stack);

  stackPopAll(ps);

  printf("Stack: ");
  stackPrint(stack);

  stackDestroy(ps);

  // Queue testing
  printf("Queue\n=====\n");
  Queue_t q = qCreate();
  assert(qIsEmpty(q));
  assert(qLength(q) == 0);

  Queue_t *pq = &q;

  v = "-";
  qEnqueue(pq, v);
  v = "H";
  qEnqueue(pq, v);
  v = "a";
  qEnqueue(pq, v);
  v = "l";
  qEnqueue(pq, v);
  qEnqueue(pq, v);
  v = "o";
  qEnqueue(pq, v);

  printf("Stack: ");
  qPrint(q);

  qDequeue(pq);

  printf("Stack: ");
  qPrint(q);

  v = "w";
  qEnqueue(pq, v);
  v = "e";
  qEnqueue(pq, v);
  qEnqueue(pq, v);
  v = "n";
  qEnqueue(pq, v);

  printf("Stack: ");
  qPrint(q);

  qClear(pq);

  printf("Stack: ");
  qPrint(q);
  
  qDestroy(pq);
}

