package top.moxingwang.simplemock.core.serialization;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastJSONSerialization implements MockSerialization {
    public byte[] serialize(Object data) {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);

        SerializeWriter out = new SerializeWriter();
        JSONSerializer serializer = new JSONSerializer(out);
        serializer.config(SerializerFeature.WriteEnumUsingToString, true);//<1>
        serializer.config(SerializerFeature.WriteClassName, true);//<1>
        serializer.write(data);
        return out.toBytes(CHARSET_NAME);
    }


    public <T> T deserialize(byte[] data, Class<T> clz) {
        return JSON.parseObject(new String(data), clz);
    }
}
