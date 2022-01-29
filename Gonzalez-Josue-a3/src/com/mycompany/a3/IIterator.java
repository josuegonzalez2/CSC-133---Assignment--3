package com.mycompany.a3;

import com.mycompany.a3.GameObject;

public interface IIterator {
	
	public boolean hasNext();
	
	public GameObject getNext();
	
	public GameObject getCur();
}
