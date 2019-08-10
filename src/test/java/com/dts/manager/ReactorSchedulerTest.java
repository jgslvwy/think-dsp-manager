package com.dts.manager;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class ReactorSchedulerTest {
    private static List<String> words = Arrays.asList(
            "the",
            "quick",
            "brown",
            "fox",
            "jumped",
            "over",
            "the",
            "lazy",
            "dog"
    );

    @Test
    public void simpleCreation() {
        Flux<String> fewWords = Flux.just("Hello", "World");
        Flux<String> manyWords = Flux.fromIterable(words);
        fewWords.subscribe(System.out::println);
        System.out.println();
        manyWords.subscribe(System.out::println);
    }

    @Test
    public void testScheduler() {
//        Mono.fromCallable( () -> System.currentTimeMillis() )
//                .repeat()
//                .publishOn(Schedulers.single())
//                .log("scheduler task")
//                .flatMap(time ->
//                                Mono.fromCallable(() -> { Thread.sleep(10000); return time; })
//                                        .subscribeOn(Schedulers.parallel())
//                        , 8) //maxConcurrency 8
//                .subscribe();
        Scheduler single = Schedulers.single();
        Mono.fromCallable(() -> new Callable() {
            @Override
            public Object call() throws Exception {
                return System.currentTimeMillis();
            }
        }).publishOn(single).log("测试线程调度");
    }

}
