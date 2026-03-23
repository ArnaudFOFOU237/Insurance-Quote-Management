package com.accenture.quote.model.log;


import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

public record AppLogEvent(
        String service,
        String level,
        String action,
        String message,
        UUID entityId,
        OffsetDateTime timestamp,
        Map<String, Object> metadata
) {}
