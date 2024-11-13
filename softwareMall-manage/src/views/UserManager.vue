<template>  
  <div>  
    <h1>用户管理</h1>  
    <el-table :data="userList" style="width: 100%">  
      <el-table-column prop="avatar" label="头像" width="100">  
        <template #default="scope">  
          <img :src="scope.row.avatar" alt="Avatar" class="user-avatar" />  
        </template>  
      </el-table-column>  
      <el-table-column prop="username" label="用户名" />  
      <el-table-column prop="phone" label="手机号" />  
      <el-table-column prop="createdAt" label="创建时间" />  
      <el-table-column prop="updatedAt" label="最后修改时间" />  
      <el-table-column prop="status" label="账号状态">  
        <template #default="scope">  
          <el-button  
            :type="scope.row.status === 'active' ? 'success' : 'danger'"  
            @click="toggleStatus(scope.row)"  
          >  
            {{ scope.row.status === 'active' ? '正常' : '禁用' }}  
          </el-button>  
        </template>  
      </el-table-column>  
    </el-table>  
  </div>  
</template>  

<script setup>  
import { reactive, onMounted } from 'vue';  

const userList = reactive([  
  {  
    id: 1,  
    avatar: 'https://via.placeholder.com/150',  
    username: 'John Doe',  
    phone: '1234567890',  
    createdAt: '2023-04-01',  
    updatedAt: '2023-04-15',  
    status: 'active',  
    originalStatus: 'active',  
  },  
  {  
    id: 2,  
    avatar: 'https://via.placeholder.com/150',  
    username: 'Jane Smith',  
    phone: '0987654321',  
    createdAt: '2023-03-20',  
    updatedAt: '2023-04-10',  
    status: 'inactive',  
    originalStatus: 'inactive',  
  },  
  {  
    id: 3,  
    avatar: 'https://via.placeholder.com/150',  
    username: 'Bob Johnson',  
    phone: '5555555555',  
    createdAt: '2023-02-01',  
    updatedAt: '2023-03-30',  
    status: 'active',  
    originalStatus: 'active',  
  },  
]);  

const toggleStatus = (row) => {  
  row.status = row.status === 'active' ? 'inactive' : 'active';  
};  

onMounted(() => {  
  // 在组件挂载时可以执行一些初始化操作  
});  
</script>  

<style scoped>  
.user-avatar {  
  width: 50px;  
  height: 50px;  
  border-radius: 50%;  
}  
</style>