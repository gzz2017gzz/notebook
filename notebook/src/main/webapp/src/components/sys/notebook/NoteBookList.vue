/*记事本列表,作者:高振中,日期:2018-04-03 11:40:55*/
<template>
  <div>
    <el-form :inline="true">
      <el-form-item label="状态">
        <el-select v-model="form.status" placeholder="请选择状态" size="small" style="width: 120px">
          <el-option v-for="item in statuses" :key="item.value" :label="item.label" :value="item.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="优先级">
        <el-select v-model="form.level" placeholder="请选择优先级" size="small" style="width: 120px">
          <el-option v-for="item in levels" :key="item.value" :label="item.label" :value="item.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="问题类型">
        <el-select v-model="form.type" placeholder="请选择问题类型" size="small" style="width: 120px">
          <el-option v-for="item in types" :key="item.value" :label="item.label" :value="item.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="日期开始">
        <el-date-picker v-model="form.release_date_start" type="date" placeholder="日期开始" size="small"
                        style="width: 120px"/>
      </el-form-item>
      <el-form-item label="日期结束">
        <el-date-picker v-model="form.release_date_end" type="date" placeholder="日期结束" size="small"
                        style="width: 120px"/>
      </el-form-item>
      <el-form-item label="作者">
        <el-input placeholder="请输入作者" size="small" v-model="form.author" style="width: 120px"/>
      </el-form-item>
      <el-form-item>
        <el-button icon="search" @click="refresh" title="根据输入的条件查询" size="small">查询</el-button>
        <el-button type="primary" icon="plus" @click="doAdd()" title="添加" size="small">添加</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="dataList" v-loading="loading" element-loading-text="正在加载......" border size="medium">
      <el-table-column prop="id" label="编号" width="50"></el-table-column>
      <el-table-column prop="title" label="标题" width="200"></el-table-column>
      <el-table-column prop="content" label="内容"></el-table-column>
      <el-table-column prop="release_date" label="发布日期" width="180">
        <template slot-scope="props"> {{props.row.release_date | DateFormat}}</template>
      </el-table-column>
      <el-table-column prop="author" label="作者" width="80"></el-table-column>
      <el-table-column prop="status" label="状态" width="80">
        <template slot-scope="props"><span v-bind:class="statusClass(props.row)">{{props.row.status}} </span></template>
      </el-table-column>
      <el-table-column prop="level" label="优先级" width="80"></el-table-column>
      <el-table-column prop="type" label="问题类型" width="120"></el-table-column>
      <el-table-column label="操作" width="120">
        <template slot-scope="props">
          <div>
            <el-button type="text" @click="doEdit(props.row)">编辑</el-button>
            <el-button type="text" @click="doDelete(props.row)" :disabled="user.login_id!='admin'">删除</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <br/>
    <div style="text-align: right" v-if="total > 0">
      <el-pagination small layout="sizes,prev, pager, next" :current-page="page" :total="total"
                     @current-change="(curr) => {this.page = curr ; this.refresh();}"
                     :page-sizes="[10, 15, 20, 100]" @size-change="(s) => {this.size = s ; this.refresh();}"
                     :page-size="size"></el-pagination>
    </div>
    <NoteBookDialog ref="dialog" :refresh="refresh"></NoteBookDialog>
  </div>
</template>
<script>
  import NoteBookDialog from './NoteBookDialog.vue';

  export default {
    data: function () {
      return {
        statuses: [{value: "", label: "全部"}, {value: "已解决", label: "已解决"}, {value: "未解决", label: "未解决"}, {
          value: "延期解决",
          label: "延期解决"
        }],
        levels: [{value: "", label: "全部"}, {value: "高", label: "高"}, {value: "中", label: "中"}, {
          value: "低",
          label: "低"
        }],
        types: [{value: "", label: "全部"}, {value: "需求问题", label: "需求问题"}, {
          value: "设计问题",
          label: "设计问题"
        }, {value: "开发问题", label: "开发问题"}],
        total: 0,
        page: 1,
        size: 10,
        dataList: [],
        active: true,
        form: {
          title: null,//标题
          content: null,//内容
          release_date_start: null,//发布日期
          release_date_end: null,//发布日期
          author: null,//作者
          status: "",//状态
          level: "",//优先级
          type: "",//问题类型
        },
        loading: false,
        user: {},
      }
    },
    computed: {},
    created: function () {
      this.refresh();
      const that = this;
      that.$http.post("/api/sysUser/getUser").then(res => {
        that.user = res.data;
      }).catch(res => {
        that.$message.error("当前用户信息失败：" + res);
      });
    },
    methods: {
      statusClass: function (row) {
        if (row.status == "未解决") return "text-red";
        if (row.status == "延期解决") return "text-danger";
        if (row.status == "已解决") return "text-green";
      },
      refresh() {
        const that = this;
        that.loading = true;
        const requestData = {...that.form, page: that.page - 1, size: that.size};
        that.$http.post("/api/noteBook/queryPage", JSON.stringify(requestData)).then(res => {
          that.loading = false;
          that.dataList = res.data.content;
          that.total = res.data.totalElements;
        }).catch(res => {
          that.$message.error("获取记事本列表失败：" + res);
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
          that.$http.delete("/api/noteBook/delete", {
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
    components: {
      NoteBookDialog
    }
  }
</script>
<style>
  .text-red {
    color: red;
  }

  .text-danger {
    color: #ff8820;
  }

  .text-green {
    color: #24ff64;
  }
</style>
