package com.awesome.web.domain.common.datatable;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @author adam
 * @ClassName DataTablePage
 * @Description DateTable 分页表格数据
 * @create 2017/6/8 17:12
 */
public class DataTablePage<T> implements Serializable{
    private int draw;
    private long recordsTotal;      //总记录数
    private long recordsFiltered;   //过滤后的总记录数
    private List<T> data;              //结果集

    /**
     * 包装Page对象，因为直接返回Page对象，在JSON处理以及其他情况下会被当成List来处理，
     * 而出现一些问题。
     * @param list          page结果
     */
    public DataTablePage(List<T> list) {
        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            this.recordsTotal = page.getTotal();
            this.recordsFiltered = page.getTotal();;
            this.data = page;
        }
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
