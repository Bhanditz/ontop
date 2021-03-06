/*
 * Copyright (C) 2009-2013, Free University of Bozen Bolzano
 * This source code is available under the terms of the Affero General Public
 * License v3.
 * 
 * Please see LICENSE.txt for full license terms, including the availability of
 * proprietary exceptions.
 */
package it.unibz.krdb.obda.owlrefplatform.core.dagjgrapht;
import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.SimpleDirectedGraph;

/** Use to build a DAG and a named DAG.
 * 
 * We probably don't need this class we can simply used SimpleDirectedGraph<V,E> A directed graph. 
 * @author Sarah
 *
 */

public class DAGImpl<V,E> extends SimpleDirectedGraph <V,E> implements DAG {



	public DAGImpl(Class<? extends E> arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public DAGImpl(EdgeFactory<V,E> ef) {
		super(ef);

		//TODO
	}
	//check if the graph is a dag
	public boolean isaDAG(){
		return false;

	}

	//check if the graph is a named description dag
	public boolean isaNamedDAG(){
		return false;


	}



}
