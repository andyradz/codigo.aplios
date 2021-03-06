package com.codigo.aplios.gui.core.gridview;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class BeanPropertyTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	private Object _bean;

	private String _nameColumnName = "Namecolumn";

	private String _valueColumnName = "Valuecolumn";

	public BeanPropertyTableModel() {

		super();
	}

	public void setBean(final Object bean) throws RuntimeException {

		this._bean = bean;
		refresh();
	}

	public void refresh() throws RuntimeException {

		final Vector<Object> columnNames = new Vector<>();
		columnNames.add(this._nameColumnName);
		columnNames.add(this._valueColumnName);
		final Vector<Object> columnData = new Vector<>();
		if (this._bean != null)
			try {
				final BeanInfo info = Introspector.getBeanInfo(this._bean.getClass(), Introspector.USE_ALL_BEANINFO);
				processBeanInfo(info, columnData);
			}
			catch (final Exception ex) {
				throw new RuntimeException(
					ex);
			}

		// Sort the rows by the property name.
		Collections.sort(columnData, new DataSorter());

		// this.setDataVector(columnData, columnNames);
	}

	private void processBeanInfo(final BeanInfo info, final Vector<Object> columnData)
			throws InvocationTargetException, IllegalAccessException {

		final BeanInfo[] extra = info.getAdditionalBeanInfo();
		if (extra != null)
			for (final BeanInfo element : extra)
				processBeanInfo(element, columnData);

		final PropertyDescriptor[] propDesc = info.getPropertyDescriptors();
		for (final PropertyDescriptor element : propDesc) {
			final String propName = element.getName();
			final Method getter = element.getReadMethod();
			if ((propName != null) && (getter != null)) {
				final Vector<Object> line = generateLine(propName, this._bean, getter);
				if (line != null)
					columnData.add(line);
			}
		}
	}

	/**
	 * Generate a line for the passed property.
	 *
	 * @param propName
	 *        Name of the property.
	 * @param bean
	 *        Bean containg the property.
	 * @param getter
	 *        The "getter" function to retrieve the properties value.
	 *
	 * @return A <CODE>Vector</CODE> containing the cells for the line in the table. Element zero the
	 *         first cell etc. Return <CODE>null</CODE> if this property is <B>not</B> to be added to
	 *         the table.
	 */
	protected Vector<Object> generateLine(final String propName, final Object bean, final Method getter)
			throws InvocationTargetException, IllegalAccessException {

		final Vector<Object> line = new Vector<>();
		line.add(propName);
		line.add(executeGetter(bean, getter));
		return line;
	}

	protected Object executeGetter(final Object bean, final Method getter)
			throws InvocationTargetException, IllegalAccessException {

		return getter.invoke(bean, (Object[]) null);
	}

	public void setNameColumnName(final String value) {

		this._nameColumnName = value;
	}

	public void setValueColumnName(final String value) {

		this._valueColumnName = value;
	}

	/**
	 * This comparator is compatible with the strange use of lists in this class. This classes lists are
	 * Vectors with Strings as the first element and any object as the other objects.
	 */
	private static final class DataSorter implements Comparator<Object> {

		@Override
		public int compare(final Object o1, final Object o2) {

			final Vector<?> v1 = Vector.class.cast(o1);
			final Vector<?> v2 = Vector.class.cast(o2);
			v1.get(0);
			v2.get(0);
			return 0;// lhs.compareToIgnoreCase(rhs);
		}

	}
}
