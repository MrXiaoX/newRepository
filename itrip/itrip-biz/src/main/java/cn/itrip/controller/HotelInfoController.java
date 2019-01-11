package cn.itrip.controller;


import cn.itrip.beans.dtos.Dto;
import cn.itrip.beans.pojo.ItripAreaDic;
import cn.itrip.beans.pojo.ItripHotelFeature;
import cn.itrip.beans.vo.ItripAreaDicVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.service.areadic.ItripAreaDicService;
import cn.itrip.service.hotel.ItripHotelService;
import cn.itrip.service.labeldic.ItripLabelDicService;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 酒店信息Controller
 * <p/>
 * 包括API接口：
 * 1、根据酒店id查询酒店拓展属性
 * 2、根据酒店id查询酒店介绍，酒店政策，酒店设施
 * 3、根据酒店id查询酒店特色属性列表
 * 4、根据type 和target id 查询酒店图片
 * 5、查询热门城市
 * 6、查询热门商圈列表（搜索-酒店列表）
 * 7、查询数据字典特色父级节点列表（搜索-酒店列表）
 * 8、根据酒店id查询酒店特色、商圈、酒店名称（视频文字描述）
 * <p/>
 * 注：错误码（100201 ——100300）
 * <p/>
 * Created by hanlu on 2017/5/9.
 */

/*@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")*/
/*@RequestMapping(value = "/api/hotel")*/
public class HotelInfoController {

    @Resource
    private ItripHotelService itripHotelService;

    @Resource
    private ItripAreaDicService itripAreaDicService;

    @Resource
    private ItripLabelDicService itripLabelDicService;

    @RequestMapping(value = "/queryhotcity/{cityId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripAreaDicVO> queryhotcity(@PathVariable Integer cityId) {
        System.out.println("是否是中国>>>>>>>>" + cityId);
        List<ItripAreaDic> itripAreaDics = null;
        List<ItripAreaDicVO> itripAreaDicVOs = null;
        try {
            if (EmptyUtils.isNotEmpty(cityId)) {
                itripAreaDics = itripHotelService.getItripAreaDicById(cityId);
                System.out.println("城市count>>" + itripAreaDics.size());
                if (EmptyUtils.isNotEmpty(itripAreaDics)) {
                    itripAreaDicVOs = new ArrayList();
                    for (ItripAreaDic dic : itripAreaDics) {
                        ItripAreaDicVO vo = new ItripAreaDicVO();
                        BeanUtils.copyProperties(dic, vo);
                        itripAreaDicVOs.add(vo);
                    }
                }
            } else {

                return DtoUtil.returnFail("cityId不能为空", "10201");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("获取常用城市信息失败", "100401");
        }
        return DtoUtil.returnSuccess("获取常用城市信息成功", itripAreaDics);
    }


    @RequestMapping(value = "/queryhotelfeature", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripHotelFeature> queryhotelfeature() {
        List<ItripHotelFeature> itripHotelFeatures = new ArrayList<ItripHotelFeature>();
        try {
            itripHotelFeatures = itripHotelService.getItripHotelFeature();
            System.out.println("城市count>>" + itripHotelFeatures.size());
            return DtoUtil.returnSuccess("获取常用城市信息成功", itripHotelFeatures);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("获取常用城市信息失败", "100401");
        }
    }
}
