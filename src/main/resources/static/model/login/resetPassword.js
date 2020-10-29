'use strict';

let App = baseVue.extend({
    data: function(){
        return {
            passwordText: "",
            passwordAlert: "",
            matchingPasswordText: "",
            matchingPasswordAlert: "",
            token: ""
        };
    },
    created: function() {
        var self = this;
        self.token = token;
    },
    watch: {
        passwordText: {
            handler: function(newVal) {
                let self = this;
                if( !correctPassword(newVal) )
                    self.passwordAlert = 'Password need at least 10 characters.\n';
                else
                    self.passwordAlert = '';
            }
        },
        matchingPasswordText: {
            handler: function(newVal) {
                let self = this;
                if( newVal !== self.passwordText )
                    self.matchingPasswordAlert = 'Password not match! Please check again. \n';
                else
                    self.matchingPasswordAlert = '';
            }
        }
    },
    methods: {
        resetPassword: function() {
            let self = this;

            // call post ajax "/resetPassword"
            $.ajax({
                url: self.apiBaseUrl + "/resetPassword",
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify({
                    password: self.passwordText,
                    matchingPassword: self.matchingPasswordText,
                    token: self.token
                })
            }).then(function(resp){
                if( resp.message == 'successful' ){
                    alert(resp.error);

                    // let user return to LOGIN Page
                    window.location.href = self.apiBaseUrl + "/login" ;
                }
                else if ( resp.message == 'fail' )
                    alert(resp.error);
            }).fail(function (resp){
                if(resp.responseJSON.error.indexOf("InternalError") > -1){
                    alert( resp.responseJSON.message );
                    window.location.href = self.apiBaseUrl + "/login" ;
                }
                else{
                    var errors = $.parseJSON(resp.responseJSON.message);
                    $.each( errors, function( index,item ){
                        console.log(item.defaultMessage);
                    });

                    errors = $.parseJSON(resp.responseJSON.error);
                    $.each( errors, function( index,item ){
                        console.log(item.defaultMessage);
                    });

                    // var errors = $.parseJSON(resp.responseJSON.message);
                    // $.each( errors, function( index,item ){
                    //     $("#globalError").show().html(item.defaultMessage);
                    // });
                    // errors = $.parseJSON(resp.responseJSON.error);
                    // $.each( errors, function( index,item ){
                    //     $("#globalError").show().append(item.defaultMessage+"<br/>");
                    // });
                }
            });
        }
    }
});