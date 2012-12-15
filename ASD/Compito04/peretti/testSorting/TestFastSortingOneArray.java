package peretti.testSorting;
import java.awt.Toolkit;
import java.io.*;

import peretti.sorting.Sorting;
import static java.lang.System.*;
import arrays.*;

public class TestFastSortingOneArray {

/**
    copia i primi n elementi dell'array a
    nell'array b
  */
  public static void copyInto(int[] a, int[] b, int n) {
    if(n > a.length) throw new IllegalArgumentException();
    for(int i = 0; i < n; i++) {
      b[i] = a[i];
    }
  }
  
  static boolean sorted(int[] a, int n){
	    int i = 1;
	    while(i < n && a[i-1] <= a[i]) i++;
	    /*if(i < n){
	    	Toolkit.getDefaultToolkit().beep();
	    	throw new IllegalArgumentException("ERRORE");
	    }*/
	    return i >= n;
	  }

  public static enum Algoritmo {
    SSORT,
    SSORT_MAX,
    MSORT,
    ISORT,
    // ... sostituire o aggiungere via via nomi per i nuovi
    //     algoritmi e (versioni di algoritmi)
    //     di ordinamento studiati
  }

  public static void sort(Algoritmo algoritmo, int[] a, int n) {
    switch(algoritmo) {
	    case SSORT: Sorting.ssort(a, n);
	    case SSORT_MAX: Sorting.ssortMax(a, n);
	    case MSORT: Sorting.mergesortASD(a, n);
	    case ISORT: Sorting.isort(a, n);
     // ... sostituire o aggiungere nomi dei nuovi algoritmi
    }
  }

  static final int N = 2000001, STEP = 100000;
  //static final int N = 2001, STEP = 100;
  
  static int[] arrayOriginale = new int[N];
  static int[] arrayDaOrdinare = new int[N];
  static int[] arrayAusiliario = new int[N];


  private static void scambia(int[] a, int i, int j) {
    if(i < 0 || i >= a.length || j < 0 || j >= a.length)
      throw new IllegalArgumentException();
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

/* Nota: in un foglio A4 in orizzontale ci stanno al più 5 o 6
  colonne di tempi */

  public static void testReport() throws IOException {
    String fileName = "fastSortTimesJavaNoGarbage" + getProperty("os.name").substring(0,3) + ".txt";
    FileWriter outFile = new FileWriter(fileName, false);
    PrintWriter out = new PrintWriter(outFile);

    out.println("Prova sorting su array di interi: " + getProperty("os.name") + " Java "+ getProperty("java.version"));
    out.flush();

    long t0, t1;
    double diff;

    Sorting.loadClass();
    out.println("                          ssort           ssortMAX             msort            isort      ");
    out.println("                    casuale    ord    casuale    ord    casuale    ord    casuale    ord   ");
    System.out.println("                          isort           isortMAX             ssort            msort      ");
    System.out.println("                    casuale    ord    casuale    ord    casuale    ord    casuale    ord   ");
    double[][] tempi = new double[N/STEP + 1][Algoritmo.values().length * 2];
    for(int n = STEP; n < N; n += STEP) {
      RandomArrays.fillRandomNatArray(arrayOriginale, n, 9*n/10);
      System.out.printf("size = %7d: ms ", n);
      out.printf("size = %7d ", n);
      boolean[] error = new boolean[Algoritmo.values().length];
      for(Algoritmo algoritmo: Algoritmo.values()) {
        copyInto(arrayOriginale, arrayDaOrdinare, n);
        t0 = nanoTime();
        sort(algoritmo, arrayDaOrdinare, n);
        t1 = nanoTime();
        diff = (t1-t0)/1000000.0;
        out.printf(" %7.2f", diff);
        System.out.printf(" %7.2f;", diff);
        tempi[n/STEP - 1][algoritmo.ordinal() * 2] = diff;
        if(!sorted(arrayDaOrdinare,n)) error[algoritmo.ordinal()] = true;

        scambia(arrayDaOrdinare,5,50);  // per ottenere array quasi ordinato
        t0 = nanoTime();
        sort(algoritmo, arrayDaOrdinare, n);
        t1 = nanoTime();
        diff = (t1-t0)/1000000.0;
        out.printf(" %7.2f;", diff);
        System.out.printf(" %7.2f;", diff);
        tempi[n/STEP - 1][algoritmo.ordinal() * 2 + 1] = diff;
        if(!sorted(arrayDaOrdinare,n)) error[algoritmo.ordinal()] = true;
      }
      out.println();
      for(Algoritmo alg: Algoritmo.values()) {
        if(error[alg.ordinal()]) {
          System.out.println("L'algoritmo " + alg + " non ha ordinato !");
          out.println("L'algoritmo " + alg + " non ha ordinato !\n");
        }
      }
      out.flush();
      System.out.println("\n--------------------------------------------------------------------------\n");
    }
    out.close();
    
    s("Scrivo i risultati formattati su file");
    
    FileWriter file = new FileWriter("Risultati formattati.txt", false);
    PrintWriter fout = new PrintWriter(file);
    for(int i = 0; i < Algoritmo.values().length * 2; i++){
    	fout.println("Algoritmo: " + Algoritmo.values()[i / 2] + (i % 2 == 0 ? " non ordinato" : " quasi ordinato"));
    	for(int j = 0; j < N/STEP; j++)
    		fout.printf("%07.5f\n", tempi[j][i]);
    }
    fout.flush();
    fout.close();
    s("Risultati scritti");
  }

  public static void main(String[] args) throws IOException {
    testReport();
  }
  
  public static void s(String s){
	  System.out.println(s);
  }
}








