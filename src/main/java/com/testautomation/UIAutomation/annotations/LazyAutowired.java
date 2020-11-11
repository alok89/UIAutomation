package com.testautomation.UIAutomation.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Lazy
@Autowired
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface LazyAutowired {

}
