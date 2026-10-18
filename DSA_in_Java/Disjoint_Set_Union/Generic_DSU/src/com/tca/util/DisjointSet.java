package com.tca.util;

public interface DisjointSet <T> {

	/** 
	 * Adds a new element as a standalone singleton set. 
	 */
    boolean makeSet(T element);

    /** 
     * Finds the representative token of the set containing the element. 
     */
    T find(T element);

    /** 
     * Merges the sets containing element1 and element2. 
     */
    boolean union(T element1, T element2);

    /** 
     * Checks if two elements belong to the same set.
     */
    boolean isConnected(T element1, T element2);

    /** 
     * Returns the total number of distinct elements tracked. 
     */
    int size();
    
    int totalSets();
    
    boolean elementExists(T element);

}
