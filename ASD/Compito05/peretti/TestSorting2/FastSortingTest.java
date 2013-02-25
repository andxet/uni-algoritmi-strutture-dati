package peretti.TestSorting2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import peretti.Sorting2.Sorting2;

public class FastSortingTest {
	
////////
//MAIN//
////////

	public static void main(String[] args) {
		Sorting2.loadclass();
	    try{
	    	//testSoglia(); //Test per calcolare la soglia
	    	//testAlgoritmi();//Test per calcolare il tempo di esecuzione degli algoritmi
	    	//testQsort();//Test per il qsort, con molti o pochi elementi ripetuti
	    	//testIsort2mil();
	    	testQuicksortErrato();
	    }catch (Exception e){
	    	e.printStackTrace();
	    }
	}

	//#define N 100001
	//#define STEP 5000

	final int TIPO_TEST_L_STRINGA = 50;

	final static int N = 2000001;
	final static int STEP = 100000;
	final static int  START = STEP;
	//#define START 1500

	final static int ELEM_TEST_SOGLIA = 2000000;

	static int ar[] = new int[N];
	static int arOrdinato[];
	static double timediff;

	final static int MERGESORTB = 0; //mergesort - versione-base, con insertion-sort su porzioni sotto una soglia;
	final static int  MERGESORTO = 1; //mergesort - versione senza chiamate inutili del merge (ed eventualmente con merge ottimizzato);
	final static int  MERGESORTPA = 2; //mergesort - versione "a passo alternato".

	final static int QUICKSORTLT = 3;
	final static int QUICKSORTH = 4;
	final static int QUICKSORTO = 5;

	final static boolean TEST_NONORDINATO = true;
	final static boolean TEST_ORDINATO = true;


	static String algoritmi[] = {"MergeSort base",
		"Mergestort ottimizzato",
		"Mergesort passo alternato",
		"Quicksort libro",
		"Quicksort \"alla Hoare\"",
		"Quicksort ottimizzato"
	};
	
	final static int NUM_ALG = algoritmi.length;

	/* restituisce TRUE (cioè un numero != 0) se l'array è ordinato,
	 restituisce FALSE (cioè 0) se l'array non è ordinato */
	public static boolean sorted(int a[], int n) {
	    int i = 1;
	    while(i < n && a[i-1] <= a[i]) i++;
	    return i >= n;
	}

	/* riempie l'array di numeri casuali */
	public static void fillArray(int a[], int n, int max) {
		Random rand = new Random();
	    if(max == 0)
	        max = 10;
	    int i;
	    for(i = 0; i < n; i++) a[i] = rand.nextInt() %max;
	  }

	public static void fillArray2(int varieta){
	    fillArray(ar, N, 100 * N);
	    arOrdinato = ar.clone();
	    Sorting2.quicksortOttimizzato(arOrdinato, N);
	    if(!sorted(arOrdinato, N)){
	        s("L'algoritmo di sorting non funziona!");
	        //exit(-1);
	    }
	}

	public void fillArrayOrdered(int a[], int n){
		int i;
		for(i = 0; i < n; i++) a[i] = i;
	}

	public static void sort(int v[], int n, int alg){//Richiama la funzione di ordinamento alg
		switch (alg) {
			case QUICKSORTLT:
				Sorting2.quicksort(v, n);
				break;
	        //case QUICKSORTF:
	            //quicksortFac(v, n);
	            //break;
			case QUICKSORTH:
				Sorting2.quicksortHoare(v, n);
				break;
	        case QUICKSORTO:
	            Sorting2.quicksortOttimizzato(v, n);
	        case MERGESORTO:
	            Sorting2.mergesortO(v, n);
	            break;
	        case MERGESORTB:
	            Sorting2.mergesortB(v, n);
	            break;
	        case MERGESORTPA:
	            Sorting2.mergesortPA(v, n);
	            break;
			default:
				break;
		}
	}

	public static boolean implem(int i){//Restituisce true se l'algoritmo i è implementato
		switch (i) {
	        case QUICKSORTLT:
	        //case QUICKSORTF:
			//case ISORT:
			case QUICKSORTH:
	        case QUICKSORTO:
	        case MERGESORTO:
	        case MERGESORTB:
	        case MERGESORTPA:
				return true;
		default:
				return false;
		}
	}

	public static void testTempi(int array[], int alg, PrintWriter log){
	    for(int n = START; n < N; n += STEP) {
			int v[] = ar.clone();
			s("PASSO " + (n/STEP - START/STEP + 1) + " di " + (N/STEP - START/STEP + 1) + " ");
	        long before, after;
	        before = System.nanoTime();
	        sort(v, n, alg);
	        after = System.nanoTime();
	        
	        before /= 1000000;
	        after /= 1000000;
	        
	        timediff = after - before;
	        System.out.printf("size = %6d: tempo = ms %5.2f;\n", n, timediff);
	        log.printf("%5.2f\n", timediff);
	        System.out.printf("%s", sorted(v, n) ? "" : "---NON ORDINATO!!!!!!---");
	    }
	}

	public static void testSort(int alg, String tipoTest) throws IOException{
		if(TEST_NONORDINATO){
	        
	        String nomefile;
	        nomefile = new String("Tempi " + algoritmi[alg] + " " + tipoTest + " non ordinato.txt");
	        FileWriter file = new FileWriter(nomefile, false);
	        PrintWriter out = new PrintWriter(file);
	        
	        System.out.printf("\n//////////\n\t%s %s non ordinato\n\tstart: %d step: %d n: %d\n//////////\n\n", algoritmi[alg], tipoTest, START, STEP, N);
	        out.printf("%s, start: %d step: %d n: %d\n\n", algoritmi[alg], START, STEP, N);
	    
	        int temp[] = ar.clone();
	        testTempi(temp, alg, out);
	        
	        out.close();
	    }
	    
		if(TEST_ORDINATO){
			String nomefile;
	        nomefile = new String("Tempi " + algoritmi[alg] + " " + tipoTest + " ordinato.txt");
	        FileWriter file = new FileWriter(nomefile, false);
	        PrintWriter out = new PrintWriter(file);
	        
	        System.out.printf("\n//////////\n\t%s %s ordinato\n\tstart: %d step: %d n: %d\n//////////\n\n", algoritmi[alg], tipoTest, START, STEP, N);
	        out.printf("%s, start: %d step: %d n: %d\n\n", algoritmi[alg], START, STEP, N);
	    
	        int temp[] = ar.clone();
	        testTempi(temp, alg, out);
	        
	        out.close();
	    }
	}

	public static void testSoglia() throws IOException{
	    int originale[] = new int[ELEM_TEST_SOGLIA];
	    fillArray(originale, ELEM_TEST_SOGLIA, 9 * ELEM_TEST_SOGLIA / 10);
	    int i;
	    int vett[];
	    
	    FileWriter file = new FileWriter("test soglia quicksort.txt", false);
        PrintWriter out = new PrintWriter(file);
        
	    vett = originale.clone();
	    System.out.printf("INIZIO TEST SOGLIA\n%d elementi\n\n", ELEM_TEST_SOGLIA);
	    
	    double before, after;
	    
	    before = System.nanoTime();
	    Sorting2.quicksortHoare(vett, ELEM_TEST_SOGLIA);
        after = System.nanoTime();
        
        before /= 1000000;
        after /= 1000000;
	    
	    timediff = after - before;
	    System.out.printf("Tempo quicksortHoare: ms %5.2f\n", timediff);
	    out.printf("Quicksort: %5.2f\n", timediff);
	    
	    for(i = 0; i < 300; i++){
	        vett = originale.clone();
	        
	        before = System.nanoTime();
	        Sorting2.quicksortOttimizzatoTestSoglia(vett, ELEM_TEST_SOGLIA, i);
	        after = System.nanoTime();
	        
	        before /= 1000000;
	        after /= 1000000;
	        
	        timediff = after - before;
	        
	        System.out.printf("Soglia: %d, ms %5.2f\n", i, timediff);
	        out.printf("%5.2f\n", timediff);
	        System.out.printf(" %s", sorted(vett, ELEM_TEST_SOGLIA) ? "" : "NON ORDINATO!!!!!!");
	    }
	    out.close();
	    
	    
	    //Test mergesort
	    file = new FileWriter("test soglia mergesort.txt", false);
        out = new PrintWriter(file);
	    
        vett = originale.clone();
	    System.out.printf("INIZIO TEST SOGLIA\n%d elementi\n\n", ELEM_TEST_SOGLIA);
	    
	    before = System.nanoTime();
	    Sorting2.mergesortASD(vett, ELEM_TEST_SOGLIA);
        after = System.nanoTime();
        
        before /= 1000000;
        after /= 1000000;
	    
        timediff = after - before;
	    System.out.printf("Tempo mergesort: ms %5.2f\n", timediff);
	    out.printf("Mergesort: %5.2f\n", timediff);
	    
	    for(i = 0; i < 300; i++){
	        vett = originale.clone();
	        
	        before = System.nanoTime();
	        Sorting2.mergesortBTestSoglia(vett, ELEM_TEST_SOGLIA, i);
	        after = System.nanoTime();
	        
	        before /= 1000000;
	        after /= 1000000;
	        
	        timediff = after - before;
	        System.out.printf("Soglia: %d, ms %5.2f\n", i, timediff);
	        out.printf("%5.2f\n", timediff);
	        System.out.printf(" %s", sorted(vett, ELEM_TEST_SOGLIA) ? "" : "NON ORDINATO!!!!!!");
	    }
	    
	    out.close();
	}

	public static void testAlgoritmi() throws IOException{
	    System.out.printf("CICLO MISURAZIONE TEMPI\n\n");
	    
	    int i;
	    fillArray2(9*N/10);
	    for(i = 0; i < NUM_ALG; i++)
	        if(implem(i))
	            testSort(i, "");
	    System.out.printf("\nFINE TEST\n");
	}

	public static void testQsort() throws IOException{
		System.out.printf("CICLO MISURAZIONE TEMPI QUICKSORT\n\n");
	    
	    int i;
	    
	    fillArray2(100 * N);//Riempio l'array con pochi elementi uguali
	    for(i = 3; i < NUM_ALG; i++)
	        if(implem(i))
	            testSort(i, "elementi molto diversi");
	    
	    fillArray2(10);//Riempio l'array con elementi poco vari (10 tipi)
	    for(i = 3; i < NUM_ALG; i++)
	        if(implem(i))
	            testSort(i, "elementi poco vari");
	    
	    s("\nFINE TEST\n");
	}


	public static void testIsort2mil(){
		System.out.println("\nTest insertionsort su 2 milioni di elementi");
		int vett[] = new int[200000];
		fillArray(vett, vett.length, 9 * vett.length / 10);
	    
	    double before, after;
	    
	    before = System.nanoTime();
	    Sorting2.isort(vett, vett.length);
        after = System.nanoTime();
        
        if(!sorted(vett, vett.length))
        	System.out.println("NON ORDINATO!!!");
        before /= 1000000;
        after /= 1000000;
	    
	    timediff = after - before;
	    System.out.printf("Tempo insertionsort su 2.000.000 di elementi: ms %5.2f\n", timediff);
	    
	}
	
	public static void testQuicksortErrato(){
		System.out.println("Questa esecuzione solleverà un'eccezione.");
		int arr[] = {8, 9, 10};
		System.out.println("L'array contiene:");
		for(int i = 0; i < arr.length; i++)
			System.out.println("[" + i + "] " + arr[i]);
		try{
			Sorting2.quickSortSottilmenteErrato(arr, arr.length);
		}catch (IndexOutOfBoundsException e){
			e.printStackTrace();
			System.out.println("ECCEZIONE!");
		}
		
		System.out.println("Mentre questa esecuzione no:");
		int arr2[] = {45, 23, 1, 5};
		System.out.println("L'array contiene:");
		for(int i = 0; i < arr2.length; i++)
			System.out.println("[" + i + "] " + arr2[i]);
		try{
			Sorting2.quickSortSottilmenteErrato(arr2, arr2.length);
		}catch (IndexOutOfBoundsException e){
			e.printStackTrace();
			System.out.println("ECCEZIONE!");
		}
		System.out.println("Fine test!");
	}
	
	public static void s(String str){
		System.out.println(str);
	}
}
