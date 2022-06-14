package provider.dao;

import org.springframework.stereotype.Repository;
import provider.pojo.Goods;

/**
 * Goods 持久层
 */
@Repository
public class GoodsDao {

    public Goods findOne(int id) {
        return new Goods(id, "华为手机"+id, 3999.9, 999);
    }
}
