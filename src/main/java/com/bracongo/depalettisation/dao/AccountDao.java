/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author f.tshizubu
 */
@Repository
public interface AccountDao extends JpaRepository<Account, Long> {

    /**
     *
     * @param id
     * @return
     */
    Optional<Account> findAccountByAccountId(Long id);

    int deleteAccountByAccountId(Long id);

    @Query("select a from Account a order by username asc")
    public List<Account> getAccountsOrderByUsernameAsc();

    @Query("select a from Account a where a.accountId=:id")
    public Account getAccountById(@Param("id") long id);

    @Query("select a,ag ,af from Account a inner join Agent ag on a.accountId = ag.account.accountId inner join "
            + " Affectation af on ag.agentId = af.agent.agentId where a.accountId =:accountId")
    public Object getDataAccountByAccountId(@Param("accountId") long accountId);

    @Query("select a from Account a where a.username =:username and a.password =:password")
    public Account getUserAccountByCredentials(@Param("username") String username, @Param("password") String password);
    
    @Query("select a from Account a where a.username =:username")
    public Account getUserAccountByUsername(@Param("username") String username);
}
