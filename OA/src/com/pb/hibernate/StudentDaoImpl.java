package com.pb.hibernate;

import org.hibernate.Session;

import com.pb.domain.Student;

public class StudentDaoImpl {
	
	
	HibernateTemplate hibernateTemplate=new HibernateTemplate();

	public void saveStudent(Student student) {
		
		final Student s=student;
		hibernateTemplate.execute(new HibernateCallback(){

			@Override
			public Object doInHibernate(Session session) {
				// TODO Auto-generated method stub
				session.save(s);
				return null;
			}});
//		Session session = null;
//		try {
//			session = HibernateUtil.getSessionFactory().getCurrentSession();
//
//			session.beginTransaction();
//			session.save(student);
//			session.getTransaction().commit();
//		} catch (HibernateException e) {
//			// TODO Auto-generated catch block
//			session.getTransaction().rollback();
//			e.printStackTrace();
//		}
	}
	
	public void updateStudent(Student student) {
		
		final Student s=student;
		class UpdateAction implements HibernateCallback{

			@Override
			public Object doInHibernate(Session session) {
				// TODO Auto-generated method stub
				session.update(s);
				return null;
			}
			
		}
		hibernateTemplate.execute(new UpdateAction());
//		Session session = null;
//		try {
//			session = HibernateUtil.getSessionFactory().getCurrentSession();
//
//			session.beginTransaction();
//			session.update(student);
//			session.getTransaction().commit();
//		} catch (HibernateException e) {
//			// TODO Auto-generated catch block
//			session.getTransaction().rollback();
//			e.printStackTrace();
//		}
	}

}
