'use strict'

Vue.component(
    'vue-message-card',
    baseVueWithoutEl.extend({
        template: '#vue-message-card-template',
        props: {
            /** 發言id */
            messageId: {
                type: Number,
                required: true
            },
            /** 發言者名稱 */
            messageName: {
                type: String,
                required: true,
            },
            /** 發言者綽號 */
            nickName: {
                type: String,
                required: false,
            },
            /** 發言者地點 */
            location: {
                type: String,
                required: false,
            },
            /** 發言內容 */
            content: {
                type: String,
                required: true,
            },
            /** 發言日期 */
            date: {
                type: String,
                required: true,
            },
            /** 發言時間 */
            time: {
                type: String,
                required: true,
            },
            /** 該發言的回覆訊息物件陣列 */
            commentObj: {
                type: Array,
                required: false
            },
            /** 元件對應collapse以及相關bootstrap功能方面使用 */
            headingId: {
                type: String,
                required: true
            },
            /** 元件對應collapse以及相關bootstrap功能方面使用 */
            collapseId: {
                type: String,
                required: true
            },
            /** 元件對應collapse以及相關bootstrap功能方面使用 */
            buttonId: {
                type: String,
                required: true
            }
        },
        computed: {
          commentCount: function() {
              let self = this;
              if(typeof (self.commentObj) !== 'undefined' && self.commentObj !== null){
                  return self.commentObj.length;
              }
              return 0 ;
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
        methods: {
            /** return true when value is even */
            even: function(value) {
                if( value % 2 === 0 )
                    return true ;

                return false ;
            },
        }
    })
);