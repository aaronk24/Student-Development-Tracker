package org.tg8.sdt.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.AbstractListModel;

public class TG8ListModel<E> extends AbstractListModel {

	private List<E> myList;
	private static final long serialVersionUID = -9004971600457812914L;

	public TG8ListModel () {
		super();
		myList = new ArrayList<E>();
	}
	
	@Override
	public E getElementAt(int index) {
		return myList.get(index);
	}

	@Override
	public int getSize() {
		return myList.size();
	}
	
	//NOTE: There is not a collection-compatible implementation
	//of ListModel as part of the standard JRE. 
	//Creating a full blown implementation of a ListModel is outside the 
	//scope of this application, but needed methods have been
	//implemented with an eye towards following the conventions 
	//of the Collections api.
	
	/**
	 * Adds an object to the end of the list
	 * @param obj
	 * @return
	 */
	boolean add (E obj) {
		this.myList.add(obj);
		this.fireIntervalAdded(this, this.myList.size() - 1, this.myList.size() - 1);
		return true;
	}
	
	/**
	 * Adds all objects in c, in iterator order,
	 * to the list
	 * @param c
	 * @return
	 */
	boolean addAll (Collection<? extends E> c) {
		for (E obj : c) {
			this.add(obj);
		}
		return (c.size() > 0);
	}
	
	void clear () {
		int oldSize = this.myList.size();
		this.myList.clear();
		this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
		return;
	}
	
	boolean remove (Object obj) {
		int oldSize = this.myList.size();
		boolean returnVal = this.myList.remove(obj);
		this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
		return returnVal;
	}
}
