package peretti.unionFind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

public class UnionFindE<E extends Comparable<E>> {
	private static class Node<E> {
		final E element;
		Node<E> parent;
		int size = 0;

		Node(E elem){
			element = elem;
			parent = null;
		}
	}

	HashMap<E, Node<E>> elemMap = new HashMap<E, Node<E>>();

	public UnionFindE(E[] elements) {
		for(E elem: elements) makeSet(elem);
	}
	public UnionFindE(Set<E> setOfElems) {
		for(E elem: setOfElems) makeSet(elem);
	}
	
	public UnionFindE(){
	}

	public void makeSet(E e) {
		if(elemMap.containsKey(e))
			throw new IllegalArgumentException();
		elemMap.put(e,new Node<E>(e));
	}

	public E find(E e) {
		Node<E> node = elemMap.get(e);
		return node==null ? null: findRoot(node).element;
	}

	public E union(E e1, E e2) {
		Node<E> nd1 = elemMap.get(e1);
		Node<E> nd2 = elemMap.get(e2);
		if(nd1 == null || nd2 == null)
			return null;
		Node<E> first1 = findRoot(nd1);
		Node<E> first2 = findRoot(nd2);
		if(first1 == first2)
			return first1.element;
		first1.parent = first2;
		return first2.element;
	}
	
	public boolean kruskalUnion(E e1, E e2) {
		Node<E> nd1 = elemMap.get(e1);
		Node<E> nd2 = elemMap.get(e2);
		if(nd1 == null || nd2 == null)
			return false;
		Node<E> first1 = findRoot(nd1);
		Node<E> first2 = findRoot(nd2);
		if(first1 == first2)
			return false;
		first1.parent = first2;
		return true;
	}


	private Node<E> findRoot(Node<E> node) {
		if(node.parent == null) return node;
		else {
			node.parent = findRoot(node.parent);
			return node.parent;
		}
	}

	/*
	public UnionFindE() {
		parentOrNSize = new Hashtable<E, E>(); 
	}//UnionFindE

	public E find(E j) {
		if (parentOrNSize.get(j) == null)//Se è uguale a null allora è la radice
			return j; // radice
		else {//Altrimenti effettuo la compressione
			E temp = find(parentOrNSize.get(j));
			parentOrNSize.put(j, temp);
			return temp;
		}
	}//find

	public int capacity() {
		return parentOrNSize.size();
	}//capacity

	public boolean union(E u1, E u2) {//kruskalUnion nelle slide
		E root1 = find(u1);
		E root2 = find(u2);
		if (root1.compareTo(root2) == 0)
			return false;
		if (parentOrNSize.get(root2).compareTo(parentOrNSize.get(root1)) < 0) {
			parentOrNSize.put(root1, parentOrNSize.get(root2) + parentOrNSize.get(root1));
			parentOrNSize.get(root2) = root1;
		} else {
			parentOrNSize.get(root2) += parentOrNSize.get(root1);
			parentOrNSize.get(root1) = root2;
		}
		return true;
	}//union*/
}
