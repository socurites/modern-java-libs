package com.socurites.modern.flow.reactor;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CreationOperationEx {

  public static void main(String[] args) {
    // 객체로부터 생성
    createFromOboject();

    // 컬렉션으로부터 생성
    createFromArray();
    createFromIterable();
    createFromStream();

    // Flux 데이터 생성
    createFromRange();
    createFromInterval();
  }

  private static void createFromInterval() {
    Flux<Long> intervalFlux = Flux
      .interval(Duration.ofSeconds(1))
      .take(5);

    intervalFlux.subscribe(
      i -> System.out.println("Generated long: " + i)
    );
  }

  private static void createFromRange() {
    Flux<Integer> rangeFlux = Flux
      .range(1, 5);

    rangeFlux.subscribe(
      i -> System.out.println("Generated int: " + i)
    );
  }

  private static void createFromStream() {
//    List<String> fruits = Arrays.asList("Apple", "Orange", "Grape", "Banana", "Strawwberry");
//    Stream<String> stream = fruits.stream();

    Stream<String> fruitStream = Stream.of("Apple", "Orange", "Grape", "Banana", "Strawwberry");

    Flux<String> fruitFlux = Flux
      .fromStream(fruitStream);

    fruitFlux.subscribe(
      f -> System.out.println("Here's some fruit: " + f)
    );
  }

  private static void createFromIterable() {
    List<String> fruits = Arrays.asList("Apple", "Orange", "Grape", "Banana", "Strawwberry");

    Flux<String> fruitFlux = Flux
      .fromIterable(fruits);

    fruitFlux.subscribe(
      f -> System.out.println("Here's some fruit: " + f)
    );
  }

  private static void createFromArray() {
    String[] fruits = new String[] {"Apple", "Orange", "Grape", "Banana", "Strawwberry"};

    Flux<String> fruitFlux = Flux
      .fromArray(fruits);

    fruitFlux.subscribe(
      f -> System.out.println("Here's some fruit: " + f)
    );
  }

  public static void createFromOboject() {
    Flux<String> fruitFlux = Flux
      .just("Apple", "Orange", "Grape", "Banana", "Strawwberry");

    fruitFlux.subscribe(
      f -> System.out.println("Here's some fruit: " + f)
    );
  }
}
