'use strict'

Vue.component(
    'vue-text-area',
    baseVueWithoutEl.extend({
        template: '#vue-text-area-template',
        mounted: function (){
            const self = this;
            let autoExpandElement = document.getElementsByClassName("autoExpand");
            for(let i = 0 ; i < autoExpandElement.length; i++){
                autoExpandElement[i].addEventListener('input', self.onExpandableTextareaInput);
            }
        },
        methods: {
            onExpandableTextareaInput : function ({ target:elm }){
                const self = this ;
                // make sure the input event originated from a textarea and it's desired to be auto-expandable
                if( !elm.classList.contains('autoExpand') || !elm.nodeName == 'TEXTAREA' ) return

                var minRows = elm.getAttribute('data-min-rows')|0, rows;
                !elm._baseScrollHeight && self.getScrollHeight(elm)

                elm.rows = minRows
                rows = Math.ceil((elm.scrollHeight - elm._baseScrollHeight) / 16)
                elm.rows = minRows + rows
            },
            getScrollHeight : function (elm){
                var savedValue = elm.value
                elm.value = ''
                elm._baseScrollHeight = elm.scrollHeight
                elm.value = savedValue
            }
        }
    })
);