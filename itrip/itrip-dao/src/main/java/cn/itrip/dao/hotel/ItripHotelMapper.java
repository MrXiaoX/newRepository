package cn.itrip.dao.hotel;

import cn.itrip.beans.pojo.ItripAreaDic;
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripHotelFeature;
import cn.itrip.beans.pojo.ItripImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripHotelMapper {
       List<ItripImage> itripImgList(@Param(value = "id") Long id)throws Exception;

       List<ItripHotel> getItripHotelById(@Param(value = "id") Long id)throws Exception;

       ItripHotel getItripHotelBy(@Param(value = "id") Long id)throws Exception;

       List<ItripAreaDic> getItripAreaDicById(@Param(value = "isChina") Integer id)throws Exception;

       List<ItripHotelFeature> getItripHotelFeature()throws Exception;

       List<ItripHotel> getItripHotel()throws Exception;

       List<ItripHotel> itripHotelListByHotCity(Map<Integer,Object> param)throws Exception;

       List<ItripAreaDic> getItripAreaDicListByMap(Map<String, Object> param)throws Exception;

       List<ItripAreaDic> getItripAreaDic(@Param(value = "id") Long id);

}
