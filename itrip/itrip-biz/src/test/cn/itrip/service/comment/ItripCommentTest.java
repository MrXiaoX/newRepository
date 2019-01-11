package cn.itrip.service.comment;

import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.vo.comment.ItripCountVO;
import cn.itrip.beans.vo.comment.ItripHotelDescVO;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ItripCommentTest {


    @Test
    public void ItripTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext-mybatis.xml");
        ItripCommentService itripCommentService = ctx.getBean(ItripCommentService.class);
        try {
            Long id = 26L;
            List<ItripComment> itripComments = itripCommentService.getHotelScore(id);
            for (ItripComment itripComment : itripComments) {
                System.out.println("itripComment" + itripComment.getFacilitiesScore());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ItripHotelDesc() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext-mybatis.xml");
        ItripCommentService itripCommentService = ctx.getBean(ItripCommentService.class);
        try {
            Long id = 44L;
            List<ItripHotelDescVO> itripComments = itripCommentService.getHotelDesc(id);
            for (ItripHotelDescVO itripComment : itripComments) {
                System.out.println("itripComment"+itripComment.getHotelName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void ItripTestCount() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext-mybatis.xml");
        ItripCommentService itripCommentService = ctx.getBean(ItripCommentService.class);
        try {
            Long id = 1L;
            List<ItripCountVO> count = itripCommentService.getCount(id);
            for (ItripCountVO itripCount : count) {
                System.out.println("count>>" + itripCount.getAllcomment());
                System.out.println("count>>" + itripCount.getHavingimg());
                System.out.println("count>>" + itripCount.getIsok());
                System.out.println("count>>" + itripCount.getImprove());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ItripTestImg() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext-mybatis.xml");
        ItripCommentService itripCommentService = ctx.getBean(ItripCommentService.class);
        try {
            Long id = 41L;
            List<ItripImage> count = itripCommentService.getImgList(id);
            for (ItripImage itripImage : count) {
                System.out.println("img"+itripImage.getCreatedBy());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
