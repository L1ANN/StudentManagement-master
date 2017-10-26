package domain;

/**
 * @Author:L1ANN
 * @Description: 班级实体类
 * @Date:Created in 0:45 2017/10/18
 * @Modified By:
 */
public class Class {
    //班级id
    private int cl_id;
    //所属专业
    private int ma_id;
    //班级名称
    private String cl_name;
    //专业名称
    private String ma_name;
    //系别名称
    private String de_name;

    public int getCl_id() {
        return cl_id;
    }

    public void setCl_id(int cl_id) {
        this.cl_id = cl_id;
    }

    public int getMa_id() {
        return ma_id;
    }

    public void setMa_id(int ma_id) {
        this.ma_id = ma_id;
    }

    public String getCl_name() {
        return cl_name;
    }

    public void setCl_name(String cl_name) {
        this.cl_name = cl_name;
    }
    public String getMa_name() {
        return ma_name;
    }

    public void setMa_name(String ma_name) {
        this.ma_name = ma_name;
    }

    public String getDe_name() {
        return de_name;
    }

    public void setDe_name(String de_name) {
        this.de_name = de_name;
    }
}
