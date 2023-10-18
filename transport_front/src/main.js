import Vue from 'vue'
import App from './App.vue'
import http from "@/config/http";
import router from './router'
import VueCookies from 'vue-cookies'
import VueI18n from "vue-i18n";
import lang from "@/lang";


Vue.use(VueI18n)
Vue.use(VueCookies)

Vue.use(http, {
  baseUrl: "https://localhost:8080/api/v1"
})
Vue.prototype.$baseUrl = "https://localhost:8080/api/v1"
export const url = Vue.prototype.$baseUrl

export const i18n = new VueI18n({
  locale: localStorage.getItem('language') || 'en',
  fallbackLocale: 'en',
  messages: lang
})

new Vue({
  i18n,
  router,
  render: h => h(App),
}).$mount('#app')
