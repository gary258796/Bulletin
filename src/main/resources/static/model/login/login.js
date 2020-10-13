var app = new Vue({
    el: '#app',
    data: {
        apiBaseUrl: "http://localhost:8080",
        accountText: "",
        passwordText: ""
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
                    }),
                    success: function(returnData){
                        if( returnData == 'successful') window.location.href = self.apiBaseUrl + "/Home/home" ;
                        else
                            $("#alertLabel").append('Login Failed! Check your account and password!');
                    }
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

        }
    }
});