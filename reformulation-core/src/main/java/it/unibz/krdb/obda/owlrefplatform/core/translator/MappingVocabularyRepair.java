/*
 * Copyright (C) 2009-2013, Free University of Bozen Bolzano
 * This source code is available under the terms of the Affero General Public
 * License v3.
 * 
 * Please see LICENSE.txt for full license terms, including the availability of
 * proprietary exceptions.
 */
package it.unibz.krdb.obda.owlrefplatform.core.translator;

import it.unibz.krdb.obda.model.CQIE;
import it.unibz.krdb.obda.model.DataTypePredicate;
import it.unibz.krdb.obda.model.Function;
import it.unibz.krdb.obda.model.Term;
import it.unibz.krdb.obda.model.OBDADataFactory;
import it.unibz.krdb.obda.model.OBDADataSource;
import it.unibz.krdb.obda.model.OBDAMappingAxiom;
import it.unibz.krdb.obda.model.OBDAModel;
import it.unibz.krdb.obda.model.OBDASQLQuery;
import it.unibz.krdb.obda.model.Predicate;
import it.unibz.krdb.obda.model.impl.OBDADataFactoryImpl;
import it.unibz.krdb.obda.model.impl.OBDAVocabulary;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * This is a hack class that helps fix and OBDA model in which the mappings
 * include predicates that have not been properly typed.
 * 
 * @author mariano
 * 
 */
public class MappingVocabularyRepair {

	private static OBDADataFactory dfac = OBDADataFactoryImpl.getInstance();

	Logger log = LoggerFactory.getLogger(MappingVocabularyRepair.class);

	public void fixOBDAModel(OBDAModel model, Set<Predicate> vocabulary) {
		log.debug("Fixing OBDA Model");
		for (OBDADataSource source : model.getSources()) {
			Collection<OBDAMappingAxiom> mappings = new LinkedList<OBDAMappingAxiom>(model.getMappings(source.getSourceID()));
			model.removeAllMappings(source.getSourceID());
			try {
				model.addMappings(source.getSourceID(), fixMappingPredicates(mappings, vocabulary));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	/***
	 * Makes sure that the mappings given are correctly typed w.r.t. the given
	 * vocabualry.
	 * 
	 * @param originalMappings
	 * @param equivalencesMap
	 * @return
	 */
	public Collection<OBDAMappingAxiom> fixMappingPredicates(Collection<OBDAMappingAxiom> originalMappings, Set<Predicate> vocabulary) {
//		log.debug("Reparing/validating {} mappings", originalMappings.size());
		HashMap<String, Predicate> urimap = new HashMap<String, Predicate>();
		for (Predicate p : vocabulary) {
			urimap.put(p.getName(), p);
		}

		Collection<OBDAMappingAxiom> result = new LinkedList<OBDAMappingAxiom>();
		for (OBDAMappingAxiom mapping : originalMappings) {
			CQIE targetQuery = (CQIE) mapping.getTargetQuery();
			List<Function> body = targetQuery.getBody();
			List<Function> newbody = new LinkedList<Function>();

			for (Function atom : body) {
				Predicate p = atom.getPredicate();
				
				Function newatom = null;
				Predicate predicate = urimap.get(p.getName());
				if (predicate == null) {
					/**
					 * ignore triple  
					 */
					//if (!p.equals(OBDAVocabulary.QUEST_TRIPLE_PRED)){
					if (!p.isTriplePredicate()){
						throw new RuntimeException("ERROR: Mapping references an unknown class/property: " + p.getName());
						
					}else{
						predicate = OBDAVocabulary.QUEST_TRIPLE_PRED;
					}
				}
				/* Fixing terms */
				LinkedList<Term> newTerms = new LinkedList<Term>();
				for (Term term : atom.getTerms()) {
					newTerms.add(fixTerm(term));
				}

				/*
				 * Fixing wrapping each variable with a URI function if the
				 * position corresponds to an URI only position
				 */
				Term t0 = newTerms.get(0);
				if (!(t0 instanceof Function)){
					newTerms.set(0, dfac.getFunction(dfac.getUriTemplatePredicate(1), t0));
				}
				if (predicate.isObjectProperty() && !(newTerms.get(1) instanceof Function)) {
					newTerms.set(1, dfac.getFunction(dfac.getUriTemplatePredicate(1), newTerms.get(1)));
				}
				newatom = dfac.getFunction(predicate, newTerms);
				newbody.add(newatom);
			}
			CQIE newTargetQuery = dfac.getCQIE(targetQuery.getHead(), newbody);
			result.add(dfac.getRDBMSMappingAxiom(mapping.getId(), ((OBDASQLQuery) mapping.getSourceQuery()).toString(), newTargetQuery));
		}
//		log.debug("Repair done. Returning {} mappings", result.size());
		return result;
	}

	/***
	 * Fixes any issues with the terms in mappings
	 * 
	 * @param term
	 * @return
	 */
	public Term fixTerm(Term term) {
		Term result = term;
		if (term instanceof Function) {
			result = fixTerm((Function) term);
		}
		return result;
	}

	/***
	 * Fix functions that represent URI templates. Currently,the only fix
	 * necessary is replacing the old-style template function with the new one,
	 * that uses a string tempalte and placeholders.
	 * 
	 * @param term
	 * @return
	 */
	public Function fixTerm(Function term) {
		Predicate predicate = term.getFunctionSymbol();
		if (predicate instanceof DataTypePredicate) {
			// no fix nexessary
			return term;
		}
		if (predicate.getName().toString().equals(OBDAVocabulary.QUEST_URI)) {
			// no fix necessary
			return term;
		}
		// We have a function that is not a built-in, hence its an old-style uri
		// template function(parm1,parm2,...)
		Predicate uriFunction = dfac.getUriTemplatePredicate(term.getArity() + 1);

		StringBuilder newTemplate = new StringBuilder();
		newTemplate.append(predicate.getName().toString());
		for (int i = 0; i < term.getArity(); i++) {
			newTemplate.append("-{}");
		}

		LinkedList<Term> newTerms = new LinkedList<Term>();
		newTerms.add(dfac.getConstantLiteral(newTemplate.toString()));
		newTerms.addAll(term.getTerms());

		return dfac.getFunction(uriFunction, newTerms);
	}
}
