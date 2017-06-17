<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>模块表单</title>
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
    <input type="hidden" name="id" value="${model.id?default('')}" />
    <input type="hidden" name="parent" value="${model.parent?default('')}" />

    <div class="form-group">
        <label for="name" class="col-sm-2">模块名称<span class="red">*</span></label>
        <div class="col-md-6 col-sm-9">
            <input type="text" class="form-control validate[required,maxSize[12]]" id="name" name="name" value="${model.name?default('')}" placeholder="模块名称" />
        </div>
    </div>
    <#--
    <div class="form-group">
        <label for="abbr" class="col-sm-2">模块简称</label>
        <div class="col-md-6 col-sm-9">
            <input type="text" class="form-control validate[maxSize[16]]" id="abbr" name="abbr" value="${model.abbr?default('')}" placeholder="模块简称" />
        </div>
    </div>
    -->

    <div class="form-group">
        <label for="url" class="col-sm-2">模块路径</label>
        <div class="col-md-6 col-sm-9">
            <input type="text" class="form-control validate[maxSize[100]]" id="url" name="url" value="${model.url?default('')}" placeholder="模块路径" />
        </div>
    </div>
    <div class="form-group">
        <label for="description" class="col-sm-2">模块介绍</label>
        <div class="col-md-6 col-sm-9">
            <input type="text" class="form-control validate[maxSize[50]]" id="description" name="description" value="${model.description?default('')}" placeholder="模块介绍" />
        </div>
    </div>
    <div class="form-group">
        <label for="order" class="col-sm-2">排序等级</label>
        <div class="col-md-6 col-sm-9">
            <input type="number" class="form-control validate[min[-999],max[9999]]" id="order" name="order" value="${model.order?default('')}" placeholder="等级" />
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
    })
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
            url: _root + "/system/model/save",
            type:"post",
            async:false,
            dataType:"json",
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