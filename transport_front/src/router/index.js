import Vue from 'vue'
import VueRouter from 'vue-router'
import {i18n} from "@/main";


Vue.use(VueRouter)

const router = new VueRouter({
    mode: 'history',
    routes: [
        {
            path: '/auth',
            name: 'auth',
            //meta: {layout: 'empty'},
            component: () => import('../components/AuthenticateView.vue')
        },
        {
            path: '/register',
            name: 'register',
            //meta: {layout: 'empty'},
            component: () => import('../components/RegistrationView.vue')
        },
        {
            path: '/drivers',
            name: 'drivers',
            //meta: {layout: 'empty'},
            component: () => import('../components/DriversView.vue')
        }
    ]
})

router.beforeEach((to, from, next) => {
    i18n.locale = localStorage.getItem('language') || 'en'
    return next()
})

export default router