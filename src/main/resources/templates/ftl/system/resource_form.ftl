<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>资源表单</title>
    <link rel="shortcut icon" type="image/ico" href="${root}/static/custome/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${root}/static/zui/dist/css/zui.min.css"> <!-- ZUI 标准版压缩后的 CSS 文件 -->

    <script type="text/javascript" src="${root}/static/jquery/jquery-1.11.3.js"></script>  <!-- jQuery -->
    <script type="text/javascript" src="${root}/static/zui/dist/js/zui.min.js"></script>   <!-- ZUI 标准版压缩后的 JavaScript 文件 -->
    <script type="text/javascript" src="${root}/static/jquery/jquery.form-3.51.0.js"></script>   <!-- jquery表单提交插件 -->
</head>
<body style="width:98%">
<br/>
<form id="form" class="form-horizontal">
    <input type="hidden" name="id" value="${resource.id?default('')}" />
    <input type="hidden" name="model" value="${resource.model?default('')}" />

    <div class="form-group">
        <label for="name" class="col-sm-2">资源名称</label>
        <div class="col-md-6 col-sm-9">
            <input type="text" class="form-control" id="name" name="name" value="${resource.name?default('')}" placeholder="资源名称" />
        </div>
    </div>
    <div class="form-group">
        <label for="url" class="col-sm-2">资源路径</label>
        <div class="col-md-6 col-sm-9">
            <input type="text" class="form-control" id="url" name="url" value="${resource.url?default('')}" placeholder="资源路径" />
        </div>
    </div>
    <div class="form-group">
        <label for="type" class="col-sm-2">资源类型</label>
        <div class="col-md-6 col-sm-9">
            <label class="radio-inline">
                <input type="radio" name="type" value="url" ${ (resource.type=='url')?string('checked','')} > 普通资源
            </label>
            <label class="radio-inline">
                <input type="radio" name="type" valu="menu" ${ (resource.type=='menu')?string('checked','')} > 菜单资源
            </label>
        </div>
    </div>
    <#--
    <div class="form-group">
        <label for="model" class="col-sm-2">所属模块</label>
        <div class="col-md-6 col-sm-9">
            <input type="hidden" class="form-control" id="model" name="model" value="${resource.model?default('')}" />
            <input type="text" class="form-control"  value="${resource.modelName?default('')}" placeholder="所属模块" />
            <ul id="tree" class="ztree"></ul>
        </div>
    </div>
    -->
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
            url: _root + "/system/resource/save",
            type:"post",
            async:false,
            dataType:"json",
            beforeSubmit:function(data){
                console.log(data);
            },
            success:function(result){
                if(result.status){
                    retrunStatus = result.status;
                }else{
                    layer.fail(result.message);
                }

            }
        });
        return retrunStatus;
    }
</script>
</html>