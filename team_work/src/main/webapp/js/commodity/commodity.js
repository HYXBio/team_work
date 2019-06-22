//获取商品列表
getCommodityList();
function getCommodityList() {
    $.ajax({
        url:"/shop/commdity/getCommidtyList.action",
        type:"GET",
        success:function (result) {
            if (result.code == 0){
                var list = result.data.list;
                handleCommodityList(list);
            }
        },
        error:function (error) {
            alert(error)
        }
    })
}

//将商品列表转成HTML标签
function handleCommodityList(list){
    var row = '      <div class="item">\n' +
            '        <a href="product_show.html?id=&commodityId">\n' +
            '        <dl>\n' +
            '        <dt><img src="&commodityImg"></dt>\n' +
            '        <dd>\n' +
            '        <img class="on" src="&commodityImg1"/><img src="&commodityImg2"/><img src="&commodityImg3"/>\n' +
            '        </dd>\n' +
            '        </dl>\n' +
            '        <p class="p01"><font>￥</font>&commodityPrice</font></p>\n' +
            '    <p class="p02"><a href="#">&commodityName</a></p>\n' +
            '    <p class="p03"><span class="sp01">月销量：<b>1335</b></span><span>评价：<strong>3958</strong></span></p>\n' +
            '    </a>\n' +
             '    </div>';
    var allHtml = "";
    for (var i = 0;i < list.length ;i++){

        var commodity = list[i];

        var row_ = row.replace(/&commodityId/g,commodity.id)
            .replace("&commodityImg",commodity.imgUrl)
            .replace("&commodityImg1",commodity.imgUrl)
            .replace("&commodityImg2",commodity.imgUrl)
            .replace("&commodityImg3",commodity.imgUrl)
            .replace("&commodityPrice",commodity.price)
            .replace("&commodityName",commodity.name);

        allHtml += row_;
    }
    $("#commodityList").html(allHtml);
}
