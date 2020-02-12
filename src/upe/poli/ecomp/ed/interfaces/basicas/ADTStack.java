package upe.poli.ecomp.ed.interfaces.basicas;

public interface ADTStack<T> {
	
	public void push(T element);
	
	public T pop();
	
	public T top();
	
	public void print();
	
	public int size();
	
	public boolean isEmpty();
	
	public boolean isFull();

}
