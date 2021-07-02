package com.socurites.modern.flow.reactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MatchOperationEx {
  private static final Logger log = LoggerFactory.getLogger(MatchOperationEx.class);

  public static void main(String[] args) {
    allMatchFlux();

    anyMatchFlux();
  }

  private static void anyMatchFlux() {
    log.info(String.format("Any Match Flux Ex ==================="));
    Flux<Integer> intFlux = Flux
      .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    Mono<Boolean> mono = intFlux
      .any(i -> 0 == i % 3);

    mono.subscribe(
      b -> log.info(String.valueOf(b))
    );
  }

  private static void allMatchFlux() {
    log.info(String.format("All Match Flux Ex ==================="));
    Flux<Integer> intFlux = Flux
      .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    Mono<Boolean> mono = intFlux
      .all(i -> i > 0);

    mono.subscribe(
      b -> log.info(String.valueOf(b))
    );
  }
}
