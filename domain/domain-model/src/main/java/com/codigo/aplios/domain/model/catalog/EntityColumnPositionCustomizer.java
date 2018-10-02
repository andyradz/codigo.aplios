package com.codigo.aplios.domain.model.catalog;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.mappings.DatabaseMapping;

public class EntityColumnPositionCustomizer implements DescriptorCustomizer {

	@Override
	public void customize(final ClassDescriptor descriptor) throws Exception {
		descriptor.setShouldOrderMappings(true);
		final List<DatabaseMapping> mappings = descriptor.getMappings();
		addWeight(this.getClass(descriptor.getJavaClassName()), mappings);
	}

	private void addWeight(final Class<?> cls, final List<DatabaseMapping> mappings) {
		final Map<String, Integer> fieldOrderMap = getColumnPositions(cls, null);
		mappings.forEach((mapping) -> {
			final String key = mapping.getAttributeName();
			final Object obj = fieldOrderMap.get(key);
			int weight = 1;
			if (obj != null)
				weight = Integer.parseInt(obj.toString());
			mapping.setWeight(weight);
		});
	}

	private Class<?> getClass(final String javaFileName) throws ClassNotFoundException {
		Class<?> cls = null;
		if ((javaFileName != null) && !javaFileName.equals(""))
			cls = Class.forName(javaFileName);
		return cls;
	}

	private Map<String, Integer> getColumnPositions(final Class<?> classFile, Map<String, Integer> columnOrder) {
		if (columnOrder == null)
			columnOrder = new HashMap<>();
		final Field[] fields = classFile.getDeclaredFields();
		for (final Field field : fields)
			if (field.isAnnotationPresent(ColumnPosition.class)) {
				final ColumnPosition cp = field.getAnnotation(ColumnPosition.class);
				columnOrder.put(field.getName(), cp.position());
			}
		if ((classFile.getSuperclass() != null) && (classFile.getSuperclass() != Object.class))
			getColumnPositions(classFile.getSuperclass(), columnOrder);
		return columnOrder;
	}

}
