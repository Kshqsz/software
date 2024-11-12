<template>  
  <div>  
    <h1>商品管理</h1>  
    <div class="filters">  
      <div class="filter-item">  
        <span>排序方式:</span>  
        <el-select v-model="sortType" @change="sortProducts">  
          <el-option label="价格升序" value="priceAsc"></el-option>  
          <el-option label="价格降序" value="priceDesc"></el-option>  
        </el-select>  
      </div>  
      <div class="filter-item">  
        <span>商品名称:</span>  
        <el-input v-model="searchText" placeholder="输入商品名称" />  
        <el-button type="primary" @click="searchProducts">搜索</el-button>  
      </div>  
      <div class="filter-item">  
        <span>商品分类:</span>  
        <el-select v-model="selectedCategory" @change="filterByCategory">  
          <el-option label="全部" value=""></el-option>  
          <el-option v-for="category in categories" :key="category" :label="category" :value="category"></el-option>  
        </el-select>  
      </div>  
    </div>  
    <el-table :data="paginatedProducts" style="width: 100%">  
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
          <el-button type="danger" @click="deleteProduct(scope.row)">强制下架</el-button>   
        </template>  
      </el-table-column>  
    </el-table>  
    <div class="pagination">  
      <el-pagination  
        v-model:current-page="currentPage"  
        v-model:page-size="pageSize"  
        :total="filteredProducts.length"  
        layout="prev, pager, next"  
        @current-change="handlePageChange"  
      />  
    </div>  
    
  </div>  
</template>  

<script setup>  
import { ref, reactive, computed, } from 'vue';  

//商品数据列表
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

 //分类列表存放
const categories = ['分类 A', '分类 B', '分类 C'];  
//价格排序
const sortType = ref('priceAsc');  
//搜索的关键词
const searchText = ref('');
//搜索的分类  
const selectedCategory = ref('');
//分页当前页码  
const currentPage = ref(1);
//一页展示数据数量
const pageSize = ref(20);  

/* let products = [...productList];: 
这行创建了 productList 数组的一个浅拷贝,用于后续的过滤操作,避免直接修改原始的 productList 数组。

if (searchText.value) { ... }: 
如果用户在搜索框中输入了内容,则会使用 filter 方法过滤 products 数组,只保留商品名称包含搜索文本的商品。

if (selectedCategory.value) { ... }: 
如果用户选择了某个商品分类,则会使用 filter 方法过滤 products 数组,只保留属于该分类的商品。

return products;: 最终,经过搜索和分类筛选后的商品列表会被返回,作为 filteredProducts 的值 */
const filteredProducts = computed(() => {  
  let products = [...productList];  
  if (searchText.value) {  
    products = products.filter((product) =>  
      product.name.toLowerCase().includes(searchText.value.toLowerCase())  
    );  
  }  
  if (selectedCategory.value) {  
    products = products.filter((product) => product.category === selectedCategory.value);  
  }  
  return products;  
});  

const paginatedProducts = computed(() => {  
  const start = (currentPage.value - 1) * pageSize.value;  
  const end = start + pageSize.value;  
  return filteredProducts.value.slice(start, end);  
});  

const sortProducts = () => {  
  if (sortType.value === 'priceAsc') {  
    filteredProducts.value.sort((a, b) => a.price - b.price);  
  } else {  
    filteredProducts.value.sort((a, b) => b.price - a.price);  
  }  
};  

const searchProducts = () => {  
  currentPage.value = 1;  
};  

const filterByCategory = () => {  
  currentPage.value = 1;  
};  

const handlePageChange = () => {  
  // 处理分页切换  
};  
 
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