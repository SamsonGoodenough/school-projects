#include<stdio.h> 
#include<stdlib.h> 

typedef struct node{
  struct  node *left;
  struct  node *right;
  int data;
  int height;
} node;

int max(int a, int b){
  return (a > b) ? a : b;
} 

node *insertInTree(node *T, int x);
node *newNode(int x);
node *deleteFromTree(node *T, int x);
void inorder(node *T);
int getTreeHeight(node *T);
node *rotateRight(node *x);
node *rotateLeft(node *x);
node *rrRotation(node *T);
node *llRotation(node *T);
node *lrRotation(node *);
node *rlRotation(node *T);
int balanceFactor(node *T);

struct node* newNode(int data) {
  node* n = (node*)malloc(sizeof(node));
  n->data = data;
  n->left = NULL;
  n->right = NULL;
  n->height = 1;
  return(n);
} 

int getTreeHeight(node *T){
  if (T)
    return T->height;
  return 0;
}

int balanceFactor(node *T){
  return getTreeHeight(T->left) - getTreeHeight(T->right);
}

node *rotateRight(node *x){
  node *y = x->left;
  node *next = y->right;

  y->right = x;
  x->left = next;

  x->height = max(getTreeHeight(x->left), getTreeHeight(x->right)) + 1;
  y->height = max(getTreeHeight(y->left), getTreeHeight(y->right)) + 1;

  return y;
}

node *rotateLeft(node *x){
  node *y = x->right;
  node *next = y->left;

  y->left = x;
  x->right = next;

  x->height = max(getTreeHeight(x->left), getTreeHeight(x->right)) + 1;
  y->height = max(getTreeHeight(y->left), getTreeHeight(y->right)) + 1;

  return y;
}

node *rrRotation(node *T){
  return rotateLeft(T);
}

node *llRotation(node *T){
  return rotateRight(T);
}

node *lrRotation(node *T){
  T->left = rotateLeft(T->left);
  return rotateRight(T);
}

node *rlRotation(node *T){
  T->right = rotateRight(T->right);
  return rotateLeft(T);
}

node* leftestLeaf(node* n){
  node* current = n;

  while (current->left) 
    current = current->left;

  return current;
} 

node *insertInTree(node *T, int x){
  if (!T)
    return newNode(x);

  if (x < T->data){
    T->left = insertInTree(T->left, x);
  } else if (x > T->data){
    T->right = insertInTree(T->right, x);
  } else {
    return T; // duplicate data
  }

  T->height = max(getTreeHeight(T->left), getTreeHeight(T->right)) + 1;

  int b = balanceFactor(T);

  if (b > 1){ // left
    if (x < T->left->data){ // left-left
      return llRotation(T);
    } else if (x > T->left->data){ // left-right
      return lrRotation(T);
    }
  } else if (b < -1){ // right
    if (x > T->right->data){ // right-right
      return rrRotation(T);
    } else if (x < T->right->data){ // right-left
      return rlRotation(T);
    }
  }

  return T;
}

node* deleteFromTree(node* T, int x) {
  if (T == NULL) // dead end
    return T;

  if (x < T->data){ // recurr through left subtree
    T->left = deleteFromTree(T->left, x);
  } else if (x > T->data){ // recurr through right subtree
    T->right = deleteFromTree(T->right, x);
  } else { // found it
    if ((!T->left) || (!T->right)){ // <= 1 child
      node *temp = T->left ? T->left : T->right;

      if (!temp){ // no child
        temp = T;
        T = NULL;
      } else { // one child 
        *T = *temp;
      }

      free(temp);
    } else { // > 1 child
      node* temp = leftestLeaf(T->right); // grab leftmost node in subtree

      T->data = temp->data; // store it

      T->right = deleteFromTree(T->right, temp->data); // recurr
    } 
  } 

  // ------------POST DELETION------------
  if (!T) 
    return T;

  T->height = max(getTreeHeight(T->left), getTreeHeight(T->right)) + 1; // set new height

  int b = balanceFactor(T); // check for inbalance

  if (b > 1){ // left
    if (balanceFactor(T->left) >= 0){ // left-left
      return llRotation(T);
    } else if (b > 1 && balanceFactor(T->left) < 0){ // left-right
      return lrRotation(T);
    }
  } else if (b < -1){ // right
    if (b < -1 && balanceFactor(T->right) <= 0){ // right-right
      return rrRotation(T);
    } else if (b < -1 && balanceFactor(T->right) > 0){ // right-left
      return rlRotation(T);
    }
  }

  return T;
}

void inorder(node *T){
  if (T){
    inorder(T->left);
    printf("%c ", T->data);
    inorder(T->right);
  }
}

int main(){
  node *root = NULL;

  int run = 1;
  int input, character;
  char temp;

  while(run){
    printf("\n1. Insert into tree\n2. Delete from tree\n3. Inorder traversal of tree\n4. Quit\nEnter choice: ");
    scanf("%d", &input);

    switch (input){
      case 1:
        printf("Enter character: ");
        scanf(" %c", &temp);
        character = (int)(temp);
        root = insertInTree(root, character);
        break;

      case 2:
        printf("Enter character: ");
        scanf(" %c", &temp);
        character = (int)(temp);
        root = deleteFromTree(root, character);
        break;

      case 3:
        inorder(root);
        break;

      case 4:
        run = 0;
        break;

      default:
        printf("Invalid Input\n");
    }
  }
  
  return 0;
}