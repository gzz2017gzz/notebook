<template>
  <div class="layout">
    <Layout :style="{minHeight: '100vh'}">
      <Sider collapsible :collapsed-width="78" v-model="isCollapsed">
        <Menu :active-name="activeName" theme="dark" width="auto" :class="menuitemClasses">
          <router-link to="/NoteBookList">
            <MenuItem name="/NoteBookList">
              <Icon type="ios-navigate"></Icon>
              <span>记事本</span>
            </MenuItem>
          </router-link>
          <div v-if="user.login_id=='admin'">
            <router-link to="/SysUserList">
              <MenuItem name="/SysUserList">
                <Icon type="search"></Icon>
                <span>用户管理</span>
              </MenuItem>
            </router-link>
          </div>
          <router-link to="/UpdatePassword">
            <MenuItem name="/UpdatePassword">
              <Icon type="iphone"></Icon>
              <span>修改密码</span>
            </MenuItem>
          </router-link>
        </Menu>
      </Sider>
      <Layout>
        <Header :style="{background: '#fff', boxShadow: '0 2px 3px 2px rgba(0,0,0,.1)'}">
          <h3>[动岚未来科技]记事本;你好[{{user.name}}]<a href="j_spring_security_logout">退出</a></h3>
        </Header>
        <Content :style="{padding: '0 16px 16px'}">
          <Breadcrumb separator=">" :style="{margin: '16px 0'}">
            <BreadcrumbItem :key="p.path" v-for="p in sitePath" :to="{ path: p.path === '' ? '/' : p.path  }">{{p.name}}
            </BreadcrumbItem>
          </Breadcrumb>
          <Card>
            <div>
              <Home></Home>
            </div>
          </Card>
        </Content>
        <footer align="center">版权所有:gzz_gzz@163.com</footer>
      </Layout>
    </Layout>
    <BackTop :height="200"></BackTop>
  </div>
</template>
<script>
  import Home from "./components/Home.vue";

  export default {
    created: function () {
      let paths = this.$route.matched;
      this.activeIndex = paths[paths.length - 1];
      this.sitePath = paths;
      this.activeName = this.activeIndex.path;

      const that = this;
      that.$http.post("/api/sysUser/getUser").then(res => {
        that.user = res.data;
      }).catch(res => {
        that.$message.error("当前用户信息失败：" + res);
      });
    },
    data() {
      return {
        user: {},
        isCollapsed: false,
        sitePath: [],
        activeIndex: null,
        activeName: "/NoteBookList"
      };
    },
    computed: {
      menuitemClasses: function () {
        return [
          'menu-item',
          this.isCollapsed ? 'collapsed-menu' : ''
        ]
      }
    },
    components: {
      Home
    },
    watch: {
      '$route': function (to, from) {
        let paths = to.matched;
        this.activeIndex = paths[paths.length - 1];
        this.sitePath = paths;
        this.activeName = this.activeIndex.path;
      }
    },
  }
</script>
