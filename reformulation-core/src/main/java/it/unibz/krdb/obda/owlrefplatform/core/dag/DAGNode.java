package it.unibz.krdb.obda.owlrefplatform.core.dag;

import it.unibz.krdb.obda.model.Predicate;
import it.unibz.krdb.obda.owlrefplatform.core.ontology.Description;
import it.unibz.krdb.obda.owlrefplatform.core.ontology.OClass;
import it.unibz.krdb.obda.owlrefplatform.core.ontology.Property;
import it.unibz.krdb.obda.owlrefplatform.core.ontology.PropertySomeRestriction;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Sergejs Pugacs
 */
public class DAGNode {

	private final Description description;

	private SemanticIndexRange range = DAG.NULL_RANGE;
	private int index = DAG.NULL_INDEX;

	private Set<DAGNode> parents = new LinkedHashSet<DAGNode>();
	private Set<DAGNode> children = new LinkedHashSet<DAGNode>();

	private Set<DAGNode> descendans = new LinkedHashSet<DAGNode>();

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
			String fragment = p.getName().getFragment();
			if (fragment != null)
				bf.append(fragment);
			else
				bf.append(p.getName());
			if (((PropertySomeRestriction) description).isInverse())
				bf.append("^-");
		} else if (description instanceof OClass) {
			
			Predicate p = ((OClass) description).getPredicate();
			String fragment = p.getName().getFragment();
			if (fragment != null)
				bf.append(fragment);
			else
				bf.append(p.getName());
			
		} else if (description instanceof Property) {
			
			Predicate p = ((Property) description).getPredicate();
			String fragment = p.getName().getFragment();
			if (fragment != null)
				bf.append(fragment);
			else
				bf.append(p.getName());
			
			if (((Property) description).isInverse()) {
				bf.append("^-");
			}
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

	public void setDescendants(Set<DAGNode> descendans) {
		this.descendans = descendans;
	}

	public Set<DAGNode> getDescendants() {
		return descendans;
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