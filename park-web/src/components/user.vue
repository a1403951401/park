<template>
  <el-form inline :label-position="'left'" label-width="80px">
    <el-form-item label="登录名">
      <el-input v-model="search.username" placeholder="请输入内容"></el-input>
    </el-form-item>
    <el-form-item label="用户名">
      <el-input v-model="search.name" placeholder="请输入内容"></el-input>
    </el-form-item>
    <el-form-item label="驾照号码">
      <el-input v-model="search.license_id" placeholder="请输入内容"></el-input>
    </el-form-item>
    <el-form-item label="手机号码">
      <el-input v-model="search.phone" placeholder="请输入内容"></el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="queryUser">搜索</el-button>
      <el-button
          icon="el-icon-refresh-left"
          @click="
          search.username = '';
          search.name = '';
          search.license_id = '';
          search.phone = '';
        "
      >重置
      </el-button>
    </el-form-item>
  </el-form>

  <!-- 搜索结果 -->
  <el-table :stripe="true" v-loading="search.loading" :data="search.table" style="width: 100%"
            @selection-change="handleSelectionChange">
    <el-table-column prop="id" label="id"/>
    <el-table-column prop="username" label="登录名"/>
    <el-table-column prop="name" label="用户名"/>
    <el-table-column prop="license_id" label="驾照号码"/>
    <el-table-column prop="phone" label="手机号码"/>
    <el-table-column prop="is_admin" label="是否管理员">
      <template #default="scope">
        <el-tag
            v-if="scope.row.is_admin === 1"
            size="medium"
            effect="dark"
            type="danger"
        >
          管理员
        </el-tag>
        <el-tag
            v-if="scope.row.is_admin === 0"
            size="medium"
            effect="dark"
        >
          用户
        </el-tag>
      </template>
    </el-table-column>
    <el-table-column label="操作" width="240" v-if="search.user.is_admin">
      <template #default="scope">
        <el-button
            v-if="scope.row.name !== search.user.name"
            icon="el-icon-delete"
            size="mini"
            type="danger"
            @click="handleDelete(scope.index_, scope.row)"
        >删除
        </el-button>
      </template>
    </el-table-column>
  </el-table>
  <!-- 分页 -->
  <el-row>
    <el-col :span="2">
      <el-pagination style="text-align: left" layout="total" :total="search.count"/>
    </el-col>
    <el-col :span="20">
      <el-pagination
          style="text-align: center"
          @current-change="handleCurrentChange"
          :total="search.count"
          :page-size="search.pageSize"
          :background="true"
          layout="prev, pager, next"
      >
      </el-pagination>
    </el-col>
    <el-col :span="2">
      <el-pagination
          style="text-align: right"
          @size-change="handleSizeChange"
          :page-size="search.pageSize"
          layout="sizes"
      >
      </el-pagination>
    </el-col>
  </el-row>
</template>

<script>

import {reactive} from "vue";
import {ElMessage} from "element-plus";
import {getUser} from "@/utils/user";
import {deleteUser, getUserData} from "@/utils/api/user";

export default {
  methods: {
    handleClose(done) {
      this.$confirm('确认关闭？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        beforeClose: (action, instance, done) => {
          if (action !== 'close') {
            done();
          }
        },
      }).then(() => {
        done();
      });
    },
    handleSelectionChange(val) {
      this.search.selectUserList = [];
      for (const v of val) {
        this.search.selectUserList.push({
          id: v['id'],
        });
      }
    },
    handleSizeChange(val) {
      this.search.pageSize = val;
      this.queryUser();
    },
    handleCurrentChange(val) {
      this.search.page = val;
      this.queryUser();
    },
    handleDelete(index, row) {
      const message =
          '此操作将永久删除 [' + row['name'] + ']是否继续?';
      this.$confirm(message, '提示', {
        cancelButtonText: '取消',
        confirmButtonText: '确定',
        center: true,
        type: 'warning',
      }).then(() => {
        deleteUser(row['id']).then(response => {
          if (response) {
            ElMessage.warning('删除成功');
            this.queryUser();
          } else {
            ElMessage.error('删除失败');
          }
        });
      });
    },
  },
  setup() {
    const search = reactive({
      username: "",
      name: "",
      license_id: "",
      is_admin: "",
      phone: "",

      isShow: false,
      show: '',
      user: getUser(),

      selectUserList: [],

      table: [],
      page: 1,
      pageSize: 50,
      count: 0,
      loading: false,
    });

    const queryUser = () => {
      search.loading = true;
      getUserData(
          search.username,
          search.name,
          search.license_id,
          search.is_admin,
          search.phone,
          search.pageSize,
          search.page
      ).then(response => {
        search.table = response['user'];
        search.count = response['count'];
        search.loading = false;
        ElMessage.success('搜索成功');
      }).catch(() => {
        search.loading = false;
      });
    };
    queryUser();
    return {search, queryUser};
  },
};
</script>

<style scoped></style>