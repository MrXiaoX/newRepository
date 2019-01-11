package cn.itrip.controller;

import cn.itrip.beans.dtos.Dto;
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.vo.ItripHotelVO;
import cn.itrip.beans.vo.hotel.SearchHotCityVO;
import cn.itrip.beans.vo.hotel.SearchHotelVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;
import cn.itrip.service.hotellist.ItripHotelListService;
import cn.itrip.service.hotellist.ItripSearchService;
import cn.itrip.service.hotellist.ItripSearchServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/hotellist")
public class HotelListController {

    @Resource
    private ItripSearchService itripSearchService;

    @RequestMapping(value = "/searchItripHotelListByHotCity", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Dto<Page<ItripHotelVO>> itripHotelSearch(@RequestBody SearchHotCityVO searchHotCityVO) {
        System.out.println("进来了》》》》》》");
        System.out.println("城市id>>>>>>>>" + searchHotCityVO.getCityId() + "count>" + searchHotCityVO.getCount());
        List<ItripHotelVO> itripHotelVOList = itripSearchService.searchHotel(searchHotCityVO.getCityId(), searchHotCityVO.getCount());
        if (EmptyUtils.isNotEmpty(itripHotelVOList)) {
            System.out.println("list>>>" + itripHotelVOList);
            return DtoUtil.returnSuccess("获取成功", itripHotelVOList);
        } else {
            return DtoUtil.returnSuccess("不能为空", itripHotelVOList);
        }
    }


    /****
     * 根据热门城市查询6个酒店的接口
     *
     * @param
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "搜索酒店（分页）", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询酒店分页(用于查询酒店列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>20001 : 系统异常，获取失败 </p>" +
            "<p>20002 : 目的地不能为空</p>")
    @RequestMapping(value = "/searchItripHotelPage", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Dto<Page<ItripHotelVO>> searchItripHotelPage(@RequestBody SearchHotelVO searchHotelVO) throws Exception {
        System.out.println("method searchItripHotelPage===============");
        Page page=null;
        if(EmptyUtils.isEmpty(searchHotelVO) || EmptyUtils.isEmpty(searchHotelVO.getDestination())){
            return DtoUtil.returnFail("目的地不能为空", "20002");
        }
        page=itripSearchService.searchItripHotelPage(searchHotelVO,searchHotelVO.getPageNo(),searchHotelVO.getPageSize());
        return DtoUtil.returnDataSuccess(page);
    }
}
