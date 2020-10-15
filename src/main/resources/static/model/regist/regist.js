'use strict';

var App = baseVue.extend({
    data: function(){
        return {
            registerInfo: {
                accountText: "",
                passwordText: "",
                emailText: ""
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
            // 1. 檢查各欄位之正確性
            if( self.isFieldsFormatOk(registerData) ) {
                // 2. 通過之後 call Api ( /regist POST )
                $.ajax({
                    url: self.apiBaseUrl + "/regist",
                    type: "POST",
                    dataType: "json",
                    contentType: "application/json;charset=utf-8",
                    data: JSON.stringify({
                        account: registerData.accountText,
                        email: registerData.emailText,
                        password: registerData.passwordText
                    })
                }).then(function(resp) {
                    // 取得resp查看resp訊息
                    if( resp.returnStatus == 'successful') {
                        // 提示 註冊成功
                        alert("Regist success!");

                        // 讓使用者返回LOGIN頁面
                        window.location.href = self.apiBaseUrl + "/login" ;
                    }
                    else
                        $("#alertLabel").append(resp.returnMsg);
                }).fail(function() {
                    alert('註冊失敗,請洽系統管理員');
                }).always(function() {
                    //
                });
            } else {
                // 在出錯欄位上顯示提示
                if( !isEmail(registerData.emailText) )
                    $("#emailLabel").append('Email format uncorrect!');
                if( !correctAccount(registerData.accountText) )
                    $("#accountLabel").append('Account format uncorrect!');
                if( !correctPassword(registerData.passwordText) )
                    $("#passwordLabel").append('Password format uncorrect!');

                return;
            }
        },
        /**
         * 驗證各欄位的格式
         * @return Boolean
         */
        isFieldsFormatOk(registerData) {
            if( isEmail(registerData.emailText) && correctAccount(registerData.accountText)
                && correctPassword(registerData.passwordText) )
                return true ;

            return false ;
        }
    }
});