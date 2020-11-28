#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define SIZE 9
#define MAX_PASSWORD 128

/*
----------------------------------------------------------
typedef struct listNode
----------------------------------------------------------
Variables:
  key     - key returned by hash
  next    - next listNode in the linked list
----------------------------------------------------------
*/
typedef struct listNode {
  unsigned int key;
  char *password;
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
listNode* appendList(list *l){
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
listNode* searchList(list *l, char key){
  listNode *curr = l->head; // start at head
  while (curr && curr->key != key){ // traverse
    curr = curr->next;
  }

  return curr;
}

int hashCode(int key){
  return key % SIZE;
}

listNode* searchHash(list *hash, int key){
  unsigned int hashIndex = hashCode(key);

  if ((hash + hashIndex))
    return (hash + hashIndex)->head;
  
  return NULL;
}

void insert(list *hash, int key, char password[MAX_PASSWORD]){
  list *l = (list*)malloc(sizeof(list));
  listNode *node = appendList(l);
  node->key = key;
  node->password = strdup(password);

  unsigned int hashIndex = hashCode(key);
  *(hash + hashIndex) = *l;

}

int main(int argc, char **argv) {
  FILE* f = fopen("password.txt", "r");
  char line[MAX_PASSWORD*2];
  list *hash[SIZE+1];

  while (fgets(line, sizeof(line), f)) {
    char *password = strtok(line, " ");
    password = strtok(NULL, " ");
    password = strtok(password, "\n");

    int ascii = 0;
    for(int i = 0; i < strlen(line); i++)
      ascii += (int)line[i];
    
    insert(*hash, ascii, password);
  }

  char user[MAX_PASSWORD];
  char pass[MAX_PASSWORD];
  int i, ascii;
  printf("Enter username: ");
  scanf("%s", user);

  printf("Enter password: ");
  scanf("%s", pass);

  char combo[MAX_PASSWORD*2+1];
  strcat(combo, user);
  strcat(combo, " ");
  strcat(combo, pass);

  ascii = 0;
  for(i = 0; i < strlen(combo); i++)
    ascii += (int)combo[i];

  listNode *l = searchHash(*hash, ascii);
  if (l){
    if (strcmp(l->password, pass)){
      printf("Correct");
    } else {
      printf("Incorrect");
    }
  } else {
    printf("Incorrect");
  }
  

  fclose(f);

  return 0;
}