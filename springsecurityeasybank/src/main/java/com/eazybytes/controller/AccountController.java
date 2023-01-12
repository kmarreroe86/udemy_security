package com.eazybytes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
public class AccountController {

    @GetMapping("/details")
    public String getAccountDetails() {
        return "Here are the account detail from Db";
    }

    @GetMapping("/my-balance")
    public String getBalanceDetails() {
        return "Here are the balance details from Db";

    }

    @GetMapping("/my-cards")
    public String getCardsDetails() {
        return "Here are the card details from Db";
    }
}
