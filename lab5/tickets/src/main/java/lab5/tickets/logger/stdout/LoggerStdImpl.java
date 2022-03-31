package lab5.tickets.logger.stdout;

import lab5.tickets.config.AppConfig;
import lab5.tickets.vo.LoggerLevel;
import lab5.tickets.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Profile({"local", "naroden", "stairichen"})
@Component("stdLogger")
public class LoggerStdImpl implements Logger {

    @Autowired
    private AppConfig appConfig;

    private void log(LoggerLevel logLevel, Object toLog) {
        String level = appConfig.getLogger().getLevel();

        if(LoggerLevel.valueOf(level).getCode() >= logLevel.getCode()) {
            System.out.println(LocalDateTime.now() + " [" + logLevel.name() + "] " + toLog);
        }
    }

    @Override
    public void info(Object toLog) {
        this.log(LoggerLevel.INFO, toLog);
    }

    @Override
    public void debug(Object toLog) {
        this.log(LoggerLevel.DEBUG, toLog);
    }

    @Override
    public void trace(Object toLog) {
        this.log(LoggerLevel.TRACE, toLog);
    }

    @Override
    public void error(Object toLog) {
        this.log(LoggerLevel.ERROR, toLog);
    }
}
