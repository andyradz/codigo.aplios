package com.codigo.aplios.domain.model;

import com.codigo.aplios.domain.model.evidence.gender.Gender;
import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.mappings.DirectToFieldMapping;
import org.eclipse.persistence.mappings.converters.ObjectTypeConverter;

public class MyCustomizer
        implements DescriptorCustomizer {

    @Override
    public void customize(final ClassDescriptor descriptor) {

        final DirectToFieldMapping genderMapping = (DirectToFieldMapping)descriptor
                .getMappingForAttributeName("gender");
        final ObjectTypeConverter converter = new ObjectTypeConverter();
        converter.addConversionValue("M", Gender.MALE);
        converter.addConversionValue("F", Gender.FEMALE);
        genderMapping.setConverter(converter);
    }

}
