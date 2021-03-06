package com.testautomation.UIAutomation.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@Bean
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface BeanWithPrototypeScope {

}
