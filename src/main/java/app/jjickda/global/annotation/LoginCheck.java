package app.jjickda.global.annotation;

import app.jjickda.domain.role.Role;
import app.jjickda.global.config.exception.Type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface LoginCheck {
    Role auth() default Role.USER;
    Type type() default  Type.API;

}
