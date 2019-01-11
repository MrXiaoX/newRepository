package cn.itrip.service.room;

import cn.itrip.beans.pojo.ItripHotelRoom;
import cn.itrip.dao.room.ItripImgMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ItripHotelRoomServiceImpl implements ItripHotelRoomService {
    @Resource
    private ItripImgMapper itripImgMapper;

    @Override
    public List<ItripHotelRoom> itripHotelRoomList(Map<String, Object> map) throws Exception {
        return itripImgMapper.itripHotelRoomList(map);
    }
}
