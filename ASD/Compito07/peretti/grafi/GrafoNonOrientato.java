package peretti.grafi;

public interface GrafoNonOrientato {
	//Si realizzi una interfaccia GrafoNonOrientato che definisca il tipo
	//della struttura dati mutabile "grafo non orientato con nodi identificati univocamente da stringhe". L'interfaccia dovrà specificare almeno le seguenti operazioni:

	int nNodi();

	String toString();// che restituisca una stringa che rappresenta il grafo nel formato dot usato dal tool graphviz (vedi file di istruzioni).

	//- Una operazione che aggiunga un nuovo nodo:
	void aggiungiNodo(String nome);


	//- Una operazione che aggiunga un arco al grafo:
	void aggiungiArco(String nodo1, String nodo2, double peso, String nomeArco);
	//(per un grafo geografico il nome dell'arco potrà essere, ad esempio, una sigla di autostrada o di strada, o il nome di una via, ecc.)
	
	//- Una operazione che, dato un nodo di partenza s, costruisca l'albero dei cammini minimi da s, e ne restituisca il formato dot.
	String minPath(String s);
	
	//- Una operazione che, dato un nodo di partenza e un nodo di arrivo, visualizzi una descrizione del (o di un) cammino minimo dal nodo di partenza a quello di arrivo. Tale operazione deve ovviamente usare l'algoritmo di Dijkstra, ottimizzato -- se si vuole -- con l'euristica A*.
	String path(String from, String to);
	
	//- Una operazione che dato un nodo restituisca il minimo albero ricoprente usando l'algoritmo di Prim (scegliete come rappresentarlo).
	String alberoRicoprentePrim(String root);
	
	//- Una operazione che restituisca il minimo albero ricoprente usando l'algoritmo di Kruskal (scegliete come rappresentarlo).
	GrafoNonOrientatoListeDiAdiacenza alberoRicoprenteKruskal(String root);//Restituisco un grafo, anche se non è la struttura dati migliore per la memorizzazione di un albero, posso utili
}
