<script setup>  
import { reactive, ref } from 'vue';  
import { useRouter } from 'vue-router';  
/* import axios from 'axios';  
import { ElMessage } from 'element-plus'; 
import { UserStore } from '@/stores'; */
const form = reactive({  
  username: '',  
  password: '',  
});  

const rules = {  
  username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],  
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }],  
};  

const formRef = ref(null);  
const router = useRouter();  



const handleLogin = () => {  
  router.push('/home'); 
  // 验证数据是否合法
  /* formRef.value.validate(async (valid) => {  
    if (valid) {  
      try {  
        //发送请求携带数据
        const response = await axios.post('/admin/login', {  
          username: form.username,  
          password: form.password,  
        });  
        // 处理登录成功的逻辑,保存 token到UserStore,跳转到home页面   
        UserStore.setItem(response.data.token);
        ElMessage.success('登录成功')  
        router.push('/home');  
      } catch (error) {  
        // 处理登录失败的逻辑,例如显示错误消息  
        console.error(error);  
        ElMessage.error('登录失败,请检查用户名和密码');  
      }  
    }  
  });   */
};  
</script>  

<template>  
  <div class="login-container">  
    <el-card class="login-card">  
      <h2>软件商城管理员登录登录</h2>  
      <el-form :model="form" :rules="rules" ref="formRef" label-position="top" status-icon>  
        <el-form-item label="用户名" prop="username">  
          <el-input v-model="form.username" placeholder="请输入用户名"></el-input>  
        </el-form-item>  

        <el-form-item label="密码" prop="password">  
          <el-input type="password" v-model="form.password" placeholder="请输入密码"></el-input>  
        </el-form-item>  

        <el-form-item>  
          <el-button type="primary" @click="handleLogin">登录</el-button>  
        </el-form-item>  
      </el-form>  
    </el-card>  
  </div>  
</template>  



<style>  
.login-container {  
  display: flex;  
  align-items: center;  
  justify-content: center;  
  height: 100vh;  
  background-size: cover; 
  background-position: center;  
  background-repeat: no-repeat; 
  background-image: url('../assets/img/IMG_20220902_152937.jpg');
}  

.login-card {  
  width: 400px;  
}  
</style>