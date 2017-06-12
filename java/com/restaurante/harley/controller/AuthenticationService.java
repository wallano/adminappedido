package com.restaurante.harley.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.restaurante.harley.dao.AccountDAO;
import com.restaurante.harley.entity.Account;

@Service
public class AuthenticationService implements UserDetailsService{
	
	@Autowired
    private AccountDAO iAccountFacade;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = iAccountFacade.findAccount(username);
        System.out.println("Account= " + account);
 
        if (account == null) {
            throw new UsernameNotFoundException("Usuario " //
                    + username + " no fue encontrado en la base de datos");
        }

        String role = account.getUserRole();
 
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
 
        grantList.add(authority);
 
        boolean enabled = account.isActive();
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
 
        UserDetails userDetails = (UserDetails) new User(account.getUserName(), //
                account.getPassword(), enabled, accountNonExpired, //
                credentialsNonExpired, accountNonLocked, grantList);
 
        return userDetails;
	}
 
    

}
