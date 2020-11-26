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
                type: Date,
                required: true,
            },
            time: {
                type: String,
                required: true,
            }
        },
        methods: {
            clickMessage: function() {

            }
        }
    })
);