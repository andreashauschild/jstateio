package io.jstate.spi;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LogEntry implements Serializable {

    private LogLevel level;
    private String message;
    private LocalDateTime timestamp;

    public LogEntry(LogLevel level, String message) {
        this.level = level;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public LogLevel getLevel() {
        return level;
    }
}
