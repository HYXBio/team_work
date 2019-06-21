
getShopCartList();
//获取购物车内的商品列表信息
function getShopCartList() {

    $.ajax({
        url:"/shop/shop_Cart/getShop_CartList.action",
        type:"GET",
        success:function (result) {
            if (result.code=="0")
            {
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

        '        <td> <input type="checkbox" checked="checked" class="pId" value="&&pid" /> </td>\n' +
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


    var allhtml="";
    for (var i = 0; i <list.length ; i++) {
        var commodity=list[i];
        var row_=row.replace("&&pid",commodity.id);
        row_=row_.replace("&&img_url",commodity. img_url);
        row_=row_.replace("&&name",commodity.name);
        row_=row_.replace("&&price",commodity.price);
        row_=row_.replace("&&count",commodity.count);
        row_=row_.replace("&&allprice",commodity.price*commodity.count()*1.0);
        allhtml+=row_;
    }
    $("#getShopCartList").html(allHtml);

}


//添加 - 1 的点击事件
$("#getShopCartList").on("click",".jian",function () {
    var count=$(this).next().val();
    var pId=$(this).parent().parent().children().eq(0).children(":input.pId").val();
    if(count=="1")
    {
        //删除
    }else {
        $.ajax({
            url:"/shop/shop_Cart/minus.action",
            data:{
                pId:pId,

            },
            success:function (result) {
                if (result.code=="0")
                {
                    $(".pCount").val(count-1)
                }
                else {
                    console.log("减一出错")
                }
            },
            error:function (error) {
                console.log(error)
            }
        })
    }

});

//添加 +1 的点击事件
$("#getShopCartList").on("click",".jia",function () {
    var count=$(this).prev().val();
    var pId=$(this).parent().parent().children().eq(0).children(":input.pId").val();
    $.ajax({
        url:"/shop/shop_Cart/addOne.action",
        data:{
            pId:pId
        },
        success:function (result) {
            if (result.code=="0")
            {
                $(".pCount").val(count+1)
            }
            else {
                console.log("加一出错")
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
});

// 改变 商品数量  光标移开的是时候就
$("#getShopCartList").on("blur",".pCount",function () {
    var count=$(this).val();
    var pId=$(this).parent().parent().children().eq(0).children(":input.pId").val();
    $.ajax({
        url:"/shop/shop_Cart/minus.action",
        data:{
            pId:pId
        },
        success:function (result) {
            if (result.code=="0")
            {
                $(".pCount").val(count+1)
            }
            else {
                console.log("减一出错")
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
});

//删除 一种商品
$("#getShopCartList").on("click",".deleteOne",function () {
    var pId=$(this).parent().parent().children().eq(0).children(":input.pId").val();
    $.ajax({
        url:"/shop/shop_Cart/deleteOne.action",
        data:{
            pId:pId
        },
        success:function (result) {
            if (result.code=="0")
            {
                console.log("移除成功");
                $(this).parent().parent().toggle();
            }
            else {
                console.log("移除出错")
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
});
// 批量删除

$(".batchDelete").click(function () {


    var pId=$(this).parent().parent().children().eq(0).children(":input.pId").val();
    var pIds = new Array();
    //给每一个选中的标签都绑定一个方法
    //$("input[name='uname']:checked").each(function(){
    $("input.pId:checked").each(function(){
        //将标签的值放入数组中
        pIds.push($(this).val());//此处添加不能使用add  add不是一个function
    });

    $.ajax({
        url:"/shop/shop_Cart/batchDelete.action",
        data:{
            pId:pId
        },
        success:function (result) {
            if (result.code=="0")
            {
                console.log("全部移除成功");
                $(this).parent().parent().toggle();
            }
            else {
                console.log("移除出错")
            }
        },
        error:function (error) {
            console.log(error)
        }
    })
});

