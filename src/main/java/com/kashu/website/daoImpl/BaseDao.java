package com.kashu.website.daoImpl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

//BaseDao主要意義為定義Dao基本常用的幾個操作行為(CRUD)，
//以便後續新增的XXXDaoImpl有一個基礎的功能可以使用，免去程式碼重複算寫的麻煩
//故日後只須根據業務邏輯所需來新增function即可
public abstract class BaseDao<PK extends Serializable, T> {
		
	public static Log INFO = LogFactory.getLog("db-info");
	public static Log ERROR = LogFactory.getLog("db-error");

	@Autowired
	private SessionFactory sessionFactory;

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public BaseDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[1];
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}

}