package com.pb.dao;

import com.pb.base.dao.GenericHibernateDao;
import com.pb.domain.User;

public class UerDaoImpl extends GenericHibernateDao<User,Integer> implements UserDao {

	@Override
	public User validateUser(String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
