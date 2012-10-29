package peretti.testbinarytrees;

import static java.lang.System.out;
import peretti.binarytrees.*;
import peretti.bintree.BinTree;

public class TestBinaryTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree t = new BinaryTree();
		t.add(1, "RLLR");
		t.add(2, "RLLR");
		t.add(3, "RLLR");
		t.add(4, "RLLR");
		t.add(5, "RLLR");
		t.add(6, "RLLRR");
		t.add(7, "RLLRLL");
		t.add(8, "RLLR");

		t.add(55, "L");
		t.add(66, "LL");
		t.add(77, "LR");
		t.add(88, "LRL");
		s("Cerco il numero 8: " + t.search(8));
		t.display();
	}
	
	public static void s(String st){
		System.out.println(st);
	}


}
