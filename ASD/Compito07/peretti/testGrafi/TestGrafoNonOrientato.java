package peretti.testGrafi;

import peretti.grafi.*;
import static peretti.grafi.FabbricaGrafoNonOrientato.*;

public class TestGrafoNonOrientato {
	//Il programma salva la descrizione dei grafi in formato dot nella cartella grafigenerati.
	//Nella cartella grafigenerati, lo script bash genera per ogni file di estensione grf, tramite il comando dot, la rappresentazione dei grafi.
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FabbricaGrafoNonOrientato grafo = new FabbricaGrafoNonOrientato();/*
		grafo.setGrafoAutostradeItaliane();
		s(grafo.toString());
		s(grafo.path("Milano", "Bardonecchia"));
		s(grafo.path("Bardonecchia","Milano"));
		try{
		s(grafo.path("Milano", "ggg"));
		}catch (IllegalArgumentException e){
			e.getMessage();
		}
		s(grafo.path("Bardonecchia", "Teramo"));
		
		s("Albero cammini minimi Torino: " + camminiMinimi(grafo, "Milano"));
		s("Albero cammini minimi Torino: " + camminiMinimi(grafo, "Torino"));
		s("Albero cammini minimi Trieste: " + camminiMinimi(grafo, "Trieste"));
		s("Albero cammini minimi ggg: " + camminiMinimi(grafo, "ggg"));
		
		String s = alberoRicoprentePrim(grafo, "Milano");
		s(s);
		salvaSuFile("prim from milano", s);
		s = alberoRicoprentePrim(grafo, "Bardonecchia");
		s(s);
		salvaSuFile("prim from bardonecchia", s);
		s = alberoRicoprentePrim(grafo, "Bardonecchia");
		s(s);
		s = alberoRicoprenteKruskal(grafo);
		salvaSuFile("kruskal", s);
		
		///////////*/
		grafo.setGrafoSecondo();
		salvaSuFile("Grafo secondo", grafo.toString());
		s(grafo.percorso("A", "L"));
		s(grafo.percorso("A", "H"));
		s(grafo.percorso("A", "Z"));
		salvaSuFile("Grafo secondo cammini minimi A", grafo.camminiMinimi("A"));
		salvaSuFile("Grafo secondo cammini minimi L", grafo.camminiMinimi("L"));
		salvaSuFile("Grafo secondo cammini minimi G", grafo.camminiMinimi("G"));
		salvaSuFile("Grafo secondo prim A", grafo.alberoRicoprentePrim("A"));
		salvaSuFile("Grafo secondo prim L", grafo.alberoRicoprentePrim("L"));
		salvaSuFile("Grafo secondo prim G", grafo.alberoRicoprentePrim("G"));
		salvaSuFile("Grafo secondo kruskal", grafo.alberoRicoprenteKruskal());		
	}
	
	public static void s(String s){
		System.out.println(s);
	}

}
