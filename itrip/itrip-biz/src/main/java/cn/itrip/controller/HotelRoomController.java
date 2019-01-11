package cn.itrip.controller;

import cn.itrip.beans.dtos.Dto;
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripHotelRoom;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.ItripImageVO;
import cn.itrip.beans.vo.ItripLabelDicVO;
import cn.itrip.beans.vo.hotelroom.ItripHotelRoomVO;
import cn.itrip.beans.vo.hotelroom.SearchHotelRoomVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.service.gitimg.ItripImgService;
import cn.itrip.service.labeldic.ItripLabelDicService;
import cn.itrip.service.room.ItripHotelRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/hotelroom")
public class HotelRoomController {

    @Resource
    private ItripImgService itripImgService;

    @Resource
    private ItripLabelDicService itripLabelDicService;

    @Resource
    private ItripHotelRoomService itripHotelRoomService;

    @ApiOperation(value = "查询酒店房型图片", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询酒店房型图片(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/getimg/{roomId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripImageVO> ItripGetImg(@PathVariable Long roomId) {
        List<ItripImage> itripImages = null;
        List<ItripImageVO> itripImageVOS = null;
        System.out.println("查询酒店房型图片》》。");
        System.out.println("房型图片id>>>>>>>>" + roomId);
        try {
            itripImages = itripImgService.gitImage(roomId);
            if (EmptyUtils.isNotEmpty(itripImages)) {
                itripImageVOS = new ArrayList();
                for (ItripImage img : itripImages) {
                    ItripImageVO vo = new ItripImageVO();
                    BeanUtils.copyProperties(img, vo);
                    itripImageVOS.add(vo);
                }
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("查询酒店房型图片成功", itripImageVOS);
    }

    /**
     * 查询酒店房型列表
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询酒店房型列表", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询酒店房型列表(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/queryhotelroombyhotel", method = RequestMethod.POST)
    @ResponseBody
    public Dto<ItripHotelRoomVO> queryHotelRoombyHotel(@RequestBody SearchHotelRoomVO searchHotelRoomVO) throws Exception {
        List<ItripHotelRoom> itripHotelRoomList = null;
        List<ItripHotelRoomVO> itripHotelRoomVOS = null;
        System.out.println("查询酒店房型列表》》。" );
        try {
            Map param = new HashMap();
            param.put("hotelId", searchHotelRoomVO.getHotelId());
            param.put("isBook", searchHotelRoomVO.getIsBook());
            param.put("isHavingBreakfast", searchHotelRoomVO.getIsHavingBreakfast());
            param.put("endDate", new Date());
            itripHotelRoomList = itripHotelRoomService.itripHotelRoomList(param);
            if (EmptyUtils.isNotEmpty(itripHotelRoomList)) {
                itripHotelRoomVOS = new ArrayList();
                for (ItripHotelRoom room : itripHotelRoomList) {
                    ItripHotelRoomVO vo = new ItripHotelRoomVO();
                    BeanUtils.copyProperties(room, vo);
                    itripHotelRoomVOS.add(vo);
                }
                return DtoUtil.returnSuccess("查询酒店房型列表",itripHotelRoomVOS);
            }else {
                return DtoUtil.returnSuccess("查询酒店房型列表");
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnDataSuccess("");
    }

    /**
     * 查询所有房型
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询所有房型", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询所有房型" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/queryhotelroombed", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripLabelDicVO> queryHotelRoombed() {
        List<ItripLabelDic> itripLabelDics = null;
        System.out.println("查询所有房型》》。");
        List<ItripLabelDicVO> itripLabelDicVOs = null;
        try {
            itripLabelDics = itripLabelDicService.getBedName();
            if (EmptyUtils.isNotEmpty(itripLabelDics)) {
                itripLabelDicVOs = new ArrayList();
                for (ItripLabelDic dic : itripLabelDics) {
                    ItripLabelDicVO vo = new ItripLabelDicVO();
                    BeanUtils.copyProperties(dic, vo);
                    itripLabelDicVOs.add(vo);
                }
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("获取床型成功", itripLabelDicVOs);
    }
}
