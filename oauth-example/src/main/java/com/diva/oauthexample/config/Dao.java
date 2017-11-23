package com.diva.oauthexample.config;

import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class Dao<E> {
	
	private final Class<E> entity;
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	protected Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
		 
	public Dao(Class<E> classe) {
		this.entity = classe;
	}

	@Transactional
	public boolean persist(E transientInstance) {
		sessionFactory.getCurrentSession().persist(transientInstance);
		return true;
	}
	
	@Transactional
	public boolean remove(E transientInstance) {
		sessionFactory.getCurrentSession().delete(transientInstance);
		return true;
	}
	
	@Transactional
	public boolean merge(E detachedInstance) {
		sessionFactory.getCurrentSession().merge(detachedInstance);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public E findById(int id) {
		E instance = (E) sessionFactory.getCurrentSession().get(entity, id);
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<E> findByStringField(String field, String value) {
		String expression = entity.toString();
		String className = new String();
		StringTokenizer st = new StringTokenizer(expression);
		while (st.hasMoreTokens()) {
			className = st.nextToken();
		}
		String query = "from "+className+" where "+field+" = :data";
		//System.out.println("Query :: " +query+":"+value);
		List<E> instance = sessionFactory.getCurrentSession().createQuery(query).setString("data", value).list();
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<E> findByStringsField(String field1, String value1, String field2, String value2) {
		String expression = entity.toString();
		String className = new String();
		StringTokenizer st = new StringTokenizer(expression);
		while (st.hasMoreTokens()) {
			className = st.nextToken();
		}
		String query = "from "+className+" where "+field1+" = :data1 and "+field2+" = :data2";
		
		List<E> instance = sessionFactory.getCurrentSession().createQuery(query)
						.setString("data1", value1)
						.setString("data2", value2)
						.list();
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<E> findByIntegerField(String field, Integer value) {
		String expression = entity.toString();
		String className = new String();
		StringTokenizer st = new StringTokenizer(expression);
		while (st.hasMoreTokens()) {
			className = st.nextToken();
		}
		String query = "from "+className+" where "+field+" = :data";
		List<E> instance = sessionFactory.getCurrentSession().createQuery(query).setInteger("data", value).list();
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<E> findAll() {
		return (List<E>) sessionFactory.getCurrentSession().createCriteria(entity).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}
	
}
