package cn.liuminkai.provider.pojo;

/**
 * Goods 实体类
 */
public class Goods {

    private int id; // id
    private String title; // 标题
    private double prive; // 价格
    private int count; // 库存

    public Goods() {
    }

    public Goods(int id, String title, double prive, int count) {
        this.id = id;
        this.title = title;
        this.prive = prive;
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

    public double getPrive() {
        return prive;
    }

    public void setPrive(double prive) {
        this.prive = prive;
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
                ", prive=" + prive +
                ", count=" + count +
                '}';
    }
}
