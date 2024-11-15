<template>
  <div>
    <h1>软件产品管理</h1>
    <div class="filters">
      <div class="filter-item">
        <span>排序方式:</span>
        <el-select v-model="sortType" @change="sortProducts">
          <el-option label="价格升序" value="priceAsc"></el-option>
          <el-option label="价格降序" value="priceDesc"></el-option>
        </el-select>
      </div>
      <div class="filter-item">
        <span>软件查询:</span>
        <el-input v-model="searchText" placeholder="" />
        <el-button type="primary" @click="searchProducts">搜索</el-button>
      </div>
      <div class="filter-item">
        <span>软件分类:</span>
        <el-select v-model="selectedCategory" @change="filterByCategory">
          <el-option label="全部" value=""></el-option>
          <el-option v-for="category in categories" :key="category" :label="category" :value="category"></el-option>
        </el-select>
      </div>
      <div class="filter-item">
        <el-button type="primary" @click="showAddProductDialog">上架新商品</el-button>
      </div>
    </div>
    <el-table :data="paginatedProducts" style="width: 100%">
      <el-table-column prop="image" label="软件图片" width="100">
        <template #default="scope">
          <img :src="scope.row.image" alt="商品图片" class="product-image" />
        </template>
      </el-table-column>
      <el-table-column prop="name" label="软件名称" />
      <el-table-column prop="description" label="软件描述" />
      <el-table-column prop="price" label="软件价格" />
      <el-table-column prop="link" label="软件链接" />
      <el-table-column prop="category" label="软件分类" />
      <el-table-column label="状态">
        <template #default="scope">
          <el-tag v-if="scope.row.status === 'pending'">审核中</el-tag>
          <el-tag v-else-if="scope.row.status === 'published'" type="success">已上架</el-tag>
          <el-tag v-else type="danger">已下架</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button type="primary" @click="editProduct(scope.row)">编辑</el-button>
          <el-button v-if="scope.row.status === 'published'" type="danger" @click="deleteProduct(scope.row)">下架</el-button>
          
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination">
      <el-pagination v-model:current-page="currentPage" v-model:page-size="pageSize" :total="filteredProducts.length" layout="prev, pager, next" @current-change="handlePageChange" />
    </div>
    <el-dialog v-model="addProductDialogVisible" title="上架新商品">
      <el-form :model="newProduct" label-width="120px">
        <el-form-item label="商品名称">
          <el-input v-model="newProduct.name" />
        </el-form-item>
        <el-form-item label="商品描述">
          <el-input v-model="newProduct.description" type="textarea" />
        </el-form-item>
        <el-form-item label="商品价格">
          <el-input-number v-model="newProduct.price" :min="0" />
        </el-form-item>
        <el-form-item label="商品链接">
          <el-input v-model="newProduct.link" />
        </el-form-item>
        <el-form-item label="商品图片">
          <el-input v-model="newProduct.image" />
        </el-form-item>
        <el-form-item label="商品分类">
          <el-select v-model="newProduct.category">
            <el-option v-for="category in categories" :key="category" :label="category" :value="category"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="addProductDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="addProduct">确定</el-button>
      </template>
    </el-dialog>
    <el-dialog v-model="editProductDialogVisible" title="编辑商品">
      <el-form :model="editedProduct" label-width="120px">
        <el-form-item label="商品名称">
          <el-input v-model="editedProduct.name" />
        </el-form-item>
        <el-form-item label="商品描述">
          <el-input v-model="editedProduct.description" type="textarea" />
        </el-form-item>
        <el-form-item label="商品价格">
          <el-input-number v-model="editedProduct.price" :min="0" />
        </el-form-item>
        <el-form-item label="商品链接">
          <el-input v-model="editedProduct.link" />
        </el-form-item>
        <el-form-item label="商品图片">
          <el-input v-model="editedProduct.image" />
        </el-form-item>
        <el-form-item label="商品分类">
          <el-select v-model="editedProduct.category">
            <el-option v-for="category in categories" :key="category" :label="category" :value="category"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="editProductDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="updateProduct">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';

const products = ref([
  { name: 'Product 1', description: 'Description 1', price: 100, link: 'link1.com', image: 'image1.jpg', category: 'Category 1', status: 'published' },
  { name: 'Product 2', description: 'Description 2', price: 200, link: 'link2.com', image: 'image2.jpg', category: 'Category 2', status: 'pending' },
  { name: 'Product 3', description: 'Description 3', price: 300, link: 'link3.com', image: 'image3.jpg', category: 'Category 1', status: 'pending' }
]);

const sortType = ref('priceAsc');
const searchText = ref('');
const selectedCategory = ref('');
const currentPage = ref(1);
const pageSize = ref(10);
const addProductDialogVisible = ref(false);
const editProductDialogVisible = ref(false);
const newProduct = ref({
  name: '',
  description: '',
  price: 0,
  link: '',
  image: '',
  category: '',
  status: 'pending'
});
const editedProduct = ref({});

const filteredProducts = computed(() => {
  let filtered = [...products.value];
  if (searchText.value) {
    filtered = filtered.filter(product =>
      product.name.toLowerCase().includes(searchText.value.toLowerCase())
    );
  }
  if (selectedCategory.value) {
    filtered = filtered.filter(product => product.category === selectedCategory.value);
  }
  return filtered;
});

const paginatedProducts = computed(() => {
  const startIndex = (currentPage.value - 1) * pageSize.value;
  const endIndex = startIndex + pageSize.value;
  return filteredProducts.value.slice(startIndex, endIndex);
});

const categories = computed(() => {
  return [...new Set(products.value.map(product => product.category))];
});

function sortProducts() {
  if (sortType.value === 'priceAsc') {
    products.value.sort((a, b) => a.price - b.price);
  } else {
    products.value.sort((a, b) => b.price - a.price);
  }
}

function searchProducts() {
  currentPage.value = 1;
}

function filterByCategory() {
  currentPage.value = 1;
}

function handlePageChange(page) {
  currentPage.value = page;
}

function showAddProductDialog() {
  addProductDialogVisible.value = true;
}

function addProduct() {
  products.value.push({ ...newProduct.value });
  addProductDialogVisible.value = false;
  newProduct.value = {
    name: '',
    description: '',
    price: 0,
    link: '',
    image: '',
    category: '',
    status: 'pending'
  };
}

function editProduct(product) {
  editedProduct.value = { ...product };
  editProductDialogVisible.value = true;
}

function updateProduct() {
  const index = products.value.findIndex(product => product.name === editedProduct.value.name);
  products.value[index] = { ...editedProduct.value };
  editProductDialogVisible.value = false;
}

function deleteProduct(product) {
  const index = products.value.indexOf(product);
  products.value.splice(index, 1);
}
</script>


<style scoped>  
.product-image {  
  width: 50px;  
  height: 50px;  
  object-fit: cover;  
}  
.filters {  
  display: flex;  
  justify-content: space-between;  
  align-items: center;  
  margin-bottom: 20px;  
}  
.filter-item {  
  display: flex;  
  align-items: center;  
}  
.filter-item span {  
  margin-right: 10px;  
  width: 130px;  
}  
.filter-item .el-input,  
.filter-item .el-select {  
  margin-right: 10px;  
}  
.pagination {  
  display: flex;  
  justify-content: center;  
  margin-top: 20px;  
}  
</style>