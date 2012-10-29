package peretti.testcompito2;

import java.io.File;
import java.util.Random;

import peretti.bintree.BinTree;

public class TestBinTree {
	
	public static void main(String[] args) {
		
		BinTree nullTree = null;
		BinTree t = null;
		
		t = BinTree.add(1, t, "RLLR");
		t = BinTree.add(2, t, "RLLR");
		t = BinTree.add(3, t, "RLLR");
		t = BinTree.add(4, t, "RLLR");
		t = BinTree.add(5, t, "RLLR");
		t = BinTree.add(6, t, "RLLRR");
		t = BinTree.add(7, t, "RLLRLL");
		t = BinTree.add(8, t, "RLLR");

		t = BinTree.add(55, t, "L");
		t = BinTree.add(66, t, "LL");
		t = BinTree.add(77, t, "LR");
		t = BinTree.add(88, t, "LRL");
		
		//t = treeRandom(25);
		BinTree.draw(BinTree.find(6, nullTree), "Albero nullo");
		
		//BinTree t2 = BinTree.buildFromFile("Compito02/peretti/bintree/tree.txt");
		BinTree t2 = BinTree.find(4, t);
		
		BinTree.draw(t, " t ");
		BinTree.draw(t2, "t2");
		
		s("t preord: ");
				BinTree.preord(t);
		s("\nt inord: ");
				BinTree.inord(t);
		s("\nt postord: ");
				BinTree.postord(t);
		
		s("\nAltezza di t: " + BinTree.altezza(t));
		s("Altezza di t2: " + BinTree.altezza(t2));
		s("Altezza di null: " + BinTree.altezza(nullTree));
		
		s("Heigth di t: " + BinTree.height(t));
		s("Heigth di t2: " + BinTree.height(t2));
		s("Heigth di null: " + BinTree.height(nullTree));
		
		s("Somma degli elementi di t:" + BinTree.somma(t));
		s("Somma degli elementi di t2:" + BinTree.somma(t2));
		s("Somma dei discendenti di 4 in t:" + BinTree.sommaDiscendenti(4, t));
		
		s("Somma degli elementi di null:" + BinTree.somma(nullTree));
		
		
		s("Nodi di t:" + BinTree.numNodi(t));
		s("Nodi di t2:" + BinTree.numNodi(t2));
		s("Nodi di null:" + BinTree.numNodi(nullTree));
		
		s("Max di t:" + BinTree.max(t));
		s("Max di t2:" + BinTree.max(t2));
		s("Max di null:" + BinTree.max(nullTree));
		
		s("Max di t:" + BinTree.max(t));
		s("Max di t2:" + BinTree.max(t2));
		s("Max di null:" + BinTree.max(nullTree));
		
		s("MaxPath di t:" + BinTree.maxPath(t));
		s("MaxPath di t2:" + BinTree.maxPath(t2));
		s("MaxPath di null:" + BinTree.maxPath(nullTree));
		
		s("Num foglie di t:" + BinTree.numFoglie(t));
		s("Num foglie di t2:" + BinTree.numFoglie(t2));
		s("Num foglie di null:" + BinTree.numFoglie(nullTree));
		
		s("Ricerca di 88 in t: " + BinTree.search(88, t));
		s("Ricerca di 126 in t: " + BinTree.search(126, t));
		s("Ricerca di 8 in t: " + BinTree.search(8, t));
		s("Ricerca di 126 in nulltree: " + BinTree.search(126, nullTree));
		
		BinTree t3 = treeRandom(30);
		BinTree.draw(t3, " t3 Random");
		
		//Potrebbe non funzionare, cambiare il path delle chiamate a funzione seguenti in modo che puntino a tree.txt
		BinTree t4 = BinTree.buildFromFile("Compito02/peretti/compito02/tree.txt");//Potrebbe non funzionare
		BinTree t5 = BinTree.buildFromFile("Compito02/peretti/compito02/tree.txt");
		
		s("t4 e t5 uguali: " + BinTree.uguali(t4, t5));
		s("t2 e t5 uguali: " + BinTree.uguali(t2, t5));
		s("t4 e nulltree uguali: " + BinTree.uguali(t4, nullTree));
		
		BinTree t6 = BinTree.copia(t3);
		s("t3 e t6 uguali: " + BinTree.uguali(t3, t6));		
		
		s("Cardini di t:");
		BinTree.scriviCardini(t);
		s("\nCardini di t3:");
		BinTree.scriviCardini(t3);
		
		BinTree.draw(t4, "t4");
		
		s("\nCardini di t4:");
		BinTree.scriviCardini(t4);
		
		
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		BinTree.increment(t);
		BinTree.increment(nullTree);
		BinTree.draw(t, "t Nodi incrementati");
		
		BinTree.incrementFoglie(t3);
		BinTree.draw(t3, "t3 foglie incrementate");
		
		BinTree t7 = BinTree.speculare(t6);
		BinTree.draw(t7, "t7 speculare di t6");
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		BinTree.reflect(t6);
		s("t6 riflesso uguale a t7: " + BinTree.uguali(t7, t6));
		
		/*try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//System.exit(0);*/
	}
	
	public static BinTree treeRandom(int cycles){
		BinTree tree = null;
		int j = 0;
		for(int i = 0; i < cycles; i++){
			String s = randomPath(3);
			while(continueCycle()){
				s = s.concat(randomPath());
				tree = BinTree.add(j++, tree, s);
			}
		}
		return tree;
	}
	
	private static String randomPath(int j) {
		Random r = new Random();
		String s = new String("");
		for(int i = 0; i < j; i++){
			s = s.concat(r.nextBoolean() ? "L" : "R");
		}
		return s;
	}

	private static boolean continueCycle() {
		Random r = new Random();
		return Math.abs(r.nextInt()) >= Integer.MAX_VALUE / 2 * 1 ? false : true;//Restituisce true con una probabilitˆ del 80%
	}

	public static String randomPath(){
		return randomPath(1);
	}
	
	public static void s(String st){
		System.out.println(st);
	}

}
