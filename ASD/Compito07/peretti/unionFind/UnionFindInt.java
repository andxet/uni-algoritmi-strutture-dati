package peretti.unionFind;

public interface UnionFindInt {
	int capacity(); // restituisce n;
	int find(int e);
	boolean union(int e1, int e2);
	void resize(int m);/* m > n: aumenta il range a 0..m-1, creando m-n nuovi singoletti */
}