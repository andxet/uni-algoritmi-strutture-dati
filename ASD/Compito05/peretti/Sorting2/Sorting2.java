package peretti.Sorting2;

import java.util.Random;

public class Sorting2 {

	static Random rand = new Random();
	
	final static int SOGLIA_QSORT = 69;
	final static int SOGLIA_MSORT  = 64;


	/** SELECTION SORT */

	 public static void ssort(int a[], int n) {
		  int i = 0, j, min;
		  while (i < n) {
			  min = i;
			  for(j = 0; j < n - i; j ++){
				  if(a[min] > a[j+i])
					  min = j+i;
			  }
			  scambia(a, i, min);
			  i++;
		  }
	  }

	  public static void ssortMax(int a[], int n) {
		  int i = n, j, max;
		  while (i > 0){
			  max = i;
			  for (j = i - 1; j >= 0; j--) {
				  if(a[max] < a[j])
					  max = j;
			  }
			  scambia(a, i, max);
			  i--;
		  }
		  //for(i = 0; i < n; i ++)
		  //printf("\n%d", a[i]);
	  }


	/** INSERTION SORT */

	/** METODO AUSILIARIO INSERISCI
	  preso come parametro un array v di interi ORDINATO
	  fino all'elemento di indice i-1,
	  inserisce l'intero a[i] al posto giusto in v[0..i]
	*/

	  private static void inserisci(int v[], int i) {
		  int temp = v[i], j = i;
		  while(j > 0 && v[j - 1] > temp){
			  v[j] = v[j-1];
			  j--;
		  }
		  v[j] = temp;
	  }

	  public static void isort(int a[], int n) {
		  //printf("isort");
		  int i;
		  for(i = 0; i < n; i++)
			  inserisci(a, i);
	  }

	private static void inserisci2(int v[], int i, int min) {
	    int temp = v[i], j = i;
	    while(j > min && v[j - 1] > temp){
	        v[j] = v[j-1];
	        j--;
	    }
	    v[j] = temp;
	}

	public static void isort2(int a[], int inf, int sup){
		int i;
		for (i = inf; i <= sup; i++)
			inserisci2(a, i, inf);
	}
	
	public static void isort3(int[] a, int fst, int lst) { 
		for (int i = fst + 1; i <= lst; i++) { 
			int x = a[i];
			int j = i;

			while (j > fst && x < a[j-1]) {
				a[j] = a[j-1];
				j--;
			}

			a[j] = x;
		}
	}



	/** MERGESORT */

	private static void merge(int a[], int first, int middle, int last, int aux[]){
		int i = first, j = middle+1, k = first;
		while(i <= middle && j <= last) {
			if(a[i] <= a[j])
				aux[k++] = a[i++];
			else
				aux[k++] = a[j++];
		}
		int h, l, r;
		for(h = middle, l = last; h >= i;)//Ricopio la parte ordinata del primo segmento in a
			a[l--] = a[h--];
		for(r = first; r < k; r++)//Ricopio la parte ordinata del secondo segmento in a
			a[r] = aux[r];
	}

	public static void mergesortASDric(int a[], int first, int last, int aux[]){
		if(first < last) {
			int m = (first+last)/2;
			mergesortASDric(a, first, m, aux);
			mergesortASDric(a, m+1, last, aux);
			merge(a, first, m, last, aux);
		}
	}

	public static void mergesortASD(int a[], int n) {
		  int aux[] = new int[n];
		  mergesortASDric(a, 0, n-1, aux);
	  }

	public static void scambia(int arr[], int i1, int i2){
		int temp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = temp;
	}
	/////////////////////////
	/** INIZIO COMPITO 05 **/
	/////////////////////////

	//TODO: mergesort - versione-base, con insertion-sort su porzioni sotto una soglia;
	//TODO: mergesort - versione senza chiamate inutili del merge (ed eventualmente con merge ottimizzato);
	//TODO: mergesort - versione "a passo alternato".

	/** QUICKSORT **/

	//TODO: quicksort - versione del libro di testo;
	//TODO: quicksort - una delle versioni alla Hoare;
	//TODO: (facoltativo) quicksort - un'altra versione a scelta;
	//TODO: quicksort - la versione alla Hoare scelta sopra, ottimizzata con insertion sort sotto un'opportuna soglia, da determinarsi per mezzo di prove;

	/////////////
	//Compito05//
	//Mergesort//
	/////////////
	private static void msortB(int a[], int first, int last, int aux[], int soglia){
	    if(last - first < soglia){
	        isort2(a, first, last);
	        return;
	    }
	    
	    if(first < last) {
			int m = (first+last)/2;
			msortB(a, first, m, aux, soglia);
			msortB(a, m+1, last, aux, soglia);
			merge(a, first, m, last, aux);
		}
	}

	public static void mergesortB(int a[], int l){
	    int aux[] = new int[l];
	    msortB(a, 0, l-1, aux, SOGLIA_MSORT);
	}

	public static void mergesortBTestSoglia(int a[], int l, int soglia){
	    int aux[] = new int[l];
	    msortB(a, 0, l-1, aux, soglia);
	}

	//Mergesort senza chiamate inutili del merge

	private static void msortO(int a[], int first, int last, int aux[]){
		if(first < last) {
			int m = (first+last)/2;
			msortO(a, first, m, aux);
			msortO(a, m+1, last, aux);
	        if(a[m] > a[m+1])
	            merge(a, first, m, last, aux);
		}
	}

	public static void mergesortO(int a[], int n) {
	    int aux[] = new int[n];
	    msortO(a, 0, n-1, aux);
	}

	//Mergesort passo alternato

	private static void mergePA(int a[], int fst, int mid, int lst, int c[]) {
	    int i = fst, j = mid+1, k = fst;
	    while(i <= mid && j <= lst) {
	        if(a[i]<= a[j])
	            c[k++] = a[i++];
	        else
	            c[k++] = a[j++];
	    }
	    while(i <= mid) c[k++] = a[i++];
	    while(j <= lst) c[k++] = a[j++];
	}

	private static void msortPA(int a[], int inf, int sup, int b[]){
	    if(inf < sup) {
	        int m = (inf + sup)/2;
	        msortPA(b, inf, m, a);
	        msortPA(b, m+1, sup, a);
	        mergePA(b, inf, m, sup, a);
	    }
	}

	public static void mergesortPA(int a[], int l) {
	    int aux[] = a.clone();
	    msortPA(a, 0, l-1, aux);
	}


	/////////////
	//Quicksort//
	/////////////
	private static void qsortASD(int a[], int inf, int sup){
		if(inf < sup){
			int iPivot = inf + rand.nextInt(sup - inf);
			scambia(a, sup, iPivot);
			int x = a[sup];
			int i = inf;
			int j = sup-1;
			while(i <= j) {
				while(i <= j && a[i] <= x) i++;
				while(i <= j && a[j] >= x) j--;
				if(i < j) scambia(a, i, j);
			}
			scambia(a,i,sup);
			qsortASD(a, inf, i-1);
			qsortASD(a, i+1, sup);
		}
	}

	public static void quicksort(int arr[], int l){
		qsortASD(arr, 0, l);
	}


	//Quicksort Hoare
	private static void qsortHoare(int a[], int inf, int sup) {
		if(inf < sup) {
			int iPivot = inf + rand.nextInt(sup - inf);
			int x = a[iPivot];
			int i = inf;
			int j = sup;
			
			do{
				while(a[i] < x) i++;
				while(a[j] > x) j--;
				if(i <= j){
					scambia(a,i,j);
					i++; j--;
				}
			}while (i <= j);
			
		qsortHoare(a, inf, j);
		qsortHoare(a, i, sup);
		}
	}


	public static void quicksortHoare(int v[], int l){
		qsortHoare(v, 0, l - 1);
	}



	private static void qsortOttimizzato(int a[], int inf, int sup, int soglia){
	    if((sup - inf) <= soglia){
	        isort2(a, inf, sup);
	        return;
	    }
	        
	    if(inf < sup) {
			Random rand = new Random();
			int iPivot = inf + rand.nextInt(sup - inf);
			int x = a[iPivot];
			int i = inf;
			int j = sup;
			
			do{
				while(a[i] < x) i++;
				while(a[j] > x) j--;
				if(i <= j){
					scambia(a,i,j);
					i++; j--;
				}
			}while (i <= j);
			
	        qsortOttimizzato(a, inf, j, soglia);
	        qsortOttimizzato(a, i, sup, soglia);
		}
	}

	public static void quicksortOttimizzato(int a[], int l){
	    qsortOttimizzato(a, 0, l - 1, SOGLIA_QSORT);
	}

	public static void qsortSErrato(int a[], int inf, int sup) {
		if (inf < sup) {
			int iPivot = inf; //Per dimostrare che può non funzionare, si assume che l'algoritmo scelga l'elemento all'inizio della porzione di array
			int x = a[iPivot];
			int i = inf;
			int j = sup;
			while (true) {
				while (a[i] < x)
					i++;
				while (a[j] > x)
					j--;
				if (i <= j) {
					scambia(a, i, j);
					i++;
					j--;
				} else
					break;
			}
			qsortSErrato(a, inf, j);
			qsortSErrato(a, i, sup);
		}
	}
	
	public static void quickSortSottilmenteErrato(int a[], int l){
		qsortSErrato(a, 0, l - 1);
	}

	public static void quicksortOttimizzatoTestSoglia(int a[], int l, int soglia){
	    qsortOttimizzato(a, 0, l - 1, soglia);
	}




public static void loadclass(){}





}
