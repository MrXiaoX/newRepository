package cn.itrip.service.img;

import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripHotelRoom;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.service.gitimg.ItripImgService;
import cn.itrip.service.hotel.ItripHotelService;
import cn.itrip.service.room.ItripHotelRoomService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItripImgTest {

    @Test
    public void ItripImgTest(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext-mybatis.xml");
        ItripHotelService itripImgService = ctx.getBean(ItripHotelService.class);
        try {
            Long id=180L;
            List<ItripImage> itripImages = itripImgService.getImgList(id);
            for (ItripImage itripImage : itripImages) {
                System.out.println("id:"+itripImage.getImgUrl()+">>>position"+itripImage.getTargetId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ItripTest(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext-mybatis.xml");
        ItripImgService itripImgService = ctx.getBean(ItripImgService.class);
        try {
            Long id=2L;
            List<ItripImage> itripImages = itripImgService.gitImage(id);
            for (ItripImage itripImage : itripImages) {
                System.out.println("id:"+itripImage.getImgUrl());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ItripRoomTest(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext-mybatis.xml");
        ItripHotelRoomService itripImgService = ctx.getBean(ItripHotelRoomService.class);
        try {
            Map map=new HashMap();
            map.put("hotelId",1);
            List<ItripHotelRoom> list=itripImgService.itripHotelRoomList(map);
            for (ItripHotelRoom room : list) {
                System.out.println("room"+room.getRoomTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
