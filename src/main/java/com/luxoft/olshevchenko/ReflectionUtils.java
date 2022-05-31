package com.luxoft.olshevchenko;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Oleksandr Shevchenko
 */
public class ReflectionUtils {

    //    Метод принимает класс и возвращает созданный объект этого класса
    static Object createObject(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?> constructor = clazz.getConstructor();
        TestPerson testPerson = (TestPerson) constructor.newInstance();
        return testPerson;
    }


    //    Метод принимает object и вызывает у него все методы без параметров
    static int callAllMethodsWithoutParameters(Object obj) throws InvocationTargetException, IllegalAccessException {
        Class<?> clazz = obj.getClass();
        int count = 0;
        for (Method method : clazz.getDeclaredMethods()) {
            method.setAccessible(true);
            if (method.getParameters().length == 0) {
                method.invoke(obj);
                count++;
            }
        }
        return count;
    }


    //    Метод принимает object и выводит на экран все сигнатуры методов в который есть final
    static List<String> showSignatures(Object obj) {
        Class<?> clazz = obj.getClass();
        List<String> methods = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            method.setAccessible(true);
            if (Modifier.isFinal(method.getModifiers())) {
                methods.add(String.valueOf(method));
            }
        }
        return methods;
    }


    //    Метод принимает Class и выводит все не публичные методы этого класса
    static List<String> getAllNotPublicMethods(Class<?> clazz) {
        List<String> methods = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            if (!Modifier.isPublic(method.getModifiers())) {
                methods.add(String.valueOf(method));
            }
        }
        return methods;
    }


    //    Метод принимает Class и выводит всех предков класса и все интерфейсы которое класс имплементирует
    static List<String> getAllSuperClassesAndInterfaces(Class<?> clazz) {
        List<String> inherited = new ArrayList<>();
        inherited.add(String.valueOf(clazz.getSuperclass()));
        inherited.add(Arrays.toString(clazz.getInterfaces()));
        return inherited;
    }


    //    Метод принимает объект и меняет всего его приватные поля на их нулевые значение (null, 0, false etc)+
    static void changePrivateFieldsToDefault(Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (Modifier.isPrivate(field.getModifiers())) {
                if (field.getType().getSuperclass() == Object.class) {
                    field.set(obj, null);
                } else if (field.getType() == int.class) {
                    field.setInt(obj, 0);
                } else if (field.getType() == boolean.class) {
                    field.setBoolean(obj, false);
                }
            }
        }
    }


}
