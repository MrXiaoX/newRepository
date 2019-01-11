package cn.itrip.service.gitimg;

import cn.itrip.beans.pojo.ItripImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItripImgService {

    List<ItripImage> gitImage(@Param(value = "id")Long id);
}

