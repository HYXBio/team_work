//获取商品列表
getCommodityList();
function getCommodityList() {
    $.ajax({
        url:"/shop/tb_Commodity/getCommodityList.action",
        type:"GET",
        success:function (result) {
            var result = JSON.parse(result)
            if (result.code == 0){
                var list = result.data;
                handleCommodityList(list);
            }else{
                alert(result.msg)
            }
        },
        error:function (error) {
            alert(error)
        }
    })
}

//将商品列表转成HTML标签
function handleCommodityList(list){
    var row = '        <div class="item">\n' +
            '        <a href="product_show.html">\n' +
            '        <dl>\n' +
            '        <dt><img src="&commodityImg"/></dt>\n' +
            '        <dd>\n' +
            '        <img class="on" src="&commodityImg"/><img src="&commodityImg"/><img src="images/img/img39.jpg"/>\n' +
            '        </dd>\n' +
            '        </dl>\n' +
            '        <p class="p01"><font>￥</font>&commodityPrice</font></p>\n' +
            '    <p class="p02"><a href="#">&commodityName</a></p>\n' +
            '    <p class="p03"><span class="sp01">月销量：<b>&commoditySales</b></span><span>评价：<strong>3958</strong></span></p>\n' +
            '    </a>\n' +
            '    </div>';
    var allHtml = "";
    for (var i = 0;i < list.length ;i++){
        var commodity = list[i];

        var row_ = row.replace("&commodityImg",commodity.img_url)
            .replace("&commodityPrice",commodity.price)
            .replace("&commodityName",commodity.name)
            .replace("&commoditySales",commodity.sales_volume);

        allHtml += row_;
    }
    $("#commodityList").html(allHtml);
}
