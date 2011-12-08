package it.unibz.krdb.sql.api;

import java.util.LinkedList;
import java.util.Queue;

public class Selection {
	
	/**
	 * Collection of boolean conditions and boolean operators.
	 */
	private LinkedList<Object> conditions;
	
	public Selection() { 
		conditions = new LinkedList<Object>();
	}
	
	/**
	 * Inserts a boolean condition to the list. A condition must not
	 * succeed another boolean condition.
	 * 
	 * @param predicate
	 * 			The boolean condition in the form of comparison
	 * 			predicate.
	 * @throws Exception An exception is thrown if a boolean condition
	 * immediately succeed another boolean condition.
	 */
	public void addCondition(ComparisonPredicate predicate) throws Exception {
		if (!conditions.isEmpty()) {
			Object obj = conditions.peekLast();
			if (obj instanceof ComparisonPredicate) {
				throw new Exception("Illegal conditional expression!");
			}
		}
		conditions.add(predicate);
	}
	
	/**
	 * Inserts a boolean operator among boolean conditions. A boolean
	 * operator must not succeed another boolean operator.
	 * 
	 * @param op
	 * 			A {@link LogicalOperator} object.
	 * @throws Exception An exception is thrown if a boolean operator
	 * immediately succeed another boolean operator.
	 * @see {@link AndOperator}, {@link OrOperator}
	 */
	public void addOperator(LogicalOperator op) throws Exception {
		if (!conditions.isEmpty()) {
			Object obj = conditions.peekLast();
			if (!(obj instanceof LogicalOperator)) {
				conditions.add(op);
			}
			else {
				throw new Exception("Illegal conditional expression!");
			}
		}
	}
	
	/**
	 * Copies the input boolean specification into the list.
	 * 
	 * @param specification
	 * 			The collection of conditions and boolean operator.
	 * @throws Exception An exception is thrown if it violates the
	 * rule for adding boolean conditions or boolean operators.
	 */
	public void copy(Queue<Object> specification) throws Exception {
		for (Object obj : specification) {
			if (obj instanceof ComparisonPredicate) {
				addCondition((ComparisonPredicate)obj);
			}
			else {
				addOperator((LogicalOperator)obj);
			}
		}
	}
	
	/**
	 * Updates the conditions list in this selection. Any existing
	 * conditions are going to be replaced by the new specification.
	 * 
	 * @param specification
	 * 			The new collection of conditions and boolean operator.
	 * @throws Exception An exception is thrown if it violates the
	 * rule for adding boolean conditions or boolean operators.
	 */
	public void update(Queue<Object> specification) throws Exception {
		conditions.clear();
		copy(specification);
	}
	
	/**
	 * Returns the number of boolean conditions.
	 */
	public int conditionSize() {
		return (conditions.size()/2) + 1;
	}
	
	/**
	 * Returns the boolean condition in a specific order.
	 * The initial order starts at 0 index.
	 * 
	 * @param index
	 * 			The specific order.
	 */
	public ComparisonPredicate getCondition(int index) {
		index = index * 2;
		return (ComparisonPredicate)conditions.get(index);
	}
	
	/**
	 * Returns the boolean operator in a specific order.
	 * The initial order starts at 0 index.
	 * 
	 * @param index
	 * 			The specific order.
	 */
	public String getLogicalOperator(int index) {
		index = (index * 2) + 1;
		return (String)conditions.get(index);
	}
	
	/**
	 * Returns the object inside the condition expression list, 
	 * it can be either a condition or a logical operator (e.g., and, or).
	 * 
	 * @param index
	 * 			The index of the list.
	 */
	public Object getObject(int index) {
		return conditions.get(index);
	}
	
	@Override
	public String toString() {
		String str = "where";
		
		for (Object obj : conditions) {
			str += " ";
			str += obj.toString();
		}
		return str;
	}
}