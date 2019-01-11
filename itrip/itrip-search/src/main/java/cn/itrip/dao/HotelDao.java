package cn.itrip.dao;


import cn.itrip.beans.vo.ItripHotelVO;
import cn.itrip.entity.Hotel;

import java.util.List;

public interface HotelDao {

    public List<Hotel> queryHotelList(String keyword);

    public List<ItripHotelVO> itripHotelVO(String keyword);
}
