package io.github.hotspacode.neeza.transport.netty.http.codec;

import io.github.hotspacode.neeza.base.config.NeezaBaseConfig;

import java.nio.charset.Charset;


public class StringDecoder implements Decoder<String> {

    @Override
    public boolean canDecode(Class<?> clazz) {
        return String.class.isAssignableFrom(clazz);
    }

    @Override
    public String decode(byte[] bytes) throws Exception {
        return decode(bytes, Charset.forName(NeezaBaseConfig.DEFAULT_CHARSET));
    }

    @Override
    public String decode(byte[] bytes, Charset charset) {
        if (bytes == null || bytes.length <= 0) {
            throw new IllegalArgumentException("Bad byte array");
        }
        return new String(bytes, charset);
    }
}
