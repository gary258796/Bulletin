'use strict';

var Config = {
    data: {
        apiBaseUrl: null
    },
    init: function(opt) {
        var self = this;
        self.data = $.extend(self.data, opt);

        // Vue.prototype.numeral = numeral; // 可以直接在vue括號裡使用numeral.min.js
        return self.initVue();
    },
    initVue: function() {
        var self = this;
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
