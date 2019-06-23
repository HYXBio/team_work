$(function () {
    welcome();
    function welcome() {
        var user_name = sessionStorage.getItem("name")||"";
        if(user_name!=""){
            var a = "<button class='user_exit' id='user_exit'>退出</input>"
            $("#user_name_message").html("欢迎："+user_name+a);
            $("#show_user_name").text(user_name);
            $("#user_balance").text(sessionStorage.getItem("balance"));
        }
    }

    $("#userLogin").click(function () {
        var password =hex_md5($("#password").val());
        $.ajax({
            url:"/shop/customer/customerLogin.action",
            type:"POST",
            data:{
                name:$("#name").val(),
                password:password
            }, success:function (result) {
                if(result.code!=0){
                    alert(result.msg);
                }
                else {
                    alert(result.msg);
                    sessionStorage.setItem("name",result.data.name);
                    sessionStorage.setItem("balance",result.data.balance);
                    window.location.href= "pwd.html";
                }
            },error:function (data) {
                alert(data);
            }
        });
    });


    /***
     * 用户退出
     */
    $("#user_exit").click(function(){
            $.ajax({
                url:"/shop/user/customer/exitLogin.action",
                type:"POST",
                success:function (result) {
                    if(result.code == 0){
                        sessionStorage.removeItem("name");
                        sessionStorage.removeItem("balance");
                        window.location.href="index.html";
                    }
                }
            });
    });
});

