/*
 * Copyright (C) 2009-2013, Free University of Bozen Bolzano This source code is
 * available under the terms of the Affero General Public License v3.
 * 
 * Please see LICENSE.txt for full license terms, including the availability of
 * proprietary exceptions.
 */
package it.unibz.krdb.obda.owlapi3;

import it.unibz.krdb.obda.model.Predicate;
import it.unibz.krdb.obda.ontology.Assertion;
import it.unibz.krdb.obda.ontology.Description;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLIndividualAxiom;
import org.semanticweb.owlapi.model.OWLOntology;

/***
 * A read only iterator that will translate OWLAPI2 data assertions into ABox
 * assertions in ontop's API. This is used in our Statement classes (e.g.,
 * {@link QuestOWLStatement} and SemanticIndexManager to iterate over the input
 * and then insert it into the semantic index database.
 * 
 * @author Mariano Rodriguez Muro
 * 
 */
public class OWLAPI3ABoxIterator implements Iterator<Assertion> {

	Iterator<OWLAxiom> owlaxiomiterator = null;
	Iterator<OWLOntology> ontologies = null;

	OWLIndividualAxiom next = null;

	OWLAPI3Translator translator = new OWLAPI3Translator();
	private Map<Predicate, Description> equivalenceMap;

	public OWLAPI3ABoxIterator(Collection<OWLOntology> ontologies) {
		this(ontologies, new HashMap<Predicate, Description>());
	}

	public OWLAPI3ABoxIterator(Collection<OWLOntology> ontologies, Map<Predicate, Description> equivalenceMap) {
		this.equivalenceMap = equivalenceMap;
		if (ontologies.size() > 0) {
			this.ontologies = ontologies.iterator();
			this.owlaxiomiterator = this.ontologies.next().getAxioms().iterator();
		}
	}

	public OWLAPI3ABoxIterator(OWLOntology ontology) {
		this(ontology, new HashMap<Predicate, Description>());
	}

	public OWLAPI3ABoxIterator(OWLOntology ontology, Map<Predicate, Description> equivalenceMap) {
		this.ontologies = Collections.singleton(ontology).iterator();
		this.owlaxiomiterator = ontologies.next().getAxioms().iterator();
	}

	public OWLAPI3ABoxIterator(Iterable<OWLAxiom> axioms) {
		this(axioms, new HashMap<Predicate, Description>());
	}

	public OWLAPI3ABoxIterator(Iterable<OWLAxiom> axioms, Map<Predicate, Description> equivalenceMap) {
		this.owlaxiomiterator = axioms.iterator();
	}

	public OWLAPI3ABoxIterator(Iterator<OWLAxiom> axioms) {
		this(axioms, new HashMap<Predicate, Description>());
	}

	public OWLAPI3ABoxIterator(Iterator<OWLAxiom> axioms, Map<Predicate, Description> equivalenceMap) {
		this.owlaxiomiterator = axioms;
	}

	@Override
	public boolean hasNext() {
		while (true) {
			try {
				boolean hasnext = hasNextInCurrentIterator();
				if (hasnext) {
					return true;
				} else {
					try {
						switchToNextIterator();
					} catch (NoSuchElementException e) {
						return false;
					}
				}
			} catch (NoSuchElementException e) {
				try {
					switchToNextIterator();
				} catch (NoSuchElementException e2) {
					return false;
				}

			}
		}
	}

	@Override
	public Assertion next() {
		while (true) {
			try {
				OWLIndividualAxiom next = nextInCurrentIterator();

				Assertion ass = translator.translate(next, equivalenceMap);
				if (ass == null)
					throw new NoSuchElementException();
				else
					return ass;
			} catch (NoSuchElementException e) {
				switchToNextIterator();
			}
		}
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("This iterator is read-only");

	}

	/***
	 * Tries to advance to the next ontology in the iterator.
	 * 
	 * @throws NoSuchElementException
	 */
	private void switchToNextIterator() throws NoSuchElementException {
		if (ontologies == null) {
			throw new NoSuchElementException();
		}

		OWLOntology nextOntology = ontologies.next();
		owlaxiomiterator = nextOntology.getAxioms().iterator();
	}

	/***
	 * Gives the next individual axiom in the current iterator. If none is found
	 * it will throw no such element execption.
	 * 
	 * @return
	 * @throws NoSuchElementException
	 */
	private OWLIndividualAxiom nextInCurrentIterator() throws NoSuchElementException {

		if (owlaxiomiterator == null)
			throw new NoSuchElementException();

		OWLAxiom currentABoxAssertion = null;

		if (next != null) {
			OWLIndividualAxiom out = next;
			next = null;
			return out;
		}

		currentABoxAssertion = owlaxiomiterator.next();

		while (true) {
			// System.out.println(currentABoxAssertion);
			if ((currentABoxAssertion instanceof OWLIndividualAxiom)
					&& (translator.translate((OWLIndividualAxiom) currentABoxAssertion) != null)) {
				return (OWLIndividualAxiom) currentABoxAssertion;
			}
			currentABoxAssertion = owlaxiomiterator.next();
		}
	}

	private boolean hasNextInCurrentIterator() {
		if (owlaxiomiterator == null)
			return false;

		OWLAxiom currentABoxAssertion = null;

		try {
			currentABoxAssertion = owlaxiomiterator.next();

		} catch (NoSuchElementException e) {
			return false;
		}

		while (true) {
			if ((currentABoxAssertion instanceof OWLIndividualAxiom)
					&& (translator.translate((OWLIndividualAxiom) currentABoxAssertion) != null)) {
				next = (OWLIndividualAxiom) currentABoxAssertion;
				return true;
			}
			try {
				currentABoxAssertion = owlaxiomiterator.next();
			} catch (NoSuchElementException e) {
				return false;
			}

		}
	}

}
