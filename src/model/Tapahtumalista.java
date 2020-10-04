package model;

import java.util.PriorityQueue;

public class Tapahtumalista {
	
	private PriorityQueue<Tapahtuma> lista = new PriorityQueue<Tapahtuma>();
	
	public Tapahtumalista() {
		
	}
	
	public Tapahtuma poista() {
		Trace.out(Trace.Level.INFO, "Poisto " + lista.peek()); //palauttaa listan ensimmäisen
		return lista.remove();
	}

	public void lisaa (Tapahtuma t) {
		lista.add(t);
	}
	
	public long getSeuraavanAika() {
		return lista.peek().getAika();
	}
}
