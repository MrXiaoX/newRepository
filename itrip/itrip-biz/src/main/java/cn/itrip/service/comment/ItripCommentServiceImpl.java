package cn.itrip.service.comment;

import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.vo.ItripLabelDicVO;
import cn.itrip.beans.vo.comment.ItripCountVO;
import cn.itrip.beans.vo.comment.ItripHotelDescVO;
import cn.itrip.dao.comment.ItripCommentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItripCommentServiceImpl implements ItripCommentService {
    @Resource
    private ItripCommentMapper itripCommentMapper;


    @Override
    public List<ItripComment> getHotelScore(Long userId) {
        return itripCommentMapper.getHotelScore(userId);
    }

    @Override
    public List<ItripCountVO> getCount(Long cityId) {
        return itripCommentMapper.getCount(cityId);
    }

    @Override
    public List<ItripImage> getImgList(Long cityId) {
        return itripCommentMapper.getImgList(cityId);
    }

    @Override
    public List<ItripLabelDicVO> getTravelType() {
        return itripCommentMapper.getTravelType();
    }

    @Override
    public List<ItripHotelDescVO> getHotelDesc(Long hotelId) {
        return itripCommentMapper.getHotelDesc(hotelId);
    }

    @Override
    public List<ItripComment> getCommentList(ItripComment itripComment) throws Exception{
        return itripCommentMapper.getCommentList(itripComment);
    }
}
