// 请求头封装
layui.define(['jquery', 'table'], function (exports) {
    var $ = layui.jquery, table = layui.table;
    var obj = {
        ajax: function (params) {
            if (!params.headers) {
                params.headers = {　　　　　　　　　　　　// 兼容IE9
                    'cache-control': 'no-cache',
                    'Pragma': 'no-cache',
                    'Authorization': window.sessionStorage.getItem("token")
                };
            }
            if (!params.type) params.type = "GET";
            if (!params.dataType) params.dataType = "json";
            // 配置为false时，表示不从浏览器缓存中获取数据，调试时可以看到，发Get请求时，会自动加上时间戳
            if (!params.cache) params.cache = false;
            if (!params.async) params.async = true;
            params.crossDomain = true === !(document.all);//这句是关键

            if (!params.error) {
                params.error = function (err) {
                    layer.msg("网络连接失败!");
                    console.log(err);
                }
            }
            $.ajax(params);
        },
        tableRender: function (params) {
            if (!params.page) params.page = false;
            if (!params.limit) params.limit = 10;
            if (!params.limits) params.limits = [5, 10, 15, 20];
            params.method = 'get';
            params.contentType = 'application/json           ';
            params.headers = {
                'cache-control': 'no-cache',
                'Pragma': 'no-cache',
                'Authorization': window.sessionStorage.getItem("token")
            };
            // 对分页请求参数page, limit重新设定名称
            params.request = {
                pageName: 'pageNum',
                limitName: 'pageSize'
            }
            // 重新定义返回值
            params.response = {
                statusCode: 200,
                statusName: 'status',
                msgName: 'msg',
                countName: 'total',
                dataName: 'dataList'
            };
            // params.parseData = function (res) {
            //     return {
            //         "code": res.status,
            //         "msg": res.msg,
            //         "count": res.total,
            //         "data": res.dataList
            //     }
            // }
            // 全局结果数据封装
            table.render(params);
        },
        // 监听表格复选框选择
        tableEdit: function (tableId, callback) {
            table.on('edit(' + tableId + ')', callback);
        },
        // 监听表格复选框选择
        tableCheckbox: function (tableId, callback) {
            table.on('checkbox(' + tableId + ')', callback);
        },
        // 监听工具条
        tableTool: function (tableId, callback) {
            table.on('tool(' + tableId + ')', callback);
        },
        tableToolbar: function (tableId, callback) {
            table.on('toolbar(' + tableId + ')', callback);
        },
        // 监听行单击事件
        tableRow: function (tableId, callback) {
            table.on('row(' + tableId + ')', callback);
        },
        // 监听行双击事件
        tableRowDouble: function (tableId, callback) {
            table.on('rowDouble(' + tableId + ')', callback);
        },
        tableReload: function (tableId, params) {
            params.page.curr = 1; // 重新定位第一页
            table.reload(tableId, params, 'data');
        },
        tableSort: function (tableId, callback) {
            table.on('sort(' + tableId + ')', callback);
        }
    };
    //输出接口
    exports('common', obj); // 使用的模块名称
});