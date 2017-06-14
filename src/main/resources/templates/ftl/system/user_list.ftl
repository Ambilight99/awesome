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
    var _defaultSelectTreeNodeId;
    var _setting = {
        async: {
            enable: true,
            url: _root + "/system/department/treeData"
        },
        data:{
            simpleData:{
                enable:true
            }
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
            showRenameBtn: true,    //默认编辑按钮
            showRemoveBtn: showRemoveBtn     //默认删除按钮
        },
        callback: {
            onAsyncSuccess :onAsyncSuccess,  //异步加载成功后触发
            onClick : onClick,              //点击按钮
            beforeRename : beforeRename ,   //编辑触发
            beforeRemove : beforeRemove ,   //删除触发
            onDrop : onDrop                 // 移动后回调
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
        layer.openIframe({
            title: '部门添加',
            area : ['800px' , '320px'],
            content: _root + "/system/department/add?id="+ treeNode.id ,
            btn: ['保存', '取消'],
            yes: function(index, layero){
                var iframeWin = window[layero.find('iframe')[0]['name']];
                var model = iframeWin.save();       //从子iframe页面中获取返回值
                if(model){
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
            }
        }
    }

    /**
     *  编辑回调
     */
    function beforeRename(treeId, treeNode, newName, isCancel){
        var oldName = treeNode.name;
        if(isCancel || newName == oldName ){
            return true;
        }
        var info = "是否将部门【"+oldName+"】修改为【"+newName+"】？";
        layer.confirm(info,
            {
                cancel: function(){
                    treeNode.name = oldName;
                    $("#"+treeNode.tId+"_span").html(oldName);
                }
            },
            function(index){
                var department ={
                    id : treeNode.id,
                    name : newName
                }
                editDepartment(department);
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
        if(treeNode.children){
            layer.warn("存在子部门，请先删除子部门！");
        }
        //判断部门是否存在用户。
        $.ajax({
            url : _table_url ,
            data : {
                department:treeNode.id
            },
            success : function(result){
                if(result && result.recordsFiltered > 0){
                    var info = "部门【"+treeNode.name+"】存在 "+result.recordsFiltered+" 名用户，不能被删除！"
                    layer.warn(info);
                }else{
                    layer.confirm("是否删除部门【"+treeNode.name+"】",function (index) {
                        deleteDepartment(treeNode.id);
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
     * 删除部门
     */
    function deleteDepartment(id){
        $.ajax({
           url : _root + "/system/department/delete",
           data : {
               id : id
           },
           success : function(result){
               if(result.status){
                   layer.success(result.message);
               }else{
                   layer.success(result.message);
               }

           }
        });
    }


    /**
     *  拖拽移动后回调
     */
    function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy){
        var department = {
            id: treeNodes[0].id
        };
        //moveType "inner"：成为子节点，"prev"：成为同级前一个节点，"next"：成为同级后一个节点
        if(moveType == "inner"){
            department.parent = targetNode.id;
        }else if(moveType == "prev"){
            department.parent = targetNode.parent;
            department.order = targetNode.order - 1;
        }else if(moveType == "next"){
            department.parent = targetNode.parent;
            department.order = targetNode.order + 1;
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
                if(result.status){
                    layer.success(result.message);
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
                            + '<a class="a-button" onclick="authorize('+ data +')" >【授权】<a />';
                },
                "bSortable": false
            }

        ],

    } );
    //生成添加按钮
    $(".dataTables_wrapper .top").append("<div class='datatable-a-button'><a class='a-button' onclick='addUser()' >【添加】</a><div>")

    /**
     * 重新加载
     * @param abbr
     */
    function dataTableReload(department){
        var param = {
            department : department,
            subdivision : true
        };
        _table.ajax.url( _table_url + "?"+ $.param(param) ).load();
    }

    /**
     * 添加用户
     */
    function addUser(){
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
     * 打开用户表单
     */
    function openForm(url,title){
        //弹出一个页面层
        layer.openIframe({
            title: title,
            area : ['800px' , '500px'],
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
     * 打开授权页面
     * @param userId
     */
    function authorize(userId){
        var url = _root + "/system/user/auth/list";
        $.get(url,{id:userId},function(result){
            // 遍历所有角色
            var content= '<div class="role-container">';
            for(var i= 0; i<result.length; i++){
                var role = result[i];
                var checked = role.users.length > 0 ? "checked" :"" ;
                content += ' <label class="checkbox-inline"><input id="roles" type="checkbox" value="'+role.id+'" '+ checked +'>'+role.name+'</label>';
            }
            content +='</div>';
            openAuthorize(content,"授权",userId);
        });
    }

    /**
     * 打开用户表单
     */
    function openAuthorize(content,title,userId){
        //弹出一个页面层
        layer.openContent({
            title: title,
            area : ['400px' , '200px'],
            content: content,
            btn: ['保存', '取消'],
            yes: function(index, layero){
                var roles =[1];
                $("#roles:checked").each(function(index,obj){
                    var roleId = parseInt( $(obj).val() );
                    if( roleId && $.inArray(roleId, roles)==-1 ){
                        roles.push(roleId);
                    }
                });

                //保存用户和角色关系到数据库
                $.ajax({
                    url : _root + '/system/user/role/save',
                    type: "post",
                    data : {
                        userId:userId,
                        roles:roles
                    },
                    success : function(result){
                        if(result.status){
                            layer.close(index);         //关闭页面
                            layer.success(result.message);
                        }else{
                            layer.fail(result.message);
                        }
                    }
                });
            }
        });
    }
</script>
</html>

