/*
 * Copyright (C) 2009-2013, Free University of Bozen Bolzano
 * This source code is available under the terms of the Affero General Public
 * License v3.
 * 
 * Please see LICENSE.txt for full license terms, including the availability of
 * proprietary exceptions.
 */
package it.unibz.krdb.obda.owlrefplatform.core.srcquerygeneration;

import it.unibz.krdb.obda.model.DatalogProgram;
import it.unibz.krdb.obda.model.OBDAException;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * A general interface which should be use to implement new source query
 * generation which can than be integrated in to a technique wrapper
 * 
 * @author Manfred Gerstgrasser
 * 
 */

public interface SQLQueryGenerator extends Serializable {

	/**
	 * Translates the given datalog program into a source query, which can later
	 * be evaluated by a evaluation engine.
	 * 
	 * @param query
	 *            the datalog program
	 * @return the souce query
	 * @throws Exception
	 */
	public String generateSourceQuery(DatalogProgram query, List<String> signature) throws OBDAException;
	/**
	 * Updates the current view manager with the new given parameters
	 * 
	 * @param man
	 *            the new prefix manager
	 * @param onto
	 *            the new dlliter ontology
	 * @param uris
	 *            the set of URIs of the ontologies integrated into the dlliter
	 *            ontology
	 */
	// public void update(PrefixManager man, DLLiterOntology onto, Set<URI>
	// uris);

	void setUriIds(Map<String, Integer> uriRefIds);

	// public ViewManager getViewManager();
}
