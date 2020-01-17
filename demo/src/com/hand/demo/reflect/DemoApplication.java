package com.hand.demo.reflect;

import com.hand.demo.reflect.domain.entity.Student;

import java.lang.reflect.Method;

/**
 * @author yi.liang@hand-china.com
 * @version 0.0.1
 * @date 2020/1/17 17:48
 */
public class DemoApplication {

    public static void main(String[] args) {
        Class cls = Student.class;
        Method[] methods = cls.getMethods();

        for(Method m : methods){
            System.out.println(m.getName());
        }
    }
}
