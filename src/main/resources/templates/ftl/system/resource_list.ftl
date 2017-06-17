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
                        <th>资源类型</th>
                        <th>所属模块</th>
                        <#--<th>资源状态</th>-->
                        <th style="width:165px">操作</th>
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
    var _defaultSelectTreeNodeId;  //选中节点的id
    var _setting = {
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
            removeTitle : "删除",
            renameTitle : "编辑",
            enable: true,
            showRenameBtn: false,    //默认编辑按钮
            showRemoveBtn: showRemoveBtn     //默认删除按钮
        },
        callback: {
            onAsyncSuccess :onAsyncSuccess,  //异步加载成功后触发
            onClick : onClick,               //点击按钮
        //    beforeRename : beforeRename ,    //编辑触发
            beforeRemove : beforeRemove ,    //删除触发
            onDrop : onDrop                  // 移动后回调
        }
    };
    $(document).ready(function(){
        $.fn.zTree.init( _tree , _setting);
    });

    function addHoverDom(treeId, treeNode) {
        var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
        var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='添加子模块' onfocus='this.blur();' ></span>";
        var editStr = "<span class='button edit' id='editBtn_" + treeNode.tId + "' title='编辑' onfocus='this.blur();' ></span>";
        sObj.after(editStr).after(addStr);

        //添加按钮绑定点击事件
        $("#addBtn_"+treeNode.tId).on("click",function(){
            addModel(treeNode);
        });
        //编辑按钮绑定点击事件
        $("#editBtn_"+treeNode.tId).on("click",function(){
            editOpenModel(treeNode);
        });
    };
    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_"+treeNode.tId).unbind().remove();
        $("#editBtn_"+treeNode.tId).unbind().remove();
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
     *  添加模块
     */
    function addModel(treeNode){
        var url = _root + "/system/model/add?id=" + treeNode.id ;
        openModel(url, "模块添加",treeNode);
    }

    /**
     * 编辑模块
     */
    function editOpenModel(treeNode){
        var url = _root + "/system/model/edit?id=" + treeNode.id ;
        openModel(url, "模块编辑",treeNode);
    }

    /**
     *  弹出模块添加框
     */
    function openModel(url,title,treeNode){
        //弹出一个页面层
        layer.openIframe({
            title: '模块添加',
            area : ['800px' , '320px'],
            content: url,
            btn: ['保存', '取消'],
            yes: function(index, layero){
                var iframeWin = window[layero.find('iframe')[0]['name']];
                var retrunResult = iframeWin.save();       //从子iframe页面中获取返回值
                if(retrunResult){
                    layer.ok(retrunResult.message);
                    var treeObj = $.fn.zTree.getZTreeObj( _treeId );
                    // treeObj.addNodes(treeNode, {id:model.id , name: model.name, iconSkin:"department_icon"}); //生成一个节点
                    treeObj.reAsyncChildNodes(null, "refresh");
                    _defaultSelectTreeNodeId =  treeNode.id;
                    layer.close(index);         //关闭页面
                }
            }
        });
    }

    /**
     *  异步加载成功后触发
     */
    function onAsyncSuccess(event, treeId, treeNode, msg){
        //选中根节点
        var treeObj = $.fn.zTree.getZTreeObj(_treeId);
        if(_defaultSelectTreeNodeId){
            var node = treeObj.getNodeByParam("id", _defaultSelectTreeNodeId, null);
            treeObj.selectNode(node);
            treeObj.expandNode(node,true,false);
        }else{
            var nodes = treeObj.getNodes();
            if (nodes.length>0) {
                treeObj.selectNode(nodes[0]);
                _defaultSelectTreeNodeId = nodes[0]["id"];
            }
        }
    }

    /**
     *  编辑后回调
     */
    function beforeRename(treeId, treeNode, newName, isCancel){
        var oldName = treeNode.name;
        if(isCancel || newName == oldName ){
            return true;
        }
        var info = "是否将模块【"+oldName+"】修改为【"+newName+"】？";
        layer.confirm(info,
                {
                    cancel: function(){
                        treeNode.name = oldName;
                        $("#"+treeNode.tId+"_span").html(oldName);
                    }
                },
                function(index){
                    var model ={
                        id : treeNode.id,
                        name : newName
                    }
                    editModel(model);

                    layer.close(index);
                },function () {
                    treeNode.name = oldName;
                    $("#"+treeNode.tId+"_span").html(oldName);
                }
        );
        return true;
    }

    /**
     * 删除回调
     */
    function beforeRemove(treeId, treeNode){
        if(treeNode.children && treeNode.children.length>0){
            layer.warn("存在子模块，请先删除子模块！");
            return false;
        }
        //判断模块是否存在资源。
        $.ajax({
            url : _tableUrl ,
            data : {
                model:treeNode.id
            },
            success : function(result){
                if(result && result.recordsFiltered > 0){
                    var info = "模块【"+treeNode.name+"】存在 "+result.recordsFiltered+" 个资源，不能被删除！"
                    layer.warn(info);
                    return false;
                }else{
                    layer.confirm("是否删除模块【"+treeNode.name+"】",function (index) {
                        deleteModel(treeNode.id);
                        var treeObj = $.fn.zTree.getZTreeObj(treeId);
                        treeObj.removeNode(treeNode);
                        layer.close(index);
                    });
                }
            },
            error : function(result){
                layer.warn("操作失败！");
            }
        });
        return false;
    }

    /**
     * 删除模块
     */
    function deleteModel(id){
        $.ajax({
            url : _root + "/system/model/delete",
            data : {
                id : id
            },
            success : function(result){
                if(result.status){
                    layer.ok(result.message);
                }else{
                    layer.fail(result.message);
                }
            }
        });
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
                if(result.status){
                    layer.ok(result.message);
                }else{
                    layer.fail(result.message);
                }
            }
        });
    }

    /**
     * 点击树节点，加载右侧资源
     */
    function onClick(event, treeId, treeNode, clickFlag){
        _defaultSelectTreeNodeId = treeNode.id;
        clearDataTableSearch();
        dataTableReload(_defaultSelectTreeNodeId)
    }

    /*---------------------------------------dataTable -----------------------------------*/
    var _tableUrl = _root + "/system/resource/loadData";
    var _tableId = "#source-table";
    var _table = $(_tableId).DataTable( {
        "ajax": {
            "url": _tableUrl
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
            {
                "data": "type" ,
                "render" : function(data, type, full, meta){
                    var html = "-";
                    if(data=="menu"){
                        html = "菜单资源";
                    }else if(data=="url"){
                        html = "普通资源";
                    }
                    return html;
                }
            },
            {"data": "modelName"},
//            {"data": "status"},
            {
                "sClass": "text-center",
                "data": "id",
                "render": function (data, type, full, meta) {
                    var model = full.model;
                    return '<a class="a-button" onclick="editResource(this)" data-id="' + data + '">【编辑】<a />'
                         + '<a class="a-button" onclick="moveResource(this)" data-id="' + data + '" data-model="'+full.model+'"  >【移动】<a />'
                         + '&nbsp;&nbsp;&nbsp;'
                         + '<a class="a-button-del" onclick="deleteResource(this)"  data-id="' + data + '" data-name="'+full.name+'"  >【删除】<a />';
                },
                "bSortable": false
            }
        ],
        language:{
            searchPlaceholder : "资源名称、资源路径"
        }

    } );
    //生成添加按钮
    $(".dataTables_wrapper .top").append("<div class='datatable-a-button'><a class='a-button' onclick='addResource()' >【添加】</a><div>")

    /**
     * 重新加载
     * @param abbr
     */
    function dataTableReload(modelId){
        var param = {
            model : modelId
        };
        _table.ajax.url( _tableUrl + "?"+ $.param(param) ).load();
    }

    /**
     * 添加资源
     */
    function addResource(){
        var url = _root + "/system/resource/add?model=" + _defaultSelectTreeNodeId;
        openForm(url,"资源添加");
    }

    /**
     * 编辑资源
     */
    function editResource(e){
        var url = _root + "/system/resource/edit?id=" + $(e).data("id");
        openForm( url ,'资源编辑')
    }

    /**
     * 打开资源表单
     */
    function openForm(url,title){
        //弹出一个页面层
        layer.openIframe({
            title: title,
            area : ['800px' , '280px'],
            content: url ,
            btn: ['保存', '取消'],
            yes: function(index, layero){
                var iframeWin = window[layero.find('iframe')[0]['name']];
                var retrunResult = iframeWin.save();       //从子iframe页面中获取返回值
                if(retrunResult){
                    layer.ok(retrunResult.message);
                    _table.ajax.reload();
                    layer.close(index);         //关闭页面
                }
            }
        });
    }

    /**
     * 移动单个资源
     * @param e
     */
    function moveResource(e){
        var data = {
            id :  $(e).data("model")
        };
        var url = _root + "/system/model/tree?" + $.param(data);
        var ids = [];
        ids.push( $(e).data("id"));
        openModelTree(url,ids);
    }
    /**
     * 移动多个个资源
     * @param e
     */
    function moveResources(){
//        var data = {
//            id :  $(e).data("model")
//        };
//        var ids = [];
        openModelTree(url,ids);
    }
    /**
     * 打开模块树
     */
    function openModelTree(url ,ids){
        //弹出一个页面层
        layer.openIframe({
            title: "模块树",
            area : ['300px' , '400px'],
            content: url ,
            btn: ['保存', '取消'],
            yes: function(index, layero){
                var iframeWin = window[layero.find('iframe')[0]['name']];
                var modelId = iframeWin.save();       //从子iframe页面中获取返回值
                if(modelId){
                    $.ajax({
                        url : _root + "/system/resource/move",
                        data : {
                            model : modelId,
                            ids : ids
                        },
                        success : function(result){
                            if(result.status){
                                layer.ok(result.message);
                                _table.ajax.reload();
                                layer.close(index);         //关闭页面
                            }else{
                                layer.fail(result.message);
                            }
                        }
                    });
                }else{
                    layer.fail("操作失败！");
                }
            }
        });
    }



    /**
     * 删除资源
     * @param e
     */
    function deleteResource(e){
        var info = "是否删除资源【"+$(e).data('name')+"】？";
        var id = $(e).data("id");
        layer.confirm(info,function (index) {
            $.ajax({
                url : _root + "	/system/resource/delete",
                data : {
                    id : id
                },
                success : function(result){
                    if(result.status){
                        dataTableReload(_defaultSelectTreeNodeId); //根据模块id ，刷新表格，
                        layer.ok(result.message)
                    }else{
                        layer.fail(result.message);
                    }
                }
            });
            layer.close(index);
        });
    }

    /**
     * 清空dataTable的搜索框
     */
    function clearDataTableSearch(){
//        var inputs =$(_tableId+"_filter").find("input[type='search']");
//        if(inputs.length > 0){
//            $(inputs[0]).val('');
//        }
        //待处理
    }
</script>
</html>