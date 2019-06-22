var cartList;
getShopCartList();
//获取购物车内的商品列表信息
function getShopCartList() {

    $.ajax({
        url:"/shop/user/shopping_cart/getCustomerShoppingCar.action",
        type:"GET",
        success:function (result) {
            if (result.code=="0")
            {
                cartList=result.data;
                handleShopCartList(result.data);
            }
        },
        error:function (error) {
            alert(error)
        }
    })

}

//处理获取到的商品信息  展示在页面上
function  handleShopCartList(list) {
    var row=
        '  <tr class="tr_c" >    ' +

        '        <td> <input    type="checkbox" checked="checked" class="pId" value="&&pid" /> </td>\n' +
        '\n' +
        '                    <td colspan="2">\n' +
        '\n' +
        '                    \t<table width="100%" border="0" cellspacing="0" cellpadding="0">\n' +
        '\n' +
        '                          <tr>\n' +
        '                            <td width="15%"><img src="&&img_url"/></td>\n' +
        '                            <td width="85%"><a href="#" class="title">&&name</a></td>\n' +
        '                          </tr>\n' +
        '                        </table>\n' +
        '                    </td>\n' +
        '                    <td class="price">&&price</td>\n' +
        '                    <td><span class="jian">-</span><input type="text" class="pCount" value="&&count"/><span class="jia">+</span></td>\n' +
        '                    <td class="price">&&allprice</td>\n' +
        '           <td><a href="#" class="deleteOne">删除 </a></td>\n' +
        ' </tr>   ' +
        '';
    var totalPrice=0 ;
    var allhtml="";
    for (var i = 0; i <list.length ; i++) {
        var cart=list[i];
        var commodity=cart.commodity;
        var row_=row.replace("&&pid",cart.id);
        row_=row_.replace("&&img_url",commodity. img_url);
        row_=row_.replace("&&name",commodity.name);
        row_=row_.replace("&&price",commodity.price);
        row_=row_.replace("&&count",cart.commodityNumber);
        row_=row_.replace("&&allprice",commodity.price*cart.commodityNumber*1.0);
        row_=row_.replace("&&cnt",i);
        totalPrice+=commodity.price*cart.commodityNumber*1.0;
        allhtml+=row_;


    }

    $("#getShopCartList").html(allhtml);
    $("#totalCount").text(list.length);
    $("#totalPrice").text(totalPrice);

}

//添加 - 1 的点击事件
$("#getShopCartList").on("click",".jian",function () {
    var commodityNumber=0;
    commodityNumber=  $(this).next().val()-1;
    var pId=$(this).parent().parent().children().eq(0).children(":input.pId").val();

    if(commodityNumber==0)
    {
        //删除
        batchDelete(pId)

    }else {
        $(this).next().val(commodityNumber);
        updateCount(pId,commodityNumber);
    }

});

//添加 +1 的点击事件
$("#getShopCartList").on("click",".jia",function () {
    var commodityNumber=0;
        commodityNumber=1+Number($(this).prev().val());
    var pId=$(this).parent().parent().children().eq(0).children(":input.pId").val();

    $(this).prev().val(commodityNumber);
    updateCount(pId,commodityNumber);

});

// 改变 商品数量  光标移开的是时候就
$("#getShopCartList").on("blur",".pCount",function () {
    var commodityNumber=$(this).val();
    var pId=$(this).parent().parent().children().eq(0).children(":input.pId").val();
    updateCount(pId,commodityNumber);
});


//删除 一种商品
$("#getShopCartList").on("click",".deleteOne",function () {
    var pId=$(this).parent().parent().children().eq(0).children(":input.pId").val();
    // var id=new Array();
    // id.push(pId);

    batchDelete(pId)
});

// 批量删除
$(".batchDelete").click(function () {

    var pIds = [];
    //给每一个选中的标签都绑定一个方法
    //$("input[name='uname']:checked").each(function(){

    $("input.pId:checked").each(function(i){
        //将标签的值放入数组中
        pIds[i]=$(this).val();
       // pIds.push($(this).val());//此处添加不能使用add  add不是一个function
    });
    batchDelete(pIds)

});



//移除购物车中的商品
function batchDelete(pIds) {
    $.ajax({
        url:"/shop/user/shopping_cart/deleteShoppingCar.action",
        data:{
            id:pIds
        },
        traditional: true,  //解决传递数组的问题
        success:function (result) {
            if (result.code=="0")
            {
                console.log("全部移除成功");
                getShopCartList();
            }
            else {
                console.log("移除出错")
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
}

//修改商品的数量
function updateCount(pId,commodityNumber)
{
    $.ajax({
        url:"/shop/user/shopping_cart/updateShoppingCar.action",
        data:{
            id:pId,
            commodityNumber:commodityNumber

        },
        success:function (result) {
            if (result.code=="0")
            {
                alert("修改成功")
            }
            else {
                getShopCartList();
            }
        },
        error:function (error) {
            alert(error);
        }
    })

}

//点击结算  将选中的商品ids存入到session中
$(".code").click(function () {

    var pIds = [];
    //给每一个选中的标签都绑定一个方法
    //$("input[name='uname']:checked").each(function(){

    $("input.pId:checked").each(function(i){
        //将标签的值放入数组中
        pIds[i]=$(this).val();
        // pIds.push($(this).val());//此处添加不能使用add  add不是一个function
    });

   sessionStorage.setItem("pIds",pIds);
   location.href="info.html";
});
