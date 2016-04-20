package ru.ncedu.java.tasks;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


import static java.lang.Class.forName;
import static java.lang.reflect.Modifier.*;

public class ReflectionsImpl implements Reflections {
    @Override public Set<String> getProtectedMethodNames(Class clazz) {
        if(clazz==null){
            throw new NullPointerException();
        }else {
            Set<String> setStrings = new HashSet<>();
            Method[] methods=clazz.getDeclaredMethods();
            for (Method m:methods){
                if(isProtected(m.getModifiers())){
                    setStrings.add(m.getName());
                }
            }
            return setStrings;
        }

    }
    @Override public List<Class> getThrownExceptions(Method method) {
        if(method==null){
            throw new NullPointerException();
        }else {
            Class<?>[] exceptions = method.getExceptionTypes();
            List<Class> listOfAllExceptions=new ArrayList<>();
            for(Class<?> e:exceptions){
                listOfAllExceptions.add(e);
            }
            return listOfAllExceptions;
        }
    }
    @Override public Set<Class> getImplementedInterfaces(Class clazz) {
        if(clazz==null){
            throw new NullPointerException();
        }else {
            Class<?>[] interfaces=clazz.getInterfaces();
            Set<Class> setInterfaces=new HashSet<>();
            for(Class<?> c:interfaces) {
                setInterfaces.add(c);
            }
            return setInterfaces;
        }
    }
    @Override public String getFooFunctionResultForDefaultConstructedClass() {
        Class <?> clazz;
        try {
            clazz = forName("ru.ncedu.java.tasks.Reflections");
            clazz=clazz.getClasses()[0];

            Constructor<?> constructor= clazz.getDeclaredConstructor(new Class<?>[0]);
            constructor.setAccessible(true);

            Object secretClassInstance=constructor.newInstance(new Object[0]);

            Method method=clazz.getDeclaredMethod("foo",new Class<?>[0]);
            method.setAccessible(true);

            String result =(String)method.invoke(secretClassInstance,new Object[0]);
            return result;
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Class not found", e);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Method or constructor not found", e);
        } catch (SecurityException e){
            throw new IllegalStateException("Method is private", e);
        } catch (InstantiationException e) {
            throw new IllegalStateException("Constructor error1",e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Constructor error2",e);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Constructor error3",e);
        } catch (InvocationTargetException e) {
            throw new IllegalStateException("Constructor error4",e);
        }

    }
    @Override public String getFooFunctionResultForClass(String constructorParameter, String string, Integer... integers) {
        Class <?> clazz;
        try {
            clazz = forName("ru.ncedu.java.tasks.Reflections");
            clazz=clazz.getClasses()[0];

            Constructor<?> constructor= clazz.getDeclaredConstructor(String.class);
            constructor.setAccessible(true);

            Object secretClassInstance=constructor.newInstance(constructorParameter);

            Method method=clazz.getDeclaredMethod("foo",new Class<?>[]{String.class,Integer[].class});
            method.setAccessible(true);

            String result =(String)method.invoke(secretClassInstance,string,integers);
            return result;
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Class not found", e);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Method or constructor not found", e);
        } catch (SecurityException e){
            throw new IllegalStateException("Method is private", e);
        } catch (InstantiationException e) {
            throw new IllegalStateException("Constructor error1",e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Constructor error2",e);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Constructor error3",e);
        } catch (InvocationTargetException e) {
            throw new IllegalStateException("Constructor error4",e);
        }
    }
    @Override public List<Class> getExtendsHierarchy(Class clazz) {
        if(clazz==null){
            throw new NullPointerException();
        }else {
            List<Class> listSuperClasses = new ArrayList<>();
            Class myClazz=clazz.getSuperclass();//тут лежит уже суперкласс номер один
            while (myClazz!=null){
                listSuperClasses.add(myClazz);
                myClazz=myClazz.getSuperclass();
            }

            return listSuperClasses;
        }
    }
    /**
     * Метод возвращает набор всех методов класса, в т.ч. методов его суперклассов.
     * Возвращаемые методы могут иметь любые модификаторы.
     * Если в иерархии есть переопределенные методы, должны возвращаться все они
     * (а не только метод, переопределяющий остальные).<br/>
     *
     * @param clazz класс
     * @return Набор методов для всей иерархии классов.
     * @throws NullPointerException если clazz является null-ом
     */ @Override public Set<Method> getAllImplementedMethodsWithSupers(Class clazz) {
        Set<Method> ret = new HashSet<Method>();
        Method[] methods = clazz.getDeclaredMethods();
        for(Method x: methods)
            ret.add(x);

        if(clazz.getSuperclass() == null){
            return ret;
        }else{
            Set<Method> tmp = getAllImplementedMethodsWithSupers(clazz.getSuperclass());
            for(Method x: tmp)
                ret.add(x);
            return ret;
        }
    }


    /**
     * Метод возвращает текущее значение поля для данного экземпляра,
     * имеющего идентификатор private, public, protected или default.
     *
     * @param object    экземпляр класса
     * @param fieldName имя поля класса
     * @return Текущее значение поля
     * @throws NoSuchFieldException если поля с указанным именем не существует
     * @throws NullPointerException если fieldName or object является null-ом
     */ @Override public Object getFieldValueByName(Object object, String fieldName) throws NoSuchFieldException {
        if (object == null) {
            throw new NullPointerException();
        }
        Class cl = object.getClass();
        Field f = cl.getDeclaredField(fieldName);
        f.setAccessible(true);
        try {
            return f.get(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
