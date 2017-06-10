<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>资源列表</title>
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
                <table id="source-table" class="display" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>编号</th>
                        <th>资源名称</th>
                        <th>资源路径</th>
                        <th>资源状态</th>
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
    var setting = {
        async: {
            enable: true,
            url: _root + "/system/model/treeData",
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
        $.fn.zTree.init( _tree , setting);
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
            title: '模块添加',
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area : ['800px' , '320px'],
            content: _root + "/system/model/add?id="+ treeNode.id ,
            btn: ['保存', '取消'],
            yes: function(index, layero){
                var iframeWin = window[layero.find('iframe')[0]['name']];
                var model = iframeWin.save();       //从子iframe页面中获取返回值
                if(model){
                    var zTree = $.fn.zTree.getZTreeObj( _treeId );
                    zTree.addNodes(treeNode, {id:model.id , name: model.name}); //生成一个节点
                    layer.close(index);         //关闭页面
                }
            }
        });
    }

    /**
     *  编辑后回调
     */
    function onRename(event, treeId, treeNode, isCancel){
        var model ={
            id : treeNode.id,
            name : treeNode.name
        }
        editModel(model);
    }

    /**
     *  拖拽移动后回调
     */
    function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy){
        console.log(treeNodes)
        console.log(targetNode)
        console.log(moveType)
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
        editModel(model);
    }



    /**
     * 编辑保存
     */
    function editModel(model){
        $.ajax({
            url: _root + "/system/model/save",
            type:"post",
            data:model,
            dataType:"json",
            success:function(result){

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
    var _table_url = _root + "/system/resource/loadData";
    var _table = $('#source-table').DataTable( {
        "processing": true,
        "serverSide":true,  //后台分页
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
            {"data": "url"},
            {"data": "status"}
        ],

    } );
    //生成添加按钮
    $(".dataTables_wrapper .top").append("<a class='a-button' onclick='addResource()' >【添加】</a>")

    /**
     * 重新加载
     * @param abbr
     */
    function dataTableReload(modelId){
        var param = {
            model : modelId
        };
        _table.ajax.url( _table_url + "?"+ $.param(param) ).load();
    }
</script>
</html>