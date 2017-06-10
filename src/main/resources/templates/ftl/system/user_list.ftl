<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" type="image/ico" href="${root}/static/custome/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${root}/static/zui/dist/css/zui.min.css"> <!-- ZUI 标准版压缩后的 CSS 文件 -->
    <link rel="stylesheet" type="text/css" href="${root}/static/datatable/media/css/jquery.dataTables.css"> <!--datatable 样式 -->
    <script type="text/javascript" src="${root}/static/jquery/jquery-1.11.3.js"></script>  <!-- jQuery -->
    <script type="text/javascript" src="${root}/static/zui/dist/js/zui.min.js"></script>   <!-- ZUI 标准版压缩后的 JavaScript 文件 -->
    <script type="text/javascript" src="${root}/static/datatable/media/js/jquery.dataTables.js"></script>   <!--datatable  -->
</head>
<body>
    <#include "../common/menu.ftl" />
    <div>
        <table id="userTable" class="display" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th>编号</th>
                <th>姓名</th>
                <th>Office</th>
                <th>Extn.</th>
            </tr>
            </thead>
        </table>
    </div>
</body>
<script>
    var root = "${root}";
    $('#userTable').DataTable( {
        "processing": true,
        "serverSide":true,  //后台分页
        "ajax": {
            "url": root + "/system/user/loadData"
        },
        columns: [
            {"data": null },//此列不绑定数据源，用来显示序号
            {"data": "id"},
            {"data": "username"},
            {"data": "createDate"}
        ],
    } );
</script>
</html>