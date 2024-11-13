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
          <el-button  
            :type="scope.row.status === 'active' ? 'success' : 'danger'"  
            @click="toggleStatus(scope.row)"  
          >  
            {{ scope.row.status === 'active' ? '正常' : '禁用' }}  
          </el-button>  
        </template>  
      </el-table-column>  
      <el-table-column label="上架商品">  
        <template #default="scope">  
          <el-button type="primary" @click="showProductModal(scope.row)">  
            查看商品  
          </el-button>  
        </template>  
      </el-table-column>  
    </el-table>  

    <!-- 商品展示模态框 -->  
    <el-dialog v-model="showModal" title="上架商品" width="80%">  
      <div v-if="selectedMerchant">  
        <h3>{{ selectedMerchant.name }}的上架商品</h3>  
        <div>  
          <el-form :inline="true" class="demo-form-inline">  
            <el-form-item label="排序方式">  
              <el-select v-model="sortBy" placeholder="请选择">  
                <el-option label="价格升序" value="priceAsc" />  
                <el-option label="价格降序" value="priceDesc" />  
              </el-select>  
            </el-form-item>  
            <el-form-item label="商品分类">  
              <el-select v-model="selectedCategory" placeholder="请选择">  
                <el-option label="全部" value="" />  
                <el-option  
                  v-for="category in categories"  
                  :key="category"  
                  :label="category"  
                  :value="category"  
                />  
              </el-select>  
            </el-form-item>  
          </el-form>  
        </div>  
        <el-table :data="filteredProducts" style="width: 100%">  
          <el-table-column prop="image" label="图片" width="100">  
            <template #default="scope">  
              <img :src="scope.row.image" alt="Product Image" width="50" />  
            </template>  
          </el-table-column>  
          <el-table-column prop="name" label="商品名称" />  
          <el-table-column prop="description" label="商品描述" />  
          <el-table-column prop="price" label="价格" />  
          <el-table-column prop="link" label="商品链接">  
            <template #default="scope">  
              <a :href="scope.row.link" target="_blank">{{ scope.row.link }}</a>  
            </template>  
          </el-table-column>  
          <el-table-column prop="category" label="商品分类" />  
        </el-table>  
      </div>  
      <template #footer>  
        <span class="dialog-footer">  
          <el-button @click="showModal = false">关闭</el-button>  
        </span>  
      </template>  
    </el-dialog>  
  </div>  
</template>  

<script setup>  
import { reactive, ref, computed, onMounted } from 'vue';  

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
    products: [  
      {  
        id: 1,  
        name: 'Product A',  
        description: 'This is Product A',  
        price: 9.99,  
        link: 'https://example.com/product-a',  
        image: 'https://via.placeholder.com/150',  
        category: 'Category A',  
      },  
      {  
        id: 2,  
        name: 'Product B',  
        description: 'This is Product B',  
        price: 14.99,  
        link: 'https://example.com/product-b',  
        image: 'https://via.placeholder.com/150',  
        category: 'Category B',  
      },  
      {  
        id: 3,  
        name: 'Product C',  
        description: 'This is Product C',  
        price: 19.99,  
        link: 'https://example.com/product-c',  
        image: 'https://via.placeholder.com/150',  
        category: 'Category A',  
      },  
    ],  
  },  
  // 其他商家数据  
]);  

const toggleStatus = (row) => {  
  row.status = row.status === 'active' ? 'inactive' : 'active';  
};  

const showModal = ref(false);  
const selectedMerchant = ref(null);  
const sortBy = ref('');  
const selectedCategory = ref('');  

const showProductModal = (merchant) => {  
  selectedMerchant.value = merchant;  
  showModal.value = true;  
};  

const filteredProducts = computed(() => {  
  let products = selectedMerchant.value?.products || [];  

  // 根据分类过滤  
  if (selectedCategory.value) {  
    products = products.filter(  
      (product) => product.category === selectedCategory.value  
    );  
  }  

  // 根据排序方式排序  
  if (sortBy.value === 'priceAsc') {  
    products.sort((a, b) => a.price - b.price);  
  } else if (sortBy.value === 'priceDesc') {  
    products.sort((a, b) => b.price - a.price);  
  }  

  return products;  
});  

const categories = computed(() => {  
  const categories = new Set();  
  selectedMerchant.value?.products.forEach((product) => {  
    categories.add(product.category);  
  });  
  return Array.from(categories);  
});  

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