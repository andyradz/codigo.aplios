package com.codigo.aplios.gui.control;

/**
 *
 * @author JHS
 */
public interface IFilterOperator<T> {

	/**
	 * Probably should turn this into a normal class, so I can create true subsets
	 * of these type in IFilterOperator subclasses
	 */
	public enum Type {
		NONE("No Filter"),
		NOTSET("Not Set"),
		EQUALS("Equals"),
		NOTEQUALS("Not Equals"),
		GREATERTHAN("Greater Than"),
		GREATERTHANEQUALS("Equals/Greater Than"),
		LESSTHAN("Less Than"),
		LESSTHANEQUALS("Equals/Less Than"),
		CONTAINS("Contains"),
		STARTSWITH("Starts With"),
		ENDSWITH("Ends With"),
		BEFORE("Before"),
		BEFOREON("Before Or On"),
		AFTER("After"),
		AFTERON("After Or On"),
		TRUE("True"),
		FALSE("False");

		private final String display;

		Type(final String display) {

			this.display = display;
		}

		@Override
		public String toString() {

			return this.display;
		}
	};

	public T getValue();

	public Type getType();
}
