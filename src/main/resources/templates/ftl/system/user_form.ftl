<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户表单</title>
    <link rel="shortcut icon" type="image/ico" href="${root}/static/custome/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${root}/static/zui/dist/css/zui.min.css">                  <!-- ZUI 标准版压缩后的 CSS 文件 -->
    <link rel="stylesheet" type="text/css" href="${root}/static/font-awesome/css/font-awesome.css"/>        <!-- font-awesome 图标库 -->
    <link rel="stylesheet" type="text/css" href="${root}/static/ztree/css/zTreeStyle/zTreeStyle.css">       <!--ztree 样式 -->
    <link rel="stylesheet" type="text/css" href="${root}/static/custome/css/custome.from.plugin.css">                      <!--自定义通用表单样式 -->

    <script type="text/javascript" src="${root}/static/jquery/jquery-1.11.3.js"></script>               <!-- jQuery -->
    <script type="text/javascript" src="${root}/static/zui/dist/js/zui.min.js"></script>                <!-- ZUI 标准版压缩后的 JavaScript 文件 -->
    <script type="text/javascript" src="${root}/static/ztree/js/jquery.ztree.core.js"></script>         <!--ztree 核心  -->
    <script type="text/javascript" src="${root}/static/ztree/js/jquery.ztree.excheck.js"></script>      <!--ztree 选择  -->
    <script type="text/javascript" src="${root}/static/ztree/js/jquery.ztree.exedit.js"></script>       <!--ztree 编辑  -->
    <script type="text/javascript" src="${root}/static/jquery/jquery.form-3.51.0.js"></script>          <!-- jquery表单提交插件 -->
    <script type="text/javascript" src="${root}/static/custome/js/custome.from.plugin.js"></script>     <!-- 自定义的表单控件插件 -->
</head>
<body style="width:98%">
<br/>
<form id="form" class="form-horizontal">
    <input type="hidden" name="id" value="${user.id?default('')}" />
    <div class="form-group">
        <label for="username" class="col-sm-2">登录名</label>
        <div class="col-md-6 col-sm-9">
            <input type="text" class="form-control" id="username" name="username" value="${user.username?default('')}" placeholder="登录名" />
        </div>
    </div>
    <div class="form-group">
        <label for="password" class="col-sm-2">密码</label>
        <div class="col-md-6 col-sm-9">
            <input type="password" class="form-control" id="password" name="password" value="" placeholder="密码" />
        </div>
    </div>
    <div class="form-group">
        <label for="verifyPassword" class="col-sm-2">确认密码</label>
        <div class="col-md-6 col-sm-9">
            <input type="password" class="form-control" id="verifyPassword" name="verifyPassword"  placeholder="确认密码" />
        </div>
    </div>
    <div class="form-group">
        <label for="name" class="col-sm-2">姓名</label>
        <div class="col-md-6 col-sm-9">
            <input type="text" class="form-control" id="name" name="name" value="${user.name?default('')}" placeholder="姓名" />
        </div>
    </div>
    <div class="form-group">
        <label for="phone" class="col-sm-2">手机号</label>
        <div class="col-md-6 col-sm-9">
            <input type="text" class="form-control" id="phone" name="phone" value="${user.phone?default('')}" placeholder="手机号" />
        </div>
    </div>
    <div class="form-group">
        <label for="email" class="col-sm-2">邮箱</label>
        <div class="col-md-6 col-sm-9">
            <input type="text" class="form-control" id="email" name="email" value="${user.email?default('')}" placeholder="邮箱" />
        </div>
    </div>
    <div class="form-group">
        <label for="department" class="col-sm-2">所属部门</label>
        <div class="col-md-6 col-sm-9">
            <span class="form-control select_tree" id="departmentName" data-ztree-url="${root}/system/department/treeData">
                ${user.departmentName?default('111')}
            </span>
            <input type="hidden" name="department" value="${user.department?default('')}" />
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
            url: _root + "/system/user/save",
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
                    layer.msg(result.message);
                }

            }
        });
        return retrunStatus;
    }
</script>
</html>