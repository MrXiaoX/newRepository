
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.service.hotellist.ItripHotelListService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItripSearchTest {

    @Test
    public void ItripSearchList() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext-mybatis.xml");
        ItripHotelListService itripHotelListService = ctx.getBean(ItripHotelListService.class);
        try {
            Map param = new HashMap();
            param.put("cityId", 3657);
            param.put("count", 6);

            List<ItripHotel> itripHotel = itripHotelListService.ItripHotelListByHotCity(param);
            for (ItripHotel hotel : itripHotel) {
                System.out.println(hotel.getHotelName() + " 测试id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

