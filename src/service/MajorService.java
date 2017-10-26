package service;

import domain.Major;

import java.util.List;

/**
 * @Author:L1ANN
 * @Description: 对专业表(major)操作的服务层
 * @Date:Created in 16:27 2017/10/19
 * @Modified By:
 */
public interface MajorService {

    /**
     * 根据系别id查找专业的服务
     * @param id 系别id
     * @return 专业集合
     */
    public List<Major> selectMajorByDepartment(int id);
}
