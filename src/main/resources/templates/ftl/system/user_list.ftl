<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
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
        <div class="col-xs-3 container-tree">
            <div>
                <ul id="tree" class="ztree"></ul>
            </div>
        </div>
        <div class="col-xs-9 container-table">
            <div>
                <table id="user-table" class="display" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>编号</th>
                        <th>姓名</th>
                        <th>用户名</th>
                        <th>手机号</th>
                        <th>所属部门</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div><!-- .container -->
    <#include "../common/footer.ftl" />
</body>
<script>
    var _root = "${root}";
    /*--------------------------------------- ztree  -----------------------------------*/
    var _treeId = "tree";
    var _tree = $( "#" + _treeId);
    var _setting = {
        async: {
            enable: true,
            url: _root + "/system/department/treeData",
            type:"get"
        },
        view: {
            expandSpeed:"",
            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom,
            selectedMulti: false
        },
        edit: {
            enable: true,
            showRenameBtn: true,    //默认编辑按钮
            showRemoveBtn: showRemoveBtn     //默认删除按钮
        },
        callback: {
            onClick : onClick,       //点击按钮
            onRename : onRename ,   //编辑完成后触发
            onDrop : onDrop         // 移动后回调
        }
    };
    $(document).ready(function(){
        $.fn.zTree.init( _tree , _setting);
    });

    function addHoverDom(treeId, treeNode) {
        var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
        var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                + "' title='添加子模块' onfocus='this.blur();' ></span>";

        sObj.after(addStr);

        //添加按钮绑定点击事件
        $("#addBtn_"+treeNode.tId).on("click",function(){
            addModel(treeNode);
        });
    };
    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_"+treeNode.tId).unbind().remove();
    };

    /**
     * 是否显示删除按钮
     */
    function showRemoveBtn(treeId, treeNode){
        if(treeNode.id == 1 ){  //根节点不允许被删除
            return false;
        }else{
            return true;
        }
    }

    /**
     *  弹出模块添加框
     */
    function addModel(treeNode){
        //弹出一个页面层
        layer.open({
            type: 2,
            title: '部门添加',
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area : ['800px' , '320px'],
            content: _root + "/system/department/add?id="+ treeNode.id ,
            btn: ['保存', '取消'],
            yes: function(index, layero){
                var iframeWin = window[layero.find('iframe')[0]['name']];
                var model = iframeWin.save();       //从子iframe页面中获取返回值
                if(model){
                    var zTree = $.fn.zTree.getZTreeObj( _treeId );
                    zTree.addNodes(treeNode, {id:model.id , name: model.name, iconSkin:"department_icon"}); //生成一个节点
                    layer.close(index);         //关闭页面
                }
            }
        });
    }

    /**
     *  编辑后回调
     */
    function onRename(event, treeId, treeNode, isCancel){
        var department ={
            id : treeNode.id,
            name : treeNode.name
        }
        editDepartment(department);
    }

    /**
     *  拖拽移动后回调
     */
    function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy){
        var model = {
            id: treeNodes[0].id
        };
        //moveType "inner"：成为子节点，"prev"：成为同级前一个节点，"next"：成为同级后一个节点
        if(moveType == "inner"){
            model.parent = targetNode.id;
        }else if(moveType == "prev"){
            model.parent = targetNode.parent;
            model.order = targetNode.order - 1;
        }else if(moveType == "next"){
            model.parent = targetNode.parent;
            model.order = targetNode.order + 1;
        }else{
            return;
        }
        editDepartment(department);
    }


    /**
     * 编辑保存
     */
    function editDepartment(department){
        $.ajax({
            url: _root + "/system/department/save",
            type:"post",
            data:department,
            dataType:"json",
            success:function(result){
                layer.msg(result.message);
            }
        });
    }

    /**
     * 点击树节点，加载右侧资源
     */
    function onClick(event, treeId, treeNode, clickFlag){
        dataTableReload(treeNode.id)
    }

    /*---------------------------------------dataTable -----------------------------------*/
    var _table_url = _root + "/system/user/loadData";
    var _table = $('#user-table').DataTable( {
        "ajax": {
            "url": _table_url
        },
        columns: [
            {
                "data": null ,
                "render" : function(data, type, full, meta){
                    return meta.row + 1 + meta.settings._iDisplayStart;
                }
            },
            {"data": "name"},
            {"data": "username"},
            {"data": "phone"},
            {"data": "departmentName"},
            {
                "sClass": "text-center",
                "data": "id",
                "render": function (data, type, full, meta) {
                    var department = full.department;
                    return '<a class="a-button" onclick="editUser(this)"  data-id="' + data + ' ">【编辑】<a />'
                            + '<a class="a-button" onclick="moveUser('+ department +')" >【移动】<a />';
                },
                "bSortable": false
            }

        ],

    } );
    //生成添加按钮
    $(".dataTables_wrapper .top").append("<div class='datatable-a-button'><a class='a-button' onclick='addResource()' >【添加】</a><div>")

    /**
     * 重新加载
     * @param abbr
     */
    function dataTableReload(department){
        var param = {
            department : department
        };
        _table.ajax.url( _table_url + "?"+ $.param(param) ).load();
    }

    /**
     * 添加用户
     */
    function editUser(){
        var url = _root + "/system/user/add?department="+1;
        openForm(url,"用户添加");
    }

    /**
     * 编辑用户
     */
    function editUser(e){
        var url = _root + "/system/user/edit?id=" + $(e).data("id");
        openForm( url ,'用户编辑')
    }

    /**
     * 打开资源表单
     */
    function openForm(url,title){
        //弹出一个页面层
        layer.open({
            type: 2,
            title: title,
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area : ['800px' , '280px'],
            content: url ,
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
     *
     * @param e
     */
    function moveResource(model){
        console.log(model);
    }
</script>
</html>

