/*记事本新增与修改,作者:高振中,日期:2018-04-03 11:40:55*/
<template>
  <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px">
      <el-row>
        <el-col>
          <el-form-item label="标题" prop="title">
            <el-input placeholder="请输入标题" v-model="form.title"></el-input>
          </el-form-item>
          <el-form-item label="内容" prop="content" >
            <el-input type="textarea" placeholder="请输入内容" v-model="form.content" :rows="5"></el-input>
          </el-form-item>
          <el-form-item label="状态">
            <el-select v-model="form.status" placeholder="请选择状态" size="small" style="width: 120px">
              <el-option v-for="item in statuses" :key="item.value" :label="item.label" :value="item.value"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="优先级">
            <el-select v-model="form.level" placeholder="请选择优先级" size="small" style="width: 120px">
              <el-option v-for="item in levels" :key="item.value" :label="item.label" :value="item.value"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="问题类型">
            <el-select v-model="form.type" placeholder="请选择问题类型" size="small" style="width: 120px">
              <el-option v-for="item in types" :key="item.value" :label="item.label" :value="item.value"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div slot="footer" style="text-align: right">
      <el-button @click="show = false"    >取消</el-button>
      <el-button type="primary" @click="save()"   >确定</el-button>
    </div>
  </el-dialog>
</template>
<script>
  export default {
    props: ["refresh"],
    data() {
      return {
        statuses: [{value: "已解决", label: "已解决"}, {value: "未解决", label: "未解决"}],
        levels: [{value: "高", label: "高"}, {value: "中", label: "中"}, {value: "低", label: "低"}],
        types: [{value: "需求问题", label: "需求问题"}, {value: "设计问题", label: "设计问题"}, {value: "开发问题", label: "开发问题"}],
        title: '',
        form: this.initForm(),
        dialogMode: "save",
        show: false,
        rules: {
          title: [
            {required: true, message: '请输入标题', trigger: 'blur'},
            {min: 2, max: 100, message: '标题长度不正确', trigger: 'blur'},
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
          that.$http.post("/api/noteBook/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
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
          title: null,//标题
          content: null,//内容
          release_date: null,//发布日期
          author: null,//作者
          status:"未解决" ,//状态
          level: "低",//优先级
          type: "需求问题",//问题类型
        }
      },
      addDialog() {//新增
        this.title = "新增记事本内容";
        this.dialogMode = "save";
        this.form = this.initForm();
        this.show = true;
      },
      editDialog(row) {//修改
        this.title = "修改记事本内容";
        this.dialogMode = "update";
        this.form = {...row};
        this.show = true;
      },
    },
    components: {}
  }
</script>
<style></style>
