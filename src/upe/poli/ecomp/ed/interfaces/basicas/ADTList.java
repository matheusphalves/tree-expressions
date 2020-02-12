package upe.poli.ecomp.ed.interfaces.basicas;

public interface ADTList<T> {
	
	public void add (T a);
	
	public boolean remove(T a);
	
	public boolean contains(T a);
	
	public int indexOf(T a);
	
	public void print();
	
	public int size();

}
