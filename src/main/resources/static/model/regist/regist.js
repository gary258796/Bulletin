'use strict';

var App = baseVue.extend({
    data: function(){
        return {
            registerInfo: {
                accountText: "",
                passwordText: "",
                emailText: "",
                matchingPasswordText: ""
            },
        };
    },
    created: function() {
    },
    methods: {
        /**
         *  註冊流程
         */
        register: function() {

            var self = this ;
            let registerData = self.registerInfo;

            if( !isFieldsFormatOk(registerData) ){ // 在出錯欄位上顯示提示
                if( !isEmail(registerData.emailText) )
                    $("#emailError").show().append("Email format uncorrect!<br/>");
                if( !correctAccount(registerData.accountText) )
                    $("#accountError").show().append("Account format uncorrect!<br/>");
                if( !correctPassword(registerData.passwordText) )
                    $("#passwordError").show().append("Password format uncorrect!<br/>");

                return;
            }else {
                $.ajax({
                    url: self.apiBaseUrl + "/regist",
                    type: "post",
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify({
                        account: registerData.accountText,
                        email: registerData.emailText,
                        password: registerData.passwordText,
                        matchingPassword: registerData.matchingPasswordText
                    })
                }).then(function(resp) {
                    if( resp.returnStatus == 'successful') {
                        // alert : user registered!
                        alert("Regist success!");

                        // let user return to LOGIN Page
                        window.location.href = self.apiBaseUrl + "/login" ;
                    }
                    else if( resp.returnStatus == 'fail' )
                        alert(resp.returnMsg);
                }).fail(function(resp) {

                    // TODO: resp 會缺少 responseJSON 不知道為什麼
                    // var errors = $.parseJSON(resp.responseJSON.message);
                    // $.each( errors, function( index,item ){
                    //     if (item.field){
                    //         $("#"+item.field+"Error").show().append(item.defaultMessage+"<br/>");
                    //     }
                    //     else {
                    //         $("#globalError").show().append(item.defaultMessage+"<br/>");
                    //     }
                    // });
                });
            }
        },
        /**
         * 驗證各欄位的格式
         * @return Boolean
         */
        isFieldsFormatOk(registerData) {
            return true ;

            if( isEmail(registerData.emailText) && correctAccount(registerData.accountText)
                && correctPassword(registerData.passwordText) )
                return true ;

            return false ;
        }
    }
});