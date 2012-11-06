package peretti.bintree;

//import lists.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import static java.awt.GraphicsEnvironment.*;
import static java.lang.System.*;

/** La classe BinTree implementa "in stile libro di testo"
   la struttura ricorsiva "Albero binario di interi".
   I metodi sono tutti statici.
*/
public class BinTree {
  public int element;
  private BinTree left, right;

  public BinTree(int e, BinTree l, BinTree r) {
    element = e; left = l; right = r;
  }

  public BinTree(int e) {
    element = e; left = null; right = null;
  }

  public static BinTree binTree3(int e, int el, int er) {
    return new BinTree(e, new BinTree(el), new BinTree(er));
  }

/**
  Due classi interne usate per la visualizzazione
  dell'albero in una finestra grafica.
*/

/**
  Classe introdotta solo per ragioni didattiche,
  al fine di non appesantire la classe BinTree
*/
  private static class TreeDrawing {
    int element;
    TreeDrawing left, right;
    int x, y, width, height;

    TreeDrawing(int element, int x, int y, int width, int height, TreeDrawing left, TreeDrawing right) {
      this.element = element;
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.left = left;
      this.right = right;
    }
  }

/**
  Classe che permette di visualizzare
  l'albero in una finestra grafica.
*/

  private static class DrawingPanel extends JPanel {
    BinTree tree;
    private int visitedNodeNumber = 1;
    private Graphics graphics;
    private int hScale = 40;
    private int vScale = 40;

    DrawingPanel(BinTree t) {
      tree = t;
    }

    TreeDrawing build(BinTree t, int level) {
      if(t==null) return null;
      else {
        TreeDrawing left = build(t.left, level + 1);
        String str = Integer.toString(t.element);
        Rectangle2D rect = graphics.getFontMetrics().getStringBounds(str, graphics);
        int width = (int)Math.round(rect.getWidth())+2;
        int height = (int)Math.round(rect.getHeight());
        int dx = (int)Math.round(rect.getCenterX());
        int dy = (int)Math.round(rect.getCenterY());
        int x = hScale*visitedNodeNumber - dx;
        int y = vScale*level + dy;
        visitedNodeNumber++;
        TreeDrawing right = build(t.right, level + 1);
        return new TreeDrawing(t.element, x, y, width, height, left, right);
      }
    }

    void draw(TreeDrawing td) {
      if(td != null) {
        String str = Integer.toString(td.element);
        graphics.drawRect(td.x, td.y, td.width, td.height);
        graphics.drawString(str, td.x+1, td.y + td.height - 2);
        if(td.left != null) {
          int x1 = td.x + td.width/2;
          int y1 = td.y + td.height;
          int x2 = td.left.x + td.left.width/2;
          int y2 = td.left.y;
          graphics.drawLine(x1, y1, x2, y2);
        }
        draw(td.left);
        if(td.right != null) {
          int x1 = td.x + td.width/2;
          int y1 = td.y + td.height;
          int x2 = td.right.x + td.right.width/2;
          int y2 = td.right.y;
          graphics.drawLine(x1, y1, x2, y2);
        }
        draw(td.right);
      }
    }

    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      graphics = g;
      TreeDrawing td = build(tree, 1);
      draw(td);
      visitedNodeNumber = 1;
    }
  }
/* fine delle classi interne */

  static int numFrame = 0;

/** disegna l'albero t in una finestra di titolo title */
  public static void draw(BinTree t, String title) {
    final JFrame frame = new JFrame(title);
    JPanel controlPanel = new JPanel();
    JButton refreshButton = new JButton("refresh");

    refreshButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          frame.repaint();
        }
       }
     );

    controlPanel.add(refreshButton);
    GraphicsEnvironment ge = getLocalGraphicsEnvironment();
    Rectangle bounds = ge.getMaximumWindowBounds();
    int x = bounds.x + bounds.width/4 + numFrame*30;
    int y = bounds.y + bounds.height/4 + (numFrame++)*30;
    frame.setBounds(x,y,2*bounds.width/3,2*bounds.height/3);
    frame.add(BorderLayout.SOUTH, controlPanel);
    frame.add(BorderLayout.CENTER, new DrawingPanel(t));
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
  public static void draw(BinTree t) {
	  draw(t, "Alberi binari");
  }

/** costruisce e restituisce un albero binario descritto
    in un file di testo di nome fileName */
  public static BinTree buildFromFile(String fileName) {
    try {
      BinTree t = null;
      Scanner input = new Scanner(new File(fileName));
      while(input.hasNextLine()) {
        String line = input.nextLine();
        Scanner lineScan = new Scanner(line);
        int elem = lineScan.nextInt();
        String path = lineScan.hasNext()? lineScan.next() : "";
        t = add(elem, t, path);
      }
      return t;
    } catch(IOException e) { 
    	System.out.println("File non trovato.");
    	return null;
    	}
  }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*** INIZIO COMPITO 2   ***/
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
/** inserisce elem nell'albero t nel punto
    specificato dalla stringa path
    e restituisce l'albero t modificato */
  public static BinTree add(int elem, BinTree t, String path) {
    if(t==null)
    	return new BinTree(elem);
    if(path.length() == 0){
    	t.element = elem;
    	return t;
    }
    //System.out.println(path.length());
    char go = path.charAt(0);
    String nextPath = path.substring(1);
    if(go == 'L')
    	t.left = add(elem, t.left, nextPath);
    else if(go == 'R')
    	t.right = add(elem, t.right, nextPath);
    else
    	throw new IllegalArgumentException("Bad string");
    return t;
  }

/** visualizza in modo semplice l'albero t sulla consolle,
    ruotato di 90 gradi */
  public static void display(BinTree t) {
    display(t, 0);
  }

  private static void display(BinTree t, int k) {
    if(t != null) {
      display(t.right, k+1);
      for(int i = 0; i < k; i++) out.print("   ");
      out.println(t.element);
      display(t.left, k+1);
    }
  }

/** PRE-condizione: t non e' nullo;
    da' come risultato true se t e' una foglia, false altrimenti */
  public static boolean foglia(BinTree t) {
    return t.left == null && t.right == null;
  }

public static void preord(BinTree t) {
	if(t==null)
		return;

	System.out.print(t.element+" ");
	preord(t.left);
	preord(t.right);
  }

  public static void inord(BinTree t) {
	  if(t==null)
		  return;
	  
	  inord(t.left);
	  System.out.print(t.element+" ");
	  inord(t.right);
  }

  public static void postord(BinTree t) {
	  if(t==null)
		  return;
	  
	  inord(t.left);
	  inord(t.right);
	  System.out.print(t.element+" ");
  }

/** da' come risultato l'altezza dell'albero */ //L'altezza dell'albero nullo  di 0
  public static int altezza(BinTree t) {
	  if(t == null)
		  return 0;
	  else
		  return 1 + Math.max(altezza(t.left), altezza(t.right));
  }

  public static int height(BinTree t) { //L'altezza dell'albero nullo  di 0
	  return t==null ? 0 : 1 + Math.max(height(t.left), height(t.right));
  }

	/**
	 * da' come risultato la somma dei valori di tutti gli elementi
	 */
	public static int somma(BinTree t) {
		if (t == null)
			return 0;
		return t.element + somma(t.left) + somma(t.right);
	}

/** da' come risultato il numero dei nodi */
  public static int numNodi(BinTree t) {
	  return t == null ? 0 : numNodi(t.left) + numNodi(t.right) + 1;
  }

/** da' come risultato il massimo degli elementi;
    se l'albero e' vuoto da' come risultato Integer.MIN_VALUE
 */
  public static int max(BinTree t) {
	  if(t == null)
		  return Integer.MIN_VALUE;
	  int max = Math.max(BinTree.max(t.left), BinTree.max(t.right));
	  return Math.max(max, t.element);
	//return Math.max(t.element, Math.max(BinTree.max(t.left), BinTree.max(t.right)));
  }

/** da' come risultato il massimo dei pesi dei cammini
    dalla radice a una foglia,
    dove il peso del cammino e' la somma dei valori
    degli elementi dei nodi lungo il cammino
 */
  public static int maxPath(BinTree t) {
    return t == null ? 0 : t.element + Math.max(maxPath(t.left), maxPath(t.right));
  }


/** da' come risultato il numero delle foglie */
  public static int numFoglie(BinTree t) {
	  if(t == null)
		  return 0;
    return foglia(t) ? 1 : numFoglie(t.left) + numFoglie(t.right);
  }

/** modifica l'albero incrementando di 1 i valori
    di tutti gli elementi */
  public static void increment(BinTree t) {
    if(t == null)
    	return;
    t.element++;
    increment(t.left);
    increment(t.right);
  }

/** modifica l'albero incrementando di 1 i valori
    di tutte le foglie */
  public static void incrementFoglie(BinTree t) {
	  if(t == null)
	    	return;
	  if(foglia(t)){
		  t.element++;
	  }
	  else{
	    incrementFoglie(t.left);
	    incrementFoglie(t.right);
	  }
  }

/** da' come risultato true se l'elemento x si trova in t,
    false altrimenti  */
  public static boolean search(int x, BinTree t) {
    if(t == null)
    	return false;
    if(t.element == x)
    	return true;
    return search(x, t.left) || search(x, t.right);
  }


/** da' come risultato
    il (o un) sottoalbero di t avente come radice x, se esiste
    (senza creare nuovi nodi o un muovo albero);
    se invece x non e' presente in t, da' come risultato null  */
  public static BinTree find(int x, BinTree t) {
	  if(t == null)
		  return null;
	  if(t.element == x)
		  return t;
	  BinTree d = find(x, t.left);//In questo modo viene data la prioritˆ agli elementi pi a sinistra!!
	  if(d != null)
		  return d;
	  return find(x, t.right);
  }

  /*
    Due alberi t1 e t2 sono uguali se e solo se:
	  -sono entrambi nulli;
	oppure:
	  Ð sono entrambi non nulli ...
	  Ð e le due rispettive radici di t1 e di t2 sono uguali
	  Ð e i due rispettivi sottoalberi sinistri sono uguali
	  Ð e i due rispettivi sottoalberi destri uguali
  */
  public static boolean uguali(BinTree t1, BinTree t2) {
    // testa l'uguaglianza
	  return t1 == t2 || (t1 != null && t2 !=null && t1.element == t2.element && uguali(t1.left, t2.left) && uguali(t1.right, t2.right));
  }

  public static BinTree copia(BinTree t) {
    if(t == null)
    	return null;
    else
    	return new BinTree(t.element, copia(t.left), copia(t.right));
  }

  public static BinTree speculare(BinTree t) {
	  return t == null ? null : new BinTree(t.element, speculare(t.right), speculare(t.left));
  }

  //modifica l'albero t, senza creare nuovi nodi,
  // facendolo diventare il suo speculare
  public static void reflect(BinTree t) {
	  if(t == null)
		  return;
	  BinTree temp = t.left;
	  t.left = t.right;
	  t.right = temp;
	  reflect(t.left);
	  reflect(t.right);
  }

  /** restituisce la somma dei valori di tutti i nodi
      di un sottoalbero di t di radice x,
      includendo x stesso nella somma.
      Se esistono piu' elementi di valore x,
      restituisce il risultato relativo al primo elemento x che trova.
      Se il valore x non compare nell'albero, restituisce 0.
      Nota: puo' richiamare altri metodi di questa classe.
  */
  public static int sommaDiscendenti(int x, BinTree t) {
	if(t == null)
		return 0;
	if(t.element == x)
		return somma(t);
	int i = sommaDiscendenti(x, t.left);
	if (i > 0)
		return i;
	else return sommaDiscendenti(x, t.right);
  }

  /** scrive sulla console i nodi-cardine dell'albero
      (vedi libro di testo pag. 95):
      chiamiamo cardine un nodo tale che il suo livello nell'albero sia uguale
      all'altezza del sottoalbero di cui esso  radice, assumendo che 
      un (sotto)albero costituito da un solo nodo abbia altezza 0 
      (e quindi un sottoalbero vuoto abbia altezza -1).
  */
  public static void scriviCardini(BinTree t) {
    // si deve usare una funzione ausiliaria
	cardini(t, 0);
  }
  
  private static int cardini(BinTree t, int x){
	  if(t == null)
          return -1;
      int hl = cardini(t.left, x + 1);
      int hd = cardini(t.right, x + 1);    
      int high;
      if(hl >= hd)
          high = hl + 1;
      else
          high = hd + 1;
      if(x == high)
          System.out.println(t.element + "\t");
         
      return high;
  }

}