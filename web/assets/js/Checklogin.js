/**
 * Created by aboge on 2017/5/25.
 */

/*验证当前用户是否登录*/
function check_login()
{
    $.ajax({
        url: '/INC/CheckLogin.do',
        type: "get",
        success: function (data) {
            if (data.username) {
                $(".username").text(data.username);
                $(".avatar").attr("src", "assets/img/user/" + data.avatar);

                /*用户主页面的头像和姓名*/
              /*  $(".user-avatar-img").attr("src","assets/img/user/"+ data.avatar);
                $(".user-avatar-img").attr("alt",data.username);*/
                /*用户头像和姓名*/
                /*$(".user-name").html("<h1>"+data.username+"</h1>");*/
                $(".avatar-edit").attr("src","assets/img/user/"+ data.avatar);

                /*works详细展示列表内，评论头像*/
                /*$("#reviewer-avatar").attr("src","assets/img/user/"+ data.avatar);*/
            }
            else {
                alert("您还没有登录，请先登录！");
                window.location.href = "inc-login.html";
            }
        }
    });

}

/*退出当前用户*/
$('#quit').click(function () {

    $.ajax({
        url: '/INC/Logout.do',
        type: "get",
        success: function (data) {
            alert(data.result);
            window.location.href = "inc-login.html";
        }
    });
});

