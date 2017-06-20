<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>角色表单</title>
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
    <input type="hidden" name="id" value="${role.id?default('')}" />
    <div class="form-group">
        <label for="name" class="col-sm-2 ">角色名称<span class="red">*</span></label>
        <div class="col-md-6 col-sm-9">
            <input type="text" <#if role.id??>disabled</#if>  class="form-control validate[required,maxSize[12]]" id="name" name="name" value="${role.name?default('')}" placeholder="角色名称" />
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
            <input type="text" class="form-control validate[maxSize[50]]" id="description" name="description" value="${role.description?default('')}" placeholder="角色描述" />
        </div>
    </div>
</form>
</body>
<script>
    var _root = "${root}";
    var _form = "#form";

    $(function(){
//        $.extend($.validationEngineLanguage.allRules,
//                {
//                    "ajaxNameUnique": {
//                        'url': _root + '/system/role/formValidate',
//                        //"extraData": "dt="+(new Date()).getTime(),
//                        //'alertTextOk': '角色名可用',
//                        'alertText': '角色名已存在！',
//                        'alertTextLoad': '角色名验证中...'
//                    }
//                }
//        );

        $(_form).validationEngine({
            promptPosition: 'centerRight: -300, 0'
        });

        $("#name").on("blur",function(){
           var value = $(this).val();
           validateNameUnique("name",value)
        });
    });



    function validateNameUnique(id,value){
        var status=false;
        $.ajax({
            url :_root + '/system/role/formValidate',
            data:{
                fieldId : id,
                fieldValue: value
            },
            async:false,
            success:function(result){
                if(!result || !result[1]){
                    layer.msg("角色名已存在！");
                }else{
                    status =true;
                }

            }
        });
        return status;
    }


    /**
     * 保存
     */
    function save(){
        if( ! validateNameUnique("name",$("#name").val())){
            return;
        }

        if( $(_form).validationEngine('validate') ){
            return formSubmit();
        }
    }

    /**
     * 表单提交  保存
     */
    function formSubmit() {
        var retrunResult;
        $("#form").ajaxSubmit({
            url: _root + "/system/role/save",
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