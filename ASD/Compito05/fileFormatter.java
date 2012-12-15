import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class fileFormatter {

	public static void main(String[] args){
		if(args.length == 0){
			s("Specifica un nome di file!");
			System.exit(-1);
		}
		try {
			BufferedReader fin = new BufferedReader(new FileReader(args[0]));
			BufferedWriter fout = new BufferedWriter(new FileWriter("modificato " + new File(args[0]).getName()));
			String temp;
			while((temp = fin.readLine()) != null){
				temp = temp.replace(".", ",");
				fout.write(temp + "\n");
			}
			fin.close();
			fout.close();
		} catch (FileNotFoundException e) {
			s("Scrivi bene il nome del file idiota!!");
			e.printStackTrace();
		} catch (IOException e) {
			s("Scrivi bene il nome del file idiota!!");
			e.printStackTrace();
		}
		
	}
	
	public static void s(String s){
		System.out.println(s);
	}
}
