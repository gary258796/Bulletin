'use strict';

let Config = {
    data: {
        apiBaseUrl: null
    },
    /**
     * @param value
     * @param opt
     * @return {*}
     */
    init: function(value,opt) {
        let self = this;
        self.data = $.extend(self.data, opt);
        return value === 1 ? self.initVue() : self.initVueWithoutEl();
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
    },
    initVueWithoutEl: function() {
        let self = this;
        return Vue.extend({
            data: function() {
                return $.extend(self.data, {});
            },
            methods: {}
        });
    }
};
