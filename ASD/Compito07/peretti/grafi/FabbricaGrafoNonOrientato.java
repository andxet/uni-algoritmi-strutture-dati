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
	static final String path = "Compito07/grafi generati/";
	static final String ext = ".grf";
	
	public static GrafoNonOrientatoListeDiAdiacenza grafoAutostradeItaliane(){
		return caricaGrafo("Compito07/grafi/Autostrade2" + ext);
	}
	
	public static GrafoNonOrientatoListeDiAdiacenza grafoPrimo(){
		return caricaGrafo("Compito07/grafi/primo" + ext);
	}
	
	public static GrafoNonOrientatoListeDiAdiacenza caricaGrafo(String path){
		try {
			GrafoNonOrientatoListeDiAdiacenza.s((new File(".").getAbsolutePath()));
			return GrafoNonOrientatoListeDiAdiacenza.loadFromFile(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			GrafoNonOrientatoListeDiAdiacenza.s("Errore nel caricamento del file");
			e.printStackTrace();
		}
		return null;
	}
	
	public static String AlberoRicoprentePrim(GrafoNonOrientatoListeDiAdiacenza g, String root){
		return g.alberoRicoprentePrim(root);
	}
	
	public static void salvaSuFile(String s){
		try {
			File nome = new File(path);
			if(!nome.exists())
				nome.mkdirs();
			FileOutputStream file = new FileOutputStream(path + "grafo" + ++fileCount + ext);
			PrintStream out = new PrintStream(file); 
			out.println(s);
			out.close();
		} catch (IOException e) {
			System.out.println("Errore: " + e);
		}
	}
}
