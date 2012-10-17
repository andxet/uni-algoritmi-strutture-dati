package peretti.compito01;

import java.util.Random;

public abstract class Bandiera {
	//Resto e colore nei commenti si questa classe vengono usati come sinonimi
	//Quando si parla di vettore ordinato, si parla di vettore ordinato per il resto della divisione per 3 degli elementi in esso contenuti.
	public static final int VERDE = 0;//Il colore di un elemento viene determinato dal resto della divisione per 3
	public static final int BIANCO = 1;
	public static final int ROSSO = 2;
	
	public static final int NUM_ELEMENTI = 1000;

	public static boolean dividiColoriPostCond(int[] a) {
		int stato = VERDE, diff; //Stato determina quale è il colore corrente.
		
		for(int i = 0; i < a.length; i++){
			diff = Math.abs(a[i] % 3);//Viene fatto il valore assoluto del resto perchè il resto di una divisione negativa è negativo. In questo modo tutti i risultati sono positivi
			
			if(diff < stato)//Se il colore dell'elemento esaminato è minore di quello attuale l'array non è ordinato
				return false;
			else if(diff > stato)//Se il colore è maggiore invece devo aggiornare stato
				stato = diff;
		}
		return true;//Ho esaminato tutto l'array senza trovare colori minori nei successivi elementi per ogni elemento, ritorno true.
	}

	static void dividiColori(int[] a) {//La parte da esaminare si trova VERDE BIANCO qui ROSSO
		int i = 0, j = 0, k = a.length - 1, val;//Ho bisogno di tre variabili, i è l'indice del primo bianco, j il primo elemento da esaminare e k l'ultimo.
		while (j <= k){
			val = Math.abs(a[j] % 3);//Calcolo il valore assoluto per lo stesso motivo spiegato più in alto
			int temp;
			switch(val){//Ho tre possibili casi
			case VERDE://Il colore esaminato è verde, scambio questo elemento con il primo bianco, poi esaminerò il colore sucessivo.
				temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				j++; i++;
				break;
			case BIANCO://Se è bianco è già al suo posto, aggiorno la variabile per analizzare l'elemento successivo
				j++;
				break;
			case ROSSO://Se è rosso scambio l'elemento con l'ultimo da analizzare, che è adiacente alla parte rossa, poi diminuisco k.
				temp = a[k];
				a[k] = a[j];
				a[j] = temp;
				k--;
				break;
			}
		}
	}

	public static void main(String[] args) {
		//Questo test genera interi positivi casuali, si stampano i resti di ogni elemento, poi si utilizza la funzione dividiColoriPostCond per verificare se è ordinato,
		//poi si ordina con la funzione dividi colori, si ristampano i resti e si utilizza di nuovo la funzione per testare se il vettore è ordinato. 
		int v[] = getRandomIntArray(NUM_ELEMENTI);
		stampaVettColori(v);//Stampo i resti degli elementi, non gli elementi. Il programma utilizza interi che possono essere molto maggiori di 3, come richiesto, questa funzione è utile solo per controllare che la funzione faccia il suo lavoro corretamente. se si vuole vedere i valori reali contenuti nel vettore, togliere "Colori" dal nome della funzione.
		System.out.println("Vettore ordinato:" + dividiColoriPostCond(v));
		dividiColori(v);
		stampaVettColori(v);
		System.out.println("Vettore ordinato dopo:" + dividiColoriPostCond(v));

		//Come sopra, ma viene usata la funzione dividiColoriAlternativa
		v = getRandomIntArray(NUM_ELEMENTI);
		stampaVettColori(v);
		System.out.println("Vettore ordinato:" + dividiColoriPostCond(v));
		dividiColoriAlternativa(v);
		stampaVettColori(v);
		System.out.println("Vettore ordinato dopo:" + dividiColoriPostCond(v));
		
		//Hard test:
		//Utilizzo vettori generati casualmente sempre più lunghi, ognuno viene duplicato ed ordinato con i due tipi di funzioni. Se solo uno non risulta ordinato, viene stampato un messaggio di errore.
		int v1[], v2[];
		boolean test = true;
		for(int i = 0; i < 5000; i++){
			System.out.println(i);
			v1 = getRandomIntArray(NUM_ELEMENTI * i / 10);
			v2 = v1.clone();
			dividiColori(v1);
			dividiColoriAlternativa(v2);
			if(!dividiColoriPostCond(v1) || !dividiColoriPostCond(v2)){
				System.out.println("ERRORE!!!");
				test = false;
			}
		}
		System.out.println("Esito: " + test);
	}
	
	private static int[] getRandomIntArray(int n){//Funzione utilizzata per generare array di interi casuali di dimensione n
		Random r = new Random();
		int v[] = new int[n];
		for(int i = 0; i < v.length; i ++){
			v[i] = r.nextInt();
		}
		return v;
	}

	static void stampaVett(int[] v) {//Stampo gli elementi del vettore
		for(int i = 0; i < v.length; i ++){
			System.out.println("[" + i + "] " + v[i]);
		}
	}
	
	static void stampaVettColori(int[] v) {//Stampo i resti degli elementi del vettore
		for(int i = 0; i < v.length; i ++){
			System.out.println("[" + i + "] " + Math.abs(v[i] % 3));
		}
	}
	
/*
 * Passo generico della versione alternativa di dividiColori (La parte da esaminare è tra il verde ed il bianco)
 *  0       k               j            i         n-1
 * |    0  |  da esaminare   |      1     |      2   |
 * ___________________________________________________
 */

	static void dividiColoriAlternativa(int[] a) {//La soluzione, rispetto alla versione scritta più sopra, è inversa con l'analisi della parte da esaminare che parte dal fondo invece che dall'inizio.
		int i = a.length - 1, j = a.length - 1, k = 0, val;
		while (j >= k){
			val = Math.abs(a[j] % 3);
			int temp;
			switch(val){
			case ROSSO:
				temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				j--; i--;
				break;
			case BIANCO:
				j--;
				break;
			case VERDE:
				temp = a[k];
				a[k] = a[j];
				a[j] = temp;
				k++;
				break;
			}
		}
	}
}
