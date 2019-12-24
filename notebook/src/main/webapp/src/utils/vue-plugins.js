/**
 * Created by tangzhichao on 2017/2/21.
 */
import http from './http'

export const HttpPlugin = {
  install: function (Vue, options) {
    Vue.$http = http;
    Vue.prototype.$http = http;
  }
}

