package monitor.controller;



import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import monitor.controller.vo.MetricVO;
import monitor.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.validation.Valid;
import java.util.List;


@Controller("/message")
public class MessageController {

    private static final Logger LOG = LoggerFactory.getLogger(MessageController.class);

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Get
    public List<String> get() {
        LOG.info("get message");
        return messageService.getMessages();
    }

    @Post
    public void publish(@Valid MetricVO metricVO) {
        LOG.info("publish={}", metricVO);
        HttpResponse.created(metricVO);
    }
}
