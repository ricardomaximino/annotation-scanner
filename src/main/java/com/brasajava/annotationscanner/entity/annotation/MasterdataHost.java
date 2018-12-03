package com.brasajava.annotationscanner.entity.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.brasajava.annotationscanner.entity.validator.MasterdataValidator;

@Documented
@Constraint(validatedBy = MasterdataValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MasterdataHost {
	static enum Level{
		ROOT,BRANCH;
	}
	Level level() default Level.BRANCH;
	String message() default "Invalid phone number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
