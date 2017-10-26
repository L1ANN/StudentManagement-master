package domain;

/**
 * @Author:L1ANN
 * @Description: 系别表实体类
 * @Date:Created in 0:42 2017/10/18
 * @Modified By:
 */
public class Department {
    //系别id
    private int de_id;
    //系别名称
    private String de_name;

    public int getDe_id() {
        return de_id;
    }

    public void setDe_id(int de_id) {
        this.de_id = de_id;
    }

    public String getDe_name() {
        return de_name;
    }

    public void setDe_name(String de_name) {
        this.de_name = de_name;
    }
}
