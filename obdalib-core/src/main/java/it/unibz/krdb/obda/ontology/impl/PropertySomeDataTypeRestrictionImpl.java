/*
 * Copyright (C) 2009-2013, Free University of Bozen Bolzano
 * This source code is available under the terms of the Affero General Public
 * License v3.
 * 
 * Please see LICENSE.txt for full license terms, including the availability of
 * proprietary exceptions.
 */
package it.unibz.krdb.obda.ontology.impl;

import it.unibz.krdb.obda.model.Predicate;
import it.unibz.krdb.obda.ontology.DataType;
import it.unibz.krdb.obda.ontology.PropertySomeDataTypeRestriction;

public class PropertySomeDataTypeRestrictionImpl implements PropertySomeDataTypeRestriction {

	private static final long serialVersionUID = -3140348825768903966L;

	private final Predicate predicate;
	private final boolean isInverse;
	private final DataType filler;
	
	public PropertySomeDataTypeRestrictionImpl(Predicate p, boolean isInverse, DataType filler) {
		this.predicate = p;
		this.isInverse = isInverse;
		this.filler = filler;
	}

	@Override
	public boolean isInverse() {
		return isInverse;
	}
	
	@Override
	public Predicate getPredicate() {
		return predicate;
	}

	@Override
	public DataType getFiller() {
		return filler;
	}
	
	public int hashCode() {
		return toString().hashCode();
	}
	
	public boolean equals(Object obj) {
		if (!(obj instanceof PropertySomeDataTypeRestrictionImpl)) {
			return false;
		}
		PropertySomeDataTypeRestrictionImpl concept = (PropertySomeDataTypeRestrictionImpl) obj;
		return (isInverse == concept.isInverse) 
				&& (predicate.equals(concept.getPredicate()))
				&& (filler.equals(concept.filler));
	}

	public String toString() {
		StringBuilder bf = new StringBuilder();
		bf.append("E");
		bf.append(predicate.toString());
		if (isInverse) {
			bf.append("^-");
		}
		bf.append(".");
		bf.append(filler.toString());
		return bf.toString();
	}	
}
