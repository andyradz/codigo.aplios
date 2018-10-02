package com.codigo.aplios.data.struct;

import java.lang.reflect.ParameterizedType;

public class TestParametrized {

    private static ParameterizedType getParametrizedType(final Class<?> clazz) {

        // if (clazz.getSuperclass()
        // .equals(Domain.class)) { // check that we are at the top of the hierarchy
        // return (ParameterizedType) clazz.getGenericSuperclass();
        // }
        // else {
        // return MinPQ.getParametrizedType(clazz.getSuperclass());
        // }
        return null;
    }

}
