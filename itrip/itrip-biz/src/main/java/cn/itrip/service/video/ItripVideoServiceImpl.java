package cn.itrip.service.video;

import cn.itrip.dao.video.ItripVideoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItripVideoServiceImpl implements ItripVideoService {

    @Resource
    ItripVideoMapper itripVideoMapper;

    @Override
    public List<String> getHotelFeatureList(Long id) {
        return itripVideoMapper.getHotelFeatureList(id);
    }

    @Override
    public List<String> getTradingAreaNameList(Long id) {
        return itripVideoMapper.getTradingAreaNameList(id);
    }
}
