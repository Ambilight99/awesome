/**
 * datatable 自定义初始化通用配置
 */
$.extend( $.fn.dataTable.defaults, {
    "processing": true,
    "serverSide":true,  //后台分页
    "dom": '<"top"f>rt<"bottom"ilp><"clear">',
    // "paging":   false,
    "ordering": false,
    "ajax": {           //ajax请求前数据处理,方便后台用DataTableSearch类接收
        data: function (param) {
            if(param && param.columns){
                for (var i = 0; i < param.columns.length; i++) {
                    column = param.columns[i];
                    column.searchRegex = column.search.regex;
                    column.searchValue = column.search.value;
                    delete(column.search);
                }
            }
        }
    },
    language:{
        "sProcessing":   "处理中...",
        "sLengthMenu":   "显示 _MENU_ 项结果",
        "sZeroRecords":  "没有匹配结果",
        "sInfo":         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
        "sInfoEmpty":    "显示第 0 至 0 项结果，共 0 项",
        "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
        "sInfoPostFix":  "",
        "sSearch":       "搜索:",
        "sUrl":          "",
        "sEmptyTable":     "表中数据为空",
        "sLoadingRecords": "载入中...",
        "sInfoThousands":  ",",
        "oPaginate": {
            "sFirst":    "首页",
            "sPrevious": "上页",
            "sNext":     "下页",
            "sLast":     "末页"
        },
        "oAria": {
            "sSortAscending":  ": 以升序排列此列",
            "sSortDescending": ": 以降序排列此列"
        }
    }
} );
