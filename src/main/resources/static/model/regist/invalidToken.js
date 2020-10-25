'use strict';

let App = baseVue.extend({
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
        resendRegistrationEmail: function() {

            let self = this;
            $.get(self.apiBaseUrl + "/regist/resendRegistrationEmail?token="+ token, function(data){

                if( confirm(data.message) )
                    // 回到Login page並帶上message
                    window.location.href = self.apiBaseUrl +"/login";

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