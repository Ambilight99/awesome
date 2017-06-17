<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>模块树</title>
    <link rel="shortcut icon" type="image/ico" href="${root}/static/custome/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${root}/static/custome/css/common.css">                    <!-- 自定义的通用样式 -->
    <link rel="stylesheet" type="text/css" href="${root}/static/zui/dist/css/zui.min.css">                  <!-- ZUI 标准版压缩后的 CSS 文件 -->
    <link rel="stylesheet" type="text/css" href="${root}/static/ztree/css/zTreeStyle/zTreeStyle.css">       <!--ztree 样式 -->

    <script type="text/javascript" src="${root}/static/jquery/jquery-1.11.3.js"></script>               <!-- jQuery -->
    <script type="text/javascript" src="${root}/static/zui/dist/js/zui.min.js"></script>                <!-- ZUI 标准版压缩后的 JavaScript 文件 -->
    <script type="text/javascript" src="${root}/static/ztree/js/jquery.ztree.core.js"></script>         <!--ztree 核心  -->
    <script type="text/javascript" src="${root}/static/ztree/js/jquery.ztree.excheck.js"></script>      <!--ztree 选择  -->
    <script type="text/javascript" src="${root}/static/layer/layer.js"></script>                    <!--layer弹窗   -->
</head>
<body>
    <ul id="model_open_tree" class="ztree"></ul>
</body>
<script>

    /*************************************************ztree ******************************************/
    var _modelIds = ${models!""};
    var _root = "${root}";
    var _treeId = "model_open_tree";
    var _tree = $( "#" + _treeId);
    var _treeObj ;
    var _setting = {
        async: {
            enable: true,
            url: _root + "/system/model/treeData"
        },
        check: {
            enable: true
        },
        view: {
            dblClickExpand: false
        },
        callback: {
            onAsyncSuccess:onAsyncSuccess,
            onClick: onClick
        }
    };

    $(document).ready(function(){
        _treeObj = $.fn.zTree.init( _tree , _setting);
    });

    /**
     * 异步加载完成后选中 部门节点
     */
    function onAsyncSuccess(event, treeId, treeNode, msg){
        for(var i =0 ; i < _modelIds.length ; i++){
            var modelId = _modelIds[i];
            var node = _treeObj.getNodeByParam("id", modelId, null);
            _treeObj.checkNode(node, true, false);
        }
    }
    /**
     * 点击节点触发 选中选项框
     */
    function onClick(event, treeId, treeNode) {
        _treeObj.checkNode(treeNode, !treeNode.checked, null, true);
        return false;
    }

    /*************************************************  main **********************************/
    /**
     * 保存
     */
    function save(){
        var nodes = _treeObj.getCheckedNodes(true);
        return nodes;
    }


</script>
</html>