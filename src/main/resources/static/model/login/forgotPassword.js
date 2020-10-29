'use strict';

let App = baseVue.extend({
    data: function(){
        return {
            emailText: "",
            emailError: ""
        };
    },
    created: function() {
    },
    watch: {
        emailText: {
            handler: function(newVal) {
                let self = this;
                if( !isEmail(newVal) )
                    self.emailError = 'Input email format is incorrect!';
                else
                    self.emailError = '';
            }
        },
    },
    methods: {
        /**
         *  發送信件給使用者
         */
        sendResetMail: function() {
            let self = this;
            if( isEmail(self.emailText) ){
                // ajax post
                $.post(self.apiBaseUrl + "/forgetPassword",{email: self.emailText} ,function(resp){
                    if( resp.message == 'successful' ){
                        alert("Reset password mail send! Please click the link inside the mail nand reset your password.");

                        // let user return to LOGIN Page
                        window.location.href = self.apiBaseUrl + "/login" ;
                    }
                    else if ( resp.message == 'fail' )
                        alert(resp.error);
                }).fail(function(resp) {
                    // TODO: This fail part not tested yet
                    alert(resp.responseJSON.message);
                    console.log(resp.responseJSON.message);
                });
            }
            else
                alert("Email isn't valid. Please check your email.");

        }
    }
});