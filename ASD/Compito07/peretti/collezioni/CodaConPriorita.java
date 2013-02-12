package peretti.collezioni;

public interface CodaConPriorita<E, K extends Comparable<?>> {
	//a minimo: 1 priorità massima, > 1 priorità minore in base al valore
	boolean vuota();
	void inserisci(E o, K p);
	E primo();
	E estraiPrimo();
	//void cambiaPriorita(int i, K p);
	void cambiaPrioritaOggetto(E o, K p);
	K valore(E o);//Restituisce il peso di o
}
