package domain;

/**
 * @Author:L1ANN
 * @Description: 宿舍楼实体表
 * @Date:Created in 0:49 2017/10/18
 * @Modified By:
 */
public class Building {
    //宿舍楼id
    private int bu_id;
    //宿舍楼名称
    private String bu_name;

    public int getBu_id() {
        return bu_id;
    }

    public void setBu_id(int bu_id) {
        this.bu_id = bu_id;
    }

    public String getBu_name() {
        return bu_name;
    }

    public void setBu_name(String bu_name) {
        this.bu_name = bu_name;
    }
}
