package cn.itrip.service.comment;

import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.vo.ItripLabelDicVO;
import cn.itrip.beans.vo.comment.ItripCountVO;
import cn.itrip.beans.vo.comment.ItripHotelDescVO;


import java.util.List;

public interface ItripCommentService {

    List<ItripComment> getHotelScore(Long userId);

    List<ItripCountVO> getCount(Long cityId);

    List<ItripImage> getImgList(Long cityId);

    List<ItripLabelDicVO> getTravelType();

    List<ItripHotelDescVO> getHotelDesc(Long hotelId);

    List<ItripComment> getCommentList(ItripComment itripComment)throws Exception;
}
