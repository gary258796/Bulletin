'use strict';

let App = baseVue.extend({
    data: function(){
        return {
            tempObject: [{
                messageId: 12,
                messageName: "Gary Liao",
                nickName: "garySukk",
                location: "Taiwan, Taipei",
                content: "sdafdsafdsafdsafdsafsdafasdf. rta eros.Vestibulum bibendum diam nec urna dignissim sodales.",
                date: "Sat 06 aug 2016",
                time: "22h14",
                headingId: "headingOne",
                collapseId: "collapseOne",
                buttonId: "buttonOne",
                commentObj: [{
                    commentId: 0,
                    commentName: "Ipple",
                    colorNumber: 1,
                    time: "2020 08 11 - 15:12",
                    content: "Hi! I'm gary ."
                }, {
                    commentId: 1,
                    commentName: "Youis",
                    colorNumber: 2,
                    time: "2020 08 11 - 15:12",
                    content: "Hi! I'm Louis ."
                }, {
                    commentId: 2,
                    commentName: "Zenny",
                    colorNumber: 1,
                    time: "2020 08 11 - 15:12",
                    content: "Hi! I'm Jenny ."
                }]
            }, {
                messageId: 22,
                messageName: "Louis Liao",
                nickName: "louissss",
                location: "Taiwan, Taipei",
                content: "sdafdsafdsafdsafdsafsdafasdf. rta eros.Vestibulum bibendum diam nec urna dignissim sodales.",
                date: "Sat 06 aug 2016",
                time: "22h14",
                headingId: "headingTwo",
                collapseId: "collapseTwo",
                buttonId: "buttonTwo",
                // commentObj: [{
                //     commentId: 0,
                //     commentName: "Gary",
                //     colorNumber: 1,
                //     time: "2020 08 11 - 15:12",
                //     content: "Hi! I'm gary ."
                // },{
                //     commentId: 1,
                //     commentName: "Louis",
                //     colorNumber: 2,
                //     time: "2020 08 11 - 15:12",
                //     content: "Hi! I'm Louis ."
                // },{
                //     commentId: 2,
                //     commentName: "Jenny",
                //     colorNumber: 1,
                //     time: "2020 08 11 - 15:12",
                //     content: "Hi! I'm Jenny ."
                // }]
            }]
        }
    },
    created: function() {
    },
    watch: {
    },
    methods: {
    }
});