package com.pb.dao;

import com.pb.base.dao.GenericDao;
import com.pb.domain.User;

public interface UserDao extends GenericDao<User,Integer> {
	
	
	User validateUser(String name,String password);

}
