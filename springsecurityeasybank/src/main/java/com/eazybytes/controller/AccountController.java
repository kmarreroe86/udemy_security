package com.eazybytes.controller;

import com.eazybytes.model.AccountTransactions;
import com.eazybytes.model.Accounts;
import com.eazybytes.model.Cards;
import com.eazybytes.repository.AccountTransactionsRepository;
import com.eazybytes.repository.AccountsRepository;
import com.eazybytes.repository.CardsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {


    private final AccountsRepository accountsRepository;

    private final AccountTransactionsRepository accountTransactionsRepository;

    private final CardsRepository cardsRepository;

    public AccountController(AccountsRepository accountsRepository, AccountTransactionsRepository accountTransactionsRepository, CardsRepository cardsRepository) {
        this.accountsRepository = accountsRepository;
        this.accountTransactionsRepository = accountTransactionsRepository;
        this.cardsRepository = cardsRepository;
    }

    @GetMapping("/details")
    public Accounts getAccountDetails(@RequestParam int id) {

        Accounts accounts = accountsRepository.findByCustomerId(id);
        return accounts;
    }

    @GetMapping("/my-balance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam int id) {
        List<AccountTransactions> accountTransactions = accountTransactionsRepository.
                findByCustomerIdOrderByTransactionDtDesc(id);

        return accountTransactions;
    }

    @GetMapping("/my-cards")
    public List<Cards> getCardDetails(@RequestParam int id) {
        List<Cards> cards = cardsRepository.findByCustomerId(id);

        return cards;
    }
}
