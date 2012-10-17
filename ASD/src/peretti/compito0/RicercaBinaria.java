package peretti.compito0;

public abstract class RicercaBinaria {
	public static int ricercaBinaria(int v[], int a) {
		int min = 0, max = v.length -1, mid = max / 2;
		while (min <= max) {
			if (a > v[mid])
				min = mid + 1;//Modifico il limite inferiore del sottovettore
			else if (a < v[mid])
				max = mid - 1;//Modifico il limite superiore del sottovettore
			else if(a == v[mid])
				return mid;//Elemento trovato
			mid = (max + min) / 2;//Ricalcolo il valore centrale
		}
		return -1;
	}

	public static boolean ricercaBinariaBool(int v[], int a) {
		int min = 0, max = v.length -1, mid = max / 2;
		while (min <= max) {
			if (a > v[mid])
				min = mid + 1;//Modifico il limite inferiore del sottovettore
			else if (a < v[mid])
				max = mid - 1;//Modifico il limite superiore del sottovettore
			else if(a == v[mid])
				return true;//Elemento trovato
			mid = (max + min) / 2;//Ricalcolo il valore centrale
		}
		return false;
	}

	public static int ricercaBinariaRic(int x, int[] a) {
		return ricBin(x, a, 0, a.length - 1);//Richiamo la funzione iterativa
	}

	private static int ricBin(int a, int[] v, int inf, int sup) {
		int mid = (inf + sup) / 2;
		if (inf > sup)
			return -1;//L'algoritmo ha terminato la la ricerca non trovando nulla, ritorno -1
		if (a > v[mid])
			return ricBin(a, v, mid + 1, sup);//Richiamo la funzione modificando min
		if (a < v[mid])
			return ricBin(a, v, inf, mid - 1);//Richiamo la funzione modificando max
		return mid;//Ritorno l'indice dell'elemento cercato
	}
	
	public static void main(String args[]){
		int v1[] = {4, 5, 6, 10, 11, 20, 34, 100, 120, 123, 230, 234, 236, 300, 600};
		
		//Metodo 1
		int val = 0;
		for(int i = 0; i < 30; i++){
			val = (int) ((i%10) * Math.pow(10, i/10));//Calcolo il valore da cercare. Per valutare la correttezza delle funzioni scritte, genero dei valori e verifico se sono presenti nel vettore. 
			System.out.println("Cerco " + val +
					" che esiste nel vettore: " + ricercaBinariaBool(v1, val) +
					".\n\tIndice con ricerca binaria: " + ricercaBinaria(v1, val) +
					"\n\tRicorsiva: " + ricercaBinariaRic(val, v1));
		}
		
		//Metodo 2
		int v2[] = {1,2,6,34,35,600, 236, 7, 60};
		for(int i = 0; i < v2.length; i++)
			System.out.println("Cerco " + v2[i] +
					" che esiste nel vettore: " + ricercaBinariaBool(v1, v2[i]) +
					".\n\tIndice con ricerca binaria: " + ricercaBinaria(v1, v2[i]) +
					"\n\tRicorsiva: " + ricercaBinariaRic(v2[i], v1));
	}
}
