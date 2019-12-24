/*用户新增与修改,作者:高振中,日期:2018-04-03 11:40:55*/
<template>
  <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px">
      <el-row>
        <el-col>
          <el-form-item label="用户" prop="name">
            <el-input placeholder="请输入用户" v-model="form.name"/>
          </el-form-item>
            <el-form-item label="密码" prop="new_password">
              <el-input type="password" placeholder="请输入密码" v-model="form.new_password"/>
            </el-form-item>
          <el-form-item label="登录名" prop="login_id">
            <el-input placeholder="请输入登录名" v-model="form.login_id"/>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div slot="footer" style="text-align: right">
      <el-button @click="show = false" size="small">取消</el-button>
      <el-button type="primary" @click="save()" size="small">确定</el-button>
    </div>
  </el-dialog>
</template>
<script>
  export default {
    props: ["refresh"],
    data() {
      return {
        title: '',
        form: this.initForm(),
        dialogMode: "save",
        show: false,
        rules: {
          name: [
            {required: true, message: '请输入用户', trigger: 'blur'},
            {min: 1, max: 10, message: '用户长度不正确', trigger: 'blur'},
          ],
          new_password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {min: 1, max: 10, message: '密码长度不正确', trigger: 'blur'},
          ],
          login_id: [
            {required: true, message: '请输入角色ID', trigger: 'blur'},
            {min: 1, max: 10, message: '角色ID长度不正确', trigger: 'blur'},
          ],
        }
      }
    },

    methods: {
      save() {//新增及修改记录
        const that = this;
        this.$refs['form'].validate((valid) => {
          if (!valid) {
            return;
          }
          that.$http.post("/api/sysUser/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
            that.show = false;
            that.$message.success(that.title + "成功!");
            that.refresh();
          }).catch(res => {
            that.$message.error(that.title + "出错!" + res);
          });
        });
      },
      initForm() {//初始数据
        return {
          id: null,//主键
          name: null,//用户
          password: null,//密码
          login_id: null,//角色ID
          new_password:null,
        }
      },
      addDialog() {//新增
        this.title = "新增用户";
        this.dialogMode = "save";
        this.form = this.initForm();
        this.show = true;
      },
      editDialog(row) {//修改
        this.title = "修改用户";
        this.dialogMode = "update";
        this.form = {...row};
        this.show = true;
      },
    },
    components: {}
  }
</script>
<style></style>
