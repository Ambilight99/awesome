<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户表单</title>
    <link rel="shortcut icon" type="image/ico" href="${root}/static/custome/images/favicon.ico">

    <link rel="stylesheet" type="text/css" href="${root}/static/custome/css/common.css">                    <!-- 自定义的通用样式 -->
    <link rel="stylesheet" type="text/css" href="${root}/static/zui/dist/css/zui.min.css">                  <!-- ZUI 标准版压缩后的 CSS 文件 -->
    <link rel="stylesheet" type="text/css" href="${root}/static/font-awesome/css/font-awesome.css"/>        <!-- font-awesome 图标库 -->
    <link rel="stylesheet" type="text/css" href="${root}/static/ztree/css/zTreeStyle/zTreeStyle.css">       <!--ztree 样式 -->
    <link rel="stylesheet" type="text/css" href="${root}/static/custome/css/custome.from.plugin.css">                      <!--自定义通用表单样式 -->
    <link rel="stylesheet" type="text/css" href="${root}/static/jquery-validation-engine/css/validationEngine.jquery.css"> <!-- jquery表单验证插件 样式 -->

    <script type="text/javascript" src="${root}/static/jquery/jquery-1.11.3.js"></script>               <!-- jQuery -->
    <script type="text/javascript" src="${root}/static/zui/dist/js/zui.min.js"></script>                <!-- ZUI 标准版压缩后的 JavaScript 文件 -->
    <script type="text/javascript" src="${root}/static/ztree/js/jquery.ztree.core.js"></script>         <!--ztree 核心  -->
    <script type="text/javascript" src="${root}/static/ztree/js/jquery.ztree.excheck.js"></script>      <!--ztree 选择  -->
    <script type="text/javascript" src="${root}/static/jquery/jquery.form-3.51.0.js"></script>          <!-- jquery表单提交插件 -->
    <script type="text/javascript" src="${root}/static/custome/js/custome.from.plugin.js"></script>     <!-- 自定义的表单控件插件 -->
    <script type="text/javascript" src="${root}/static/jquery-validation-engine/js/jquery.validationEngine.js"></script>   <!-- jquery表单验证插件   -->
    <script type="text/javascript" src="${root}/static/layer/layer.js"></script>                    <!--layer弹窗   -->
</head>
<body style="width:98%">
<br/>
<form id="form" class="form-horizontal">
    <input type="hidden" name="id" value="${user.id?default('')}" />
    <div class="form-group">
        <label for="username" class="col-sm-2">登录名<span class="red">*</span></label>
        <div class="col-md-6 col-sm-9">
            <input type="text"  <#if user.id??>disabled</#if> class="form-control validate[required,custom[onlyLetterNumber],minSize[2],maxSize[10]],ajax[ajaxUserUnique]" id="username" name="username" value="${user.username?default('')}" placeholder="登录名" />
        </div>
    </div>
    <#if !user.id??>
    <div class="form-group">
        <label for="password" class="col-sm-2">密码<span class="red">*</span></label>
        <div class="col-md-6 col-sm-9">
            <input type="text" class="form-control validate[required,custom[onlyLetterNumber],minSize[6],maxSize[6]]" id="password" name="password" value="" placeholder="密码" />
        </div>
    </div>
    </#if>
    <#--
    <div class="form-group">
        <label for="verifyPassword" class="col-sm-2">确认密码</label>
        <div class="col-md-6 col-sm-9">
            <input type="password" class="form-control validate[condRequired[password],equals[password]]" id="verifyPassword" name="verifyPassword"  placeholder="确认密码" />
        </div>
    </div>
    -->
    <div class="form-group">
        <label for="name" class="col-sm-2">姓名<span class="red">*</span></label>
        <div class="col-md-6 col-sm-9">
            <input type="text" class="form-control validate[required,maxSize[20]]" id="name" name="name" value="${user.name?default('')}" placeholder="姓名" />
        </div>
    </div>
    <div class="form-group">
        <label for="phone" class="col-sm-2">手机号</label>
        <div class="col-md-6 col-sm-9">
            <input type="text" class="form-control validate[custom[phone]]" id="phone" name="phone" value="${user.phone?default('')}" placeholder="手机号" />
        </div>
    </div>
    <div class="form-group">
        <label for="email" class="col-sm-2">邮箱</label>
        <div class="col-md-6 col-sm-9">
            <input type="text" class="form-control validate[custom[email]]" id="email" name="email" value="${user.email?default('')}" placeholder="邮箱" />
        </div>
    </div>
    <div class="form-group">
        <label for="department" class="col-sm-2">所属部门</label>
        <div class="col-md-6 col-sm-9">
            <span class="form-control select_tree" id="departmentName" data-ztree-url="${root}/system/department/treeData">
                ${user.departmentName?default('')}
            </span>
            <input type="hidden" class="validate[required]" name="department" value="${user.department?default('')}" />
        </div>
    </div>
    <div class="form-group">
        <label for="type" class="col-sm-2">性别</label>
        <div class="col-md-6 col-sm-9">
            <label class="radio-inline">
                <input type="radio" name="sex" value="男" ${ ( (user.sex?default("男"))=='男')?string('checked','')} > 男
            </label>
            <label class="radio-inline">
                <input type="radio" name="sex" valu="女" ${ (user.sex?default("男")=='女')?string('checked','')} > 女
            </label>
        </div>
    </div>
</form>
</body>
<script>
    var _root = "${root}";

    $(function(){
        $.extend($.validationEngineLanguage.allRules,
            {
                "ajaxUserUnique": {
                    'url': _root + '/system/user/formValidate',
                    //"extraData": "dt="+(new Date()).getTime(),
                    //'alertTextOk': '用户名可用',
                    'alertText': '用户名已存在！',
                    'alertTextLoad': '用户名验证中...'
                }
            }
        );

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
            url: _root + "/system/user/save",
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