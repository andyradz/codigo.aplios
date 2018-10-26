package data.mapping;

import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.mappings.DirectToFieldMapping;
import org.eclipse.persistence.mappings.converters.ObjectTypeConverter;


//DescriptorCustomizer : defines an interface to customize the mapping meta-data for a class.
//SessionCustomizer : defines an interface to customize the meta-data for a persistence unit, or a set of its classes.

public class MyCustomizer implements DescriptorCustomizer {

	public void customize(ClassDescriptor descriptor) {

//		DirectToFieldMapping genderMapping = (DirectToFieldMapping) descriptor.getMappingForAttributeName("gender");
//		ObjectTypeConverter converter = new ObjectTypeConverter();
//		converter.addConversionValue("M", Gender.MALE);
//		converter.addConversionValue("F", Gender.FEMALE);
//		genderMapping.setConverter(converter);
	}
}