/**
 *  自定义的表单控件插件
 *
 *  需要依赖ztree
 */
$.fn.extend({
   selectTree:function(option){
       var _this = this;
       if(option){
           option.openIcon = option.openIcon || option.icon;
       }

       //定义插件的默认参数
       var defaultOption ={
           icon : "fa fa-chevron-down",     //默认关闭的样式
           openIcon :"fa fa-chevron-up",                    //展开后的样式
           iconStyle : "float:right; margin-top: 3px;",
           treeNodeDataMap : {  //树节点映射

           },
           setting :{
               async: {
                   enable: true,
                   url: $(this).data("ztree-url")
               },
               data:{
                   simpleData:{
                       enable :true
                   }
               },
               check: {
                   enable: true,
                   chkStyle: "radio",
                   radioType: "all"
               },
               view: {
                   dblClickExpand: false
               },
               callback: {
                   onAsyncSuccess:onAsyncSuccess,
                   onClick: onClick,
                   onCheck: onCheck
               }
           }
       };

       option = $.extend({},defaultOption,option);

       var $icon =$("<i></i>");  //生成i标签
       $icon.addClass(option.icon).attr("style",option.iconStyle);

       var treeId = "select_tree_" + ( $(this).attr("id") || $(this).arrt("name") || "default" );
       var $ztreeDiv = $('<div><ul class="ztree"></ul></div>');
       $ztreeDiv.find("ul").attr("id",treeId);

       $(this).append($icon).after($ztreeDiv);

       //初始化树结构
       var _tree = $( "#" + treeId);
       var _treeObj = $.fn.zTree.init( _tree , option.setting);




       /**
        * 异步加载完成后选中 部门节点
        */
       function onAsyncSuccess(event, treeId, treeNode, msg){
           var selectNodeId =  $("#"+treeId).parent().next("input").val();
           var treeObj = $.fn.zTree.getZTreeObj(treeId);
           var node = treeObj.getNodeByParam("id", selectNodeId, null);
           treeObj.checkNode(node, true, true);
       }
       /**
        * 点击节点触发 选中选项框
        */
       function onClick(event, treeId, treeNode) {
           var treeObj = $.fn.zTree.getZTreeObj(treeId);
           treeObj.checkNode(treeNode, !treeNode.checked, null, true);
           return false;
       }
       /**
        * 点击单选按钮触发
        */
       function onCheck(event, treeId, treeNode) {
           $(_this).html(treeNode.name).append($icon);
           $( $(_this).nextAll("input").get(0)).val(treeNode.id);
       }

       $(_this).on("click",function(){
           var $treeDiv = $(this).next();
           if($treeDiv.is(":hidden")){
               $treeDiv.show();    //如果元素为隐藏,则将它显现
               $(this).find("i").removeClass(option.icon);
           }else{
               $treeDiv.hide();     //如果元素为显现,则将其隐藏
               $(this).find("i").addClass(option.openIcon);
           }
       });

       return this;
   }
});


//初始化
$(function(){
   //生成一个树下拉框
    $(".select_tree").selectTree();
});
