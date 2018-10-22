require(['../util/common'], function(globalData) {
    require(['jquery'], function($) {
        $(function() {
            $('#login-submit-btn').click(login);
            $(document).keydown(function(e) {
                if(e.keyCode == 13) {
                    login();
                }
            });
            function login () {
                var username = $('#username').val(),
                password = $('#password').val();
                if(!username) {
                    $('.error-msg.username').text('用户名不能为空');
                }else {
                    $('.error-msg.username').text('');
                } 
                if(!password) {
                    $('.error-msg.password').text('密码不能为空');
                } else {
                    $('.error-msg.password').text('');
                } 
                if(username && password) {
                    $.ajax({
                        url: '/api/login',
                        type: 'post',
                        headers: {
                            "Content-Type": "application/json; charset=utf-8"
                        },
                        data: JSON.stringify({
                            username: username,
                            password: password,
                            rememberMe: !!$('#rememberMe').attr('checked')
                        }),
                        success: function(res) {
                            if(res.success) {
                                location.href = '/index.html';
                            }else {
                                $('.error-msg.password').text(res.msg);
                            }
                        }
                    })
                }
            }
        })
    })
})