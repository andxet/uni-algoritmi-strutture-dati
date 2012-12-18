package peretti.collezioni;

public class CodaConPrioritaImplemHeap implements CodaConPriorita{
	
	private class ElemConPrior {
		Object elem;
		int prior;
		ElemConPrior(Object el, int p) {
			elem = el; prior = p;
		}
	}
	
	ElemConPrior heap[];
	int indiceUltimo;	
	
	public CodaConPrioritaImplemHeap(int n) {
		heap = new ElemConPrior[n+1];
		indiceUltimo = 0;
	}
	
	/**
	 * dato un nodo che sia già nell'ordine giusto rispetto ai figli, ma in generale non rispetto al genitore, lo fa risalire fino a un posto giusto;
	 * @param n
	 */
	private void moveUp(int i){
		if(i > indiceUltimo)
			throw new IllegalArgumentException();
		ElemConPrior ep = heap[i];
		while (i > 1 && ep.prior < heap[i/2].prior){
			heap[i] = heap[i/2];
			i = i/2;
		}
		heap[i] = ep;		
	}
	/**
	 * dato un nodo che sia già nell'ordine giusto rispetto al genitore, lo fa scendere fino a un posto giusto.
	 * @param n
	 */
	private void moveDown(ElemConPrior n){
		
	}

	@Override
	public boolean vuota() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void inserisci(Object o, int p) {
		if(indiceUltimo == heap.length-1)
			rialloca(); 
		heap[++indiceUltimo] = new ElemConPrior(o,p);
		moveUp(indiceUltimo);
	}

	@Override
	public Object primo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object estraiPrimo() {
		// TODO Auto-generated method stub
		
		//moveDown(n);
		return null;
	}

	@Override
	public void cambiaPriorità(Object o, int p) {
		// TODO Auto-generated method stub
		
	}

}
