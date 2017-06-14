package com.awesome.web.domain.common.datatable;


import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * @author adam
 * @ClassName DataTableSearch
 * @Description DataTable通用搜索条件
 * @create 2017/6/10 8:45
 */
public class DataTableSearch implements Serializable {

    private Integer draw = 1;      //重绘次数
    private Integer start = 1;     //开始位置
    private Integer length = 10;    //长度
    private List<Map<Column, String>> columns = new ArrayList<>();
    private Map<Search, String> search = new HashMap<>();

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public List<Map<Column, String>> getColumns() {
        return columns;
    }

    public void setColumns(List<Map<Column, String>> columns) {
        this.columns = columns;
    }

    public Map<Search, String> getSearch() {
        return search;
    }

    public void setSearch(Map<Search, String> search) {
        this.search = search;
    }


    /**
     * Search 枚举
     */
    public enum Search {
        value, regex
    }

    /**
     * Cloumn 枚举
     */
    public enum Column {
        data, name, searchable, orderable, searchValue, searchRegex
    }
}
