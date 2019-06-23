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
    $(this).text();
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

//排序后的俩表
$("#commodityPrice").click(function(){
    $.ajax({
        url:"/shop/commdity/orderByStock.action",
        type:"GET",
        success:function (result) {
            if (result.code == 0){
                var list1 = result.data;
                handleCommodityListDesc(list1);
            }
        },
        error:function(error){
            alert(error);
        }
    })
})
//排序好的list显示在页面
function handleCommodityListDesc(list1){
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
    for (var i = 0;i < list1.length ;i++){

        var commodity = list1[i];

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

$(".on").click(function () {
    var a = $(this).text();
})




/*function handleCommodityListNumber(list) {
    var arr = [list.price];
    var len = arr.length;
    if(arr.length == 1) {
        return
    }
    for(var i = 0; i < len; i++) {
        for(var j = 0; j < len-1; j++) {
            //如果前一个值比后一个值大，那么交换位置
            if(arr[j] > arr[j+1]) {
                var temp = arr[j];
                arr[j]  = arr[j+1];
                arr[j+1] = temp;
            }
        }
    }
    return arr;
}*/


/*分页
* 下一页 上一页  跳转到选中页面
* */
//获取数据库商品列表，判断商品数



