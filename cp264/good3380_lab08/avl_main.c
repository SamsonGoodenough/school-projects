/*
-------------------------------------
File:    avl_main.c
Project: avl_tested
file description
-------------------------------------
Author:  Heider Ali
ID:      9999999999
Email:   heali@wlu.ca
Version  2020-11-15
-------------------------------------
 */
#include <stdio.h>
#include <stdlib.h>
#include "avl_tree.h"

#ifndef NULL
#define NULL   ((void *) 0)
#endif



int main()
{
	int choice,key;
	struct node *root = NULL;

	while(1)
	{
		setbuf(stdout, NULL);
		printf("\n");
		printf("1.Insert\n");
		printf("2.Display\n");
		printf("3.Quit\n");

		printf("\nEnter your choice : ");
		scanf("%d",&choice);

		switch(choice)
		{
		 case 1:
			printf("\nEnter the key to be inserted : ");
			scanf("%d",&key);
			root = insert(root,key);
			break;

	case 2:
			printf("\n");
			display(root,0);
			printf("\n");
			break;

	case 3:
			exit(1);

		 default:
			printf("Wrong choice\n");

		}/*End of switch */
	}/*End of while */

  return 0;

}/*End of main()*/

