package cn.liuminkai.provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.liuminkai.provider.dao.GoodsDao;
import cn.liuminkai.provider.pojo.Goods;

@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    public Goods findOne(int id) {
        Goods goods = goodsDao.findOne(id);
        // 业务逻辑
        return  goods;
    }
}
