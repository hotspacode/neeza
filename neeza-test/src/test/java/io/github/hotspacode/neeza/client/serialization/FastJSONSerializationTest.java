package io.github.hotspacode.neeza.client.serialization;

import org.junit.Test;
import io.github.hotspacode.neeza.core.serialization.FastJSONSerialization;

import java.util.ArrayList;
import java.util.List;

public class FastJSONSerializationTest {
    @Test
    public void test() {
        FastJSONSerialization fastJSONSerialization = new FastJSONSerialization();
       /* Map<String, String> obj = new HashMap<>();
        obj.put("key1", "string1");
        obj.put("key2", "string2");
        obj.put("key3", "string3");*/

        byte obj = 1;
//       int obj = 1;

        System.out.println(new String(fastJSONSerialization.serialize(obj)));
    }

    @Test
    public void testArrayList() {
        FastJSONSerialization fastJSONSerialization = new FastJSONSerialization();
        List<String> obj = new ArrayList<>();
        obj.add("1");
        obj.add("2");
        obj.add("3");
        System.out.println(new String(fastJSONSerialization.serialize(obj)));
    }
}
