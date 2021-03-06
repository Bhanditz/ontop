/*
 * Copyright (C) 2009-2013, Free University of Bozen Bolzano
 * This source code is available under the terms of the Affero General Public
 * License v3.
 * 
 * Please see LICENSE.txt for full license terms, including the availability of
 * proprietary exceptions.
 */
package it.unibz.krdb.obda.ontology;

import it.unibz.krdb.obda.model.Predicate;

/***
 * ABox assertions a.k.a. intentional axioms. Data assertions.
 */
public interface Assertion extends Axiom {

	public int getArity();
	
	public Predicate getPredicate();
}
