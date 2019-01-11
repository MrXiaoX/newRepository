package cn.itrip.service.hotellist;

import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.dao.hotel.ItripHotelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ItripHotelListServiceImpl implements ItripHotelListService {

    @Resource
     private ItripHotelMapper itripHotelMapper;

    public List<ItripHotel> ItripHotelListByHotCity(Map<Integer, Object> param) throws Exception {
        return itripHotelMapper.itripHotelListByHotCity(param);
    }
}
