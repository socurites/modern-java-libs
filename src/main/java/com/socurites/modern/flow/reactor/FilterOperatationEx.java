package com.socurites.modern.flow.reactor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class FilterOperatationEx {
  private static Logger log = LoggerFactory.getLogger(FilterOperatationEx.class);

  public static void main(String[] args) throws InterruptedException {
//    skipByNumberFlux();
//    skipByDurationFlux();
//
//    takeByNumberFlux();
//    takeByDurationFlux();

    filterFlux();

    distinctFlux();
  }

  private static void distinctFlux() {
    log.info(String.format("Distinct Flux Ex ==================="));
    Flux<Integer> intFlux = Flux
      .just(1, 2, 1, 3, 2, 1, 5, 4, 4, 3);

    Flux<Integer> distinctFlux = intFlux
      .distinct();

    Disposable subscribe = distinctFlux.subscribe(
      i -> log.info(String.format("%d", i))
    );
  }

  private static void filterFlux() {
    log.info(String.format("Filtering Flux Ex ==================="));

    Flux<String> filterFlux = foodFlux()
      .filter(f -> f.startsWith("L"));

    Disposable subscribe = filterFlux.subscribe(
      f -> log.info(String.format("%s", f))
    );
  }

  private static void takeByDurationFlux() throws InterruptedException {
    log.info(String.format("Taking by duration Flux Ex ==================="));
    Flux<Integer> intFlux = Flux
      .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
      .delayElements(Duration.ofSeconds(1));

    Flux<Integer> takeFlux = intFlux
      .take(Duration.ofMillis(4500));

    Disposable subscribe = takeFlux.subscribe(
      i -> log.info(String.format("%d", i))
    );

    Thread.sleep(7000);
  }

  private static void takeByNumberFlux() {
    log.info(String.format("Taking by number Flux Ex ==================="));
    Flux<Integer> intFlux = Flux
      .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    Flux<Integer> takeFlux = intFlux
      .take(4);

    Disposable subscribe = takeFlux.subscribe(
      i -> log.info(String.format("%d", i))
    );
  }

  private static void skipByDurationFlux() throws InterruptedException {
    log.info(String.format("Skipping by duration Flux Ex ==================="));
    Flux<Integer> intFlux = Flux
      .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
      .delayElements(Duration.ofSeconds(1));

    Flux<Integer> skipFlux = intFlux
      .skip(Duration.ofSeconds(4));

    Disposable subscribe = skipFlux.subscribe(
      i -> log.info(String.format("%d", i))
    );

    Thread.sleep(5000);
  }

  private static void skipByNumberFlux() {
    log.info(String.format("Skipping by number Flux Ex ==================="));
    Flux<Integer> intFlux = Flux
      .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    Flux<Integer> skipFlux = intFlux
      .skip(3);

    skipFlux.subscribe(
      i -> log.info(String.format("%d", i))
    );
  }

  private static Flux<String> foodFlux() {
    Flux<String> foodFlux = Flux
      .just("Lasagna", "Lillipops", "Hamburger", "Chicken");

    return foodFlux;
  }
}
