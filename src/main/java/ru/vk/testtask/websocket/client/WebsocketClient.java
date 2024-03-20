package ru.vk.testtask.websocket.client;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.vk.testtask.websocket.WebSocketConfigPostman;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class WebsocketClient {
    final private  WebsocketClientEndpoint clientEndPoint;
    final private WebSocketConfigPostman webSocketConfigPostman;

    public WebsocketClient(@Lazy WebSocketConfigPostman webSocketConfigPostman) throws URISyntaxException {
        this.webSocketConfigPostman = webSocketConfigPostman;
        this.clientEndPoint = new WebsocketClientEndpoint(new URI("wss://echo.websocket.org"));

        clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
            public void handleMessage(String message) {
                System.out.println(message);
            }
        });

    }

    public void send(String message) {
        this.clientEndPoint.sendMessage(message);
    }
}
