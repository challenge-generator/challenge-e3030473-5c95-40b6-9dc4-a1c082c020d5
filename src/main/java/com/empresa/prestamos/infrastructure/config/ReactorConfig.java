package com.empresa.prestamos.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.Executor;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

@Configuration
public class ReactorConfig {

    @Bean
    public Scheduler reactorScheduler() {
        return Schedulers.newBoundedElastic(
                100,
                1000,
                "reactor-bounded",
                60,
                true
        );
    }

    @Bean
    public Executor taskExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }

    @Bean
    public reactor.core.scheduler.Scheduler creditCheckScheduler() {
        return Schedulers.boundedElastic();
    }

    @Bean
    public reactor.core.scheduler.Scheduler persistenceScheduler() {
        return Schedulers.parallel();
    }
}