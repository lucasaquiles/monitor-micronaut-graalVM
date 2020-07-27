package monitor.service;

import javax.inject.Singleton;
import java.util.Arrays;
import java.util.List;

@Singleton
public class MessageService {

    public List<String> getMessages() {

        return Arrays.asList(
                "Message1",
                "Message2"
        );
    }
}
