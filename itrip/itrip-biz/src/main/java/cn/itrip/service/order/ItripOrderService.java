package cn.itrip.service.order;

import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.beans.vo.order.ItripAddHotelOrderVO;
import cn.itrip.beans.vo.order.ItripPersonalOrderRoomVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItripOrderService {
    List<ItripHotelOrder> getPreOrderInfo(ItripHotelOrder itripHotelOrder)throws Exception;

    List<ItripHotelOrder> getPersonalOrderInfo(Long id);

    Integer addOrderInfo(ItripAddHotelOrderVO itripAddHotelOrderVO);

    List<ItripPersonalOrderRoomVO> getOrderRoomInfo(Long hotelId);

}
