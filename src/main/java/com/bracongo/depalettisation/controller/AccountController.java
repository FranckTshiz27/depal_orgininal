/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.dto.AuthentificateAccountDto;
import com.bracongo.depalettisation.dto.ChangeCredentialsDto;
import com.bracongo.depalettisation.dto.ChangePasswordDto;
import com.bracongo.depalettisation.dto.ChangePasswordResponseDto;
import com.bracongo.depalettisation.service.impl.AccountService;
import com.bracongo.depalettisation.entities.Account;
import com.bracongo.depalettisation.entities.Message;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author f.tshizubu
 */
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Component
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> create(@Valid @RequestBody Account account) {
        Account newAccount = accountService.saveOrUpdate(account);
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }

    @PostMapping("/authentificate")
    public ResponseEntity<Message> create(@Valid @RequestBody AuthentificateAccountDto authentificateAccountDto) {
        Message message = accountService.Authentificate(authentificateAccountDto.getUsername(), authentificateAccountDto.getPassword());
        
        switch (message.getMessage()) {
            case "ACTIVATED ACCOUNT":
                return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
            case "DISABLED ACCOUNT":
                return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
            default:
                return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<Account> update(@RequestBody Account account, @PathVariable("id") long id) {
        account.setAccountId(id);
        Account updateAccount = accountService.updateAccount(account);
        return new ResponseEntity<>(updateAccount, HttpStatus.OK);
    }

    @PatchMapping("/activateAccount/{id}")
    public ResponseEntity<Account> activate(@RequestBody Account account, @PathVariable("id") long id) {
        account.setAccountId(id);
        Account updateAccount = accountService.activate(account);
        return new ResponseEntity<>(updateAccount, HttpStatus.OK);
    }

    @PatchMapping("/changeCredentials/{id}")
    public ResponseEntity<Account> changeCredentials(@RequestBody ChangeCredentialsDto changeCredentialsDto, @PathVariable("id") long id) {
        Account updateAccount = accountService.changeCredentials(changeCredentialsDto, id);
        return new ResponseEntity<>(updateAccount, HttpStatus.OK);
    }

    @PatchMapping("/changePassword/{username}")
    public ResponseEntity<ChangePasswordResponseDto> changePassword(@RequestBody ChangePasswordDto passwords, @PathVariable("username") String username) {
        ChangePasswordResponseDto response = accountService.changePassword(username, passwords);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {

        if (accountService.delete(id) > 1) {
            return new ResponseEntity<>(1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(-1, HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @GetMapping
    public ResponseEntity<List<Account>> findAll() {
        List<Account> accounts = accountService.getAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Account> findById(@PathVariable("id") Long id) {
        Account account = accountService.getAccountById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/agent/{id}")
    public ResponseEntity<Account> findAccountByAgentId(@PathVariable("id") Long id) {
        Account account = accountService.getAccountById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
