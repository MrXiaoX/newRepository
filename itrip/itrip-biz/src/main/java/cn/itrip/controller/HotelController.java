package cn.itrip.controller;

import cn.itrip.beans.dtos.Dto;
import cn.itrip.beans.pojo.ItripAreaDic;
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.ItripAreaDicVO;
import cn.itrip.beans.vo.ItripImageVO;
import cn.itrip.beans.vo.ItripLabelDicVO;
import cn.itrip.beans.vo.hotel.HotelVideoDescVO;
import cn.itrip.beans.vo.hotel.ItripSearchDetailsHotelVO;
import cn.itrip.beans.vo.hotel.ItripSearchFacilitiesHotelVO;
import cn.itrip.beans.vo.hotel.ItripSearchPolicyHotelVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.service.areadic.ItripAreaDicService;
import cn.itrip.service.hotel.ItripHotelService;
import cn.itrip.service.labeldic.ItripLabelDicService;
import cn.itrip.service.video.ItripVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/hotel")
public class HotelController {

    private Logger logger = Logger.getLogger(HotelController.class);

    @Resource
    private ItripAreaDicService itripAreaDicService;

    @Resource
    private ItripLabelDicService itripLabelDicService;

    @Resource
    private ItripHotelService itripHotelService;

    @Resource
    private ItripVideoService itripVideoService;

    /****
     * 查询热门城市的接口
     *
     * @param type
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询热门城市", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询国内、国外的热门城市(1:国内 2:国外)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>10201 : hotelId不能为空 </p>" +
            "<p>10202 : 系统异常,获取失败</p>")
    @RequestMapping(value = "/queryhotcity/{type}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripAreaDicVO> queryHotCity(@PathVariable Integer type) {
        List<ItripAreaDic> itripAreaDics = null;
        List<ItripAreaDicVO> itripAreaDicVOs = null;
        try {
            if (EmptyUtils.isNotEmpty(type)) {
                Map param = new HashMap();
                param.put("isHot", 1);
                param.put("isChina", type);
                itripAreaDics = itripAreaDicService.getItripAreaDicListByMap(param);
                if (EmptyUtils.isNotEmpty(itripAreaDics)) {
                    itripAreaDicVOs = new ArrayList();
                    for (ItripAreaDic dic : itripAreaDics) {
                        ItripAreaDicVO vo = new ItripAreaDicVO();
                        BeanUtils.copyProperties(dic, vo);
                        itripAreaDicVOs.add(vo);
                    }
                }
            } else {
                DtoUtil.returnFail("type不能为空", "10201");
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10202");
            e.printStackTrace();
        }
        return DtoUtil.returnDataSuccess(itripAreaDicVOs);
    }


    /***
     * 查询酒店特色列表
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询酒店特色列表", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "获取酒店特色(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/queryhotelfeature", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripLabelDicVO> queryHotelFeature() {
        List<ItripLabelDic> itripLabelDics = null;
        List<ItripLabelDicVO> itripAreaDicVOs = null;
        try {
            Map param = new HashMap();
            param.put("parentId", 16);
            itripLabelDics = itripLabelDicService.getItripLabelDicListByMap(param);
            System.out.println("特色列表进来了》》》》》》");
            if (EmptyUtils.isNotEmpty(itripLabelDics)) {
                itripAreaDicVOs = new ArrayList();
                for (ItripLabelDic dic : itripLabelDics) {
                    ItripLabelDicVO vo = new ItripLabelDicVO();
                    BeanUtils.copyProperties(dic, vo);
                    itripAreaDicVOs.add(vo);
                }
            }

        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnDataSuccess(itripAreaDicVOs);
    }


    /***
     * 查询城市商圈
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询城市商圈", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询城市商圈(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/querytradearea/{cityId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripAreaDicVO> queryTradeArea(@PathVariable Long cityId) {
        System.out.println("城市id》》》》》》" + cityId);
        System.out.println("商圈进来了》》。");
        List<ItripAreaDic> itripAreaDics = null;
        List<ItripAreaDicVO> itripAreaDicVOS = null;
        try {
            Map map = new HashMap();
            map.put("parent", cityId);
            itripAreaDics = itripAreaDicService.getItripAreaDicListByMap(map);
            if (EmptyUtils.isNotEmpty(itripAreaDics)) {
                itripAreaDicVOS = new ArrayList();
                for (ItripAreaDic dic : itripAreaDics) {
                    ItripAreaDicVO vo = new ItripAreaDicVO();
                    BeanUtils.copyProperties(dic, vo);
                    itripAreaDicVOS.add(vo);
                }
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnDataSuccess(itripAreaDicVOS);
    }

    /***
     * 查询视频信息
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询视频信息", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询视频信息(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/getvideodesc/{cityId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<HotelVideoDescVO> getVideoDesc(@PathVariable Long cityId) {
        System.out.println("城市id》》》》》》" + cityId);
        System.out.println("视频进来了》》。");
        HotelVideoDescVO hotelVideoDescVOS = null;
        List<String> featureList = null;
        List<String> TradingAreaNameList = null;
        ItripHotel itripHotel = null;
        try {
            featureList = itripVideoService.getHotelFeatureList(cityId);
            TradingAreaNameList = itripVideoService.getTradingAreaNameList(cityId);
            itripHotel = itripHotelService.getItripHotelBy(cityId);
            if (EmptyUtils.isNotEmpty(cityId)) {
                hotelVideoDescVOS = new HotelVideoDescVO();
                hotelVideoDescVOS.setHotelFeatureList(featureList);
                hotelVideoDescVOS.setHotelName(itripHotel.getHotelName());
                hotelVideoDescVOS.setTradingAreaNameList(TradingAreaNameList);
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("视频信息获取成功", hotelVideoDescVOS);
    }


    /***
     * 查询酒店描述和特色
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询酒店描述和特色", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询酒店描述和特色(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/queryhoteldetails/{cityId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripSearchDetailsHotelVO> queryHotelDetails(@PathVariable Long cityId) {
        System.out.println("城市id》》》》》》" + cityId);
        System.out.println("特色进来了》》。");
        ItripHotel itripHotels;
        List<ItripSearchDetailsHotelVO> itripSearchDetailsHotelVOS = null;
        try {
            ItripSearchDetailsHotelVO vo = new ItripSearchDetailsHotelVO();
            itripHotels = itripHotelService.getItripHotelBy(cityId);
            Map map = new HashMap();
            map.put("parentId", 16);
            itripSearchDetailsHotelVOS = itripLabelDicService.getItripLabelDicListByMap(map);
            if (EmptyUtils.isNotEmpty(itripSearchDetailsHotelVOS)) {
                vo.setName("酒店介绍");
                vo.setDescription(itripHotels.getDetails());
                itripSearchDetailsHotelVOS.add(0, vo);
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("查询酒店描述和特色成功", itripSearchDetailsHotelVOS);
    }


    /***
     * 查询酒店设施
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询酒店设施", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询酒店设施(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/queryhotelfacilities/{cityId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripSearchFacilitiesHotelVO> queryHotelFaciLities(@PathVariable Long cityId) {
        System.out.println("城市id》》》》》》" + cityId);
        System.out.println("设施1进来了》》。");
        ItripHotel itripHotel = null;
        ItripSearchFacilitiesHotelVO itripSearchFacilitiesHotelVOS = null;
        try {
            if (EmptyUtils.isNotEmpty(cityId)) {
                itripHotel = itripHotelService.getItripHotelBy(cityId);
                itripSearchFacilitiesHotelVOS = new ItripSearchFacilitiesHotelVO();
                itripSearchFacilitiesHotelVOS.setFacilities(itripHotel.getFacilities());
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("查询酒店设施1成功", itripSearchFacilitiesHotelVOS.getFacilities());
    }

    /***
     * 查询酒店政策
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询酒店政策", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询酒店政策(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    // queryhoteldetails
    @RequestMapping(value = "/queryhotelpolicy/{cityId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripSearchPolicyHotelVO> queryHotelPolicy(@PathVariable Long cityId) {
        System.out.println("城市id》》》》》》" + cityId);
        System.out.println("查询酒店政策》》。");
        ItripHotel itripHotels = null;
        ItripSearchPolicyHotelVO itripSearchPolicyHotelVOS = new ItripSearchPolicyHotelVO();
        try {
            if (EmptyUtils.isNotEmpty(cityId)) {
                itripHotels = itripHotelService.getItripHotelBy(cityId);
                itripSearchPolicyHotelVOS.setHotelPolicy(itripHotels.getHotelPolicy());
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("查询酒店政策成功", itripSearchPolicyHotelVOS.getHotelPolicy());
    }

    /***
     * 查询酒店图片
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询酒店图片", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询酒店图片(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    // queryhoteldetails
    @RequestMapping(value = "/getimg/{roomId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripImageVO> getHotelImg(@PathVariable Long roomId) {
        System.out.println("城市id》》》》》》" + roomId);
        System.out.println("查询酒店图片》》。");
        List<ItripImage> itripImages = null;
        List<ItripImageVO> itripImageVO = null;
        try {
            itripImages = itripHotelService.getImgList(roomId);
            if (EmptyUtils.isNotEmpty(itripImages)) {
                itripImageVO = new ArrayList();
                for (ItripImage img : itripImages) {
                    ItripImageVO vo = new ItripImageVO();
                    BeanUtils.copyProperties(img, vo);
                    itripImageVO.add(vo);
                }
                return DtoUtil.returnSuccess("查询酒店图片成功", itripImageVO);
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("");
    }
}
