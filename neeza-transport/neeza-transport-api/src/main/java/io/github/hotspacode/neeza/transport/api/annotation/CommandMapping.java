package io.github.hotspacode.neeza.transport.api.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface CommandMapping {

    String name();

    String desc();
}
