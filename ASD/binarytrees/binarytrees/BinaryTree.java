package binarytrees;
/*
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import static java.awt.GraphicsEnvironment.*;
*/
public class BinaryTree {

  //static int numFrame = 0;

  protected class Node {
    Integer element;
    Node left, right;

    Node(int element) {
      this.element = element;
      left = right = null;
    }

    Node(int element, Node left, Node right) {
      this.element = element;
      this.left = left;
      this.right = right;
    }

    boolean isLeaf() {
      return left == null && right == null;
    }
  }

  protected Node root;

  public BinaryTree() {
    root = null;
  }

  public void display() {
    display(root, 0);
  }

  protected void display(Node node, int k) {
    if(node != null) {
      display(node.right, k+1);
      for(int i = 0; i < k; i++) System.out.print("   ");
      System.out.println(node.element);
      display(node.left, k+1);
    }
  }

  public boolean isEmpty() {
    return root == null;
  }

  public void add(int element, String path) {
    // ...
  }

  protected Node add(int elem, String path, Node nd) {
    // ...
    return nd;
  }

  public void printPreOrder() {
    // ...
  }

  protected void printPreOrder(Node node) {
     // ...
  }

  public void printInOrder() {
     // ...
  }

  // ...

  public void printPostOrder() {
    // ...
  }

  // ...

  protected int height(Node node) {
    // ...
    return 1000;
  }

  public int height() {
    return 1000; // ...
  }

  public int somma() {
    return 1000; // ...
  }

  // ...

  public int size() {
    return 1000; // ...
  }

  // ...

  public int numFoglie() {
    return 1000;
  }

  // ...


/** da' come risultato true se l'elemento x si trova in this,
    false altrimenti  */
  public boolean search(int x) {
    return false;
  }

 // ...


  public void reflect() {
    // ...
  }

  // ...

  public void increment() {
    // ...
  }

  // ...

  public void trim(int h) {
     // ...
  }

// ...

  public BinaryTree trimmed(int h) {
   // ...
    return null;
  }

  // ...

  public BinaryTree specular() {
    BinaryTree t = new BinaryTree();
    // ...
    return t;
  }

  // ...

  private boolean uguali(Node node1, Node node2) {
    return false;  // ...
  }

  public boolean ugualeA(BinaryTree t) {
    return false; // ...
  }

  public boolean equals(Object ob) {
    if(ob == null) return false;
    if(getClass() != ob.getClass()) return false;
    BinaryTree t = (BinaryTree) ob;
    return uguali(root, t.root);
  }

  public BinaryTree copy() {
    // ...
    return null;
  }

  // ...


  public boolean isComplete() {
    return isComplete(root) > -2;
  }

  private int isComplete(Node nd) {
    // ...
    return 1000;
  }

/** classe di oggetti coppia booleano-nodo,
    utile per realizzare il sottostante
    metodo removeSubtree
*/
  private class BoolNode {
    boolean fatto;
    Node nodo;

    BoolNode(boolean fatto, Node nodo) {
      this.fatto = fatto;
      this.nodo = nodo;
    }
  }

/** Esercizio opzionale:
   elimina il sottoalbero di radice x;
   se l'elemento x e' presente piu' volte,
   elimina uno solo dei sottoalberi di radice x
   (il primo che trova);
   se l'eliminazione e' stata effettuata,
   restituisce true;
   se invece l'elemento x non e' presente,
   restituisce false
 */
  public boolean removeSubtree(int x) {
    return removeSubtree(x, root).fatto;
  }

  private BoolNode removeSubtree(int x, Node nd) {
    // ...
    return null;
  }

/** Un nodo U si dice nodo-cardine di un albero
    se il suo livello (o profondita') nell'albero
    e' uguale all'altezza del sottoalbero
    avente per radice U.
    Vedi libro di testo pag. 95.
*/

/** Scrive sulla console, su una sola riga,
    i (valori dei) nodi-cardine dell'albero
 */
  public void scriviCardini() {
    System.out.print("nodi-cardine: ");
    // ...
    System.out.println();
  }

  private int cardini(Node nd, int h) {
    // ...
    return 1000;
  }

}


