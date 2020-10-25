'use strict';

let App = baseVue.extend({
    data: function(){
        return {
            usernameText: "",
            usernameAlert: "",
            passwordText: "",
            passwordAlert: ""
        };
    },
    created: function() {
    },
    watch: {
        usernameText: {
            handler: function(newVal) {
                let self = this;
                if( newVal.length < 5 )
                    self.usernameAlert = 'UserName can\'t be empty and at least 5 characters. \n';
                else
                    self.usernameAlert = '';
            }
        },
        passwordText: {
            handler: function(newVal, oldVal) {
                let self = this;
                if( newVal.length < 5 )
                    self.passwordAlert = 'Password can\'t be empty and at least 5 characters. \n';
                else
                    self.passwordAlert = '';
            }
        }
    },
    methods: {
        /**
         * 檢查表單內容格式有無符合
         * @param e
         * @return {boolean} , true的話 form就會submit出去
         */
        checkForm: function(e){
            let self = this;

            if( self.usernameText.length >= 5 && self.passwordText.length >= 5) {
                return true;
            } // do submit form

            e.preventDefault();
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