package com.piyush.geeksforgeeks;

public class CheckIfArrayRepresentsPreOrderOfBST {

  class Node{
    int val;
    Node left;
    Node right;

    public Node(int val){
      this.val = val;
    }
  }

  class Elem {
    int index = 0;
  }

  boolean canRepresentBST(int pre[], int n) {
    Elem elem = new Elem();
    elem = preOrder(elem, pre, pre[0], 0);
    if(elem.index == n - 1){
      return true;
    }
    if(pre[elem.index] < pre[0]){
      return false;
    }

    preOrder(elem, pre, pre[0], 1);
    if(elem.index == n - 1){
      return true;
    }

    return false;
  }

  Elem preOrder(Elem elem, int[] pre, int rootVal, int leftOrRightSubtree){

    int currIndex = elem.index;
    if(currIndex >= pre.length -1){
      return elem;
    }

    Node newNode = new Node(pre[elem.index]);
    elem.index = elem.index + 1;
    if(pre[elem.index] < newNode.val){
      elem = preOrder(elem, pre, rootVal, leftOrRightSubtree);
    } else if(pre[elem.index] > newNode.val){
      if((leftOrRightSubtree == 0 && pre[elem.index] < rootVal) ||
          (leftOrRightSubtree == 1 && pre[elem.index] > rootVal)
      ){
        elem = preOrder(elem, pre, rootVal, leftOrRightSubtree);
      }
    }

    return elem;
  }



  public static void main(String args[]) {
    CheckIfArrayRepresentsPreOrderOfBST bst = new CheckIfArrayRepresentsPreOrderOfBST();
//    int[] pre1 = new int[]{40, 30, 35, 80, 100};
//    int n = pre1.length;
//    if (bst.canRepresentBST(pre1, n)) {
//      System.out.println("true");
//    } else {
//      System.out.println("false");
//    }
    int[] pre2 = new int[]{40, 30, 35, 20, 80, 100};
    int n1 = pre2.length;
    if (bst.canRepresentBST(pre2, n1)) {
      System.out.println("true");
    } else {
      System.out.println("false");
    }
  }

}
