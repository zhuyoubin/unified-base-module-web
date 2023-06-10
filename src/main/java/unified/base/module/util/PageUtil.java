package unified.base.module.util;
 
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import unified.base.module.domain.PageVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
 
/**
 * @version 1.0.0
 * @Date: 2023/6/6 10:58
 * @Copyright (C) ZhuYouBin
 * @Description 自定义分页工具类
 */
public class PageUtil {
    private static final Logger logger = LoggerFactory.getLogger(PageUtil.class);
    private static final String PAGE_NUM = "pageNum";
    private static final String PAGE_SIZE = "pageSize";

    /**
     * 创建分页对象，从请求中获取分页参数
     * @param request 请求对象
     * @return 返回分页对象
     */
    public static <T> PageVo<T> createPage(HttpServletRequest request) {
        PageVo<T> pageVo = new PageVo<>();
        String num = StrUtil.trim(request.getParameter(PAGE_NUM));
        String size = StrUtil.trim(request.getParameter(PAGE_SIZE));
        pageVo.setPageNum(1);
        pageVo.setPageSize(10);
        try {
            int pageNum = Integer.parseInt(num);
            int pageSize = Integer.parseInt(size);
            pageVo.setPageNum(pageNum);
            pageVo.setPageSize(pageSize);
        } catch (Exception e) {
            logger.error("异常信息:", e);
        }
        return pageVo;
    }
 
    /**
     * 开启分页
     * @param pageVo 分页参数对象
     * @return
     */
    public static <T> PageVo<T> startPage(PageVo<T> pageVo) {
        int pageNum = 0;
        int pageSize = 0;
        if (!Objects.isNull(pageVo)) {
            pageNum = pageVo.getPageNum();
            pageSize = pageVo.getPageSize();
        } else {
            pageVo = new PageVo<>();
        }
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);
        return pageVo;
    }
 
    /**
     * 结束分页
     * @param pageVo 分页参数对象
     * @param list 查询数据结果集
     * @return 返回分页结果对象
     */
    public static <T> PageVo<T> endPage(PageVo<T> pageVo, List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>(list);
        pageVo.setData(list);
        pageVo.setTotal(pageInfo.getTotal());
        pageVo.setTotalPages(pageInfo.getPages());
        // 清除分页参数对象
        PageHelper.clearPage();
        return pageVo;
    }

}