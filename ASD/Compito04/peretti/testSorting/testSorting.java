package peretti.testSorting;

import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;

import arrays.RandomArrays;

import peretti.sorting.Sorting;

public class testSorting {

	/* ESEMPIO DI PROGRAMMA TEST DEGLI ALGORITMI DI ORDINAMENTO */

	public static final int N = 100001;
	//public static final int N = 5001;
	public static final int STEP = 5000;

	static int[] ar;
	static FileOutputStream pFile;
	static double t1, t2;
	static double timediff;

	  /* riempie l'array di numeri casuali */
	  static void fillArray(int[] a, int max) {
	    RandomArrays.fillRandomNatArray(a, a.length, max);
	  }

	 /* restituisce TRUE (cioË un numero != 0) se l'array Ë ordinato,
	    restituisce FALSE (cioË 0) se l'array non Ë ordinato */
	  static boolean sorted(int[] a) throws Exception {
	    int i = 1;
	    while(i < a.length && a[i-1] <= a[i]) i++;
	    if(i < a.length){
	    	Toolkit.getDefaultToolkit().beep();
	    	throw new Exception("ERRORE");
	    }
	    return i >= a.length;
	  }
	  
	  static boolean sortedNoEx(int[] a) throws Exception {//Non solleva un'eccezione se l'array non è ordinato
		    int i = 1;
		    while(i < a.length && a[i-1] <= a[i]) i++;
		    return i >= a.length;
		  }

	static void fillArrayOrdered(int[] a){
		int i;
		for(i = 0; i < a.length; i++) a[i] = i;
	}

	protected static void testSSort() throws Exception{
		pFile = new FileOutputStream("java tempi ssort.txt");
		PrintStream fout = new PrintStream(pFile);
		int n;
		s("SSORT\n\n"); fout.println("SSORT\n\n");
	    for(n = STEP; n < N; n += STEP) {
			ar = new int[n];
			s("-----------------\nPASSO " + n/STEP + " di " + N/STEP + "\n");
			fillArray(ar, 9*n/10);
			s(sortedNoEx(ar) ? "ordinato\n" : "non ordinato\n");
			s("sto ordinando\n");
			t1 = System.nanoTime();
			Sorting.ssort(ar);
			t2 = System.nanoTime();
			t1 /= 1000000;
			t2 /= 1000000;
			timediff = t2 - t1;
			s("size = " + n + ": tempo = ms " + timediff + ";\n");
			fout.print("size = " + n + ": tempo = ms " + timediff + "; ");
			s(sorted(ar) ? "ordinato\n" : "---NON ORDINATO---\n");
			
			Sorting.scambia(ar, 5, 50);
			t1 = System.nanoTime();
			Sorting.ssort(ar);
			t2 = System.nanoTime();
			t1 /= 1000000;
			t2 /= 1000000;
			timediff = t2 - t1;
			s("su quasi ord.: tempo = ms " + timediff + ";\n");
			fout.print("su quasi ord.: tempo = ms " + timediff + ";\n");
			s(sorted(ar) ? "ordinato\n\n" : "---NON ORDINATO---\n\n");
	    }
		fout.close();
	}

	protected static void testSSortMAX() throws Exception{
		pFile = new FileOutputStream("java tempi ssortMax.txt");
		PrintStream fout = new PrintStream(pFile);
		int n;
		s("SSORT MAX\n\n"); fout.println("SSORT MAX\n\n");
	    for(n = STEP; n < N; n += STEP) {
	    	ar = new int[n];
			s("-----------------\nPASSO " + n/STEP + " di " + N/STEP + "\n");
			fillArray(ar, 9*n/10);
			s(sortedNoEx(ar) ? "ordinato\n" : "non ordinato\n");
			s("sto ordinando\n");
			t1 = System.nanoTime();
			Sorting.ssortMax(ar);
			t2 = System.nanoTime();
			t1 /= 1000000;
			t2 /= 1000000;
			timediff = t2 - t1;
			s("size = " + n + ": tempo = ms " + timediff + ";\n");
			fout.print("size = " + n + ": tempo = ms " + timediff + "; ");
			s(sorted(ar) ? "ordinato\n" : "---NON ORDINATO---\n");
			
			Sorting.scambia(ar, 5, 50);
			t1 = System.nanoTime();
			Sorting.ssortMax(ar);
			t2 = System.nanoTime();
			t1 /= 1000000;
			t2 /= 1000000;
			timediff = t2 - t1;
			s("su quasi ord.: tempo = ms " + timediff + ";\n");
			fout.print("su quasi ord.: tempo = ms " + timediff + ";\n");
			s(sorted(ar) ? "ordinato\n\n" : "---NON ORDINATO---\n\n");
	    }
		fout.close();
	}

	protected static void testMergesort() throws Exception{
		pFile = new FileOutputStream("java tempi mergesort.txt");
		PrintStream fout = new PrintStream(pFile);
		int n;
		s("mergesort\n\n"); fout.println("mergesort\n\n");
	    for(n = STEP; n < N; n += STEP) {
	    	ar = new int[n];
			s("-----------------\nPASSO " + n/STEP + " di " + N/STEP + "\n");
			fillArray(ar, 9*n/10);
			s(sortedNoEx(ar) ? "ordinato\n" : "non ordinato\n");
			s("sto ordinando\n");
			t1 = System.nanoTime();
			Sorting.mergesortASD(ar);
			t2 = System.nanoTime();
			t1 /= 1000000;
			t2 /= 1000000;
			timediff = t2 - t1;
			s("size = " + n + ": tempo = ms " + timediff + ";\n");
			fout.print("size = " + n + ": tempo = ms " + timediff + "; ");
			s(sorted(ar) ? "ordinato\n" : "---NON ORDINATO---\n");
			
			Sorting.scambia(ar, 5, 50);
			t1 = System.nanoTime();
			Sorting.mergesortASD(ar);
			t2 = System.nanoTime();
			t1 /= 1000000;
			t2 /= 1000000;
			timediff = t2 - t1;
			s("su quasi ord.: tempo = ms " + timediff + ";\n");
			fout.print("su quasi ord.: tempo = ms " + timediff + ";\n");
			s(sorted(ar) ? "ordinato\n\n" : "---NON ORDINATO---\n\n");
	    }
		fout.close();
	}

	protected static void testInsertionsort() throws Exception{
		pFile = new FileOutputStream("java tempi insertion sort.txt");
		PrintStream fout = new PrintStream(pFile);
		int n;
		s("insertion sort" + "\n\n"); fout.println("insertion sort\n\n");
	    for(n = STEP; n < N; n += STEP) {
	    	ar = new int[n];
			s("-----------------\nPASSO " + n/STEP + " di " + N/STEP + "\n");
			fillArray(ar, 9*n/10);
			s(sortedNoEx(ar) ? "ordinato\n" : "non ordinato\n");
			s("sto ordinando\n");
			t1 = System.nanoTime();
			Sorting.isort(ar);
			t2 = System.nanoTime();
			t1 /= 1000000;
			t2 /= 1000000;
			timediff = t2 - t1;
			s("size = " + n + ": tempo = ms " + timediff + ";\n");
			fout.print("size = " + n + ": tempo = ms " + timediff + "; ");
			s(sorted(ar) ? "ordinato\n" : "---NON ORDINATO---\n");
			
			Sorting.scambia(ar, 5, 50);
			t1 = System.nanoTime();
			Sorting.isort(ar);
			t2 = System.nanoTime();
			t1 /= 1000000;
			t2 /= 1000000;
			timediff = t2 - t1;
			s("su quasi ord.: tempo = ms " + timediff + ";\n");
			fout.print("su quasi ord.: tempo = ms " + timediff + ";\n");
			s(sorted(ar) ? "ordinato\n\n" : "---NON ORDINATO---\n\n");
	    }
		fout.close();
	}
	    /* Modificare il programma precedente
	       aggiungendo un analogo testing per tutti gli altri algoritmi
	       di ordinamento realizzati o che realizzeremo
	       (per ora ssortMax, isort, mergesort).
	       Testare (e confrontare fra di loro) gli algoritmi precedenti
	       anche su array ordinati.

	       Esaminare il file dei risultati prodotto
	       e confrontare tali risultati con le previsioni teoriche.

	       Potete scegliere fra:
	       1) testare ciascun algoritmo separatamente
	          su array di lunghezza crescente: cosÏ
	          Ë facile costruire la tabella dei tempi
	          per ciascun algoritmo;
	       2) per ciascun array di una data lunghezza
	          testare tutti gli algoritmi su copie identiche
	          dello stesso array: cosÏ Ë facile confrontare
	          fra di loro i diversi algoritmi.

	    */




	public static void main(String[] args) {
		
		Toolkit.getDefaultToolkit().beep();
		
		Sorting.loadClass();
		
		s("CICLO MISURAZIONE TEMPI\n\n");

		 try {
			testSSort();
			testSSortMAX();
			testMergesort();
			testInsertionsort();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 catch (Exception e) {
			//s("Un array non è stato ordinato");
			e.printStackTrace();
			System.exit(-1);
		}
		 

	    s("\n\nFINE TEST");
	}
	
	public static void s(String s){
		System.out.println(s);
	}
}
