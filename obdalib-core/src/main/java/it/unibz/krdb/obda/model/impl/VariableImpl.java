/*
 * Copyright (C) 2009-2013, Free University of Bozen Bolzano
 * This source code is available under the terms of the Affero General Public
 * License v3.
 * 
 * Please see LICENSE.txt for full license terms, including the availability of
 * proprietary exceptions.
 */
package it.unibz.krdb.obda.model.impl;

import it.unibz.krdb.obda.model.Variable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class VariableImpl extends AbstractLiteral implements Variable {

	private static final long serialVersionUID = 5723075311798541659L;

	private final String name;

	private final int identifier;

	protected VariableImpl(String name) {
		if (name == null) {
			throw new RuntimeException("Variable name cannot be null");
		}
		this.name = name;
		this.identifier = name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof VariableImpl)) {
			return false;
		}
		VariableImpl name2 = (VariableImpl) obj;
		return this.identifier == name2.identifier;
	}

	@Override
	public int hashCode() {
		return identifier;
	}

	@Override
	public String getName() {
		return name;
	}

	// TODO this method seems to be tied to some semantics, if we modified it,
	// things become slow and maybe wrong we must make sure that this is not the
	// case
	@Override
	public String toString() {
		return TermUtil.toString(this);
	}

	@Override
	public Variable clone() {
		return this;
	}

	@Override
	public Set<Variable> getReferencedVariables() {
		return Collections.singleton((Variable)this);
	}
	
	@Override
	public Map<Variable, Integer> getVariableCount() {
		Map<Variable,Integer> count =  new HashMap<Variable,Integer>();
		count.put(this, 1);
		return count;
	}
	

}
