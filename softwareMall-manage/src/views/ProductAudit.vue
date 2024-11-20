<template>  
  <div>  
    <h1>软件上架审核</h1>  
    <el-table :data="pendingProducts" style="width: 100%">  
      <el-table-column prop="name" label="软件名称" />  
      <el-table-column prop="price" label="软件价格" />  
      <el-table-column prop="category" label="软件分类" />  
      <el-table-column prop="seller" label="商家" />  
      <el-table-column label="操作">  
        <template #default="scope">  
          <div style="display: flex; justify-content: center;">  
            <el-button type="success" @click="approveProduct(scope.row)">同意</el-button>  
            <el-button type="danger" @click="rejectProduct(scope.row)">拒绝</el-button>  
            <el-button type="primary" @click="showProductDetails(scope.row)">查看详情</el-button>  
          </div>  
        </template>  
      </el-table-column>  
    </el-table>  

    <el-dialog v-model="showDetailsDialog" title="商品详情" width="50%" center>  
      <div v-if="selectedProduct" class="product-details">  
        <div class="product-image-container">  
          <img :src="selectedProduct.image" alt="软件图片" class="product-image" />  
        </div>  
        <div class="product-info">  
          <h2>{{ selectedProduct.name }}</h2>  
          <p>{{ selectedProduct.description }}</p>  
          <p>价格: {{ selectedProduct.price }}</p>  
          <p>分类: {{ selectedProduct.category }}</p>  
          <p>商家: {{ selectedProduct.seller }}</p>  
          <div class="download-button-container">  
            <el-button type="primary" @click="downloadProduct(selectedProduct)">查看视频</el-button> 
            <el-button type="primary" @click="downloadProduct(selectedProduct)">下载软件</el-button>  
          </div>  
        </div>  
      </div>  
    </el-dialog>  
  </div>  
</template>  

<script setup>  
import { reactive, ref, onMounted } from 'vue';  

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
]);  

const showDetailsDialog = ref(false);  
const selectedProduct = ref(null);  

const approveProduct = (product) => {  
  // 审核通过逻辑  
  product.status = 'approved';  
};  

const rejectProduct = (product) => {  
  // 审核失败逻辑  
  product.status = 'rejected';  
};  

const showProductDetails = (product) => {  
  selectedProduct.value = product;  
  showDetailsDialog.value = true;  
};  

const downloadProduct = (product) => {  
  // 下载商品的逻辑  
  console.log(`下载商品: ${product.name}`);  
};  

onMounted(() => {  
  // 在组件挂载时可以执行一些初始化操作  
});  
</script>  

<style scoped>  
.product-details {  
  display: flex;  
  justify-content: space-between;  
  align-items: center;  
}  

.product-image-container {  
  flex-basis: 50%;  
  display: flex;  
  justify-content: center;  
  align-items: center;  
}  

.product-image {  
  max-width: 100%;  
  max-height: 300px;  
  object-fit: contain;  
}  

.product-info {  
  flex-basis: 50%;  
  text-align: left;  
  padding-left: 20px;  
}  

.download-button-container {  
  display: flex;  
  justify-content: center;  
  margin-top: 20px;  
}  
</style>