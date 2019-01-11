package cn.itrip.dao.comment;

import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.vo.ItripLabelDicVO;
import cn.itrip.beans.vo.comment.ItripCountVO;
import cn.itrip.beans.vo.comment.ItripHotelDescVO;
import cn.itrip.beans.vo.comment.ItripSearchCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripCommentMapper {

    List<ItripComment> getHotelScore(@Param(value = "hotelId") Long cityId);

    List<ItripCountVO> getCount(@Param("hotelId") Long cityId);

    List<ItripComment> getCommentList(ItripComment itripComment) throws Exception;

    List<ItripImage> getImgList(@Param("targetId") Long targetId);

    List<ItripLabelDicVO> getTravelType();

    List<ItripHotelDescVO> getHotelDesc(@Param("hotelId") Long cityId);
}
