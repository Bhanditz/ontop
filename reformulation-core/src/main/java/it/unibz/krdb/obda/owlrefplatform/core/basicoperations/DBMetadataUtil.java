/*
 * Copyright (C) 2009-2013, Free University of Bozen Bolzano
 * This source code is available under the terms of the Affero General Public
 * License v3.
 * 
 * Please see LICENSE.txt for full license terms, including the availability of
 * proprietary exceptions.
 */
package it.unibz.krdb.obda.owlrefplatform.core.basicoperations;

import it.unibz.krdb.obda.model.CQIE;
import it.unibz.krdb.obda.model.Function;
import it.unibz.krdb.obda.model.Term;
import it.unibz.krdb.obda.model.OBDADataFactory;
import it.unibz.krdb.obda.model.Predicate;
import it.unibz.krdb.obda.model.impl.OBDADataFactoryImpl;
import it.unibz.krdb.sql.DBMetadata;
import it.unibz.krdb.sql.Reference;
import it.unibz.krdb.sql.TableDefinition;
import it.unibz.krdb.sql.api.Attribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.hp.hpl.jena.iri.IRIFactory;

public class DBMetadataUtil {

	private static OBDADataFactory fac = OBDADataFactoryImpl.getInstance();
	
	private static Logger log = LoggerFactory.getLogger(DBMetadataUtil.class);
	
	/*
	 * genereate CQIE rules from foreign key info of db metadata
	 * TABLE1.COL1 references TABLE2.COL2 as foreign key then 
	 * construct CQIE rule TABLE2(P1, P3, COL2, P4) :- TABLE1(COL2, T2, T3).
	 */
	public static List<CQIE> generateFKRules(DBMetadata metadata) {
		List<CQIE> rules = new ArrayList<CQIE>();
		List<TableDefinition> tableDefs = metadata.getTableList();
		for (TableDefinition def : tableDefs) {
			Map<String, List<Attribute>> foreignKeys = def.getForeignKeys();
			for (String fkName : foreignKeys.keySet()) {
				List<Attribute> fkAttributes = foreignKeys.get(fkName);
				try {
					String table1 = def.getName();
					String table2 = "";
					TableDefinition def2 = null;
					Map<Integer, Integer> positionMatch = new HashMap<Integer, Integer>();
					for (Attribute attr : fkAttributes) {
						// Get current table and column (1)
						String column1 = attr.getName();
						
						// Get referenced table and column (2)
						Reference reference = attr.getReference();
						table2 = reference.getTableReference();
						String column2 = reference.getColumnReference();				
						
						// Get table definition for referenced table
						def2 = (TableDefinition) metadata.getDefinition(table2);
						if (def2 == null) { // in case of broken FK
							throw new BrokenForeignKeyException(reference, "Missing table: " + table2);
						}
						// Get positions of referenced attribute
						int pos1 = def.getAttributePosition(column1);
						if (pos1 == -1) {
							throw new BrokenForeignKeyException(reference, "Missing column: " + column1);
						}
						int pos2 = def2.getAttributePosition(column2);
						if (pos2 == -1) {
							throw new BrokenForeignKeyException(reference, "Missing column: " + column2);
						}
						positionMatch.put(pos1, pos2);
					}
					// Construct CQIE
					Predicate p1 = fac.getPredicate(table1, def.countAttribute());
					Predicate p2 = fac.getPredicate(table2, def2.countAttribute());
					
					List<Term> terms1 = new ArrayList<Term>();
					for (int i=0; i<def.countAttribute(); i++) {
						 terms1.add(fac.getVariable("t"+(i+1)));
					}
					List<Term> terms2 = new ArrayList<Term>();
					for (int i=0; i<def2.countAttribute(); i++) {
						 terms2.add(fac.getVariable("p"+(i+1)));
					}
					// Do the swapping
					for (Integer pos1 : positionMatch.keySet()) {
						Integer pos2 = positionMatch.get(pos1);
						terms1.set(pos1, terms2.get(pos2));
					}
					Function head = fac.getFunction(p2, terms2);
					Function body1 = fac.getFunction(p1, terms1);
					List<Function> body = new ArrayList<Function>();
					body.add(body1);
					
					CQIE rule = fac.getCQIE(head, body);
					rules.add(rule);
				
				} catch (BrokenForeignKeyException e) {
					// Log the warning message
					log.warn(e.getMessage());
				}
			}
		}		
		return rules;
	}
}
