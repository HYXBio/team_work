var cartIds=sessionStorage.getItem("cartIds")||"";
//获取购物车选中的商品信息list
getCheckCartByCartIds();
function getCheckCartByCartIds() {
    $.ajax({
        url:"/shop/user/shopping_cart/getOrderCart.action",
        type:"GET",
        data:{
            ids:cartIds
        },
        success:function (result) {
            if (result.code=="0")
            {
                cartList=result.data;
                handleCheckCartList(result.data);
            }
        },
        error:function (error) {
            alert(error)
        }
    })
}


//处理获取到的商品信息  展示在页面上
function   handleCheckCartList(list) {
    var row=' <tr class="tr_c">\n' +
        '                        <td>&nbsp;</td>\n' +
        '                        <td>\n' +
        '                            <table width="100%" border="0" cellspacing="0" cellpadding="0">\n' +
        '                                <tr>\n' +
        '                                    <td width="15%"><img src="&&img_url"/></td>\n' +
        '                                    <td width="85%"><a href="#" class="title"> &&name  </a></td>\n' +
        '                                </tr>\n' +
        '                            </table>\n' +
        '                        </td>\n' +
        '                        <td class="price">&&price</td>\n' +
        '                        <td>&&count</td>\n' +
        '                        <td class="price">&&allprice</td>\n' +
        '                    </tr>'

    var totalPrice=0 ;
    var allhtml="";
    for (var i = 0; i <list.length ; i++) {
        var cart=list[i];
        var commodity=cart.commodity;

        var row_=row.replace("&&img_url",commodity. img_url);
        row_=row_.replace("&&name",commodity.name);
        row_=row_.replace("&&price",commodity.price);
        row_=row_.replace("&&count",cart.commodityNumber);
        row_=row_.replace("&&allprice",commodity.price*cart.commodityNumber*1.0);

        totalPrice+=commodity.price*cart.commodityNumber*1.0;
        allhtml+=row_;


    }

    $("#CheckCartList").html(allhtml);
    $("#all_price").text(totalPrice);

}


//点击保存地址时间  获取省市区 详细信息
$("#saveAddressbtn").click(function () {
    var province = $("#province").val();
    var city=$("#city").val();
    var area=$("#area").val();
    var detialAddress = $("#detialAddress").val();
    saveAddress(province,city,area,detialAddress);
});

//保存地址ajax
function  saveAddress(province,city,area,detialAddress) {
    $.ajax({
        url:"/shop/user/address/upDateOrAddAddress.action",
        data:{
            province:province,
            city: city,
            distric:area,
            detialAddress:detialAddress
        },
        type:"POST",
        success:function (result) {
            if (result.code == "0") {
                alert(result.msg);

                getAddressList();
            }
            else {
                alert(result.msg);
            }
        },
        error:function (error) {
            alert(error);
        }
    })
}



//获取顾客的地址list
getAddressList();
function getAddressList() {
    $.ajax({
        url:"/shop/user/address/getCustomerAddress.action",
        type:"GET",
        success:function (result) {
            if (result.code=="0")
            {
                cartList=result.data;
                handleAddressList(result.data);
            }
        },
        error:function (error) {
            alert(error)
        }
    })
}



//处理获取到的地址信息  展示在页面上
function   handleAddressList(list) {
    var row=' <tr class="tr_c">\n' +
        '        <td> <input    type="radio"  name="addressId" checked="radio" class="addressId" value="&&addressId" />   </td>\n' +

        '                        <td> &&province </td>\n' +
        '                        <td >&&city</td>\n' +
        '                        <td>&&distric</td>\n' +
        '                        <td >&&detialAddress</td>\n' +
        '                        <td><a href="#" class="update">修改</a></td>\n' +
        '                        <td><a href="#" class="deleteOne">删除 </a></td>\n' +
        '                    </tr>';


    var allhtml="";
    for (var i = 0; i <list.length ; i++) {
        var Address=list[i];
        var row_=row.replace("&&addressId",Address.id);
        row_=row_.replace("&&province",Address.province);
        row_=row_.replace("&&city",Address.city);
        row_=row_.replace("&&distric",Address.distric);
        row_=row_.replace("&&detialAddress",Address.detialAddress);
        allhtml+=row_;
    }

    $("#addressList").html(allhtml);
}


//删除一个地址   获取地址ID
$("#addressList").on("click",".deleteOne",function () {
    var addressId=$(this).parent().parent().children().eq(0).children(":input.addressId").val();



    deleteAddress(addressId);
});

//删除一个地址  ajax
function deleteAddress(addressId) {
    $.ajax({
        url:"",
        data:{
            id:addressId
        },
        traditional: true,  //解决传递数组的问题
        success:function (result) {
            if (result.code=="0")
            {
                console.log("全部移除成功");
                getAddressList();

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

//点击结算按钮  提交订单
$(".code").click(function () {

    var id=$("input[name='addressId']:checked").val();
    submitOrder(id);
});

//提交订单的 ajax
function submitOrder(id) {
    $.ajax({
        url:"/shop/user/order/orderGeneration.action",
        data:{
            cart_ids:cartIds,
            address_id:id
        },
        type:"POST",
        success:function (result) {
            if (result.code == "0") {
                alert(result.msg);
                location.href="payply.html";
            }
            else {
                alert(result.msg);
            }
        },
        error:function (error) {
            alert(error);
        }
    })
}
