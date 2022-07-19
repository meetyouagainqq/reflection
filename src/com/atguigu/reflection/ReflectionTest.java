package com.atguigu.reflection;

import com.atguigu.reflection.entity.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest {
    //反射前
    @Test
    public void test1() {
        Person person = new Person("lishi123", 10);
        person.age = 10;
        System.out.println(person);
        person.show();
    }

    //反射之后
    @Test
    public void test2() throws Exception {
        Class personClass = Person.class;
        Constructor constructor = personClass.getConstructor(String.class, int.class);
        Object sunshine = constructor.newInstance("sunshine", 10);
        Person person = (Person) sunshine;
        System.out.println(person);
        Field age = personClass.getDeclaredField("age");
        age.set(person, 12);
        System.out.println(person);
        Method show = personClass.getDeclaredMethod("show");
        show.invoke(person);
        System.out.println("*******************");
        //通过反射调用类的私有构造方法
        Constructor constructor1=personClass.getDeclaredConstructor(String.class);
        constructor1.setAccessible(true);
        Person person1 = (Person) constructor1.newInstance("name的test");
        System.out.println(person1);
        System.out.println("*******************");
        //通过反射调用类的私有属性
        Field name = personClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(person1, "hahha改名字");
        System.out.println(person1);
    }


}
