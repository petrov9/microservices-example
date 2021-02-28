package org.example.license.events.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.license.events.CustomChannels;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.example.license.model.CheckerChangeModel;

@EnableBinding(CustomChannels.class)
@Slf4j
public class CheckChangeHandler {

    @StreamListener("inboundCheckChannel")
    public void loggerSink(CheckerChangeModel model) {
        log.info("Received an {} event from checker service for license {}", model.getAction(), model.getLicense());
    }
}
