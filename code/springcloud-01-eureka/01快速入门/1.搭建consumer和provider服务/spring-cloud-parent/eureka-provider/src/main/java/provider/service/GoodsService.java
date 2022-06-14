package provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import provider.dao.GoodsDao;
import provider.pojo.Goods;

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
