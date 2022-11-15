package app.jjickda.global.annotation;

import app.jjickda.domain.role.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface LoginCheck {
    Role auth() default Role.USER;
}
