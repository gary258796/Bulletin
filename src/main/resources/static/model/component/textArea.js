'use strict'

Vue.component(
    'vue-text-area',
    baseVueWithoutEl.extend({
        template: '#vue-text-area-template',
        props: {
           /** indicate if this text-area will use scroll bar on Y */
           usedWithScrollBarOnY: {
               type: Boolean,
               required: false
           }
        },
        data: function () {
            return {
                textAreaData: ""
            }
        },
        mounted: function (){
            const self = this ;
            // set auto expand only if component is told
            if( self.usedWithScrollBarOnY !== null && !self.usedWithScrollBarOnY ) { // Add auto expand calculated
                let currentElement = self.$el;
                currentElement.addEventListener('input', self.onExpandableTextareaInput);
            }
        },
        methods: {
            /** send emit to father component to trace data of text area*/
            changeTextData: function(value) {
                this.$emit('text-area-changed', value);
            },
            /** Below are two function for auto expandable */
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