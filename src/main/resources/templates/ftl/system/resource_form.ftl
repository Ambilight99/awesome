<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>资源表单</title>
    <link rel="shortcut icon" type="image/ico" href="${root}/static/custome/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${root}/static/custome/css/common.css">                    <!-- 自定义的通用样式 -->
    <link rel="stylesheet" type="text/css" href="${root}/static/zui/dist/css/zui.min.css"> <!-- ZUI 标准版压缩后的 CSS 文件 -->
    <link rel="stylesheet" type="text/css" href="${root}/static/jquery-validation-engine/css/validationEngine.jquery.css"> <!-- jquery表单验证插件 样式 -->

    <script type="text/javascript" src="${root}/static/jquery/jquery-1.11.3.js"></script>  <!-- jQuery -->
    <script type="text/javascript" src="${root}/static/zui/dist/js/zui.min.js"></script>   <!-- ZUI 标准版压缩后的 JavaScript 文件 -->
    <script type="text/javascript" src="${root}/static/jquery/jquery.form-3.51.0.js"></script>   <!-- jquery表单提交插件 -->
    <script type="text/javascript" src="${root}/static/jquery-validation-engine/js/jquery.validationEngine.js"></script>   <!-- jquery表单验证插件   -->
    <script type="text/javascript" src="${root}/static/layer/layer.js"></script>                    <!--layer弹窗   -->
</head>
<body style="width:98%">
<br/>
<form id="form" class="form-horizontal">
    <input type="hidden" name="id" value="${resource.id?default('')}" />
    <input type="hidden" name="model" value="${resource.model?default('')}" />

    <div class="form-group">
        <label for="name" class="col-sm-2">资源名称<span class="red">*</span></label>
        <div class="col-md-6 col-sm-9">
            <input type="text" class="form-control validate[required,maxSize[20]]" id="name" name="name" value="${resource.name?default('')}" placeholder="资源名称" />
        </div>
    </div>
    <div class="form-group">
        <label for="url" class="col-sm-2">资源路径<span class="red">*</span></label>
        <div class="col-md-6 col-sm-9">
            <input type="text" class="form-control validate[required,maxSize[100]]" id="url" name="url" value="${resource.url?default('')}" placeholder="资源路径" />
        </div>
    </div>
    <div class="form-group">
        <label for="type" class="col-sm-2">资源类型<span class="red">*</span></label>
        <div class="col-md-6 col-sm-9">
            <label class="radio-inline">
                <input type="radio" class="validate[required] radio" name="type" value="url" ${ (resource.type=='url')?string('checked','')} > 普通资源
            </label>
            <label class="radio-inline">
                <input type="radio" class="validate[required] radio" name="type" value="menu" ${ (resource.type=='menu')?string('checked','')} > 菜单资源
            </label>
        </div>
    </div>
</form>
</body>
<script>
    var _root = "${root}";

    $(function(){
        $('#form').validationEngine({
            promptPosition: 'centerRight: -300, 0'
        });
    });

    /**
     * 保存
     */
    function save(){
        if( $('#form').validationEngine('validate') ){
            return formSubmit();
        }
    }

    /**
     * 表单提交  保存
     */
    function formSubmit() {
        var retrunResult=null;
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
                    retrunResult = result;
                }else{
                    layer.fail(result.message);
                }

            }
        });
        return retrunResult;
    }
</script>
</html>