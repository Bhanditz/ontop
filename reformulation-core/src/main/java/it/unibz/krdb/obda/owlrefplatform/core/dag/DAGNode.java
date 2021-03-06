/*
 * Copyright (C) 2009-2013, Free University of Bozen Bolzano
 * This source code is available under the terms of the Affero General Public
 * License v3.
 * 
 * Please see LICENSE.txt for full license terms, including the availability of
 * proprietary exceptions.
 */
package it.unibz.krdb.obda.owlrefplatform.core.dag;

import it.unibz.krdb.obda.model.Predicate;
import it.unibz.krdb.obda.ontology.DataType;
import it.unibz.krdb.obda.ontology.Description;
import it.unibz.krdb.obda.ontology.OClass;
import it.unibz.krdb.obda.ontology.Property;
import it.unibz.krdb.obda.ontology.PropertySomeRestriction;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Sergejs Pugacs
 */
public class DAGNode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3207267860221176982L;

	private final Description description;

	private SemanticIndexRange range = DAG.NULL_RANGE;
	private int index = DAG.NULL_INDEX;

	private Set<DAGNode> parents = new LinkedHashSet<DAGNode>();
	private Set<DAGNode> children = new LinkedHashSet<DAGNode>();

	private Set<DAGNode> ancestors = new LinkedHashSet<DAGNode>();
	private Set<DAGNode> descendants = new LinkedHashSet<DAGNode>();
	
	public Set<DAGNode> equivalents = new LinkedHashSet<DAGNode>();

	String string = "";

	int hashcode = 0;

	boolean hashNeedsUpdate = true;

	boolean stringNeedsUpdate = true;

	public DAGNode(Description description) {
		this.description = description;
		computeHash();
		computeString();
	}

	private void computeHash() {
		if (!hashNeedsUpdate)
			return;

		hashcode = description != null ? description.hashCode() : 0;
		hashcode = 31 * hashcode + (range != null ? range.hashCode() : 0);
		hashcode = 31 * hashcode + index;

		hashNeedsUpdate = false;
	}

	private void computeString() {
		if (!stringNeedsUpdate)
			return;
		StringBuilder bf = new StringBuilder();
		bf.append("DAGNode{");
		if (description instanceof PropertySomeRestriction) {
			bf.append("E");
			Predicate p = ((PropertySomeRestriction) description).getPredicate();
			String fragment = p.getName().toString();//.getFragment();
			if (fragment != null)
				bf.append(fragment);
			else
				bf.append(p.getName());
			if (((PropertySomeRestriction) description).isInverse())
				bf.append("^-");
		} else if (description instanceof OClass) {
			
			Predicate p = ((OClass) description).getPredicate();
			String fragment = p.getName().toString();//.getFragment();
			if (fragment != null)
				bf.append(fragment);
			else
				bf.append(p.getName());
			
		} else if (description instanceof Property) {
			
			Predicate p = ((Property) description).getPredicate();
			String fragment = p.getName().toString();//.getFragment();
			if (fragment != null)
				bf.append(fragment);
			else
				bf.append(p.getName());
			
			if (((Property) description).isInverse()) {
				bf.append("^-");
			}
			
		} else if (description instanceof DataType) {
		
			DataType datatype = (DataType) description;
			bf.append(datatype.toString());
			
		} else {
			throw new IllegalArgumentException("Invalid description for a node. Description: " + description);
		}

		// bf.append(description);
		bf.append(", range=");
		bf.append(range);
		bf.append(", index=");
		bf.append(index);
		bf.append('}');

		string = bf.toString();
		stringNeedsUpdate = false;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (other == this)
			return true;
		if (this.getClass() != other.getClass())
			return false;

		DAGNode otherNode = (DAGNode) other;
		return this.description.equals(otherNode.description) && this.range.equals(otherNode.range) && this.index == otherNode.index;
	}

	@Override
	public String toString() {
		computeString();
		return string;
	}

	@Override
	public int hashCode() {
		computeHash();
		return hashcode;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
		hashNeedsUpdate = true;
		stringNeedsUpdate = true;
	}

	public Set<DAGNode> getParents() {
		return parents;
	}

	public void setRange(SemanticIndexRange range) {
		this.range = range;
		hashNeedsUpdate = true;
		stringNeedsUpdate = true;
	}

	public SemanticIndexRange getRange() {
		return this.range;

	}

	public Set<DAGNode> getChildren() {
		return children;
	}

	public Collection<DAGNode> getEquivalents() {
		return equivalents;
	}

	public void setAncestors(Set<DAGNode> ancestors) {
		this.ancestors = ancestors;
	}
	
	public Set<DAGNode> getAncestors() {
		return ancestors;
	}
	
	public void setDescendants(Set<DAGNode> descendants) {
		this.descendants = descendants;
	}

	public Set<DAGNode> getDescendants() {
		return descendants;
	}

	public void setChildren(Set<DAGNode> children) {
		this.children = children;
		hashNeedsUpdate = true;
		stringNeedsUpdate = true;
	}

	public void setParents(Set<DAGNode> parents) {
		this.parents = parents;
		hashNeedsUpdate = true;
		stringNeedsUpdate = true;
	}

	public Description getDescription() {
		return description;
	}
}
