package com.vckadam.oopdesign.CircularArray;

import java.util.Iterator;

public class CircularArray<T> implements Iterator<T>{

	private T[] array;
	private int length, current;
	
	@SuppressWarnings("unchecked")
	public CircularArray(int len) {
		this.length = len;
		this.array = (T[])new Object[len];
		current = 0;
	}
	public void rotateRight(int k) {
		if (k < 0) {
			throw new RuntimeException("Invalid rotation count");
		}
		if (k == 0) {
			return;
		}
		k = k % this.length;
		this.rotateLeft(this.length-k);
	}
	public void rotateLeft(int k) {
		if (k < 0) {
			throw new RuntimeException("Invalid rotation count");
		}
		if (k == 0) {
			return;
		}
		k = k % this.length;
		reverse(0,k-1);
		reverse(k,this.length-1);
		reverse(0,this.length-1);
	}
	
	public void reverse(int start, int end){
		int i = start, j = end;
		while(i < j) {
			swap(i++, j--);
		}
	}
	public void swap(int i, int j) {
		T temp = this.array[i];
		this.array[i] = this.array[j];
		this.array[j] = temp;
	}
	public void setArray(int ind, T val) {
		if(ind < 0 || ind >= this.length) {
			throw new RuntimeException("Array Index is invalid");
		}
		this.array[ind] = val;
	}

	@Override
	public boolean hasNext() {
		return current < this.length;
	}

	@Override
	public T next() {
		return this.array[current++];
	}
	
	public static void main(String[] arg) {
		CircularArray<Integer> object = new CircularArray<Integer>(4);
		 object.setArray(0, 2);
		 object.setArray(1, 3);
		 object.setArray(2, 4);
		 object.setArray(3, 5);
		for (Object obj : object.array) {
			System.out.print(obj.toString()+" ");
		}
		System.out.println();
		object.rotateLeft(1);
		for (Object obj : object.array) {
			System.out.print(obj.toString()+" ");
		}
		System.out.println();
		object.rotateRight(2);
		for (Object obj : object.array) {
			System.out.print(obj.toString()+" ");
		}
		System.out.println();
		/*
		 * output : 
		 * 2 3 4 5 
		   3 4 5 2  	
           5 2 3 4 
		 */
		
	}

}
