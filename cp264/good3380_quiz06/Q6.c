#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define MAX(a,b) ((a) > (b) ? a : b)
#define MIN(a,b) ((a) < (b) ? a : b)

struct Node{
	int key;
	struct Node *left, *right;
};

struct Node* newNode(int key){
	struct Node *p = (struct Node*)malloc(sizeof(struct Node));
  p->key = key;
  p->left = NULL;
  p->right = NULL;

  return p;
};

void inorder(struct Node *root){
  if(root != NULL){
    inorder(root->left);
		printf("%d ",root->key);
		inorder(root->right);
  } else {
    return;
  }
};

bool isBalanced(struct Node *root, int *maxh, int *minh){
  if (root == NULL) { 
    *maxh = *minh = 0; 
    return true; 
  }

  int left_maxh, left_minh;
  int right_maxh, right_minh;

  if (isBalanced(root->left, &left_maxh, &left_minh) == false){ // check left subtree
    return false; 
  }

  if (isBalanced(root->right, &right_maxh, &right_minh) == false){ // check right subtree
    return false;
  }

  *maxh = MAX(left_maxh, right_maxh) + 1; // set to max of the subtress
  *minh = MIN(left_minh, right_minh) + 1; // set to min of the subtress

  //RULE: max height must always be less than two times the min
  if ((*maxh) <= (*minh) * 2){
    return true; 
  } else {
    return false; 
  }
}

// A wrapper over isBalanced()
bool isHeightBalanced(struct Node *root){
  int maxh, minh;
	return isBalanced(root, &maxh, &minh);
}

void testCase1(){

	struct Node * root = newNode(40);
	root->left = newNode(10);
	root->right = newNode(100);
	root->right->left = newNode(60);
	root->right->right = newNode(150);

	if (isHeightBalanced(root)) {
		printf("\nBalanced\n");
		inorder(root);
	} else {
		printf("\nNot Balanced\n");
		inorder(root);
	}
}

void testCase2(){

	struct Node * root = newNode(12);
	root->left = newNode(14);
	root->left->left = newNode(16);

	if (isHeightBalanced(root)) {
		printf("\nBalanced\n");
		inorder(root);
	} else {
		printf("\nNot Balanced\n");
		inorder(root);
	}
}

void testCase3(){

	struct Node * root = newNode(70);
	root->left = newNode(40);
	root->right = newNode(90);
	root->right->left = newNode(80);
	root->right->right = newNode(110);
	root->right->left->left = newNode(79);
	root->left->left = newNode(30);
	root->left->right = newNode(50);
	root->left->left->left = newNode(10);

	if (isHeightBalanced(root)) {
		printf("\nBalanced\n");
		inorder(root);
	} else {
		printf("\nNot Balanced\n");
		inorder(root);
	}
}


/* Driver program to test above functions*/
int main()
{
	testCase1();
	testCase2();
	testCase3();

	return 0;
}