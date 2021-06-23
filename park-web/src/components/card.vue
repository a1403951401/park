<template>
  <el-form inline :label-position="'left'" label-width="80px">
    <el-form-item label="车辆">
      <el-input v-model="search.name" placeholder="请输入内容"></el-input>
    </el-form-item>
    <el-form-item label="日期" label-width="40px">
      <el-date-picker
          v-model="search.time"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
      />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="queryCard">搜索</el-button>
      <el-button
          icon="el-icon-refresh-left"
          @click="
          search.name = '';
          search.time = [];
        "
      >重置
      </el-button>
    </el-form-item>
  </el-form>
  <!-- 搜索结果 -->
  <el-table :stripe="true" v-loading="search.loading" :data="search.table" style="width: 100%">
    <el-table-column width="200" prop="organization_name" label="院线"/>
    <el-table-column width="200" prop="complex_name" label="影院"/>
    <el-table-column width="300" prop="client_mac" label="客户端"/>
    <el-table-column
        width="120"
        prop="type"
        :formatter="
        (row, column, data) => {
          return search.logTypeOptions_[data] || data;
        }
      "
        label="类型"
    />
    <el-table-column
        width="100"
        prop="state"
        :formatter="
        (row, column, data) => {
          return data ? '成功' : '失败';
        }
      "
        label="状态"
    />
    <el-table-column width="180" prop="timestamp" :formatter="formatUnix" label="时间"/>
    <el-table-column prop="details" label="日志" show-overflow-tooltip/>
    <el-table-column label="操作" width="120">
      <template #default="scope">
        <el-button
            icon="el-icon-view"
            size="mini"
            type="primary"
            @click="
            search.show = preText(scope.row.details);
            search.isShow = true;
          "
        >查看
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
import {getCard} from "@/utils/api/card";
import {ElMessage} from "element-plus";

export default {
  methods: {},
  setup() {
    const search = reactive({
      name: "",
      time: [],

      isShow: false,
      show: '',

      table: [],
      page: 1,
      pageSize: 50,
      count: 0,
      loading: false,
    });


    const queryCard = () => {
      search.loading = true;
      let t1, t2;
      if (search.time !== null) {
        t1 = search.time[0];
        t2 = search.time[1];
      }
      getCard(
          search.name,
          t1,
          t2,
          search.pageSize,
          search.page
      )
          .then(response => {
            search.table = response['card'];
            search.count = response['count'];
            search.loading = false;
            ElMessage.success('搜索成功');
          })
          .catch(() => {
            search.loading = false;
          });
    };
    queryCard();
    return {search, queryCard};
  },
};
</script>

<style scoped></style>