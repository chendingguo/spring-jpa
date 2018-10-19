$(document).ready(function () {


    $('#user-table').bootstrapTable({
        url: '/api/adi/user/list2',         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        queryParams: queryParams,//传递参数（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10],        //可供选择的每页的行数（*）
        search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        contentType: "application/x-www-form-urlencoded",
        strictSearch: true,
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        height: 400,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "no",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        toolbar: '#select-div',
        columns: [
            {
                field: 'id',
                title: 'ID'
            }, {
                field: 'name',
                title: '名字'
            },

            {
                field: 'email',
                title: '邮箱'
            },
            {
                field: 'operate',
                title: 'edit',
                formatter: operateFormatter,//自定义方法，添加操作按钮
                events: 'operateEvents'
            },
        ],

        responseHandler: handler
    });

    $("[id^='select']").change(function () {

        var opt = {
            query: {
                type: $("#select-user-type").val(),
                packageType:$("#select-package-type").val()
            }
        }

        $("#user-table").bootstrapTable('refresh', opt);
    });

});


function queryParams(params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的

        limit: params.limit, // 页面大小
        offset: params.offset
    };
    return temp;
}


function operateFormatter(value, row, index) {//赋予的参数
    return "<a  class='edit' href='javascript:void(0)'>编辑</a>";
}


function handler(res) {
    return res.content;
}

function editUser() {
    var email = $("#email").val();
    var id = $("#user_id").val();
    var jsonObj = {
        id: id,
        email: email
    }
    $.ajax({
        type: "POST",
        url: "/api/adi/user/update",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(jsonObj),
        dataType: "json",
        success: function (result) {
            $('#user-detail-modal').modal('hide');
            var opt = {};
            $("#user-table").bootstrapTable('refresh', opt);

        },
        error: function () {
            alert("error");
        }
    })


}


window.operateEvents = {
    'click .edit': function (e, value, row, indexindex) {
        console.log(row);
        $("#user_id").val(row.id);
        $("#name").val(row.name);
        $("#email").val(row.email)
        $('#user-detail-modal').modal('show')
        return false;
    }

};


