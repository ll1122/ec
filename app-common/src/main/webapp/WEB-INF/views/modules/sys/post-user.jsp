<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    var postId = "${model.id}";
    var userIds = ${fns:toJson(userIds)};
</script>
<script type="text/javascript" src="${ctxStatic}/app/modules/sys/post-user${yuicompressor}.js?_=${sysInitTime}" charset="utf-8"></script>
<div class="easyui-layout" fit="true" style="margin: 0px;border: 0px;overflow: hidden;width:100%;height:100%;">
    <div data-options="region:'north',title:'过滤条件',collapsed:true,split:false,border:false"
         style="padding: 0px; height: 70px;width:100%; overflow-y: hidden;">
        <form id="user_post_search_form" style="padding: 5px;">
            <input class="easyui-textbox" id="query" name="query"
                   data-options="buttonText:' 查 询 ',buttonIcon:'easyui-icon-search',prompt:'用户信息...',onClickButton:searchPostUser"
                   onkeydown="if(event.keyCode==13)searchPostUser()" maxLength="36"
                   style="width:250px;height:28px;">
        </form>
    </div>
    <%-- 中间部分 列表 --%>
    <div data-options="region:'center',split:false,border:false"
         style="padding: 0px; height: 100%;width:100%; overflow-y: hidden;">
        <table id="post_user_datagrid"></table>
    </div>
</div>
<iframe id="annexFrame" style="display:none" src=""></iframe>