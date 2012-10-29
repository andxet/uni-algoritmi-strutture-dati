package peretti.binarytrees;

import peretti.bintree.BinTree;

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

  public BinaryTree(Node t) {
	root = t;
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
	  root = add(element, path, root);
  }

  protected Node add(int elem, String path, Node nd) {
		if (nd == null)
			return new Node(elem);
		if (path.length() == 0) {
			nd.element = elem;
			return nd;
		}
		char go = path.charAt(0);
	    String nextPath = path.substring(1);
	    if(go == 'L')
	    	nd.left = add(elem, nextPath, nd.left);
	    else if(go == 'R')
	    	nd.right = add(elem, nextPath, nd.right);
	    else
	    	throw new IllegalArgumentException("Bad string");
	    return nd;
  }

  public void printPreOrder() {
    printPreOrder(root);
  }

  protected void printPreOrder(Node t) {
	  if(t==null)
			return;

		System.out.print(t.element+" ");
		printPreOrder(t.left);
		printPreOrder(t.right);
  }

  public void printInOrder() {
     printInOrder(root);
  }

  protected void printInOrder(Node t){
	  if(t==null)
		  return;
	  
	  printInOrder(t.left);
	  System.out.print(t.element+" ");
	  printInOrder(t.right);
  }

  public void printPostOrder() {
	  printPostOrder(root); 
  }

  public void printPostOrder(Node t){
	  if(t==null)
		  return;
	  
	  printPostOrder(t.left);
	  printPostOrder(t.right);
	  System.out.print(t.element+" ");
  }

  protected int height(Node t) {
	  return t==null ? 0 : 1 + Math.max(height(t.left), height(t.right));
  }

  public int height() {
    return height(root);
  }

  protected int somma(Node t){
	  return t == null ? 0 : t.element + somma(t.left) + somma(t.right);
  }
  
  public int somma() {
    return somma(root);
  }

  public int size() {
    return size(root);
  }

  private int size(Node t) {
	  return t == null ? 0 : size(t.left) + size(t.right) + 1;
}

public int numFoglie() {
    return numFoglie(root);
  }


private int numFoglie(Node t) {
	if(t == null)
		  return 0;
	return t.isLeaf() ? 1 : numFoglie(t.left) + numFoglie(t.right);
}

/** da' come risultato true se l'elemento x si trova in this,
    false altrimenti  */
  public boolean search(int x) {
    return search(x, root);
  }

  private boolean search(int x, Node t) {
	if(t == null)
		return false;
	return t.element == x ? true : search(x, t.left) || search(x, t.right);
	
}

public void reflect() {
    reflect(root);
  }

public void reflect(Node t){
	if(t == null)
		  return;
	  Node temp = t.left;
	  t.left = t.right;
	  t.right = temp;
	  reflect(t.left);
	  reflect(t.right);
}

  public void increment() {
    increment(root);
  }

  /** modifica l'albero incrementando di 1 i valori
  di tutti gli elementi */
public static void increment(Node t) {
  if(t == null)
  	return;
  t.element++;
  increment(t.left);
  increment(t.right);
}

  public void trim(int h) {
     root = trim(h, root);//La radice ha altezza 0, quindi la funzione taglia dal livello h (compreso) in gi.
  }

  private Node trim(int h, Node t) {
	  if(t == null || h == 0)
		  return null;
	t.left = trim(h-1, t.left);
	t.right = trim(h-1, t.right);
	return t;
}

public BinaryTree trimmed(int h) {
	return new BinaryTree(trimmed(h, root));
  }

  private Node trimmed(int h, Node t) {
	if(t == null || h == 0)
		return null;
	return new Node(t.element, trimmed(h - 1, t.left), trimmed(h - 1, t.right));
}

  public BinaryTree specular() {
	  return new BinaryTree(specular(root));
  }

  private Node specular(Node t) {
	if (t == null)
		return null;
	return new Node(t.element, specular(t.right), specular(t.left));
}

  private boolean uguali(Node n1, Node n2) {
    return n1 == n2 && uguali(n1.left, n2.left) && uguali(n1, n2);
  }

  public boolean ugualeA(BinaryTree t) {
    return uguali(root, t.root);
  }

  public boolean equals(Object ob) {
    if(ob == null) return false;
    if(getClass() != ob.getClass()) return false;
    BinaryTree t = (BinaryTree) ob;
    return uguali(root, t.root);
  }

  public BinaryTree copy() {
	  return new BinaryTree(copy(root));
  }

  private Node copy(Node t) {
	if (t == null)
		return null;
	return new Node(t.element, copy(t.left), copy(t.right));
}


  public boolean isComplete() {
    return isComplete(root) > -2;// TODO: CERCARE SOLUZIONE
  }

  private int isComplete(Node nd) {
    // ... TODO: isComplete
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
    BoolNode ris = removeSubtree(x, root);
    // root = ...; TODO: facoltativo: removeSubtree
    return ris.fatto;
  }

  private BoolNode removeSubtree(int x, Node nd) {
    // ... //TODO: removeSubtree
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
    // ... TODO: scriviCardini
    System.out.println();
  }

  private int cardini(Node nd, int h) {
    // ... TODO: cardini
    return 1000;
  }

}


