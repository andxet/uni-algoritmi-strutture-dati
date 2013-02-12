package peretti.collezioni;

import java.util.Hashtable;

public class CodaConPrioritaImplemHeap<E, K extends Comparable<K>> implements CodaConPriorita<E, K>{
	
	private class ElemConPrior<F, L extends Comparable<L>> {
		F elem;
		L prior;
		ElemConPrior(F el, L p) {
			elem = el; prior = p;
		}
	}
	
	static final int dimensioneIniziale = 2;
	ElemConPrior<E, K> heap[];
	int indiceUltimo;
	private Hashtable<E, Integer> posizione;
	
	public CodaConPrioritaImplemHeap() {
		heap = new ElemConPrior[dimensioneIniziale];
		indiceUltimo = 0;
		posizione = new Hashtable<E,Integer>();
	}
	
	/**
	 * dato un nodo che sia già nell'ordine giusto rispetto ai figli, ma in generale non rispetto al genitore, lo fa risalire fino a un posto giusto;
	 * @param n
	 */
	private void moveUp(int i){
		if(i > indiceUltimo)
			throw new IllegalArgumentException();
		ElemConPrior<E, K> ep = heap[i];
		while (i > 1 && ep.prior.compareTo(heap[i/2].prior) < 0){
			heap[i] = heap[i/2];
			posizione.put((E) heap[i].elem, i);
			i = i/2;
		}
		heap[i] = ep;
		posizione.put((E) ep.elem,i);
	}
	/**
	 * dato un nodo che sia già nell'ordine giusto rispetto al genitore, lo fa scendere fino a un posto giusto.
	 * @param n
	 */
	private void moveDown(int i){
		if(i > indiceUltimo)
			throw new IllegalArgumentException();
		ElemConPrior<E, K> ep = heap[i];
		int j;
		while((j = 2 * i) <= indiceUltimo){
			if(j + 1 <= indiceUltimo && heap[j+1].prior.compareTo(heap [j].prior) < 0)
				j++;
			if(ep.prior.compareTo(heap[j].prior) <= 0)
				break;
			heap[i] = heap[j];
			posizione.put((E) heap[i].elem, i);
			i = j;
		}
		heap[i] = ep;
		posizione.put((E) ep.elem,i);
	}

	@Override
	public boolean vuota() {
		return indiceUltimo <= 0;
	}

	@Override
	public void inserisci(E o, K p) {
		if(indiceUltimo >= heap.length-1)
			riallocaRaddoppia(); 
		heap[++indiceUltimo] = new ElemConPrior(o,p);
		moveUp(indiceUltimo);
	}

	private void riallocaRaddoppia() {
		//System.out.println("Raddoppio l'array");
		ElemConPrior<E, K> nuovo[];
		nuovo = new ElemConPrior[heap.length * 2];
		for(int i = 1; i <= indiceUltimo; i++)
			nuovo[i] = heap[i];
		heap = nuovo;
	}
	
	private void riallocaRiduci() {
		//System.out.println("Riduco l'array");
		ElemConPrior<E, K> nuovo[];
		nuovo = new ElemConPrior[(heap.length) / 4 + 1];
		for(int i = 1; i <= indiceUltimo; i++)
			nuovo[i] = heap[i];
		heap = nuovo;
	}

	@Override
	public E primo() {
		return (E) heap[1].elem;
	}

	@Override
	public E estraiPrimo() {
		if(indiceUltimo <= 0)
			throw new IllegalArgumentException();
		E primo = (E) heap[1].elem;
		ElemConPrior<E, K> ultimo = heap[indiceUltimo];
		heap[indiceUltimo--] = null;
		posizione.remove(ultimo.elem);
		if(indiceUltimo > 0){
			heap[1] = ultimo;
			moveDown(1);
		}
		if(indiceUltimo < heap.length / 4)
			riallocaRiduci(); 
		return primo;
	}
	

	private void cambiaPriorita(int i, K p) {
		if(i > indiceUltimo)
			throw new IllegalArgumentException();
		heap[i].prior = p;
		moveDown(i);
		moveUp(i);

	}
	

	@Override
	public void cambiaPrioritaOggetto(E o, K p) {
		int i = posizione.get(o);
	    cambiaPriorita(i, p);
	}
	
	public K valore(E o){
		return heap[posizione.get(o)].prior;
	}
}
