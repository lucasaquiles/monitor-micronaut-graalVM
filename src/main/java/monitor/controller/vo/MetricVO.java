package monitor.controller.vo;

import io.micronaut.core.annotation.Introspected;

import java.time.LocalDateTime;

@Introspected
public class MetricVO {

    private LocalDateTime dateCreated;
    private String content;
}
