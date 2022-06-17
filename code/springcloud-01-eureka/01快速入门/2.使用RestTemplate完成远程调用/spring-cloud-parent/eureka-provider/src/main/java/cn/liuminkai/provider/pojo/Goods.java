package cn.liuminkai.provider.pojo;

/**
 * Goods 实体类
 */
public class Goods {

    private int id; // id
    private String title; // 标题
    private double price; // 价格
    private int count; // 库存

    public Goods() {
    }

    public Goods(int id, String title, double price, int count) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getprice() {
        return price;
    }

    public void setprice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
