import axios from 'axios';
import {Modal,Message} from 'iview'
axios.defaults.headers.post['Content-Type'] = 'application/json';
let instance = axios.create({
  validateStatus: function (status) {
    return status == 200 || status == 400;
  },
});
// Add a response interceptor
instance.interceptors.response.use(function (response) {
  if (response.status == 400) {
    Message.error(response.data.error);
    response.data = null;
  }
  return response;
}, function (error) {
  if (error.message === 'Network Error') {
    Modal.info( {
      title:'提示',
      content:'回话过期，请重新登录',
      closable:true,
      onOk:()=>{
        window.location.href = '/';
      }
    });
  }
  // Do something with response error
  return Promise.reject(error);
});

export default instance;
