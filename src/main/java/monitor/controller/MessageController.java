package monitor.controller;


import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import monitor.service.MessageService;

import java.util.List;

@Controller("/message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Get("/")
    public List<String> get() {

        return messageService.getMessages();
    }
}
