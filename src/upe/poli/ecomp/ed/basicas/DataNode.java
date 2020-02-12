package upe.poli.ecomp.ed.basicas;
/**
 * 
 * @author Matheus Phelipe e Nilton Vieira.
 * @category
 *  Universidade de Pernambuco - UPE,
 *  Escola Politécnica de Pernambuco - POLI,
 *  Estruturas de Dados - 18.2
 *  @objective
 *  Resolução de exercícios diversos que envolvam as estruturas Lista, Pilha e Fila,
 *  Prof. Byron Leite
 *  @param <T>
 */

public class DataNode<T> {
	
	private T info;
	private DataNode<T> next;
	private DataNode<T> previous; //estruturas duplamente encadeadas
	
	public DataNode(T info) {
		this.info=info;
	}
	
	public DataNode() {
		
	}

	public T getInfo() {
		return info;
	}
	
	public void setInfo(T info) {
		this.info=info;
	}
	
	public DataNode<T> getNext(){
		return next;
	}
	
	public void setNext(DataNode<T> next) {
		this.next=next;
	}

	public DataNode<T> getPrevious() {
		return previous;
	}

	public void setPrevious(DataNode<T> previous) {
		this.previous = previous;
	}
	

}
