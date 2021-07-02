package com.socurites.modern.flow.reactor;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Map;

public class TransformOperationEx {
  private static final Logger log = LoggerFactory.getLogger(TransformOperationEx.class);

  public static void main(String[] args) throws InterruptedException {
//    mapFlux();
//
//    flatMapFlux();

//    bufferFlux();

    collectListMono();

    collectMapMono();
  }

  private static void collectMapMono() {
    log.info(String.format("Collect Map Mono Ex ==================="));
    Flux<Integer> intFlux = Flux
      .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    Mono<Map<String, Integer>> mapMono = intFlux
      .collectMap(i -> String.valueOf(i));

    mapMono.subscribe(
      i -> log.info(i.toString())
    );

  }

  private static void collectListMono() {
    log.info(String.format("Collect List Mono Ex ==================="));
    Flux<Integer> intFlux = Flux
      .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    Mono<List<Integer>> intListMono = intFlux.collectList();

    intListMono.subscribe(
      i -> log.info(i.toString())
    );
  }

  private static void bufferFlux() throws InterruptedException {
    log.info(String.format("Buffer Flux Ex ==================="));
    Flux<Integer> intFlux = Flux
      .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    Flux<List<Integer>> bufferedFlux = intFlux
      .buffer(3);

    bufferedFlux
      .flatMap(x ->
        Flux.fromIterable(x)
          .map(y -> y * 2)
          .subscribeOn(Schedulers.parallel())
//          .log()
      ).subscribe(
        i -> log.info(String.format("%d", i))
    );

    Thread.sleep(5000);
  }

  private static void flatMapFlux() throws InterruptedException {
    log.info(String.format("Flat-Mapping Flux Ex ==================="));

    List<Player> players = List.of(new Player("John", 35),
      new Player("Michael", 23),
      new Player("Rachael", 13));

    Flux<String> mapFlux = Flux
      .fromIterable(players)
      .flatMap(p -> Mono.just(p)
        .map(Player::getDesc)
        .subscribeOn(Schedulers.parallel()));

    Disposable subscribe = mapFlux.subscribe(
      p -> log.info(p)
    );

    Thread.sleep(5000);
  }

  private static void mapFlux() {
    log.info(String.format("Mapping Flux Ex ==================="));
    List<Player> players = List.of(new Player("John", 35),
      new Player("Michael", 23),
      new Player("Rachael", 13));

    Flux<String> mapFlux = Flux
      .fromIterable(players)
      .map(Player::getDesc);

    Disposable subscribe = mapFlux.subscribe(
      p -> log.info(p)
    );
  }

  @Data
  @AllArgsConstructor
  static class Player {
    String name;
    int age;

    String getDesc() {
      return String.format("%s - %d", name, age);
    }
  }
}
