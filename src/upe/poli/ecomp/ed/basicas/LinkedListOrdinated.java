package upe.poli.ecomp.ed.basicas;

public class LinkedListOrdinated<T extends Comparable<T>> extends LinkedList<T>{
	
	public void add(T a) {
		
		if(getFirst()==null||getFirst().getInfo().compareTo(a)<=0) {//adicionar elemento inicial adicionar elemento na frente dos outros
			
			super.add(a); //adição de elemento à frente da lista
			
		}else {//adicionar elemento entre a lista, obedecendo ordenação
			
			DataNode<T> aux = this.getFirst();
			DataNode<T> aux2 = new DataNode<T>();
			
			while(aux.getNext()!=null) {
				
				if(aux.getNext().getInfo().compareTo(a)<=0) {
				
					break;
				}
				
				aux = aux.getNext();
			}		
			aux2.setInfo(a);
			aux2.setNext(aux.getNext());					
			aux.setNext(aux2);
			
			this.setSize(1);	
		}
		
	}

	//demais métodos não necessitam ser sobrescritos
}
