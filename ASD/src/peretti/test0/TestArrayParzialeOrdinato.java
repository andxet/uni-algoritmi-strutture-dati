package peretti.test0;

import peretti.compito0.*;

public class TestArrayParzialeOrdinato {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayParzialeOrdinato a = new ArrayParzialeOrdinato(10); //Creo l'array ordinato
		try{
		a.inserisci("ciao");
		a.inserisci("semaforo");
		a.inserisci("zazo");
		a.inserisci("andrea");
		a.inserisci("avocado");
		a.inserisci("0ciao");
		if(a.inserisci("ciao") == -1)
			System.out.println("Restituito -1");
		a.inserisci("zuzzurellone");
		a.inserisci("caramella");
		a.inserisci("grammofono");
		a.inserisci("ermenegildo");
		a.inserisci("altra stringa"); //Questa è l'11a, genererà un eccezione.
		}catch (Exception e) {//Catturo l'eccezione
			e.printStackTrace();//Stampo le informazioni dell'eccezione
		}
		
		a.print(); //Stampo l'array
	}

}
