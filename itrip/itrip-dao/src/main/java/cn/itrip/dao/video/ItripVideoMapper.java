package cn.itrip.dao.video;

import java.util.List;

public interface ItripVideoMapper {
    List<String> getHotelFeatureList(Long id);

    List<String> getTradingAreaNameList(Long id);
}
