import Vue from 'vue'
import App from './App.vue'
import messages from '/src/lang'
Vue.config.productionTip = false
import router from './router'
import VueI18n from 'vue-i18n'
// use beforeEach route guard to set the language
Vue.use(VueI18n)

export const i18n = new VueI18n({
  locale: 'en',
  fallbackLocale: 'en',
  messages
})


new Vue({
  i18n,
  router,
  render: h => h(App),
}).$mount('#app')
