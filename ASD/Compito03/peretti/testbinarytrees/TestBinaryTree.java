package peretti.testbinarytrees;

import static java.lang.System.out;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import peretti.binarytrees.*;
import peretti.bintree.BinTree;

public class TestBinaryTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree t1 = buildFromFile("Compito03/peretti/testbinarytrees/tree.txt");
		BinaryTree t2 = buildFromFile("Compito03/peretti/testbinarytrees/tree.txt");
		//s(new File(".").getAbsolutePath());
		BinaryTree t4 = t1;
		BinaryTree nullTree = null;
		
		t1.display();
		s("-------");
		
		s("t1 e t4 stesso oggetto: " + t1.equals(t4));
		s("t1 e t2 uguali: " + t1.ugualeA(t2));
		
		s("null e t4 stesso oggetto: " + t4.equals(nullTree));
		s("null e t2 uguali: " + t2.ugualeA(nullTree));
		
		
		BinaryTree t3 = t2.copy();
		s("Cerco il numero 100: " + t1.search(100));
		s("Cerco il numero 30: " + t1.search(30));
		s("Cerco il numero 99: " + t1.search(99));
		s("Cerco il numero 65: " + t1.search(65));
		s("Cerco il numero 42: " + t1.search(42));
		
		for(int i = 0; i < 10; i ++){
			s("Nodi per il livello " + i + " " + t1.numNodiLiv(i));
		}
		
		
		
		s("-------");
		
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


	public static BinaryTree buildFromFile(String fileName) {
	    try {
	      BinaryTree t = new BinaryTree();
	      Scanner input = new Scanner(new File(fileName));
	      while(input.hasNextLine()) {
	        String line = input.nextLine();
	        Scanner lineScan = new Scanner(line);
	        int elem = lineScan.nextInt();
	        String path = lineScan.hasNext()? lineScan.next() : "";
	        t.add(elem, path);
	      }
	      return t;
	    } catch(IOException e) { 
	    	System.out.println("File non trovato.");
	    	return null;
	    	}
	  }
}
