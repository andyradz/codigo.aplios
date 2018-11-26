package com.codigo.aplios.data.core.paging;

public class PageElement<T> {

	private T dataElement;

	private long ordinalNumber;

	/**
	 * Podstawowy konstruktor obiektu klasy <code>PageElement</code>
	 * 
	 * @param ordinalNumber Numer porządkowy pozycji na stronie
	 */
	public PageElement(long ordinalNumber) {
		super();
		this.setOrdinalNumber(ordinalNumber);
	}

	/**
	 * @return the dataElement
	 */
	public T getDataElement() {
		return dataElement;
	}

	/**
	 * @param dataElement the dataElement to set
	 */
	public void setDataElement(T dataElement) {
		this.dataElement = dataElement;
	}

	/**
	 * @return the ordinalNumber
	 */
	public long getOrdinalNumber() {
		return ordinalNumber;
	}

	/**
	 * @param ordinalNumber the ordinalNumber to set
	 */
	private void setOrdinalNumber(long ordinalNumber) {
		this.ordinalNumber = ordinalNumber;
	}
}
