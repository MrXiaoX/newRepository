package cn.itrip.controller;

import cn.itrip.beans.dtos.Dto;
import cn.itrip.beans.pojo.*;
import cn.itrip.beans.vo.order.ItripAddHotelOrderVO;
import cn.itrip.beans.vo.order.ItripPersonalHotelOrderVO;
import cn.itrip.beans.vo.order.ItripPersonalOrderRoomVO;
import cn.itrip.beans.vo.order.PreAddOrderVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.service.order.ItripOrderService;
import cn.itrip.service.store.ItripStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/hotelorder")
public class HotelOrderController {
    @Resource
    private ItripOrderService itripOrderService;

    @Resource
    private ItripStoreService itripStoreService;

    /****
     * 预生产订单信息
     *
     * @param
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "预生产订单信息", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "预生产订单信息" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>10201 : hotelId不能为空 </p>" +
            "<p>10202 : 系统异常,获取失败</p>")
    @RequestMapping(value = "/getpreorderinfo", method = RequestMethod.POST)
    @ResponseBody
    public Dto<PreAddOrderVO> getPreOrderInfo(@RequestBody ItripHotelOrder itripHotelOrder) throws Exception {
        List<ItripHotelOrder> itripHotelOrders = null;
        // ItripHotelTempStore itripHotelTempStore = null;
        // ItripHotelTempStore itripHotelTempStore1 = null;
        PreAddOrderVO preAddOrderVO = null;
        System.out.println("订单信息进来了");
        try {
            itripHotelOrders = itripOrderService.getPreOrderInfo(itripHotelOrder);
            if (EmptyUtils.isNotEmpty(itripHotelOrder)) {
                System.out.println("订单count>>" + itripHotelOrders.size());
              /*  itripHotelTempStore1 = new ItripHotelTempStore();
                itripHotelTempStore1.setRoomId(itripHotelOrder.getRoomId());
                itripHotelTempStore1.setHotelId(Integer.parseInt(itripHotelOrder.getHotelId().toString()));
                itripHotelTempStore = itripStoreService.getHotelTempStore(itripHotelTempStore1);
                System.out.println("list" + itripHotelTempStore);
                System.out.println("store>>" + itripHotelTempStore.getStore() + "对象" + itripHotelTempStore);*/
                preAddOrderVO = new PreAddOrderVO();
                for (ItripHotelOrder hotelOrder : itripHotelOrders) {
                    preAddOrderVO.setStore(20);
                    preAddOrderVO.setCheckInDate(hotelOrder.getCheckInDate());
                    preAddOrderVO.setCheckOutDate(hotelOrder.getCheckOutDate());
                    preAddOrderVO.setHotelId(hotelOrder.getHotelId());
                    preAddOrderVO.setPrice(hotelOrder.getPayAmount());
                    preAddOrderVO.setRoomId(hotelOrder.getRoomId());
                    preAddOrderVO.setCount(hotelOrder.getCount());
                    preAddOrderVO.setHotelName(hotelOrder.getHotelName());
                    System.out.println("HotelName" + hotelOrder.getHotelName());
                }
                return DtoUtil.returnSuccess("预生产订单信息成功", preAddOrderVO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("预生产订单信息失败", "100401");
        }
        return DtoUtil.returnSuccess();
    }


    /****
     * 获取个人订单信息
     *
     * @param
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取个人订单信息", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "预生产订单信息" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>10201 : hotelId不能为空 </p>" +
            "<p>10202 : 系统异常,获取失败</p>")
    @RequestMapping(value = "/getpersonalorderinfo/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripPersonalHotelOrderVO> getPersonalOrderInfo(@PathVariable Long orderId) throws Exception {
        List<ItripHotelOrder> itripHotelOrders = null;
        List<ItripPersonalHotelOrderVO> itripPersonalHotelOrderVOS = null;
        ItripPersonalHotelOrderVO itripPersonalHotelOrderVO = null;
        System.out.println("获取个人订单信息");
        try {
            itripHotelOrders = itripOrderService.getPersonalOrderInfo(orderId);
            if (EmptyUtils.isNotEmpty(itripHotelOrders)) {
                System.out.println("信息count>>" + itripHotelOrders.size());
                itripPersonalHotelOrderVOS = new ArrayList();
                for (ItripHotelOrder itripHotelOrder : itripHotelOrders) {
                    System.out.println("orderNo" + itripHotelOrder.getOrderNo());
                    itripPersonalHotelOrderVO = new ItripPersonalHotelOrderVO();
                    Map map = new HashMap();
                    map.put("1", "订单提交");
                    map.put("2", "订单支付");
                    map.put("3", "支付成功");
                    map.put("4", "入住");
                    map.put("5", "订单点评");
                    map.put("6", "订单完成");
                    itripPersonalHotelOrderVO.setOrderProcess(map);
                    itripPersonalHotelOrderVO.setProcessNode("6");
                 /*   System.out.println("type"+itripHotelOrder.getRoomPayType());
                    itripPersonalHotelOrderVO.setRoomPayType(itripHotelOrder.getRoomPayType());*/
                    BeanUtils.copyProperties(itripHotelOrder, itripPersonalHotelOrderVO);
                    itripPersonalHotelOrderVOS.add(itripPersonalHotelOrderVO);
                }
                return DtoUtil.returnSuccess("获取个人订单信息成功", itripPersonalHotelOrderVO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("获取个人订单信息失败", "100401");
        }
        return DtoUtil.returnSuccess();
    }

    /****
     * 添加订单信息
     *
     * @param
     * @return
     * @throws Exception
     */
  /*  @ApiOperation(value = "添加订单信息", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "添加订单信息" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>10201 : hotelId不能为空 </p>" +
            "<p>10202 : 系统异常,获取失败</p>")
    @RequestMapping(value = "/addhotelorder", method = RequestMethod.POST)
    @ResponseBody
    public Dto<ItripAddHotelOrderVO> addHotelOrder(@RequestBody ItripAddHotelOrderVO itripAddHotelOrderVO) throws Exception {
        List<ItripHotelOrder> itripHotelOrders = null;
        List<ItripPersonalHotelOrderVO> itripPersonalHotelOrderVOS = null;
        ItripPersonalHotelOrderVO itripPersonalHotelOrderVO = null;
        System.out.println("添加订单信息");
        try {
            itripHotelOrders = itripOrderService.getPersonalOrderInfo(orderId);
            if (EmptyUtils.isNotEmpty(itripHotelOrders)) {
                System.out.println("信息count>>" + itripHotelOrders.size());
                itripPersonalHotelOrderVOS = new ArrayList();
                for (ItripHotelOrder itripHotelOrder : itripHotelOrders) {
                    System.out.println("orderNo" + itripHotelOrder.getOrderNo());
                    itripPersonalHotelOrderVO = new ItripPersonalHotelOrderVO();
                    Map map = new HashMap();
                    map.put("1", "订单提交");
                    map.put("2", "订单支付");
                    map.put("3", "支付成功");
                    map.put("4", "入住");
                    map.put("5", "订单点评");
                    map.put("6", "订单完成");
                    itripPersonalHotelOrderVO.setOrderProcess(map);
                    itripPersonalHotelOrderVO.setProcessNode("6");
                 *//*   System.out.println("type"+itripHotelOrder.getRoomPayType());
                    itripPersonalHotelOrderVO.setRoomPayType(itripHotelOrder.getRoomPayType());*//*
                    BeanUtils.copyProperties(itripHotelOrder, itripPersonalHotelOrderVO);
                    itripPersonalHotelOrderVOS.add(itripPersonalHotelOrderVO);
                }
                return DtoUtil.returnSuccess("添加订单信息成功", itripPersonalHotelOrderVO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("添加订单信息失败", "100401");
        }
        return DtoUtil.returnSuccess();
    }*/


    /****
     * 获取个人订单房型信息
     *
     * @param
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取个人订单房型信息", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "预生产订单信息" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>10201 : hotelId不能为空 </p>" +
            "<p>10202 : 系统异常,获取失败</p>")
    @RequestMapping(value = "/getpersonalorderroominfo/{hotelId}", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripPersonalOrderRoomVO> getOrderRoomInfo(@PathVariable Long hotelId) throws Exception {
        List<ItripPersonalOrderRoomVO> itripPersonalOrderRoomVOS = null;
        ItripPersonalOrderRoomVO itripPersonalHotelOrderVO = null;
        System.out.println("获取个人订单房型信息");
        try {
            itripPersonalOrderRoomVOS = itripOrderService.getOrderRoomInfo(hotelId);
            if (EmptyUtils.isNotEmpty(itripPersonalOrderRoomVOS)) {
                System.out.println("信息count>>" + itripPersonalOrderRoomVOS.size());
                for (ItripPersonalOrderRoomVO itripPersonalOrderRoomVO : itripPersonalOrderRoomVOS) {
                    System.out.println("地址" + itripPersonalOrderRoomVO.getAddress());
                    itripPersonalHotelOrderVO = new ItripPersonalOrderRoomVO();
                    itripPersonalHotelOrderVO.setCheckInWeek(2);
                    itripPersonalHotelOrderVO.setAddress(itripPersonalOrderRoomVO.getAddress());
                    itripPersonalHotelOrderVO.setBookingDays(itripPersonalOrderRoomVO.getBookingDays());
                    itripPersonalHotelOrderVO.setCount(itripPersonalOrderRoomVO.getCount());
                    itripPersonalHotelOrderVO.setHotelId(itripPersonalOrderRoomVO.getHotelId());
                    itripPersonalHotelOrderVO.setId(itripPersonalOrderRoomVO.getId());
                    itripPersonalHotelOrderVO.setCheckInDate(itripPersonalOrderRoomVO.getCheckInDate());
                    itripPersonalHotelOrderVO.setCheckOutDate(itripPersonalOrderRoomVO.getCheckOutDate());
                    itripPersonalHotelOrderVO.setSpecialRequirement(itripPersonalOrderRoomVO.getSpecialRequirement());
                    itripPersonalHotelOrderVO.setHotelLevel(itripPersonalOrderRoomVO.getHotelLevel());
                    itripPersonalHotelOrderVO.setHotelName(itripPersonalOrderRoomVO.getHotelName());
                    itripPersonalHotelOrderVO.setPayAmount(itripPersonalOrderRoomVO.getPayAmount());
                    itripPersonalHotelOrderVO.setLinkUserName(itripPersonalOrderRoomVO.getLinkUserName());
                    itripPersonalHotelOrderVO.setRoomId(itripPersonalOrderRoomVO.getRoomId());
                    itripPersonalHotelOrderVO.setRoomPayType(itripPersonalOrderRoomVO.getRoomPayType());
                    itripPersonalHotelOrderVO.setRoomBedTypeName(itripPersonalOrderRoomVO.getRoomBedTypeName());
                    itripPersonalHotelOrderVO.setRoomTitle(itripPersonalOrderRoomVO.getRoomTitle());
                    itripPersonalHotelOrderVO.setRoomBedTypeId(itripPersonalOrderRoomVO.getRoomBedTypeId());
                    itripPersonalHotelOrderVO.setIsHavingBreakfast(itripPersonalOrderRoomVO.getIsHavingBreakfast());
                    itripPersonalHotelOrderVO.setCheckOutWeek(3);
                }
                return DtoUtil.returnSuccess("获取个人订单房型信息成功", itripPersonalHotelOrderVO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("获取个人订单房型信息失败", "100401");
        }
        return DtoUtil.returnSuccess();
    }

}
