<link rel="stylesheet" type="text/css" href="${root}/static/font-awesome/css/font-awesome.css"/>    <!-- font-awesome 图标库 -->
<style>
    .nav-right{
        text-align: right;
        line-height: 36px;
        height: 36px;
    }

    .nav-right .fa-sign-out{
        margin-top: 6px;
        color:#145CCD
    }

</style>
<nav class="navbar menu-navbar" role="navigation">
    <div class="container-fluid">
        <!-- 导航头部 -->
        <#--<div class="navbar-header">-->
            <#--<!-- 移动设备上的导航切换按钮 &ndash;&gt;-->
            <#--<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse-example">-->
                <#--<span class="sr-only">切换导航</span>-->
                <#--<span class="icon-bar"></span>-->
                <#--<span class="icon-bar"></span>-->
                <#--<span class="icon-bar"></span>-->
            <#--</button>-->
            <#--<!-- 品牌名称或logo &ndash;&gt;-->
            <#--<a class="navbar-brand" href="your/nice/url">ZUI</a>-->
        <#--</div>-->
        <!-- 导航项目 -->
        <div class="col-xs-10">
            <!-- 一般导航项目 -->
            <ul class="nav nav-pills">
                <#--<li class="active"><a href="your/nice/url">项目</a></li>-->
                <#--<li><a href="your/nice/url">需求</a></li>-->
                <!-- 导航中的下拉菜单 -->
                <li class="dropdown">
                    <a href="your/nice/url" class="dropdown-toggle" data-toggle="dropdown">后台管理<b class="caret"></b></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="${root}/system/user/list">用户管理</a></li>
                        <li><a href="${root}/system/role/list">角色管理</a></li>
                        <li><a href="${root}/system/resource/list">资源管理</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- END .navbar-collapse -->


        <div class="nav-right col-xs-2">
            <a href="${root}/logout" title="退出" ><i class="fa fa-sign-out fa-2x" style=""></i></a>
        </div>
    </div>
</nav>

<script>


    function getHeight() {
        //页面的高度 - 页头的高度 - 页脚的高度
        var maxHeight =document.body.scrollHeight  - $(".menu-navbar").outerHeight() ;
        $(".container,.container-table,.container-tree").css("height",maxHeight );
    }
    /**
     * 页面加载时触发
     */
    window.onload = function() {
        getHeight();
    };
    /**
     * 浏览器窗口变化时 触发
     */
    window.onresize = function() {
        getHeight();
    };
    /**
     * 浏览器窗口滚动时触发
     */
    $(window).scroll(function() {
        getHeight();
    });


</script>