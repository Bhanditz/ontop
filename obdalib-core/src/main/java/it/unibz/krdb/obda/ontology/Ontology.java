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

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

public interface Ontology extends Cloneable, Serializable {

	public void addAssertion(Axiom assertion);

	public void addAssertions(Collection<Axiom> assertion);

	public void addEntity(Predicate c);
	
	public void addConcept(Predicate c);

	public void addRole(Predicate role);

	public void addConcepts(Collection<Predicate> cd);

	public void addRoles(Collection<Predicate> rd);

	public Set<Predicate> getRoles();

	public Set<Predicate> getConcepts();
	
	public Set<Predicate> getVocabulary();

	public Set<Axiom> getAssertions();

	public boolean referencesPredicate(Predicate pred);

	public boolean referencesPredicates(Collection<Predicate> preds);

	/***
	 * This will retrun all the assertions whose right side concept description
	 * refers to the predicate 'pred'
	 */
	public Set<SubDescriptionAxiom> getByIncluding(Predicate pred);

	/***
	 * As before but it will only return assetions where the right side is an
	 * existential role concept description
	 */
	public Set<SubDescriptionAxiom> getByIncludingExistOnly(Predicate pred);

	public Set<SubDescriptionAxiom> getByIncludingNoExist(Predicate pred);

	public Set<SubDescriptionAxiom> getByIncluded(Predicate pred);

	public String getUri();

	/**
	 * This will saturate the ontology, i.e. it will make sure that all axioms
	 * implied by this ontology are asserted in the ontology and accessible
	 * through the methods of the ontology.
	 */
	public void saturate();

	public Ontology clone();

	public void addEntities(Set<Predicate> referencedEntities);

	/**
	 * @return
	 */
	Set<Assertion> getABox();
}
