<template>  
  <div>  
    <h1>商家管理</h1>  
    <el-table :data="merchantList" style="width: 100%">  
      <el-table-column prop="avatar" label="头像" width="100">  
        <template #default="scope">  
          <img :src="scope.row.avatar" alt="Avatar" class="merchant-avatar" />  
        </template>  
      </el-table-column>  
      <el-table-column prop="username" label="商家名称" />  
      <el-table-column prop="phone" label="手机号" />  
      <el-table-column prop="createTime" label="创建时间" />  
      <el-table-column prop="updateTime" label="最后修改时间" />  
      <el-table-column prop="status" label="账号状态">  
        <template #default="scope">  
          <el-button  
            :type="scope.row.status == '1' ? 'success' : 'danger'"  
            @click="toggleStatus(scope.row)"  
          >  
            {{ scope.row.status == '1' ? '正常' : '禁用' }}  
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
import { getAllMerchant ,ChangeMerchantStatus} from '../api/merchant'

//获取所有商家列表实现
const merchantList = reactive([]);    
const getMerchant = async () => {  
  try {  
    const res = await getAllMerchant();  
    console.log('获取用户成功:', res.data.data);  
    // 使用 merchantList.splice 来确保响应式  
    merchantList.splice(0, merchantList.length, ...res.data.data); // 这个步骤确保 userList 被更新并且是响应式的  
  } catch (error) {  
    console.error('获取用户失败:', error);  
  }  
};  




//修改商家账号状态
const status = ref({  
  id: '',  
  status: '',  
});

const toggleStatus = (row) => {  
  status.value.id = row.id
  row.status = row.status == '1' ? '0' : '1';  
  status.value.status = row.status
  console.log(status.value.id)
  console.log(status.value.status)
  ChangeMerchantStatus(status.value)

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
  getMerchant()  
  console.log("查询商家列表成功")
});  
</script>  

<style scoped>  
.merchant-avatar {  
  width: 50px;  
  height: 50px;  
  border-radius: 50%;  
}  
</style>