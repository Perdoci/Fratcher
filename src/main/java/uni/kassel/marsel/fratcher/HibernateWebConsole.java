package uni.kassel.marsel.fratcher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;

@Configuration
@ComponentScan
@Profile("Test")
public class HibernateWebConsole {

    @Bean(initMethod="start",destroyMethod="stop")
    public org.h2.tools.Server h2WebConsonleServer () throws SQLException {
        return org.h2.tools.Server.createWebServer("-web","-webAllowOthers","-webDaemon","-webPort", "8082");
    }
}
