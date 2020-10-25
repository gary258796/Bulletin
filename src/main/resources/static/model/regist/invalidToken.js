'use strict';

var App = baseVue.extend({
    data: function(){
        return {
        };
    },
    created: function() {
    },
    methods: {
        /**
         * 請求重新發送認證信
         */
        resendRegistrationEmail: function () {

            var self = this;
            var token = [[${token}]]; // get token in model by using thymeleaf technique

            $.get(self.apiBaseUrl + "/regist/resendRegistrationEmail?token="+token, function(data){

                // 回到Login page並帶上message
                window.location.href = self.apiBaseUrl +"login?message=" + data.message;

            }).fail(function(data) {
                    if(data.responseJSON.error.indexOf("MailError") > -1)
                    {
                        alert(data.responseJSON.error);
                    }
                    else{
                        alert(data.responseJSON.error);
                    }
            });
        },
        /**
         * 進到註冊頁面
         */
        register: function() {
            // redirect to register page
            window.location.href = this.apiBaseUrl + "/regist/regist" ;
        }
    }
});