/*
 -----------------------------------------
 Author:             Samson Goodenough
 ID:                 190723380
 Email:              good3380@mylaurier.ca
 Version             2020-11-27
 -----------------------------------------
 function(s) / structures created:
  - linked list
    - listNode
    - list
    - append()
    - search()
  
  - encode()
  - getCodes()
  
 -----------------------------------------
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define MAX 127 // number of possible characters in ascii table
#define BITLIMIT 256 // max number of bits pushed at any given time

/*
----------------------------------------------------------
typedef struct listNode
----------------------------------------------------------
Variables:
  letter  - the letter to be represented as a binary code
  code    - the binary path that is used to traverse tree
              (0 = traverse left, 1 = traverse right)
              (end of code denoted by -1)
  next    - next listNode in the linked list
----------------------------------------------------------
*/
typedef struct listNode {
  char letter;
  int *code;
  struct listNode* next;
} listNode;

/*
----------------------------------------------------------
typedef struct list (linked list) (letter-code pairs)
----------------------------------------------------------
Variables:
  head    - first listNode in the linked list
  tail    - last listNode in the linked list
----------------------------------------------------------
*/
typedef struct list { 
  listNode* head;
  listNode* tail;
} list;

// Function prototypes

void getCodes(letterNode *root, list *letterCodes, int path[], int step);
listNode* append(list *l);
listNode* search(list *l, char key);

/*
----------------------------------------------------------
Encodes a given string into a binary output file
Use: encode(dict_tree, output_file_name, string);
----------------------------------------------------------
Parameters:
  letterNode *root    - tree used to generate codes
  char output_name[]  - string holding output file's name
  char string[]       - string to be encoded
Returns:
  true if encoded successfully
----------------------------------------------------------
*/
int encode(letterNode *root, char output_name[], char string[]){
  int path[MAX]; // used to track encoding path
  int index = 0; // used to track placement in tree

  // create an empty linked list to store the letter-code pairs
  list *letterCodes;
  letterCodes = (list*)malloc(sizeof(list));
  letterCodes->head = NULL;
  letterCodes->tail = NULL;

  // fill linked list with all letter-code pairs
  getCodes(root, letterCodes, path, index);

  listNode *curr = letterCodes->head;
  while (curr){
    int i = 0;
    while (curr->code[i] != -1){
      i++;
    }
    curr = curr->next;
  }

  FILE *w;
  w = fopen(output_name, "wb"); // open the output file

  if (!w){ // check if file opened successfully
    fprintf(stderr,
      "INVALID OUTPUT FILE: cannot find file '%s'\n", output_name);
    return 0;
  }

  unsigned long long masterCode = 0; // this code can hold more than one letter's code (max size 8-bits)
  unsigned int bit_count = 0;
  unsigned int bytes = 0;

  for (int i = 0; i < strlen(string); i++){ // loop through all letters of the string
    listNode *pair = search(letterCodes, string[i]); // search linked list for letter-code pair

    unsigned int j = 0;
    while (*(pair->code+j) != -1){ // read through code
      if (bit_count >= 8){
        fwrite(&masterCode, 1, 1, w); // write byte
        masterCode = 0; // reset master code
        bit_count = 0;
        bytes++;
      } else {
        masterCode <<= 1; // append bit

        if (*(pair->code+j) == 1){ // set 1
          masterCode += 1;
        }

        bit_count++;
        j++;
      }
    }
  }

  fwrite(&masterCode, 1, 1, w); // push remainging to the file and close it
  masterCode = (unsigned long long)(8-bit_count);
  fwrite(&masterCode, 1, 1, w); // write how many bits to skip when reading remaining byte
  
  fclose(w);

  return 1;
}

/*
----------------------------------------------------------
Finds all the letter-code pairs and stores them in linked list
Use: getCodes(root, letterCodes, path, index); (Recursive)
----------------------------------------------------------
Parameters:
  letterNode *root    - tree that holds letter-code dict
  list *letterCodes   - linked list for storage
  int path[]          - current path for given code
  int step            - tracks direction in tree
Returns:
  None
----------------------------------------------------------
*/
void getCodes(letterNode *root, list *letterCodes, int path[], int step){
  if (root->left){ // check if left subtree exists
    path[step]=0;
    getCodes(root->left, letterCodes, path, step + 1); // traverse left
  }

  if (root->right){ // check if right subtree exists
    path[step]=1;
    getCodes(root->right, letterCodes, path, step + 1); // traverse right
  }

  if(!root->left && !root->right){ // check if leaf
    int *binaryNum = malloc(BITLIMIT);
    unsigned int i = 0;
    for (int n = 0; n < step; n++){
      if (path[n] == 0){ // set 0
        binaryNum[i] = 0;
        i++;
      } else { // set 1
        binaryNum[i] = 1;
        i++;
      }
    }
    binaryNum[i] = -1; // end of code number

    listNode *n = append(letterCodes); // append code and letter to linked list
    n->letter = root->letter;
    n->code = binaryNum;
  }
}

/*
----------------------------------------------------------
Appends a new node on the end of the linked list
Use: listNode *n = append(letterCodes);
----------------------------------------------------------
Parameters:
  list *l - linked list holding all nodes
Returns:
  listNode* - pointer to newly created node
----------------------------------------------------------
*/
listNode* append(list *l){
  listNode *n; // make a new node
  n = (listNode*)malloc(sizeof(listNode)); // allocate space
  n->next = NULL;

  if (!l->head){ // if list is empty set head and tail
    l->head = n;
    l->tail = l->head;
  } else { // if nodes already in list, append it
    l->tail->next = n;
    l->tail = l->tail->next;
  }

  return n; // return new node
}

/*
----------------------------------------------------------
Searches linked list for a given letter key
Use: listNode *ln = search(letterCodes, string[i])->code);
----------------------------------------------------------
Parameters:
  list *l   - list holding all letter-code pairs
  char key  - letter to be searched for
Returns:
  curr      - node containing the keyed pair, or null
----------------------------------------------------------
*/
listNode* search(list *l, char key){
  listNode *curr = l->head; // start at head
  while (curr && curr->letter != key){ // traverse
    curr = curr->next;
  }

  return curr;
}