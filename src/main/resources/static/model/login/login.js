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

            var accountTxt = '';
            var passwordTxt = '';

            // Validate Account & Password
            if( self.accountText.length == 0 || self.accountText.length < 10 )
                accountTxt = 'Account can\'t be empty and at least 10 characters.' ;
            if( self.passwordText.length == 0 || self.passwordText.length < 10 )
                passwordTxt = 'Password can\'t be empty and at least 10 characters.' ;

            // call Login Api after validation is all correct
            if( accountTxt == '' && passwordTxt == '') {
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
                        console.log("returnData : " + returnData);
                    },
                    error: function(xhr, ajaxOptions, thrownError){
                        console.log(xhr.status);
                        console.log(thrownError);
                    }
                });
            }
            else{
                $("#accountLabel").innerHTML = accountTxt ;
                $("#passwordLabel").innerHTML = passwordTxt ;
                return ;
            }

        }
    }
});