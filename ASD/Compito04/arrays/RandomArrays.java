package arrays;

import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;


public class RandomArrays {

  private static Random generatore = new Random();

/*  genera un array di interi (int) casuali con probabilitˆ uniforme
dal minimo al massimo valore intero possibile */
  public static int[] randomIntArray(int lungh) {
    int[] a = new int[lungh];
    for(int i = 0; i < lungh; i++) {
      a[i] = generatore.nextInt();
    }
    return a;
  }

/*  genera un array di int casuali con probabilitˆ uniforme
da 0 fino a max incluso, quindi al pi max+1 numeri distinti */
  public static int[] randomNatArray(int lungh, int max) {
    int[] a = new int[lungh];
    int sup = max+1;
    for(int i = 0; i < lungh; i++) {
      a[i] = generatore.nextInt(sup);
    }
    return a;
  }

/*  genera un array di int casuali con probabilitˆ uniforme
da 0 fino a sup-1 incluso, quindi al pi sup elem. distinti  */
  public static int[] randomDistinctNatArray(int lungh, int sup) {
    if(sup < lungh*10/8)
      throw new IllegalArgumentException("range troppo piccolo");
    int[] a = new int[lungh];
    for(int i = 0; i < lungh; i++) {
      int temp;
      do temp = generatore.nextInt(sup);
      while(esiste(temp, a, i));
      a[i] = temp;
    }
    return a;
  }

  private static boolean esiste(int x, int[] a, int n) {
    for(int i = 0; i < n; i++) {
      if(x == a[i]) return true;
    }
    return false;
  }

/*  riempie di int casuali con probabilitˆ uniforme
da 0 fino a max incluso una porzione da 0 a n-1 di array giˆ allocato */
  public static void fillRandomNatArray(int[] a, int n, int max) {
    if(n > a.length) throw new IllegalArgumentException();
    int sup = max+1;
    for(int i = 0; i < n; i++) {
      a[i] = generatore.nextInt(sup);
    }
  }

/**
   genera un array di int casuali compresi
   fra min e max, estremi inclusi
*/
  public static int[] randomIntArray(int lungh, int min, int max) {
    int range = max + 1 - min;
    if(range < 0) throw new IllegalArgumentException("range troppo grande");
    int[] a = new int[lungh];
    for(int i = 0; i < lungh; i++) {
      a[i] = min + generatore.nextInt(range);
    }
    return a;
  }

/**
   genera un array di Integer casuali compresi
   fra min e max, estremi inclusi
*/
  public static Integer[] randomIntegerArray(int lungh, int min, int max) {
    int range = max + 1 - min;
    if(range < 0) throw new IllegalArgumentException("range troppo grande");
    Integer[] a = new Integer[lungh];
    for(int i = 0; i < lungh; i++) {
      a[i] = min + generatore.nextInt(range);
    }
    return a;
  }

/**
   genera un array di Double casuali compresi
   fra min e max, estremi inclusi
*/
  public static Double[] randomDoubleArray(int lungh, double max) {
    Double[] a = new Double[lungh];
    for(int i = 0; i < lungh; i++) {
      a[i] = max*generatore.nextDouble();
    }
    return a;
  }

  public static double[] randomdoubleArray(int lungh, double max) {
    double[] a = new double[lungh];
    for(int i = 0; i < lungh; i++) {
      a[i] = max*generatore.nextDouble();
    }
    return a;
  }

/**
  genera una stringa casuale di lettere minuscole,
  di lunghezza casuale minore o uguale a
  una lunghezza massima data
 */

  public static String randomString(int lunghMax) {
    if(lunghMax == 0) return "";
    int lungh = generatore.nextInt(lunghMax+1);
    if(lungh == 0) lungh = (lunghMax+1)/2;
    char[] a = new char[lungh];
    for(int i = 0; i < lungh; i++) {
      a[i] = (char) (97 + generatore.nextInt(123-97));
    }
    return new String(a);
  }

/**
  genera una array di lunghezza lungh, di stringhe casuali di lettere minuscole
  di lunghezza minore o uguale maxLunghString
 */
  public static String[] randomStringArray(int lungh, int maxLunghString) {
    String[] a = new String[lungh];
    for(int i = 0; i < lungh; i++) {
      a[i] = randomString(maxLunghString);
    }
    return a;
  }

/**
  crea un array di stringhe leggendo da un file di testo,
  dove ogni riga  una stringa
 */

  private static String[] readStringArray(File file) throws IOException {
    Scanner input = new Scanner(file); // pu˜ generare un'eccezione
    int n = 0;
    while(input.hasNextLine()) {
      input.nextLine();
      n++;
    }
    String[] nuovoArray = new String[n];
    input.close();
    input = new Scanner(file);
    for(int i = 0; i < n; i++) {
      nuovoArray[i] = input.nextLine();
    }
    return nuovoArray;
  }

/**
  crea un array di stringhe estratte a caso da un elenco
  di stringhe memorizzato in un file di testo (un "dizionario")
 */

  public static String[] randomStringArrayFromDict(int length, String fileName)
  throws IOException {
    String[] dictionary = readStringArray(new File(fileName));

    String[] a = new String[length];
    for(int i = 0; i < length; i++) {
      a[i] = dictionary[generatore.nextInt(dictionary.length)];
    }
    return a;
  }
}