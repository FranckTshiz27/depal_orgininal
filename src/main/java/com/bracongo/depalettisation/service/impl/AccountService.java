/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.service.impl;

import com.bracongo.depalettisation.exception.CustomNotFoundException;
import com.bracongo.depalettisation.dao.AccountDao;
import com.bracongo.depalettisation.dao.AgentDao;
import com.bracongo.depalettisation.dto.ChangeCredentialsDto;
import com.bracongo.depalettisation.dto.ChangePasswordDto;
import com.bracongo.depalettisation.dto.ChangePasswordResponseDto;
import com.bracongo.depalettisation.service.IAccountService;
import com.bracongo.depalettisation.entities.Account;
import com.bracongo.depalettisation.entities.Affectation;
import com.bracongo.depalettisation.entities.Agent;
import com.bracongo.depalettisation.entities.Message;
import com.bracongo.depalettisation.enumerations._Role;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import lombok.AllArgsConstructor;

/**
 *
 * @author f.tshizubu
 */
@Service
@Transactional
@AllArgsConstructor
public class AccountService implements IAccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AgentDao agentDao;
    @Autowired
    private AffectationService affectationService;


    @Override
    public int delete(Long id) {

        Optional<Account> deletingAccount = accountDao.findAccountByAccountId(id);

        if (deletingAccount != null) {
            accountDao.deleteAccountByAccountId(id);
            return 1;
        }

        return -1;
    }

    @Override
    public Account getAccountById(Long id) {
        return accountDao.findAccountByAccountId(id).
                orElseThrow(() -> new CustomNotFoundException("Le compte dont le id " + id + " est introuvable"));
    }

    @Override
    public List<Account> getAccounts() {
        return accountDao.getAccountsOrderByUsernameAsc();
    }

    @Override
    public Account saveOrUpdate(Account account) {
        return accountDao.save(account);
    }

    public Account changeCredentials(ChangeCredentialsDto changeCredentialsDto, long accountId) {
        Account existingAccount = accountDao.getAccountById(accountId);
        if (existingAccount != null && existingAccount.isActive() && !changeCredentialsDto.getPassword().equals(existingAccount.getPassword())) {
            existingAccount.setPassword(changeCredentialsDto.getPassword());
            return accountDao.save(existingAccount);
        }

        return null;
    }

    public ChangePasswordResponseDto changePassword(String username, ChangePasswordDto passwords) {
        ChangePasswordResponseDto changePasswordResponse = new ChangePasswordResponseDto();
        if (username.equals("") || passwords.getNewPassword().equals("") || passwords.getRepeatNewPassword().equals("")) {
            changePasswordResponse.setStatus(0);
            changePasswordResponse.setMessage("Veuillez renseigner toutes les données nécessaires pour procéder au changement de mot de passe");
            return changePasswordResponse;
        }

        if (!passwords.getNewPassword().equals(passwords.getRepeatNewPassword())) {
            changePasswordResponse.setStatus(0);
            changePasswordResponse.setMessage("Le nouveau mot de passe ne correspond pas au nouveau mot de passe répété");
            return  changePasswordResponse;
        }

        Account existingAccount = accountDao.getUserAccountByUsername(username);
        if (existingAccount == null) {
            changePasswordResponse.setStatus(0);
            changePasswordResponse.setMessage("Compte non trouvé");
            return changePasswordResponse;
        }

        if (existingAccount.isActive() == false) {
            changePasswordResponse.setStatus(0);
            changePasswordResponse.setMessage("Compte non activé");
            return changePasswordResponse;
        }
        
        existingAccount.setPassword(passwords.getNewPassword());
        changePasswordResponse.setStatus(1);
        changePasswordResponse.setMessage("Mot de passe changé avec succès");
        return changePasswordResponse;
    }

    public Account activate(Account account) {
        Account existingAccount = accountDao.getAccountById(account.getAccountId());

        if (existingAccount != null && !existingAccount.isActive() && !(account.getPassword().equals(existingAccount.getPassword())
                || (account.getUsername().equals(existingAccount.getUsername())))) {

            existingAccount.setPassword(account.getPassword());
            existingAccount.setUsername(account.getUsername());
            existingAccount.setActive(true);
            return accountDao.save(existingAccount);

        }

        return null;
    }

    @Override
    public Message Authentificate(String username, String password) {
        Account account = accountDao.getUserAccountByCredentials(username, password);

        Message message = new Message();

        if (account == null) {
            message.setStatus(false);
            message.setMessage("USER NOT FOUND");
        } else if (!account.isActive()) {
            message.setStatus(false);
            message.setMessage("DISABLED ACCOUNT");
            message.setAccountId(account.getAccountId());
        } else if (account.isActive()) {
            message.setStatus(true);
            message.setMessage("ACTIVATED ACCOUNT");
            message.setAccountId(account.getAccountId());
            message.setRole(account.get_role());
            Agent agent = agentDao.getAgentByAccount(account.getAccountId());
            Affectation affectation = affectationService.getCurrentAffectationByAccountId(account.getAccountId());
            message.setCurrentAffectation(affectation);
            message.setAgent(agent);

        }
        return message;
    }

    @Override
    public Object getUserDataById(long accountId) {
        return this.accountDao.getDataAccountByAccountId(accountId);
    }

    public Account updateAccount(Account account) {

        Optional<Account> savedAccount = accountDao.findAccountByAccountId(account.getAccountId());
        if (savedAccount != null) {
            return accountDao.save(account);
        }
        return null;
    }

}
