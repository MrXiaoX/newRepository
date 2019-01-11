package cn.itrip.dao.impl;

import cn.itrip.beans.vo.ItripHotelVO;
import cn.itrip.dao.BaseDao;
import cn.itrip.dao.HotelDao;
import cn.itrip.entity.Hotel;
import org.apache.solr.client.solrj.SolrQuery;

import java.util.List;

public class HotelDaoImpl implements HotelDao {
    public static String url = "http://localhost:8003/solr/hotel/";

    private BaseDao<Hotel> hotelBaseDao = new BaseDao<Hotel>(url);
    private BaseDao<ItripHotelVO> hotelSearch = new BaseDao<ItripHotelVO>(url);

    public List<Hotel> queryHotelList(String keyword) {

        SolrQuery solrQuery = new SolrQuery("keyword:" + keyword);
        List<Hotel> list = null;
        //hotelBaseDao.queryList(solrQuery, Hotel.class);
        return list;
    }

    public List<ItripHotelVO> itripHotelVO(String keyword) {
        Integer rows = 0;
        SolrQuery solrQuery = new SolrQuery("keyword:" + keyword);
        // solrQuery.setRows(rows);
        List<ItripHotelVO> list = hotelSearch.queryList(solrQuery, rows, ItripHotelVO.class);
        return list;
    }
}
