package lab5.tickets.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

// annotation for configuration
@ConfigurationProperties(prefix = "config")
@Configuration
@Getter
public class AppConfig {

    private final LoggerConfig logger = new LoggerConfig();
    private final EventConfig event = new EventConfig();

    @ConfigurationProperties(prefix = "logger")
    @Data
    public class LoggerConfig {
        private String level;
    }

    @ConfigurationProperties(prefix = "event")
    @Data
    public class EventConfig {
        private int maximumRow;
        private int maximumSeat;
        private List<String> names;
        private List<String> descriptions;
    }
}