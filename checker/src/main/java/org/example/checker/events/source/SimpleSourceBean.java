package org.example.checker.events.source;

import lombok.extern.slf4j.Slf4j;
import org.example.checker.events.model.CheckerChangeModel;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SimpleSourceBean {

    private Source source;

    public SimpleSourceBean(Source source) {
        this.source = source;
    }

    public void publishCheckerChange(String action, String license) {
        log.debug("Sending Kafka message {} for license: {}", action, license);

        CheckerChangeModel message = new CheckerChangeModel(
            CheckerChangeModel.class.getTypeName(),
            action,
            license
        );

        source.output().send(MessageBuilder.withPayload(message).build());
    }
}
