'use strict';

let App = baseVue.extend({
    data: function(){
        return {
            registerInfo: {
                userNameText: "",
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
            // 清空錯誤提示欄位
            $(".alert").html("").hide();

            let self = this ;
            let registerData = self.registerInfo;

            if( !self.isFieldsFormatOk(registerData) ){ // 在出錯欄位上顯示提示
                if( !isEmail(registerData.emailText) )
                    $("#emailError").show().append("Email format incorrect!<br/>");
                if( !correctUserName(registerData.userNameText) )
                    $("#userNameError").show().append("UserName format incorrect!<br/>");
                if( !correctPassword(registerData.passwordText) )
                    $("#passwordError").show().append("Password format incorrect!<br/>");
            }else {
                $.ajax({
                    url: self.apiBaseUrl + "/regist/regist",
                    type: "post",
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify({
                        userName: registerData.userNameText,
                        email: registerData.emailText,
                        password: registerData.passwordText,
                        matchingPassword: registerData.matchingPasswordText
                    })
                }).then(function(resp) {
                    if( resp.message === 'successful') {
                        // alert : user registered!
                        alert("Regist success!");

                        // let user return to LOGIN Page
                        window.location.href = self.apiBaseUrl + "/login" ;
                    }
                    else if( resp.message === 'fail' ) alert(resp.error);
                }).fail(function(resp) {
                    if(resp.responseJSON.error === "UserAlreadyExist" ){
                        $("#emailError").show().html(resp.responseJSON.message);
                    }else {
                        let errors = $.parseJSON(resp.responseJSON.message);
                        $.each( errors, function( index,item ){
                            if (item.field){
                                $("#"+item.field+"Error").show().append(item.defaultMessage+"<br/>");
                            }
                            else {
                                $("#globalError").show().append(item.defaultMessage+"<br/>");
                            }
                        });
                    }
                });
            }
        },
        /**
         * 驗證各欄位的格式
         * @return Boolean
         */
        isFieldsFormatOk(registerData) {
            return isEmail(registerData.emailText) && correctUserName(registerData.userNameText)
                && correctPassword(registerData.passwordText);
        }
    }
});