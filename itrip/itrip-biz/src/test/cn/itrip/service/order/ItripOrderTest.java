
package cn.itrip.service.order;

import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.beans.vo.comment.ItripHotelDescVO;
import cn.itrip.beans.vo.order.ItripPersonalOrderRoomVO;
import cn.itrip.service.comment.ItripCommentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class ItripOrderTest {


  /*  @Test
    public void ItripHotelDesc() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext-mybatis.xml");
        ItripOrderService itripOrderService = ctx.getBean(ItripOrderService.class);
        try {
            ItripHotelOrder itripHotelOrder=new ItripHotelOrder();
            itripHotelOrder.setCheckInDate();
            List<ItripHotelOrder> itripHotelOrderList = itripOrderService.getPreOrderInfo(itripHotelOrder);
            for (ItripHotelDescVO itripComment : itripComments) {
                System.out.println("itripComment"+itripComment.getHotelName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    @Test
    public void ItripHotelDesc() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext-mybatis.xml");
        ItripOrderService itripOrderService = ctx.getBean(ItripOrderService.class);
        try {
            List<ItripPersonalOrderRoomVO> itripHotelOrder=itripOrderService.getOrderRoomInfo(44L);
            for (ItripPersonalOrderRoomVO itripPersonalOrderRoomVO : itripHotelOrder) {
                System.out.println(itripPersonalOrderRoomVO.getAddress());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

