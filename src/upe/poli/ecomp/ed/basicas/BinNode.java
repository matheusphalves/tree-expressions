package upe.poli.ecomp.ed.basicas;

public class BinNode<T> {
	
	T data;
	BinNode<T> left;
	BinNode<T> right;
	
	public BinNode(T element) {
		data = element;
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public BinNode<T> getLeft() {
		return left;
	}
	public void setLeft(BinNode<T> left) {
		this.left = left;
	}
	public BinNode<T> getRight() {
		return right;
	}
	public void setRight(BinNode<T> right) {
		this.right = right;
	}
	
	@Override
	public String toString() {
		return ""+data;
	}

}
