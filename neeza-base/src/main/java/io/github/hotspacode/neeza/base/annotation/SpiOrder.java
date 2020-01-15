package io.github.hotspacode.neeza.base.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface SpiOrder {

    /**
     * Represents the lowest precedence.
     */
    int LOWEST_PRECEDENCE = Integer.MAX_VALUE;
    /**
     * Represents the highest precedence.
     */
    int HIGHEST_PRECEDENCE = Integer.MIN_VALUE;

    /**
     * The SPI precedence value. Lowest precedence by default.
     *
     * @return the precedence value
     */
    int value() default LOWEST_PRECEDENCE;
}
