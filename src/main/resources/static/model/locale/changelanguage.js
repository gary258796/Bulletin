'use strict';

Vue.component(
    'vue-header',
    baseVueWithoutEl.extend({
        props: {
        },
        data: function() {
            return {
                locale: ''
            };
        },
        computed: {},
        created: function() {
        },
        watch: {
            locale: {
                // 之後應該還會需要調整 目前先這樣
                handler: function(newVal) {

                    let currentUrl = window.location.href ;

                    if( currentUrl.includes("?lang=") ) {

                        let replaceEndIndex = currentUrl.indexOf("?lang="); // 取道?lang開始之前

                        window.location.href =  currentUrl.substring(0, replaceEndIndex) + "?lang=" + newVal;
                    }
                    else
                        window.location.href = window.location.href + "?lang=" + newVal;
                }
            }
        },
        methods: {}
    })
);

