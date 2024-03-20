package ru.vk.testtask.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.*;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import ru.vk.testtask.websocket.client.WebsocketClient;
import ru.vk.testtask.websocket.client.WebsocketClientEndpoint;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfigPostman implements WebSocketConfigurer {
    private final WebsocketClient websocketClient;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/ws");
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new WebSocketHandler() {
            @Override
            public void afterConnectionEstablished(WebSocketSession session) throws Exception {

            }

            @Override
            public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
                websocketClient.send(message.getPayload().toString());
                session.sendMessage(new TextMessage(message.getPayload().toString()));
            }

            @Override
            public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

            }

            @Override
            public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

            }

            @Override
            public boolean supportsPartialMessages() {
                return false;
            }
        };
    }
}
