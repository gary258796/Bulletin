'use strict'

Vue.component(
    'vue-logout-button',
    baseVueWithoutEl.extend({
        template: '<button @click="logout()" class="btn btn-primary" style="display: inline;"><span th:text="#{message.logout}">Logout</span></button>',
        methods: {
            /**
             * do logout
             */
            logout: function() {
                window.location.href = this.apiBaseUrl + "/do_logout" ;
            }
        }
    })
);