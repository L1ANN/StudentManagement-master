package domain;

/**
 * @Author:L1ANN
 * @Description: 管理员实体表
 * @Date:Created in 13:13 2017/10/17
 * @Modified By:
 */
public class Admin {
    //管理员id
    private int ad_id;
    //管理员用户名
    private String ad_name;
    //管理员密码
    private int ad_pass;

    public int getAd_id() {
        return ad_id;
    }

    public void setAd_id(int ad_id) {
        this.ad_id = ad_id;
    }

    public String getAd_name() {
        return ad_name;
    }

    public void setAd_name(String ad_name) {
        this.ad_name = ad_name;
    }

    public int getAd_pass() {
        return ad_pass;
    }

    public void setAd_pass(int ad_pass) {
        this.ad_pass = ad_pass;
    }
}
