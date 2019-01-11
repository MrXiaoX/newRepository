package cn.itrip.service.video;

import java.util.List;

public interface ItripVideoService {
    List<String> getHotelFeatureList(Long id);

    List<String> getTradingAreaNameList(Long id);
}
