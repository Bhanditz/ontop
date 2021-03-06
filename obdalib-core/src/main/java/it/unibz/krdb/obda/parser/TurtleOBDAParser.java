// $ANTLR 3.5 /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g 2013-09-02 21:10:10

package it.unibz.krdb.obda.parser;

import it.unibz.krdb.obda.model.CQIE;
import it.unibz.krdb.obda.model.Constant;
import it.unibz.krdb.obda.model.Function;
import it.unibz.krdb.obda.model.Term;
import it.unibz.krdb.obda.model.OBDADataFactory;
import it.unibz.krdb.obda.model.OBDALibConstants;
import it.unibz.krdb.obda.model.Predicate;
import it.unibz.krdb.obda.model.URIConstant;
import it.unibz.krdb.obda.model.ValueConstant;
import it.unibz.krdb.obda.model.Variable;
import it.unibz.krdb.obda.model.Predicate.COL_TYPE;
import it.unibz.krdb.obda.model.impl.OBDADataFactoryImpl;
import it.unibz.krdb.obda.model.impl.OBDAVocabulary;
import it.unibz.krdb.obda.utils.QueryUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class TurtleOBDAParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ALPHA", "ALPHANUM", "AMPERSAND", 
		"APOSTROPHE", "ASTERISK", "AT", "BACKSLASH", "BASE", "BLANK", "BLANK_PREFIX", 
		"CARET", "CHAR", "COLON", "COMMA", "DECIMAL", "DECIMAL_NEGATIVE", "DECIMAL_POSITIVE", 
		"DIGIT", "DOLLAR", "DOUBLE", "DOUBLE_NEGATIVE", "DOUBLE_POSITIVE", "DOUBLE_SLASH", 
		"ECHAR", "EQUALS", "EXCLAMATION", "FALSE", "GREATER", "HASH", "ID", "ID_CORE", 
		"ID_START", "INTEGER", "INTEGER_NEGATIVE", "INTEGER_POSITIVE", "LCR_BRACKET", 
		"LESS", "LPAREN", "LSQ_BRACKET", "LTSIGN", "MINUS", "NAMESPACE", "NAME_CHAR", 
		"NAME_START_CHAR", "NCNAME", "NCNAME_EXT", "PERCENT", "PERIOD", "PLUS", 
		"PREFIX", "PREFIXED_NAME", "QUESTION", "QUOTE_DOUBLE", "QUOTE_SINGLE", 
		"RCR_BRACKET", "REFERENCE", "RPAREN", "RSQ_BRACKET", "RTSIGN", "SCHEMA", 
		"SEMI", "SLASH", "STRING_URI", "STRING_WITH_BRACKET", "STRING_WITH_CURLY_BRACKET", 
		"STRING_WITH_QUOTE", "STRING_WITH_QUOTE_DOUBLE", "TILDE", "TRUE", "UNDERSCORE", 
		"URI_PATH", "VARNAME", "WS", "'a'"
	};
	public static final int EOF=-1;
	public static final int T__77=77;
	public static final int ALPHA=4;
	public static final int ALPHANUM=5;
	public static final int AMPERSAND=6;
	public static final int APOSTROPHE=7;
	public static final int ASTERISK=8;
	public static final int AT=9;
	public static final int BACKSLASH=10;
	public static final int BASE=11;
	public static final int BLANK=12;
	public static final int BLANK_PREFIX=13;
	public static final int CARET=14;
	public static final int CHAR=15;
	public static final int COLON=16;
	public static final int COMMA=17;
	public static final int DECIMAL=18;
	public static final int DECIMAL_NEGATIVE=19;
	public static final int DECIMAL_POSITIVE=20;
	public static final int DIGIT=21;
	public static final int DOLLAR=22;
	public static final int DOUBLE=23;
	public static final int DOUBLE_NEGATIVE=24;
	public static final int DOUBLE_POSITIVE=25;
	public static final int DOUBLE_SLASH=26;
	public static final int ECHAR=27;
	public static final int EQUALS=28;
	public static final int EXCLAMATION=29;
	public static final int FALSE=30;
	public static final int GREATER=31;
	public static final int HASH=32;
	public static final int ID=33;
	public static final int ID_CORE=34;
	public static final int ID_START=35;
	public static final int INTEGER=36;
	public static final int INTEGER_NEGATIVE=37;
	public static final int INTEGER_POSITIVE=38;
	public static final int LCR_BRACKET=39;
	public static final int LESS=40;
	public static final int LPAREN=41;
	public static final int LSQ_BRACKET=42;
	public static final int LTSIGN=43;
	public static final int MINUS=44;
	public static final int NAMESPACE=45;
	public static final int NAME_CHAR=46;
	public static final int NAME_START_CHAR=47;
	public static final int NCNAME=48;
	public static final int NCNAME_EXT=49;
	public static final int PERCENT=50;
	public static final int PERIOD=51;
	public static final int PLUS=52;
	public static final int PREFIX=53;
	public static final int PREFIXED_NAME=54;
	public static final int QUESTION=55;
	public static final int QUOTE_DOUBLE=56;
	public static final int QUOTE_SINGLE=57;
	public static final int RCR_BRACKET=58;
	public static final int REFERENCE=59;
	public static final int RPAREN=60;
	public static final int RSQ_BRACKET=61;
	public static final int RTSIGN=62;
	public static final int SCHEMA=63;
	public static final int SEMI=64;
	public static final int SLASH=65;
	public static final int STRING_URI=66;
	public static final int STRING_WITH_BRACKET=67;
	public static final int STRING_WITH_CURLY_BRACKET=68;
	public static final int STRING_WITH_QUOTE=69;
	public static final int STRING_WITH_QUOTE_DOUBLE=70;
	public static final int TILDE=71;
	public static final int TRUE=72;
	public static final int UNDERSCORE=73;
	public static final int URI_PATH=74;
	public static final int VARNAME=75;
	public static final int WS=76;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public TurtleOBDAParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public TurtleOBDAParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return TurtleOBDAParser.tokenNames; }
	@Override public String getGrammarFileName() { return "/Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g"; }


	/** Map of directives */
	private HashMap<String, String> directives = new HashMap<String, String>();

	/** The current subject term */
	private Term currentSubject;

	/** All variables */
	private Set<Term> variableSet = new HashSet<Term>();

	/** A factory to construct the predicates and terms */
	private static final OBDADataFactory dfac = OBDADataFactoryImpl.getInstance();


	private String error = "";

	public String getError() {
	   return error;
	}

	protected void mismatch(IntStream input, int ttype, BitSet follow) throws RecognitionException {
	   throw new MismatchedTokenException(ttype, input);
	}

	public Object recoverFromMismatchedSet(IntStream input, RecognitionException e, BitSet follow) throws RecognitionException {
	   throw e;
	}

	@Override
	public void recover(IntStream input, RecognitionException re) {
	   throw new RuntimeException(error);
	}

	@Override
	public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
	   String hdr = getErrorHeader(e);
	   String msg = getErrorMessage(e, tokenNames);
	   emitErrorMessage("Syntax error: " + msg + " Location: " + hdr);
	}

	@Override
	public void emitErrorMessage(String msg) {
	   error = msg;
	}
	    
	public Object recoverFromMismatchedTokenrecoverFromMismatchedToken(IntStream input, int ttype, BitSet follow) throws RecognitionException {
	   throw new RecognitionException(input);
	}

	private String removeBrackets(String text) {
	   return text.substring(1, text.length()-1);
	}

	private Term construct(String text) {
	   Term toReturn = null;
	   final String PLACEHOLDER = "{}"; 
	   List<Term> terms = new LinkedList<Term>();
	   List<FormatString> tokens = parse(text);
	   int size = tokens.size();
	   if (size == 1) {
	      FormatString token = tokens.get(0);
	      if (token instanceof FixedString) {
	          ValueConstant uriTemplate = dfac.getConstantLiteral(token.toString()); // a single URI template
	          toReturn = dfac.getFunction(dfac.getUriTemplatePredicate(1), uriTemplate);
	      } else if (token instanceof ColumnString) {
	         ValueConstant uriTemplate = dfac.getConstantLiteral(PLACEHOLDER); // a single URI template
	         Variable column = dfac.getVariable(token.toString());
	         terms.add(0, uriTemplate);
	         terms.add(column);
	         toReturn = dfac.getFunction(dfac.getUriTemplatePredicate(terms.size()), terms);
	      }
	   } else {
	      StringBuilder sb = new StringBuilder();
	      for(FormatString token : tokens) {
	         if (token instanceof FixedString) { // if part of URI template
	            sb.append(token.toString());
	         } else if (token instanceof ColumnString) {
	            sb.append(PLACEHOLDER);
	            Variable column = dfac.getVariable(token.toString());
	            terms.add(column);
	         }
	      }
	      ValueConstant uriTemplate = dfac.getConstantLiteral(sb.toString()); // complete URI template
	      terms.add(0, uriTemplate);
	      toReturn = dfac.getFunction(dfac.getUriTemplatePredicate(terms.size()), terms);
	   }
	   return toReturn;
	}

	// Column placeholder pattern
	private static final String formatSpecifier = "\\{([\\w.]+)?\\}";
	private static Pattern chPattern = Pattern.compile(formatSpecifier);

	private List<FormatString> parse(String text) {
	   List<FormatString> toReturn = new ArrayList<FormatString>();
	   Matcher m = chPattern.matcher(text);
	   int i = 0;
	   while (i < text.length()) {
	      if (m.find(i)) {
	         if (m.start() != i) {
	            toReturn.add(new FixedString(text.substring(i, m.start())));
	         }
	         String value = m.group(1);
	         toReturn.add(new ColumnString(value));
	         i = m.end();
	      }
	      else {
	         toReturn.add(new FixedString(text.substring(i)));
	         break;
	      }
	   }
	   return toReturn;
	}

	private interface FormatString {
	   int index();
	   String toString();
	}

	private class FixedString implements FormatString {
	   private String s;
	   FixedString(String s) { this.s = s; }
	   @Override public int index() { return -1; }  // flag code for fixed string
	   @Override public String toString() { return s; }
	}

	private class ColumnString implements FormatString {
	   private String s;
	   ColumnString(String s) { this.s = s; }
	   @Override public int index() { return 0; }  // flag code for column string
	   @Override public String toString() { return s; }
	}

	/**
	 * This methods construct an atom from a triple 
	 * 
	 * For the input (subject, pred, object), the result is 
	 * <ul>
	 *  <li> object(subject), if pred == rdf:type and subject is grounded ; </li>
	 *  <li> predicate(subject, object), if pred != rdf:type and predicate is grounded ; </li>
	 *  <li> triple(subject, pred, object), otherwise (it is a higher order atom). </li>
	 * </ul>
	 */
	private Function makeAtom(Term subject, Term pred, Term object) {
	     Function atom = null;
	      
	       if (pred instanceof Constant && ((Constant) pred).getValue().equals(OBDAVocabulary.RDF_TYPE)) {
	             if (object instanceof  Function) {
	                  if(QueryUtils.isGrounded(object)) {
	                      ValueConstant c = ((ValueConstant) ((Function) object).getTerm(0));  // it has to be a URI constant
	                      Predicate predicate = dfac.getClassPredicate(c.getValue());
	                      atom = dfac.getFunction(predicate, subject);
	                  } else {
	                        Predicate uriPredicate = dfac.getPredicate(OBDAVocabulary.QUEST_URI, 1);
	                        Term uriOfPred = dfac.getFunction(uriPredicate, pred);
	                        atom = dfac.getFunction(OBDAVocabulary.QUEST_TRIPLE_PRED, subject, uriOfPred, object);                  }
	             } else if (object instanceof  Variable){
	                  Predicate uriPredicate = dfac.getPredicate(OBDAVocabulary.QUEST_URI, 1);
	                  Term uriOfPred = dfac.getFunction(uriPredicate, pred);
	                  Term uriOfObject = dfac.getFunction(uriPredicate, object);
	                  atom = dfac.getFunction(OBDAVocabulary.QUEST_TRIPLE_PRED, subject, uriOfPred,  uriOfObject);
	              } else {
	                  throw new IllegalArgumentException("parser cannot handle object " + object);  
	              }
	        } else if( ! QueryUtils.isGrounded(pred) ){
	             atom = dfac.getFunction(OBDAVocabulary.QUEST_TRIPLE_PRED, subject, pred,  object);
	        } else {
	             //Predicate predicate = dfac.getPredicate(pred.toString(), 2); // the data type cannot be determined here!
	             Predicate predicate;
	             if(pred instanceof Function){
	                  ValueConstant pr = (ValueConstant) ((Function) pred).getTerm(0);
	                  predicate = dfac.getPredicate(pr.getValue(), 2);
	             } else {
	                  throw new IllegalArgumentException("predicate should be a URI Function");
	             }
	             atom = dfac.getFunction(predicate, subject, object);
	       }
	        return atom;
	  }





	// $ANTLR start "parse"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:295:1: parse returns [CQIE value] : ( directiveStatement )* t1= triplesStatement (t2= triplesStatement )* EOF ;
	public final CQIE parse() throws RecognitionException {
		CQIE value = null;


		List<Function> t1 =null;
		List<Function> t2 =null;

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:296:3: ( ( directiveStatement )* t1= triplesStatement (t2= triplesStatement )* EOF )
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:296:5: ( directiveStatement )* t1= triplesStatement (t2= triplesStatement )* EOF
			{
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:296:5: ( directiveStatement )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==AT) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:296:5: directiveStatement
					{
					pushFollow(FOLLOW_directiveStatement_in_parse52);
					directiveStatement();
					state._fsp--;

					}
					break;

				default :
					break loop1;
				}
			}

			pushFollow(FOLLOW_triplesStatement_in_parse61);
			t1=triplesStatement();
			state._fsp--;


			      int arity = variableSet.size();
			      List<Term> distinguishVariables = new ArrayList<Term>(variableSet);
			      Function head = dfac.getFunction(dfac.getPredicate(OBDALibConstants.QUERY_HEAD, arity), distinguishVariables);
			      
			      // Create a new rule
			      List<Function> triples = t1;
			      value = dfac.getCQIE(head, triples);
			    
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:306:5: (t2= triplesStatement )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==PREFIXED_NAME||(LA2_0 >= STRING_WITH_BRACKET && LA2_0 <= STRING_WITH_CURLY_BRACKET)) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:306:6: t2= triplesStatement
					{
					pushFollow(FOLLOW_triplesStatement_in_parse72);
					t2=triplesStatement();
					state._fsp--;

					}
					break;

				default :
					break loop2;
				}
			}

			match(input,EOF,FOLLOW_EOF_in_parse76); 

			      List<Function> additionalTriples = t2;
			      if (additionalTriples != null) {
			        // If there are additional triple statements then just add to the existing body
			        List<Function> existingBody = value.getBody();
			        existingBody.addAll(additionalTriples);
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
	// $ANTLR end "parse"



	// $ANTLR start "directiveStatement"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:316:1: directiveStatement : directive PERIOD ;
	public final void directiveStatement() throws RecognitionException {
		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:317:3: ( directive PERIOD )
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:317:5: directive PERIOD
			{
			pushFollow(FOLLOW_directive_in_directiveStatement91);
			directive();
			state._fsp--;

			match(input,PERIOD,FOLLOW_PERIOD_in_directiveStatement93); 
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
	// $ANTLR end "directiveStatement"



	// $ANTLR start "triplesStatement"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:320:1: triplesStatement returns [List<Function> value] : triples ( WS )* PERIOD ;
	public final List<Function> triplesStatement() throws RecognitionException {
		List<Function> value = null;


		List<Function> triples1 =null;

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:321:3: ( triples ( WS )* PERIOD )
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:321:5: triples ( WS )* PERIOD
			{
			pushFollow(FOLLOW_triples_in_triplesStatement110);
			triples1=triples();
			state._fsp--;

			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:321:13: ( WS )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( (LA3_0==WS) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:321:13: WS
					{
					match(input,WS,FOLLOW_WS_in_triplesStatement112); 
					}
					break;

				default :
					break loop3;
				}
			}

			match(input,PERIOD,FOLLOW_PERIOD_in_triplesStatement115); 
			 value = triples1; 
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
	// $ANTLR end "triplesStatement"



	// $ANTLR start "directive"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:324:1: directive : ( base | prefixID );
	public final void directive() throws RecognitionException {
		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:325:3: ( base | prefixID )
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0==AT) ) {
				int LA4_1 = input.LA(2);
				if ( (LA4_1==BASE) ) {
					alt4=1;
				}
				else if ( (LA4_1==PREFIX) ) {
					alt4=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 4, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}

			switch (alt4) {
				case 1 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:325:5: base
					{
					pushFollow(FOLLOW_base_in_directive130);
					base();
					state._fsp--;

					}
					break;
				case 2 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:326:5: prefixID
					{
					pushFollow(FOLLOW_prefixID_in_directive136);
					prefixID();
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
	// $ANTLR end "directive"



	// $ANTLR start "base"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:329:1: base : AT BASE uriref ;
	public final void base() throws RecognitionException {
		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:330:3: ( AT BASE uriref )
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:330:5: AT BASE uriref
			{
			match(input,AT,FOLLOW_AT_in_base149); 
			match(input,BASE,FOLLOW_BASE_in_base151); 
			pushFollow(FOLLOW_uriref_in_base153);
			uriref();
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
	// $ANTLR end "base"



	// $ANTLR start "prefixID"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:333:1: prefixID : AT PREFIX ( namespace | defaultNamespace ) uriref ;
	public final void prefixID() throws RecognitionException {
		ParserRuleReturnScope namespace2 =null;
		ParserRuleReturnScope defaultNamespace3 =null;
		String uriref4 =null;


		  String prefix = "";

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:337:3: ( AT PREFIX ( namespace | defaultNamespace ) uriref )
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:337:5: AT PREFIX ( namespace | defaultNamespace ) uriref
			{
			match(input,AT,FOLLOW_AT_in_prefixID171); 
			match(input,PREFIX,FOLLOW_PREFIX_in_prefixID173); 
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:337:15: ( namespace | defaultNamespace )
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0==NAMESPACE) ) {
				alt5=1;
			}
			else if ( (LA5_0==COLON) ) {
				alt5=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 5, 0, input);
				throw nvae;
			}

			switch (alt5) {
				case 1 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:337:16: namespace
					{
					pushFollow(FOLLOW_namespace_in_prefixID176);
					namespace2=namespace();
					state._fsp--;

					 prefix = (namespace2!=null?input.toString(namespace2.start,namespace2.stop):null); 
					}
					break;
				case 2 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:337:58: defaultNamespace
					{
					pushFollow(FOLLOW_defaultNamespace_in_prefixID182);
					defaultNamespace3=defaultNamespace();
					state._fsp--;

					 prefix = (defaultNamespace3!=null?input.toString(defaultNamespace3.start,defaultNamespace3.stop):null); 
					}
					break;

			}

			pushFollow(FOLLOW_uriref_in_prefixID187);
			uriref4=uriref();
			state._fsp--;


			      String uriref = uriref4;
			      directives.put(prefix.substring(0, prefix.length()-1), uriref); // remove the end colon
			    
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
	// $ANTLR end "prefixID"



	// $ANTLR start "triples"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:343:1: triples returns [List<Function> value] : subject predicateObjectList ;
	public final List<Function> triples() throws RecognitionException {
		List<Function> value = null;


		Term subject5 =null;
		List<Function> predicateObjectList6 =null;

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:344:3: ( subject predicateObjectList )
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:344:5: subject predicateObjectList
			{
			pushFollow(FOLLOW_subject_in_triples206);
			subject5=subject();
			state._fsp--;

			 currentSubject = subject5; 
			pushFollow(FOLLOW_predicateObjectList_in_triples210);
			predicateObjectList6=predicateObjectList();
			state._fsp--;


			      value = predicateObjectList6;
			    
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
	// $ANTLR end "triples"



	// $ANTLR start "predicateObjectList"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:349:1: predicateObjectList returns [List<Function> value] : v1= verb l1= objectList ( SEMI v2= verb l2= objectList )* ;
	public final List<Function> predicateObjectList() throws RecognitionException {
		List<Function> value = null;


		Term v1 =null;
		List<Term> l1 =null;
		Term v2 =null;
		List<Term> l2 =null;


		   value = new LinkedList<Function>();

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:353:3: (v1= verb l1= objectList ( SEMI v2= verb l2= objectList )* )
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:353:5: v1= verb l1= objectList ( SEMI v2= verb l2= objectList )*
			{
			pushFollow(FOLLOW_verb_in_predicateObjectList236);
			v1=verb();
			state._fsp--;

			pushFollow(FOLLOW_objectList_in_predicateObjectList242);
			l1=objectList();
			state._fsp--;


			      for (Term object : l1) {
			        Function atom = makeAtom(currentSubject, v1, object);
			        value.add(atom);
			      }
			    
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:359:5: ( SEMI v2= verb l2= objectList )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( (LA6_0==SEMI) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:359:6: SEMI v2= verb l2= objectList
					{
					match(input,SEMI,FOLLOW_SEMI_in_predicateObjectList251); 
					pushFollow(FOLLOW_verb_in_predicateObjectList255);
					v2=verb();
					state._fsp--;

					pushFollow(FOLLOW_objectList_in_predicateObjectList259);
					l2=objectList();
					state._fsp--;


					      for (Term object : l2) {
					        Function atom = makeAtom(currentSubject, v2, object);
					        value.add(atom);
					      }
					    
					}
					break;

				default :
					break loop6;
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
	// $ANTLR end "predicateObjectList"



	// $ANTLR start "verb"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:368:1: verb returns [Term value] : ( predicate | 'a' );
	public final Term verb() throws RecognitionException {
		Term value = null;


		Term predicate7 =null;

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:369:3: ( predicate | 'a' )
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==PREFIXED_NAME||LA7_0==STRING_WITH_BRACKET) ) {
				alt7=1;
			}
			else if ( (LA7_0==77) ) {
				alt7=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}

			switch (alt7) {
				case 1 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:369:5: predicate
					{
					pushFollow(FOLLOW_predicate_in_verb283);
					predicate7=predicate();
					state._fsp--;

					 value = predicate7; 
					}
					break;
				case 2 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:370:5: 'a'
					{
					match(input,77,FOLLOW_77_in_verb291); 
					value = dfac.getConstantLiteral(OBDAVocabulary.RDF_TYPE); 
					  //value = OBDAVocabulary.RDF_TYPE; 
					  
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
	// $ANTLR end "verb"



	// $ANTLR start "objectList"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:375:1: objectList returns [List<Term> value] : o1= object ( COMMA o2= object )* ;
	public final List<Term> objectList() throws RecognitionException {
		List<Term> value = null;


		Term o1 =null;
		Term o2 =null;


		  value = new ArrayList<Term>();

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:379:3: (o1= object ( COMMA o2= object )* )
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:379:5: o1= object ( COMMA o2= object )*
			{
			pushFollow(FOLLOW_object_in_objectList317);
			o1=object();
			state._fsp--;

			 value.add(o1); 
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:379:42: ( COMMA o2= object )*
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( (LA8_0==COMMA) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:379:43: COMMA o2= object
					{
					match(input,COMMA,FOLLOW_COMMA_in_objectList322); 
					pushFollow(FOLLOW_object_in_objectList326);
					o2=object();
					state._fsp--;

					 value.add(o2); 
					}
					break;

				default :
					break loop8;
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
	// $ANTLR end "objectList"



	// $ANTLR start "subject"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:382:1: subject returns [Term value] : ( resource | variable );
	public final Term subject() throws RecognitionException {
		Term value = null;


		Term resource8 =null;
		Variable variable9 =null;

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:383:3: ( resource | variable )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==PREFIXED_NAME||LA9_0==STRING_WITH_BRACKET) ) {
				alt9=1;
			}
			else if ( (LA9_0==STRING_WITH_CURLY_BRACKET) ) {
				alt9=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:383:5: resource
					{
					pushFollow(FOLLOW_resource_in_subject348);
					resource8=resource();
					state._fsp--;

					 value = resource8; 
					}
					break;
				case 2 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:384:5: variable
					{
					pushFollow(FOLLOW_variable_in_subject356);
					variable9=variable();
					state._fsp--;

					 value = variable9; 
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
	// $ANTLR end "subject"



	// $ANTLR start "predicate"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:389:1: predicate returns [Term value] : resource ;
	public final Term predicate() throws RecognitionException {
		Term value = null;


		Term resource10 =null;

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:390:3: ( resource )
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:390:5: resource
			{
			pushFollow(FOLLOW_resource_in_predicate377);
			resource10=resource();
			state._fsp--;


			  	value = resource10; 
			//      Term nl = resource10;
			//      if (nl instanceof URIConstant) {
			//        URIConstant c = (URIConstant) nl;
			//        value = c.getValue();
			//      } else {
			//        throw new RuntimeException("Unsupported predicate syntax: " + nl.toString());
			//      }
			    
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



	// $ANTLR start "object"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:402:1: object returns [Term value] : ( resource | literal | typedLiteral | variable );
	public final Term object() throws RecognitionException {
		Term value = null;


		Term resource11 =null;
		Term literal12 =null;
		Function typedLiteral13 =null;
		Variable variable14 =null;

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:403:3: ( resource | literal | typedLiteral | variable )
			int alt10=4;
			switch ( input.LA(1) ) {
			case PREFIXED_NAME:
			case STRING_WITH_BRACKET:
				{
				alt10=1;
				}
				break;
			case DECIMAL:
			case DECIMAL_NEGATIVE:
			case DECIMAL_POSITIVE:
			case DOUBLE:
			case DOUBLE_NEGATIVE:
			case DOUBLE_POSITIVE:
			case FALSE:
			case INTEGER:
			case INTEGER_NEGATIVE:
			case INTEGER_POSITIVE:
			case STRING_WITH_QUOTE_DOUBLE:
			case TRUE:
				{
				alt10=2;
				}
				break;
			case STRING_WITH_CURLY_BRACKET:
				{
				int LA10_3 = input.LA(2);
				if ( (LA10_3==AT||LA10_3==REFERENCE) ) {
					alt10=3;
				}
				else if ( (LA10_3==COMMA||LA10_3==PERIOD||LA10_3==SEMI||LA10_3==WS) ) {
					alt10=4;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 10, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}
			switch (alt10) {
				case 1 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:403:5: resource
					{
					pushFollow(FOLLOW_resource_in_object396);
					resource11=resource();
					state._fsp--;

					 value = resource11; 
					}
					break;
				case 2 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:404:5: literal
					{
					pushFollow(FOLLOW_literal_in_object404);
					literal12=literal();
					state._fsp--;

					 value = literal12; 
					}
					break;
				case 3 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:405:5: typedLiteral
					{
					pushFollow(FOLLOW_typedLiteral_in_object413);
					typedLiteral13=typedLiteral();
					state._fsp--;

					 value = typedLiteral13; 
					}
					break;
				case 4 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:406:5: variable
					{
					pushFollow(FOLLOW_variable_in_object421);
					variable14=variable();
					state._fsp--;

					 value = variable14; 
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
	// $ANTLR end "object"



	// $ANTLR start "resource"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:410:1: resource returns [Term value] : ( uriref | qname );
	public final Term resource() throws RecognitionException {
		Term value = null;


		String uriref15 =null;
		String qname16 =null;

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:411:4: ( uriref | qname )
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( (LA11_0==STRING_WITH_BRACKET) ) {
				alt11=1;
			}
			else if ( (LA11_0==PREFIXED_NAME) ) {
				alt11=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}

			switch (alt11) {
				case 1 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:411:6: uriref
					{
					pushFollow(FOLLOW_uriref_in_resource442);
					uriref15=uriref();
					state._fsp--;

					 value = construct(uriref15); 
					}
					break;
				case 2 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:412:6: qname
					{
					pushFollow(FOLLOW_qname_in_resource451);
					qname16=qname();
					state._fsp--;

					 value = construct(qname16); 
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
	// $ANTLR end "resource"



	// $ANTLR start "uriref"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:417:1: uriref returns [String value] : STRING_WITH_BRACKET ;
	public final String uriref() throws RecognitionException {
		String value = null;


		Token STRING_WITH_BRACKET17=null;

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:418:3: ( STRING_WITH_BRACKET )
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:418:5: STRING_WITH_BRACKET
			{
			STRING_WITH_BRACKET17=(Token)match(input,STRING_WITH_BRACKET,FOLLOW_STRING_WITH_BRACKET_in_uriref476); 
			 value = removeBrackets((STRING_WITH_BRACKET17!=null?STRING_WITH_BRACKET17.getText():null)); 
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
	// $ANTLR end "uriref"



	// $ANTLR start "qname"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:421:1: qname returns [String value] : PREFIXED_NAME ;
	public final String qname() throws RecognitionException {
		String value = null;


		Token PREFIXED_NAME18=null;

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:422:3: ( PREFIXED_NAME )
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:422:5: PREFIXED_NAME
			{
			PREFIXED_NAME18=(Token)match(input,PREFIXED_NAME,FOLLOW_PREFIXED_NAME_in_qname495); 

			      String[] tokens = (PREFIXED_NAME18!=null?PREFIXED_NAME18.getText():null).split(":", 2);
			      String uri = directives.get(tokens[0]);  // the first token is the prefix
			      value = uri + tokens[1];  // the second token is the local name
			    
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
	// $ANTLR end "qname"



	// $ANTLR start "blank"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:429:1: blank : ( nodeID | BLANK );
	public final void blank() throws RecognitionException {
		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:430:3: ( nodeID | BLANK )
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0==BLANK_PREFIX) ) {
				alt12=1;
			}
			else if ( (LA12_0==BLANK) ) {
				alt12=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 12, 0, input);
				throw nvae;
			}

			switch (alt12) {
				case 1 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:430:5: nodeID
					{
					pushFollow(FOLLOW_nodeID_in_blank510);
					nodeID();
					state._fsp--;

					}
					break;
				case 2 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:431:5: BLANK
					{
					match(input,BLANK,FOLLOW_BLANK_in_blank516); 
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
	// $ANTLR end "blank"



	// $ANTLR start "variable"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:434:1: variable returns [Variable value] : STRING_WITH_CURLY_BRACKET ;
	public final Variable variable() throws RecognitionException {
		Variable value = null;


		Token STRING_WITH_CURLY_BRACKET19=null;

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:435:3: ( STRING_WITH_CURLY_BRACKET )
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:435:5: STRING_WITH_CURLY_BRACKET
			{
			STRING_WITH_CURLY_BRACKET19=(Token)match(input,STRING_WITH_CURLY_BRACKET,FOLLOW_STRING_WITH_CURLY_BRACKET_in_variable533); 

			      value = dfac.getVariable(removeBrackets((STRING_WITH_CURLY_BRACKET19!=null?STRING_WITH_CURLY_BRACKET19.getText():null)));
			      variableSet.add(value);
			    
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
	// $ANTLR end "variable"



	// $ANTLR start "function"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:441:1: function returns [Function value] : resource LPAREN terms RPAREN ;
	public final Function function() throws RecognitionException {
		Function value = null;


		Term resource20 =null;
		Vector<Term> terms21 =null;

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:442:3: ( resource LPAREN terms RPAREN )
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:442:5: resource LPAREN terms RPAREN
			{
			pushFollow(FOLLOW_resource_in_function554);
			resource20=resource();
			state._fsp--;

			match(input,LPAREN,FOLLOW_LPAREN_in_function556); 
			pushFollow(FOLLOW_terms_in_function558);
			terms21=terms();
			state._fsp--;

			match(input,RPAREN,FOLLOW_RPAREN_in_function560); 

			      String functionName = resource20.toString();
			      int arity = terms21.size();
			      Predicate functionSymbol = dfac.getPredicate(functionName, arity);
			      value = dfac.getFunction(functionSymbol, terms21);
			    
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
	// $ANTLR end "function"



	// $ANTLR start "typedLiteral"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:450:1: typedLiteral returns [Function value] : ( variable AT language | variable REFERENCE resource );
	public final Function typedLiteral() throws RecognitionException {
		Function value = null;


		Variable variable22 =null;
		Term language23 =null;
		Variable variable24 =null;
		Term resource25 =null;

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:451:3: ( variable AT language | variable REFERENCE resource )
			int alt13=2;
			int LA13_0 = input.LA(1);
			if ( (LA13_0==STRING_WITH_CURLY_BRACKET) ) {
				int LA13_1 = input.LA(2);
				if ( (LA13_1==AT) ) {
					alt13=1;
				}
				else if ( (LA13_1==REFERENCE) ) {
					alt13=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 13, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 13, 0, input);
				throw nvae;
			}

			switch (alt13) {
				case 1 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:451:5: variable AT language
					{
					pushFollow(FOLLOW_variable_in_typedLiteral579);
					variable22=variable();
					state._fsp--;

					match(input,AT,FOLLOW_AT_in_typedLiteral581); 
					pushFollow(FOLLOW_language_in_typedLiteral583);
					language23=language();
					state._fsp--;


					      Predicate functionSymbol = dfac.getDataTypePredicateLiteralLang();
					      Variable var = variable22;
					      Term lang = language23;   
					      value = dfac.getFunction(functionSymbol, var, lang);
					    
					}
					break;
				case 2 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:457:5: variable REFERENCE resource
					{
					pushFollow(FOLLOW_variable_in_typedLiteral591);
					variable24=variable();
					state._fsp--;

					match(input,REFERENCE,FOLLOW_REFERENCE_in_typedLiteral593); 
					pushFollow(FOLLOW_resource_in_typedLiteral595);
					resource25=resource();
					state._fsp--;


					      Variable var = variable24;
					      //String functionName = resource25.toString();
					      // resource25 must be a URIConstant
					      String functionName = null;
					      if (resource25 instanceof Function){
					        functionName = ((ValueConstant) ((Function)resource25).getTerm(0)).getValue();
					      } else {
					        throw new IllegalArgumentException("resource25 should be an URI");
					      }
					      Predicate functionSymbol = null;
					      if (functionName.equals(OBDAVocabulary.RDFS_LITERAL_URI)) {
					          functionSymbol = dfac.getDataTypePredicateLiteral();
					      } else if (functionName.equals(OBDAVocabulary.XSD_STRING_URI)) {
					          functionSymbol = dfac.getDataTypePredicateString();
					      } else if (functionName.equals(OBDAVocabulary.XSD_INTEGER_URI) || functionName.equals(OBDAVocabulary.XSD_INT_URI)) {
					          functionSymbol = dfac.getDataTypePredicateInteger();
					      } else if (functionName.equals(OBDAVocabulary.XSD_DECIMAL_URI)) {
					          functionSymbol = dfac.getDataTypePredicateDecimal();
					      } else if (functionName.equals(OBDAVocabulary.XSD_DOUBLE_URI)) {
					          functionSymbol = dfac.getDataTypePredicateDouble();
					      } else if (functionName.equals(OBDAVocabulary.XSD_DATETIME_URI)) {
					          functionSymbol = dfac.getDataTypePredicateDateTime();
					      } else if (functionName.equals(OBDAVocabulary.XSD_BOOLEAN_URI)) {
					          functionSymbol = dfac.getDataTypePredicateBoolean();
					      } else {
					          throw new RecognitionException();
					      }
					      value = dfac.getFunction(functionSymbol, var);
					     
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
	// $ANTLR end "typedLiteral"



	// $ANTLR start "language"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:489:1: language returns [Term value] : ( languageTag | variable );
	public final Term language() throws RecognitionException {
		Term value = null;


		ParserRuleReturnScope languageTag26 =null;
		Variable variable27 =null;

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:490:3: ( languageTag | variable )
			int alt14=2;
			int LA14_0 = input.LA(1);
			if ( (LA14_0==VARNAME) ) {
				alt14=1;
			}
			else if ( (LA14_0==STRING_WITH_CURLY_BRACKET) ) {
				alt14=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 14, 0, input);
				throw nvae;
			}

			switch (alt14) {
				case 1 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:490:5: languageTag
					{
					pushFollow(FOLLOW_languageTag_in_language614);
					languageTag26=languageTag();
					state._fsp--;


					    	value = dfac.getConstantLiteral((languageTag26!=null?input.toString(languageTag26.start,languageTag26.stop):null).toLowerCase(), COL_TYPE.STRING);
					    
					}
					break;
				case 2 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:493:5: variable
					{
					pushFollow(FOLLOW_variable_in_language622);
					variable27=variable();
					state._fsp--;


					    	value = variable27;
					    
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
	// $ANTLR end "language"



	// $ANTLR start "terms"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:498:1: terms returns [Vector<Term> value] : t1= term ( COMMA t2= term )* ;
	public final Vector<Term> terms() throws RecognitionException {
		Vector<Term> value = null;


		Term t1 =null;
		Term t2 =null;


		  value = new Vector<Term>();

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:502:3: (t1= term ( COMMA t2= term )* )
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:502:5: t1= term ( COMMA t2= term )*
			{
			pushFollow(FOLLOW_term_in_terms648);
			t1=term();
			state._fsp--;

			 value.add(t1); 
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:502:40: ( COMMA t2= term )*
			loop15:
			while (true) {
				int alt15=2;
				int LA15_0 = input.LA(1);
				if ( (LA15_0==COMMA) ) {
					alt15=1;
				}

				switch (alt15) {
				case 1 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:502:41: COMMA t2= term
					{
					match(input,COMMA,FOLLOW_COMMA_in_terms653); 
					pushFollow(FOLLOW_term_in_terms657);
					t2=term();
					state._fsp--;

					 value.add(t2); 
					}
					break;

				default :
					break loop15;
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
	// $ANTLR end "terms"



	// $ANTLR start "term"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:505:1: term returns [Term value] : ( function | variable | literal );
	public final Term term() throws RecognitionException {
		Term value = null;


		Function function28 =null;
		Variable variable29 =null;
		Term literal30 =null;

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:506:3: ( function | variable | literal )
			int alt16=3;
			switch ( input.LA(1) ) {
			case PREFIXED_NAME:
			case STRING_WITH_BRACKET:
				{
				alt16=1;
				}
				break;
			case STRING_WITH_CURLY_BRACKET:
				{
				alt16=2;
				}
				break;
			case DECIMAL:
			case DECIMAL_NEGATIVE:
			case DECIMAL_POSITIVE:
			case DOUBLE:
			case DOUBLE_NEGATIVE:
			case DOUBLE_POSITIVE:
			case FALSE:
			case INTEGER:
			case INTEGER_NEGATIVE:
			case INTEGER_POSITIVE:
			case STRING_WITH_QUOTE_DOUBLE:
			case TRUE:
				{
				alt16=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 16, 0, input);
				throw nvae;
			}
			switch (alt16) {
				case 1 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:506:5: function
					{
					pushFollow(FOLLOW_function_in_term678);
					function28=function();
					state._fsp--;

					 value = function28; 
					}
					break;
				case 2 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:507:5: variable
					{
					pushFollow(FOLLOW_variable_in_term686);
					variable29=variable();
					state._fsp--;

					 value = variable29; 
					}
					break;
				case 3 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:508:5: literal
					{
					pushFollow(FOLLOW_literal_in_term694);
					literal30=literal();
					state._fsp--;

					 value = literal30; 
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
	// $ANTLR end "term"



	// $ANTLR start "literal"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:511:1: literal returns [Term value] : ( stringLiteral ( AT language )? | dataTypeString | numericLiteral | booleanLiteral );
	public final Term literal() throws RecognitionException {
		Term value = null;


		ValueConstant stringLiteral31 =null;
		Term language32 =null;
		Term dataTypeString33 =null;
		ValueConstant numericLiteral34 =null;
		ValueConstant booleanLiteral35 =null;

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:512:3: ( stringLiteral ( AT language )? | dataTypeString | numericLiteral | booleanLiteral )
			int alt18=4;
			switch ( input.LA(1) ) {
			case STRING_WITH_QUOTE_DOUBLE:
				{
				int LA18_1 = input.LA(2);
				if ( (LA18_1==AT||LA18_1==COMMA||LA18_1==PERIOD||LA18_1==RPAREN||LA18_1==SEMI||LA18_1==WS) ) {
					alt18=1;
				}
				else if ( (LA18_1==REFERENCE) ) {
					alt18=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 18, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case DECIMAL:
			case DECIMAL_NEGATIVE:
			case DECIMAL_POSITIVE:
			case DOUBLE:
			case DOUBLE_NEGATIVE:
			case DOUBLE_POSITIVE:
			case INTEGER:
			case INTEGER_NEGATIVE:
			case INTEGER_POSITIVE:
				{
				alt18=3;
				}
				break;
			case FALSE:
			case TRUE:
				{
				alt18=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 18, 0, input);
				throw nvae;
			}
			switch (alt18) {
				case 1 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:512:5: stringLiteral ( AT language )?
					{
					pushFollow(FOLLOW_stringLiteral_in_literal713);
					stringLiteral31=stringLiteral();
					state._fsp--;

					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:512:19: ( AT language )?
					int alt17=2;
					int LA17_0 = input.LA(1);
					if ( (LA17_0==AT) ) {
						alt17=1;
					}
					switch (alt17) {
						case 1 :
							// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:512:20: AT language
							{
							match(input,AT,FOLLOW_AT_in_literal716); 
							pushFollow(FOLLOW_language_in_literal718);
							language32=language();
							state._fsp--;

							}
							break;

					}


					       ValueConstant constant = stringLiteral31;
					       Term lang = language32;
					       if (lang != null) {
					         value = dfac.getFunction(dfac.getDataTypePredicateLiteralLang(), constant, lang);
					       } else {
					       	 value = dfac.getFunction(dfac.getDataTypePredicateLiteral(), constant);
					       }
					    
					}
					break;
				case 2 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:521:5: dataTypeString
					{
					pushFollow(FOLLOW_dataTypeString_in_literal728);
					dataTypeString33=dataTypeString();
					state._fsp--;

					 value = dataTypeString33; 
					}
					break;
				case 3 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:522:5: numericLiteral
					{
					pushFollow(FOLLOW_numericLiteral_in_literal736);
					numericLiteral34=numericLiteral();
					state._fsp--;

					 value = numericLiteral34; 
					}
					break;
				case 4 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:523:5: booleanLiteral
					{
					pushFollow(FOLLOW_booleanLiteral_in_literal744);
					booleanLiteral35=booleanLiteral();
					state._fsp--;

					 value = booleanLiteral35; 
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



	// $ANTLR start "stringLiteral"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:526:1: stringLiteral returns [ValueConstant value] : STRING_WITH_QUOTE_DOUBLE ;
	public final ValueConstant stringLiteral() throws RecognitionException {
		ValueConstant value = null;


		Token STRING_WITH_QUOTE_DOUBLE36=null;

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:527:3: ( STRING_WITH_QUOTE_DOUBLE )
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:527:5: STRING_WITH_QUOTE_DOUBLE
			{
			STRING_WITH_QUOTE_DOUBLE36=(Token)match(input,STRING_WITH_QUOTE_DOUBLE,FOLLOW_STRING_WITH_QUOTE_DOUBLE_in_stringLiteral763); 

			      String str = (STRING_WITH_QUOTE_DOUBLE36!=null?STRING_WITH_QUOTE_DOUBLE36.getText():null);
			      value = dfac.getConstantLiteral(str.substring(1, str.length()-1), COL_TYPE.LITERAL); // without the double quotes
			    
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
	// $ANTLR end "stringLiteral"



	// $ANTLR start "dataTypeString"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:533:1: dataTypeString returns [Term value] : stringLiteral REFERENCE resource ;
	public final Term dataTypeString() throws RecognitionException {
		Term value = null;


		ValueConstant stringLiteral37 =null;
		Term resource38 =null;

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:534:3: ( stringLiteral REFERENCE resource )
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:534:6: stringLiteral REFERENCE resource
			{
			pushFollow(FOLLOW_stringLiteral_in_dataTypeString783);
			stringLiteral37=stringLiteral();
			state._fsp--;

			match(input,REFERENCE,FOLLOW_REFERENCE_in_dataTypeString785); 
			pushFollow(FOLLOW_resource_in_dataTypeString787);
			resource38=resource();
			state._fsp--;


			      ValueConstant constant = stringLiteral37;
			      String functionName = resource38.toString();
			      Predicate functionSymbol = null;
			      if (resource38 instanceof Function){
				 functionName = ( (ValueConstant) ((Function)resource38).getTerm(0) ).getValue();
			      }
			      if (functionName.equals(OBDAVocabulary.RDFS_LITERAL_URI)) {
			    	functionSymbol = dfac.getDataTypePredicateLiteral();
			      } else if (functionName.equals(OBDAVocabulary.XSD_STRING_URI)) {
			    	functionSymbol = dfac.getDataTypePredicateString();
			      } else if (functionName.equals(OBDAVocabulary.XSD_INTEGER_URI)) {
			     	functionSymbol = dfac.getDataTypePredicateInteger();
			      } else if (functionName.equals(OBDAVocabulary.XSD_DECIMAL_URI)) {
			    	functionSymbol = dfac.getDataTypePredicateDecimal();
			      } else if (functionName.equals(OBDAVocabulary.XSD_DOUBLE_URI)) {
			    	functionSymbol = dfac.getDataTypePredicateDouble();
			      } else if (functionName.equals(OBDAVocabulary.XSD_DATETIME_URI)) {
			    	functionSymbol = dfac.getDataTypePredicateDateTime();
			      } else if (functionName.equals(OBDAVocabulary.XSD_BOOLEAN_URI)) {
			    	functionSymbol = dfac.getDataTypePredicateBoolean();
			      } else {
			        throw new RuntimeException("Unknown datatype: " + functionName);
			      }
			      value = dfac.getFunction(functionSymbol, constant);
			    
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
	// $ANTLR end "dataTypeString"



	// $ANTLR start "numericLiteral"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:562:1: numericLiteral returns [ValueConstant value] : ( numericUnsigned | numericPositive | numericNegative );
	public final ValueConstant numericLiteral() throws RecognitionException {
		ValueConstant value = null;


		ValueConstant numericUnsigned39 =null;
		ValueConstant numericPositive40 =null;
		ValueConstant numericNegative41 =null;

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:563:3: ( numericUnsigned | numericPositive | numericNegative )
			int alt19=3;
			switch ( input.LA(1) ) {
			case DECIMAL:
			case DOUBLE:
			case INTEGER:
				{
				alt19=1;
				}
				break;
			case DECIMAL_POSITIVE:
			case DOUBLE_POSITIVE:
			case INTEGER_POSITIVE:
				{
				alt19=2;
				}
				break;
			case DECIMAL_NEGATIVE:
			case DOUBLE_NEGATIVE:
			case INTEGER_NEGATIVE:
				{
				alt19=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 19, 0, input);
				throw nvae;
			}
			switch (alt19) {
				case 1 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:563:5: numericUnsigned
					{
					pushFollow(FOLLOW_numericUnsigned_in_numericLiteral806);
					numericUnsigned39=numericUnsigned();
					state._fsp--;

					 value = numericUnsigned39; 
					}
					break;
				case 2 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:564:5: numericPositive
					{
					pushFollow(FOLLOW_numericPositive_in_numericLiteral814);
					numericPositive40=numericPositive();
					state._fsp--;

					 value = numericPositive40; 
					}
					break;
				case 3 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:565:5: numericNegative
					{
					pushFollow(FOLLOW_numericNegative_in_numericLiteral822);
					numericNegative41=numericNegative();
					state._fsp--;

					 value = numericNegative41; 
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
	// $ANTLR end "numericLiteral"



	// $ANTLR start "nodeID"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:568:1: nodeID : BLANK_PREFIX name ;
	public final void nodeID() throws RecognitionException {
		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:569:3: ( BLANK_PREFIX name )
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:569:5: BLANK_PREFIX name
			{
			match(input,BLANK_PREFIX,FOLLOW_BLANK_PREFIX_in_nodeID837); 
			pushFollow(FOLLOW_name_in_nodeID839);
			name();
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
	// $ANTLR end "nodeID"



	// $ANTLR start "relativeURI"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:572:1: relativeURI : STRING_URI ;
	public final void relativeURI() throws RecognitionException {
		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:573:3: ( STRING_URI )
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:573:5: STRING_URI
			{
			match(input,STRING_URI,FOLLOW_STRING_URI_in_relativeURI853); 
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
	// $ANTLR end "relativeURI"


	public static class namespace_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "namespace"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:576:1: namespace : NAMESPACE ;
	public final TurtleOBDAParser.namespace_return namespace() throws RecognitionException {
		TurtleOBDAParser.namespace_return retval = new TurtleOBDAParser.namespace_return();
		retval.start = input.LT(1);

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:577:3: ( NAMESPACE )
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:577:5: NAMESPACE
			{
			match(input,NAMESPACE,FOLLOW_NAMESPACE_in_namespace866); 
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "namespace"


	public static class defaultNamespace_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "defaultNamespace"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:580:1: defaultNamespace : COLON ;
	public final TurtleOBDAParser.defaultNamespace_return defaultNamespace() throws RecognitionException {
		TurtleOBDAParser.defaultNamespace_return retval = new TurtleOBDAParser.defaultNamespace_return();
		retval.start = input.LT(1);

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:581:3: ( COLON )
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:581:5: COLON
			{
			match(input,COLON,FOLLOW_COLON_in_defaultNamespace881); 
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "defaultNamespace"



	// $ANTLR start "name"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:584:1: name : VARNAME ;
	public final void name() throws RecognitionException {
		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:585:3: ( VARNAME )
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:585:5: VARNAME
			{
			match(input,VARNAME,FOLLOW_VARNAME_in_name894); 
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
	// $ANTLR end "name"


	public static class languageTag_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "languageTag"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:588:1: languageTag : VARNAME ;
	public final TurtleOBDAParser.languageTag_return languageTag() throws RecognitionException {
		TurtleOBDAParser.languageTag_return retval = new TurtleOBDAParser.languageTag_return();
		retval.start = input.LT(1);

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:589:3: ( VARNAME )
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:589:5: VARNAME
			{
			match(input,VARNAME,FOLLOW_VARNAME_in_languageTag907); 
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "languageTag"



	// $ANTLR start "booleanLiteral"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:592:1: booleanLiteral returns [ValueConstant value] : ( TRUE | FALSE );
	public final ValueConstant booleanLiteral() throws RecognitionException {
		ValueConstant value = null;


		Token TRUE42=null;
		Token FALSE43=null;

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:593:3: ( TRUE | FALSE )
			int alt20=2;
			int LA20_0 = input.LA(1);
			if ( (LA20_0==TRUE) ) {
				alt20=1;
			}
			else if ( (LA20_0==FALSE) ) {
				alt20=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 20, 0, input);
				throw nvae;
			}

			switch (alt20) {
				case 1 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:593:5: TRUE
					{
					TRUE42=(Token)match(input,TRUE,FOLLOW_TRUE_in_booleanLiteral924); 
					 value = dfac.getConstantLiteral((TRUE42!=null?TRUE42.getText():null), COL_TYPE.BOOLEAN); 
					}
					break;
				case 2 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:594:5: FALSE
					{
					FALSE43=(Token)match(input,FALSE,FOLLOW_FALSE_in_booleanLiteral933); 
					 value = dfac.getConstantLiteral((FALSE43!=null?FALSE43.getText():null), COL_TYPE.BOOLEAN); 
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
	// $ANTLR end "booleanLiteral"



	// $ANTLR start "numericUnsigned"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:597:1: numericUnsigned returns [ValueConstant value] : ( INTEGER | DOUBLE | DECIMAL );
	public final ValueConstant numericUnsigned() throws RecognitionException {
		ValueConstant value = null;


		Token INTEGER44=null;
		Token DOUBLE45=null;
		Token DECIMAL46=null;

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:598:3: ( INTEGER | DOUBLE | DECIMAL )
			int alt21=3;
			switch ( input.LA(1) ) {
			case INTEGER:
				{
				alt21=1;
				}
				break;
			case DOUBLE:
				{
				alt21=2;
				}
				break;
			case DECIMAL:
				{
				alt21=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 21, 0, input);
				throw nvae;
			}
			switch (alt21) {
				case 1 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:598:5: INTEGER
					{
					INTEGER44=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_numericUnsigned952); 
					 value = dfac.getConstantLiteral((INTEGER44!=null?INTEGER44.getText():null), COL_TYPE.INTEGER); 
					}
					break;
				case 2 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:599:5: DOUBLE
					{
					DOUBLE45=(Token)match(input,DOUBLE,FOLLOW_DOUBLE_in_numericUnsigned960); 
					 value = dfac.getConstantLiteral((DOUBLE45!=null?DOUBLE45.getText():null), COL_TYPE.DOUBLE); 
					}
					break;
				case 3 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:600:5: DECIMAL
					{
					DECIMAL46=(Token)match(input,DECIMAL,FOLLOW_DECIMAL_in_numericUnsigned969); 
					 value = dfac.getConstantLiteral((DECIMAL46!=null?DECIMAL46.getText():null), COL_TYPE.DECIMAL); 
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
	// $ANTLR end "numericUnsigned"



	// $ANTLR start "numericPositive"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:603:1: numericPositive returns [ValueConstant value] : ( INTEGER_POSITIVE | DOUBLE_POSITIVE | DECIMAL_POSITIVE );
	public final ValueConstant numericPositive() throws RecognitionException {
		ValueConstant value = null;


		Token INTEGER_POSITIVE47=null;
		Token DOUBLE_POSITIVE48=null;
		Token DECIMAL_POSITIVE49=null;

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:604:3: ( INTEGER_POSITIVE | DOUBLE_POSITIVE | DECIMAL_POSITIVE )
			int alt22=3;
			switch ( input.LA(1) ) {
			case INTEGER_POSITIVE:
				{
				alt22=1;
				}
				break;
			case DOUBLE_POSITIVE:
				{
				alt22=2;
				}
				break;
			case DECIMAL_POSITIVE:
				{
				alt22=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 22, 0, input);
				throw nvae;
			}
			switch (alt22) {
				case 1 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:604:5: INTEGER_POSITIVE
					{
					INTEGER_POSITIVE47=(Token)match(input,INTEGER_POSITIVE,FOLLOW_INTEGER_POSITIVE_in_numericPositive988); 
					 value = dfac.getConstantLiteral((INTEGER_POSITIVE47!=null?INTEGER_POSITIVE47.getText():null), COL_TYPE.INTEGER); 
					}
					break;
				case 2 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:605:5: DOUBLE_POSITIVE
					{
					DOUBLE_POSITIVE48=(Token)match(input,DOUBLE_POSITIVE,FOLLOW_DOUBLE_POSITIVE_in_numericPositive996); 
					 value = dfac.getConstantLiteral((DOUBLE_POSITIVE48!=null?DOUBLE_POSITIVE48.getText():null), COL_TYPE.DOUBLE); 
					}
					break;
				case 3 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:606:5: DECIMAL_POSITIVE
					{
					DECIMAL_POSITIVE49=(Token)match(input,DECIMAL_POSITIVE,FOLLOW_DECIMAL_POSITIVE_in_numericPositive1005); 
					 value = dfac.getConstantLiteral((DECIMAL_POSITIVE49!=null?DECIMAL_POSITIVE49.getText():null), COL_TYPE.DECIMAL); 
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
	// $ANTLR end "numericPositive"



	// $ANTLR start "numericNegative"
	// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:609:1: numericNegative returns [ValueConstant value] : ( INTEGER_NEGATIVE | DOUBLE_NEGATIVE | DECIMAL_NEGATIVE );
	public final ValueConstant numericNegative() throws RecognitionException {
		ValueConstant value = null;


		Token INTEGER_NEGATIVE50=null;
		Token DOUBLE_NEGATIVE51=null;
		Token DECIMAL_NEGATIVE52=null;

		try {
			// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:610:3: ( INTEGER_NEGATIVE | DOUBLE_NEGATIVE | DECIMAL_NEGATIVE )
			int alt23=3;
			switch ( input.LA(1) ) {
			case INTEGER_NEGATIVE:
				{
				alt23=1;
				}
				break;
			case DOUBLE_NEGATIVE:
				{
				alt23=2;
				}
				break;
			case DECIMAL_NEGATIVE:
				{
				alt23=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 23, 0, input);
				throw nvae;
			}
			switch (alt23) {
				case 1 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:610:5: INTEGER_NEGATIVE
					{
					INTEGER_NEGATIVE50=(Token)match(input,INTEGER_NEGATIVE,FOLLOW_INTEGER_NEGATIVE_in_numericNegative1024); 
					 value = dfac.getConstantLiteral((INTEGER_NEGATIVE50!=null?INTEGER_NEGATIVE50.getText():null), COL_TYPE.INTEGER); 
					}
					break;
				case 2 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:611:5: DOUBLE_NEGATIVE
					{
					DOUBLE_NEGATIVE51=(Token)match(input,DOUBLE_NEGATIVE,FOLLOW_DOUBLE_NEGATIVE_in_numericNegative1032); 
					 value = dfac.getConstantLiteral((DOUBLE_NEGATIVE51!=null?DOUBLE_NEGATIVE51.getText():null), COL_TYPE.DOUBLE); 
					}
					break;
				case 3 :
					// /Users/xiao/Projects/ontop/obdalib-core/src/main/java/it/unibz/krdb/obda/parser/TurtleOBDA.g:612:5: DECIMAL_NEGATIVE
					{
					DECIMAL_NEGATIVE52=(Token)match(input,DECIMAL_NEGATIVE,FOLLOW_DECIMAL_NEGATIVE_in_numericNegative1041); 
					 value = dfac.getConstantLiteral((DECIMAL_NEGATIVE52!=null?DECIMAL_NEGATIVE52.getText():null), COL_TYPE.DECIMAL); 
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
	// $ANTLR end "numericNegative"

	// Delegated rules



	public static final BitSet FOLLOW_directiveStatement_in_parse52 = new BitSet(new long[]{0x0040000000000200L,0x0000000000000018L});
	public static final BitSet FOLLOW_triplesStatement_in_parse61 = new BitSet(new long[]{0x0040000000000000L,0x0000000000000018L});
	public static final BitSet FOLLOW_triplesStatement_in_parse72 = new BitSet(new long[]{0x0040000000000000L,0x0000000000000018L});
	public static final BitSet FOLLOW_EOF_in_parse76 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_directive_in_directiveStatement91 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_PERIOD_in_directiveStatement93 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_triples_in_triplesStatement110 = new BitSet(new long[]{0x0008000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_WS_in_triplesStatement112 = new BitSet(new long[]{0x0008000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_PERIOD_in_triplesStatement115 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_base_in_directive130 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_prefixID_in_directive136 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_AT_in_base149 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_BASE_in_base151 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_uriref_in_base153 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_AT_in_prefixID171 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_PREFIX_in_prefixID173 = new BitSet(new long[]{0x0000200000010000L});
	public static final BitSet FOLLOW_namespace_in_prefixID176 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_defaultNamespace_in_prefixID182 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_uriref_in_prefixID187 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_subject_in_triples206 = new BitSet(new long[]{0x0040000000000000L,0x0000000000002008L});
	public static final BitSet FOLLOW_predicateObjectList_in_triples210 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_verb_in_predicateObjectList236 = new BitSet(new long[]{0x00400070439C0000L,0x0000000000000158L});
	public static final BitSet FOLLOW_objectList_in_predicateObjectList242 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
	public static final BitSet FOLLOW_SEMI_in_predicateObjectList251 = new BitSet(new long[]{0x0040000000000000L,0x0000000000002008L});
	public static final BitSet FOLLOW_verb_in_predicateObjectList255 = new BitSet(new long[]{0x00400070439C0000L,0x0000000000000158L});
	public static final BitSet FOLLOW_objectList_in_predicateObjectList259 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
	public static final BitSet FOLLOW_predicate_in_verb283 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_77_in_verb291 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_object_in_objectList317 = new BitSet(new long[]{0x0000000000020002L});
	public static final BitSet FOLLOW_COMMA_in_objectList322 = new BitSet(new long[]{0x00400070439C0000L,0x0000000000000158L});
	public static final BitSet FOLLOW_object_in_objectList326 = new BitSet(new long[]{0x0000000000020002L});
	public static final BitSet FOLLOW_resource_in_subject348 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_variable_in_subject356 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_resource_in_predicate377 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_resource_in_object396 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_literal_in_object404 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_typedLiteral_in_object413 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_variable_in_object421 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_uriref_in_resource442 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_qname_in_resource451 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_WITH_BRACKET_in_uriref476 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PREFIXED_NAME_in_qname495 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_nodeID_in_blank510 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BLANK_in_blank516 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_WITH_CURLY_BRACKET_in_variable533 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_resource_in_function554 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_LPAREN_in_function556 = new BitSet(new long[]{0x00400070439C0000L,0x0000000000000158L});
	public static final BitSet FOLLOW_terms_in_function558 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_RPAREN_in_function560 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_variable_in_typedLiteral579 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_AT_in_typedLiteral581 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000810L});
	public static final BitSet FOLLOW_language_in_typedLiteral583 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_variable_in_typedLiteral591 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_REFERENCE_in_typedLiteral593 = new BitSet(new long[]{0x0040000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_resource_in_typedLiteral595 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_languageTag_in_language614 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_variable_in_language622 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_term_in_terms648 = new BitSet(new long[]{0x0000000000020002L});
	public static final BitSet FOLLOW_COMMA_in_terms653 = new BitSet(new long[]{0x00400070439C0000L,0x0000000000000158L});
	public static final BitSet FOLLOW_term_in_terms657 = new BitSet(new long[]{0x0000000000020002L});
	public static final BitSet FOLLOW_function_in_term678 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_variable_in_term686 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_literal_in_term694 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_stringLiteral_in_literal713 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_AT_in_literal716 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000810L});
	public static final BitSet FOLLOW_language_in_literal718 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dataTypeString_in_literal728 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_numericLiteral_in_literal736 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_booleanLiteral_in_literal744 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_WITH_QUOTE_DOUBLE_in_stringLiteral763 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_stringLiteral_in_dataTypeString783 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_REFERENCE_in_dataTypeString785 = new BitSet(new long[]{0x0040000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_resource_in_dataTypeString787 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_numericUnsigned_in_numericLiteral806 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_numericPositive_in_numericLiteral814 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_numericNegative_in_numericLiteral822 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BLANK_PREFIX_in_nodeID837 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_name_in_nodeID839 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_URI_in_relativeURI853 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NAMESPACE_in_namespace866 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COLON_in_defaultNamespace881 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VARNAME_in_name894 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VARNAME_in_languageTag907 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TRUE_in_booleanLiteral924 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FALSE_in_booleanLiteral933 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTEGER_in_numericUnsigned952 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLE_in_numericUnsigned960 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DECIMAL_in_numericUnsigned969 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTEGER_POSITIVE_in_numericPositive988 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLE_POSITIVE_in_numericPositive996 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DECIMAL_POSITIVE_in_numericPositive1005 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTEGER_NEGATIVE_in_numericNegative1024 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLE_NEGATIVE_in_numericNegative1032 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DECIMAL_NEGATIVE_in_numericNegative1041 = new BitSet(new long[]{0x0000000000000002L});
}
