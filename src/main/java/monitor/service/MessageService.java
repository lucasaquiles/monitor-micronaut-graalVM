package monitor.service;

import io.micronaut.core.annotation.Introspected;

import java.util.Arrays;
import java.util.List;

@Introspected
public class MessageService {

    public List<String> getMessages() {

        return Arrays.asList(
                "Message1",
                "Message2"
        );
    }
}
