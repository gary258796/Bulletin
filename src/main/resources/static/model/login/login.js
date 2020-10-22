'use strict';

var App = baseVue.extend({
    data: function(){
        return {
            usernameText: "",
            passwordText: ""
        };
    },
    created: function() {
    },
    methods: {
        login: function(){

            var self = this;

            $('#alertLabel').empty();
            $('#usernameLabel').empty();
            $('#passwordLabel').empty();

            // call Login Api
            if( self.usernameText.length >= 5 && self.passwordText.length >= 5) {
                $.ajax({
                    url: self.apiBaseUrl + "/login",
                    type: "POST",
                    dataType: "text",
                    contentType: "application/json;charset=utf-8",
                    data: JSON.stringify({
                        userName: self.usernameText,
                        password: self.passwordText
                    })
                }).done(function(resp) {
                    if( resp == 'successful')
                        window.location.href = self.apiBaseUrl + "/home" ;
                    else if( resp == 'fail')
                        $("#alertLabel").append('Login Failed! Check your userName and password!');
                }).fail(function() {
                    alert('登入失敗,請洽系統管理員');
                }).always(function() {
                    //
                });
            }
            else{
                // Validate UserName & Password
                if( self.usernameText.length < 5 )
                    $("#usernameLabel").append('UserName can\'t be empty and at least 5 characters.');
                if( self.passwordText.length < 5 )
                    $("#passwordLabel").append('Password can\'t be empty and at least 5 characters.');

                return ;
            }

        },
        register: function() {
            // redirect to register page
            window.location.href = this.apiBaseUrl + "/regist" ;
        }
    }
});