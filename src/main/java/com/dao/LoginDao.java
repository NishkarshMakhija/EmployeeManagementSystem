package com.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bean.Login;

@Repository
public class LoginDao {

	@Autowired
	EntityManagerFactory emf;
	
	public Login checkLogin(Login login) {
		try {
			EntityManager manager = emf.createEntityManager();
			EntityTransaction tran = manager.getTransaction();
			tran.begin();
				Login l = manager.find(Login.class, login.getUserid());
			tran.commit();
			if(l.getPassword().equals(login.getPassword())) {
				return l;
			}
			else {
				return null;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
