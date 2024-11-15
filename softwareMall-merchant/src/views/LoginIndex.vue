<script setup>  
import { ref } from 'vue';  
import { useRouter } from 'vue-router';  
import { adminLoginService } from '@/api/admin';
import { useAdminStore} from '@/stores';
const form = ref({  
  username: '',  
  password: '',  
});  
// 注册逻辑
const showRegisterModal = ref(false); 
const registerForm = ref({  
  username: '',  
  password: '',  
  confirmPassword: '',  
});  

/* const registerRules = {  
  username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],  
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }],  
  confirmPassword: [  
    { required: true, message: '请确认密码', trigger: 'blur' },  
    { validator: validatePassword, trigger: 'blur' },  
  ],  
};   */

const registerFormRef = ref(null);  

/* const validatePassword = (rule, value, callback) => {  
  if (value !== registerForm.value.password) {  
    callback(new Error('两次输入的密码不一致'));  
  } else {  
    callback();  
  }  
};   */

 const handleRegister = async () => {  
  registerFormRef.value.validate(async (valid) => {  
    if (valid) {  
      try {  
        // 在这里添加注册的逻辑,例如调用后端 API 进行注册  
        console.log('注册成功:', registerForm.value);  
        showRegisterModal.value = false;  
      } catch (error) {  
        console.error('注册失败:', error);  
      }  
    }  
  });  
};  





const rules = {  
  username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],  
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }],  
};  

const formRef = ref(null);  
const router = useRouter();  

const adminStore = useAdminStore()

const handleLogin = async () => {  
  const res = await adminLoginService(form.value)
  adminStore.setToken(res.data.token)
 // ElMessage.success("登录成功");
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
      <h2>软件商城商家登录</h2>  
      <el-form :model="form" :rules="rules" ref="formRef" label-position="top" status-icon>  
        <el-form-item label="用户名" prop="username">  
          <el-input v-model="form.username" placeholder="请输入用户名"></el-input>  
        </el-form-item>  

        <el-form-item label="密码" prop="password">  
          <el-input type="password" v-model="form.password" placeholder="请输入密码"></el-input>  
        </el-form-item>  

        <el-form-item>  
          <el-button type="primary" @click="handleLogin">登录</el-button>  
          <el-button type="text" @click="showRegisterModal = true">注册</el-button>  
        </el-form-item>  
      </el-form>  
    </el-card>  
    <el-dialog v-model="showRegisterModal" title="注册账号" width="400px">  
  <el-form :model="registerForm" :rules="registerRules" ref="registerFormRef" label-position="top" status-icon>  
    <el-form-item label="用户名" prop="username">  
      <el-input v-model="registerForm.username" placeholder="请输入用户名"></el-input>  
    </el-form-item>  
    <el-form-item label="密码" prop="password">  
      <el-input type="password" v-model="registerForm.password" placeholder="请输入密码"></el-input>  
    </el-form-item>  
    <el-form-item label="确认密码" prop="confirmPassword">  
      <el-input type="password" v-model="registerForm.confirmPassword" placeholder="请确认密码"></el-input>  
    </el-form-item>  
  </el-form>  
  <template #footer>  
    <span class="dialog-footer">  
      <el-button @click="showRegisterModal = false">取消</el-button>  
      <el-button type="primary" @click="handleRegister">注册</el-button>  
    </span>  
  </template>  
</el-dialog>
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