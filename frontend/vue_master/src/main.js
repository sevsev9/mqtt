import Vue from 'vue'
import App from './App.vue'
import BootstrapVue from 'bootstrap-vue'
import VueRouter from 'vue-router'


import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import LoginTest from "@/components/LoginTest";
import StatisticsTest from "@/components/StatisticsTest";
import AlarmListTest from "@/components/AlarmListTest";
import MesswerteTest from "@/components/MesswerteTest";

Vue.use(BootstrapVue);
Vue.use(VueRouter);

const routes = [
  {path: '/', component: LoginTest  },
  {path: '/user/statistics', component: StatisticsTest},
  {path: '/user/alarmlist', component: AlarmListTest},
  {path: '/user/messwerte', component: MesswerteTest},

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
