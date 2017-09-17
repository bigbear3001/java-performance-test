package com.perhab.napalm.statement;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by bigbear3001 on 02.07.17.
 */
public class ExecutionExplorerParameterTest {
    @Test
    public void testStringParameters() {
        Method[] methods = ExecutionExplorer.findExecutionMethods(ExecutionExplorerParameterTest.ClassWithStringParameters.class);
        assertNotNull("Expected ExecutionExplorer to find method", methods);
        assertEquals("Expected ExecutionExplorer to find only one method", 1, methods.length);
        Object[] arguments = ExecutionExplorer.getArguments(methods[0]);
        assertArrayEquals("Expected to get string parameters back.", new Object[]{"1", "2"}, arguments);
    }

    public static class ClassWithStringParameters {
        @Execute(parameters = {@Parameter("1"), @Parameter("2")})
        public void method(String a, String b) {

        }
    }

    @Test
    public void testIntegerParameters() {
        Method[] methods = ExecutionExplorer.findExecutionMethods(ExecutionExplorerParameterTest.ClassWithIntegerParameters.class);
        assertNotNull("Expected ExecutionExplorer to find method", methods);
        assertEquals("Expected ExecutionExplorer to find only one method", 1, methods.length);
        Object[] arguments = ExecutionExplorer.getArguments(methods[0]);
        assertArrayEquals("Expected to get integer parameters back.", new Object[]{1, 2}, arguments);
    }

    public static class ClassWithIntegerParameters {
        @Execute(parameters = {@Parameter("1"), @Parameter("2")})
        public void method(Integer a, Integer b) {

        }
    }

    @Test
    public void testIntegerArrayParameter() {
        Method[] methods = ExecutionExplorer.findExecutionMethods(ExecutionExplorerParameterTest.ClassWithIntegerArrayParameter.class);
        assertNotNull("Expected ExecutionExplorer to find methods", methods);
        assertEquals("Expected ExecutionExplorer to find only one methods", 1, methods.length);
        Object[] arguments = ExecutionExplorer.getArguments(methods[0]);
        assertArrayEquals("Expected to get integer parameters back.", new Object[]{new Integer[]{1, 2}}, arguments);
    }

    public static class ClassWithIntegerArrayParameter {
        @Execute(parameters = {@Parameter("1"), @Parameter("2")})
        public void method(Integer[] a) {

        }
    }

    @Test
    public void testIntegerArrayDefinitionParameter() {
        Method[] methods = ExecutionExplorer.findExecutionMethods(ExecutionExplorerParameterTest.ClassWithIntegerArrayDefinitionParameter.class);
        assertNotNull("Expected ExecutionExplorer to find method", methods);
        assertEquals("Expected ExecutionExplorer to find only one method", 1, methods.length);
        Object[] arguments = ExecutionExplorer.getArguments(methods[0]);
        assertArrayEquals("Expected to get integer parameters back.", new Object[]{new Integer[]{1, 2, 3, 4, 5}}, arguments);
    }

    public static class ClassWithIntegerArrayDefinitionParameter {
        @Execute(parameters = {@Parameter(arrayDefinition = "1...5")})
        public void method(Integer[] a) {

        }
    }

    @Test
    public void testStringArrayParameter() {
        Method[] methods = ExecutionExplorer.findExecutionMethods(ExecutionExplorerParameterTest.ClassWithStringArrayParameter.class);
        assertNotNull("Expected ExecutionExplorer to find method", methods);
        assertEquals("Expected ExecutionExplorer to find only one method", 1, methods.length);
        Object[] arguments = ExecutionExplorer.getArguments(methods[0]);
        assertArrayEquals("Expected to get integer parameters back.", new Object[]{new String[]{"1", "2"}}, arguments);
    }

    public static class ClassWithStringArrayParameter {
        @Execute(parameters = {@Parameter("1"), @Parameter("2")})
        public void method(String[] a) {

        }
    }



}
