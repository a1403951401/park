<template>
  <el-form inline :label-position="'left'" label-width="80px">
    <el-form-item label="车辆名称">
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

  <el-form-item v-if="search.user.is_admin">
    <el-button type="primary" icon="el-icon-plus" @click="createCardShow()">添加车辆</el-button>
    <el-button icon="el-icon-delete" type="danger" @click="handleDeleteSelect()">批量删除
    </el-button>
  </el-form-item>

  <el-dialog
      title="添加车辆"
      v-model="create.create"
      width="30%"
      :close-on-press-escape="true"
      :before-close="handleClose"
      center
  >
    <el-form ref="create.form" :model="create.form" :rules="create.rules" label-position="top">
      <el-form-item label="车辆名称" prop="name">
        <el-input placeholder="请输入内容" v-model="create.form.name">
        </el-input>
      </el-form-item>
      <el-form-item label="车辆租金" prop="cost">
        <el-input placeholder="请输入内容" v-model.number="create.form.cost">
        </el-input>
      </el-form-item>
      <el-form-item label="时间" prop="time">
        <el-date-picker
            v-model="create.form.time"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
        />
      </el-form-item>

      <el-form-item>
        <el-button icon="el-icon-check" type="primary" @click="handleCreate()">提交</el-button>
        <el-button icon="el-icon-refresh-left" @click="create.form.name='';create.form.cost=0;create.form.time=[];">重置
        </el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

  <el-dialog
      title="修改车辆"
      v-model="update.show"
      width="30%"
      :close-on-press-escape="true"
      :before-close="handleClose"
      center
  >
    <el-form ref="update.form" :model="update.form" :rules="update.rules" label-position="top">
      <el-form-item label="车辆名称" prop="name">
        <el-input placeholder="请输入内容" v-model="update.form.name">
        </el-input>
      </el-form-item>
      <el-form-item label="车辆租金" prop="cost">
        <el-input placeholder="请输入内容" v-model.number="update.form.cost">
        </el-input>
      </el-form-item>
      <el-form-item label="时间" prop="time">
        <el-date-picker
            v-model="update.form.time"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
        />
      </el-form-item>

      <el-form-item>
        <el-button icon="el-icon-check" type="primary" @click="handleUpdate()">提交</el-button>
        <el-button icon="el-icon-refresh-left" @click="update.form.name='';update.form.cost=0;update.form.time=[];">重置
        </el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
  <!-- 搜索结果 -->
  <el-table :stripe="true" v-loading="search.loading" :data="search.table" style="width: 100%"
            @selection-change="handleSelectionChange">
    <el-table-column type="selection" v-if="search.user.is_admin"/>
    <el-table-column prop="id" label="id"/>
    <el-table-column prop="name" label="车辆名称"/>
    <el-table-column prop="cost" label="价格"/>
    <el-table-column prop="rentable_after" label="开始日期"/>
    <el-table-column prop="rentable_before" label="结束日期"/>
    <el-table-column label="操作" width="240" v-if="!search.user.is_admin">
      <template #default="scope">
        <el-button
            icon="el-icon-check"
            size="mini"
            type="primary"
            @click="rentCard(scope.row)"
        >租用
        </el-button>
      </template>
    </el-table-column>
    <el-table-column label="操作" width="240" v-if="search.user.is_admin">
      <template #default="scope">
        <el-button
            icon="el-icon-edit-outline"
            size="mini"
            type="primary"
            @click="updateCardShow(scope.row)"
        >修改
        </el-button>
        <el-button
            v-if="!scope.row.count"
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
import {createCard, deleteCard, getCard, updateCard} from "@/utils/api/card";
import {ElMessage} from "element-plus";
import {getUser} from "@/utils/user";
import {rentCard} from "@/utils/api/rent";

export default {
  methods: {
    rentCard(row) {
      const message = '确定要租用 [' + row['name'] + ']吗?';
      this.$prompt(message, '提示', {
        cancelButtonText: '取消',
        confirmButtonText: '确定',
        center: true,
        type: 'warning',
        inputPattern: new RegExp(row['name']),
        inputErrorMessage: '车辆名称不一致',
      }).then(() => {
        rentCard(row['id']).then(response => {
          if (response) {
            ElMessage.success('租车成功');
            this.queryCard();
          } else {
            ElMessage.error('租车失败');
          }
        });
      });
    },

    handleCreate() {
      this.$refs['create.form'].validate(valid => {
        if (valid) {
          let t1, t2;
          if (this.create.form.time !== null) {
            t1 = this.create.form.time[0];
            t2 = this.create.form.time[1];
          }
          createCard(this.create.form.name, this.create.form.cost, t1, t2).then(response => {
            if (response) {
              ElMessage.success('创建成功');
              this.create.create = false;
              this.queryCard();
            } else {
              ElMessage.error('创建失败');
            }
          });
        }
      });
    },
    handleUpdate() {
      this.$refs['update.form'].validate(valid => {
        if (valid) {
          let t1, t2;
          if (this.update.form.time !== null) {
            t1 = this.update.form.time[0];
            t2 = this.update.form.time[1];
          }
          updateCard(this.update.form.id, this.update.form.name, this.update.form.cost, t1, t2).then(response => {
            if (response) {
              ElMessage.success('更新成功');
              this.update.show = false;
              this.queryCard();
            } else {
              ElMessage.error('更新失败');
            }
          });
        }
      });
    },

    createCardShow() {
      this.create.create = true;
      this.create.form = {
        "name": "",
        "cost": 0,
        "time": [],
      };
    },
    updateCardShow(row) {
      this.update.show = true;
      this.update.form = {
        "id": row['id'],
        "name": row['name'],
        "cost": row['cost'],
        "time": [
            row['rentable_before'], row['rentable_after']
        ],
      };
    },
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
      this.search.selectCardList = [];
      for (const v of val) {
        this.search.selectCardList.push({
          id: v['id'],
        });
      }
    },
    handleSizeChange(val) {
      this.search.pageSize = val;
      this.queryCard();
    },
    handleCurrentChange(val) {
      this.search.page = val;
      this.queryCard();
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
        deleteCard(row['id']).then(response => {
          if (response) {
            ElMessage.warning('删除成功');
            this.queryCard();
          } else {
            ElMessage.error('删除失败');
          }
        });
      });
    },
    handleDeleteSelect() {
      if (this.search.selectCardList.length ===0){
        ElMessage.warning('请先选择需要删除的车辆信息');
        return
      }
      const message =
          '此操作将永久删除是否继续?';
      this.$confirm(message, '提示', {
        cancelButtonText: '取消',
        confirmButtonText: '确定',
        center: true,
        type: 'warning',
      }).then(() => {
        for (const v of this.search.selectCardList) {
          deleteCard(v['id']).then(response => {
            if (response) {
              ElMessage.warning('删除成功');
            } else {
              ElMessage.error('删除失败');
            }
          });
        }
        this.queryCard();
      });
    },
  },
  setup() {
    const search = reactive({
      name: "",
      time: [],

      isShow: false,
      show: '',
      user: getUser(),

      selectCardList: [],

      table: [],
      page: 1,
      pageSize: 50,
      count: 0,
      loading: false,
    });

    const create = reactive({
      create: false,
      form: {
        "name": "",
        "cost": 0,
        "time": [],
      },
      rules: {
        name: [
          {
            required: true,
            message: '请输入车辆名称',
            trigger: ['blur', 'change'],
          },
        ],
        cost: [
          {
            required: true,
            message: '请输入车辆租金',
            trigger: ['blur', 'change'],
          },
          {
            type: 'number',
            message: '车辆租金必须为数字'
          },
          {
            validator: (rule, value, callback) => {
              if (value === 0) {
                callback(new Error('请填写大于0的数字'))
              } else {
                callback()
              }
            },
            trigger: 'change'
          }

        ],
        time: [
          {
            required: true,
            message: '请选择时间',
            trigger: ['blur', 'change'],
          },
        ],
      },
    });

    const update = reactive({
      show: false,
      form: {
        "id":null,
        "name": "",
        "cost": 0,
        "time": [],
      },
      rules: {
        name: [
          {
            required: true,
            message: '请输入车辆名称',
            trigger: ['blur', 'change'],
          },
        ],
        cost: [
          {
            required: true,
            message: '请输入车辆租金',
            trigger: ['blur', 'change'],
          },
          {
            type: 'number',
            message: '车辆租金必须为数字'
          },
          {
            validator: (rule, value, callback) => {
              if (value === 0) {
                callback(new Error('请填写大于0的数字'))
              } else {
                callback()
              }
            },
            trigger: 'change'
          }

        ],
        time: [
          {
            required: true,
            message: '请选择时间',
            trigger: ['blur', 'change'],
          },
        ],
      },
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
    return {search, create, update, queryCard};
  },
};
</script>

<style scoped></style>