package com.pb.base.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.pb.domain.Page;

public class GenericHibernateDao<T, ID extends Serializable> extends
		HibernateDaoSupport implements GenericDao<T, ID> {

	public GenericHibernateDao() {
		entityClass = (Class<?>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

		// TODO Auto-generated constructor stub
		
		
	}

	protected Class entityClass;

	public Class getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public void save(T entity) {
		getHibernateTemplate().save(entity);

	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(entity);

	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(entity);

	}

	@Override
	public T findById(ID id) {
		// TODO Auto-generated method stub

		return (T) getHibernateTemplate().get(getEntityClass(), id);
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(
				" from " + getEntityClass().getName());
	}

	@Override
	public Page findByPage(int currentPage, T entity) {
		// TODO Auto-generated method stub

		StringBuffer sb = createSQL(entity);

		final String countSQL = " select count(*) " + sb.toString();
		final String listSQL = sb.toString();

		Session session = null;

		final int from = (currentPage - 1) * 10;

		List list = null;
		Long count = null;

		count = getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				return session.createQuery(countSQL).uniqueResult();
			}
		});

		list = getHibernateTemplate().executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				return session.createQuery(listSQL).setFirstResult(from)
						.setMaxResults(10).list();
			}
		});
		// try {
		// session = HibernateUtil.getSessionFactory().getCurrentSession();
		//
		// session.beginTransaction();
		// count = (Long) session.createQuery(countSQL).uniqueResult();
		//
		// list = session.createQuery(listSQL).setFirstResult(from)
		// .setMaxResults(10).list();
		// session.getTransaction().commit();
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// session.getTransaction().rollback();
		// }

		int totalNumber = count.intValue();

		int totalPage = totalNumber % 10 == 0 ? totalNumber / 10
				: totalNumber / 10 + 1;

		Page page = new Page();
		page.setCurrentPage(currentPage);
		page.setList(list);
		page.setTotalNumber(totalNumber);
		page.setTotalPage(totalPage);

		return page;
	}

	protected StringBuffer createSQL(T entity) {
		StringBuffer sb = new StringBuffer(" from "
				+ getEntityClass().getName());

		// if (student.getName() != null && student.getName() != "") {
		// sb.append(" and s.name like '%").append(student.getName())
		// .append("%'");
		// }
		//
		// if (student.getEmail() != null && student.getEmail() != "") {
		// sb.append(" and s.email like '%").append(student.getEmail())
		// .append("%'");
		// }
		return sb;
	}

	//
	// protected Class entityClass;
	//
	//
	//
	//
	// public Class getEntityClass() {
	// return entityClass;
	// }
	//
	// public void setEntityClass(Class entityClass) {
	// this.entityClass = entityClass;
	// }
	//
	// @Override
	// public Object save(Object entity) {
	// // TODO Auto-generated method stub
	// Serializable result = null;
	// Session session = null;
	// try {
	// session = HibernateUtil.getSessionFactory().getCurrentSession();
	//
	// session.beginTransaction();
	// result = session.save(entity);
	//
	// session.getTransaction().commit();
	// } catch (HibernateException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// session.getTransaction().rollback();
	// }
	// return result;
	//
	// }
	//
	// @Override
	// public void delete(Object entity) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void update(Object entity) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public Object findById(Serializable id) {
	// // TODO Auto-generated method stub
	// Session session = null;
	// Object object = null;
	// try {
	// session = HibernateUtil.getSessionFactory().getCurrentSession();
	//
	// session.beginTransaction();
	// object = (Object) session.get(getEntityClass(), id);
	// session.getTransaction().commit();
	// } catch (HibernateException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// session.getTransaction().rollback();
	// }
	// return object;
	// }
	//
	// @Override
	// public List findAll() {
	// // TODO Auto-generated method stub
	// return null;
	// }

}
