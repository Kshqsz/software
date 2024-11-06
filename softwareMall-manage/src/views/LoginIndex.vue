<template>  
  <div class="login-container">  
    <el-card class="login-card">  
      <h2>登录</h2>  
      <el-form :model="form" ref="form" label-position="top" status-icon>  
        <el-form-item label="用户名" prop="username" :rules="usernameRules">  
          <el-input v-model="form.username" placeholder="请输入用户名"></el-input>  
        </el-form-item>  

        <el-form-item label="密码" prop="password" :rules="passwordRules">  
          <el-input  
            type="password"  
            v-model="form.password"  
            placeholder="请输入密码"  
          ></el-input>  
        </el-form-item>  

        <el-form-item>  
          <el-button type="primary" @click="handleLogin">登录</el-button>  
        </el-form-item>  
      </el-form>  
    </el-card>  
  </div>  
</template>  

<script>  
export default {  
  data() {  
    return {  
      form: {  
        username: '',  
        password: '',  
      },  
      usernameRules: [  
        { required: true, message: '请输入用户名', trigger: 'blur' }  
      ],  
      passwordRules: [  
        { required: true, message: '请输入密码', trigger: 'blur' }  
      ],  
    };  
  },  
  methods: {  
    handleLogin() {  
      // 在这里可以进行验证逻辑，暂时只用于跳转  
      this.$router.push('/home');  
    },  
  },  
};  
</script>  

<style>  
.login-container {  
  display: flex;  
  align-items: center;  
  justify-content: center;  
  height: 100vh;  
}  

.login-card {  
  width: 400px;  
}  
</style>
