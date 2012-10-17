package peretti.compito01;

import java.util.Random;

public abstract class Bandiera {
	
	public static final int VERDE = 0;//Il colore di un elemento viene determinato dal resto della divisione per 3
	public static final int BIANCO = 1;
	public static final int ROSSO = 2;
	
	public static final int NUM_ELEMENTI = 1000;

	public static boolean dividiColoriPostCond(int[] a) {
		int stato = VERDE, diff;
		
		for(int i = 0; i < a.length; i++){
			diff = Math.abs(a[i] % 3);
			
			if(diff < stato)
				return false;
			else if(diff > stato)
				stato = diff;
		}
		return true;
	}

	static void dividiColori(int[] a) {
		int i = 0, j = 0, k = a.length - 1, val;
		while (j <= k){
			val = Math.abs(a[j] % 3);
			int temp;
			switch(val){
			case VERDE:
				temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				j++; i++;
				break;
			case BIANCO:
				j++;
				break;
			case ROSSO:
				temp = a[k];
				a[k] = a[j];
				a[j] = temp;
				k--;
				break;
			}
		}
	}

	public static void main(String[] args) {
		int v[] = getRandomIntArray(NUM_ELEMENTI);
		stampaVettColori(v);
		System.out.println("Vettore ordinato:" + dividiColoriPostCond(v));
		dividiColori(v);
		stampaVettColori(v);
		System.out.println("Vettore ordinato dopo:" + dividiColoriPostCond(v));

		v = getRandomIntArray(NUM_ELEMENTI);
		stampaVettColori(v);
		System.out.println("Vettore ordinato:" + dividiColoriPostCond(v));
		dividiColoriAlternativa(v);
		stampaVettColori(v);
		System.out.println("Vettore ordinato dopo:" + dividiColoriPostCond(v));
		
		//Hard test
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
	
	private static int[] getRandomIntArray(int n){
		Random r = new Random();
		int v[] = new int[n];
		for(int i = 0; i < v.length; i ++){
			v[i] = r.nextInt();
		}
		return v;
	}

	static void stampaVett(int[] v) {
		for(int i = 0; i < v.length; i ++){
			System.out.println("[" + i + "] " + v[i]);
		}
	}
	
	static void stampaVettColori(int[] v) {
		for(int i = 0; i < v.length; i ++){
			System.out.println("[" + i + "] " + Math.abs(v[i] % 3));
		}
	}
	
/*
 * Passo generico della versione alternativa di dividiColori (La parte da esaminare  tra il verde ed il bianco)
 *  0       k               j            i         n-1
 * |    0  |  da esaminare   |      1     |      2   |
 * ___________________________________________________
 */

	static void dividiColoriAlternativa(int[] a) {
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
