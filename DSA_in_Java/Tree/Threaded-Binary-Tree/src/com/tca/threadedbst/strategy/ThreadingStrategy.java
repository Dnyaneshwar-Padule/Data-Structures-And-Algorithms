package com.tca.threadedbst.strategy;

import com.tca.threadedbst.context.InsertContext;

public interface ThreadingStrategy<E>{
	
	public void afterInsert(InsertContext<E> insertContext);

}
