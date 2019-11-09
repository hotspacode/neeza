package io.github.hotspacode.neeza.deputy.api;

public interface INeezaSerialization {

    byte[] serialize(Object data);

    <T> T deserialize(byte[] data, Class<T> clz);

}
