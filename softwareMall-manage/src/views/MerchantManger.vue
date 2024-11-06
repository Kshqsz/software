<template>  
  <div>  
    <h1>商家管理</h1>  
    <el-table :data="merchantList" style="width: 100%">  
      <el-table-column prop="avatar" label="头像" width="100">  
        <template #default="scope">  
          <img :src="scope.row.avatar" alt="Avatar" class="merchant-avatar" />  
        </template>  
      </el-table-column>  
      <el-table-column prop="name" label="商家名称" />  
      <el-table-column prop="email" label="邮箱" />  
      <el-table-column prop="phone" label="手机号" />  
      <el-table-column prop="createdAt" label="创建时间" />  
      <el-table-column prop="updatedAt" label="最后修改时间" />  
      <el-table-column prop="status" label="账号状态">  
        <template #default="scope">  
          <div v-if="editingRowId === scope.row.id">  
            <el-select v-model="scope.row.status">  
              <el-option label="激活" value="active" />  
              <el-option label="禁用" value="inactive" />  
            </el-select>  
          </div>  
          <div v-else>  
            <el-tag :type="scope.row.status === 'active' ? 'success' : 'danger'">  
              {{ scope.row.status }}  
            </el-tag>  
            <el-button type="text" @click="editStatus(scope.row)">  
              {{ scope.row.status === 'active' ? '禁用' : '激活' }}  
            </el-button>  
          </div>  
        </template>  
      </el-table-column>  
    </el-table>  
  </div>  
</template>  

<script setup>  
import { ref, reactive, onMounted } from 'vue';  

const merchantList = reactive([  
  {  
    id: 1,  
    avatar: 'https://via.placeholder.com/150',  
    name: 'Acme Inc.',  
    email: 'info@acme.com',  
    phone: '1234567890',  
    createdAt: '2023-04-01',  
    updatedAt: '2023-04-15',  
    status: 'active',  
    originalStatus: 'active',  
  },  
  {  
    id: 2,  
    avatar: 'https://via.placeholder.com/150',  
    name: 'Widgets Co.',  
    email: 'contact@widgets.com',  
    phone: '0987654321',  
    createdAt: '2023-03-20',  
    updatedAt: '2023-04-10',  
    status: 'inactive',  
    originalStatus: 'inactive',  
  },  
  {  
    id: 3,  
    avatar: 'https://via.placeholder.com/150',  
    name: 'Gadgets LLC',  
    email: 'sales@gadgets.com',  
    phone: '5555555555',  
    createdAt: '2023-02-01',  
    updatedAt: '2023-03-30',  
    status: 'active',  
    originalStatus: 'active',  
  },  
]);  

const editingRowId = ref(null);  

const editStatus = (row) => {  
  editingRowId.value = row.id;  
  row.status = row.status === 'active' ? 'inactive' : 'active';  
};  

onMounted(() => {  
  // 在组件挂载时可以执行一些初始化操作  
});  
</script>  

<style scoped>  
.merchant-avatar {  
  width: 50px;  
  height: 50px;  
  border-radius: 50%;  
}  
</style>