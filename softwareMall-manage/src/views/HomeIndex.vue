<script setup>  
import { ref } from 'vue';  
import { useRouter } from 'vue-router';  

const router = useRouter();  
const activeIndex = ref('audit');  

const navigateTo = (path) => {  
  router.push(path);  
};  

const handleSelect = (index) => {  
  activeIndex.value = index;  
  const routes = {  
    audit: '/home/audit',  
    product: '/home/product',  
    merchant: '/home/merchant',  
    user: '/home/user',  
    order: '/home/order',  
  };  
  router.push(routes[index]);  
};  
</script>  


<template>  
  <div class="home-container">  
    <el-row class="row">  
      <el-col :span="5" class="menu-column">  
        <el-menu  
          :default-active="activeIndex"  
          class="side-menu"  
          @select="handleSelect"  
          background-color="#f9f9f9"  
          text-color="#545c64"  
          active-text-color="#409EFF"  
        >  
          <el-menu-item index="audit" @click="navigateTo('/home/audit')">审核</el-menu-item>  
          <el-menu-item index="product" @click="navigateTo('/home/product')">产品</el-menu-item>  
          <el-menu-item index="merchant" @click="navigateTo('/home/merchant')">商家</el-menu-item>  
          <el-menu-item index="user" @click="navigateTo('/home/user')">用户</el-menu-item>  
          <el-menu-item index="order" @click="navigateTo('/home/order')">订单</el-menu-item>  
        </el-menu>  
      </el-col>  
      <el-col :span="19" class="content-column">  
        <div class="content">  
          <router-view></router-view>  
        </div>  
      </el-col>  
    </el-row>  
  </div>  
</template>  



<style scoped>  
.home-container {  
  height: 100vh;  
}  

.row {  
  height: 100%;  
}  

.menu-column {  
  border-right: 1px solid #eaeaea;  
  height: 100%;  
}  

.side-menu {  
  height: 100%;  
}  

.content-column {  
  padding: 20px;  
  height: 100%;  
  overflow-y: auto;  
  background-color: #ffffff;  
}  

.content {  
  min-height: 100%;  
}  
</style>