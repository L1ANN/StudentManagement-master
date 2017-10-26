package service.impl;

import dao.MajorDAO;
import dao.impl.MajorDAOImpl;
import domain.Major;
import service.MajorService;

import java.util.List;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 16:30 2017/10/19
 * @Modified By:
 */
public class MajorServiceImpl implements MajorService {
    private MajorDAO majorDAO = new MajorDAOImpl();

    @Override
    public List<Major> selectMajorByDepartment(int id) {
        return majorDAO.selectMajorByDepartment(id);

    }
}
