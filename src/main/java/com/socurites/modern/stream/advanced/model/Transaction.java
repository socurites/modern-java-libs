package com.socurites.modern.stream.advanced.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {
  private Trader trader;
  private int year;
  private int value;
}
