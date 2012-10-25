package peretti.compito0;

import java.lang.Exception;

public class ArrayParzialeOrdinato {
	private int numElementi;//Numero corrente di elementi contenuti nell'array. Corrisponde anche al primo indice vuoto
	private String[] elementi;//Elementi

	public ArrayParzialeOrdinato(int numElementi) {
		if (numElementi <= 0)
			throw new IllegalArgumentException(); // Deve essere maggiore di 0!
		this.numElementi = 0;
		elementi = new String[numElementi];//Creo l'array
	}

	private int ricercaBinaria(String x) throws Exception{
		
			int inf = 0, sup = numElementi;
			
			if(sup == 0 || x.compareTo(elementi[0]) < 0)//Verifico se il vettore è vuoto o se l'elemento è minore del primo del vettore
				return 0;
			if(x.compareTo(elementi[numElementi - 1]) > 0)//Verifico se l'elemento è maggiore dell'ultimo elemento riempito del vettore
				return (numElementi);
			int i = (inf + sup) /  2;//Calcolo mid
			while(inf <= sup) {
				int distanza = x.compareTo(elementi[i]);//Calcolo le differenze tra le due stringhe
				if(distanza < 0)
					sup = i-1; 
				else if(distanza > 0)
					inf = i+1;
				else if(distanza == 0)
					return -1;
				i = (inf + sup) /  2;//Calcolo mid	
			}
			return (i+1);
	}

	public int inserisci(String st) throws Exception {
		if (numElementi == elementi.length)//Verifico se il vettore è pieno
			throw new IllegalStateException("Il vettore è pieno");
		int indice = ricercaBinaria(st);//Calcolo l'indice con la funzione
		if(indice == -1)//Se l'elemento esiste, non lo inserisco
			return -1;
		for (int i = numElementi; i > indice; i--) {//Sposto tutti gli elementi successivi per inserire il nuovo elemento
			elementi[i] = elementi[i - 1];
		}
		elementi[indice] = st;//Assegno il nuovo elemento
		numElementi++;//Aumento il contatore degli elementi
		return indice;
	}

	public String get(int i) {
		if (i >= 0 && i < numElementi)
			return elementi[i];
		else
			return null;
	}

	public void print() {
		for (int i = 0; i < numElementi; i++) {
			System.out.println("[" + i + "] " + elementi[i]);
		}
	}

}
