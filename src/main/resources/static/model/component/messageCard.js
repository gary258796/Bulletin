'use strict'

Vue.component(
    'vue-message-card',
    baseVueWithoutEl.extend({
        template: '#vue-message-card-template',
        props: {
            name: {
                type: String,
                required: true,
            },
            nickName: {
                type: String,
                required: false,
            },
            location: {
                type: String,
                required: false,
            },
            content: {
                type: String,
                required: true,
            },
            date: {
                type: String,
                required: true,
            },
            time: {
                type: String,
                required: true,
            },
            commentObj: {
                type: Array
            },
            headingId: {
                type: String
            },
            collapseId: {
                type: String
            },
            buttonId: {
                type: String
            }
        },
        mounted: function() {
            /**
             * 每個留言卡加上各自的顯示delay
             * @type {number}
             */
            const s = 0.1 ;
            $(".contact nav.show a").each(function(index){
                $(this).css({
                    'transition-delay' : s * (1+index) + 's'
                });
            });
        },
        methods: {}
    })
);