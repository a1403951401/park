<template slot-scope="scope">
  <div class="login-wrap">
    <el-tabs type="border-card">
      <el-tab-pane label="用户登录">
        <el-form class="login-form" label-position="top" label-width="0px">
          <el-form-item label="用户名">
            <el-input
                placeholder="请输入用户名..."
                prefix-icon="el-icon-user"
                v-model="data.username"
            />
          </el-form-item>
          <el-form-item label="密码">
            <el-input
                placeholder="请输入密码..."
                prefix-icon="el-icon-lock"
                v-model="data.password"
                show-password
            />
          </el-form-item>
          <el-form-item label="验证码">
            <el-row>
              <el-col :span="16">
                <el-input
                    placeholder="请输入验证码..."
                    prefix-icon="el-icon-picture-outline"
                    v-model="data.code"
                    v-on:keydown.enter="login"
                />
              </el-col>
              <el-col :span="8">
                <img :src="data.code_url" v-on:click="code()"
                     style="width: 120px; height: 30px;margin-top: 5px;margin-left: 10px"/>
              </el-col>
            </el-row>
          </el-form-item>
          <el-button class="login-btn" type="primary" @click="login">登录</el-button>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="用户注册">
        <el-form class="login-form" label-position="top" label-width="0px">
          <el-form-item label="用户名">
            <el-input
                placeholder="请输入用户名..."
                prefix-icon="el-icon-user"
                v-model="data.username"
            />
          </el-form-item>
          <el-form-item label="密码">
            <el-input
                placeholder="请输入密码..."
                prefix-icon="el-icon-lock"
                v-model="data.password"
                show-password
            />
          </el-form-item>
          <el-form-item label="名称">
            <el-input
                placeholder="请输入名称..."
                prefix-icon="el-icon-user-solid"
                v-model="data.name"
            />
          </el-form-item>
          <el-form-item label="手机号">
            <el-input
                placeholder="请输入手机号..."
                prefix-icon="el-icon-phone-outline"
                v-model="data.phone"
            />
          </el-form-item>
          <el-form-item label="驾照号码">
            <el-input
                placeholder="请输入驾照号码..."
                prefix-icon="el-icon-postcard"
                v-model="data.license_id"
            />
          </el-form-item>
          <el-form-item label="验证码">
            <el-row>
              <el-col :span="16">
                <el-input
                    placeholder="请输入验证码..."
                    prefix-icon="el-icon-picture-outline"
                    v-model="data.code"
                    v-on:keydown.enter="login"
                />
              </el-col>
              <el-col :span="8">
                <img :src="data.code_url" v-on:click="code()"
                     style="width: 100px; height: 25px;margin-top: 5px;margin-left: 20px"/>
              </el-col>
            </el-row>
          </el-form-item>
          <el-button class="login-btn" type="primary" @click="register">注册</el-button>
        </el-form>
      </el-tab-pane>
    </el-tabs>

  </div>
</template>

<script lang="ts">
import {reactive} from 'vue';
import {getUser} from '../utils/user';

export default {
  name: 'Login',

  methods: {

    code() {
      this.data.code_url = `/api/auth/get_code?num=${Math.ceil(Math.random() * 100)}`;
    },
    login() {
      // 登录成功重定向
      this.data.user.login(this.data.username, this.data.password, this.data.code).then(state => {
        if (state) {
          this.$router.push({path: '/'});
        }
      });
    },
    register() {
      // 注册成功重定向
      this.data.user.register(this.data.username, this.data.password, this.data.code, this.data.name, this.data.license_id, this.data.phone).then(state => {
        if (state) {
          this.$router.push({path: '/'});
        }
      });
    },
  },
  setup() {
    const data = reactive({
      user: getUser(),
      username: "",
      password: "",
      name: "",
      license_id: "",
      phone: "",
      code: "",
      code_url: "/api/auth/get_code",
    });
    return {data};
  },
};
</script>

<style>
.login-wrap {
  height: 100vh;
  background: #324152;
  display: flex;
  justify-content: center;
  align-items: center;
}

.login-form {
  width: 400px;
  background: #fff;
  padding: 40px;
}

.login-btn {
  width: 100%;
}

.el-tabs__item {
  width: 255px;
  text-align: center;
}
</style>
