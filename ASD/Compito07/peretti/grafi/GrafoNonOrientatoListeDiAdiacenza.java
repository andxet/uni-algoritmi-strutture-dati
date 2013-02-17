package peretti.grafi;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import peretti.collezioni.*;
import peretti.unionFind.UnionFindE;

public class GrafoNonOrientatoListeDiAdiacenza implements GrafoNonOrientato{
	///Classe interna Nodo
	private class Nodo{
		protected String element;
		protected ArrayList<Nodo> listaAdiacenza;
		protected ArrayList<Double> pesi;
		protected ArrayList<String> nomi;

		Nodo(String s){
			this.element = s;
			listaAdiacenza = new ArrayList<Nodo>();
			pesi = new ArrayList<Double>();
			nomi = new ArrayList<String>();
		}

		public void aggiungiArco(Nodo g, double peso, String nomeArco) {
			if(this.element.equals(g.element))
				throw new IllegalArgumentException("Non sono permessi cappi");//I cappi non sono permessi
			listaAdiacenza.add(g);
			pesi.add(peso);
			nomi.add(nomeArco);
		}
	}//Fine classe interna Nodo

	HashMap<String, Nodo> nodi;
	String nome;
	Boolean dijApplicabile;

	public GrafoNonOrientatoListeDiAdiacenza(String s) {
		nodi = new HashMap<String, Nodo>();
		nome = s;
		dijApplicabile = true;
	}

	public GrafoNonOrientatoListeDiAdiacenza() {
		this("Grafo");
	}

	@Override
	public int nNodi() {
		return nodi.size();
	}

	@Override
	public void aggiungiNodo(String nome) {
		nodi.put(nome, new Nodo(nome));

	}

	@Override
	public void aggiungiArco(String nodo1, String nodo2, double peso, String nomeArco) {//Siccome non è un grafo orientato, aggiungo l'arco in entrambi i nodi
		Nodo n1, n2;
		n1 = nodi.get(nodo1);
		n2 = nodi.get(nodo2);
		if(n1 == null){
			aggiungiNodo(nodo1);
			n1 = nodi.get(nodo1);
		}
		if(n2 == null){
			aggiungiNodo(nodo2);
			n2 = nodi.get(nodo2);
		}
		n1.aggiungiArco(n2, peso, nomeArco);//Aggiungo l'arco al primo nodo
		n2.aggiungiArco(n1, peso, nomeArco);//E poi al secondo
		if(peso < 0)
			dijApplicabile = false;
	}


	public static GrafoNonOrientatoListeDiAdiacenza loadFromFile(String path) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
		GrafoNonOrientatoListeDiAdiacenza grafo = new GrafoNonOrientatoListeDiAdiacenza();
		String strLine, strPart[];
		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
			strPart = strLine.split(":");
			if(strPart.length != 4){
				s("salto");
				continue;
			}
			grafo.aggiungiArco(strPart[1], strPart[2], Double.valueOf(strPart[3]), strPart[0]);
			s("Aggiunto arco: " + strPart[0] + " " + strPart[1] + " " + strPart[2] + " " + strPart[3]);
		}
		br.close();
		return grafo;
	}

	public HashMap<String, String> dijkstra(String s){//Utilizzo come indice la stringa elem dei nodi, non il riferimento all'oggetto
		if(!dijApplicabile)
			//	throw new Exception("Il grafo contiene pesi negativi");
			return null;//Il grafo contiene pesi negativi
		CodaConPriorita<String, Double> Q = new CodaConPrioritaImplemHeap<String, Double>();//Utilizzo la coda com priorità perchè risulterà comoda per l'estrazione del peso più basso

		HashMap<String, Double> dist = new HashMap<String, Double>();
		HashMap<String, String> padre = new HashMap<String, String>();//Creo le HashMap

		Iterator<String> elem = nodi.keySet().iterator();//Ottengo l'elenco dei nodi
		String keya;

		while(elem.hasNext()){//Assegno +inf alle distanze, null all'elenco dei padri
			keya = elem.next();
			dist.put(keya, Double.POSITIVE_INFINITY);
			padre.put(keya, null);
			Q.inserisci(keya, Double.POSITIVE_INFINITY);
		}

		dist.put(s, 0.0d);
		padre.put(s, s);
		Q.cambiaPrioritaOggetto(s, 0.0d);

		//String cammino = s;
		String u;

		while (!Q.vuota()){
			u = Q.estraiPrimo();
			Nodo nodoOggettoDiU = nodi.get(u);
			for(int v = 0; v < nodoOggettoDiU.listaAdiacenza.size(); v++){
				double pesoV = nodoOggettoDiU.pesi.get(v);
				String elementoV = nodoOggettoDiU.listaAdiacenza.get(v).element;
				if(dist.get(u) + pesoV < dist.get(elementoV)){
					padre.put(elementoV, u);
					dist.put(elementoV, dist.get(u) + pesoV);
					Q.cambiaPrioritaOggetto(elementoV, dist.get(elementoV));
				}
			}		
		}
		return padre;
	}//dijkstra

	@Override
	public String path(String from, String to) {
		if(!nodi.containsKey(from) || !nodi.containsKey(to))//controllo se esistono entrambi i nodi
			//throw new IllegalArgumentException("Uno dei nodi non esiste.");
			return "Nodo/i inesistente/i";

		if(!dijApplicabile)
			return null;

		HashMap<String, String> camminiMinimi;
		camminiMinimi = dijkstra(from);
		String cammino = "", iter = to;

		while(iter != from && iter != null){
			cammino = "--->".concat(iter + cammino);
			iter = camminiMinimi.get(iter);
		}

		if(iter == null)
			cammino = from + "x--Percorso non disponibile--x" + to;
		return cammino;
	}//path

	public GrafoNonOrientatoListeDiAdiacenza kruskal() {//Restituisce un grafo non orientato, in realtà quel grafo è un albero.

		class Arco{
			String da, a, nome;
			double peso;

			Arco(String da, String a, double peso, String nome){
				this.a = a;
				this.da = da;
				this.peso = peso;
				this.nome = "";
			}

			Arco(String da, String a, double peso){
				this(da, a, peso, "");
			}
		}//Arco

		CodaConPrioritaImplemHeap<Arco, Double> PQ = new CodaConPrioritaImplemHeap<Arco, Double>();
		UnionFindE<String> forestaMAR = new UnionFindE<String>();
		GrafoNonOrientatoListeDiAdiacenza alberoRicoprente = new GrafoNonOrientatoListeDiAdiacenza();

		Iterator<Nodo> elencoNodi = nodi.values().iterator();
		while(elencoNodi.hasNext()){
			Nodo nodo = elencoNodi.next();
			ArrayList<Nodo> nodiAdiacenti = nodo.listaAdiacenza;
			ArrayList<Double> pesiAdiacenti = nodo.pesi;
			ArrayList<String> nomiAdiacenti = nodo.nomi;
			for(int v = 0; v < nodiAdiacenti.size(); v++){
				Arco a = new Arco(nodo.element, nodiAdiacenti.get(v).element, pesiAdiacenti.get(v), nomiAdiacenti.get(v));
				PQ.inserisci(a, pesiAdiacenti.get(v));
			}
			forestaMAR.makeSet(nodo.element);
		}

		while(!PQ.vuota()){
			Arco arco = PQ.estraiPrimo();
			if(forestaMAR.kruskalUnion(arco.da, arco.a)){
				alberoRicoprente.aggiungiArco(arco.da, arco.a, arco.peso, arco.nome);
			}
		}
		return alberoRicoprente;
	}//kruskal
	
	@Override public String alberoRicoprenteKruskal(){
		return kruskal().toString();
	} 

	@Override
	public String minPath(String s) {
		if(!nodi.containsKey(s))//controllo se esiste il nodo
			return "Nodo inesistente";

		if(!dijApplicabile)
			return "Algoritmo non applicabile, esistono archi con peso negativo";

		HashMap<String, String> camminiMinimi = dijkstra(s);
		return this.ArrayToDot(camminiMinimi);
		/*//Questa parte di codice produce una stringa nel formato --->nodo--->nodo ecc.
		Iterator<String> nodi = camminiMinimi.keySet().iterator();
		String cammino = "", temp;

		while(nodi.hasNext()){
			temp = nodi.next();
			while(temp != s && temp != null){
				cammino = cammino.concat("--->" + temp);
				temp = camminiMinimi.get(temp);
			}
			if(temp == null)
				cammino = cammino.concat("--X -Cammino non disponibile- X\n");
			else
				cammino = cammino.concat("\n");
		}

		return cammino;*/
	}//minPath

	@Override
	public String alberoRicoprentePrim(String root) {//Restituisco il formato dot dell'albero
		return this.ArrayToDot(prim(root));
	}//alberoRicoprentePrim

	public HashMap<String, String> prim(String root) {
		if(!nodi.containsKey(root))
			return null;
		Iterator<String> insiemeNodi = nodi.keySet().iterator();
		HashMap<String, Double> dist = new HashMap<String, Double>();
		HashMap<String, Boolean> definitivo = new HashMap<String, Boolean>();
		HashMap<String, String> padre = new HashMap<String, String>();
		CodaConPrioritaImplemHeap<String, Double> Q = new CodaConPrioritaImplemHeap<String, Double>();
		while (insiemeNodi.hasNext()){
			String nodo = insiemeNodi.next();
			dist.put(nodo, Double.POSITIVE_INFINITY);
			definitivo.put(nodo, false);
			padre.put(nodo, null);
			Q.inserisci(nodo, Double.POSITIVE_INFINITY);
		}
		padre.put(root, root);
		dist.put(root, 0.0d);
		Q.cambiaPrioritaOggetto(root, 0.0d);
		while(!Q.vuota()){
			String u = Q.estraiPrimo();
			definitivo.put(u, true);
			Nodo nodoOggettoDiU = nodi.get(u);
			for(int v = 0; v < nodoOggettoDiU.listaAdiacenza.size(); v++){
				double pesoV = nodoOggettoDiU.pesi.get(v);
				String elementoV = nodoOggettoDiU.listaAdiacenza.get(v).element;
				if(!definitivo.get(elementoV) && pesoV < dist.get(elementoV)){
					padre.put(elementoV, u);
					dist.put(elementoV, pesoV);
					Q.cambiaPrioritaOggetto(elementoV, dist.get(elementoV));
				}
			}
		}

		return padre;
	}//prim
	
	@Override
	public String toString(){//Supporta solo grafi non multi-arco (a causa del controllo)
		List<String> archiStampati = new ArrayList<String>();
		String s = "graph " + nome + " {\n";
		Iterator<Entry<String, Nodo>> elencoNodi = nodi.entrySet().iterator();
		Nodo nodo, vicino;
		while (elencoNodi.hasNext()){
			nodo = elencoNodi.next().getValue();

			for(int i = 0; i < nodo.listaAdiacenza.size(); i ++){
				vicino = nodo.listaAdiacenza.get(i);
				if(archiStampati.contains(nodo.element.concat(vicino.element)))
					continue;//Se l'arco tra i due nodi è già stato inserito, viene saltato (per questo non sono supportati i grafi multi-arco)
				vicino = nodo.listaAdiacenza.get(i);
				s = s.concat("\"" + nodo.element + "\" -- \"" + vicino.element + "\" [label = \"" + nodo.pesi.get(i) + "\\n" + nodo.nomi.get(i) + "\"];\n");
				archiStampati.add(vicino.element.concat(nodo.element));
			}
		}
		s = s.concat("}");
		return s;
	}//toString
	
	private String ArrayToDot(HashMap<String, String> albero){
		if(albero == null)
			return null;
		String nodo, s = "graph " + nome + " {\n";
		Iterator<String> keys = albero.keySet().iterator();
		while(keys.hasNext()){
			nodo = keys.next();
			if(albero.get(nodo) == null || nodo.equals(albero.get(nodo)))
				continue;
			s = s.concat("\"" + nodo + "\" -- \"" + albero.get(nodo) + "\";\n");
		}
		s = s.concat("}");
		return s;
	}

	public String getNome(){
		return this.nome;
	}//getNome

	public static void s(String s){
		System.out.println(s);
	}//s
}
