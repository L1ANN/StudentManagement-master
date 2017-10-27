package web.controller.upload;

import domain.Student;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import service.StudentService;
import service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 10:40 2017/10/26
 * @Modified By:
 */
@WebServlet(name = "UploadExcelServlet")
public class UploadExcelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到上传文件的保存目录
        String savepath = getServletContext().getRealPath("/WEB-INF/upload");
        //上传时生成的临时文件保存目录
        String temppath = getServletContext().getRealPath("/WEB-INF/temp");
        File tempPath = new File(temppath);
        if (!tempPath.exists()) {
            //创建临时目录
            tempPath.mkdir();
        }
        //消息提醒
        String message = "";
        boolean result = false;
        String excelfile = "";
        try {
            //使用Apache文件上传组件处理文件上传步骤

            //1、创建DiskFileItemFactory工厂
            // 设置工厂缓冲区大小，当上传文件大小超过缓冲区大小就会生成一个临时文件存放到指定的临时目录中
            //设置上传时生成的临时文件的保存目录
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024 * 100);//设置缓冲区大小为100kb
            factory.setRepository(tempPath);

            //2、创建一个文件上传解析器
            //监听文件上传进度
            //解决上传文件名乱码
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setProgressListener(new ProgressListener() {
                public void update(long pBytesRead, long pContentLength, int arg2) {
                    System.out.println("文件大小为：" + pContentLength + ";当前已处理：" + pBytesRead);
                }
            });
            upload.setHeaderEncoding("UTF-8");

            //3、判断提交上来的数据是否是上传表单的数据
            //设置上传单个文件的大小的最大值，设置为1024*1024字节，也就是1MB
            if (!ServletFileUpload.isMultipartContent(request)) {
                //按照传统方式获取数据
                return;
            }
            upload.setFileSizeMax(1024 * 1024);

            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每个FileItem对应一个form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                //如果fileItem封装的是上传文件
                if (!item.isFormField()) {
                    //得到上传的文件名称
                    String filename = item.getName();
                    if (filename == null || filename.equals("")) {
                        continue;
                    }
                    //注意，不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：c\a\b\1.txt;
                    //而有些只是单纯的文件名，所以还要对获得的文件名称filename进行处理，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("//") + 1);
                    //得到上传文件的扩展名
                    String fileExName = filename.substring(filename.lastIndexOf(".") + 1);
                    //如果扩展名不是.xlxs
                    if (!fileExName.equals("xls")) {
                        message = "请上传xls格式文件！";
                        request.setAttribute("message", message);
                        request.getRequestDispatcher("/stu_upload.jsp").forward(request, response);
                    } else {
                        //获取item中上传文件的输入流
                        InputStream in = item.getInputStream();
                        //得到唯一用户名：调用makeFileName方法，生成UUID_原文件名称
                        String saveFilename = makeFileName(filename);
                        //得到文件的保存目录:使用Hash算法打散存储
                        String realSavePath = makePath(saveFilename, savepath);
                        //创建一个文件的输出流
                        FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);
                        //创建一个缓冲区
                        byte[] buffer = new byte[1024];
                        //判断输入流中的数据是已经读完的标识
                        int len = 0;
                        //循环将输入流读入到缓冲区中，(len = in.read(buffer))>0表示in里面还有数据
                        while ((len = in.read(buffer)) > 0) {
                            //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录当中
                            out.write(buffer, 0, len);
                        }
                        //关闭输入流
                        in.close();
                        //关闭输出流
                        out.close();
                        //删除文件上传时生成的临时文件
                        item.delete();
                        excelfile = realSavePath + "\\" + saveFilename;
                    }
                }
            }
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
            request.setAttribute("message", "单个文件超出最大值!");
            request.getRequestDispatcher("/stu_upload.jsp").forward(request, response);
        } catch (Exception e) {
            message = "文件上传失败";
            e.printStackTrace();
        }

        //创建一个excel文件的输入流
        FileInputStream fis = new FileInputStream(excelfile);
        //接受输入流
        POIFSFileSystem pfs = new POIFSFileSystem(fis);
        //创建一个工作簿
        HSSFWorkbook hwb = new HSSFWorkbook(pfs);
        //获取第一个sheet页
        HSSFSheet sheetAt = hwb.getSheetAt(0);
        List<Student> students = new ArrayList<>();

        StudentService studentService = new StudentServiceImpl();
        if (sheetAt == null) {
            return;
        }
        //遍历行里面的单元格内容
        for (int i = 1; i <= sheetAt.getLastRowNum(); i++) {
            Student stu = new Student();
            //得到每一行
            HSSFRow row = sheetAt.getRow(i);
            if (row == null) {
                continue;
            }
            //遍历列
            for (int j = 0; j < row.getLastCellNum(); j++) {
                //遍历列里面的内容
                HSSFCell cell = row.getCell(j);
                //如果该行为空，直接跳出本次循环
                if (cell == null) {
                    continue;
                    //如果非空，将数据存入Student实体类中
                } else {
                    HSSFRow row0 = sheetAt.getRow(0);
                    if (getCellValue(row0.getCell(j)).equals("准考证号")) {
                        int num = Integer.parseInt(getCellValue(row.getCell(j)));
                        stu.setStu_num(num);

                    }
                    if (getCellValue(row0.getCell(j)).equals("姓名")) {
                        stu.setStu_name(getCellValue(row.getCell(j)));

                    }
                    if (getCellValue(row0.getCell(j)).equals("年龄")) {
                        int age = Integer.parseInt(getCellValue(row.getCell(j)));
                        stu.setStu_age(age);

                    }
                    if (getCellValue(row0.getCell(j)).equals("性别")) {
                        stu.setStu_gender(getCellValue(row.getCell(j)));

                    }
                    if (getCellValue(row0.getCell(j)).equals("民族")) {
                        stu.setStu_ethnic(getCellValue(row.getCell(j)));

                    }
                    if (getCellValue(row0.getCell(j)).equals("籍贯")) {
                        stu.setStu_native(getCellValue(row.getCell(j)));

                    }
                    if (getCellValue(row0.getCell(j)).equals("入学时间")) {
                        int time = Integer.parseInt(getCellValue(row.getCell(j)));
                        stu.setStu_time(time);

                    }
                    if (getCellValue(row0.getCell(j)).equals("出生日期")) {
                        String birth = getCellValue(row.getCell(j));
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date date = null;
                        try {
                            date = format.parse(birth);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        java.sql.Date dataTime = new java.sql.Date(date.getTime());
                        stu.setStu_birth(dataTime);

                    }
                    if (getCellValue(row0.getCell(j)).equals("电话号码")) {
                        stu.setStu_phone(getCellValue(row.getCell(j)));

                    }
                    if (getCellValue(row0.getCell(j)).equals("家庭地址")) {
                        stu.setStu_address(getCellValue(row.getCell(j)));

                    }
                    if (getCellValue(row0.getCell(j)).equals("已缴学费")) {
                        float tuition = Float.parseFloat(getCellValue(row.getCell(j)));
                        stu.setStu_tuition(tuition);

                    }
                    if (getCellValue(row0.getCell(j)).equals("英语成绩")) {
                        float eng_re = Float.parseFloat(getCellValue(row.getCell(j)));
                        stu.setRe_eng(eng_re);

                    }
                    if (getCellValue(row0.getCell(j)).equals("政治成绩")) {
                        float po_re = Float.parseFloat(getCellValue(row.getCell(j)));
                        stu.setRe_pol(po_re);

                    }
                    if (getCellValue(row0.getCell(j)).equals("高数成绩")) {
                        float ma_re = Float.parseFloat(getCellValue(row.getCell(j)));
                        stu.setRe_math(ma_re);

                    }
                    if (getCellValue(row0.getCell(j)).equals("专业课成绩")) {
                        float pro_re = Float.parseFloat(getCellValue(row.getCell(j)));
                        stu.setRe_pro(pro_re);

                    }
                    if (getCellValue(row0.getCell(j)).equals("总分")) {
                        float total = Float.parseFloat(getCellValue(row.getCell(j)));
                        stu.setRe_total(total);

                    }
                }
            }
            students.add(stu);
        }

        result = studentService.addStudent(students);
        if(result) {
            message = "新生基本信息导入成功！";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/stu_upload.jsp").forward(request, response);
        }else{
            message="新生基本信息导入失败！";
            request.setAttribute("message",message);
            request.getRequestDispatcher("/stu_upload.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 生成上传文件的文件名，文件名：uuid_文件的原始名称
     *
     * @param filename 原文件名
     * @return 新文件名
     */
    private String makeFileName(String filename) {
        return UUID.randomUUID() + "_" + filename;
    }

    private String makePath(String filename, String savePath) {
        int hashcode = filename.hashCode();
        int dir1 = hashcode & 0xf;
        int dir2 = (hashcode & 0xf0) >> 4;
        //构造新的保存目录
        String dir = savePath + "\\" + dir1 + "\\" + dir2;
        File file = new File(dir);
        //如果目录不存在
        if (!file.exists()) {
            file.mkdirs();
        }
        return dir;
    }

    //获取单元格的值
    private String getCellValue(Cell cell) {
        String cellValue = "";
        DataFormatter formatter = new DataFormatter();
        if (cell != null) {
            //判断单元格数据的类型，不同类型调用不同的方法
            switch (cell.getCellType()) {
                //数值类型
                case Cell.CELL_TYPE_NUMERIC:
                    //进一步判断 ，单元格格式是日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellValue = formatter.formatCellValue(cell);
                    } else {
                        //数值，因为Cell单元格读取数值时，即使1也会读成1.0，这里就是要验证该数值时int型还是double类型
                        double value = cell.getNumericCellValue();
                        int intValue = (int) value;
                        cellValue = value - intValue == 0 ? String.valueOf(intValue) : String.valueOf(value);
                    }
                    break;
                //字符串类型
                case Cell.CELL_TYPE_STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                //布尔类型
                case Cell.CELL_TYPE_BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                //判断单元格是公式格式，需要做一种特殊处理来得到相应的值
                case Cell.CELL_TYPE_FORMULA: {
                    try {
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    } catch (IllegalStateException e) {
                        cellValue = String.valueOf(cell.getRichStringCellValue());
                    }

                }
                break;
                //空
                case Cell.CELL_TYPE_BLANK:
                    cellValue = "";
                    break;
                //异常
                case Cell.CELL_TYPE_ERROR:
                    cellValue = "";
                    break;
                default:
                    cellValue = cell.toString().trim();
                    break;
            }
        }
        //删除字符串前后的空格
        return cellValue.trim();
    }
}
