<template>
  <el-container style="height: 100vh;">
    <el-aside width="auto" @mouseenter="open" @mouseleave="close">
      <el-menu
          @select="handleSelect"
          mode="vertical"
          :collapse="false"
          :default-active="activeMenu"
          router
      >
        <template v-for="(item, index) in data.routers" :key="index">
          <el-menu-item :index="item.path" style="width: 200px" v-if="!item.need_admin || data.user.is_admin">
            <i :class="item.class"></i>
            <span>{{ item.title }}</span>
          </el-menu-item>
        </template>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header div="header">
        <ul class="nav">
          <li class="nav-item">
            <span class="minor"
            ><i class="el-icon-user-solid">{{ data.user.name }}</i></span
            >
          </li>
          <li class="nav-item">
            <el-divider direction="vertical"></el-divider>
          </li>
          <li class="nav-item" @click="logout">
            <span class="minor"><i class="el-icon-switch-button">退出</i></span>
          </li>
        </ul>
      </el-header>
      <el-main>
        <div class="content">
          <div class="backtop">
            <router-view />
          </div>
          <el-backtop target=".content .backtop" :bottom="60"></el-backtop>
        </div>
      </el-main>
    </el-container>
  </el-container>
  <el-backtop />
</template>

<script>
import { reactive } from 'vue';
import { getUser } from '@/utils/user';

export default {
  computed: {
    activeMenu() {
      let target = '';
      const path = this.$route.path;
      this.data.routers.some(item => {
        if (path.includes(item.path)) {
          target = item.path;
          return true;
        }
        return false;
      });
      return target;
    },
  },
  methods: {
    open() {
      this.data.menu = false;
    },
    close() {
      this.data.menu = true;
    },
    logout() {
      this.data.user.logout().then(state => {
        if (state) {
          location.reload();
        }
      });
    },
    handleSelect(key) {
      this.$router.push({
        path: key,
      });
    },
  },
  setup() {
    const data = reactive({
      menu: true,
      user: getUser(),
      routers: [
        {
          class: 'el-icon-s-custom',
          title: '用户管理',
          path: '/user',
          need_admin:true
        },
        {
          class: 'el-icon-edit-outline',
          title: '车辆管理',
          path: '/card',
          need_admin:false
        },
        {
          class: 'el-icon-tickets',
          title: '租车记录',
          path: '/rent',
          need_admin:false
        }
      ],
    });
    return { data };
  },
};
</script>

<style>
.menu-logo {
  height: 58px;
}

.nav {
  float: right;
}

.nav-item {
  margin: 0;
  float: left;
  list-style: none;
  position: relative;
  cursor: pointer;
}

.el-aside {
  overflow-x: hidden;
  background-color: #ffffff;
  line-height: 200px;
}

.el-row {
  margin-top: 15px;
}

.el-main {
  background-color: #f2f6fc;
}

.minor {
  color: #909399;
}

a {
  color: #333;
  text-decoration: none;
}

.router-link-active {
  text-decoration: none;
}

* {
  font-size: 14px;
}

.el-table .cell {
  white-space: pre-line;
}

/*.el-scrollbar__wrap {*/
/*  overflow: scroll;*/
/*  margin-right: -60px;*/
/*  height: 110%;*/
/*}*/

.el-autocomplete {
  width: 200px;
}

.el-dialog {
  margin: 5vh auto !important;
  max-height: 90vh !important;
  min-height: 5vh;
  overflow-y: auto;
}

.el-dialog__body {
  overflow: auto;
}

::-webkit-scrollbar {
  width: 8px;
}

::-webkit-scrollbar-thumb {
  background-color: #eaecf1;
  border-radius: 3px;
}

.content {
  height: 100%;
}

.backtop {
  overflow: scroll;
  height: 100%;
}
</style>