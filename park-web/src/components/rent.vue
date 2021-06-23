<template>
  <!-- 搜索结果 -->
  <el-table :stripe="true" v-loading="search.loading" :data="search.table" style="width: 100%">
    <el-table-column prop="ident" label="ident"/>
    <el-table-column prop="card_id" label="车辆id"/>
    <el-table-column prop="user_id" label="用户id"/>
    <el-table-column prop="cost" label="价格"/>
    <el-table-column prop="rentable_after" label="开始日期"/>
    <el-table-column prop="rentable_before" label="结束日期"/>
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
import {getRemt} from "@/utils/api/rent";

export default {
  methods: {
    handleSizeChange(val) {
      this.search.pageSize = val;
      this.queryRemt();
    },
    handleCurrentChange(val) {
      this.search.page = val;
      this.queryRemt();
    },
  },
  setup() {
    const search = reactive({
      user: getUser(),

      selectUserList: [],

      table: [],
      page: 1,
      pageSize: 50,
      count: 0,
      loading: false,
    });

    const queryRemt = () => {
      search.loading = true;
      getRemt().then(response => {
        search.table = response['rent'];
        search.count = response['count'];
        search.loading = false;
        ElMessage.success('搜索成功');
      }).catch(() => {
        search.loading = false;
      });
    };
    queryRemt();
    return {search, queryRemt};
  },
};
</script>

<style scoped></style>