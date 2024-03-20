//package ru.vk.testtask.websocket;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RestController;
//import ru.vk.testtask.websocket.client.WebsocketClient;
//
//@RestController
//@RequiredArgsConstructor
//public class WebSocketController {
//    private final WebsocketClient websocketClient;
//
//    @MessageMapping("/process-message")
//    @SendTo("/topic/messages")
//    public Message processMessage( Message message) throws Exception {
//        System.out.println(message);
//        websocketClient.send(message.toString());
//        return message;
//    }
//}
