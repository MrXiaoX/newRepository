package cn.itrip.service.gitimg;


import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.dao.room.ItripImgMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItripImgServiceImpl implements ItripImgService {

    @Resource
    private ItripImgMapper itripImgMapper;

    public List<ItripImage> gitImage(Long id) {
        return itripImgMapper.itripImgList(id);
    }
}

