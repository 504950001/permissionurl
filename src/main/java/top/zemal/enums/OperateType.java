package top.zemal.enums;

/**
 * @author zemal-tan
 * @description
 * @create 2017-07-24 16:43
 */
public enum OperateType {
    add("添加",1),modify("修改",2),delete("删除",3),search("查询",4)
    ,searchall("查询所有",5), searchsome("查询pk所在分组",6);

    private String name;
    private int index;
    // 构造方法
    private OperateType(String name, int index) {
        this.name = name;
        this.index = index;
    }
    //覆盖方法
    @Override
    public String toString() {
        return this.index+"_"+this.name;
    }

    // 普通方法
    public static String getName(int index) {
        for (OperateType c : OperateType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }
    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
