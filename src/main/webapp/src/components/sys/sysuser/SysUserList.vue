/*用户列表,作者:高振中,日期:2018-04-03 11:40:55*/
<template>
  <div>
    <el-form :inline="true">
      <el-form-item label="用户名">
        <el-input placeholder="请输入用户名" size="small" v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="登录名">
        <el-input placeholder="请输入登录名" size="small" v-model="form.login_id"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button icon="search" @click="refresh" title="根据输入的条件查询" size="small">查询</el-button>
        <el-button type="primary" icon="plus" @click="doAdd()" title="添加" size="small">添加</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="dataList" v-loading="loading" element-loading-text="正在加载......" border size="medium">
      <el-table-column prop="name" label="用户"></el-table-column>
      <el-table-column prop="password" label="密码"></el-table-column>
      <el-table-column prop="login_id" label="登录名"></el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="props">
          <div>
            <el-button type="text" @click="doEdit(props.row)" size="small">编辑</el-button>
            <el-button type="text" @click="doDelete(props.row)" :disabled="props.row.login_id=='admin'" size="small">删除</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <br/>
    <div style="text-align: right" v-if="total > 0">
      <el-pagination small layout="sizes,prev, pager, next" :current-page="page" :total="total" @current-change="(curr) => {this.page = curr ; this.refresh();}"
                     :page-sizes="[10, 15, 20, 100]" @size-change="(s) => {this.size = s ; this.refresh();}" :page-size="size"></el-pagination>
    </div>
    <SysUserDialog ref="dialog" :refresh="refresh"></SysUserDialog>
  </div>
</template>
<script>
  import SysUserDialog from './SysUserDialog.vue';

  export default {
    data: function () {
      return {
        total: 0,
        page: 1,
        size: 10,
        dataList: [],
        form: {
          id: null,//主键
          name: null,//用户
          password: null,//密码
          login_id: null,//角色ID
        },
        loading: false
      }
    },
    computed: {},
    created: function () {
      this.refresh();
    },
    methods: {
      refresh() {
        const that = this;
        that.loading = true;
        const requestData = {...that.form, page: that.page - 1, size: that.size};
        that.$http.post("/api/sysUser/queryPage", JSON.stringify(requestData)).then(res => {
          that.loading = false;
          that.dataList = res.data.content;
          that.total = res.data.totalElements;
        }).catch(res => {
          that.$message.error("获取用户列表失败：" + res);
          that.loading = false;
        });
      },
      doAdd() {
        this.$refs["dialog"].addDialog();
      },
      doEdit(row) {
        this.$refs["dialog"].editDialog(row);
      },
      doDelete(row) {
        const that = this;
        this.$confirm('你确定要删除吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          that.$http.delete("/api/sysUser/delete", {
            params: {ids: [row.id]}
          }).then(res => {
            this.$message.success("删除成功");
            that.refresh();
          }).catch(res => {
            that.$message.error("删除失败：" + res);
          });
        }).catch(() => {
        });
      }
    },
    components: {SysUserDialog}
  }
</script>
<style></style>
