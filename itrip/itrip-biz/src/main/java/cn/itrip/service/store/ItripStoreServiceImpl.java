package cn.itrip.service.store;

import cn.itrip.beans.pojo.ItripHotelTempStore;
import cn.itrip.dao.store.ItripStoreMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ItripStoreServiceImpl implements ItripStoreService  {

    @Resource
    private ItripStoreMapper itripStoreMapper;

    @Override
    public ItripHotelTempStore getHotelTempStore(ItripHotelTempStore itripHotelTempStore) {
        return itripStoreMapper.getHotelTempStore(itripHotelTempStore);
    }
}
