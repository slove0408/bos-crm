package cn.itcast.mavencrm.customer.service.impl;

import cn.itcast.mavencrm.customer.dao.CustomerDao;
import cn.itcast.mavencrm.customer.service.CustomerService;
import cn.itcast.mavencrm.customer.domain.Customer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by sun on 2017/7/25.
 */
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<Customer> getNoAssociations() {
        return customerDao.getNoAssociations();
    }

    @Override
    public List<Customer> getInUseAssociations(String decidedzoneId) {
        return customerDao.getInUseAssociations(decidedzoneId);
    }

    @Override
    public void assignedCustomerToDecidedZone(String customerids, String decidedZone_id) {
        //给客户重新关联定区 先解除之前用户定区绑定 再重新绑定
        //先取消所有定区关联客户
        customerDao.cancleDecidedZoneCustomers(decidedZone_id);

        if (customerids.equals("noCustomerIds")) {
            return;
        }
        //重新绑定
        if (StringUtils.isNoneBlank(customerids)) {
            String customerIds[] = customerids.split(",");
            for (String id : customerIds) {
                customerDao.assignedCustomerToDecidedZone(id,decidedZone_id);
            }
        }
    }

    @Override
    public Customer findCustomerByTelephone(String telephone) {
        return customerDao.findCustomerByTelephone(telephone);
    }

    @Override
    public Customer findCustomerByAddress(String address) {
        return customerDao.findCustomerByAddress(address);
    }

    @Override
    public Customer save(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public void updateAdressById(String customerid, String address) {
        customerDao.updateAddressById(customerid,address);
        customerDao.setDecidedzoneNull(customerid);
    }
}
