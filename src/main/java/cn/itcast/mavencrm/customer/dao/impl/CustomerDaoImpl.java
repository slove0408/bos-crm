package cn.itcast.mavencrm.customer.dao.impl;

import cn.itcast.mavencrm.customer.dao.CustomerDao;
import cn.itcast.mavencrm.customer.domain.Customer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sun on 2017/7/25.
 */
@SuppressWarnings("all")
@Repository
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

    @Autowired
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }


    @Override
    public List<Customer> getNoAssociations() {
        List<Customer> list = getHibernateTemplate().find("from Customer where decidedzoneId is null");
        return list.isEmpty() ? null : list;
    }

    @Override
    public List<Customer> getInUseAssociations(String decidezone_id) {
        List<Customer> list = getHibernateTemplate().find("from Customer where decidedzoneId = ?", decidezone_id);
//        return list.isEmpty() ? null : list;
        return list;
    }

    @Override
    public void assignedCustomerToDecidedZone(String customer_id, String decidedZone_id) {
        getSession().createQuery("update Customer set decidedzoneId = ? where id = ?")
                .setParameter(0, decidedZone_id)
                .setParameter(1, Integer.parseInt(customer_id)).executeUpdate();

    }

    @Override
    public void cancleDecidedZoneCustomers(String decidedZone_id) {
        getSession().createQuery("update Customer set decidedzoneId = null where decidedzoneId = ?")
                .setParameter(0, decidedZone_id).executeUpdate();
    }

    @Override
    public Customer findCustomerByTelephone(String telephone) {
        List<Customer> list = getHibernateTemplate().find("from Customer where telephone = ?", telephone);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Customer save(Customer customer) {
        Serializable id = getHibernateTemplate().save(customer);
        customer.setId((Integer) id);
        return customer;
    }

    @Override
    public void updateAddressById(String customerid, String address) {
        getSession().createQuery("update Customer set address = ? where id = ?")
                .setParameter(0, address)
                .setParameter(1, Integer.parseInt(customerid)).executeUpdate();
    }

    @Override
    public Customer findCustomerByAddress(String address) {
        List<Customer> list = getHibernateTemplate().find("from Customer where address = ?", address);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void setDecidedzoneNull(String customerid) {
        getSession().createQuery("update Customer set decidedzoneId = null where id =?")
                .setParameter(0, Integer.parseInt(customerid)).executeUpdate();

    }
}
