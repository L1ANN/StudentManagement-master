package domain;

/**
 * @Author:L1ANN
 * @Description: 入学成绩实体表
 * @Date:Created in 0:52 2017/10/18
 * @Modified By:
 */
public class Record {
    //学生准考证号
    private int stu_num;
    //英语成绩
    private float re_eng;
    //政治成绩
    private float re_pol;
    //高数成绩
    private float re_math;
    //专业课成绩
    private float re_pro;
    //总成绩
    private float re_total;

    public int getStu_num() {
        return stu_num;
    }

    public void setStu_num(int stu_num) {
        this.stu_num = stu_num;
    }

    public float getRe_eng() {
        return re_eng;
    }

    public void setRe_eng(float re_eng) {
        this.re_eng = re_eng;
    }

    public float getRe_pol() {
        return re_pol;
    }

    public void setRe_pol(float re_pol) {
        this.re_pol = re_pol;
    }

    public float getRe_math() {
        return re_math;
    }

    public void setRe_math(float re_math) {
        this.re_math = re_math;
    }

    public float getRe_pro() {
        return re_pro;
    }

    public void setRe_pro(float re_pro) {
        this.re_pro = re_pro;
    }

    public float getRe_total() {
        return re_total;
    }

    public void setRe_total(float re_total) {
        this.re_total = re_total;
    }
}
