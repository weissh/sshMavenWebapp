package dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import common.PageBean;
import entity.Student;

/**
 * A data access object (DAO) providing persistence and search support for
 * Student entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see entity.Student
 * @author MyEclipse Persistence Tools
 */

public class StudentDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(StudentDAO.class);
	// property constants
	public static final String CNO = "cno";
	public static final String CNAME = "cname";
	public static final String CTEACHER = "cteacher";

	protected void initDao() {
		// do nothing
	}

	public void save(Student transientInstance) {
		log.debug("saving Student instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Student persistentInstance) {
		log.debug("deleting Student instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Student findById(java.lang.Integer id) {
		log.debug("getting Student instance with id: " + id);
		try {
			Student instance = (Student) getHibernateTemplate().get(
					"entity.Student", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Student instance) {
		log.debug("finding Student instance by example");
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
		log.debug("finding Student instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Student as model where model."
					+ propertyName + "= ?";
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
		log.debug("finding all Student instances");
		try {
			String queryString = "from Student";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Student merge(Student detachedInstance) {
		log.debug("merging Student instance");
		try {
			Student result = (Student) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Student instance) {
		log.debug("attaching dirty Student instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Student instance) {
		log.debug("attaching clean Student instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static StudentDAO getFromApplicationContext(ApplicationContext ctx) {
		return (StudentDAO) ctx.getBean("StudentDAO");
	}
	
	@SuppressWarnings("rawtypes")
	public PageBean listpage(final int pagenum) {
		final String hql = "from Student";
		int num = 0; // 定义整个数据库中的信息记录数
		int allpage = 0; // 定义总页数
		final PageBean pageBean = new PageBean();
		num = (getHibernateTemplate().find(hql)).size(); // 先计算出整个数据记录信息
		pageBean.setAllRow(num); // 把记录数保存到pagebean中的allrow(总记录数)里面
		pageBean.setPageSize(8); // 设置每页显示几条信息记录
		allpage = PageBean.countTotalPage(pageBean.getPageSize(),
				pageBean.getAllRow()); // 根据每页显示几条和总条数计算出，要用多少页来显示
		pageBean.setTotalPage(allpage);
		if (allpage < pagenum) // 如果用户根据地址栏强制输入第几页，那么如果大于总页数，就要把总页数赋值给当前页，否则把传递过来的页数赋值给当前页数
		{
			pageBean.setCurrentPage(allpage);
		} else {
			pageBean.setCurrentPage(PageBean.countCurrentPage(pagenum));
		}
		pageBean.setOffset(PageBean.countOffset(pageBean.getPageSize(),
				pageBean.getCurrentPage())); // 根据每页显示的信息条数和当前页数，计算当前页开始记录号
		if (pageBean.getTotalPage() > 0) {
			// 首页判断
			// 前一页判断
			// 满足条件：当当前页为第一页的时候首页和前一页不可用，否则可以使用
			if (pageBean.getCurrentPage() == 1) {
				pageBean.setFirstPage(false);
				pageBean.setHasPreviousPage(false);
			} else {
				pageBean.setFirstPage(true);
				pageBean.setHasPreviousPage(true);
			}
			// 后一页判断
			// 尾页判断
			if (pageBean.getTotalPage() == pageBean.getCurrentPage()) {
				pageBean.setHasNextPage(false);
				pageBean.setLastPage(false);
			} else {
				pageBean.setHasNextPage(true);
				pageBean.setLastPage(true);
			}
		}
		// 通过回调函数把Goods根据条件查询的集合放到PageBean中的List里面
		pageBean.setList(getHibernateTemplate().executeFind
				(new org.springframework.orm.hibernate3.HibernateCallback() {
					public Object doInHibernate(org.hibernate.Session session)
							throws org.hibernate.HibernateException, java.sql.SQLException
					{
						org.hibernate.Query query = session.createQuery(hql);
						query.setFirstResult(pageBean.getOffset());
						query.setMaxResults(pageBean.getPageSize());
						List<Object> list = query.list();
						return list;
					}
				}));
		return pageBean;
	}
}