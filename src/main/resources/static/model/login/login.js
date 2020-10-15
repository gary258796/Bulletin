'use strict';

var App = baseVue.extend({
    data: function(){
        return {
            accountText: "",
            passwordText: ""
        };
    },
    created: function() {
    },
    methods: {
        login: function(){

            var self = this;

            $('#alertLabel').empty();
            $('#accountLabel').empty();
            $('#passwordLabel').empty();

            // call Login Api
            if( self.accountText.length >= 10 && self.passwordText.length >= 10) {
                $.ajax({
                    url: self.apiBaseUrl + "/login",
                    type: "POST",
                    dataType: "text",
                    contentType: "application/json;charset=utf-8",
                    data: JSON.stringify({
                        account: self.accountText,
                        password: self.passwordText
                    })
                }).done(function(resp) {
                    if( resp == 'successful')
                        window.location.href = self.apiBaseUrl + "/Home/home" ;
                    else if( resp == 'fail')
                        $("#alertLabel").append('Login Failed! Check your account and password!');
                }).fail(function() {
                    alert('登入失敗,請洽系統管理員');
                }).always(function() {
                    //
                });
            }
            else{
                // Validate Account & Password
                if( self.accountText.length == 0 || self.accountText.length < 10 )
                    $("#accountLabel").append('Account can\'t be empty and at least 10 characters.');
                if( self.passwordText.length == 0 || self.passwordText.length < 10 )
                    $("#passwordLabel").append('Password can\'t be empty and at least 10 characters.');

                return ;
            }

        },
        register: function() {
            // redirect to register page
            window.location.href = this.apiBaseUrl + "/regist" ;
        }
    }
});