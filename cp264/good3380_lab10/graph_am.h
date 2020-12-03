/*
 -------------------------------------
 File:     graph_am.h
 Project:  lab10_am
 Adjacency Matrix Graph header file.
 -------------------------------------
 Author:  David Brown
 ID:      999999999
 Email:   dbrown@wlu.ca
 Version  2019-02-25
 Version  2020-03-25 Rick Magnotta
 Version 2020-11-29 Heider Ali
 -------------------------------------
 */

#ifndef GRAPH_AM_H_
#define GRAPH_AM_H_

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include "My_Definitions.h"

#define  MAX 10

void randomGraph(int adj[][MAX]);

void displayAdjacentMatrix(int adj[][MAX]);

void connected(int adj[][MAX],
		       int node);

void node_counts(int adg[][MAX],
		         int count);

#endif /* GRAPH_AM_H_ */
