package io.github.hotspacode.neeza.transport.client.http;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;


/**
 * @author moxingwang
 */
public class TransportClientTest {
    public static void main(String[] args) {

    }

    @Test
    public void testClient() {
        TransportClient transportClient = new TransportClient();
        CompletableFuture<Object> objectCompletableFuture = transportClient.executeCommand("localhost", 8818, "ssdsd", false).thenApply(json -> {
            System.out.println(json);
            return json;
        });


    }
}

