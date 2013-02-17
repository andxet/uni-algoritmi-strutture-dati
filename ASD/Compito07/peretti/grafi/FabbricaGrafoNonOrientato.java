package peretti.grafi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

public class FabbricaGrafoNonOrientato {
	static int fileCount = 0;
	static final String path = "grafigenerati/";
	static final String ext = ".grf";
	GrafoNonOrientato grafo;
	
	//caricamento grafi
	public void setGrafoAutostradeItaliane(){
		caricaGrafo("grafi/Autostrade2" + ext);
	}
	
	public void setGrafoPrimo(){
		caricaGrafo("grafi/primo" + ext);
	}
	
	public void setGrafoSecondo(){
		caricaGrafo("grafi/grafo2" + ext);
	}
	
	public boolean caricaGrafo(String path){
		try {
			GrafoNonOrientatoListeDiAdiacenza.s((new File(".").getAbsolutePath()));
			grafo = GrafoNonOrientatoListeDiAdiacenza.loadFromFile(path);
			return true;
		} catch (IOException e) {
			GrafoNonOrientatoListeDiAdiacenza.s("Errore nel caricamento del file");
			e.printStackTrace();
		}
		return false;
	}
	
	public String percorso(String da, String a){
		try{
			return grafo.path(da, a);
		}catch (IllegalArgumentException e){
			return e.getMessage();
		}
	}
	
	public String camminiMinimi(String root){
		return grafo.minPath(root);
	}
	
	public String alberoRicoprentePrim(String root){
		return grafo.alberoRicoprentePrim(root);
	}
	
	public String alberoRicoprenteKruskal(){
		return grafo.alberoRicoprenteKruskal();
	}
	
	@Override public String toString(){
		return grafo.toString();
	}
	
	public static void salvaSuFile(String nomefile, String s){
		try {
			File nome = new File(path);
			if(!nome.exists())
				nome.mkdirs();
			FileOutputStream file = new FileOutputStream(path + ++fileCount + " " + nomefile + ext);
			PrintStream out = new PrintStream(file); 
			out.println(s);
			out.close();
		} catch (IOException e) {
			System.out.println("Errore: " + e);
		}
	}
	
	public static void salvaSuFile(String s){
		salvaSuFile("grafo", s);
	}
}
