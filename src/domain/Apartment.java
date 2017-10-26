package domain;

/**
 * @Author:L1ANN
 * @Description: 寝室实体表
 * @Date:Created in 0:50 2017/10/18
 * @Modified By:
 */
public class Apartment {
    //宿舍id
    private int ap_id;
    //宿舍楼id
    private int bu_id;
    //宿舍楼名称
    private String bu_name;
    //宿舍号
    private int ap_num;
    //宿舍人数
    private int ap_total;

    public int getAp_id() {
        return ap_id;
    }

    public void setAp_id(int ap_id) {
        this.ap_id = ap_id;
    }

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

    public int getAp_num() {
        return ap_num;
    }

    public void setAp_num(int ap_num) {
        this.ap_num = ap_num;
    }

    public int getAp_total() {
        return ap_total;
    }

    public void setAp_total(int ap_total) {
        this.ap_total = ap_total;
    }
}
