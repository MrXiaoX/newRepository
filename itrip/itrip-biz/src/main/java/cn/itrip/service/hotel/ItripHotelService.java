package cn.itrip.service.hotel;

import cn.itrip.beans.pojo.ItripAreaDic;
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripHotelFeature;
import cn.itrip.beans.pojo.ItripImage;

import java.util.List;

public interface ItripHotelService {
    List<ItripHotel> getItripHotelById(Long id) throws Exception;

    ItripHotel getItripHotelBy(Long id)throws Exception;

    List<ItripAreaDic> getItripAreaDicById(Integer id) throws Exception;

    List<ItripHotelFeature> getItripHotelFeature() throws Exception;

    List<ItripAreaDic> getItripAreaDic(Long id)throws Exception;

    List<ItripImage> getImgList(Long id)throws Exception;
}
