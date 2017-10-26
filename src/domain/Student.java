package domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Date;


/**
 * @Author:L1ANN
 * @Description: 新生实体类
 * @Date:Created in 23:53 2017/10/17
 * @Modified By:
 */
public class Student {
    //准考证号
    @NotNull(message="准考证号不能为空!")
    private int stu_num;
    //姓名
    @NotNull(message="姓名不能为空!")
    private String stu_name;
    //年龄
    @NotNull(message="年龄不能为空!")
    private int stu_age;
    //性别
    @NotNull(message="性别不能为空!")
    private String stu_gender;
    //民族
    @NotNull(message="民族不能为空!")
    private String stu_ethnic;
    //籍贯
    @NotNull(message="籍贯不能为空!")
    private String stu_native;
    //入学时间，格式20170904
    @NotNull(message="入学时间不能为空!")
    private int stu_time;
    //系别
    @NotNull(message="系别不能为空!")
    private String stu_department;
    //专业
    @NotNull(message="专业不能为空!")
    private String stu_major;
    //班级
    @NotNull(message="班级不能为空!")
    private String stu_class;
    //生日，格式1995-02-27
    @NotNull(message="生日不能为空!")
    private Date stu_birth;
    //电话
    @Pattern(regexp="^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$",message="手机号码无效!")
    private String stu_phone;
    //地址
    @NotNull(message="地址不能为空!")
    private String stu_address;
    //已缴学费
    private float stu_tuition;
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

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public int getStu_age() {
        return stu_age;
    }

    public void setStu_age(int stu_age) {
        this.stu_age = stu_age;
    }

    public String getStu_gender() {
        return stu_gender;
    }

    public void setStu_gender(String stu_gender) {
        this.stu_gender = stu_gender;
    }

    public String getStu_ethnic() {
        return stu_ethnic;
    }

    public void setStu_ethnic(String stu_ethnic) {
        this.stu_ethnic = stu_ethnic;
    }

    public String getStu_native() {
        return stu_native;
    }

    public void setStu_native(String stu_native) {
        this.stu_native = stu_native;
    }

    public int getStu_time() {
        return stu_time;
    }

    public void setStu_time(int stu_time) {
        this.stu_time = stu_time;
    }

    public String getStu_department() {
        return stu_department;
    }

    public void setStu_department(String stu_department) {
        this.stu_department = stu_department;
    }

    public String getStu_major() {
        return stu_major;
    }

    public void setStu_major(String stu_major) {
        this.stu_major = stu_major;
    }

    public String getStu_class() {
        return stu_class;
    }

    public void setStu_class(String stu_class) {
        this.stu_class = stu_class;
    }

    public Date getStu_birth() {
        return stu_birth;
    }

    public void setStu_birth(Date stu_birth) {
        this.stu_birth = stu_birth;
    }

    public String getStu_phone() {
        return stu_phone;
    }

    public void setStu_phone(String stu_phone) {
        this.stu_phone = stu_phone;
    }

    public String getStu_address() {
        return stu_address;
    }

    public void setStu_address(String stu_address) {
        this.stu_address = stu_address;
    }

    public float getStu_tuition() {
        return stu_tuition;
    }

    public void setStu_tuition(float stu_tuition) {
        this.stu_tuition = stu_tuition;
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
