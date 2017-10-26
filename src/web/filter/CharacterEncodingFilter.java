package web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:L1ANN
 * @Description: 此过滤器用来解决全站中文乱码问题
 * @Date:Created in 12:46 2017/10/20
 * @Modified By:
 */
@WebFilter(filterName = "CharacterEncodingFilter")
public class CharacterEncodingFilter implements Filter {
    private FilterConfig filterConfig = null;
    private String defaultCharset = "UTF-8";

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String charset = filterConfig.getInitParameter("charset");
        if (charset == null) {
            charset = defaultCharset;
        }
        request.setCharacterEncoding(charset);
        response.setCharacterEncoding(charset);
        response.setContentType("text/html;charset=" + charset);

        MyCharacterEncodingRequest requestWrapper = new MyCharacterEncodingRequest(request);
        chain.doFilter(requestWrapper, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        //得到过滤器的初始化配置信息
        filterConfig = config;
    }

}

/*
实现一个包装器的具体步骤
1.实现与被增强对象相同的接口
2.定义一个变量记住被增强对象
3.定义一个构造器，接收被增强对象
4.覆盖需要增强的方法
5.对于不想增强的方法，直接调用被增强对象（目标对象）的方法
 */
//包装request,重写getParameter方法，对于get方式接受的数据进行解码
class MyCharacterEncodingRequest extends HttpServletRequestWrapper {
    //定义一个变量记住被增强的对象requet
    private HttpServletRequest request;

    //定一个构造器，接收被增强的对象request
    public MyCharacterEncodingRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    //覆盖要增强的方法getParameter
    @Override
    public String getParameter(String name) {
        try {
            //获取参数的值
            String value = this.request.getParameter(name);
            if (value == null || value == "") return "";
            //如果不是以get方法提交数据的，直接返回获取到的值
            if (!this.request.getMethod().equalsIgnoreCase("get")) {
                return value;
            } else {
                //如果是以get方式提交数据，就对获取到的值进行转码处理
                value = new String(value.getBytes("iso8859-1"), this.request.getCharacterEncoding());
                return value;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}