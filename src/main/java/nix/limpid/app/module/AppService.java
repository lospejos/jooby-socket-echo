package nix.limpid.app.module;

import com.google.inject.Singleton;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Singleton
public class AppService {
    @PostConstruct
    public void start() {
        // ...
    }

    @PreDestroy
    public void stop() {
        // ...
    }
}
