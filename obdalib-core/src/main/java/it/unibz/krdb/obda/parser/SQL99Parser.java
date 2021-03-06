/*
 * Copyright (C) 2009-2013, Free University of Bozen Bolzano
 * This source code is available under the terms of the Affero General Public
 * License v3.
 * 
 * Please see LICENSE.txt for full license terms, including the availability of
 * proprietary exceptions.
 */
// $ANTLR 3.5 /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g 2013-05-16 15:28:08

package it.unibz.krdb.obda.parser;

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.EmptyStackException;

import java.lang.Number;

import it.unibz.krdb.sql.DBMetadata;

import it.unibz.krdb.sql.api.IValueExpression;
import it.unibz.krdb.sql.api.IPredicate;

import it.unibz.krdb.sql.api.QueryTree;
import it.unibz.krdb.sql.api.Projection;
import it.unibz.krdb.sql.api.Selection;
import it.unibz.krdb.sql.api.Aggregation;

import it.unibz.krdb.sql.api.Attribute;
import it.unibz.krdb.sql.api.JoinOperator;
import it.unibz.krdb.sql.api.SetUnion;
import it.unibz.krdb.sql.api.Relation;
import it.unibz.krdb.sql.api.RelationalAlgebra;

import it.unibz.krdb.sql.api.TableExpression;
import it.unibz.krdb.sql.api.AbstractValueExpression;
import it.unibz.krdb.sql.api.NumericValueExpression;
import it.unibz.krdb.sql.api.StringValueExpression;
import it.unibz.krdb.sql.api.ReferenceValueExpression;
import it.unibz.krdb.sql.api.CollectionValueExpression;
import it.unibz.krdb.sql.api.BooleanValueExpression;

import it.unibz.krdb.sql.api.TablePrimary;
import it.unibz.krdb.sql.api.DerivedColumn;
import it.unibz.krdb.sql.api.GroupingElement;
import it.unibz.krdb.sql.api.ComparisonPredicate;
import it.unibz.krdb.sql.api.NullPredicate;
import it.unibz.krdb.sql.api.AndOperator;
import it.unibz.krdb.sql.api.OrOperator;
import it.unibz.krdb.sql.api.LeftParenthesis;
import it.unibz.krdb.sql.api.RightParenthesis;
import it.unibz.krdb.sql.api.ColumnReference;

import it.unibz.krdb.sql.api.Literal;
import it.unibz.krdb.sql.api.StringLiteral;
import it.unibz.krdb.sql.api.BooleanLiteral;
import it.unibz.krdb.sql.api.NumericLiteral;
import it.unibz.krdb.sql.api.IntegerLiteral;
import it.unibz.krdb.sql.api.DecimalLiteral;
import it.unibz.krdb.sql.api.DateTimeLiteral;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class SQL99Parser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ALL", "ALPHA", "ALPHANUM", "AMPERSAND", 
		"AND", "ANY", "APOSTROPHE", "AS", "ASTERISK", "AT", "AVG", "BACKSLASH", 
		"BY", "CARET", "CHAR", "COLON", "COMMA", "CONCATENATION", "COUNT", "DATETIME", 
		"DECIMAL", "DECIMAL_NEGATIVE", "DECIMAL_POSITIVE", "DIGIT", "DISTINCT", 
		"DOLLAR", "DOUBLE_SLASH", "ECHAR", "EQUALS", "EVERY", "EXCLAMATION", "FALSE", 
		"FROM", "FULL", "GREATER", "GROUP", "HASH", "IN", "INNER", "INTEGER", 
		"INTEGER_NEGATIVE", "INTEGER_POSITIVE", "IS", "JOIN", "LEFT", "LESS", 
		"LPAREN", "LSQ_BRACKET", "MAX", "MIN", "MINUS", "NOT", "NULL", "ON", "OR", 
		"ORDER", "OUTER", "PERCENT", "PERIOD", "PLUS", "QUESTION", "QUOTE_DOUBLE", 
		"QUOTE_SINGLE", "RIGHT", "RPAREN", "RSQ_BRACKET", "SELECT", "SEMI", "SOLIDUS", 
		"SOME", "STRING_WITH_QUOTE", "STRING_WITH_QUOTE_DOUBLE", "SUM", "TILDE", 
		"TRUE", "UNDERSCORE", "UNION", "USING", "VARNAME", "WHERE", "WS"
	};
	public static final int EOF=-1;
	public static final int ALL=4;
	public static final int ALPHA=5;
	public static final int ALPHANUM=6;
	public static final int AMPERSAND=7;
	public static final int AND=8;
	public static final int ANY=9;
	public static final int APOSTROPHE=10;
	public static final int AS=11;
	public static final int ASTERISK=12;
	public static final int AT=13;
	public static final int AVG=14;
	public static final int BACKSLASH=15;
	public static final int BY=16;
	public static final int CARET=17;
	public static final int CHAR=18;
	public static final int COLON=19;
	public static final int COMMA=20;
	public static final int CONCATENATION=21;
	public static final int COUNT=22;
	public static final int DATETIME=23;
	public static final int DECIMAL=24;
	public static final int DECIMAL_NEGATIVE=25;
	public static final int DECIMAL_POSITIVE=26;
	public static final int DIGIT=27;
	public static final int DISTINCT=28;
	public static final int DOLLAR=29;
	public static final int DOUBLE_SLASH=30;
	public static final int ECHAR=31;
	public static final int EQUALS=32;
	public static final int EVERY=33;
	public static final int EXCLAMATION=34;
	public static final int FALSE=35;
	public static final int FROM=36;
	public static final int FULL=37;
	public static final int GREATER=38;
	public static final int GROUP=39;
	public static final int HASH=40;
	public static final int IN=41;
	public static final int INNER=42;
	public static final int INTEGER=43;
	public static final int INTEGER_NEGATIVE=44;
	public static final int INTEGER_POSITIVE=45;
	public static final int IS=46;
	public static final int JOIN=47;
	public static final int LEFT=48;
	public static final int LESS=49;
	public static final int LPAREN=50;
	public static final int LSQ_BRACKET=51;
	public static final int MAX=52;
	public static final int MIN=53;
	public static final int MINUS=54;
	public static final int NOT=55;
	public static final int NULL=56;
	public static final int ON=57;
	public static final int OR=58;
	public static final int ORDER=59;
	public static final int OUTER=60;
	public static final int PERCENT=61;
	public static final int PERIOD=62;
	public static final int PLUS=63;
	public static final int QUESTION=64;
	public static final int QUOTE_DOUBLE=65;
	public static final int QUOTE_SINGLE=66;
	public static final int RIGHT=67;
	public static final int RPAREN=68;
	public static final int RSQ_BRACKET=69;
	public static final int SELECT=70;
	public static final int SEMI=71;
	public static final int SOLIDUS=72;
	public static final int SOME=73;
	public static final int STRING_WITH_QUOTE=74;
	public static final int STRING_WITH_QUOTE_DOUBLE=75;
	public static final int SUM=76;
	public static final int TILDE=77;
	public static final int TRUE=78;
	public static final int UNDERSCORE=79;
	public static final int UNION=80;
	public static final int USING=81;
	public static final int VARNAME=82;
	public static final int WHERE=83;
	public static final int WS=84;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public SQL99Parser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public SQL99Parser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return SQL99Parser.tokenNames; }
	@Override public String getGrammarFileName() { return "/Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g"; }



	    String error = "";
	    
	    public String getError() {
	    	return error;
	    }

	    //protected void mismatch(IntStream input, int ttype, BitSet follow)
	    //throws RecognitionException
	    //{
	    //throw new MismatchedTokenException(ttype, input);
	    //}

	    //public Object recoverFromMismatchedSet(IntStream input, RecognitionException e, BitSet follow)
	    //throws RecognitionException
	   // {
	    //throw e;
	    //}

	  //  @Override
	  //  public void recover(IntStream input, RecognitionException re) {
	  //  	throw new RuntimeException(error);
	  //  }

	    
	    @Override
	    public void displayRecognitionError(String[] tokenNames,
	                                        RecognitionException e) {
	        String hdr = getErrorHeader(e);
	        String msg = getErrorMessage(e, tokenNames);
	        emitErrorMessage("Syntax error: " + msg + " Location: " + hdr);
	    }
	    @Override
	    public void emitErrorMessage(	String 	msg	 ) 	{
	    	error = msg;
	    	    }
	    
	  //  @Override
	 //   public Object recoverFromMismatchedToken	(	IntStream 	input,
	 //   		int 	ttype,
	 //   		BitSet 	follow	 
	 //   		)			 throws RecognitionException {
	 //   	throw new RecognitionException(input);
	 //   }
	    

	/** Global stack for keeping the projection column list */
	private Stack<Projection> projectionStack = new Stack<Projection>();

	/** Global stack for keeping the select all projection */
	private Stack<Boolean> AsteriskStack = new Stack<Boolean>();

	/** Global stack for keeping the relations */
	private Stack<RelationalAlgebra> relationStack = new Stack<RelationalAlgebra>();

	/** Temporary cache for keeping the numeric value expression */
	private NumericValueExpression numericExp;

	/** Temporary cache for keeping the string value expression */
	private StringValueExpression stringExp;

	/** Temporary cache for keeping the reference value expression */
	private ReferenceValueExpression referenceExp;

	/** Temporary cache for keeping the collection value expression */
	private CollectionValueExpression collectionExp;

	/** Temporary cache for keeping the boolean value expression */
	private BooleanValueExpression booleanExp;

	/** The root of the query tree */
	private QueryTree queryTree;

	/**
	 * Retrieves the query tree object. The tree represents
	 * the data structure of the SQL statement.
	 *
	 * @return Returns a query tree.
	 */
	public QueryTree getQueryTree() {
	  return queryTree;
	}

	/**
	 * A helper method to construct the projection. A projection
	 * object holds the information about the table columns in
	 * the SELECT keyword.
	 */
	private Projection createProjection(ArrayList<DerivedColumn> columnList) {
	  Projection prj = new Projection();
	  prj.addAll(columnList);
	  return prj;
	}

	/**
	 * A helper method to construct the selection. A selection object
	 * holds the information about the comparison predicate (e.g., A = B)
	 * in the WHERE statment.
	 */
	private Selection createSelection(BooleanValueExpression booleanExp) {
	  if (booleanExp == null) {
	    return null;
	  }
	  Selection slc = new Selection();
	  
	  try {
		  Queue<Object> specification = booleanExp.getSpecification();
		  slc.copy(specification);
		}
	  catch(Exception e) {
	    // Does nothing.
	  }
	  return slc;
	}

	/**
	 * A helper method to constuct the aggregation. An aggregation object
	 * holds the information about the table attributes that are used
	 * to group the data records. They appear in the GROUP BY statement.
	 */
	private Aggregation createAggregation(ArrayList<GroupingElement> groupingList) {
	  if (groupingList == null) {
	    return null;
	  }
	  Aggregation agg = new Aggregation();
	  agg.addAll(groupingList);
	  return agg;
	}

	/**
	 * Another helper method to construct the query tree. This method
	 * constructs the sub-tree taken the information from a query 
	 * specification.
	 *
	 * @param relation
	 *           The root of this sub-tree.
	 * @return Returns the query sub-tree.
	 */
	private QueryTree constructQueryTree(RelationalAlgebra relation) {

	  QueryTree parent = new QueryTree(relation);
	  
	  int flag = 1;
	  while (!relationStack.isEmpty()) {
	    relation = relationStack.pop();
	    QueryTree node = new QueryTree(relation);
	        
	    if ((flag % 2) == 1) {  // right child
	      parent.attachRight(node);
	    }
	    else {  // left child
	      parent.attachLeft(node);
	      parent = node;
	    }
	    flag++;
	  }
	  return parent.root();
	}



	// $ANTLR start "parse"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:277:1: parse returns [QueryTree value] : query EOF ;
	public final QueryTree parse() throws RecognitionException {
		QueryTree value = null;


		QueryTree query1 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:278:3: ( query EOF )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:278:5: query EOF
			{
			pushFollow(FOLLOW_query_in_parse51);
			query1=query();
			state._fsp--;

			match(input,EOF,FOLLOW_EOF_in_parse53); 

			      value = query1;
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "parse"



	// $ANTLR start "query"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:283:1: query returns [QueryTree value] : query_specification ;
	public final QueryTree query() throws RecognitionException {
		QueryTree value = null;


		QueryTree query_specification2 =null;


		int quantifier = 0;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:287:3: ( query_specification )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:287:5: query_specification
			{
			pushFollow(FOLLOW_query_specification_in_query79);
			query_specification2=query_specification();
			state._fsp--;

			 
			      queryTree = query_specification2; 
			      value = queryTree;
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "query"



	// $ANTLR start "query_specification"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:308:1: query_specification returns [QueryTree value] : SELECT ( set_quantifier )? select_list table_expression ;
	public final QueryTree query_specification() throws RecognitionException {
		QueryTree value = null;


		TableExpression table_expression3 =null;
		ArrayList<DerivedColumn> select_list4 =null;
		int set_quantifier5 =0;


		int quantifier = 0;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:312:3: ( SELECT ( set_quantifier )? select_list table_expression )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:312:5: SELECT ( set_quantifier )? select_list table_expression
			{
			match(input,SELECT,FOLLOW_SELECT_in_query_specification120); 
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:312:12: ( set_quantifier )?
			int alt1=2;
			int LA1_0 = input.LA(1);
			if ( (LA1_0==ALL||LA1_0==DISTINCT) ) {
				alt1=1;
			}
			switch (alt1) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:312:12: set_quantifier
					{
					pushFollow(FOLLOW_set_quantifier_in_query_specification122);
					set_quantifier5=set_quantifier();
					state._fsp--;

					}
					break;

			}

			pushFollow(FOLLOW_select_list_in_query_specification125);
			select_list4=select_list();
			state._fsp--;

			pushFollow(FOLLOW_table_expression_in_query_specification127);
			table_expression3=table_expression();
			state._fsp--;


			  
			      TableExpression te = table_expression3;
			      
			      // Construct the projection
			      ArrayList<TablePrimary> tableList = te.getFromClause();
			      ArrayList<DerivedColumn> columnList = select_list4;
			      Projection prj = createProjection(columnList);
			            
			      quantifier = set_quantifier5;
			      prj.setType(quantifier);
			      
			      // Construct the selection
			      BooleanValueExpression booleanExp = te.getWhereClause();
			      Selection slc = createSelection(booleanExp);
			      
			      // Construct the aggregation
			      ArrayList<GroupingElement> groupingList = te.getGroupByClause();
			      Aggregation agg = createAggregation(groupingList);
			      
			      // Construct the query tree
			      try {
				      RelationalAlgebra root = relationStack.pop();
				      root.setProjection(prj);
				      if (slc != null) {
				        root.setSelection(slc);
				      }
				      if (agg != null) {
				        root.setAggregation(agg);
				      }
				      value = constructQueryTree(root);
			      } 
			      catch(EmptyStackException e) {
			        // Does nothing
			      } 
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "query_specification"



	// $ANTLR start "set_quantifier"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:356:1: set_quantifier returns [int value] : ( ALL | DISTINCT );
	public final int set_quantifier() throws RecognitionException {
		int value = 0;


		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:357:3: ( ALL | DISTINCT )
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==ALL) ) {
				alt2=1;
			}
			else if ( (LA2_0==DISTINCT) ) {
				alt2=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}

			switch (alt2) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:357:5: ALL
					{
					match(input,ALL,FOLLOW_ALL_in_set_quantifier148); 
					 value = 1; 
					}
					break;
				case 2 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:358:5: DISTINCT
					{
					match(input,DISTINCT,FOLLOW_DISTINCT_in_set_quantifier156); 
					 value = 2; 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "set_quantifier"



	// $ANTLR start "select_list"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:361:1: select_list returns [ArrayList<DerivedColumn> value] : a= select_sublist ( COMMA b= select_sublist )* ;
	public final ArrayList<DerivedColumn> select_list() throws RecognitionException {
		ArrayList<DerivedColumn> value = null;


		DerivedColumn a =null;
		DerivedColumn b =null;


		  value = new ArrayList<DerivedColumn>();

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:365:3: (a= select_sublist ( COMMA b= select_sublist )* )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:365:5: a= select_sublist ( COMMA b= select_sublist )*
			{
			pushFollow(FOLLOW_select_sublist_in_select_list184);
			a=select_sublist();
			state._fsp--;

			 value.add(a); 
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:365:48: ( COMMA b= select_sublist )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( (LA3_0==COMMA) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:365:49: COMMA b= select_sublist
					{
					match(input,COMMA,FOLLOW_COMMA_in_select_list189); 
					pushFollow(FOLLOW_select_sublist_in_select_list193);
					b=select_sublist();
					state._fsp--;

					 value.add(b); 
					}
					break;

				default :
					break loop3;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "select_list"



	// $ANTLR start "select_sublist"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:368:1: select_sublist returns [DerivedColumn value] : derived_column ;
	public final DerivedColumn select_sublist() throws RecognitionException {
		DerivedColumn value = null;


		DerivedColumn derived_column6 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:369:3: ( derived_column )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:369:5: derived_column
			{
			pushFollow(FOLLOW_derived_column_in_select_sublist216);
			derived_column6=derived_column();
			state._fsp--;

			 value = derived_column6; 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "select_sublist"



	// $ANTLR start "qualified_asterisk"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:373:1: qualified_asterisk : table_identifier PERIOD ASTERISK ;
	public final void qualified_asterisk() throws RecognitionException {
		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:374:3: ( table_identifier PERIOD ASTERISK )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:374:5: table_identifier PERIOD ASTERISK
			{
			pushFollow(FOLLOW_table_identifier_in_qualified_asterisk234);
			table_identifier();
			state._fsp--;

			match(input,PERIOD,FOLLOW_PERIOD_in_qualified_asterisk236); 
			match(input,ASTERISK,FOLLOW_ASTERISK_in_qualified_asterisk238); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "qualified_asterisk"



	// $ANTLR start "derived_column"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:377:1: derived_column returns [DerivedColumn value] : value_expression ( ( AS )? alias_name )? ;
	public final DerivedColumn derived_column() throws RecognitionException {
		DerivedColumn value = null;


		AbstractValueExpression value_expression7 =null;
		String alias_name8 =null;


		  value = new DerivedColumn();

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:381:3: ( value_expression ( ( AS )? alias_name )? )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:381:5: value_expression ( ( AS )? alias_name )?
			{
			pushFollow(FOLLOW_value_expression_in_derived_column262);
			value_expression7=value_expression();
			state._fsp--;

			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:381:22: ( ( AS )? alias_name )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0==AS||LA5_0==STRING_WITH_QUOTE_DOUBLE||LA5_0==VARNAME) ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:381:23: ( AS )? alias_name
					{
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:381:23: ( AS )?
					int alt4=2;
					int LA4_0 = input.LA(1);
					if ( (LA4_0==AS) ) {
						alt4=1;
					}
					switch (alt4) {
						case 1 :
							// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:381:23: AS
							{
							match(input,AS,FOLLOW_AS_in_derived_column265); 
							}
							break;

					}

					pushFollow(FOLLOW_alias_name_in_derived_column268);
					alias_name8=alias_name();
					state._fsp--;

					}
					break;

			}


			      value.setValueExpression(value_expression7);
			      String alias = alias_name8;
			      if (alias != null) {
			        value.setAlias(alias_name8);
			      }
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "derived_column"



	// $ANTLR start "value_expression"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:390:1: value_expression returns [AbstractValueExpression value] : reference_value_expression ;
	public final AbstractValueExpression value_expression() throws RecognitionException {
		AbstractValueExpression value = null;


		ReferenceValueExpression reference_value_expression9 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:391:3: ( reference_value_expression )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:391:5: reference_value_expression
			{
			pushFollow(FOLLOW_reference_value_expression_in_value_expression292);
			reference_value_expression9=reference_value_expression();
			state._fsp--;

			 value = reference_value_expression9; 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "value_expression"



	// $ANTLR start "numeric_value_expression"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:397:1: numeric_value_expression returns [NumericValueExpression value] : LPAREN numeric_operation RPAREN ;
	public final NumericValueExpression numeric_value_expression() throws RecognitionException {
		NumericValueExpression value = null;



		  numericExp = new NumericValueExpression();

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:401:3: ( LPAREN numeric_operation RPAREN )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:401:5: LPAREN numeric_operation RPAREN
			{
			match(input,LPAREN,FOLLOW_LPAREN_in_numeric_value_expression319); 
			pushFollow(FOLLOW_numeric_operation_in_numeric_value_expression321);
			numeric_operation();
			state._fsp--;

			match(input,RPAREN,FOLLOW_RPAREN_in_numeric_value_expression323); 

			      value = numericExp;
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "numeric_value_expression"



	// $ANTLR start "numeric_operation"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:406:1: numeric_operation : term ( (t= PLUS |t= MINUS ) term )* ;
	public final void numeric_operation() throws RecognitionException {
		Token t=null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:407:3: ( term ( (t= PLUS |t= MINUS ) term )* )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:407:5: term ( (t= PLUS |t= MINUS ) term )*
			{
			pushFollow(FOLLOW_term_in_numeric_operation338);
			term();
			state._fsp--;

			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:408:5: ( (t= PLUS |t= MINUS ) term )*
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( (LA7_0==MINUS||LA7_0==PLUS) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:409:7: (t= PLUS |t= MINUS ) term
					{
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:409:7: (t= PLUS |t= MINUS )
					int alt6=2;
					int LA6_0 = input.LA(1);
					if ( (LA6_0==PLUS) ) {
						alt6=1;
					}
					else if ( (LA6_0==MINUS) ) {
						alt6=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 6, 0, input);
						throw nvae;
					}

					switch (alt6) {
						case 1 :
							// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:409:8: t= PLUS
							{
							t=(Token)match(input,PLUS,FOLLOW_PLUS_in_numeric_operation356); 
							}
							break;
						case 2 :
							// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:409:15: t= MINUS
							{
							t=(Token)match(input,MINUS,FOLLOW_MINUS_in_numeric_operation360); 
							}
							break;

					}

					 numericExp.putSpecification((t!=null?t.getText():null)); 
					pushFollow(FOLLOW_term_in_numeric_operation373);
					term();
					state._fsp--;

					}
					break;

				default :
					break loop7;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "numeric_operation"



	// $ANTLR start "term"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:414:1: term : a= factor ( (t= ASTERISK |t= SOLIDUS ) b= factor )* ;
	public final void term() throws RecognitionException {
		Token t=null;
		Object a =null;
		Object b =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:415:3: (a= factor ( (t= ASTERISK |t= SOLIDUS ) b= factor )* )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:415:5: a= factor ( (t= ASTERISK |t= SOLIDUS ) b= factor )*
			{
			pushFollow(FOLLOW_factor_in_term395);
			a=factor();
			state._fsp--;

			 numericExp.putSpecification(a); 
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:416:5: ( (t= ASTERISK |t= SOLIDUS ) b= factor )*
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( (LA9_0==ASTERISK||LA9_0==SOLIDUS) ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:417:7: (t= ASTERISK |t= SOLIDUS ) b= factor
					{
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:417:7: (t= ASTERISK |t= SOLIDUS )
					int alt8=2;
					int LA8_0 = input.LA(1);
					if ( (LA8_0==ASTERISK) ) {
						alt8=1;
					}
					else if ( (LA8_0==SOLIDUS) ) {
						alt8=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 8, 0, input);
						throw nvae;
					}

					switch (alt8) {
						case 1 :
							// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:417:8: t= ASTERISK
							{
							t=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_term415); 
							}
							break;
						case 2 :
							// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:417:19: t= SOLIDUS
							{
							t=(Token)match(input,SOLIDUS,FOLLOW_SOLIDUS_in_term419); 
							}
							break;

					}

					 numericExp.putSpecification((t!=null?t.getText():null)); 
					pushFollow(FOLLOW_factor_in_term433);
					b=factor();
					state._fsp--;

					 numericExp.putSpecification(b); 
					}
					break;

				default :
					break loop9;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "term"



	// $ANTLR start "factor"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:422:1: factor returns [Object value] : ( column_reference | numeric_literal );
	public final Object factor() throws RecognitionException {
		Object value = null;


		ColumnReference column_reference10 =null;
		NumericLiteral numeric_literal11 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:423:3: ( column_reference | numeric_literal )
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==STRING_WITH_QUOTE_DOUBLE||LA10_0==VARNAME) ) {
				alt10=1;
			}
			else if ( ((LA10_0 >= DECIMAL && LA10_0 <= DECIMAL_POSITIVE)||(LA10_0 >= INTEGER && LA10_0 <= INTEGER_POSITIVE)) ) {
				alt10=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}

			switch (alt10) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:423:5: column_reference
					{
					pushFollow(FOLLOW_column_reference_in_factor461);
					column_reference10=column_reference();
					state._fsp--;

					 value = column_reference10; 
					}
					break;
				case 2 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:424:5: numeric_literal
					{
					pushFollow(FOLLOW_numeric_literal_in_factor469);
					numeric_literal11=numeric_literal();
					state._fsp--;

					 value = numeric_literal11; 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "factor"



	// $ANTLR start "sign"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:427:1: sign : ( PLUS | MINUS );
	public final void sign() throws RecognitionException {
		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:428:3: ( PLUS | MINUS )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:
			{
			if ( input.LA(1)==MINUS||input.LA(1)==PLUS ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "sign"



	// $ANTLR start "string_value_expression"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:432:1: string_value_expression returns [StringValueExpression value] : LPAREN concatenation RPAREN ;
	public final StringValueExpression string_value_expression() throws RecognitionException {
		StringValueExpression value = null;



		  stringExp = new StringValueExpression();

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:436:3: ( LPAREN concatenation RPAREN )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:436:5: LPAREN concatenation RPAREN
			{
			match(input,LPAREN,FOLLOW_LPAREN_in_string_value_expression512); 
			pushFollow(FOLLOW_concatenation_in_string_value_expression514);
			concatenation();
			state._fsp--;

			match(input,RPAREN,FOLLOW_RPAREN_in_string_value_expression516); 

			      value = stringExp;
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "string_value_expression"



	// $ANTLR start "concatenation"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:441:1: concatenation : a= character_factor ( CONCATENATION b= character_factor )+ ;
	public final void concatenation() throws RecognitionException {
		Object a =null;
		Object b =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:442:3: (a= character_factor ( CONCATENATION b= character_factor )+ )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:442:5: a= character_factor ( CONCATENATION b= character_factor )+
			{
			pushFollow(FOLLOW_character_factor_in_concatenation535);
			a=character_factor();
			state._fsp--;

			 stringExp.putSpecification(a); 
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:442:66: ( CONCATENATION b= character_factor )+
			int cnt11=0;
			loop11:
			while (true) {
				int alt11=2;
				int LA11_0 = input.LA(1);
				if ( (LA11_0==CONCATENATION) ) {
					alt11=1;
				}

				switch (alt11) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:443:7: CONCATENATION b= character_factor
					{
					match(input,CONCATENATION,FOLLOW_CONCATENATION_in_concatenation547); 
					 stringExp.putSpecification(StringValueExpression.CONCAT_OP); 
					pushFollow(FOLLOW_character_factor_in_concatenation560);
					b=character_factor();
					state._fsp--;

					 stringExp.putSpecification(b); 
					}
					break;

				default :
					if ( cnt11 >= 1 ) break loop11;
					EarlyExitException eee = new EarlyExitException(11, input);
					throw eee;
				}
				cnt11++;
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "concatenation"



	// $ANTLR start "character_factor"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:447:1: character_factor returns [Object value] : ( column_reference | general_literal );
	public final Object character_factor() throws RecognitionException {
		Object value = null;


		ColumnReference column_reference12 =null;
		Literal general_literal13 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:448:3: ( column_reference | general_literal )
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0==STRING_WITH_QUOTE_DOUBLE||LA12_0==VARNAME) ) {
				alt12=1;
			}
			else if ( (LA12_0==FALSE||LA12_0==STRING_WITH_QUOTE||LA12_0==TRUE) ) {
				alt12=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 12, 0, input);
				throw nvae;
			}

			switch (alt12) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:448:5: column_reference
					{
					pushFollow(FOLLOW_column_reference_in_character_factor581);
					column_reference12=column_reference();
					state._fsp--;

					 value = column_reference12; 
					}
					break;
				case 2 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:449:5: general_literal
					{
					pushFollow(FOLLOW_general_literal_in_character_factor589);
					general_literal13=general_literal();
					state._fsp--;

					 value = general_literal13; 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "character_factor"



	// $ANTLR start "reference_value_expression"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:452:1: reference_value_expression returns [ReferenceValueExpression value] : column_reference ;
	public final ReferenceValueExpression reference_value_expression() throws RecognitionException {
		ReferenceValueExpression value = null;


		ColumnReference column_reference14 =null;


		  referenceExp = new ReferenceValueExpression();

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:456:3: ( column_reference )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:456:5: column_reference
			{
			pushFollow(FOLLOW_column_reference_in_reference_value_expression613);
			column_reference14=column_reference();
			state._fsp--;

			 
			      referenceExp.add(column_reference14);
			      value = referenceExp;
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "reference_value_expression"



	// $ANTLR start "column_reference"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:462:1: column_reference returns [ColumnReference value] : (t= table_identifier PERIOD )? column_name ;
	public final ColumnReference column_reference() throws RecognitionException {
		ColumnReference value = null;


		String t =null;
		String column_name15 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:463:3: ( (t= table_identifier PERIOD )? column_name )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:463:5: (t= table_identifier PERIOD )? column_name
			{
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:463:5: (t= table_identifier PERIOD )?
			int alt13=2;
			int LA13_0 = input.LA(1);
			if ( (LA13_0==VARNAME) ) {
				int LA13_1 = input.LA(2);
				if ( (LA13_1==PERIOD) ) {
					alt13=1;
				}
			}
			else if ( (LA13_0==STRING_WITH_QUOTE_DOUBLE) ) {
				int LA13_2 = input.LA(2);
				if ( (LA13_2==PERIOD) ) {
					alt13=1;
				}
			}
			switch (alt13) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:463:6: t= table_identifier PERIOD
					{
					pushFollow(FOLLOW_table_identifier_in_column_reference635);
					t=table_identifier();
					state._fsp--;

					match(input,PERIOD,FOLLOW_PERIOD_in_column_reference637); 
					}
					break;

			}

			pushFollow(FOLLOW_column_name_in_column_reference641);
			column_name15=column_name();
			state._fsp--;


			      String table = "";
			      if (t != null) {
			        table = t;
			      }
			      value = new ColumnReference(table, column_name15);
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "column_reference"



	// $ANTLR start "collection_value_expression"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:472:1: collection_value_expression returns [CollectionValueExpression value] : set_function_specification ;
	public final CollectionValueExpression collection_value_expression() throws RecognitionException {
		CollectionValueExpression value = null;



		  collectionExp = new CollectionValueExpression();

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:476:3: ( set_function_specification )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:476:5: set_function_specification
			{
			pushFollow(FOLLOW_set_function_specification_in_collection_value_expression669);
			set_function_specification();
			state._fsp--;

			 
			      value = collectionExp;
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "collection_value_expression"



	// $ANTLR start "set_function_specification"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:481:1: set_function_specification : ( COUNT LPAREN ASTERISK RPAREN | general_set_function );
	public final void set_function_specification() throws RecognitionException {
		Token COUNT16=null;
		Token ASTERISK17=null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:482:3: ( COUNT LPAREN ASTERISK RPAREN | general_set_function )
			int alt14=2;
			int LA14_0 = input.LA(1);
			if ( (LA14_0==COUNT) ) {
				int LA14_1 = input.LA(2);
				if ( (LA14_1==LPAREN) ) {
					int LA14_3 = input.LA(3);
					if ( (LA14_3==ASTERISK) ) {
						alt14=1;
					}
					else if ( (LA14_3==STRING_WITH_QUOTE_DOUBLE||LA14_3==VARNAME) ) {
						alt14=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 14, 3, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 14, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA14_0==ANY||LA14_0==AVG||LA14_0==EVERY||(LA14_0 >= MAX && LA14_0 <= MIN)||LA14_0==SOME||LA14_0==SUM) ) {
				alt14=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 14, 0, input);
				throw nvae;
			}

			switch (alt14) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:482:5: COUNT LPAREN ASTERISK RPAREN
					{
					COUNT16=(Token)match(input,COUNT,FOLLOW_COUNT_in_set_function_specification684); 
					match(input,LPAREN,FOLLOW_LPAREN_in_set_function_specification686); 
					ASTERISK17=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_set_function_specification688); 
					match(input,RPAREN,FOLLOW_RPAREN_in_set_function_specification690); 

					      collectionExp.putSpecification((COUNT16!=null?COUNT16.getText():null));
					      collectionExp.putSpecification((ASTERISK17!=null?ASTERISK17.getText():null));
					    
					}
					break;
				case 2 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:486:5: general_set_function
					{
					pushFollow(FOLLOW_general_set_function_in_set_function_specification698);
					general_set_function();
					state._fsp--;

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "set_function_specification"



	// $ANTLR start "general_set_function"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:490:1: general_set_function : set_function_op LPAREN column_reference RPAREN ;
	public final void general_set_function() throws RecognitionException {
		String set_function_op18 =null;
		ColumnReference column_reference19 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:491:3: ( set_function_op LPAREN column_reference RPAREN )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:491:5: set_function_op LPAREN column_reference RPAREN
			{
			pushFollow(FOLLOW_set_function_op_in_general_set_function713);
			set_function_op18=set_function_op();
			state._fsp--;

			match(input,LPAREN,FOLLOW_LPAREN_in_general_set_function715); 
			pushFollow(FOLLOW_column_reference_in_general_set_function717);
			column_reference19=column_reference();
			state._fsp--;

			match(input,RPAREN,FOLLOW_RPAREN_in_general_set_function719); 

			      collectionExp.putSpecification(set_function_op18);
			      collectionExp.add(column_reference19);
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "general_set_function"



	// $ANTLR start "set_function_op"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:497:1: set_function_op returns [String value] : (t= AVG |t= MAX |t= MIN |t= SUM |t= EVERY |t= ANY |t= SOME |t= COUNT ) ;
	public final String set_function_op() throws RecognitionException {
		String value = null;


		Token t=null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:498:3: ( (t= AVG |t= MAX |t= MIN |t= SUM |t= EVERY |t= ANY |t= SOME |t= COUNT ) )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:498:5: (t= AVG |t= MAX |t= MIN |t= SUM |t= EVERY |t= ANY |t= SOME |t= COUNT )
			{
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:498:5: (t= AVG |t= MAX |t= MIN |t= SUM |t= EVERY |t= ANY |t= SOME |t= COUNT )
			int alt15=8;
			switch ( input.LA(1) ) {
			case AVG:
				{
				alt15=1;
				}
				break;
			case MAX:
				{
				alt15=2;
				}
				break;
			case MIN:
				{
				alt15=3;
				}
				break;
			case SUM:
				{
				alt15=4;
				}
				break;
			case EVERY:
				{
				alt15=5;
				}
				break;
			case ANY:
				{
				alt15=6;
				}
				break;
			case SOME:
				{
				alt15=7;
				}
				break;
			case COUNT:
				{
				alt15=8;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 15, 0, input);
				throw nvae;
			}
			switch (alt15) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:498:6: t= AVG
					{
					t=(Token)match(input,AVG,FOLLOW_AVG_in_set_function_op743); 
					}
					break;
				case 2 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:498:14: t= MAX
					{
					t=(Token)match(input,MAX,FOLLOW_MAX_in_set_function_op749); 
					}
					break;
				case 3 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:498:22: t= MIN
					{
					t=(Token)match(input,MIN,FOLLOW_MIN_in_set_function_op755); 
					}
					break;
				case 4 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:498:30: t= SUM
					{
					t=(Token)match(input,SUM,FOLLOW_SUM_in_set_function_op761); 
					}
					break;
				case 5 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:498:38: t= EVERY
					{
					t=(Token)match(input,EVERY,FOLLOW_EVERY_in_set_function_op767); 
					}
					break;
				case 6 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:498:48: t= ANY
					{
					t=(Token)match(input,ANY,FOLLOW_ANY_in_set_function_op773); 
					}
					break;
				case 7 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:498:56: t= SOME
					{
					t=(Token)match(input,SOME,FOLLOW_SOME_in_set_function_op779); 
					}
					break;
				case 8 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:498:65: t= COUNT
					{
					t=(Token)match(input,COUNT,FOLLOW_COUNT_in_set_function_op785); 
					}
					break;

			}


			      value = (t!=null?t.getText():null);
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "set_function_op"



	// $ANTLR start "row_value_expression"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:503:1: row_value_expression returns [IValueExpression value] : ( literal | value_expression );
	public final IValueExpression row_value_expression() throws RecognitionException {
		IValueExpression value = null;


		Literal literal20 =null;
		AbstractValueExpression value_expression21 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:504:3: ( literal | value_expression )
			int alt16=2;
			int LA16_0 = input.LA(1);
			if ( ((LA16_0 >= DECIMAL && LA16_0 <= DECIMAL_POSITIVE)||LA16_0==FALSE||(LA16_0 >= INTEGER && LA16_0 <= INTEGER_POSITIVE)||LA16_0==STRING_WITH_QUOTE||LA16_0==TRUE) ) {
				alt16=1;
			}
			else if ( (LA16_0==STRING_WITH_QUOTE_DOUBLE||LA16_0==VARNAME) ) {
				alt16=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 16, 0, input);
				throw nvae;
			}

			switch (alt16) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:504:5: literal
					{
					pushFollow(FOLLOW_literal_in_row_value_expression807);
					literal20=literal();
					state._fsp--;

					 value = literal20; 
					}
					break;
				case 2 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:505:5: value_expression
					{
					pushFollow(FOLLOW_value_expression_in_row_value_expression815);
					value_expression21=value_expression();
					state._fsp--;

					 value = value_expression21; 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "row_value_expression"



	// $ANTLR start "literal"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:508:1: literal returns [Literal value] : ( numeric_literal | general_literal );
	public final Literal literal() throws RecognitionException {
		Literal value = null;


		NumericLiteral numeric_literal22 =null;
		Literal general_literal23 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:509:3: ( numeric_literal | general_literal )
			int alt17=2;
			int LA17_0 = input.LA(1);
			if ( ((LA17_0 >= DECIMAL && LA17_0 <= DECIMAL_POSITIVE)||(LA17_0 >= INTEGER && LA17_0 <= INTEGER_POSITIVE)) ) {
				alt17=1;
			}
			else if ( (LA17_0==FALSE||LA17_0==STRING_WITH_QUOTE||LA17_0==TRUE) ) {
				alt17=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 17, 0, input);
				throw nvae;
			}

			switch (alt17) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:509:5: numeric_literal
					{
					pushFollow(FOLLOW_numeric_literal_in_literal834);
					numeric_literal22=numeric_literal();
					state._fsp--;

					 value = numeric_literal22; 
					}
					break;
				case 2 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:510:5: general_literal
					{
					pushFollow(FOLLOW_general_literal_in_literal842);
					general_literal23=general_literal();
					state._fsp--;

					 value = general_literal23; 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "literal"



	// $ANTLR start "table_expression"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:513:1: table_expression returns [TableExpression value] : from_clause ( where_clause )? ;
	public final TableExpression table_expression() throws RecognitionException {
		TableExpression value = null;


		ArrayList<TablePrimary> from_clause24 =null;
		BooleanValueExpression where_clause25 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:514:3: ( from_clause ( where_clause )? )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:514:5: from_clause ( where_clause )?
			{
			pushFollow(FOLLOW_from_clause_in_table_expression861);
			from_clause24=from_clause();
			state._fsp--;


			      value = new TableExpression(from_clause24);
			    
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:517:5: ( where_clause )?
			int alt18=2;
			int LA18_0 = input.LA(1);
			if ( (LA18_0==WHERE) ) {
				alt18=1;
			}
			switch (alt18) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:517:6: where_clause
					{
					pushFollow(FOLLOW_where_clause_in_table_expression870);
					where_clause25=where_clause();
					state._fsp--;

					 value.setWhereClause(where_clause25); 
					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "table_expression"



	// $ANTLR start "from_clause"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:521:1: from_clause returns [ArrayList<TablePrimary> value] : FROM table_reference_list ;
	public final ArrayList<TablePrimary> from_clause() throws RecognitionException {
		ArrayList<TablePrimary> value = null;


		ArrayList<TablePrimary> table_reference_list26 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:522:3: ( FROM table_reference_list )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:522:5: FROM table_reference_list
			{
			match(input,FROM,FOLLOW_FROM_in_from_clause895); 
			pushFollow(FOLLOW_table_reference_list_in_from_clause897);
			table_reference_list26=table_reference_list();
			state._fsp--;


			      value = table_reference_list26;
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "from_clause"



	// $ANTLR start "table_reference_list"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:527:1: table_reference_list returns [ArrayList<TablePrimary> value] : a= table_reference ( COMMA b= table_reference )* ;
	public final ArrayList<TablePrimary> table_reference_list() throws RecognitionException {
		ArrayList<TablePrimary> value = null;


		TablePrimary a =null;
		TablePrimary b =null;


		  value = new ArrayList<TablePrimary>();

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:531:3: (a= table_reference ( COMMA b= table_reference )* )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:531:5: a= table_reference ( COMMA b= table_reference )*
			{
			pushFollow(FOLLOW_table_reference_in_table_reference_list927);
			a=table_reference();
			state._fsp--;

			 value.add(a); 
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:532:5: ( COMMA b= table_reference )*
			loop19:
			while (true) {
				int alt19=2;
				int LA19_0 = input.LA(1);
				if ( (LA19_0==COMMA) ) {
					alt19=1;
				}

				switch (alt19) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:533:7: COMMA b= table_reference
					{
					match(input,COMMA,FOLLOW_COMMA_in_table_reference_list944); 
					pushFollow(FOLLOW_table_reference_in_table_reference_list948);
					b=table_reference();
					state._fsp--;


					        JoinOperator joinOp = new JoinOperator(JoinOperator.CROSS_JOIN);
					        relationStack.push(joinOp);
					        
					        value.add(b);
					      
					}
					break;

				default :
					break loop19;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "table_reference_list"



	// $ANTLR start "table_reference"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:541:1: table_reference returns [TablePrimary value] : table_primary ( joined_table )? ;
	public final TablePrimary table_reference() throws RecognitionException {
		TablePrimary value = null;


		TablePrimary table_primary27 =null;
		TablePrimary joined_table28 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:542:3: ( table_primary ( joined_table )? )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:542:5: table_primary ( joined_table )?
			{
			pushFollow(FOLLOW_table_primary_in_table_reference975);
			table_primary27=table_primary();
			state._fsp--;

			 value = table_primary27; 
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:543:5: ( joined_table )?
			int alt20=2;
			int LA20_0 = input.LA(1);
			if ( (LA20_0==FULL||LA20_0==INNER||(LA20_0 >= JOIN && LA20_0 <= LEFT)||LA20_0==RIGHT) ) {
				alt20=1;
			}
			switch (alt20) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:543:6: joined_table
					{
					pushFollow(FOLLOW_joined_table_in_table_reference984);
					joined_table28=joined_table();
					state._fsp--;

					 value = joined_table28; 
					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "table_reference"



	// $ANTLR start "where_clause"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:546:1: where_clause returns [BooleanValueExpression value] : WHERE search_condition ;
	public final BooleanValueExpression where_clause() throws RecognitionException {
		BooleanValueExpression value = null;


		BooleanValueExpression search_condition29 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:547:3: ( WHERE search_condition )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:547:5: WHERE search_condition
			{
			match(input,WHERE,FOLLOW_WHERE_in_where_clause1006); 
			pushFollow(FOLLOW_search_condition_in_where_clause1008);
			search_condition29=search_condition();
			state._fsp--;


			      value = search_condition29;
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "where_clause"



	// $ANTLR start "search_condition"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:552:1: search_condition returns [BooleanValueExpression value] : boolean_value_expression ;
	public final BooleanValueExpression search_condition() throws RecognitionException {
		BooleanValueExpression value = null;


		BooleanValueExpression boolean_value_expression30 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:553:3: ( boolean_value_expression )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:553:5: boolean_value_expression
			{
			pushFollow(FOLLOW_boolean_value_expression_in_search_condition1027);
			boolean_value_expression30=boolean_value_expression();
			state._fsp--;


			      value = boolean_value_expression30;
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "search_condition"



	// $ANTLR start "boolean_value_expression"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:558:1: boolean_value_expression returns [BooleanValueExpression value] : boolean_term ( OR boolean_term )* ;
	public final BooleanValueExpression boolean_value_expression() throws RecognitionException {
		BooleanValueExpression value = null;



		  if (booleanExp == null) {
		     booleanExp = new BooleanValueExpression();
		  }

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:564:3: ( boolean_term ( OR boolean_term )* )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:568:5: boolean_term ( OR boolean_term )*
			{
			pushFollow(FOLLOW_boolean_term_in_boolean_value_expression1063);
			boolean_term();
			state._fsp--;

			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:568:18: ( OR boolean_term )*
			loop21:
			while (true) {
				int alt21=2;
				int LA21_0 = input.LA(1);
				if ( (LA21_0==OR) ) {
					alt21=1;
				}

				switch (alt21) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:568:19: OR boolean_term
					{
					match(input,OR,FOLLOW_OR_in_boolean_value_expression1066); 
					booleanExp.putSpecification(new OrOperator()); 
					pushFollow(FOLLOW_boolean_term_in_boolean_value_expression1070);
					boolean_term();
					state._fsp--;

					}
					break;

				default :
					break loop21;
				}
			}

			 value = booleanExp; 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "boolean_value_expression"



	// $ANTLR start "boolean_term"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:572:1: boolean_term : boolean_factor ( AND boolean_factor )* ;
	public final void boolean_term() throws RecognitionException {
		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:573:3: ( boolean_factor ( AND boolean_factor )* )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:573:5: boolean_factor ( AND boolean_factor )*
			{
			pushFollow(FOLLOW_boolean_factor_in_boolean_term1093);
			boolean_factor();
			state._fsp--;

			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:573:20: ( AND boolean_factor )*
			loop22:
			while (true) {
				int alt22=2;
				int LA22_0 = input.LA(1);
				if ( (LA22_0==AND) ) {
					alt22=1;
				}

				switch (alt22) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:573:21: AND boolean_factor
					{
					match(input,AND,FOLLOW_AND_in_boolean_term1096); 
					 booleanExp.putSpecification(new AndOperator()); 
					pushFollow(FOLLOW_boolean_factor_in_boolean_term1100);
					boolean_factor();
					state._fsp--;

					}
					break;

				default :
					break loop22;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "boolean_term"



	// $ANTLR start "boolean_factor"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:576:1: boolean_factor : ( predicate | parenthesized_boolean_value_expression );
	public final void boolean_factor() throws RecognitionException {
		IPredicate predicate31 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:577:3: ( predicate | parenthesized_boolean_value_expression )
			int alt23=2;
			int LA23_0 = input.LA(1);
			if ( ((LA23_0 >= DECIMAL && LA23_0 <= DECIMAL_POSITIVE)||LA23_0==FALSE||(LA23_0 >= INTEGER && LA23_0 <= INTEGER_POSITIVE)||(LA23_0 >= STRING_WITH_QUOTE && LA23_0 <= STRING_WITH_QUOTE_DOUBLE)||LA23_0==TRUE||LA23_0==VARNAME) ) {
				alt23=1;
			}
			else if ( (LA23_0==LPAREN) ) {
				alt23=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 23, 0, input);
				throw nvae;
			}

			switch (alt23) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:577:5: predicate
					{
					pushFollow(FOLLOW_predicate_in_boolean_factor1115);
					predicate31=predicate();
					state._fsp--;

					 booleanExp.putSpecification(predicate31); 
					}
					break;
				case 2 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:578:5: parenthesized_boolean_value_expression
					{
					pushFollow(FOLLOW_parenthesized_boolean_value_expression_in_boolean_factor1123);
					parenthesized_boolean_value_expression();
					state._fsp--;

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "boolean_factor"



	// $ANTLR start "predicate"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:581:1: predicate returns [IPredicate value] : ( comparison_predicate | null_predicate );
	public final IPredicate predicate() throws RecognitionException {
		IPredicate value = null;


		ComparisonPredicate comparison_predicate32 =null;
		NullPredicate null_predicate33 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:582:3: ( comparison_predicate | null_predicate )
			int alt24=2;
			switch ( input.LA(1) ) {
			case DECIMAL:
			case DECIMAL_NEGATIVE:
			case DECIMAL_POSITIVE:
			case FALSE:
			case INTEGER:
			case INTEGER_NEGATIVE:
			case INTEGER_POSITIVE:
			case STRING_WITH_QUOTE:
			case TRUE:
				{
				alt24=1;
				}
				break;
			case VARNAME:
				{
				switch ( input.LA(2) ) {
				case PERIOD:
					{
					int LA24_4 = input.LA(3);
					if ( (LA24_4==VARNAME) ) {
						int LA24_6 = input.LA(4);
						if ( (LA24_6==EQUALS||LA24_6==GREATER||LA24_6==LESS) ) {
							alt24=1;
						}
						else if ( (LA24_6==IS) ) {
							alt24=2;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 24, 6, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}
					else if ( (LA24_4==STRING_WITH_QUOTE_DOUBLE) ) {
						int LA24_7 = input.LA(4);
						if ( (LA24_7==EQUALS||LA24_7==GREATER||LA24_7==LESS) ) {
							alt24=1;
						}
						else if ( (LA24_7==IS) ) {
							alt24=2;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 24, 7, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 24, 4, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

					}
					break;
				case EQUALS:
				case GREATER:
				case LESS:
					{
					alt24=1;
					}
					break;
				case IS:
					{
					alt24=2;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 24, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			case STRING_WITH_QUOTE_DOUBLE:
				{
				switch ( input.LA(2) ) {
				case PERIOD:
					{
					int LA24_4 = input.LA(3);
					if ( (LA24_4==VARNAME) ) {
						int LA24_6 = input.LA(4);
						if ( (LA24_6==EQUALS||LA24_6==GREATER||LA24_6==LESS) ) {
							alt24=1;
						}
						else if ( (LA24_6==IS) ) {
							alt24=2;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 24, 6, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}
					else if ( (LA24_4==STRING_WITH_QUOTE_DOUBLE) ) {
						int LA24_7 = input.LA(4);
						if ( (LA24_7==EQUALS||LA24_7==GREATER||LA24_7==LESS) ) {
							alt24=1;
						}
						else if ( (LA24_7==IS) ) {
							alt24=2;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 24, 7, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 24, 4, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

					}
					break;
				case EQUALS:
				case GREATER:
				case LESS:
					{
					alt24=1;
					}
					break;
				case IS:
					{
					alt24=2;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 24, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 24, 0, input);
				throw nvae;
			}
			switch (alt24) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:582:5: comparison_predicate
					{
					pushFollow(FOLLOW_comparison_predicate_in_predicate1141);
					comparison_predicate32=comparison_predicate();
					state._fsp--;

					 value = comparison_predicate32; 
					}
					break;
				case 2 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:583:5: null_predicate
					{
					pushFollow(FOLLOW_null_predicate_in_predicate1149);
					null_predicate33=null_predicate();
					state._fsp--;

					 value = null_predicate33; 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "predicate"



	// $ANTLR start "parenthesized_boolean_value_expression"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:587:1: parenthesized_boolean_value_expression : LPAREN boolean_value_expression RPAREN ;
	public final void parenthesized_boolean_value_expression() throws RecognitionException {
		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:588:3: ( LPAREN boolean_value_expression RPAREN )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:588:5: LPAREN boolean_value_expression RPAREN
			{
			match(input,LPAREN,FOLLOW_LPAREN_in_parenthesized_boolean_value_expression1165); 
			 booleanExp.putSpecification(new LeftParenthesis()); 
			pushFollow(FOLLOW_boolean_value_expression_in_parenthesized_boolean_value_expression1169);
			boolean_value_expression();
			state._fsp--;

			match(input,RPAREN,FOLLOW_RPAREN_in_parenthesized_boolean_value_expression1171); 
			 booleanExp.putSpecification(new RightParenthesis()); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "parenthesized_boolean_value_expression"



	// $ANTLR start "comparison_predicate"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:591:1: comparison_predicate returns [ComparisonPredicate value] : a= row_value_expression comp_op b= row_value_expression ;
	public final ComparisonPredicate comparison_predicate() throws RecognitionException {
		ComparisonPredicate value = null;


		IValueExpression a =null;
		IValueExpression b =null;
		ComparisonPredicate.Operator comp_op34 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:592:3: (a= row_value_expression comp_op b= row_value_expression )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:592:5: a= row_value_expression comp_op b= row_value_expression
			{
			pushFollow(FOLLOW_row_value_expression_in_comparison_predicate1194);
			a=row_value_expression();
			state._fsp--;

			pushFollow(FOLLOW_comp_op_in_comparison_predicate1196);
			comp_op34=comp_op();
			state._fsp--;

			pushFollow(FOLLOW_row_value_expression_in_comparison_predicate1200);
			b=row_value_expression();
			state._fsp--;


			      value = new ComparisonPredicate(a, b, comp_op34);
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "comparison_predicate"



	// $ANTLR start "comp_op"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:597:1: comp_op returns [ComparisonPredicate.Operator value] : ( EQUALS | LESS GREATER | LESS | GREATER | LESS EQUALS | GREATER EQUALS );
	public final ComparisonPredicate.Operator comp_op() throws RecognitionException {
		ComparisonPredicate.Operator value = null;


		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:598:3: ( EQUALS | LESS GREATER | LESS | GREATER | LESS EQUALS | GREATER EQUALS )
			int alt25=6;
			switch ( input.LA(1) ) {
			case EQUALS:
				{
				alt25=1;
				}
				break;
			case LESS:
				{
				switch ( input.LA(2) ) {
				case GREATER:
					{
					alt25=2;
					}
					break;
				case EQUALS:
					{
					alt25=5;
					}
					break;
				case DECIMAL:
				case DECIMAL_NEGATIVE:
				case DECIMAL_POSITIVE:
				case FALSE:
				case INTEGER:
				case INTEGER_NEGATIVE:
				case INTEGER_POSITIVE:
				case STRING_WITH_QUOTE:
				case STRING_WITH_QUOTE_DOUBLE:
				case TRUE:
				case VARNAME:
					{
					alt25=3;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 25, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			case GREATER:
				{
				int LA25_3 = input.LA(2);
				if ( (LA25_3==EQUALS) ) {
					alt25=6;
				}
				else if ( ((LA25_3 >= DECIMAL && LA25_3 <= DECIMAL_POSITIVE)||LA25_3==FALSE||(LA25_3 >= INTEGER && LA25_3 <= INTEGER_POSITIVE)||(LA25_3 >= STRING_WITH_QUOTE && LA25_3 <= STRING_WITH_QUOTE_DOUBLE)||LA25_3==TRUE||LA25_3==VARNAME) ) {
					alt25=4;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 25, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 25, 0, input);
				throw nvae;
			}
			switch (alt25) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:598:5: EQUALS
					{
					match(input,EQUALS,FOLLOW_EQUALS_in_comp_op1219); 
					 value = ComparisonPredicate.Operator.EQ; 
					}
					break;
				case 2 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:599:5: LESS GREATER
					{
					match(input,LESS,FOLLOW_LESS_in_comp_op1227); 
					match(input,GREATER,FOLLOW_GREATER_in_comp_op1229); 
					 value = ComparisonPredicate.Operator.NE; 
					}
					break;
				case 3 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:600:5: LESS
					{
					match(input,LESS,FOLLOW_LESS_in_comp_op1237); 
					 value = ComparisonPredicate.Operator.LT; 
					}
					break;
				case 4 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:601:5: GREATER
					{
					match(input,GREATER,FOLLOW_GREATER_in_comp_op1245); 
					 value = ComparisonPredicate.Operator.GT; 
					}
					break;
				case 5 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:602:5: LESS EQUALS
					{
					match(input,LESS,FOLLOW_LESS_in_comp_op1253); 
					match(input,EQUALS,FOLLOW_EQUALS_in_comp_op1255); 
					 value = ComparisonPredicate.Operator.LE; 
					}
					break;
				case 6 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:603:5: GREATER EQUALS
					{
					match(input,GREATER,FOLLOW_GREATER_in_comp_op1263); 
					match(input,EQUALS,FOLLOW_EQUALS_in_comp_op1265); 
					 value = ComparisonPredicate.Operator.GE; 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "comp_op"



	// $ANTLR start "null_predicate"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:606:1: null_predicate returns [NullPredicate value] : column_reference IS ( NOT )? NULL ;
	public final NullPredicate null_predicate() throws RecognitionException {
		NullPredicate value = null;


		ColumnReference column_reference35 =null;


		  boolean useIsNull = true;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:610:3: ( column_reference IS ( NOT )? NULL )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:610:5: column_reference IS ( NOT )? NULL
			{
			pushFollow(FOLLOW_column_reference_in_null_predicate1289);
			column_reference35=column_reference();
			state._fsp--;

			match(input,IS,FOLLOW_IS_in_null_predicate1291); 
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:610:25: ( NOT )?
			int alt26=2;
			int LA26_0 = input.LA(1);
			if ( (LA26_0==NOT) ) {
				alt26=1;
			}
			switch (alt26) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:610:26: NOT
					{
					match(input,NOT,FOLLOW_NOT_in_null_predicate1294); 
					 useIsNull = false; 
					}
					break;

			}

			match(input,NULL,FOLLOW_NULL_in_null_predicate1300); 

			      value = new NullPredicate(column_reference35, useIsNull);
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "null_predicate"



	// $ANTLR start "in_predicate"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:615:1: in_predicate : column_reference ( NOT )? IN in_predicate_value ;
	public final void in_predicate() throws RecognitionException {
		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:616:3: ( column_reference ( NOT )? IN in_predicate_value )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:616:5: column_reference ( NOT )? IN in_predicate_value
			{
			pushFollow(FOLLOW_column_reference_in_in_predicate1315);
			column_reference();
			state._fsp--;

			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:616:22: ( NOT )?
			int alt27=2;
			int LA27_0 = input.LA(1);
			if ( (LA27_0==NOT) ) {
				alt27=1;
			}
			switch (alt27) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:616:23: NOT
					{
					match(input,NOT,FOLLOW_NOT_in_in_predicate1318); 
					}
					break;

			}

			match(input,IN,FOLLOW_IN_in_in_predicate1322); 
			pushFollow(FOLLOW_in_predicate_value_in_in_predicate1324);
			in_predicate_value();
			state._fsp--;

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "in_predicate"



	// $ANTLR start "in_predicate_value"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:619:1: in_predicate_value : ( table_subquery | LPAREN in_value_list RPAREN );
	public final void in_predicate_value() throws RecognitionException {
		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:620:3: ( table_subquery | LPAREN in_value_list RPAREN )
			int alt28=2;
			int LA28_0 = input.LA(1);
			if ( (LA28_0==LPAREN) ) {
				int LA28_1 = input.LA(2);
				if ( (LA28_1==SELECT) ) {
					alt28=1;
				}
				else if ( ((LA28_1 >= DECIMAL && LA28_1 <= DECIMAL_POSITIVE)||LA28_1==FALSE||(LA28_1 >= INTEGER && LA28_1 <= INTEGER_POSITIVE)||(LA28_1 >= STRING_WITH_QUOTE && LA28_1 <= STRING_WITH_QUOTE_DOUBLE)||LA28_1==TRUE||LA28_1==VARNAME) ) {
					alt28=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 28, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 28, 0, input);
				throw nvae;
			}

			switch (alt28) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:620:5: table_subquery
					{
					pushFollow(FOLLOW_table_subquery_in_in_predicate_value1339);
					table_subquery();
					state._fsp--;

					}
					break;
				case 2 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:621:5: LPAREN in_value_list RPAREN
					{
					match(input,LPAREN,FOLLOW_LPAREN_in_in_predicate_value1345); 
					pushFollow(FOLLOW_in_value_list_in_in_predicate_value1347);
					in_value_list();
					state._fsp--;

					match(input,RPAREN,FOLLOW_RPAREN_in_in_predicate_value1349); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "in_predicate_value"



	// $ANTLR start "table_subquery"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:624:1: table_subquery : subquery ;
	public final void table_subquery() throws RecognitionException {
		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:625:3: ( subquery )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:625:5: subquery
			{
			pushFollow(FOLLOW_subquery_in_table_subquery1362);
			subquery();
			state._fsp--;

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "table_subquery"



	// $ANTLR start "subquery"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:628:1: subquery : LPAREN query RPAREN ;
	public final void subquery() throws RecognitionException {
		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:629:3: ( LPAREN query RPAREN )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:629:5: LPAREN query RPAREN
			{
			match(input,LPAREN,FOLLOW_LPAREN_in_subquery1375); 
			pushFollow(FOLLOW_query_in_subquery1377);
			query();
			state._fsp--;

			match(input,RPAREN,FOLLOW_RPAREN_in_subquery1379); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "subquery"



	// $ANTLR start "in_value_list"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:632:1: in_value_list : row_value_expression ( COMMA row_value_expression )* ;
	public final void in_value_list() throws RecognitionException {
		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:633:3: ( row_value_expression ( COMMA row_value_expression )* )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:633:5: row_value_expression ( COMMA row_value_expression )*
			{
			pushFollow(FOLLOW_row_value_expression_in_in_value_list1394);
			row_value_expression();
			state._fsp--;

			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:633:26: ( COMMA row_value_expression )*
			loop29:
			while (true) {
				int alt29=2;
				int LA29_0 = input.LA(1);
				if ( (LA29_0==COMMA) ) {
					alt29=1;
				}

				switch (alt29) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:633:27: COMMA row_value_expression
					{
					match(input,COMMA,FOLLOW_COMMA_in_in_value_list1397); 
					pushFollow(FOLLOW_row_value_expression_in_in_value_list1399);
					row_value_expression();
					state._fsp--;

					}
					break;

				default :
					break loop29;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "in_value_list"



	// $ANTLR start "group_by_clause"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:636:1: group_by_clause returns [ArrayList<GroupingElement> value] : GROUP BY grouping_element_list ;
	public final ArrayList<GroupingElement> group_by_clause() throws RecognitionException {
		ArrayList<GroupingElement> value = null;


		ArrayList<GroupingElement> grouping_element_list36 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:637:3: ( GROUP BY grouping_element_list )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:637:5: GROUP BY grouping_element_list
			{
			match(input,GROUP,FOLLOW_GROUP_in_group_by_clause1418); 
			match(input,BY,FOLLOW_BY_in_group_by_clause1420); 
			pushFollow(FOLLOW_grouping_element_list_in_group_by_clause1422);
			grouping_element_list36=grouping_element_list();
			state._fsp--;


			      value = grouping_element_list36;
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "group_by_clause"



	// $ANTLR start "grouping_element_list"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:642:1: grouping_element_list returns [ArrayList<GroupingElement> value] : a= grouping_element ( COMMA b= grouping_element )* ;
	public final ArrayList<GroupingElement> grouping_element_list() throws RecognitionException {
		ArrayList<GroupingElement> value = null;


		GroupingElement a =null;
		GroupingElement b =null;


		  value = new ArrayList<GroupingElement>();

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:646:3: (a= grouping_element ( COMMA b= grouping_element )* )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:646:5: a= grouping_element ( COMMA b= grouping_element )*
			{
			pushFollow(FOLLOW_grouping_element_in_grouping_element_list1448);
			a=grouping_element();
			state._fsp--;

			 value.add(a); 
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:647:5: ( COMMA b= grouping_element )*
			loop30:
			while (true) {
				int alt30=2;
				int LA30_0 = input.LA(1);
				if ( (LA30_0==COMMA) ) {
					alt30=1;
				}

				switch (alt30) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:647:6: COMMA b= grouping_element
					{
					match(input,COMMA,FOLLOW_COMMA_in_grouping_element_list1458); 
					pushFollow(FOLLOW_grouping_element_in_grouping_element_list1462);
					b=grouping_element();
					state._fsp--;

					 value.add(b); 
					}
					break;

				default :
					break loop30;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "grouping_element_list"



	// $ANTLR start "grouping_element"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:650:1: grouping_element returns [GroupingElement value] : ( grouping_column_reference | LPAREN grouping_column_reference_list RPAREN );
	public final GroupingElement grouping_element() throws RecognitionException {
		GroupingElement value = null;


		ColumnReference grouping_column_reference37 =null;
		ArrayList<ColumnReference> grouping_column_reference_list38 =null;


		  value = new GroupingElement();

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:654:3: ( grouping_column_reference | LPAREN grouping_column_reference_list RPAREN )
			int alt31=2;
			int LA31_0 = input.LA(1);
			if ( (LA31_0==STRING_WITH_QUOTE_DOUBLE||LA31_0==VARNAME) ) {
				alt31=1;
			}
			else if ( (LA31_0==LPAREN) ) {
				alt31=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 31, 0, input);
				throw nvae;
			}

			switch (alt31) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:654:5: grouping_column_reference
					{
					pushFollow(FOLLOW_grouping_column_reference_in_grouping_element1490);
					grouping_column_reference37=grouping_column_reference();
					state._fsp--;

					 value.add(grouping_column_reference37); 
					}
					break;
				case 2 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:655:5: LPAREN grouping_column_reference_list RPAREN
					{
					match(input,LPAREN,FOLLOW_LPAREN_in_grouping_element1498); 
					pushFollow(FOLLOW_grouping_column_reference_list_in_grouping_element1500);
					grouping_column_reference_list38=grouping_column_reference_list();
					state._fsp--;

					match(input,RPAREN,FOLLOW_RPAREN_in_grouping_element1502); 
					 value.update(grouping_column_reference_list38); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "grouping_element"



	// $ANTLR start "grouping_column_reference"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:658:1: grouping_column_reference returns [ColumnReference value] : column_reference ;
	public final ColumnReference grouping_column_reference() throws RecognitionException {
		ColumnReference value = null;


		ColumnReference column_reference39 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:659:3: ( column_reference )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:659:5: column_reference
			{
			pushFollow(FOLLOW_column_reference_in_grouping_column_reference1523);
			column_reference39=column_reference();
			state._fsp--;

			 value = column_reference39; 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "grouping_column_reference"



	// $ANTLR start "grouping_column_reference_list"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:662:1: grouping_column_reference_list returns [ArrayList<ColumnReference> value] : a= column_reference ( COMMA b= column_reference )* ;
	public final ArrayList<ColumnReference> grouping_column_reference_list() throws RecognitionException {
		ArrayList<ColumnReference> value = null;


		ColumnReference a =null;
		ColumnReference b =null;


		  value = new ArrayList<ColumnReference>();

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:666:3: (a= column_reference ( COMMA b= column_reference )* )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:666:5: a= column_reference ( COMMA b= column_reference )*
			{
			pushFollow(FOLLOW_column_reference_in_grouping_column_reference_list1551);
			a=column_reference();
			state._fsp--;

			 value.add(a); 
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:667:5: ( COMMA b= column_reference )*
			loop32:
			while (true) {
				int alt32=2;
				int LA32_0 = input.LA(1);
				if ( (LA32_0==COMMA) ) {
					alt32=1;
				}

				switch (alt32) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:667:6: COMMA b= column_reference
					{
					match(input,COMMA,FOLLOW_COMMA_in_grouping_column_reference_list1560); 
					pushFollow(FOLLOW_column_reference_in_grouping_column_reference_list1564);
					b=column_reference();
					state._fsp--;

					 value.add(b); 
					}
					break;

				default :
					break loop32;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "grouping_column_reference_list"



	// $ANTLR start "joined_table"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:670:1: joined_table returns [TablePrimary value] : ( ( join_type )? JOIN table_reference join_specification ) ;
	public final TablePrimary joined_table() throws RecognitionException {
		TablePrimary value = null;


		int join_type40 =0;
		BooleanValueExpression join_specification41 =null;
		TablePrimary table_reference42 =null;


		  int joinType = JoinOperator.JOIN; // by default

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:674:3: ( ( ( join_type )? JOIN table_reference join_specification ) )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:674:5: ( ( join_type )? JOIN table_reference join_specification )
			{
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:674:5: ( ( join_type )? JOIN table_reference join_specification )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:674:6: ( join_type )? JOIN table_reference join_specification
			{
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:674:6: ( join_type )?
			int alt33=2;
			int LA33_0 = input.LA(1);
			if ( (LA33_0==FULL||LA33_0==INNER||LA33_0==LEFT||LA33_0==RIGHT) ) {
				alt33=1;
			}
			switch (alt33) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:674:7: join_type
					{
					pushFollow(FOLLOW_join_type_in_joined_table1594);
					join_type40=join_type();
					state._fsp--;

					 joinType = join_type40; 
					}
					break;

			}

			match(input,JOIN,FOLLOW_JOIN_in_joined_table1600); 
			pushFollow(FOLLOW_table_reference_in_joined_table1602);
			table_reference42=table_reference();
			state._fsp--;

			pushFollow(FOLLOW_join_specification_in_joined_table1604);
			join_specification41=join_specification();
			state._fsp--;


			      JoinOperator joinOp = new JoinOperator(joinType);
			      if (join_specification41 != null) {
			          joinOp.copy(join_specification41.getSpecification());
			          relationStack.push(joinOp);
			          value = table_reference42;
			      }
			    
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "joined_table"



	// $ANTLR start "join_type"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:684:1: join_type returns [int value] : ( INNER | outer_join_type ( OUTER )? );
	public final int join_type() throws RecognitionException {
		int value = 0;


		int outer_join_type43 =0;


		  boolean bHasOuter = false;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:688:3: ( INNER | outer_join_type ( OUTER )? )
			int alt35=2;
			int LA35_0 = input.LA(1);
			if ( (LA35_0==INNER) ) {
				alt35=1;
			}
			else if ( (LA35_0==FULL||LA35_0==LEFT||LA35_0==RIGHT) ) {
				alt35=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 35, 0, input);
				throw nvae;
			}

			switch (alt35) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:688:5: INNER
					{
					match(input,INNER,FOLLOW_INNER_in_join_type1629); 
					 value = JoinOperator.INNER_JOIN; 
					}
					break;
				case 2 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:689:5: outer_join_type ( OUTER )?
					{
					pushFollow(FOLLOW_outer_join_type_in_join_type1637);
					outer_join_type43=outer_join_type();
					state._fsp--;

					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:689:21: ( OUTER )?
					int alt34=2;
					int LA34_0 = input.LA(1);
					if ( (LA34_0==OUTER) ) {
						alt34=1;
					}
					switch (alt34) {
						case 1 :
							// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:689:22: OUTER
							{
							match(input,OUTER,FOLLOW_OUTER_in_join_type1640); 
							 bHasOuter = true; 
							}
							break;

					}


					      if (bHasOuter) {
					        switch(outer_join_type43) {
					          case JoinOperator.LEFT_JOIN: value = JoinOperator.LEFT_OUTER_JOIN; break;
					          case JoinOperator.RIGHT_JOIN: value = JoinOperator.RIGHT_OUTER_JOIN; break;
					          case JoinOperator.FULL_JOIN: value = JoinOperator.FULL_OUTER_JOIN; break;
					        }
					      }
					      else {
					        value = outer_join_type43;
					      }
					    
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "join_type"



	// $ANTLR start "outer_join_type"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:703:1: outer_join_type returns [int value] : ( LEFT | RIGHT | FULL );
	public final int outer_join_type() throws RecognitionException {
		int value = 0;


		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:704:3: ( LEFT | RIGHT | FULL )
			int alt36=3;
			switch ( input.LA(1) ) {
			case LEFT:
				{
				alt36=1;
				}
				break;
			case RIGHT:
				{
				alt36=2;
				}
				break;
			case FULL:
				{
				alt36=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 36, 0, input);
				throw nvae;
			}
			switch (alt36) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:704:5: LEFT
					{
					match(input,LEFT,FOLLOW_LEFT_in_outer_join_type1665); 
					 value = JoinOperator.LEFT_JOIN; 
					}
					break;
				case 2 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:705:5: RIGHT
					{
					match(input,RIGHT,FOLLOW_RIGHT_in_outer_join_type1673); 
					 value = JoinOperator.RIGHT_JOIN; 
					}
					break;
				case 3 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:706:5: FULL
					{
					match(input,FULL,FOLLOW_FULL_in_outer_join_type1681); 
					 value = JoinOperator.FULL_JOIN; 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "outer_join_type"



	// $ANTLR start "join_specification"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:709:1: join_specification returns [BooleanValueExpression value] : join_condition ;
	public final BooleanValueExpression join_specification() throws RecognitionException {
		BooleanValueExpression value = null;


		BooleanValueExpression join_condition44 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:710:3: ( join_condition )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:710:5: join_condition
			{
			pushFollow(FOLLOW_join_condition_in_join_specification1700);
			join_condition44=join_condition();
			state._fsp--;

			 value = join_condition44; 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "join_specification"



	// $ANTLR start "join_condition"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:714:1: join_condition returns [BooleanValueExpression value] : ON search_condition ;
	public final BooleanValueExpression join_condition() throws RecognitionException {
		BooleanValueExpression value = null;


		BooleanValueExpression search_condition45 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:715:3: ( ON search_condition )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:715:5: ON search_condition
			{
			match(input,ON,FOLLOW_ON_in_join_condition1720); 
			pushFollow(FOLLOW_search_condition_in_join_condition1722);
			search_condition45=search_condition();
			state._fsp--;


			      value = search_condition45;
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "join_condition"



	// $ANTLR start "named_columns_join"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:720:1: named_columns_join : USING LPAREN join_column_list RPAREN ;
	public final void named_columns_join() throws RecognitionException {
		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:721:3: ( USING LPAREN join_column_list RPAREN )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:721:5: USING LPAREN join_column_list RPAREN
			{
			match(input,USING,FOLLOW_USING_in_named_columns_join1737); 
			match(input,LPAREN,FOLLOW_LPAREN_in_named_columns_join1739); 
			pushFollow(FOLLOW_join_column_list_in_named_columns_join1741);
			join_column_list();
			state._fsp--;

			match(input,RPAREN,FOLLOW_RPAREN_in_named_columns_join1743); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "named_columns_join"



	// $ANTLR start "join_column_list"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:724:1: join_column_list : column_name ( COMMA column_name )* ;
	public final void join_column_list() throws RecognitionException {
		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:725:3: ( column_name ( COMMA column_name )* )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:725:5: column_name ( COMMA column_name )*
			{
			pushFollow(FOLLOW_column_name_in_join_column_list1756);
			column_name();
			state._fsp--;

			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:725:17: ( COMMA column_name )*
			loop37:
			while (true) {
				int alt37=2;
				int LA37_0 = input.LA(1);
				if ( (LA37_0==COMMA) ) {
					alt37=1;
				}

				switch (alt37) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:725:18: COMMA column_name
					{
					match(input,COMMA,FOLLOW_COMMA_in_join_column_list1759); 
					pushFollow(FOLLOW_column_name_in_join_column_list1761);
					column_name();
					state._fsp--;

					}
					break;

				default :
					break loop37;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "join_column_list"



	// $ANTLR start "table_primary"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:729:1: table_primary returns [TablePrimary value] : table_name ( ( AS )? alias_name )? ;
	public final TablePrimary table_primary() throws RecognitionException {
		TablePrimary value = null;


		TablePrimary table_name46 =null;
		String alias_name47 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:730:3: ( table_name ( ( AS )? alias_name )? )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:730:5: table_name ( ( AS )? alias_name )?
			{
			pushFollow(FOLLOW_table_name_in_table_primary1781);
			table_name46=table_name();
			state._fsp--;

			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:731:5: ( ( AS )? alias_name )?
			int alt39=2;
			int LA39_0 = input.LA(1);
			if ( (LA39_0==AS||LA39_0==STRING_WITH_QUOTE_DOUBLE||LA39_0==VARNAME) ) {
				alt39=1;
			}
			switch (alt39) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:731:6: ( AS )? alias_name
					{
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:731:6: ( AS )?
					int alt38=2;
					int LA38_0 = input.LA(1);
					if ( (LA38_0==AS) ) {
						alt38=1;
					}
					switch (alt38) {
						case 1 :
							// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:731:6: AS
							{
							match(input,AS,FOLLOW_AS_in_table_primary1788); 
							}
							break;

					}

					pushFollow(FOLLOW_alias_name_in_table_primary1791);
					alias_name47=alias_name();
					state._fsp--;

					}
					break;

			}


			      value = table_name46; 
			      value.setAlias(alias_name47);
			      Relation table = new Relation(value);      
			      relationStack.push(table);
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "table_primary"



	// $ANTLR start "table_name"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:743:1: table_name returns [TablePrimary value] : ( schema_name PERIOD )? table_identifier ;
	public final TablePrimary table_name() throws RecognitionException {
		TablePrimary value = null;


		String schema_name48 =null;
		String table_identifier49 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:744:3: ( ( schema_name PERIOD )? table_identifier )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:744:5: ( schema_name PERIOD )? table_identifier
			{
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:744:5: ( schema_name PERIOD )?
			int alt40=2;
			int LA40_0 = input.LA(1);
			if ( (LA40_0==VARNAME) ) {
				int LA40_1 = input.LA(2);
				if ( (LA40_1==PERIOD) ) {
					alt40=1;
				}
			}
			else if ( (LA40_0==STRING_WITH_QUOTE_DOUBLE) ) {
				int LA40_2 = input.LA(2);
				if ( (LA40_2==PERIOD) ) {
					alt40=1;
				}
			}
			switch (alt40) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:744:6: schema_name PERIOD
					{
					pushFollow(FOLLOW_schema_name_in_table_name1819);
					schema_name48=schema_name();
					state._fsp--;

					match(input,PERIOD,FOLLOW_PERIOD_in_table_name1821); 
					}
					break;

			}

			pushFollow(FOLLOW_table_identifier_in_table_name1825);
			table_identifier49=table_identifier();
			state._fsp--;


			      String schema = schema_name48;      
			      if (schema != null && schema != "") {
			        value = new TablePrimary(schema, table_identifier49);
			      }
			      else {
			        value = new TablePrimary(table_identifier49);
			      }      
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "table_name"



	// $ANTLR start "alias_name"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:755:1: alias_name returns [String value] : identifier ;
	public final String alias_name() throws RecognitionException {
		String value = null;


		String identifier50 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:756:3: ( identifier )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:756:5: identifier
			{
			pushFollow(FOLLOW_identifier_in_alias_name1846);
			identifier50=identifier();
			state._fsp--;

			 value = identifier50; 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "alias_name"



	// $ANTLR start "derived_table"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:759:1: derived_table : table_subquery ;
	public final void derived_table() throws RecognitionException {
		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:760:3: ( table_subquery )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:760:5: table_subquery
			{
			pushFollow(FOLLOW_table_subquery_in_derived_table1862);
			table_subquery();
			state._fsp--;

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "derived_table"



	// $ANTLR start "table_identifier"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:763:1: table_identifier returns [String value] : identifier ;
	public final String table_identifier() throws RecognitionException {
		String value = null;


		String identifier51 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:764:3: ( identifier )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:764:5: identifier
			{
			pushFollow(FOLLOW_identifier_in_table_identifier1883);
			identifier51=identifier();
			state._fsp--;

			 value = identifier51; 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "table_identifier"



	// $ANTLR start "schema_name"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:767:1: schema_name returns [String value] : identifier ;
	public final String schema_name() throws RecognitionException {
		String value = null;


		String identifier52 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:768:3: ( identifier )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:768:5: identifier
			{
			pushFollow(FOLLOW_identifier_in_schema_name1904);
			identifier52=identifier();
			state._fsp--;

			 value = identifier52; 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "schema_name"



	// $ANTLR start "column_name"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:771:1: column_name returns [String value] : identifier ;
	public final String column_name() throws RecognitionException {
		String value = null;


		String identifier53 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:772:3: ( identifier )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:772:5: identifier
			{
			pushFollow(FOLLOW_identifier_in_column_name1927);
			identifier53=identifier();
			state._fsp--;

			 value = identifier53; 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "column_name"



	// $ANTLR start "identifier"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:775:1: identifier returns [String value] : (t= regular_identifier |t= delimited_identifier ) ;
	public final String identifier() throws RecognitionException {
		String value = null;


		String t =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:776:3: ( (t= regular_identifier |t= delimited_identifier ) )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:776:5: (t= regular_identifier |t= delimited_identifier )
			{
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:776:5: (t= regular_identifier |t= delimited_identifier )
			int alt41=2;
			int LA41_0 = input.LA(1);
			if ( (LA41_0==VARNAME) ) {
				alt41=1;
			}
			else if ( (LA41_0==STRING_WITH_QUOTE_DOUBLE) ) {
				alt41=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 41, 0, input);
				throw nvae;
			}

			switch (alt41) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:776:6: t= regular_identifier
					{
					pushFollow(FOLLOW_regular_identifier_in_identifier1951);
					t=regular_identifier();
					state._fsp--;

					}
					break;
				case 2 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:776:29: t= delimited_identifier
					{
					pushFollow(FOLLOW_delimited_identifier_in_identifier1957);
					t=delimited_identifier();
					state._fsp--;

					}
					break;

			}

			 value = t; 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "identifier"



	// $ANTLR start "regular_identifier"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:779:1: regular_identifier returns [String value] : VARNAME ;
	public final String regular_identifier() throws RecognitionException {
		String value = null;


		Token VARNAME54=null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:780:3: ( VARNAME )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:780:5: VARNAME
			{
			VARNAME54=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_regular_identifier1977); 
			 value = (VARNAME54!=null?VARNAME54.getText():null); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "regular_identifier"



	// $ANTLR start "delimited_identifier"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:783:1: delimited_identifier returns [String value] : STRING_WITH_QUOTE_DOUBLE ;
	public final String delimited_identifier() throws RecognitionException {
		String value = null;


		Token STRING_WITH_QUOTE_DOUBLE55=null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:784:3: ( STRING_WITH_QUOTE_DOUBLE )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:784:5: STRING_WITH_QUOTE_DOUBLE
			{
			STRING_WITH_QUOTE_DOUBLE55=(Token)match(input,STRING_WITH_QUOTE_DOUBLE,FOLLOW_STRING_WITH_QUOTE_DOUBLE_in_delimited_identifier1996); 
			 
			      value = (STRING_WITH_QUOTE_DOUBLE55!=null?STRING_WITH_QUOTE_DOUBLE55.getText():null);
			      value = value.substring(1, value.length()-1);
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "delimited_identifier"



	// $ANTLR start "general_literal"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:790:1: general_literal returns [Literal value] : ( string_literal | boolean_literal );
	public final Literal general_literal() throws RecognitionException {
		Literal value = null;


		StringLiteral string_literal56 =null;
		BooleanLiteral boolean_literal57 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:791:3: ( string_literal | boolean_literal )
			int alt42=2;
			int LA42_0 = input.LA(1);
			if ( (LA42_0==STRING_WITH_QUOTE) ) {
				alt42=1;
			}
			else if ( (LA42_0==FALSE||LA42_0==TRUE) ) {
				alt42=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 42, 0, input);
				throw nvae;
			}

			switch (alt42) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:791:5: string_literal
					{
					pushFollow(FOLLOW_string_literal_in_general_literal2015);
					string_literal56=string_literal();
					state._fsp--;

					 value = string_literal56; 
					}
					break;
				case 2 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:792:5: boolean_literal
					{
					pushFollow(FOLLOW_boolean_literal_in_general_literal2023);
					boolean_literal57=boolean_literal();
					state._fsp--;

					 value = boolean_literal57; 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "general_literal"



	// $ANTLR start "string_literal"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:795:1: string_literal returns [StringLiteral value] : STRING_WITH_QUOTE ;
	public final StringLiteral string_literal() throws RecognitionException {
		StringLiteral value = null;


		Token STRING_WITH_QUOTE58=null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:796:3: ( STRING_WITH_QUOTE )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:796:5: STRING_WITH_QUOTE
			{
			STRING_WITH_QUOTE58=(Token)match(input,STRING_WITH_QUOTE,FOLLOW_STRING_WITH_QUOTE_in_string_literal2042); 

			      String str = (STRING_WITH_QUOTE58!=null?STRING_WITH_QUOTE58.getText():null);
			      str = str.substring(1, str.length()-1);
			      value = new StringLiteral(str);
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "string_literal"



	// $ANTLR start "boolean_literal"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:803:1: boolean_literal returns [BooleanLiteral value] : (t= TRUE |t= FALSE ) ;
	public final BooleanLiteral boolean_literal() throws RecognitionException {
		BooleanLiteral value = null;


		Token t=null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:804:3: ( (t= TRUE |t= FALSE ) )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:804:5: (t= TRUE |t= FALSE )
			{
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:804:5: (t= TRUE |t= FALSE )
			int alt43=2;
			int LA43_0 = input.LA(1);
			if ( (LA43_0==TRUE) ) {
				alt43=1;
			}
			else if ( (LA43_0==FALSE) ) {
				alt43=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 43, 0, input);
				throw nvae;
			}

			switch (alt43) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:804:6: t= TRUE
					{
					t=(Token)match(input,TRUE,FOLLOW_TRUE_in_boolean_literal2064); 
					}
					break;
				case 2 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:804:15: t= FALSE
					{
					t=(Token)match(input,FALSE,FOLLOW_FALSE_in_boolean_literal2070); 
					}
					break;

			}

			 value = new BooleanLiteral(Boolean.parseBoolean((t!=null?t.getText():null))); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "boolean_literal"



	// $ANTLR start "numeric_literal"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:807:1: numeric_literal returns [NumericLiteral value] : ( numeric_literal_unsigned | numeric_literal_positive | numeric_literal_negative );
	public final NumericLiteral numeric_literal() throws RecognitionException {
		NumericLiteral value = null;


		NumericLiteral numeric_literal_unsigned59 =null;
		NumericLiteral numeric_literal_positive60 =null;
		NumericLiteral numeric_literal_negative61 =null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:808:3: ( numeric_literal_unsigned | numeric_literal_positive | numeric_literal_negative )
			int alt44=3;
			switch ( input.LA(1) ) {
			case DECIMAL:
			case INTEGER:
				{
				alt44=1;
				}
				break;
			case DECIMAL_POSITIVE:
			case INTEGER_POSITIVE:
				{
				alt44=2;
				}
				break;
			case DECIMAL_NEGATIVE:
			case INTEGER_NEGATIVE:
				{
				alt44=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 44, 0, input);
				throw nvae;
			}
			switch (alt44) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:808:5: numeric_literal_unsigned
					{
					pushFollow(FOLLOW_numeric_literal_unsigned_in_numeric_literal2090);
					numeric_literal_unsigned59=numeric_literal_unsigned();
					state._fsp--;

					 value = numeric_literal_unsigned59; 
					}
					break;
				case 2 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:809:5: numeric_literal_positive
					{
					pushFollow(FOLLOW_numeric_literal_positive_in_numeric_literal2098);
					numeric_literal_positive60=numeric_literal_positive();
					state._fsp--;

					 value = numeric_literal_positive60; 
					}
					break;
				case 3 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:810:5: numeric_literal_negative
					{
					pushFollow(FOLLOW_numeric_literal_negative_in_numeric_literal2106);
					numeric_literal_negative61=numeric_literal_negative();
					state._fsp--;

					 value = numeric_literal_negative61; 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "numeric_literal"



	// $ANTLR start "numeric_literal_unsigned"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:813:1: numeric_literal_unsigned returns [NumericLiteral value] : ( INTEGER | DECIMAL );
	public final NumericLiteral numeric_literal_unsigned() throws RecognitionException {
		NumericLiteral value = null;


		Token INTEGER62=null;
		Token DECIMAL63=null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:814:3: ( INTEGER | DECIMAL )
			int alt45=2;
			int LA45_0 = input.LA(1);
			if ( (LA45_0==INTEGER) ) {
				alt45=1;
			}
			else if ( (LA45_0==DECIMAL) ) {
				alt45=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 45, 0, input);
				throw nvae;
			}

			switch (alt45) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:814:5: INTEGER
					{
					INTEGER62=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_numeric_literal_unsigned2125); 
					 value = new IntegerLiteral((INTEGER62!=null?INTEGER62.getText():null)); 
					}
					break;
				case 2 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:815:5: DECIMAL
					{
					DECIMAL63=(Token)match(input,DECIMAL,FOLLOW_DECIMAL_in_numeric_literal_unsigned2133); 
					 value = new DecimalLiteral((DECIMAL63!=null?DECIMAL63.getText():null)); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "numeric_literal_unsigned"



	// $ANTLR start "numeric_literal_positive"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:818:1: numeric_literal_positive returns [NumericLiteral value] : ( INTEGER_POSITIVE | DECIMAL_POSITIVE );
	public final NumericLiteral numeric_literal_positive() throws RecognitionException {
		NumericLiteral value = null;


		Token INTEGER_POSITIVE64=null;
		Token DECIMAL_POSITIVE65=null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:819:3: ( INTEGER_POSITIVE | DECIMAL_POSITIVE )
			int alt46=2;
			int LA46_0 = input.LA(1);
			if ( (LA46_0==INTEGER_POSITIVE) ) {
				alt46=1;
			}
			else if ( (LA46_0==DECIMAL_POSITIVE) ) {
				alt46=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 46, 0, input);
				throw nvae;
			}

			switch (alt46) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:819:5: INTEGER_POSITIVE
					{
					INTEGER_POSITIVE64=(Token)match(input,INTEGER_POSITIVE,FOLLOW_INTEGER_POSITIVE_in_numeric_literal_positive2152); 
					 value = new IntegerLiteral((INTEGER_POSITIVE64!=null?INTEGER_POSITIVE64.getText():null)); 
					}
					break;
				case 2 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:820:5: DECIMAL_POSITIVE
					{
					DECIMAL_POSITIVE65=(Token)match(input,DECIMAL_POSITIVE,FOLLOW_DECIMAL_POSITIVE_in_numeric_literal_positive2160); 
					 value = new DecimalLiteral((DECIMAL_POSITIVE65!=null?DECIMAL_POSITIVE65.getText():null)); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "numeric_literal_positive"



	// $ANTLR start "numeric_literal_negative"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:823:1: numeric_literal_negative returns [NumericLiteral value] : ( INTEGER_NEGATIVE | DECIMAL_NEGATIVE );
	public final NumericLiteral numeric_literal_negative() throws RecognitionException {
		NumericLiteral value = null;


		Token INTEGER_NEGATIVE66=null;
		Token DECIMAL_NEGATIVE67=null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:824:3: ( INTEGER_NEGATIVE | DECIMAL_NEGATIVE )
			int alt47=2;
			int LA47_0 = input.LA(1);
			if ( (LA47_0==INTEGER_NEGATIVE) ) {
				alt47=1;
			}
			else if ( (LA47_0==DECIMAL_NEGATIVE) ) {
				alt47=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 47, 0, input);
				throw nvae;
			}

			switch (alt47) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:824:5: INTEGER_NEGATIVE
					{
					INTEGER_NEGATIVE66=(Token)match(input,INTEGER_NEGATIVE,FOLLOW_INTEGER_NEGATIVE_in_numeric_literal_negative2181); 
					 value = new IntegerLiteral((INTEGER_NEGATIVE66!=null?INTEGER_NEGATIVE66.getText():null)); 
					}
					break;
				case 2 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:825:5: DECIMAL_NEGATIVE
					{
					DECIMAL_NEGATIVE67=(Token)match(input,DECIMAL_NEGATIVE,FOLLOW_DECIMAL_NEGATIVE_in_numeric_literal_negative2189); 
					 value = new DecimalLiteral((DECIMAL_NEGATIVE67!=null?DECIMAL_NEGATIVE67.getText():null)); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "numeric_literal_negative"



	// $ANTLR start "truth_value"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:828:1: truth_value returns [boolean value] : (t= TRUE |t= FALSE ) ;
	public final boolean truth_value() throws RecognitionException {
		boolean value = false;


		Token t=null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:829:3: ( (t= TRUE |t= FALSE ) )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:829:5: (t= TRUE |t= FALSE )
			{
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:829:5: (t= TRUE |t= FALSE )
			int alt48=2;
			int LA48_0 = input.LA(1);
			if ( (LA48_0==TRUE) ) {
				alt48=1;
			}
			else if ( (LA48_0==FALSE) ) {
				alt48=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 48, 0, input);
				throw nvae;
			}

			switch (alt48) {
				case 1 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:829:6: t= TRUE
					{
					t=(Token)match(input,TRUE,FOLLOW_TRUE_in_truth_value2213); 
					}
					break;
				case 2 :
					// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:829:15: t= FALSE
					{
					t=(Token)match(input,FALSE,FOLLOW_FALSE_in_truth_value2219); 
					}
					break;

			}

			 value = Boolean.getBoolean((t!=null?t.getText():null)); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "truth_value"



	// $ANTLR start "datetime_literal"
	// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:832:1: datetime_literal returns [DateTimeLiteral value] : DATETIME ;
	public final DateTimeLiteral datetime_literal() throws RecognitionException {
		DateTimeLiteral value = null;


		Token DATETIME68=null;

		try {
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:833:3: ( DATETIME )
			// /Users/johardi/Documents/Code/obdalib-parent/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/SQL99.g:833:5: DATETIME
			{
			DATETIME68=(Token)match(input,DATETIME,FOLLOW_DATETIME_in_datetime_literal2239); 
			 value = new DateTimeLiteral((DATETIME68!=null?DATETIME68.getText():null)); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "datetime_literal"

	// Delegated rules



	public static final BitSet FOLLOW_query_in_parse51 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_EOF_in_parse53 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_query_specification_in_query79 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SELECT_in_query_specification120 = new BitSet(new long[]{0x0000000010000010L,0x0000000000040800L});
	public static final BitSet FOLLOW_set_quantifier_in_query_specification122 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040800L});
	public static final BitSet FOLLOW_select_list_in_query_specification125 = new BitSet(new long[]{0x0000001000000000L});
	public static final BitSet FOLLOW_table_expression_in_query_specification127 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ALL_in_set_quantifier148 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DISTINCT_in_set_quantifier156 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_select_sublist_in_select_list184 = new BitSet(new long[]{0x0000000000100002L});
	public static final BitSet FOLLOW_COMMA_in_select_list189 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040800L});
	public static final BitSet FOLLOW_select_sublist_in_select_list193 = new BitSet(new long[]{0x0000000000100002L});
	public static final BitSet FOLLOW_derived_column_in_select_sublist216 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_table_identifier_in_qualified_asterisk234 = new BitSet(new long[]{0x4000000000000000L});
	public static final BitSet FOLLOW_PERIOD_in_qualified_asterisk236 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_ASTERISK_in_qualified_asterisk238 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_value_expression_in_derived_column262 = new BitSet(new long[]{0x0000000000000802L,0x0000000000040800L});
	public static final BitSet FOLLOW_AS_in_derived_column265 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040800L});
	public static final BitSet FOLLOW_alias_name_in_derived_column268 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_reference_value_expression_in_value_expression292 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_numeric_value_expression319 = new BitSet(new long[]{0x0000380007000000L,0x0000000000040800L});
	public static final BitSet FOLLOW_numeric_operation_in_numeric_value_expression321 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_RPAREN_in_numeric_value_expression323 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_term_in_numeric_operation338 = new BitSet(new long[]{0x8040000000000002L});
	public static final BitSet FOLLOW_PLUS_in_numeric_operation356 = new BitSet(new long[]{0x0000380007000000L,0x0000000000040800L});
	public static final BitSet FOLLOW_MINUS_in_numeric_operation360 = new BitSet(new long[]{0x0000380007000000L,0x0000000000040800L});
	public static final BitSet FOLLOW_term_in_numeric_operation373 = new BitSet(new long[]{0x8040000000000002L});
	public static final BitSet FOLLOW_factor_in_term395 = new BitSet(new long[]{0x0000000000001002L,0x0000000000000100L});
	public static final BitSet FOLLOW_ASTERISK_in_term415 = new BitSet(new long[]{0x0000380007000000L,0x0000000000040800L});
	public static final BitSet FOLLOW_SOLIDUS_in_term419 = new BitSet(new long[]{0x0000380007000000L,0x0000000000040800L});
	public static final BitSet FOLLOW_factor_in_term433 = new BitSet(new long[]{0x0000000000001002L,0x0000000000000100L});
	public static final BitSet FOLLOW_column_reference_in_factor461 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_numeric_literal_in_factor469 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_string_value_expression512 = new BitSet(new long[]{0x0000000800000000L,0x0000000000044C00L});
	public static final BitSet FOLLOW_concatenation_in_string_value_expression514 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_RPAREN_in_string_value_expression516 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_character_factor_in_concatenation535 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_CONCATENATION_in_concatenation547 = new BitSet(new long[]{0x0000000800000000L,0x0000000000044C00L});
	public static final BitSet FOLLOW_character_factor_in_concatenation560 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_column_reference_in_character_factor581 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_general_literal_in_character_factor589 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_column_reference_in_reference_value_expression613 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_table_identifier_in_column_reference635 = new BitSet(new long[]{0x4000000000000000L});
	public static final BitSet FOLLOW_PERIOD_in_column_reference637 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040800L});
	public static final BitSet FOLLOW_column_name_in_column_reference641 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_function_specification_in_collection_value_expression669 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COUNT_in_set_function_specification684 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_LPAREN_in_set_function_specification686 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_ASTERISK_in_set_function_specification688 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_RPAREN_in_set_function_specification690 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_general_set_function_in_set_function_specification698 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_function_op_in_general_set_function713 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_LPAREN_in_general_set_function715 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040800L});
	public static final BitSet FOLLOW_column_reference_in_general_set_function717 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_RPAREN_in_general_set_function719 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_AVG_in_set_function_op743 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MAX_in_set_function_op749 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MIN_in_set_function_op755 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SUM_in_set_function_op761 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_EVERY_in_set_function_op767 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ANY_in_set_function_op773 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SOME_in_set_function_op779 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COUNT_in_set_function_op785 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_literal_in_row_value_expression807 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_value_expression_in_row_value_expression815 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_numeric_literal_in_literal834 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_general_literal_in_literal842 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_from_clause_in_table_expression861 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
	public static final BitSet FOLLOW_where_clause_in_table_expression870 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FROM_in_from_clause895 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040800L});
	public static final BitSet FOLLOW_table_reference_list_in_from_clause897 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_table_reference_in_table_reference_list927 = new BitSet(new long[]{0x0000000000100002L});
	public static final BitSet FOLLOW_COMMA_in_table_reference_list944 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040800L});
	public static final BitSet FOLLOW_table_reference_in_table_reference_list948 = new BitSet(new long[]{0x0000000000100002L});
	public static final BitSet FOLLOW_table_primary_in_table_reference975 = new BitSet(new long[]{0x0001842000000002L,0x0000000000000008L});
	public static final BitSet FOLLOW_joined_table_in_table_reference984 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WHERE_in_where_clause1006 = new BitSet(new long[]{0x0004380807000000L,0x0000000000044C00L});
	public static final BitSet FOLLOW_search_condition_in_where_clause1008 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_boolean_value_expression_in_search_condition1027 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_boolean_term_in_boolean_value_expression1063 = new BitSet(new long[]{0x0400000000000002L});
	public static final BitSet FOLLOW_OR_in_boolean_value_expression1066 = new BitSet(new long[]{0x0004380807000000L,0x0000000000044C00L});
	public static final BitSet FOLLOW_boolean_term_in_boolean_value_expression1070 = new BitSet(new long[]{0x0400000000000002L});
	public static final BitSet FOLLOW_boolean_factor_in_boolean_term1093 = new BitSet(new long[]{0x0000000000000102L});
	public static final BitSet FOLLOW_AND_in_boolean_term1096 = new BitSet(new long[]{0x0004380807000000L,0x0000000000044C00L});
	public static final BitSet FOLLOW_boolean_factor_in_boolean_term1100 = new BitSet(new long[]{0x0000000000000102L});
	public static final BitSet FOLLOW_predicate_in_boolean_factor1115 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_parenthesized_boolean_value_expression_in_boolean_factor1123 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_comparison_predicate_in_predicate1141 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_null_predicate_in_predicate1149 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_parenthesized_boolean_value_expression1165 = new BitSet(new long[]{0x0004380807000000L,0x0000000000044C00L});
	public static final BitSet FOLLOW_boolean_value_expression_in_parenthesized_boolean_value_expression1169 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_RPAREN_in_parenthesized_boolean_value_expression1171 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_row_value_expression_in_comparison_predicate1194 = new BitSet(new long[]{0x0002004100000000L});
	public static final BitSet FOLLOW_comp_op_in_comparison_predicate1196 = new BitSet(new long[]{0x0000380807000000L,0x0000000000044C00L});
	public static final BitSet FOLLOW_row_value_expression_in_comparison_predicate1200 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_EQUALS_in_comp_op1219 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LESS_in_comp_op1227 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_GREATER_in_comp_op1229 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LESS_in_comp_op1237 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GREATER_in_comp_op1245 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LESS_in_comp_op1253 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_EQUALS_in_comp_op1255 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GREATER_in_comp_op1263 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_EQUALS_in_comp_op1265 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_column_reference_in_null_predicate1289 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_IS_in_null_predicate1291 = new BitSet(new long[]{0x0180000000000000L});
	public static final BitSet FOLLOW_NOT_in_null_predicate1294 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_NULL_in_null_predicate1300 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_column_reference_in_in_predicate1315 = new BitSet(new long[]{0x0080020000000000L});
	public static final BitSet FOLLOW_NOT_in_in_predicate1318 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_IN_in_in_predicate1322 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_in_predicate_value_in_in_predicate1324 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_table_subquery_in_in_predicate_value1339 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_in_predicate_value1345 = new BitSet(new long[]{0x0000380807000000L,0x0000000000044C00L});
	public static final BitSet FOLLOW_in_value_list_in_in_predicate_value1347 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_RPAREN_in_in_predicate_value1349 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_subquery_in_table_subquery1362 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_subquery1375 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_query_in_subquery1377 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_RPAREN_in_subquery1379 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_row_value_expression_in_in_value_list1394 = new BitSet(new long[]{0x0000000000100002L});
	public static final BitSet FOLLOW_COMMA_in_in_value_list1397 = new BitSet(new long[]{0x0000380807000000L,0x0000000000044C00L});
	public static final BitSet FOLLOW_row_value_expression_in_in_value_list1399 = new BitSet(new long[]{0x0000000000100002L});
	public static final BitSet FOLLOW_GROUP_in_group_by_clause1418 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_BY_in_group_by_clause1420 = new BitSet(new long[]{0x0004000000000000L,0x0000000000040800L});
	public static final BitSet FOLLOW_grouping_element_list_in_group_by_clause1422 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_grouping_element_in_grouping_element_list1448 = new BitSet(new long[]{0x0000000000100002L});
	public static final BitSet FOLLOW_COMMA_in_grouping_element_list1458 = new BitSet(new long[]{0x0004000000000000L,0x0000000000040800L});
	public static final BitSet FOLLOW_grouping_element_in_grouping_element_list1462 = new BitSet(new long[]{0x0000000000100002L});
	public static final BitSet FOLLOW_grouping_column_reference_in_grouping_element1490 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_grouping_element1498 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040800L});
	public static final BitSet FOLLOW_grouping_column_reference_list_in_grouping_element1500 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_RPAREN_in_grouping_element1502 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_column_reference_in_grouping_column_reference1523 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_column_reference_in_grouping_column_reference_list1551 = new BitSet(new long[]{0x0000000000100002L});
	public static final BitSet FOLLOW_COMMA_in_grouping_column_reference_list1560 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040800L});
	public static final BitSet FOLLOW_column_reference_in_grouping_column_reference_list1564 = new BitSet(new long[]{0x0000000000100002L});
	public static final BitSet FOLLOW_join_type_in_joined_table1594 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_JOIN_in_joined_table1600 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040800L});
	public static final BitSet FOLLOW_table_reference_in_joined_table1602 = new BitSet(new long[]{0x0200000000000000L});
	public static final BitSet FOLLOW_join_specification_in_joined_table1604 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INNER_in_join_type1629 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_outer_join_type_in_join_type1637 = new BitSet(new long[]{0x1000000000000002L});
	public static final BitSet FOLLOW_OUTER_in_join_type1640 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LEFT_in_outer_join_type1665 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_RIGHT_in_outer_join_type1673 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FULL_in_outer_join_type1681 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_join_condition_in_join_specification1700 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ON_in_join_condition1720 = new BitSet(new long[]{0x0004380807000000L,0x0000000000044C00L});
	public static final BitSet FOLLOW_search_condition_in_join_condition1722 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_USING_in_named_columns_join1737 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_LPAREN_in_named_columns_join1739 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040800L});
	public static final BitSet FOLLOW_join_column_list_in_named_columns_join1741 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_RPAREN_in_named_columns_join1743 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_column_name_in_join_column_list1756 = new BitSet(new long[]{0x0000000000100002L});
	public static final BitSet FOLLOW_COMMA_in_join_column_list1759 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040800L});
	public static final BitSet FOLLOW_column_name_in_join_column_list1761 = new BitSet(new long[]{0x0000000000100002L});
	public static final BitSet FOLLOW_table_name_in_table_primary1781 = new BitSet(new long[]{0x0000000000000802L,0x0000000000040800L});
	public static final BitSet FOLLOW_AS_in_table_primary1788 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040800L});
	public static final BitSet FOLLOW_alias_name_in_table_primary1791 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_schema_name_in_table_name1819 = new BitSet(new long[]{0x4000000000000000L});
	public static final BitSet FOLLOW_PERIOD_in_table_name1821 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040800L});
	public static final BitSet FOLLOW_table_identifier_in_table_name1825 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_alias_name1846 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_table_subquery_in_derived_table1862 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_table_identifier1883 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_schema_name1904 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_column_name1927 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_regular_identifier_in_identifier1951 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_delimited_identifier_in_identifier1957 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VARNAME_in_regular_identifier1977 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_WITH_QUOTE_DOUBLE_in_delimited_identifier1996 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_string_literal_in_general_literal2015 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_boolean_literal_in_general_literal2023 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_WITH_QUOTE_in_string_literal2042 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TRUE_in_boolean_literal2064 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FALSE_in_boolean_literal2070 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_numeric_literal_unsigned_in_numeric_literal2090 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_numeric_literal_positive_in_numeric_literal2098 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_numeric_literal_negative_in_numeric_literal2106 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTEGER_in_numeric_literal_unsigned2125 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DECIMAL_in_numeric_literal_unsigned2133 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTEGER_POSITIVE_in_numeric_literal_positive2152 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DECIMAL_POSITIVE_in_numeric_literal_positive2160 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTEGER_NEGATIVE_in_numeric_literal_negative2181 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DECIMAL_NEGATIVE_in_numeric_literal_negative2189 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TRUE_in_truth_value2213 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FALSE_in_truth_value2219 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DATETIME_in_datetime_literal2239 = new BitSet(new long[]{0x0000000000000002L});
}
