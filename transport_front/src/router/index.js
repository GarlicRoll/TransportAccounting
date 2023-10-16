import Vue from 'vue'
import VueRouter from 'vue-router'

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
        },
        {
            path: '/buses',
            name: 'buses',
            //meta: {layout: 'empty'},
            component: () => import('../components/BusesView.vue')
        },
        {
            path: '/violations',
            name: 'violations',
            //meta: {layout: 'empty'},
            component: () => import('../components/ViolationsView.vue')
        },
        {
            path: '/schedule',
            name: 'schedule',
            //meta: {layout: 'empty'},
            component: () => import('../components/ScheduleView.vue')
        }
    ]
})

export default router