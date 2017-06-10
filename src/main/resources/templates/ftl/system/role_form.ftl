<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>角色表单</title>
    <link rel="shortcut icon" type="image/ico" href="${root}/static/custome/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${root}/static/zui/dist/css/zui.min.css"> <!-- ZUI 标准版压缩后的 CSS 文件 -->

    <script type="text/javascript" src="${root}/static/jquery/jquery-1.11.3.js"></script>  <!-- jQuery -->
    <script type="text/javascript" src="${root}/static/zui/dist/js/zui.min.js"></script>   <!-- ZUI 标准版压缩后的 JavaScript 文件 -->
    <script type="text/javascript" src="${root}/static/jquery/jquery.form-3.51.0.js"></script>   <!-- jquery表单提交插件 -->
</head>
<body style="width:98%">
<br/>
<form id="form" class="form-horizontal">
    <input type="hidden" name="id" value="${role.id?default('')}" />
    <div class="form-group">
        <label for="name" class="col-sm-2">角色名称</label>
        <div class="col-md-6 col-sm-9">
            <input type="text" class="form-control" id="name" name="name" value="${role.name?default('')}" placeholder="角色名称" />
        </div>
    </div>
    <#--<div class="form-group">-->
        <#--<label for="order" class="col-sm-2">排序等级</label>-->
        <#--<div class="col-md-6 col-sm-9">-->
            <#--<input type="number" class="form-control" id="order" name="order" value="${model.order?default('')}" placeholder="等级" />-->
        <#--</div>-->
    <#--</div>-->
    <div class="form-group">
        <label for="description" class="col-sm-2">角色描述</label>
        <div class="col-md-6 col-sm-9">
            <input type="text" class="form-control" id="description" name="description" value="${role.description?default('')}" placeholder="角色描述" />
        </div>
    </div>
</form>
</body>
<script>
    var _root = "${root}";

    /**
     * 保存
     */
    function save(){
       return formSubmit();
    }

    /**
     * 表单提交  保存
     */
    function formSubmit() {
        var retrunStatus;
        $("#form").ajaxSubmit({
            url: _root + "/system/role/save",
            type:"post",
            async:false,
            dataType:"json",
            success:function(result){
                if(result.status){
                    retrunStatus = result.status;
                }else{
                    layer.msg(result.message);
                }

            }
        });
        return retrunStatus;
    }
</script>
</html>