package cn.itrip.service.hotellist;

import cn.itrip.beans.pojo.ItripHotel;
import java.util.List;
import java.util.Map;

public interface ItripHotelListService {

    List<ItripHotel> ItripHotelListByHotCity(Map<Integer,Object> param)throws Exception;
}
