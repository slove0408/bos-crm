import cn.itcast.mavencrm.customer.domain.Customer;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by sun on 2017/7/25.
 */
public class Test {
    public static void main(String[] args) {
        String url = "http://localhost:9999/bos-crm/rs/customerService/customer";

       // 查询未关联定区客户信息
        url = url + "/findcustomerbyaddress/上海闵行区浦涛路768号";
        Customer list = WebClient.create(url).accept(MediaType.APPLICATION_JSON).get(Customer.class);
        System.out.println(list);


        //url = url + "/assigencusotmertodecidedzone/DQ001/6,7,9";
        // /customer/assigencusotmertodecidedzone/{cids}/{decidezoneId}
        //findcustomerbyaddress

    }
}
