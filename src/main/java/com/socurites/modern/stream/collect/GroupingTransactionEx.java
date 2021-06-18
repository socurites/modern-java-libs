package com.socurites.modern.stream.collect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.socurites.modern.stream.collect.model.Transaction;
import com.socurites.modern.stream.collect.model.Transaction.Currency;

public class GroupingTransactionEx {
	public void groupingByCurrencyOldStyle() {
		List<Transaction> transactions = Transaction.transactions;
		
		Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
	    for (Transaction transaction : transactions) {
	      Currency currency = transaction.getCurrency();
	      List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
	      if (transactionsForCurrency == null) {
	        transactionsForCurrency = new ArrayList<>();
	        transactionsByCurrencies.put(currency, transactionsForCurrency);
	      }
	      transactionsForCurrency.add(transaction);
	    }
	}
	
	public void groupingByCurrencyStreamStyle() {
		List<Transaction> transactions = Transaction.transactions;
		
		Map<Currency, List<Transaction>> transactionsByCurrencies = 
				transactions.stream()
					.collect(Collectors.groupingBy(Transaction::getCurrency));
	}
}
