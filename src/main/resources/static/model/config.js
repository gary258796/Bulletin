'use strict';

let Config = {
    data: {
        apiBaseUrl: null
    },
    init: function(opt) {
        let self = this;
        self.data = $.extend(self.data, opt);
        return self.initVue();
    },
    initVue: function() {
        let self = this;
        return Vue.extend({
            el: '#app',
            data: function() {
                return $.extend(self.data, {
                    temp: "123" // for test only
                });
            },
            methods: {
                // isAnonymous: function() {
                //     return user === null ? true : false;
                // },
                // isAuthenticated: function() {
                //     return user !== null ? true : false;
                // }
            }
        });
    }
};
