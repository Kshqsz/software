<script setup>
import { ref, watch} from 'vue';
import { useRoute } from 'vue-router';
import { productSearchService } from '@/api/product'
import { categoryGetAllService} from '@/api/category'
import { onMounted } from 'vue';

// 获取路由的查询参数
const route = useRoute();
const categories = ref([]);

const getCategoryName = (id) => {
  const category = categories.value.find(item => item.id === id)
  return category ? category.name : "未知分类"
}
// 获取当前的搜索条件
const searchQuery = ref(route.query.query || '');

// 过滤后的搜索结果
const filteredResults = ref([]);

// 过滤函数
const filterResults = async () => {
  const res = await productSearchService(searchQuery.value)
  filteredResults.value = res.data.data;
  filteredResults.value = filteredResults.value.filter(item => item.status === 1);
};

// 监听 searchQuery 和 route.query.query 变化，确保每次查询变化时都进行过滤
watch(
  () => [route.query.query, route.query.t], 
  () => {
    searchQuery.value = route.query.query || ''; // 更新 searchQuery
    filterResults(); // 重新过滤
  },
  { immediate: true } // 页面加载时就调用一次过滤
);

onMounted( async () => {
  const category_res = await categoryGetAllService();
  categories.value = category_res.data.data;
})
</script>

<template>
  <div class="search-result-page">
    <div v-if="filteredResults.length === 0" class="no-data">
      <p>暂无数据，请尝试其他搜索关键词。</p>
    </div>
    <div v-else class="product-list">
      <ProductCard
        v-for="product in filteredResults"
        :key="product.id"
        :product="product"
        :categoryName="getCategoryName(product.categoryId)"
      />
    </div>
  </div>
</template>

<style scoped>
.no-data {
  text-align: center;
  color: #999;
  font-size: 18px;
  margin-top: 50px;
}
.category {
  font-size: 14px;
  color: #777;
  margin-top: 5px;
  font-style: italic;
}
.search-result-page {
  padding: 20px;
  margin-left: 120px;
}

.product-list {
  display: flex;
  flex-wrap: wrap;  /* 允许换行 */
  gap: 20px;  /* 设置卡片之间的间距 */
  justify-content: left;  /* 每一行的卡片居中 */
}
</style>
