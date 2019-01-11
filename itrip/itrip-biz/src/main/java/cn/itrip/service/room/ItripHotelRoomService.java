package cn.itrip.service.room;

import cn.itrip.beans.pojo.ItripHotelRoom;

import java.util.List;
import java.util.Map;

public interface ItripHotelRoomService {
    List<ItripHotelRoom> itripHotelRoomList(Map<String,Object> map) throws Exception;
}
