package com.pb.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.pb.common.HibernateUtil;

public class HibernateTemplate {

	public Object execute(HibernateCallback action) {

		Session session = null;
		Object result = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();

			session.beginTransaction();
			result = action.doInHibernate(session);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;

	}

}
