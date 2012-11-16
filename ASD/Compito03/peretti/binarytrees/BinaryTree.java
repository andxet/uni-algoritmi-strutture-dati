package peretti.binarytrees;

import javax.lang.model.element.Element;

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

 private void printPostOrder(Node t){
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
	  if(n1 == n2 && n1 == null)
		  return true;
	  if(n1 == null || n2 == null)// && n1 != n2 (sottinteso)
		  return false;
    return n1.element == n2.element && uguali(n1.left, n2.left) && uguali(n1.right, n2.right);
  }

  public boolean ugualeA(BinaryTree t) {
	  if(t == null)
		  return false;
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
    return isComplete(root) > -2;
  }
  
	/*private int isComplete(Node nd) { // Dovrebbe andare // NO non va
		if (nd.left == null && nd.right == null)
			return 0;
		else if (nd.left != null && nd.right != null) {
			int temp1 = isComplete(nd.left);
			int temp2 = isComplete(nd.right);
			if (temp1 == temp2 && temp1 > -2)
				return -1;
			else
				return -2;
		} else
			return -2;
	}*/
	
	private int isComplete(Node nd){
		if(nd == null)
			return -1;
		int h = isComplete(nd.left);
		if(isComplete(nd.right) == h)
			return h + 1;
		else return -2;
	}
  
  public int numNodiLiv(int liv) {
	    return nodiLiv(liv, root);
	  }
  
  private int nodiLiv(int x, Node t){
	  if(t == null)
		  return 0;
	  if(x == 0)
		  return 1;
	  return nodiLiv(x-1, t.left) + nodiLiv(x-1, t.right);
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
    root = ris.nodo;
    return ris.fatto;
  }

  private BoolNode removeSubtree(int x, Node nd) {
    if(nd == null)
    	return new BoolNode(false, null);;
    if(x == nd.element)
    	return new BoolNode(true, null);
    
    BoolNode b1 = removeSubtree(x, nd.left);
    nd.left = b1.nodo;
    if(b1.fatto){
    	b1.nodo = nd;
    	return b1;
    }
    	
    BoolNode b2 = removeSubtree(x, nd.right);
    nd.right = b2.nodo;
    b2.nodo = nd;
    return b2;
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
    System.out.println("nodi-cardine: ");
    cardini(root, 0);
    System.out.println();
  }

  private int cardini(Node t, int x) {
	  if(t == null)
          return -1;
      int hl = cardini(t.left, x + 1);
      int hd = cardini(t.right, x + 1);    
      int high;
      if(hl >= hd)
          high = hl + 1;
      else
          high = hd + 1;
      if(x == high)
          System.out.println(t.element + "\t");
         
      return high;
  }

}


