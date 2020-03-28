/**
 * Created by aboge on 2017/5/15.
 */
function check_login()
{
    var name=$("#user_name").val();
    var pass=$("#password").val();
    $.ajax({
        url:'/INC/CheckLogin.do',
        type:"post",
        data:{
            'username':name,
            'password':pass
        }
    }).then(function(data){

        if(data[0].result)
        {
            alert("登录成功！");
            window.location.href="inc-home.html";


        }
        else
        {
            $("#login_form").removeClass('shake_effect');
            setTimeout(function()
            {
                $("#login_form").addClass('shake_effect')
            },1);
        }
    });

}
function check_register(){
    var name = $("#r_user_name").val();
    var pass = $("#r_password").val();
    var email = $("#r_email").val();
    $.ajax({
        url:'/INC/Register.do',
        type:"post",
        data:{
            'username':name,
            'password':pass,
            'email':email
        }
    }).then(function(data){

        if(data[0].result)
         {
             alert("注册成功！");
         }
         else
         {
         $("#login_form").removeClass('shake_effect');
         setTimeout(function()
         {
         $("#login_form").addClass('shake_effect')
         },1);
         }
    });


}
$(function(){
    $("#create").click(function(){
        check_register();
        return false;
    })
    $("#login").click(function(){
        check_login();
        return false;
    })
    $('.message a').click(function () {
        $('form').animate({
            height: 'toggle',
            opacity: 'toggle'
        }, 'slow');
    });
})