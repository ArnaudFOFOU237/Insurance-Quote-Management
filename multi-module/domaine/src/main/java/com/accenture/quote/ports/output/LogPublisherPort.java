package com.accenture.quote.ports.output;

import com.accenture.quote.model.log.AppLogEvent;

public interface LogPublisherPort {
    void publish(AppLogEvent event);
}
