package io.github.hotspacode.neeza.core.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.TYPE_USE})
public @interface SimpleMock {
    /**
     * default extension name
     */
    String value() default "";
}