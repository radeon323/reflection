package com.luxoft.olshevchenko;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReflectionUtilsTest {
    private TestPerson person;

    @BeforeEach
    public void before() {
        person = new TestPerson("name", 10);
    }


    @Test
    void testCreateObjectWithDefaultConstructor() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TestPerson testPerson = (TestPerson) ReflectionUtils.createObject(TestPerson.class);
        assertNotNull(testPerson);
        assertEquals(TestPerson.class, testPerson.getClass());
        assertTrue(testPerson.isDefaultConstructor);
        assertFalse(testPerson.isConstructor);
    }

    @Test
    void testCreateObject() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TestPerson testPerson = (TestPerson) ReflectionUtils.createObject(TestPerson.class);
        assertNotNull(person);
        assertNotNull(testPerson);
        assertEquals(TestPerson.class, person.getClass());
        assertEquals(person.getClass(), testPerson.getClass());
        assertFalse(person.isDefaultConstructor);
        assertTrue(person.isConstructor);
    }

    @Test
    void testCallAllMethodsWithoutParameters() throws InvocationTargetException, IllegalAccessException {
        int count = ReflectionUtils.callAllMethodsWithoutParameters(person);
        assertEquals("Person", person.getName());
        assertEquals(11, person.getAge());
        person.incrementNumberAge();
        assertEquals(12, person.getAge());
        assertEquals(7, count);
    }

    @Test
    void testShowSignatures() {
        List<String> methods = ReflectionUtils.showSignatures(person);
        assertEquals(2, methods.size());
        assertTrue(ReflectionUtils.getAllNotPublicMethods(TestPerson.class).toString().contains("incrementNumberAge"));
    }

    @Test
    void testGetAllNotPublicMethods() {
        List<String> methods = ReflectionUtils.getAllNotPublicMethods(TestPerson.class);
        assertEquals(2, methods.size());
        assertTrue(ReflectionUtils.getAllNotPublicMethods(TestPerson.class).toString().contains("incrementNumberAge"));
    }

    @Test
    void testGetAllSuperClassesAndInterfaces() {
        List<String> inherited = ReflectionUtils.getAllSuperClassesAndInterfaces(TestPerson.class);
        assertEquals(2, inherited.size());
        assertTrue(ReflectionUtils.getAllSuperClassesAndInterfaces(TestPerson.class).toString().contains("Serializable"));
        assertTrue(ReflectionUtils.getAllSuperClassesAndInterfaces(TestPerson.class).toString().contains("Object"));
    }

    @Test
    void testChangePrivateFieldsToDefault() throws IllegalAccessException {
        ReflectionUtils.changePrivateFieldsToDefault(person);
        assertEquals(0, person.getAge());
        assertNull(person.getName());
        assertNull(person.getArrayOfPets());
        assertEquals(0, person.getNumberOfPets());
        assertFalse(person.isChild());
    }
}