package cn.itrip.service.hotel;

import cn.itrip.beans.pojo.ItripAreaDic;
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripHotelFeature;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.dao.hotel.ItripHotelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItripHotelServiceImpl implements ItripHotelService {

    @Resource
    private ItripHotelMapper itripHotelMapper;


    @Override
    public List<ItripHotel> getItripHotelById(Long id) throws Exception {
        return itripHotelMapper.getItripHotelById(id);
    }

    @Override
    public ItripHotel getItripHotelBy(Long id) throws Exception {
        return itripHotelMapper.getItripHotelBy(id);
    }

    public List<ItripAreaDic> getItripAreaDicById(Integer id) throws Exception {
        return itripHotelMapper.getItripAreaDicById(id);
    }

    @Override
    public List<ItripHotelFeature> getItripHotelFeature() throws Exception {

        return itripHotelMapper.getItripHotelFeature();
    }

    @Override
    public List<ItripAreaDic> getItripAreaDic(Long id) throws Exception {
        return itripHotelMapper.getItripAreaDic(id);
    }

    @Override
    public List<ItripImage> getImgList(Long id) throws Exception {
        return itripHotelMapper.itripImgList(id);
    }
}
