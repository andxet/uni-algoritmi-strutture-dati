package peretti.testCollezioni;

import peretti.collezioni.CodaConPrioritaImplemHeap;

public class TestCodaConPriorita {
	
	static final String UNO = "primo";
	static final String DUE = "secondo";
	static final String TRE = "terzo";
	static final String QUATTRO = "quarto";
	static final String CINQUE = "quinto";
	static final String SEI = "sesto";
	static final String SETTE = "settimo";
	static final String OTTO = "ottavo";
	static final String NOVE = "nono";
	static final String DIECI = "decimo";
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CodaConPrioritaImplemHeap<String, Integer> coda = new CodaConPrioritaImplemHeap<String, Integer>();
		
		coda.inserisci(SETTE, 7);
		coda.inserisci(UNO, 1);
		coda.inserisci(OTTO, 8);
		coda.inserisci(TRE, 3);
		coda.inserisci(DIECI, 10);
		coda.inserisci(QUATTRO, 4);
		coda.inserisci(SEI, 6);
		coda.inserisci(DUE, 2);
		coda.inserisci(NOVE, 9);
		coda.inserisci(CINQUE, 5);
		
		while (!coda.vuota()) System.out.println(coda.estraiPrimo());
		
		coda.inserisci(TRE, 3);
		coda.inserisci(DIECI, 10);
		coda.inserisci(SEI, 6);
		
		System.out.println("primo: " + coda.primo());
		
		coda.inserisci(DUE, 2);

		System.out.println("primo: " + coda.primo());
		
		coda.inserisci(OTTO, 8);

		System.out.println("primo: " + coda.primo());

		coda.inserisci(UNO, 1);

		System.out.println("primo: " + coda.primo());

		coda.estraiPrimo();
		coda.estraiPrimo();
		coda.estraiPrimo();
		
		System.out.println("primo: " + coda.primo());

		coda.inserisci(OTTO, 8);
		
		while (!coda.vuota()) System.out.println(coda.estraiPrimo());
		
		coda.inserisci(QUATTRO, 4);
		coda.inserisci(SEI, 6);
		coda.inserisci(DUE, 2);
		coda.inserisci(NOVE, 9);
		coda.inserisci(CINQUE, 5);
		coda.inserisci(SETTE, 7);
		coda.inserisci(UNO, 1);
		coda.inserisci(OTTO, 8);
		coda.inserisci(TRE, 3);
		coda.inserisci(DIECI, 10);
		
		coda.cambiaPrioritaOggetto(DIECI, 1);
		coda.cambiaPrioritaOggetto(UNO, 10);
		
		while (!coda.vuota()) System.out.println(coda.estraiPrimo());
		
		coda.inserisci("Ripara la bici", 3);
		coda.inserisci("Compra il giornale", 7);
		coda.inserisci("Innaffia le piante", 8);
		coda.inserisci("Compra un nuovo telefonino", 10);
		coda.inserisci("Porta l’auto dal meccanico", 5);
		coda.inserisci("Studia la parte teorica di Algoritmi", 1);
		coda.inserisci("Scarica le foto", 9);
		coda.inserisci("Svolgi i compiti di Algoritmi", 2);
		while (!coda.vuota()) System.out.println(coda.estraiPrimo());
		
	}

}
