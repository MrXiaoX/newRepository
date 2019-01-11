package cn.itrip.service.hotellist;

import cn.itrip.beans.vo.ItripHotelVO;
import cn.itrip.beans.vo.hotel.SearchHotelVO;
import cn.itrip.common.Page;

import java.util.List;

public interface ItripSearchService {
    List<ItripHotelVO> searchHotel(Integer keyword,Integer rows);

    public Page<ItripHotelVO> searchItripHotelPage(SearchHotelVO searchHotelVO, Integer pageNo, Integer pageSize) throws Exception;
}
