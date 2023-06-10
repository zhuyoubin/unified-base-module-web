package unified.base.module.domain;
 
import java.io.Serializable;
import java.util.List;

/**
 * @version 1.0.0
 * @Date: 2023/6/6 10:58
 * @Copyright (C) ZhuYouBin
 * @Description 分页实体类对象
 */
public class PageVo<T> implements Serializable {
    /**
     * 分页页码, 查询哪一页的数据
     */
    private int pageNum;
    /**
     * 每一页显示记录数量
     */
    private int pageSize;
    /**
     * 查询总记录数量
     */
    private long total;
    /**
     * 总页数
     */
    private int totalPages;
    /**
     * 查询数据结果集
     */
    private List<T> data;
 
    public PageVo() {}
 
    public PageVo(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
 
    public int getPageNum() {
        return pageNum;
    }
 
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
 
    public int getPageSize() {
        return pageSize;
    }
 
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
 
    public long getTotal() {
        return total;
    }
 
    public void setTotal(long total) {
        this.total = total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageVo{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", totalPages=" + totalPages +
                ", data=" + data +
                '}';
    }
}