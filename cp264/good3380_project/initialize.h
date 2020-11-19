/*
 *  -------------------------------------
 Author:             Brian Ha
 ID:                 190376250
 Email:              haxx6250@mylaurier.ca
 Version             2020-11-14
 -------------------------------------
 function(s) / structures created:
 creation of huffman structure and nodes,
 huffman node functions:
 - newNode
 - combine
 - add_to_q
 - pop
 - initalize
 -------------------------------------

 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
//number of possible characters in ascii table
#define MAX 127

//huffman tree node, acts as a linked list node at the same time
// letter: the letter
// direction: either a 0 (left) or 1 (right)
// freq: the frequency of the letter assigned
//*left: pointer to left child
//*right: pointer to right child
//*next: pointer to 'next' node (used in the linked priority queue), should be NULL at the end of the initialization
typedef struct Node {
	char letter;
	int direction;
	unsigned int freq;
	struct Node *left, *right, *next;

} letterNode;

//creates a new huffman tree node, fills it with a given letter with it frequency
letterNode* newNode(char letter, int freq) {
	//creates empty node, size it wit the data of itself
	struct Node *node = (struct Node*) malloc(sizeof(letterNode));

	//assigns it values as if it is a root node
	node->letter = letter;
	node->freq = freq;
	node->left = NULL;
	node->right = NULL;
	node->next = NULL;

	return (node);
}

//combines two nodes into one node, with its frequency being the sum of the two (newly inserted) children nodes
//left is given direction 0 (for encoding purposes)
//right is given direction 1
//letter of combined is '*' to mark it as a combined node
//its 'next' is NULL, as it somewhat becomes a new node
letterNode* combine(letterNode **left, letterNode **right) {
	letterNode *combined = (struct Node*) malloc(sizeof(letterNode));
	(*left)->direction = 0;
	(*right)->direction = 1;
	(*left)->next = NULL;
	(*right)->next = NULL;
	combined->left = (*left);
	combined->right = (*right);
	combined->freq = (*left)->freq + (*right)->freq;
	combined->letter = '*';
	combined->next = NULL;

	//returns the combined nodes (as one new one)
	return combined;
}

//works like a priority queue
//lower frequency = higher priority
void add_to_q(letterNode **head, letterNode **node) {
	letterNode *start = malloc(sizeof((*head)));
	start = (*head);

	// Create new temporary node
	letterNode *temp = malloc(sizeof((*node)));
	temp = (*node);
	int value = (*node)->freq;
	//if the temp node has a higher priority than the head node
	if ((*head)->freq > value) {

		// Insert New Node before head
		temp->next = *head;
		(*head) = temp;
	} else {

		// iterate through the list to find a position to insert new node
		while (start->next != NULL && start->freq < value) {
			start = start->next;
		}

		// Either at the ends of the list or at found location
		temp->next = start->next;
		start->next = temp;
	}

}

//pops the head node from a priority queue of letterNodes
letterNode* pop(letterNode **root) {
	letterNode *node = malloc(sizeof(*root));
	node = *root;
	*root = (*root)->next;
	return node;
}

//creates the huffman tree
letterNode* initialize(char str[]) {
	//creates 2 lists,
	//	- one contains a list of unique letters (uni[])
	// 	- the other is a list of frequencies of the corresponding letters in uni[] via index (num[])
	//2 lists of
	int num[MAX];
	char uni[MAX];

	//set variables that will be affected
	int count = 0;
	int amount = 0;
	//input
	int length = strlen(str);
	char tem_letter;
	char *pt;
	//creates a new string with each letter being unique
	for (int i = 0; i < length; i++) {
		tem_letter = str[i];
		
		pt = strchr(uni, tem_letter);
		if (pt == NULL) {
			uni[count] = tem_letter;
			count++;
		}

	}
	pt = NULL;

	//sets pointer variables after creating the string with 1 of each character from the initial string
	char *p = uni;
	char *temp = str;

	//goes though the unique string and counts how many times each character appears in the main string
	for (int i = 0; i < strlen(uni); i++) {
		for (int x = 0; x < length; x++) {
			if (*p == *(temp + x)) {
				amount++;
			}
		}
		num[i] = amount;
		amount = 0;
		p++;
	}

	//creates a root node, and begins to populate the linked list queue with
	//nodes created with values from uni[] and num[]
	letterNode *root = newNode(uni[0], num[0]);
	for (int i = 1; i < strlen(uni); i++) {
		letterNode *temp = newNode(uni[i], num[i]);
		add_to_q(&root, &temp);

	}

	//creates a temp node to hold the combined nodes
	letterNode *combined;

	//pop first 2 nodes, combine them, insert back into queue
	//continues until the root->next == NULL, AKA there is only the root node left in the linked list queue
	while (root->next != NULL) {
		letterNode *left = pop(&root);
		letterNode *right = pop(&root);
		combined = combine(&left, &right);

		if (root != NULL) {
			add_to_q(&root, &combined);
		} else {
			root = combined;
		}

	}
	//returns the root node of the huffman tree
	return combined;

}

// sample main program to initalize a sample string
int test_initialize() {
	char str[] =
			"this is a short input file composed entirely of \n spaces \n and lowercase letters";
	letterNode *root = initialize(str);
	printf("%c, %d\n", root->letter, root->freq);

}