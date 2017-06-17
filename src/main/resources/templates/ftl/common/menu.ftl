<link rel="stylesheet" type="text/css" href="${root}/static/font-awesome/css/font-awesome.css"/>    <!-- font-awesome 图标库 -->
<style>
</style>
<nav class="navbar menu-navbar" role="navigation">
    <div class="container-fluid">
        <!-- 导航头部 -->
        <div class="navbar-header col-xs-1">
            <!-- 移动设备上的导航切换按钮 -->
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse-example">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <!-- 品牌名称或logo -->
            <a  class="navbar-brand" href="${root}/index" >AWESOME</a>
        </div>
        <!-- 导航项目 -->
        <div class="col-xs-9 nav-menu">
            <!-- 菜单导航  -->
            <ul class="nav nav-pills">
                <#-- 动态生成模块名称  -->
                <#list  Session["menus"] as menu >
                    <#if menu.children?? && (menu.children?size>0) >
                        <li class="dropdown">
                            <a href="${root}${menu.url}" class="dropdown-toggle" data-toggle="dropdown">
                                 ${menu.name}<b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu" role="menu">
                            <#list  menu.children as m >
                                <li><a href="${root}${m.url}">${m.name}</a></li>
                            </#list>
                            </ul>
                         </li>
                    <#else>
                        <li><a href="${root}${menu.url}"> ${menu.name}</a></li>
                    </#if>
                </#list>

                <#---  写死的模块名称
                <li class="dropdown">
                    <a href="your/nice/url" class="dropdown-toggle" data-toggle="dropdown">后台管理<b class="caret"></b></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="${root}/system/user/list">用户管理</a></li>
                        <li><a href="${root}/system/role/list">角色管理</a></li>
                        <li><a href="${root}/system/resource/list">资源管理</a></li>
                    </ul>
                </li>
                -->
            </ul>
        </div><!-- END .navbar-collapse -->

        <div class="nav-right col-xs-2">
            <div class="btn-group user-info">
                <span class="dropdown-toggle" data-toggle="dropdown">
                    <img src="" onerror="this.src='${root}/static/custome/images/user.jpg'" width="35px" height="35px" class="img-circle" alt="">
                </span>
                <div class="dropdown-menu pull-right" >
                    <div style=" max-height:200px; overflow-y: scroll;" >
                        <div>
                             ${Session.SPRING_SECURITY_CONTEXT.authentication.principal.id!""}
                        </div>
                        <div>
                            陆奇毕业于复旦大学，获计算机科学学士、硕士学位，此后就读于卡耐基梅隆大学，获计算机科学博士学位。陆奇博士除了在学术界发表过一系列高质量的研究论文，还持有40多项美国专利。1998年，陆奇加入雅虎公司，2007年晋升为雅虎执行副总裁。2008年8月，陆奇离开雅虎，并于次年1月正式加盟微软任网络服务集团总裁，2013年出任微软集团全球执行副总裁，2016年9月宣布从微软离职。
                        </div>
                    </div>
                    <div><a href="${root}/logout" class="a-button" title="退出" >【退出】</a></div>
                </div>
            </div>
            <#--
            <a href="${root}/logout" title="退出" ><i class="fa fa-sign-out fa-2x" style=""></i></a>
            -->
        </div>
    </div>
</nav>

<script>


    function getHeight() {
        //页面的高度 - 页头的高度 - 页脚的高度 - title的高度
        var maxHeight =document.body.scrollHeight  - $(".menu-navbar").outerHeight() -$(".container-title").outerHeight() ;
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