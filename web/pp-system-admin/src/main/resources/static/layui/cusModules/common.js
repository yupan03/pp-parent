layui.define(['jquery', 'table'], function (exports) {
    var $ = layui.jquery, table = layui.table;
    var obj = {
        ajax: function (obj) {
            if (!obj.headers) {
                obj.headers = {　　　　　　　　　　　　// 兼容IE9
                    'cache-control': 'no-cache',
                    'Pragma': 'no-cache',
                    'Authorization': window.sessionStorage.getItem("token")
                };
            }
            if (!obj.type) obj.type = "GET";
            if (!obj.dataType) obj.dataType = "json";
            // 配置为false时，表示不从浏览器缓存中获取数据，调试时可以看到，发Get请求时，会自动加上时间戳
            if (!obj.cache) obj.cache = false;
            if (!obj.async) obj.async = true;
            obj.crossDomain = true === !(document.all);//这句是关键

            if (!obj.error) {
                obj.error = function (err) {
                    layer.msg("网络连接失败!");
                    console.log(err);
                }
            }
            $.ajax(obj);
        },
        render: function (obj) {
            obj.headers = {
                'cache-control': 'no-cache',
                'Pragma': 'no-cache',
                'Authorization': window.sessionStorage.getItem("token")
            };
            table.render(obj);
        }
    };
    //输出接口
    exports('common', obj); // 使用的模块名称
});