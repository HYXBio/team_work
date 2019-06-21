//获取用户列表
getTb_CommodityList();
function getTb_CommodityList() {
    $.ajax({
        url:"/shop/tb_Commodity/getTb_CommodityList.action",
        type:"GET",
        success:function (result) {
            var result = JSON.parse(result)
            if (result.code == 0){
                var list = result.data;
                handleTb_CommodityList(list);
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
function handleTb_CommodityList() {
    var row = '<div class="left">\n' +
        '                    <!--放大镜-->\n' +
        '                \t<div class="pro_detail">\n' +
        '                        <div class="pro_detail_left">\n' +
        '                            <div class="jqzoom"><img src="&tb_CommodityListImg" class="fs" alt="" jqimg="&tb_CommodityListImg" id="bigImg"/></div>\n' +
        '                            <span>\n' +
        '                                 <ul class="imgList">\n' +
        '                                    <li class="on"><img src="&tb_CommodityListImg" alt="" /></li>\n' +
        '                                    <li><img src="&tb_CommodityListImg" alt="" /></li>\n' +
        '                                    <li><img src="&tb_CommodityListImg" alt="" /></li>\n' +
        '                                    <li><img src="&tb_CommodityListImg" alt="" /></li>\n' +
        '                                    <li><img src="&tb_CommodityListImg" alt="" /></li>\n' +
        '                                    <li class="last"><img src="&tb_CommodityListImg" alt="" /></li>\n' +
        '                                 </ul>\n' +
        '                             </span>\n' +
        '                        </div>\n' +
        '                    </div>\n' +
        '                    <!--放大镜-->\n' +
        '                </div>\n' +
        '                <div class="right">\n' +
        '                \t<div class="title">&tb_CommodityListName</div>\n' +
        '                    <div class="info">&tb_CommodityListDesc</div>\n' +
        '                    \n' +
        '                    <div class="price">\n' +
        '                    \t<table width="100%" height="61" border="0" cellpadding="0" cellspacing="0">\n' +
        '                          <tr>\n' +
        '                            <td width="8%">售价：</td>\n' +
        '                            <td width="41%"><b>￥<span>&tb_CommodityListPrice</span></b></td>\n' +
        '                            <td width="7%">原价：</td>\n' +
        '                            <td width="44%"><del>￥<span>&tb_CommodityListDiscount</span>></del></td>\n' +
        '                          </tr>\n' +
        '                        </table>\n' +
        '                  \t</div>\n' +
        '                    <div class="send"><small>配送至：</small><input value="北京朝阳区三环以内" name="" /><span>由 <u>奥凡尼旗舰店</u>从广东佛山市发货，并提供售后服务。</span><div class="clear"></div></div>\n' +
        '                    <div class="size"><small>选择尺码：</small><p><span>进口厚皮 双人位+贵妃位+边几</span><span class="on">进口厚皮 双人位+贵妃位+边几</span><span>进口厚皮 全套系列</span><span>进口厚皮 双+贵+单+边几</span></p><div class="clear"></div></div>\n' +
        '                    <div class="number">\n' +
        '                        <small>数量：</small><input type="text" class="num" value="1"/><span class="jian"></span><span class="jia"></span>\n' +
        '                        <div class="ku"><p>件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;库存<span>&tb_CommodityListIn_stock</span>件</p></div>\n' +
        '                        <div class="clear"></div>\n' +
        '                    </div>\n' +
        '                    <div class="sale"><small>销量：</small><span class="on">&tb_CommodityListSales</span><div class="clear"></div></div>\n' +
        '                    <div class="sub"><a href="shopcar.html" class="btn01"></a><a href="shopcar.html" class="btn02"></a></div>\n' +
        '                </div>';

    var allHtml = "";
    for (var i = 0;i < list.length ;i++){
        var tb_commodity = list[i];

        var row_ = row.replace("&tb_CommodityListImg",tb_commodity.img_url)
            .replace("&tb_CommodityListName",tb_commodity.name)
            .replace("&tb_CommodityListDesc",tb_commodity.description)
            .replace("&tb_CommodityListPrice",tb_commodity.price)
            .replace("&tb_CommodityListDiscount",tb_commodity.discount)
            .replace("&tb_CommodityListIn_stock",tb_commodity.in_stock)
            .replace("&tb_CommodityListSales",tb_commodity.sales_volume);

        allHtml += row_;
    }
    $("#tb_CommodityList").html(allHtml);
}