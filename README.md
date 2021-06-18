# modern-java-libs

## 모던 자바 인 액선(8,9,10) 스터디 정리

## Java 8
### 요구사항
* 간결한 코드
* 멀티코어 프로세서의 쉬운 활용 (병렬처리)


### 신규 기능
* @FunctionalInterace

| @FunctionalInterface  | 기능  | 함수 syntax  | Method Syntax  | 스트림 연산  |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| Runnable  | 쓰레드  | () -> void  | int compare(T o1, T o2)  | -  |
| Callable<V>  | 쓰레드  | () -> V  | V call() throws Exception  | -  |
| Comparator<T>  | 정렬  | (T, T) -> int  | void run()  | sorted  |
| Predicate<T>  | 필터링  | (T) -> boolean  | boolean test(T t)  | filter, allMatch, anyMatch, noneMatch  |
| Consumer<T>  | 소비  | (T) -> void  | void accept(T t)  | forEach  |
| Function<T, R>  | 매핑  | (T) -> R  | R apply(T t)  | map  |
| Supplier<T>  | 생성  | () -> T  | T get()  | -  |
| BiFunction<T, U, R>  | 매핑  | (T, U) -> R  | R apply(T t, U u)  | reduce  |
| BinaryOperator<T>  | 리듀싱  | (T, T) -> T  | T get()  | reduce  |



* 스트림 API
    * 선언형 프로그래밍 like SQL
    * 병렬연산 지원
    * 조립할 수 있음
        * 중간 연산
            * 필터링: filter
            * 슬라이싱: takeWhile, dropWhile  <== Java9
            * 정렬: sorted
            * 검색: findAny, findFirst
            * 매칭: allMatch, anyMatch, noneMatch
            * 매핑: map
            * 리듀싱: reduce 
            * 축소: limit
            * 중복제거: distinct (stable only for ordered stream)
        * 단말(최종) 연산
            * 수집: collect
            * 갯수: count
            * 방문: foEach
        
    * 기타 특징
        * 게으른 연산
        * 쇼트서킷(short circuit)
        * 루프 퓨전(loop fusion)
        

* 메서드에 코드를 전달(메서드 참조 & 람다)
    * 동작 파라미터화(behavior parameterization) 
    * 7까지는 익명 클래스를 이용
    * 람다 -> 함수형 프로그래밍 가능

* 인터페이스의 디톨트 메서드


### 함수형 프로그래밍
* 무엇을 할 것인가를 기술하며 어떻게 할 것인가는 API에게 맡긴다


### Misc
* Optional<T>


## Java 9
### 신규 기능
* 모듈 시스템
* Flow API

### 리액티브 프로그래밍
* CompletableFuture


## Java 10
### 신규 기능
* 지역 변수 추론


