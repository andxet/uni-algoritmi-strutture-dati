package peretti.collezioni;

public interface CodaConPriorita {//a minimo: 1 priorità massima, > 1 priorità minore in base al valore
	boolean vuota();
	void inserisci(Object o, int p);
	Object primo();
	Object estraiPrimo();
	void cambiaPriorità(Object o, int p);
}
