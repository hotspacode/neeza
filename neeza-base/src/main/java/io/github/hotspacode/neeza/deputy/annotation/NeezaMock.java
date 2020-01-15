package io.github.hotspacode.neeza.base.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface NeezaMock {
    /**
     * default extension name
     */
    String value() default "";
}