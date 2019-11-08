package top.moxingwang.simplemock.core.serialization;

public interface MockSerialization {
    String CHARSET_NAME = "UTF-8";


    byte[] serialize(Object data);

    <T> T deserialize(byte[] data, Class<T> clz);
}
