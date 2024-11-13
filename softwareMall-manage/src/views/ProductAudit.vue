<template>  
  <div>  
    <h1>软件上架审核</h1>  
    <el-table :data="pendingProducts" style="width: 100%">  
      <el-table-column prop="image" label="软件图片" width="100">  
        <template #default="scope">  
          <img :src="scope.row.image" alt="软件图片" class="product-image" />  
        </template>  
      </el-table-column>  
      <el-table-column prop="name" label="软件名称" />  
      <el-table-column prop="description" label="软件描述" />  
      <el-table-column prop="price" label="软件价格" />  
      <el-table-column prop="link" label="软件链接" />  
      <el-table-column prop="category" label="软件分类" />  
      <el-table-column prop="seller" label="商家" />  
      <el-table-column label="操作">  
        <template #default="scope">  
          <div style="display: flex; justify-content: center;">  
            <el-button type="success" @click="approveProduct(scope.row)">同意</el-button>  
            <el-button type="danger" @click="rejectProduct(scope.row)">拒绝</el-button>  
          </div>  
        </template>  
      </el-table-column>  
    </el-table>  
  </div>  
</template>  

<script setup>  
import { reactive, onMounted } from 'vue';  

const pendingProducts = reactive([  
  {  
    id: 1,  
    name: '待审核商品 A',  
    description: '这是待审核的商品 A',  
    price: 99.99,  
    link: 'https://example.com/product-a',  
    image: 'https://via.placeholder.com/150',  
    category: '分类 A',  
    seller: '商家 A',  
    status: 'pending',  
  },  
  {  
    id: 2,  
    name: '待审核商品 B',  
    description: '这是待审核的商品 B',  
    price: 49.99,  
    link: 'https://example.com/product-b',  
    image: 'https://via.placeholder.com/150',  
    category: '分类 B',  
    seller: '商家 B',  
    status: 'pending',  
  },  
  {  
    id: 3,  
    name: '待审核商品 C',  
    description: '这是待审核的商品 C',  
    price: 24.99,  
    link: 'https://example.com/product-c',  
    image: 'https://via.placeholder.com/150',  
    category: '分类 C',  
    seller: '商家 C',  
    status: 'pending',  
  },  
]);  

const approveProduct = (product) => {  
  // 审核通过逻辑  
  product.status = 'approved';  
};  

const rejectProduct = (product) => {  
  // 审核失败逻辑  
  product.status = 'rejected';  
};  

onMounted(() => {  
  // 在组件挂载时可以执行一些初始化操作  
});  
</script>  

<style scoped>  
.product-image {  
  width: 50px;  
  height: 50px;  
  object-fit: cover;  
}  
</style>