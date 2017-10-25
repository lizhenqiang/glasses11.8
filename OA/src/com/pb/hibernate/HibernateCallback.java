package com.pb.hibernate;

import org.hibernate.Session;

public interface HibernateCallback {
	
	
	Object doInHibernate(Session session);

}
