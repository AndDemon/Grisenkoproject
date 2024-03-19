package com.rx.javajxpr;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Приклад анотації з різними політиками утримання
@Retention(RetentionPolicy.RUNTIME) // Анотація буде доступна під час виконання програми
@Target(ElementType.METHOD) // Анотація може бути використана лише для методів
@interface ExampleAnnotation {
    String value() default "";
}

class MyClass {
    @ExampleAnnotation
    public void myMethod() {
        // Логіка методу
    }
}

public class observex {
    public static void main(String[] args) {
        MyClass obj = new MyClass();
        // Демонстрація використання рефлексії для отримання анотацій
        try {
            ExampleAnnotation annotation = obj.getClass().getMethod("myMethod").getAnnotation(ExampleAnnotation.class);
            if (annotation != null) {
                System.out.println("Annotation value: " + annotation.value());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
