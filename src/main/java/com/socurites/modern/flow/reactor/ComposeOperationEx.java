package com.socurites.modern.flow.reactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;

public class ComposeOperationEx {
  private static Logger log = LoggerFactory.getLogger(ComposeOperationEx.class);

  public static void main(String[] args) throws InterruptedException {
    mergeFlux();

    zipFlux();
    zipToStringFlux();

    firstFlux();
  }

  private static void firstFlux() throws InterruptedException {
    log.info(String.format("Selecting First Flux Ex ==================="));
    Flux<String> fastFlux = Flux
      .just("hare", "cheetah", "squirrel");

    Flux<String> slowFlux = Flux
      .just("tortois", "snail", "sloth")
      .delaySubscription(Duration.ofMillis(100));

    Flux<String> firstFlux = Flux.first(slowFlux, fastFlux);

    Disposable subscribe = firstFlux.subscribe(
      d -> log.info(d)
    );

    log.info(String.format("Started to Sleep ==================="));
    Thread.sleep(5000);
    log.info(String.format("Ended to sleep ==================="));
  }

  private static void zipToStringFlux() throws InterruptedException {
    log.info(String.format("Zipping To String Flux Ex ==================="));

    Flux<String> zippedFlux = Flux
      .zip(characterFlux(), foodFlux(), (c, f) -> c + " eats " + f);

    Disposable subscribe = zippedFlux.subscribe(
      d -> log.info(d)
    );

    log.info(String.format("Started to Sleep ==================="));
    Thread.sleep(5000);
    log.info(String.format("Ended to sleep ==================="));
  }

  private static void zipFlux() throws InterruptedException {
    log.info(String.format("Zipping Flux Ex ==================="));
    Flux<Tuple2<String, String>> zippeFlux = Flux
      .zip(characterFlux(), foodFlux());

    Disposable subscribe = zippeFlux.subscribe(
      t -> log.info(String.format("Character: %s, Food: %s", t.getT1(), t.getT2()))
    );

    log.info(String.format("Started to Sleep ==================="));
    Thread.sleep(5000);
    log.info(String.format("Ended to sleep ==================="));
  }

  private static void mergeFlux() throws InterruptedException {
    log.info(String.format("Merge Flux Ex ==================="));

    Flux<String> mergedFlux = characterFlux()
      .mergeWith(foodFlux());

    Disposable subscribe = mergedFlux
      .subscribe(d -> log.info(String.format("item: %s", d)));

    log.info(String.format("Started to Sleep ==================="));
    Thread.sleep(5000);
    log.info(String.format("Ended to sleep ==================="));
  }

  private static Flux<String> characterFlux() {
    Flux<String> characterFlux = Flux
      .just("Garfield", "Kojak", "Barbosssa")
      .delayElements(Duration.ofMillis(500));

    return characterFlux;
  }

  private static Flux<String> foodFlux() {
    Flux<String> foodFlux = Flux
      .just("Lasagna", "Lillipops", "Hamburger", "Chicken")
      .delaySubscription(Duration.ofMillis(250))
      .delayElements(Duration.ofMillis(500));

    return foodFlux;
  }
}
