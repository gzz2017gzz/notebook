/*用户新增与修改,作者:高振中,日期:2018-04-03 11:40:55*/
<template>
  <div>
    <el-form :model="form" ref="form" :rules="rules" label-width="100px">
      <el-row>
        <el-col>
          <el-form-item label="用户" prop="name">
            <el-input placeholder="请输入用户" v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="新密码" prop="new_password">
            <el-input type="password" placeholder="请输入新密码" v-model="form.new_password"></el-input>
          </el-form-item>
          <el-form-item label="登录名" prop="login_id">
            <el-input placeholder="请输入登录名" v-model="form.login_id"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div slot="footer" style="text-align: right">
      <el-button type="primary" @click="save()" size="small">确定</el-button>
    </div>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        form:  {},
        rules: {
          name: [
            {required: true, message: '请输入用户', trigger: 'blur'},
            {min: 1, max: 20, message: '用户长度不正确', trigger: 'blur'},
          ],
          new_password: [
            {required: true, message: '请输入新密码', trigger: 'blur'},
            {min: 1, max: 20, message: '新密码长度不正确', trigger: 'blur'},
          ],
          login_id: [
            {required: true, message: '请输入角色ID', trigger: 'blur'},
            {min: 1, max: 20, message: '角色ID长度不正确', trigger: 'blur'},
          ],
        }
      }
    },
    created: function () {
      this.getUser();
    },
    methods: {
      save() {//新增及修改记录
        const that = this;
        this.$refs['form'].validate((valid) => {
          if (!valid) {
            return;
          }
          that.$http.post("/api/sysUser/updatePassword", JSON.stringify(that.form)).then(res => {
            that.$message.success("修改密码成功!");
          }).catch(res => {
            that.$message.error("修改密码出错!" + res);
          });
        });
      },
      getUser() {//初始数据
        const that = this;
        that.$http.post("/api/sysUser/getUser").then(res => {
          that.form = res.data;
        }).catch(res => {
          that.$message.error("当前用户信息失败：" + res);
        });
      },
    },
  }
</script>
<style></style>
