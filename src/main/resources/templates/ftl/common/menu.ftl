<nav class="navbar menu-navbar" role="navigation">
    <div class="container-fluid">
        <!-- 导航头部 -->
        <div class="navbar-header">
            <!-- 移动设备上的导航切换按钮 -->
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse-example">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <!-- 品牌名称或logo -->
            <a class="navbar-brand" href="your/nice/url">ZUI</a>
        </div>
        <!-- 导航项目 -->
        <div class="collapse navbar-collapse navbar-collapse-example">
            <!-- 一般导航项目 -->
            <ul class="nav nav-pills">
                <li class="active"><a href="your/nice/url">项目</a></li>
                <li><a href="your/nice/url">需求</a></li>
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
    </div>
</nav>

<script>


    function getHeight() {
        //页面的高度 - 页头的高度 - 页脚的高度
        var maxHeight =document.body.scrollHeight  - $(".menu-navbar").outerHeight() ;
        $(".container,.container-table,.container-tree").css("height",maxHeight );
    }

    window.onload = function() {
        getHeight();
    };
    window.onresize = function() {
        getHeight();
    };
    $(window).scroll(function() {
        getHeight();
    });


</script>