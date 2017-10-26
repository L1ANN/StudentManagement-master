package dao;

import domain.Major;

import java.util.List;

/**
 * @Author:L1ANN
 * @Description: 对专业表（major）操作的DAO层
 * @Date:Created in 16:20 2017/10/19
 * @Modified By:
 */
public interface MajorDAO {

    /**
     * 根据系别的id查找专业
     * @param id 系别id
     * @return 查找的专业集合
     */
    public List<Major> selectMajorByDepartment(int id);

}
