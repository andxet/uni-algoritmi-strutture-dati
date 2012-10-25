package binarytrees;

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import static java.awt.GraphicsEnvironment.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BinaryTreeGUI extends JFrame {
  private static int numFrame = 0;
  private BinaryTree tree;
  private JPanel controlPanel = new JPanel();
  private JTextField text = new JTextField(5);
  private JTextField text2 = new JTextField(10);
  private JButton insButton = new JButton("inserisci");
  private JButton elimButton = new JButton("elimina");
  private JTextField elimField = new JTextField(5);
  private JButton trimButton = new JButton("trim");
  private JButton trimmedButton = new JButton("trimmed");
  private JTextField trimField = new JTextField(2);
  private JPanel filePanel = new JPanel();
  private JButton loadButton = new JButton("Inserisci da file");
  private JButton resetButton = new JButton("Reset");
  private JMenuBar menuBar = new JMenuBar();
  private JMenu menu = new JMenu("Operazioni");

  private BinaryTreeGUI(String title, BinaryTree tr) {
    super(title);
    tree = tr;
    draw();

    resetButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          tree = new BinaryTree();
          repaint();
        }
      }
    );

    loadButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          JFileChooser chooser = new JFileChooser(".");
          FileNameExtensionFilter filter = new FileNameExtensionFilter("text file","txt");
          chooser.setFileFilter(filter);
          int returnVal = chooser.showOpenDialog(filePanel);
          if(returnVal == JFileChooser.APPROVE_OPTION) {
            String name = chooser.getSelectedFile().getName();
            readFile(name);
          }

          repaint();
        }
      }
    );

    insButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          try {
            int x = Integer.parseInt(text.getText());
            String s = text2.getText();
            tree.add(x, s);
          }
          catch(NumberFormatException ex) {
            System.out.println("elemento non intero");
          }
          catch(IllegalArgumentException ill) {
            System.out.println("stringa cammino errata");
          }
          repaint();
        }
      }
    );

    elimButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          try {
            int x = Integer.parseInt(elimField.getText());
            tree.removeSubtree(x);
            repaint();
          }
          catch(NumberFormatException ex) {
            System.out.println("Numero in formato non valido");
          }
        }
      }
    );

    trimButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          try {
            int n = Integer.parseInt(trimField.getText());
            tree.trim(n);
            repaint();
          }
          catch(NumberFormatException ex) {
            System.out.println("livello non intero");
          }
        }
      }
    );

    trimmedButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          try {
            int n = Integer.parseInt(trimField.getText());
            BinaryTree bt = tree.trimmed(n);
            new BinaryTreeGUI("Albero potato", bt);
          }
          catch(NumberFormatException ex) {
            System.out.println("livello non intero");
          }
        }
      }
    );
  }   // end constructors

  public BinaryTreeGUI(String title) {
    this(title, new BinaryTree());
  }

  public void draw() {
    GraphicsEnvironment ge = getLocalGraphicsEnvironment();
    Rectangle bounds = ge.getMaximumWindowBounds();
    int x = bounds.x + bounds.width/4 + numFrame*30;
    int y = bounds.y + bounds.height/4 + (numFrame++)*30;
    setBounds(x,y,2*bounds.width/3,2*bounds.height/3);
    add(BorderLayout.CENTER, getDrawingPanel());
    controlPanel.add(insButton);
    controlPanel.add(text);
    controlPanel.add(new JLabel("dove: "));
    controlPanel.add(text2);
    controlPanel.add(elimButton);
    controlPanel.add(elimField);
    controlPanel.add(trimButton);
    controlPanel.add(trimmedButton);
    controlPanel.add(new JLabel("al livello: "));
    controlPanel.add(trimField);
    add(BorderLayout.SOUTH, controlPanel);
    filePanel.add(loadButton);
    filePanel.add(resetButton);
    add(BorderLayout.NORTH, filePanel);
    setJMenuBar(menuBar);
    menuBar.add(menu);
    addMenuItems();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

/** agginge all'albero binario degli elementi come descritto
    in un file di testo di nome fileName */
  public void readFile(String fileName) {
    try {
      Scanner input = new Scanner(new File(fileName));
      while(input.hasNextLine()) {
        String line = input.nextLine();
        Scanner lineScan = new Scanner(line);
        int elem = lineScan.nextInt();
        String path = lineScan.hasNext()? lineScan.next() : "";
        tree.add(elem, path);
      }
    }
    catch(FileNotFoundException ex) {
      System.out.println("impossibile leggere il file " + fileName);
    }
    catch(Exception ex) {
      System.out.println("Bad input format - Eccezione: " + ex);
    }
  }

  private class TreeDrawing<E> {
    E element;
    TreeDrawing left, right;
    int x, y, width, height;

    TreeDrawing(E element, int x, int y, int width, int height, TreeDrawing left, TreeDrawing right) {
      this.element = element;
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.left = left;
      this.right = right;
    }
  } // end class TreeDrawing

  private class DrawingPanel extends JPanel {
    private int visitedNodeNumber = 1;
    private Graphics graphics;
    private int hScale = 40;
    private int vScale = 40;

    TreeDrawing build(BinaryTree.Node node, int level) {
      if(node==null) return null;
      else {
        TreeDrawing left = build(node.left, level + 1);
        String str = node.element.toString();
        Rectangle2D rect = graphics.getFontMetrics().getStringBounds(str, graphics);
        int width = (int)Math.round(rect.getWidth())+2;
        int height = (int)Math.round(rect.getHeight());
        int dx = (int)Math.round(rect.getCenterX());
        int dy = (int)Math.round(rect.getCenterY());
        int x = hScale*visitedNodeNumber - dx;
        int y = vScale*level + dy;
        visitedNodeNumber++;
        TreeDrawing right = build(node.right, level + 1);
        return new TreeDrawing<Integer>(node.element, x, y, width, height, left, right);
      }
    }

    void draw(TreeDrawing td) {
      if(td != null) {
        String str = td.element.toString();
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
      TreeDrawing td = build(tree.root, 1);
      draw(td);
      visitedNodeNumber = 1;
    }
  }

  DrawingPanel getDrawingPanel() {
    return new DrawingPanel();
  }

  private JMenuItem creaItem(String name, ActionListener itemListener) {
    JMenuItem item = new JMenuItem(name);
    item.addActionListener(itemListener);
    return item;
  }

  private ActionListener preorderListener = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      tree.printPreOrder();
    }
  };

  private ActionListener inorderListener = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      tree.printInOrder();
    }
  };

  private ActionListener heightListener = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      System.out.println("altezza = " + tree.height());
    }
  };

  private ActionListener cardiniListener = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      tree.scriviCardini();
    }
  };

  private ActionListener completoListener = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      System.out.println(tree.isComplete() ? "completo" : "non completo");
    }
  };

  private ActionListener copyListener = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      BinaryTree bt = tree.copy();
      new BinaryTreeGUI("Copia dell'albero", bt);
    }
  };

  private ActionListener reflectListener = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      tree.reflect();
      repaint();
    }
  };

  private ActionListener specularListener = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      BinaryTree bt = tree.specular();
      new BinaryTreeGUI("Albero speculare", bt);
    }
  };

  private ActionListener incListener = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      tree.increment();
      repaint();
    }
  };

  private void addMenuItems() {
    menu.add(creaItem("preorder", preorderListener));
    menu.add(creaItem("inorder", inorderListener));
    menu.add(creaItem("altezza", heightListener));
    menu.add(creaItem("cardini", cardiniListener));
    menu.add(creaItem("completo?", completoListener));
    menu.add(creaItem("crea copia", copyListener));
    menu.add(creaItem("rifletti", reflectListener));
    menu.add(creaItem("crea speculare", specularListener));
    menu.add(creaItem("increment", incListener));
  }

  public static void main(String[] args) {
    new BinaryTreeGUI("Alberi binari");
  }
}