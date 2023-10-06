import Vue from 'vue'
import App from './App.vue'
import messages from '/src/lang'
Vue.config.productionTip = false
import http from "@/config/http";
import router from './router'
import VueI18n from 'vue-i18n'
// use beforeEach route guard to set the language
Vue.use(VueI18n)

export const i18n = new VueI18n({
  locale: 'en',
  fallbackLocale: 'en',
  messages
})

Vue.use(http, {
  baseUrl: "http://localhost:8080/api/v1"
})
Vue.prototype.$baseUrl = "http://localhost:8080/api/v1"
export const url = Vue.prototype.$baseUrl

new Vue({
  i18n,
  router,
  render: h => h(App),
}).$mount('#app')
