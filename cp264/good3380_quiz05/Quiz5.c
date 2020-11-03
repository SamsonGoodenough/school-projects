#include <stdio.h>
#include <stdlib.h>

struct Node{
  int key;
  struct Node *left, *right;
};

// function prototypes
struct Node* newNode(int key); // create a new binary tree node having given key
void inorder(struct Node* root); // recursive function to perform in-order traversal of a binary tree
struct Node* constructBST(int preorder[], int start, int end); // recursive function to build a BST from a preorder sequence
int isBST(struct Node* node); // check if the given tree is a BST


int main(int argc, char *argv[]) {
  setbuf(stdout, NULL); // turns standard output buffering off

  int keys[] = {15, 10, 20, 12, 8, 16, 25};
  int len = sizeof(keys) / sizeof(keys[0]); // get length of array
  struct Node *root = constructBST(keys, 0, len); // construct the tree

  inorder(root); // print out tree in order

  printf("\nisBST: %d", isBST(root)); // check if tree is a valid BST

  return (0);
}

struct Node* newNode(int key){
  struct Node *p = (struct Node *)malloc(sizeof(struct Node)); // create new node +pointer and allocate space for it in memory
  p->key = key; // fill node
  p->left = NULL;
  p->right = NULL;

  return p;
}

void inorder(struct Node* root){
  if (root != NULL){
    inorder(root->left); // recursively print left sub-tree
    printf("%d ", root->key);
    inorder(root->right); // recursively print right sub-tree
  }

  return;
}

struct Node* constructBST(int preorder[], int start, int end){
  if (start < end){ // check if at the end of the sub-tree
    struct Node *root = newNode(preorder[start]); // create new 'root' for the sub-tree

    int i = start;
    while (preorder[i] <= preorder[start] && i < end) i++; // find first number larger than root

    root->left = constructBST(preorder, start+1, i); // recursively construct left sub-tree
    root->right = constructBST(preorder, i, end); // recursively construct right sub-tree

    return root;
  }

  return NULL;
}

int isBST(struct Node* node){
  struct Node *prev = NULL; 
      
  if (node != NULL){
    if (!isBST(node->left)) // check left sub-tree
      return 0; 

    if (prev != NULL && node->key <= prev->key) // check if the key of the node is within the bounds of its parent
      return 0; 

    prev = node; // store the child as the new parent

    return isBST(node->right); // check right sub-tree
  } 

  return 1; 
}