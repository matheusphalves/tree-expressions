package upe.poli.ecomp.ed.basicas;

import upe.poli.ecomp.ed.interfaces.basicas.ADTList;

public class LinkedList<T> implements ADTList<T> {
 
	private DataNode<T> first;
	private int size;
		
	public DataNode<T> getFirst() {
		return first;
	}

	public void setFirst(DataNode<T> first) {
		this.first = first;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size += size;
	}

	public void add(T a) {
	
		if(first==null) {
			
			first = new DataNode<T>(a);
			first.setNext(null);
			
		}else {
			
			DataNode<T> aux = new DataNode<T>(first.getInfo());
			aux.setNext(first.getNext());	
			first.setInfo(a);
			first.setNext(aux);	
		}
		
		size++;
	}

	public boolean remove(T a) {
		
		boolean status=false;
		
		
		while(this.contains(a)) {
			
			
			if(first.getInfo().equals(a)) { //elemento do inicio
					
				first = first.getNext();
				size--;
					
			}else {
				DataNode<T> aux = first;
				while(aux.getNext()!=null) {
					if(aux.getNext().getInfo().equals(a)) {
							
						aux.setNext(aux.getNext().getNext());
						size--;
						status=true;
						break;
					}
					aux = aux.getNext();
				}
			}	
			
		}
		
		return status;
	}

	public boolean contains(T a) {
		
		boolean status=false;
		DataNode<T> aux = first;
		
		while(aux!=null) {
			if(aux.getInfo().equals(a)) {
				status=true;
				break;
			}
			aux = aux.getNext();
		}	
		return status;
	}

	public int indexOf(T a) {
		int index = -1;
		int counter=0;
		
		DataNode<T> aux = first;
		
		while(aux!=null) {
			if(aux.equals(a)) {
				index=counter;
				break;
			}
			counter++;
			aux = aux.getNext();	
		}	
		return index;
	}
	
	public int lastIndexOf(T a) {
		
		int index = -1;
		int counter=0;
		
		DataNode<T> aux = first;
		
		while(aux!=null) {
			if(aux.getInfo().equals(a)) {
				index=counter;
			}
			counter++;
			aux = aux.getNext();	
		}	
		return index;
	}
	
	public T get(int a) {
		T resultado = null;	
		int contador=0;	
		DataNode<T> aux = this.getFirst();
		
		while(aux!=null){
			if(contador==a) {
				resultado = aux.getInfo();
			}
			aux = aux.getNext();
			contador++;
		}
		
		return resultado;
	}
	
	public String toString() {
		
		String retorno="";
		
		DataNode<T> aux = first;
		while(aux!=null) {
			retorno+= aux.getInfo() + " > ";
			aux = aux.getNext();
		}
		
		retorno += "null";
		
		return retorno;
	}

	public void print() {	
		System.out.println(this);
	}

	public int size() {
		return size;
	}
	
}
