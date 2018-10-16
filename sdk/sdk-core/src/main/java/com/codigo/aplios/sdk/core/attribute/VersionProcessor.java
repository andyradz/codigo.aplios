package com.codigo.aplios.sdk.core.attribute;

// import javax.annotation.processing.AbstractProcessor;
// import javax.annotation.processing.Messager;
// import javax.annotation.processing.RoundEnvironment;
// import javax.annotation.processing.SupportedAnnotationTypes;
// import javax.annotation.processing.SupportedSourceVersion;
// import javax.lang.model.SourceVersion;
// import javax.lang.model.element.Element;
// import javax.lang.model.element.Name;
// import javax.lang.model.element.TypeElement;
// import javax.tools.Diagnostic.Kind;
//
// @SupportedAnnotationTypes({ "com.codigo.aplios.contos.system.attributes.Version" })
// @SupportedSourceVersion(SourceVersion.RELEASE_8)
// public class VersionProcessor extends AbstractProcessor {
//
// /**
// * Podstawowy konstruktor obiektu. Konstruktor wymagany zgodnie ze specyfikacją JavaBeans;
// */
// public VersionProcessor() {
//
// }
//
// /*
// * (non-Javadoc)
// *
// * @see javax.annotation.processing.AbstractProcessor#process(java.util.Set,
// * javax.annotation.processing.RoundEnvironment)
// */
// @Override
// public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment
// roundEnv) {
//
// for (final TypeElement currAnn : annotations) {
// final Name qualifiedName = currAnn.getQualifiedName();
// if (qualifiedName.contentEquals("com.codigo.aplios.contos.system.attributes.Version")) {
// Set<? extends Element> annElems;
// annElems = roundEnv.getElementsAnnotatedWith(currAnn);
//
// for (final Element element : annElems) {
// final Version v = element.getAnnotation(Version.class);
// final int major = v.major();
// final int minor = v.minor();
// if ((major < 0) || (minor < 0)) {
// final String errorMsg = "Version cannot" + "be negative." + " major" + major + " minor" + minor;
// final Messager messager = this.processingEnv.getMessager();
// messager.printMessage(Kind.ERROR, errorMsg, element);
// }
// }
// }
// }
// return false;
// }
// }
//
/// **
// * @author Andrzej Radziszewski
// */
// @SupportedAnnotationTypes({ "com.codigo.aplios.contos.system.attributes.Version" })
// @SupportedSourceVersion(SourceVersion.RELEASE_8)
// class VersionProcessor1 extends AbstractProcessor {
//
// /**
// * Podstawowy konstruktor obiektu klasy <code>VersionProcessor</code> Wymagana definicja
// * konstruktora zgodna ze specyfikacją JavaBeans;
// */
// public VersionProcessor1() {
//
// }
//
// @Override
// public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment
// roundEnv) {
// // TODO Auto-generated method stub
// return false;
// }
//
// /*
// * (non-Javadoc)
// *
// * @see javax.annotation.processing.AbstractProcessor#process(java.util.Set,
// * javax.annotation.processing.RoundEnvironment)
// */
// /*
// * @Override public boolean process(final Set<? extends TypeElement> annotations, final
// * RoundEnvironment roundEnv) { // // for (TypeElement currAnn : annotations) { // Name
// * qualifiedName = currAnn.getQualifiedName(); // if //
// * (qualifiedName.contentEquals("com.codigo.aplios.contos.system.attributes.Version")) // { //
// Set<?
// * extends Element> annElems; // annElems = roundEnv.getElementsAnnotatedWith(currAnn); // // for
// * (Element element : annElems) { // Version v = element.getAnnotation(Version.class); // int
// major
// * = v.major(); // int minor = v.minor(); // if ((major < 0) || (minor < 0)) { // String errorMsg
// =
// * "Version cannot" + "be negative." + " major" + major + " // minor" + minor; // final Messager
// * messager = this.processingEnv.getMessager(); // messager.printMessage(Kind.ERROR, errorMsg,
// * element); // } // } // } // } return false; }
// */
//
// }
