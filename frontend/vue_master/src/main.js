import Vue from 'vue'
import App from './App.vue'
import BootstrapVue from 'bootstrap-vue'
import VueRouter from 'vue-router'


import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import { library } from '@fortawesome/fontawesome-svg-core'
import { fas } from '@fortawesome/free-solid-svg-icons'
import { far } from '@fortawesome/free-regular-svg-icons'
import { fab } from '@fortawesome/free-brands-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import StatisticsComp from "@/components/StatisticsComp";
import LoginComp from "@/components/LoginComp";
import AlarmListComp from "@/components/AlarmListComp";
import MesswerteComp from "@/components/MesswerteComp";
import RegisterComp from "@/components/RegisterComp";

Vue.component('font-awesome-icon', FontAwesomeIcon);
library.add(fas, far, fab);



Vue.use(BootstrapVue);
Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    component: LoginComp,
    name: 'login',
  },
  {
    path: '/:user/statistics',
    component: StatisticsComp,
    name: 'statistics',
  },
  {
    path: '/:user/alarmlist',
    component: AlarmListComp,
    name: 'alarmlist',
  },
  {
    path: '/:user/messwerte',
    component: MesswerteComp,
    name: 'messwerte',
  },
  {
    path: '/:user/register',
    component: RegisterComp,
    name: 'register',
  },

];

const router = new VueRouter({
  routes,
  mode: 'history'
});




Vue.config.productionTip = false;

new Vue({
  render: h => h(App),
  router,
}).$mount('#app');
