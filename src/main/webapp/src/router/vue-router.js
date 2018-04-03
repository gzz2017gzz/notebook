import Vue from 'vue'
import Router from 'vue-router'
import Home from '../components/Home.vue';
import NoteBookList from '../components/sys/notebook/NoteBookList.vue';
import SysUserList from '../components/sys/sysuser/SysUserList.vue';
import UpdatePassword from '../components/sys/sysuser/UpdatePassword.vue';
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: '主页',
      component: Home,
      redirect: "/NoteBookList",
      children: [
        {
          path: "NoteBookList",
          name: "记事本",
          component: NoteBookList
        }, {
          path: "SysUserList",
          name: "用户管理",
          component: SysUserList
        }, {
          path: "UpdatePassword",
          name: "修改密码",
          component: UpdatePassword
        },
      ]
    }
  ]
})
