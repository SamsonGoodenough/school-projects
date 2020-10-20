#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>

typedef struct passenger{
	char passenger_name [20];
	char pClass[10];
	char  pNo[10];
} passenger_a;

typedef struct queue_node {
	passenger_a pas;
	struct queue_node *restp;
} queue_node_t;

typedef struct {
	queue_node_t *frontp, *rearp;
	int size;
} queue_t;

// Create the following functions:
void scan_passenger(passenger_a *passp);
void print_passenger(passenger_a pass);
void add_to_q(queue_t *qp, passenger_a ele);
passenger_a remove_from_q(queue_t *qp);
void display_q(queue_t q);

int main(void) {

	// passenger queue - initialized to empty state
	queue_t pass_q = {NULL, NULL, 0};
	passenger_a next_pass, fst_pass;

	char choice;

    // Processes requests
	do {
		printf("Enter A(dd), R(emove), D(isplay), or Q(uit)> ");
		scanf(" %c", &choice);

		switch (toupper(choice)) {
		case 'A':
			printf("Enter passenger data> ");
			scan_passenger(&next_pass);
			add_to_q(&pass_q, next_pass);
			break;

		case 'R':
			if (pass_q.size > 0) {
				fst_pass = remove_from_q(&pass_q);
				printf("Passenger removed from queue: \n");
				print_passenger(fst_pass);
			} else {
				printf("Queue empty - no one to delete\n");
			} break;

		case 'D':
			if (pass_q.size > 0)
				display_q(pass_q);
			else
				printf("Queue is empty\n");
			break;

		case 'Q':
			printf("Leaving passenger queue program with %d \n",pass_q.size);
			printf("passengers in the queue\n");
			break;

		default:
			printf("Invalid choice -- try again\n");
		}
	} while (toupper(choice) != 'Q');

	return 0;
	}

void add_to_q(queue_t *qp,  passenger_a ele)
{
	queue_node_t *n = malloc(sizeof(queue_node_t));	//create new node
	n->pas = ele;							// init new node with values
	n->restp = NULL;

	if (qp->size <= 0){
		qp->rearp = n;					// set front and rear to n
		qp->frontp = n;
	} else {
		qp->rearp->restp = n;		// current rear point to n
		qp->rearp = n;					// set rear to n
	}

	qp->size++;
}

// Removes and frees first node of queue, returning value stored there.
// Pre-condition: queue is not empty
passenger_a remove_from_q(queue_t *qp) {

	//initial queue value which is to be returned
	passenger_a ans;
	ans = qp->frontp->pas;
	
	queue_node_t *temp = qp->frontp;	// create a temporary pointer to the front
	qp->frontp = qp->frontp->restp;		// set new front

	free(temp);												// free the old front
	qp->size--;												// decrease size

	if (qp->size == 0){
		qp->rearp = NULL;
	}

	return ans;
}

// display all passengers info. in the queue
void display_q(queue_t q){

	queue_node_t *current_node = q.frontp;	// pointer to front node

	while (current_node != NULL){						// traverse through queue
		print_passenger(current_node->pas);
		current_node = current_node->restp;
	}
	
}

// load passenger data
void scan_passenger(passenger_a *passp){

	// added \t to make listings simpler to read
	printf("\n\tEnter Name: ");
	scanf("%s", passp->passenger_name);

	printf("\tEnter Class: ");
	scanf("%s", passp->pClass);

	printf("\tEnter Passenger Number: ");
	scanf("%s", passp->pNo);

	printf("\n"); // makes commands easier to read

}

// print the info. of the removed passenger from the queue
void print_passenger(passenger_a pass){

	// added \t to make listings simpler to read
	printf("\tName: %s\n", pass.passenger_name);
	printf("\tClass: %s\n", pass.pClass);
	printf("\tPassenger Number: %s\n\n", pass.pNo);
    
}
