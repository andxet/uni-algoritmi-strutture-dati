package peretti.unionFind;

public class UnionFindIntArray<E> implements UnionFindInt {
	
	private int[] parentOrNSize;
	
	/* per ogni i in 0.. parentOrNSize.length-1,
	se parentOrNSize[i] < 0 allora i è la radice di un albero di size - parentOrNSize[i];
	se parentOrNSize[i] >= 0 allora parentOrNSize[i] è il padre di i.
	*/
	
	public UnionFindIntArray(int n) {
		parentOrNSize = new int[n]; 
		for(int i = 0; i < n; i++)
			parentOrNSize[i] = -1;
	 }//UnionFindIntArray
	

	@Override
	public int find(int j) {
		if (parentOrNSize[j] < 0)//Se è minore di 0 allora è la radice
			return j; // radice
		else {//Altrimenti effettuo la compressione
			parentOrNSize[j] = find(parentOrNSize[j]);
			return parentOrNSize[j];
		}
	}//find

	@Override
	public int capacity() {
		return parentOrNSize.length;
	}//capacity

	@Override
	public boolean union(int u1, int u2) {//kruskalUnion nelle slide
		int root1 = find(u1);
		int root2 = find(u2);
		if (root1 == root2)
			return false;
		if (parentOrNSize[root2] < parentOrNSize[root1]) {
			parentOrNSize[root1] += parentOrNSize[root2];
			parentOrNSize[root2] = root1;
		} else {
			parentOrNSize[root2] += parentOrNSize[root1];
			parentOrNSize[root1] = root2;
		}
		return true;
	}//union

	@Override
	public void resize(int m) {
		if(m < parentOrNSize.length)
			throw new IllegalArgumentException("La nuova dimensione e' maggiore della precedente.");
		int [] nuovo = new int[m];
		int i;
		for(i = 0; i < parentOrNSize.length; i++)
			nuovo[i] = parentOrNSize[i];
		for(; i < nuovo.length; i++)
			nuovo[i] = -1;
	}//resize




	
	
}
