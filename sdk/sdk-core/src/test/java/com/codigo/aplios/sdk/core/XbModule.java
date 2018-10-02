package com.codigo.aplios.sdk.core;

import com.google.inject.AbstractModule;
import com.google.inject.BindingAnnotation;
import com.google.inject.Inject;
import com.google.inject.name.Names;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class XbModule
        extends AbstractModule {

    @Override
    protected void configure() {

        this.bindConstant()
                .annotatedWith(Names.named("width"))
                .to(40);
        this.bindConstant()
                .annotatedWith(Height.class)
                .to(40);
    }
    //
    // public static Injector use() {
    // Injector injector = Guice.createInjector(new XbModule());
    // return injector;
    // }

}

class Box {

    private final int width;

    private final int height;

    private final int depth;

    @Inject
    public Box(final int width, final int height, final int depth) {

        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    // @Inject
    // public Box(final int width, @Height final int height, final int depth) {
    // this.width = width;
    // this.height = height;
    // this.depth = depth;
    // }
    public int surfaceArea() {

        return ((this.width * this.height) + (this.width * this.depth) + (this.height * this.depth)) * 2;
    }

    public int volume() {

        return this.width * this.height * this.depth;
    }

}

@BindingAnnotation
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface Height {
}
