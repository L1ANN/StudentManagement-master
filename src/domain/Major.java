package domain;

/**
 * @Author:L1ANN
 * @Description: 专业实体类
 * @Date:Created in 0:43 2017/10/18
 * @Modified By:
 */
public class Major {
    //专业id
    private int ma_id;
    //所属系别id
    private int de_id;
    //专业名称
    private String ma_name;

    public int getMa_id() {
        return ma_id;
    }

    public void setMa_id(int ma_id) {
        this.ma_id = ma_id;
    }

    public int getDe_id() {
        return de_id;
    }

    public void setDe_id(int de_id) {
        this.de_id = de_id;
    }

    public String getMa_name() {
        return ma_name;
    }

    public void setMa_name(String ma_name) {
        this.ma_name = ma_name;
    }


}
