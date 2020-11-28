/*
-------------------------------------
File:    My_Definitions.h
Project: 202009_CP264_Lab09_Heap
Heap Source Code
 -------------------------------------
Author:  Rick Magnotta/Heider Ali
ID:      xxxxxxxxx
Email:   rmagnotta@wlu.ca/heali@wlu.ca
Version  2020-11-22
-------------------------------------
 */
#ifndef MY_DEFINITIONS_H_
#define MY_DEFINITIONS_H_

#ifdef TRUE
   #undef TRUE
   #undef FALSE
#endif
#define TRUE  (1==1)
#define FALSE (0==1)

typedef unsigned int bool;

#define SUCCESS TRUE
#define FAILURE FALSE

#define DEBUG_LEVEL_01
#define DEBUG_LEVEL_02

#endif /* MY_DEFINITIONS_H_ */
