package peretti.testGrafi;

import java.util.HashMap;

import javax.swing.text.html.MinimalHTMLWriter;

import peretti.grafi.*;

public class TestGrafoNonOrientato {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GrafoNonOrientatoListeDiAdiacenza grafo = FabbricaGrafoNonOrientato.grafoAutostradeItaliane();
		s(grafo.toString());
		s(grafo.path("Milano", "Bardonecchia"));
		s(grafo.path("Bardonecchia","Milano"));
		try{
		s(grafo.path("Milano", "ggg"));
		}catch (IllegalArgumentException e){
			e.getMessage();
		}
		s(grafo.path("Bardonecchia", "Teramo"));
		
		s("Albero cammini minimi Torino: " + grafo.minPath("Milano"));
		s("Albero cammini minimi Torino: " + grafo.minPath("Torino"));
		s("Albero cammini minimi Trieste: " + grafo.minPath("Trieste"));
		s("Albero cammini minimi ggg: " + grafo.minPath("ggg"));
		
		String s = FabbricaGrafoNonOrientato.AlberoRicoprentePrim(grafo, "Milano");
		s(s);
		FabbricaGrafoNonOrientato.salvaSuFile(s);
		s = FabbricaGrafoNonOrientato.AlberoRicoprentePrim(grafo, "Bardonecchia");
		s(s);
		FabbricaGrafoNonOrientato.salvaSuFile(s);
		s = FabbricaGrafoNonOrientato.AlberoRicoprentePrim(grafo, "Bardonecchia");
		s(s);
		FabbricaGrafoNonOrientato.salvaSuFile("ggg");
	}
	
	public static void s(String s){
		System.out.println(s);
	}

}
