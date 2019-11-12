package io.github.hotspacode.neeza.client.serialization;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class KryoSerializationTest {


    public byte[] serialize(Object obj) {
        Kryo kryo = kryoLocal.get();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(byteArrayOutputStream);
        kryo.writeObject(output, obj);
        output.close();
        return byteArrayOutputStream.toByteArray();
    }

    public <T> T deserialize(byte[] bytes, Class<T> clz) {
        Kryo kryo = kryoLocal.get();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Input input = new Input(byteArrayInputStream);
        input.close();
        return (T) kryo.readObject(input, clz);
    }

    private static final ThreadLocal<Kryo> kryoLocal = new ThreadLocal<Kryo>() {
        @Override
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
            kryo.setReferences(true);
            kryo.setRegistrationRequired(false);
            return kryo;
        }
    };

    public static void main(String[] args) throws IOException {
/*        OrderResult orderResult = OrderResult.newSuccess();
        Order order = new Order();
        order.setSerialNumber("323232");
        order.setPayableAmount(new BigDecimal("1111110"));
        orderResult.setDataMap(order);

        KryoSerializationTest fastjsonSerialization = new KryoSerializationTest();
        byte[] serialize = fastjsonSerialization.serialize(orderResult);
        String temp = new String(serialize);
        OrderResult deserialize = fastjsonSerialization.deserialize(temp.getBytes(), OrderResult.class);
        System.out.println(1);*/
    }
}
