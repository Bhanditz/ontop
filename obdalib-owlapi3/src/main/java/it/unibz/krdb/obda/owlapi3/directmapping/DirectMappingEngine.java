/*
 * Copyright (C) 2009-2013, Free University of Bozen Bolzano
 * This source code is available under the terms of the Affero General Public
 * License v3.
 * 
 * Please see LICENSE.txt for full license terms, including the availability of
 * proprietary exceptions.
 */
package it.unibz.krdb.obda.owlapi3.directmapping;

import it.unibz.krdb.obda.exception.DuplicateMappingException;
import it.unibz.krdb.obda.model.CQIE;
import it.unibz.krdb.obda.model.Function;
import it.unibz.krdb.obda.model.OBDADataFactory;
import it.unibz.krdb.obda.model.OBDADataSource;
import it.unibz.krdb.obda.model.OBDAMappingAxiom;
import it.unibz.krdb.obda.model.OBDAModel;
import it.unibz.krdb.obda.model.OBDAQuery;
import it.unibz.krdb.obda.model.Predicate;
import it.unibz.krdb.obda.model.Predicate.COL_TYPE;
import it.unibz.krdb.obda.model.impl.OBDADataFactoryImpl;
import it.unibz.krdb.obda.model.impl.OBDAModelImpl;
import it.unibz.krdb.sql.DataDefinition;
import it.unibz.krdb.sql.JDBCConnectionManager;
import it.unibz.krdb.sql.TableDefinition;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

//import com.hp.hpl.jena.iri.impl.IRIFactoryImpl;
//import it.unibz.krdb.obda.model.net.IRIFactory;



/***
 * 
 * A class that provides manipulation for Direct Mapping
 * 
 * @author Victor
 *
 */


public class DirectMappingEngine {
	
	private JDBCConnectionManager conMan;
	private String baseuri;
	private int mapidx = 1;
	
	public DirectMappingEngine(String baseUri, int mapnr){
		conMan = JDBCConnectionManager.getJDBCConnectionManager();
		baseuri = baseUri;
		mapidx = mapnr + 1;
	}
	
	
	
	/*
	 * set the base URI used in the ontology
	 */
	public void setBaseURI(String prefix){
		if(prefix.endsWith("#")){
			this.baseuri = prefix.replace("#", "/");
		}else if(prefix.endsWith("/")){
			this.baseuri = prefix;
		}else this.baseuri = prefix+"/";
	}
	
	
	
	/***
	 * enrich the ontology according to the datasources specified in the OBDAModel
	 * basically from the database structure
	 * 
	 * @param ontology
	 * @param model
	 * 
	 * @return null
	 * 		   the ontology is updated
	 * 
	 * @throws Exceptions
	 */
			
	public void enrichOntology(OWLOntology ontology, OBDAModel model) throws OWLOntologyStorageException, SQLException{
		List<OBDADataSource> sourcelist = new ArrayList<OBDADataSource>();
		sourcelist = model.getSources();
		OntoExpansion oe = new OntoExpansion();
		if(model.getPrefixManager().getDefaultPrefix().endsWith("/")){
			oe.setURI(model.getPrefixManager().getDefaultPrefix());
		}else{
			oe.setURI(model.getPrefixManager().getDefaultPrefix()+"/");
		}
		
		//For each data source, enrich into the ontology
		for(int i=0;i<sourcelist.size();i++){
			oe.enrichOntology(conMan.getMetaData(sourcelist.get(i)), ontology);
		}
	}
	
	
	
	/***
	 * enrich the ontology according to mappings used in the model
	 * 
	 * @param manager
	 * @param model
	 * 
	 * @return a new ontology storing all classes and properties used in the mappings
	 * 
	 * @throws Exceptions
	 */
	public OWLOntology getOntology(OWLOntology ontology, OWLOntologyManager manager, OBDAModel model) throws OWLOntologyCreationException, OWLOntologyStorageException, SQLException{
		OWLDataFactory dataFactory = manager.getOWLDataFactory();
		
		Set<Predicate> classset = model.getDeclaredClasses();
		Set<Predicate> objectset = model.getDeclaredObjectProperties();
		Set<Predicate> dataset = model.getDeclaredDataProperties();
		
		//Add all the classes
		for(Iterator<Predicate> it = classset.iterator(); it.hasNext();){
			OWLClass newclass = dataFactory.getOWLClass(IRI.create(it.next().getName().toString()));
			OWLDeclarationAxiom declarationAxiom = dataFactory.getOWLDeclarationAxiom(newclass);
			manager.addAxiom(ontology,declarationAxiom );
		}
		
		//Add all the object properties
		for(Iterator<Predicate> it = objectset.iterator(); it.hasNext();){
			OWLObjectProperty newclass = dataFactory.getOWLObjectProperty(IRI.create(it.next().getName().toString()));
			OWLDeclarationAxiom declarationAxiom = dataFactory.getOWLDeclarationAxiom(newclass);
			manager.addAxiom(ontology,declarationAxiom );
		}
		
		//Add all the data properties
		for(Iterator<Predicate> it = dataset.iterator(); it.hasNext();){
			OWLDataProperty newclass = dataFactory.getOWLDataProperty(IRI.create(it.next().getName().toString()));
			OWLDeclarationAxiom declarationAxiom = dataFactory.getOWLDeclarationAxiom(newclass);
			manager.addAxiom(ontology,declarationAxiom );
		}
				
		return ontology;		
	}
	
	
	/***
	 * extract all the mappings from a datasource
	 * 
	 * @param source
	 * 
	 * @return a new OBDA Model containing all the extracted mappings
	 * @throws Exception 
	 */
	public OBDAModel extractMappings(OBDADataSource source) throws Exception{
		OBDAModelImpl model = new OBDAModelImpl();
		return extractMappings(model, source);
	}
	
	public OBDAModel extractMappings(OBDAModel model, OBDADataSource source) throws Exception{
		insertMapping(source, model);
		return model;
	}
	
	
	/***
	 * extract mappings from given datasource, and insert them into the given model
	 * 
	 * @param source
	 * @param model
	 * 
	 * @return null
	 * 
	 * Duplicate Exception may happen,
	 * since mapping id is generated randomly and same id may occur
	 * @throws Exception 
	 */
	public void insertMapping(OBDADataSource source, OBDAModel model) throws Exception{		
		if (baseuri == null || baseuri.isEmpty())
			this.baseuri =  model.getPrefixManager().getDefaultPrefix();
		List<TableDefinition> tables = conMan.getMetaData(source).getTableList();
		List<OBDAMappingAxiom> mappingAxioms = new ArrayList<OBDAMappingAxiom>();
		for(int i=0;i<tables.size();i++){
			TableDefinition td = tables.get(i);
			mappingAxioms.addAll(getMapping(td, source));
		}	
		model.addMappings(source.getSourceID(), mappingAxioms);
		for (URI uri : model.getMappings().keySet())
			for (OBDAMappingAxiom mapping: model.getMappings().get(uri))
			{
				OBDAQuery q = mapping.getTargetQuery();
				CQIE rule = (CQIE)q;
				for (Function f : rule.getBody())
				{
					if (f.getArity() ==1)
						model.declarePredicate(f.getFunctionSymbol());
					else if (f.getFunctionSymbol().getType(1).equals(COL_TYPE.OBJECT))
						model.declareObjectProperty(f.getFunctionSymbol());
					else
						model.declareDataProperty(f.getFunctionSymbol());
				}
			}
	}
	
	/***
	 * generate a mapping axiom from a table of some database
	 * 
	 * @param table : the datadefinition from which mappings are extraced
	 * @param source : datasource that the table may refer to, such as foreign keys
	 * 
	 *  @return a new OBDAMappingAxiom 
	 * @throws Exception 
	 */
	public List<OBDAMappingAxiom> getMapping(DataDefinition table, OBDADataSource source) throws Exception{
		OBDADataFactory dfac = OBDADataFactoryImpl.getInstance();
		DirectMappingAxiom dma = new DirectMappingAxiom(baseuri, table, conMan.getMetaData(source), dfac);
		dma.setbaseuri(this.baseuri);
		
		List<OBDAMappingAxiom> axioms = new ArrayList<OBDAMappingAxiom>();
		axioms.add(dfac.getRDBMSMappingAxiom("MAPPING-ID"+mapidx,dma.getSQL(), dma.getCQ()));
		mapidx++;
		
		Map<String, CQIE> refAxioms = dma.getRefAxioms();
		for (String refSQL : refAxioms.keySet()) {
			axioms.add(dfac.getRDBMSMappingAxiom("MAPPING-ID"+mapidx, refSQL, refAxioms.get(refSQL)));
			mapidx++;
		}
		
		return axioms;
	}


}
