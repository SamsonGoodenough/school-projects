#include <stdio.h> //i686-w64-mingw32-gcc main.c -o main.exe
#include <stdlib.h>

struct Node{
  int key;
  struct Node *left, *right;
};

// function prototypes
struct Node* newNode(int key); // create a new binary tree node having given key
// void inorder(struct Node* root); // recursive function to perform in-order traversal of a binary tree
void inorder(struct Node* root, int layer); // recursive function to perform in-order traversal of a binary tree
struct Node* constructBST(int preorder[], int start, int end); //recursive function to build a BST from a preorder sequence

int main(int argc, char *argv[]) {
  setbuf(stdout, NULL); // turns standard output buffering off

  int keys[] = {15, 10, 8, 12, 20, 16, 25};
  int len = sizeof(keys) / sizeof(keys[0]);
  struct Node *root = constructBST(keys, 0, len);

  inorder(root, 0);

  return (0);
}

struct Node* newNode(int key){
  struct Node *p = (struct Node *)malloc(sizeof(struct Node));
  p->key = key;
  p->left = NULL;
  p->right = NULL;

  return p;
}

// void inorder(struct Node* root){
//   if (root != NULL){
//     inorder(root->left);
//     printf("%d ", root->key);
//     inorder(root->right);
//   }

//   return;
// }

void inorder(struct Node* root, int layer){
  if (root != NULL){
    inorder(root->left, layer+1);
    printf("[%d]%d ", layer, root->key);
    inorder(root->right, layer+1);
  }

  return;
}

// struct Node* constructBST(int preorder[], int start, int end){
//   struct Node *parent = newNode(preorder[0]);

//   for (int i = 1; i < end; i++){
//     struct Node *node = newNode(preorder[i]);
//     struct Node *current = parent;
//     int found = 0;

//     while (!found){
//       if (node->key < current->key){
//         if (current->left != NULL){
//           current = current->left;
//         } else {
//           current->left = node;
//           found = 1;
//         }
//       } else if (node->key > current->key){
//         if (current->right != NULL){
//           current = current->right;
//         } else {
//           current->right = node;
//           found = 1;
//         }
//       } else {
//         // fuch its equal
//         found = 1;
//       }
//     }
//   }

//   return parent;
// }

struct Node* constructBST(int preorder[], int start, int end){
  if (start < end){
    struct Node *root = newNode(preorder[start]);

    if (preorder[start + 1] < preorder[start]){
      root->left = constructBST(preorder, start + 1, end);
    } else if (preorder[start + 1] > preorder[start]){
      root->right = constructBST(preorder, start + 1, end);
    }

    // root->left = constructBST(preorder, 2*start+1, end);
    // root->right = constructBST(preorder, 2*start+2, end);

    return root;
  }

  return NULL;
}