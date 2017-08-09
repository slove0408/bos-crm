package cn.itcast.mavencrm.customer.dao;

import cn.itcast.mavencrm.customer.domain.Customer;

import java.util.List;

/**
 * Created by sun on 2017/7/25.
 */
public interface CustomerDao {

    //获取定区未关联的客户信息
    List<Customer> getNoAssociations();

    //获取指定定区绑定客户信息
    List<Customer> getInUseAssociations(String decidezone_id);

    //定区绑定客户
    void assignedCustomerToDecidedZone(String customer_id, String decidedZone_id);

    // 取消定区关联所有客户
    void cancleDecidedZoneCustomers(String decidedZone_id);

    public Customer findCustomerByTelephone(String telephone);

    public Customer save(Customer customer);

    public void updateAddressById(String customerid, String address);

    public Customer findCustomerByAddress(String address);

    public void setDecidedzoneNull(String customerid);


}
