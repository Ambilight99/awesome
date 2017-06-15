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
    <link rel="stylesheet" type="text/css" href="${root}/static/custome/css/common.css"> <!-- 自定义主体 样式 -->

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
                        <#--<th>-->
                            <#--<input type="checkbox" class="checkall" />-->
                        <#--</th>-->
                        <th>编号</th>
                        <th>角色名称</th>
                        <th>角色描述</th>
                        <#--<th>角色状态</th>-->
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
            url: _root + "/system/model/resource/treeData"
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
            $.ajax({
                url: _root + "/system/role/resource/save",
                data:{
                    roleId : _roleId,
                    resourceIds : resourceIds
                },
                type:"post",
                success:function(result){
                    if(result.status){
                        layer.ok(result.message)
                    }else{
                        layer.fail(result.message)
                    }
                }
            });
            layer.close(index);
        });
    }


    /*---------------------------------------dataTable -----------------------------------*/
    var _table_id = "#role-table";
    var _table_url = _root + "/system/role/loadData";
    var _table = $(_table_id).DataTable( {
        "ajax": {
            "url": _table_url
        },
        columns: [
//            {
//                "sClass": "text-center",
//                "data": "id",
//                "render": function (data, type, full, meta) {
//                    return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
//                },
//                "bSortable": false
//            },
            {
                "data": "id" ,
                "render" : function(data, type, full, meta){
                    var no = meta.row + 1 + meta.settings._iDisplayStart;
                    return '<span>'+no+'</span>' ;
                }
            },
            {
                "data": "name" ,
                "render" : function(data, type, full, meta){
                    return "<span class='name'>" + data + "</span>";
                }
            },
            {"data": "description"},
//            {"data": "status"},
            {
                "sClass": "text-center",
                "data": "id",
                "render": function (data, type, full, meta) {
                    var html = '<a class="a-button datatable-a-edit" onclick="editRole(this)"  data-id="' + data + ' ">【编辑】<a />';
                    if(full.type!="system"){
                        html +=   '&nbsp;&nbsp;&nbsp;'
                                + '<a class="a-button-del" onclick="isDeleteRole(this)"  data-id="' + data + '" data-name="' + full.name + '"  >【删除】<a />';
                    }
                    return html;
                },
                "bSortable": false
            }
        ],
        language:{
            searchPlaceholder : "角色名称、角色描述"
        }
    } );
    //生成添加按钮
    $(".dataTables_wrapper .top").append("<div class='datatable-a-button'><a class='a-button' onclick='addRole()' >【添加】</a></div>")

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
        event.stopPropagation();
    }

    /**
     * 打开表单
     */
    function openForm(url,title){
        //弹出一个页面层
        layer.openIframe({
            title: title,
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
     *
     */
    $(_table_id).on( 'click', 'tr', function () {
        var data = _table.row( this ).data();
        _roleId =  data.id;
        if(data.name){
            $("#roleName").html(data.name);
        }
        var setting = {
            async: {
                otherParam:{
                    roleId: _roleId
                }
            },
            check: {
                enable: true
            }
        };
        setting = $.extend(true,_setting,setting);
        $.fn.zTree.init( _tree , setting);

        $(".tree-save").show();     //显示保存按钮
    } );

    /**
     *  是否删除角色
     * @param e
     */
    function isDeleteRole(e){
        var roleId = $(e).data("id");
        var roleName = $(e).data("name");
        $.ajax({
            url :_root + "/system/role/user/count",
            data:{
                id : roleId
            },
            success : function(result){
                if(result.status){
                    var info = "删除角色【"+roleName+"】，继续？";
                    if(result.data > 0){
                        info = "角色【"+roleName+"】关联【 "+result.data+" 】个用户，是否继续删除？";
                    }
                    layer.confirm(info,function(index){
                        deleteRole(roleId);//删除角色 ，以及角色关联的资源和用户
                        layer.close(index);
                    });
                }else{
                    layer.fail(result.message);
                }
            }
        });
        event.stopPropagation();
    }

    /**
     * 删除角色
     * @param id
     */
    function deleteRole(id){
        $.ajax({
            url : _root + "/system/role/delete",
            data:{
                id : id
            },
            success : function(result){
                if(result.status){
                    layer.ok(result.message);
                    _table.ajax.reload();
                }else{
                    layer.fail(result.message);
                }
            }
        });
    }
</script>
</html>