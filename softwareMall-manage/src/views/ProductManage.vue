<template>  
  <div>  
    <h1>商品管理</h1>  
    <el-table :data="productList" style="width: 100%">  
      <el-table-column prop="image" label="商品图片" width="100">  
        <template #default="scope">  
          <img :src="scope.row.image" alt="商品图片" class="product-image" />  
        </template>  
      </el-table-column>  
      <el-table-column prop="name" label="商品名称" />  
      <el-table-column prop="description" label="商品描述" />  
      <el-table-column prop="price" label="商品价格" />  
      <el-table-column prop="link" label="商品链接" />  
      <el-table-column prop="category" label="商品分类" />  
      <el-table-column label="操作">  
        <template #default="scope">  
          <el-button type="primary" @click="editProduct(scope.row)">编辑</el-button>  
          <el-button type="danger" @click="deleteProduct(scope.row)">删除</el-button>  
        </template>  
      </el-table-column>  
    </el-table>  
    <el-dialog v-model="dialogVisible" title="编辑商品" width="50%">  
      <el-form :model="editingProduct" label-width="120px">  
        <el-form-item label="商品名称">  
          <el-input v-model="editingProduct.name" />  
        </el-form-item>  
        <el-form-item label="商品描述">  
          <el-input v-model="editingProduct.description" type="textarea" />  
        </el-form-item>  
        <el-form-item label="商品价格">  
          <el-input-number v-model="editingProduct.price" />  
        </el-form-item>  
        <el-form-item label="商品链接">  
          <el-input v-model="editingProduct.link" />  
        </el-form-item>  
        <el-form-item label="商品图片">  
          <el-upload  
            v-model:file-list="editingProduct.image"  
            list-type="picture-card"  
            :auto-upload="false"  
          >  
            <i class="el-icon-plus"></i>  
          </el-upload>  
        </el-form-item>  
        <el-form-item label="商品分类">  
          <el-select v-model="editingProduct.category">  
            <el-option  
              v-for="category in categories"  
              :key="category"  
              :label="category"  
              :value="category"  
            ></el-option>  
          </el-select>  
        </el-form-item>  
      </el-form>  
      <template #footer>  
        <span class="dialog-footer">  
          <el-button @click="dialogVisible = false">取消</el-button>  
          <el-button type="primary" @click="saveProduct">保存</el-button>  
        </span>  
      </template>  
    </el-dialog>  
  </div>  
</template>  

<script setup>  
import { ref, reactive, onMounted } from 'vue';  

const productList = reactive([  
  {  
    id: 1,  
    name: '商品 A',  
    description: '这是商品 A 的描述',  
    price: 99.99,  
    link: 'https://example.com/product-a',  
    image: 'https://via.placeholder.com/150',  
    category: '分类 A',  
  },  
  {  
    id: 2,  
    name: '商品 B',  
    description: '这是商品 B 的描述',  
    price: 49.99,  
    link: 'https://example.com/product-b',  
    image: 'https://via.placeholder.com/150',  
    category: '分类 B',  
  },  
  {  
    id: 3,  
    name: '商品 C',  
    description: '这是商品 C 的描述',  
    price: 24.99,  
    link: 'https://example.com/product-c',  
    image: 'https://via.placeholder.com/150',  
    category: '分类 C',  
  },  
]);  

const dialogVisible = ref(false);  
const editingProduct = ref({  
  id: null,  
  name: '',  
  description: '',  
  price: 0,  
  link: '',  
  image: [],  
  category: '',  
});  

const categories = ['分类 A', '分类 B', '分类 C'];  

const editProduct = (product) => {  
  editingProduct.value = { ...product };  
  dialogVisible.value = true;  
};  

const saveProduct = () => {  
  // 保存编辑后的商品  
  dialogVisible.value = false;  
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