/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;
import com.bracongo.depalettisation.entities.Account;
import com.bracongo.depalettisation.entities.Message;
import java.util.List;

/**
 *
 * @author f.tshizubu
 */
public interface IAccountService{
    
    public Account saveOrUpdate(Account agent);

    public int delete(Long id);
    
    public Account getAccountById(Long id);
    
    public List<Account> getAccounts();
    
    public Object getUserDataById(long accountId);
    
    public Message Authentificate(String username,String password);
}
