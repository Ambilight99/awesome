<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>角色列表</title>
    <link rel="shortcut icon" type="image/ico" href="${root}/static/custome/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${root}/static/zui/dist/css/zui.min.css"> <!-- ZUI 标准版压缩后的 CSS 文件 -->
    <link rel="stylesheet" type="text/css" href="${root}/static/datatable/media/css/jquery.dataTables.css"> <!--datatable 样式 -->
    <link rel="stylesheet" type="text/css" href="${root}/static/custome/css/custome.datatable.css"> <!-- 自定义修改的datatable 样式 -->
    <link rel="stylesheet" type="text/css" href="${root}/static/ztree/css/zTreeStyle/zTreeStyle.css">       <!--ztree 样式 -->
    <link rel="stylesheet" type="text/css" href="${root}/static/custome/css/common.css"> <!-- 自定义样式 -->

    <script type="text/javascript" src="${root}/static/jquery/jquery-1.11.3.js"></script>  <!-- jQuery -->
    <script type="text/javascript" src="${root}/static/zui/dist/js/zui.min.js"></script>   <!-- ZUI 标准版压缩后的 JavaScript 文件 -->
    <script type="text/javascript" src="${root}/static/datatable/media/js/jquery.dataTables.js"></script>   <!--datatable  -->
    <script type="text/javascript" src="${root}/static/custome/js/datatable.custom.init.js"></script>   <!--datatable 自定义通用初始化  -->
    <script type="text/javascript" src="${root}/static/ztree/js/jquery.ztree.core.js"></script>     <!--ztree   -->
    <script type="text/javascript" src="${root}/static/ztree/js/jquery.ztree.excheck.js"></script>  <!--ztree   -->
    <script type="text/javascript" src="${root}/static/ztree/js/jquery.ztree.exedit.js"></script>   <!--ztree   -->
    <script type="text/javascript" src="${root}/static/layer/layer.js"></script>                    <!--layer弹窗   -->
</head>
<body>
    <#include "../common/menu.ftl" />
    <div class="container">
        <div class="col-xs-9 container-table">
            <div>
                <table id="role-table" class="display" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>
                            <input type="checkbox" class="checkall" />
                        </th>
                        <th>编号</th>
                        <th>角色名称</th>
                        <th>角色描述</th>
                        <th>角色状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div><!-- .col-xs-9 -->
        <div class="col-xs-3 container-tree">
            <div>
                <span id="roleName" ></span>
                <div>
                    <a class="a-button" onclick="expandAll()">【全部展开】</a>
                    <a class="a-button tree-save" onclick="saveResource()" style="display: none">【保存】</a>
                </div>
                <ul id="tree" class="ztree"></ul>
            </div>
        </div>
    </div><!-- .container -->
    <#include "../common/footer.ftl" />
</body>
<style type="text/css">

</style>
<script>
    var _root = "${root}";
    var _roleId;
    /*--------------------------------------- ztree  -----------------------------------*/
    var _treeId = "tree";
    var _tree = $( "#" + _treeId);
    var _setting = {
        async: {
            enable: true,
            url: _root + "/system/model/resource/treeData",
            type:"get"
        },
        check: {
            enable: true
        }
    };
    $(document).ready(function(){
        $.fn.zTree.init( _tree , _setting);
    });

    /**
     * 展开树的全部节点
     */
    function expandAll(){
        var treeObj = $.fn.zTree.getZTreeObj(_treeId);
        treeObj.expandAll(true);
    }

    /**
     * 保存角色关联的资源
     */
    function saveResource(){
        var treeObj = $.fn.zTree.getZTreeObj(_treeId);
        var nodes = treeObj.getCheckedNodes(true);
        var resourceIds = [];
        for(var i=0; i<nodes.length; i++ ){
            if(nodes[i].type == "resource"){
                resourceIds.push( nodes[i].id );
            }
        }

        layer.confirm('已选资源【<span style="color:#3280fc" >' + resourceIds.length + '</span>】个,是否保存？', function(index){
            console.log(resourceIds);

            $.ajax({
                url: _root + "/system/role/resource/save",
                data:{
                    roleId : _roleId,
                    resourceIds : resourceIds
                },
                type:"post",
                success:function(result){
                    if(result.status){
                        layer.msg("操作成功！")
                    }else{
                        layer.msg("操作失败！")
                    }
                }
            });
            layer.close(index);
        });
    }


    /*---------------------------------------dataTable -----------------------------------*/
    var _table_url = _root + "/system/role/loadData";
    var _table = $('#role-table').DataTable( {
//        "dom": '<"top"<"#aaaa">f>rt<"bottom"ilp><"clear">',
        "processing": true,
        "serverSide":true,  //后台分页
        "ajax": {
            "url": _table_url
        },
        columns: [
            {
                "sClass": "text-center",
                "data": "id",
                "render": function (data, type, full, meta) {
                    return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
                },
                "bSortable": false
            },
            {
                "data": null ,
                "render" : function(data, type, full, meta){
                    return meta.row + 1 + meta.settings._iDisplayStart;
                }
            },
            {
                "data": "name" ,
                "render" : function(data, type, full, meta){
                    return "<span class='name'>" + data + "</span>";
                }
            },
            {"data": "description"},
            {"data": "status"},
            {
                "sClass": "text-center",
                "data": "id",
                "render": function (data, type, full, meta) {
                    return '<a class="a-button datatable-a-edit" onclick="editRole(this)"  data-id="' + data + ' ">【编辑】<a />';
                },
                "bSortable": false
            }
        ],
        buttons: [
            { extend: "create" },

        ]
    } );

    //生成添加按钮
    $(".dataTables_wrapper .top").append("<a class='a-button' onclick='addRole()' >【添加】</a>")

    /**
     * 重新加载
     * @param abbr
     */
    function dataTableReload(abbr){
        var param = {
            model : abbr
        };
        _table.ajax.url( _table_url + "?"+ $.param(param) ).load();
    }

    /**
     *  datatable 全选
     */
    $(".checkall").on("click",function () {
        var check = $(this).prop("checked");
        $(".checkchild").prop("checked", check);
    });

    /**
     * 角色添加
     */
    function addRole(){
        openForm( _root + "/system/role/add" ,'角色添加')
    }

    /**
     * 角色编辑
     */
    function editRole(e){
        var roleId = $(e).data("id");
        openForm( _root + "/system/role/edit?id="+ roleId ,'角色编辑')
    }

    /**
     * 打开表单
     */
    function openForm(url,title){
        //弹出一个页面层
        layer.open({
            type: 2,
            title: title,
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area : ['800px' , '220px'],
            content: url,
            btn: ['保存', '取消'],
            yes: function(index, layero){
                var iframeWin = window[layero.find('iframe')[0]['name']];
                var retrunStatus = iframeWin.save();       //从子iframe页面中获取返回值
                if(retrunStatus){
                    _table.ajax.reload();
                    layer.close(index);         //关闭页面
                }
            }
        });
    }

    /**
     * dataTable 行点击时执行
     */
    $('#role-table').on("click","tr[role]",function(){
        var name = $(this).find("span.name").html() ; //角色名称
        if(name){
            $("#roleName").html(name);
        }

        _roleId= $(this).find("input.checkchild").val();    //角色id
        var setting = {
            async: {
                otherParam:{
                    roleId: _roleId
                }
            }
        };
        setting = $.extend(true,_setting,setting);
        $.fn.zTree.init( _tree , setting);

        $(".tree-save").show();     //显示保存按钮
    })
</script>
</html>