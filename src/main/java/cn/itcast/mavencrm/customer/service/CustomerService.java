package cn.itcast.mavencrm.customer.service;

import cn.itcast.mavencrm.customer.domain.Customer;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by sun on 2017/7/25.
 */
@SuppressWarnings("all")
@Produces("*/*")
public interface CustomerService {

    //客户端查询未关联客户请求
    @GET
    @Path("/customer/noassociation")
    @Produces({"applcation/xml","application/json"})
    List<Customer> getNoAssociations();

    //客户端查询已关联客户请求
    @GET
    @Path("/customer/getinuseassociations/{decidedzoneId}")
    @Consumes({"applcation/xml","application/json"})
    @Produces({"applcation/xml","application/json"})
    List<Customer> getInUseAssociations(@PathParam("decidedzoneId")String decidedzoneId);

    @PUT
    @Path("/customer/assignedcusotmertodecidedzone/{decidedzoneId}/{cids}")
    @Consumes({ "application/xml", "application/json" })
    void assignedCustomerToDecidedZone(@PathParam("cids") String customerids, @PathParam("decidedzoneId") String decidedZone_id);

    @GET
    // 客户端 查询请求
    @Path("/customer/findcustomerbytelephone/{telephone}")
    // http://xxxx/user/1
    @Consumes({ "application/xml", "application/json" })
    // 客户端 只能发送 xml 数据类型
    @Produces({ "application/xml", "application/json" })
    Customer findCustomerByTelephone(@PathParam("telephone") String telephone);

    @GET
    // 客户端 查询请求
    @Path("/customer/findcustomerbyaddress/{address}")
    // http://xxxx/user/1
    @Consumes({ "application/xml", "application/json" })
    // 客户端 只能发送 xml 数据类型
    @Produces({ "application/xml", "application/json" })
    public Customer findCustomerByAddress(@PathParam("address") String address);

    @POST
    // 客户端 查询请求
    @Path("/customer/save")
    // http://xxxx/user/1
    @Consumes({ "application/xml", "application/json" })
    // 客户端 只能发送 xml 数据类型
    @Produces({ "application/xml", "application/json" })
    public Customer save(Customer customer);

    @PUT
    @Path("/customer/updateadressbyid/{customerid}/{address}")
    @Consumes({ "application/xml", "application/json" })
    public void updateAdressById(@PathParam("customerid") String customerid, @PathParam("address") String address);
}
