'use strict';

var App = baseVue.extend({
    data: function(){
        return {
            usernameText: "",
            passwordText: "",
            errorMessages: []
        };
    },
    created: function() {
    },
    methods: {
        checkForm: function(e){
            var self = this;
            self.errorMessages = []; // clear

            if( self.usernameText.length >= 5 && self.passwordText.length >= 5) {
                return true;
            } // do submit form

            if( self.usernameText.length < 5 )
                self.errorMessages.push('UserName can\'t be empty and at least 5 characters. \n');
            if( self.passwordText.length < 5 )
                self.errorMessages.push('Password can\'t be empty and at least 5 characters. \n');

            e.preventDefault();
        },
        register: function() {
            // redirect to register page
            window.location.href = this.apiBaseUrl + "/regist" ;
        }
    }
});