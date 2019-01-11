package cn.itrip.dao.hotelorder;

import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.beans.vo.order.ItripAddHotelOrderVO;
import cn.itrip.beans.vo.order.ItripPersonalOrderRoomVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItripOrderMapper {

    List<ItripHotelOrder> getPreOrderInfo(ItripHotelOrder itripHotelOrder)throws Exception;

    List<ItripHotelOrder> getPersonalOrderInfo(@Param("id") Long id);

    Integer addHotelOrder(ItripAddHotelOrderVO itripAddHotelOrderVO);

    List<ItripPersonalOrderRoomVO> getOrderRoomInfo(@Param("hotelId")Long hotelId );

}
