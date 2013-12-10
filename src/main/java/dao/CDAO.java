package dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entity.C;

/**
 * A data access object (DAO) providing persistence and search support for C
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see entity.C
 * @author MyEclipse Persistence Tools
 */

public class CDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(CDAO.class);
	// property constants
	public static final String CNO = "cno";
	public static final String CNAME = "cname";
	public static final String CTEACHER = "cteacher";

	protected void initDao() {
		// do nothing
	}

	public void save(C transientInstance) {
		log.debug("saving C instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(C persistentInstance) {
		log.debug("deleting C instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public C findById(java.lang.Integer id) {
		log.debug("getting C instance with id: " + id);
		try {
			C instance = (C) getHibernateTemplate().get("dao.C", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(C instance) {
		log.debug("finding C instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding C instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from C as model where model." + propertyName
					+ "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCno(Object cno) {
		return findByProperty(CNO, cno);
	}

	public List findByCname(Object cname) {
		return findByProperty(CNAME, cname);
	}

	public List findByCteacher(Object cteacher) {
		return findByProperty(CTEACHER, cteacher);
	}

	public List findAll() {
		log.debug("finding all C instances");
		try {
			String queryString = "from C";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public C merge(C detachedInstance) {
		log.debug("merging C instance");
		try {
			C result = (C) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(C instance) {
		log.debug("attaching dirty C instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(C instance) {
		log.debug("attaching clean C instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CDAO) ctx.getBean("CDAO");
	}
}