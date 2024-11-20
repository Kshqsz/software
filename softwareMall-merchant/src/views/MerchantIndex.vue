<template>  
  <div class="merchant-info-container">  
    <el-card class="box-card">  
      <template #header>  
        <div class="card-header">  
          <span>个人信息</span>  
        </div>  
      </template>  
      <div class="user-profile">  
        <div class="avatar-wrapper">  
          <el-avatar :size="80" :src="userInfo.avatar" />  
        </div>  
        <div class="user-info">  
          <div class="username">{{ userInfo.username }}</div>  
          <div class="phone">{{ userInfo.phone }}</div>  
        </div>  
      </div>  
      <div class="actions">  
        <el-button type="primary" @click="showEditDialog = true">  
          编辑信息  
        </el-button>  
        <el-button type="primary" @click="showChangePasswordDialog = true">  
          修改密码  
        </el-button>  
      </div>  
    </el-card>  

    <el-dialog v-model="showEditDialog" title="编辑个人信息">  
      <el-form :model="editForm" label-width="80px">  
        <el-form-item label="用户名">  
          <el-input v-model="editForm.username" />  
        </el-form-item>  
        <el-form-item label="电话">  
          <el-input v-model="editForm.phone" />  
        </el-form-item>  
        <el-form-item label="头像">  
          <el-upload  
            class="avatar-uploader"  
            :show-file-list="false"  
            :on-success="handleAvatarSuccess"  
            :before-upload="beforeAvatarUpload"  
          >  
            <img v-if="editForm.avatar" :src="editForm.avatar" class="avatar" />  
            <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>  
          </el-upload>  
        </el-form-item>  
      </el-form>  
      <template #footer>  
        <el-button @click="showEditDialog = false">取消</el-button>  
        <el-button type="primary" @click="saveUserInfo">保存</el-button>  
      </template>  
    </el-dialog>  

    <el-dialog v-model="showChangePasswordDialog" title="修改密码">  
      <el-form :model="passwordForm" label-width="80px">  
        <el-form-item label="原密码">  
          <el-input v-model="passwordForm.oldPassword" type="password" />  
        </el-form-item>  
        <el-form-item label="新密码">  
          <el-input v-model="passwordForm.newPassword" type="password" />  
        </el-form-item>  
        <el-form-item label="确认密码">  
          <el-input v-model="passwordForm.confirmPassword" type="password" />  
        </el-form-item>  
      </el-form>  
      <template #footer>  
        <el-button @click="showChangePasswordDialog = false">取消</el-button>  
        <el-button type="primary" @click="changePassword">保存</el-button>  
      </template>  
    </el-dialog>  
  </div>  
</template>  

<script setup>  
import { ref } from 'vue';  

const userInfo = ref({  
  username: 'John Doe',  
  phone: '123-456-7890',  
  avatar: 'https://via.placeholder.com/80',  
});  

const editForm = ref({  
  username: userInfo.value.username,  
  phone: userInfo.value.phone,  
  avatar: userInfo.value.avatar,  
});  

const showEditDialog = ref(false);  
const showChangePasswordDialog = ref(false);  

const passwordForm = ref({  
  oldPassword: '',  
  newPassword: '',  
  confirmPassword: '',  
});  

const handleAvatarSuccess = (response, uploadFile) => {  
  editForm.value.avatar = URL.createObjectURL(uploadFile.raw);  
};  

const beforeAvatarUpload = (file) => {  
  const isJPG = file.type === 'image/jpeg';  
  const isLt2M = file.size / 1024 / 1024 < 2;  

  if (!isJPG) {  
   // ElMessage.error('头像图片只能是 JPG 格式!');  
  }  
  if (!isLt2M) {  
   // ElMessage.error('头像图片大小不能超过 2MB!');  
  }  
  return isJPG && isLt2M;  
};  

const saveUserInfo = () => {  
  // 执行保存用户信息的逻辑  
  console.log('保存用户信息:', editForm.value);  
  showEditDialog.value = false;  
};  

const changePassword = () => {  
  // 执行修改密码的逻辑  
  console.log('修改密码:', passwordForm.value);  
  showChangePasswordDialog.value = false;  
};  
</script>  

<style scoped>  
.merchant-info-container {  
  display: flex;  
  justify-content: center;  
  align-items: center;  
  height: 100vh;  
  background-color: #f5f5f5;  
}  

.box-card {  
  width: 600px;  
}  

.card-header {  
  font-size: 18px;  
  font-weight: bold;  
}  

.user-profile {  
  display: flex;  
  align-items: center;  
  margin-bottom: 20px;  
}  

.avatar-wrapper {  
  margin-right: 20px;  
}  

.username {  
  font-size: 18px;  
  font-weight: bold;  
}  

.phone {  
  color: #666;  
}  

.actions {  
  display: flex;  
  justify-content: flex-end;  
}  

.actions button {  
  margin-left: 10px;  
}  

.avatar-uploader .avatar {  
  width: 78px;  
  height: 78px;  
  display: block;  
}  

.avatar-uploader .el-upload {  
  border: 1px dashed var(--el-border-color);  
  border-radius: 6px;  
  cursor: pointer;  
  position: relative;  
  overflow: hidden;  
  transition: var(--el-transition-duration-fast);  
}  

.avatar-uploader .el-upload:hover {  
  border-color: var(--el-color-primary);  
}  

.avatar-uploader-icon {  
  font-size: 28px;  
  color: #8c939d;  
  width: 78px;  
  height: 78px;  
  text-align: center;  
}  
</style>