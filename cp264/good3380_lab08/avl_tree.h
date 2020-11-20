/*
-------------------------------------
File:    avl_tree.h
Project: avl_tested
file description
-------------------------------------
Author:  Heider Ali
ID:      9999999999
Email:   heali@wlu.ca
Version  2020-11-15
-------------------------------------
 */

#ifndef AVL_TREE_H_
#define AVL_TREE_H_



// void inorder(struct node *ptr);
struct node *RotateLeft(struct node *pptr);
struct node *RotateRight(struct node *pptr);

struct node *insert(struct node *pptr, int ikey);
struct node *insert_left_check(struct node *pptr, int *ptaller);
struct node *insert_right_check(struct node *pptr, int *ptaller);
struct node *insert_LeftBalance(struct node *pptr);
struct node *insert_RightBalance(struct node *pptr);

void display(struct node *ptr,int level);

#endif /* AVL_TREE_H_ */
