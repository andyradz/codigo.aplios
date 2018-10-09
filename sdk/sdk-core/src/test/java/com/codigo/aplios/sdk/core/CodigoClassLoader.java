package com.codigo.aplios.sdk.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;

public class CodigoClassLoader extends ClassLoader {

	public static void main(final String[] args)
			throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		final URLClassLoader clsLoader = URLClassLoader.newInstance(new URL[] {
				new URL(
					"file:/d://program.jar") });
		Class<?> cls = clsLoader.loadClass("com.codigo.aplios.contos.system.identity.Screen");

		// Method method = cls.getMethod("main", String[].class);
		// String[] params = new String[2];
		// method.invoke(null, (Object) params);

		final CodigoClassLoader loader = new CodigoClassLoader(
			Arrays.asList(new URL(
				"file:/d://program.jar")));
		cls = loader.loadClass("com.codigo.aplios.contos.system.identity.Screen");
		final Method method = cls.getMethod("main", String[].class);
		final String[] params = new String[2];
		method.invoke(null, (Object) params);
	}

	private final ChildClassLoader childClassLoader;

	public CodigoClassLoader(final List<URL> classpath) {
		super(Thread.currentThread()
				.getContextClassLoader());

		final URL[] urls = classpath.toArray(new URL[classpath.size()]);
		this.childClassLoader = new ChildClassLoader(
			urls, new DetectClass(
				getParent()));
	}

	@Override
	protected synchronized Class<?> loadClass(final String name, final boolean resolve) throws ClassNotFoundException {
		try {
			return this.childClassLoader.findClass(name);
		}
		catch (final ClassNotFoundException e) {
			return super.loadClass(name, resolve);
		}
	}

	private static class ChildClassLoader extends URLClassLoader {
		private final DetectClass realParent;

		public ChildClassLoader(final URL[] urls, final DetectClass realParent) {
			super(urls, null);
			this.realParent = realParent;
		}

		@Override
		public Class<?> findClass(final String name) throws ClassNotFoundException {
			try {
				final Class<?> loaded = super.findLoadedClass(name);
				if (loaded != null)
					return loaded;
				return super.findClass(name);
			}
			catch (final ClassNotFoundException e) {
				return this.realParent.loadClass(name);
			}
		}
	}

	private static class DetectClass extends ClassLoader {
		public DetectClass(final ClassLoader parent) {
			super(parent);
		}

		@Override
		public Class<?> findClass(final String name) throws ClassNotFoundException {
			return super.findClass(name);
		}
	}
}