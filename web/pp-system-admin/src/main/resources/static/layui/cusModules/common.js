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
        render: function (params) {
            if (!params.page) params.page = false;
            if (!params.limit) params.limit = 10;
            if (!params.limits) params.limits = [5, 10, 15, 20];
            params.headers = {
                'cache-control': 'no-cache',
                'Pragma': 'no-cache',
                'Authorization': window.sessionStorage.getItem("token")
            };
            // 重新定义返回值
            params.response = {
                'statusCode': 200
            };
            params.parseData = function (res) {
                return {
                    "code": res.status,
                    "msg": res.msg,
                    "count": res.total,
                    "data": res.dataList
                }
            }
            // 全局结果数据封装
            table.render(params);
        }
    };
    //输出接口
    exports('common', obj); // 使用的模块名称
});