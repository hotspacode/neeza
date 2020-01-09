package io.github.hotspacode.neeza.transport.netty.http.codec;

import java.nio.charset.Charset;

/**
 * @author moxingwang
 */
public interface Encoder<R> {

    /**
     * Check whether the encoder supports the given source type.
     *
     * @param clazz type of the class
     * @return {@code true} if supported, {@code false} otherwise
     */
    boolean canEncode(Class<?> clazz);

    /**
     * Encode the given object into a byte array with the given charset.
     *
     * @param r the object to encode
     * @param charset the charset
     * @return the encoded byte buffer
     * @throws Exception error occurs when encoding the object (e.g. IO fails)
     */
    byte[] encode(R r, Charset charset) throws Exception;

    /**
     * Encode the given object into a byte array with the default charset.
     *
     * @param r the object to encode
     * @return the encoded byte buffer, witch is already flipped.
     * @throws Exception error occurs when encoding the object (e.g. IO fails)
     */
    byte[] encode(R r) throws Exception;
}