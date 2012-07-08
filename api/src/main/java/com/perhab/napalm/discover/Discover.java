package com.perhab.napalm.discover;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.reflections.Configuration;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

public class Discover {

	private static Reflections methodReflections;
	private static Reflections classReflections;
	
	private static synchronized void init() {
		if (methodReflections == null) {
			ConfigurationBuilder configuration = new ConfigurationBuilder()
				.setUrls(ClasspathHelper.forJavaClassPath())
				.setScanners(new MethodAnnotationsScanner());
			methodReflections = new Reflections(configuration);
		}
		if (classReflections == null) {
			ConfigurationBuilder configuration = new ConfigurationBuilder()
				.setUrls(ClasspathHelper.forJavaClassPath())
				.setScanners(new SubTypesScanner());
			classReflections = new Reflections(configuration);
		
		}
	}
	

	public static Class<?>[] findClassesWithMethodsAnnotatedWith(final Class<? extends Annotation> annotation) {
		init();
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>(); 
		for (Method method : methodReflections.getMethodsAnnotatedWith(annotation)) {
			Class<?> declaringClass = method.getDeclaringClass();
			if (declaringClass.isInterface()) {
				classes.addAll(findClassesForInterface(declaringClass));
			} else {
				classes.add(declaringClass);
			}
			
		}
		return classes.toArray(new Class<?>[classes.size()]);
	}


	public static Set<? extends Class<?>> findClassesForInterface(
			Class<?> declaringClass) {
		return filterBy(classReflections.getSubTypesOf(declaringClass),
				Predicates.and(HasPublicConstructor.get(), Predicates.not(IsAbstract.get())));
	}


	public static <T> Set<T> filterBy(final Set<? extends T> elements, final Predicate<T> filter) {
		LinkedHashSet<T> result = new LinkedHashSet<T>(elements);
		Iterables.removeIf(result, Predicates.not(filter));
		return result;
	}

}
