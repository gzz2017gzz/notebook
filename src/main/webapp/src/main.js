// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router/vue-router'
import http from './utils/http';
import installFilter from './utils/vue-filters';

Vue.config.productionTip = false

import iView from 'iview';
import 'iview/dist/styles/iview.css';
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

Vue.use(iView);
Vue.use(ElementUI);
installFilter(Vue);
Vue.$http = http;
Vue.prototype.$http = http;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
})
