//获取商品id
var id = null;
//通过正则表达式获取商品id
getPara();
function getPara() {
    id =getQueryString("id")||"";
    alert(id);
    if (id.length > 0){
        getTb_CommodityList(id);
    }
}

//获取商品详情列表
function getTb_CommodityList(uid) {
    $.ajax({
        url:"/shop/commdity/getCommidtyDetial.action",
        type:"GET",
        data:{
            id:uid
        },
        success:function (result) {
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
function handleTb_CommodityList(list) {
    var row = '<div class="left">\n' +
        '                    <!--放大镜-->\n' +
        '                  <div class="pro_detail">\n' +
        '                        <div class="pro_detail_left">\n' +
        '                            <div class="jqzoom"><img src="&tb_CommodityListImg" class="fs" alt="" jqimg="&tb_CommodityListImg1" id="bigImg"/></div>\n' +
        '                            <span>\n' +
        '                                 <ul class="imgList">\n' +
        '                                    <li class="on"><img src="&tb_CommodityListImg2" alt="" /></li>\n' +
        '                                    <li><img src="&tb_CommodityListImg3" alt="" /></li>\n' +
        '                                    <li><img src="&tb_CommodityListImg4" alt="" /></li>\n' +
        '                                    <li><img src="&tb_CommodityListImg5" alt="" /></li>\n' +
        '                                    <li><img src="&tb_CommodityListImg6" alt="" /></li>\n' +
        '                                    <li class="last"><img src="&tb_CommodityListImg7" alt="" /></li>\n' +
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
        '                            <td width="44%"><del>￥<span>&tb_CommodityListDiscount</span></del></td>\n' +
        '                          </tr>\n' +
        '                        </table>\n' +
        '                  </div>\n' +
        '                    <div class="send"><small>配送至：</small><input value="北京朝阳区三环以内" name="" /><span>由 <u>奥凡尼旗舰店</u>从广东佛山市发货，并提供售后服务。</span><div class="clear"></div></div>\n' +
        '                    <div class="size"><small>选择尺码：</small><p><span>进口厚皮 双人位+贵妃位+边几</span><span class="on">进口厚皮 双人位+贵妃位+边几</span><span>进口厚皮 全套系列</span><span>进口厚皮 双+贵+单+边几</span></p><div class="clear"></div></div>\n' +
        '                    <div class="number">\n' +
        '                        <small>数量：</small><input type="text" class="num" id="commodity_number"/><span class="add"></span><span class="minus"></span>\n' +
        '                        <div class="ku"><p>件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;库存<span>&tb_CommodityListIn_stock</span>件</p></div>\n' +
        '                        <div class="clear"></div>\n' +
        '                    </div>\n' +
        '                    <div class="sale"><small>销量：</small><span class="on">3350</span><div class="clear"></div></div>\n' +
        '                    <div class="sub"><a href="#" class="btn01" id="buy"></a><a href="#" id="join" class="btn02"></a></div>\n' +
        '                </div>' +
        '                <div class="clear"></div>';

        var allHtml = "";
        var tb_commodity = list;
        var row_ = row.replace("&tb_CommodityListImg",tb_commodity.imgUrl)
                    .replace("&tb_CommodityListImg1",tb_commodity.imgUrl)
                    .replace("&tb_CommodityListImg2",tb_commodity.imgUrl)
                    .replace("&tb_CommodityListImg3",tb_commodity.imgUrl)
                    .replace("&tb_CommodityListImg4",tb_commodity.imgUrl)
                    .replace("&tb_CommodityListImg5",tb_commodity.imgUrl)
                    .replace("&tb_CommodityListImg6",tb_commodity.imgUrl)
                    .replace("&tb_CommodityListImg7",tb_commodity.imgUrl)
                    .replace("&tb_CommodityListName",tb_commodity.name)
                    .replace("&tb_CommodityListDesc",tb_commodity.description)
                    .replace("&tb_CommodityListPrice",tb_commodity.price)
                    .replace("&tb_CommodityListDiscount",tb_commodity.discount)
                    .replace("&tb_CommodityListIn_stock",tb_commodity.inStock)
                    .replace("&tb_CommodityListSales",tb_commodity.salesVolume)
                    .replace("&tb_CommodityListId",tb_commodity.id)
                    .replace("&tb_CommodityListId1",tb_commodity.id);

        allHtml = row_;

    $("#tb_CommodityList").html(allHtml);


    //点击立即购买
    $("#buy").click(function(){
        /*
        * 获取输入框内的数量
        * 发送后端
        * */
        var id = tb_commodity.id;
        var number = $("#commodity_number").val()||"";

        if(number > 0){
            //添加id,数量到购物车
            addBuyShopping_cart(id,number);
        }
    });

    //点击立即购买，添加id,数量到购物车
    function addBuyShopping_cart(id,number){
        $.ajax({
            url:"/shop/user/shopping_cart/addToShoppingCar.action",
            type:"GET",
            data:{
                commodityId:id,
                commodityNumber:number
            },
            success:function (result) {
                if (result.code == 0){
                    location.href = "shopcar.html"
                }else {
                    alert(result.msg);
                }
            },
            error:function(error){
                alert(error);
            }
        })
    }

    //点击加入购物车
    $("#join").click(function(){
        /*
        * 获取输入框内的数量
        * 发送后端
        * */
        var id = tb_commodity.id;
        var number = $("#commodity_number").val()||"";

        if(number > 0){
            //添加id,数量到购物车
            $.ajax({
                url:"/shop/user/shopping_cart/addToShoppingCar.action",
                type:"GET",
                data:{
                    commodityId:id,
                    commodityNumber:number
                },
                success:function (result) {
                    if (result.code == 0){
                       alert(result.msg);
                    }
                },
                error:function(error){
                    alert(error);
                }
            })
        }
    });
}

//正则表达式
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}







