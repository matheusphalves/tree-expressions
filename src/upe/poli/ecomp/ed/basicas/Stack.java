package upe.poli.ecomp.ed.basicas;

import upe.poli.ecomp.ed.interfaces.basicas.ADTStack;

public class Stack<T> implements ADTStack<T> {

	
	private DataNode<T> top;
	private int size;
	
	
	public void push(T element) {
		
		if(top==null) { //primeiro elemento
			
			top = new DataNode<T>();
			top.setInfo(element);
			top.setNext(null);
			
		}else {
				
			DataNode<T> aux = new DataNode<T>();
			aux.setInfo(top.getInfo());
			aux.setNext(top.getNext());		
			top.setInfo(element);
			top.setNext(aux);
			
		}
		
		size++;
		
	}

	public T pop() {
		T element = null;
		
		if(!this.isEmpty()) {
			element = top.getInfo();
			top = top.getNext();
			size--;
		}
		
		
		return element;
	}

	public T top() {
		
		T element = null;
		
		if(!this.isEmpty()) {
			element = this.top.getInfo();
		}
		
		return element;
	}

	public String toString() {
		
		DataNode<T> aux = top;
		String retorno="";
		
		while(aux!=null) {
			retorno+= aux.getInfo() + " > ";
			aux = aux.getNext();
		}
		
		retorno+= "null";
		return retorno;
	}

	public void print() {
		System.out.println(this.toString());
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size==0;
	}

	public boolean isFull() {
		return false;
	}
	
}
