package com.arohau.e2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("com.arohau.e2")
@EnableAspectJAutoProxy
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

        LogicV2 logic = context.getBean(LogicV2.class);

        logic.perform();
    }
}
