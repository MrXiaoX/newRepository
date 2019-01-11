package cn.itrip.controller;

import cn.itrip.beans.dtos.Dto;
import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.vo.ItripImageVO;
import cn.itrip.beans.vo.ItripLabelDicVO;
import cn.itrip.beans.vo.comment.ItripCountVO;
import cn.itrip.beans.vo.comment.ItripHotelDescVO;
import cn.itrip.beans.vo.comment.ItripListCommentVO;
import cn.itrip.beans.vo.comment.ItripScoreCommentVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.service.comment.ItripCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.management.Agent;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping("/api/comment")
public class HotelCommentController {

    @Resource
    private ItripCommentService itripCommentService;

    /**
     * 查询酒店评分
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询酒店评分", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询酒店评分" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/gethotelscore/{cityId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripScoreCommentVO> getHotelScore(@PathVariable Long cityId) {
        System.out.println("查询酒店评分》》。" + cityId);
        List<ItripComment> itripCommentList = null;
        List<ItripScoreCommentVO> itripScoreCommentVOList = null;
        ItripScoreCommentVO itripScoreCommentVOS = null;
        try {
            itripCommentList = itripCommentService.getHotelScore(cityId);
            if (EmptyUtils.isNotEmpty(itripCommentList)) {
                for (ItripComment comment : itripCommentList) {
                    System.out.println("评分" + itripCommentList + comment.getFacilitiesScore() + ":" + comment.getPositionScore());
                    itripScoreCommentVOList = new ArrayList();
                    itripScoreCommentVOS = new ItripScoreCommentVO();
                    itripScoreCommentVOS.setAvgFacilitiesScore(comment.getFacilitiesScore());
                    itripScoreCommentVOS.setAvgHygieneScore(comment.getHygieneScore());
                    itripScoreCommentVOS.setAvgPositionScore(comment.getPositionScore());
                    itripScoreCommentVOS.setAvgServiceScore(comment.getServiceScore());
                    itripScoreCommentVOS.setAvgScore(comment.getScore());
                    itripScoreCommentVOList.add(itripScoreCommentVOS);
                }

                return DtoUtil.returnSuccess("查询酒店评分", itripScoreCommentVOS);
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("");
    }

    /**
     * 查询评论数量
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询评论数量", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询评论数量" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/getcount/{cityId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripCountVO> getCount(@PathVariable Long cityId) {
        System.out.println("查询评论数量》》。" + cityId);
        List<ItripCountVO> itripCountList = null;
        ItripCountVO itripCount = null;
        try {
            itripCountList = itripCommentService.getCount(cityId);
            if (EmptyUtils.isNotEmpty(itripCountList)) {
                for (ItripCountVO count : itripCountList) {
                    System.out.println("评分" + itripCountList + count.getHavingimg() + ":" + count.getAllcomment());
                    itripCountList = new ArrayList();
                    itripCount = new ItripCountVO();
                    itripCount.setAllcomment(count.getAllcomment());
                    itripCount.setIsok(count.getIsok());
                    itripCount.setHavingimg(count.getHavingimg());
                    itripCount.setImprove(count.getImprove());
                    itripCountList.add(itripCount);
                }
                return DtoUtil.returnSuccess("查询酒店评分成功", itripCount);
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("查询酒店评分失败");
    }

    /*  *//**
     * 查询评论内容列表
     *
     * @return
     * @throws Exception
     *//*
    @ApiOperation(value = "查询评论内容列表", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询评论内容列表" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/getcommentlist", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Dto<ItripListCommentVO> getCommentList(@RequestBody ItripListCommentVO itripListCommentVo) {
        System.out.println("查询评论内容列表》》。");
        List<ItripListCommentVO> itripCountList = null;
        ItripCountVO itripCount = null;
        try {
            itripCountList = itripCommentService.getCount(cityId);
            if (EmptyUtils.isNotEmpty(itripCountList)) {
                for (ItripCountVO count : itripCountList) {
                    System.out.println("评分" + itripCountList + count.getHavingimg() + ":" + count.getAllcomment());
                    itripCountList = new ArrayList();
                    itripCount = new ItripCountVO();
                    itripCount.setAllcomment(count.getAllcomment());
                    itripCount.setIsok(count.getIsok());
                    itripCount.setHavingimg(count.getHavingimg());
                    itripCount.setImprove(count.getAllcomment() - count.getIsok());
                    itripCountList.add(itripCount);
                }
                return DtoUtil.returnSuccess("查询酒店评分成功", itripCount);
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("查询酒店评分失败");
    }*/

    /**
     * 查询评论数量
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询评论数量", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询评论数量" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/getimg/{cityId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripImageVO> getImgList(@PathVariable Long cityId) {
        System.out.println("查询评论数量》》。" + cityId);
        List<ItripImageVO> itripImageVOS = null;
        List<ItripImage> itripImage = null;
        try {
            itripImage = itripCommentService.getImgList(cityId);
            if (EmptyUtils.isNotEmpty(itripImage)) {
                itripImageVOS = new ArrayList();
                for (ItripImage image : itripImage) {
                    ItripImageVO vo = new ItripImageVO();
                    System.out.println("img" + image.getImgUrl());
                    BeanUtils.copyProperties(image, vo);
                    itripImageVOS.add(vo);
                }
                return DtoUtil.returnSuccess("查询酒店评分成功", itripImageVOS);
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("查询酒店评分失败");
    }

    /**
     * 查询旅游类型列表
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询旅游类型列表", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询旅游类型列表" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/gettraveltype", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripLabelDicVO> getTravelType() {
        System.out.println("查询旅游类型列表》》。");
        List<ItripLabelDicVO> itripLabelDicVOS = null;
        List<ItripLabelDicVO> itripLabelDicVOList = null;
        try {
            itripLabelDicVOS = itripCommentService.getTravelType();
            if (EmptyUtils.isNotEmpty(itripLabelDicVOS)) {
                itripLabelDicVOList = new ArrayList();
                for (ItripLabelDicVO itripLabelDicVO : itripLabelDicVOS) {
                    ItripLabelDicVO vo = new ItripLabelDicVO();
                    System.out.println("name" + itripLabelDicVO.getName());
                    BeanUtils.copyProperties(itripLabelDicVO, vo);
                    itripLabelDicVOList.add(vo);
                }
                return DtoUtil.returnSuccess("查询旅游类型列表成功", itripLabelDicVOList);
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("");
    }

    /**
     * 查询酒店详情（评论）
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询酒店详情", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询酒店详情" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/gethoteldesc/{hotelId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripHotelDescVO> getHotelDesc(@PathVariable Long hotelId) {
        System.out.println("查询酒店详情》》。" + hotelId);
        List<ItripHotelDescVO> itripHotelDescVOS = null;
        ItripHotelDescVO itripLabelDicVOList = null;
        try {
            itripHotelDescVOS = itripCommentService.getHotelDesc(hotelId);
            if (EmptyUtils.isNotEmpty(itripHotelDescVOS)) {
                itripLabelDicVOList = new ItripHotelDescVO();
                for (ItripHotelDescVO itripHotelDescVO : itripHotelDescVOS) {
                    System.out.println("name" + itripHotelDescVO.getHotelName());
                    itripLabelDicVOList.setHotelId(itripHotelDescVO.getHotelId());
                    itripLabelDicVOList.setHotelName(itripHotelDescVO.getHotelName());
                    itripLabelDicVOList.setHotelLevel(itripHotelDescVO.getHotelLevel());
                }
                return DtoUtil.returnSuccess("查询酒店详情成功", itripLabelDicVOList);
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("");
    }
}
