package cn.itrip.dao.room;

import cn.itrip.beans.pojo.ItripHotelRoom;
import cn.itrip.beans.pojo.ItripImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripImgMapper {
    List<ItripImage> itripImgList(@Param(value = "id") Long id);

    List<ItripHotelRoom> itripHotelRoomList(Map<String,Object> map) throws Exception;
}
