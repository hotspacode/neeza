package io.github.hotspacode.neeza.transport.client.http;

import org.junit.Test;


/**
 * @author moxingwang
 */
public class TransportClientTest {
    public static void main(String[] args) {

    }

    @Test
    public void testClient() {
        TransportClient transportClient = new TransportClient();
        transportClient.executeCommand("localhost", 8818, "ssdsd",false);
    }
}

