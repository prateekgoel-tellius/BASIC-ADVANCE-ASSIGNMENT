package assignment1basic

object BinaryTreeExercise extends App{
  sealed trait BinaryTree[+A]

  case class Node[+A](value: A, left: BinaryTree[A], right: BinaryTree[A]) extends BinaryTree[A] // node which have a left child and a right child
  case object Empty extends BinaryTree[Nothing]

  object BinaryTree {
    def height[A](tree: BinaryTree[A]): Int = tree match {
      case Empty => 0 // if our tree is empty, then we are just returning the value 0
      case Node(_, left, right) => 1 + math.max(height(left), height(right)) // if we are having a Node, then we are just taking 1+max(left,right)
    }

    def countLeaves[A](tree: BinaryTree[A]): Int = tree match {
      case Empty => 0 // if the tree is empty, it doesn't contain any node so no leaf node are there
      case Node(_, Empty, Empty) => 1 // if the node have empty left and right child then that node is a leaf node
      case Node(_, left, right) => countLeaves(left) + countLeaves(right) //if node contain left or right child that it is not an leaf node and move to the next level to check for the leaf's node
    }

    def preorderTraversal[A](tree: BinaryTree[A]): List[A] = tree match {
      case Empty => Nil
      case Node(value, left, right) => value :: preorderTraversal(left) ::: preorderTraversal(right)
        /*
        The preorder property of an tree is  - Root.value + preorder(root -> leftChild) + preorder(root -> rightChild)
        we are returning a list which contain the preorder traversal of the node.
        */
    }
  }

  // tree input
//  val tree: BinaryTree[Int] = Node(1, Node(2, Node(4, Empty, Empty), Node(5, Empty, Empty)), Node(3, Empty, Node(6, Empty, Empty)))
//  val tree: BinaryTree[Int] = Node(1, Node(2, Node(3,Node(4,Node(5,Empty,Empty),Empty),Empty),Empty),Empty)
  val tree  = Node(1,Empty,Node(2,Empty,Node(3,Empty,Node(4,Empty,Node(5,Node(6,Node(8,Empty,Empty),Node(7,Empty,Empty)),Empty)))))
  /*
          1
         / \
            2
             \
              3
               \
                4
                 \
                  5
                 / \
                6
               / \
              8   7

  */
  val height = BinaryTree.height(tree)
  println(s"Height of the tree is: $height")

  val leafCount = BinaryTree.countLeaves(tree)
  println(s"Number of leaf nodes in the tree is: $leafCount")

  val preorder = BinaryTree.preorderTraversal(tree)
  println(s"Preorder traversal of the tree is: $preorder")

}
