package cn.itrip.service.order;

import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.beans.vo.order.ItripAddHotelOrderVO;
import cn.itrip.beans.vo.order.ItripPersonalOrderRoomVO;
import cn.itrip.dao.hotelorder.ItripOrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItripOrderServiceImpl implements ItripOrderService{

    @Resource
    private ItripOrderMapper itripOrderMapper;

    @Override
    public List<ItripHotelOrder> getPreOrderInfo(ItripHotelOrder itripHotelOrder) throws Exception {
        return itripOrderMapper.getPreOrderInfo(itripHotelOrder);
    }

    @Override
    public List<ItripHotelOrder> getPersonalOrderInfo(Long id) {
        return itripOrderMapper.getPersonalOrderInfo(id);
    }

    @Override
    public Integer addOrderInfo(ItripAddHotelOrderVO itripAddHotelOrderVO) {
        return itripOrderMapper.addHotelOrder(itripAddHotelOrderVO);
    }

    @Override
    public List<ItripPersonalOrderRoomVO> getOrderRoomInfo(Long hotelId) {
        return itripOrderMapper.getOrderRoomInfo(hotelId);
    }
}
