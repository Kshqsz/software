<script setup>
import { ref, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import img1 from '@/assets/logo.png'
import img2 from '@/assets/logo1.png'
import img3 from '@/assets/logo2.png'

// 获取路由的查询参数
const route = useRoute();
const router = useRouter();

// 获取当前的搜索条件
const searchQuery = ref(route.query.query || '');
const detail = async (id) => {
  router.push(`/productDetail/${id}`)
}
// 商品列表
const searchResults1 = ref([
  {
    id: 1,
    name: '小米手机',
    description: '一款性价比超高的智能手机',
    price: 1999,
    imageUrl: img1,  
  },
  {
    id: 2,
    name: 'Redmi手机',
    description: '价格亲民的高性价比手机',
    price: 999,
    imageUrl: img2,  
  },
  {
    id: 3,
    name: '小米电视',
    description: '智能大屏电视，画质出众',
    price: 2999,
    imageUrl: img3,  
  },
  {
    id: 4,
    name: '小米笔记本',
    description: '轻薄便携，办公必备',
    price: 4999,
    imageUrl: img1,  
  },
  {
    id: 5,
    name: '小米笔记本',
    description: '轻薄便携，办公必备',
    price: 4999,
    imageUrl: img1, 
  },
  {
    id: 6,
    name: 'Redmi电视',
    description: '超高性价比的智能电视',
    price: 2499,
    imageUrl: img2,
  }
]);

const searchResults2 = ref([
  {
    id: 6,
    name: 'Redmi电视',
    description: '超高性价比的智能电视',
    price: 2499,
    imageUrl: img2,
  }
]);

// 过滤后的搜索结果
const filteredResults = ref([]);

// 过滤函数
const filterResults = () => {
  if (searchQuery.value === '') {
    filteredResults.value = searchResults1.value;
  } else {
    filteredResults.value = searchResults2.value;
  }
};

// 监听 searchQuery 和 route.query.query 变化，确保每次查询变化时都进行过滤
watch(
  () => route.query.query, 
  (newQuery) => {
    searchQuery.value = newQuery || ''; // 更新 searchQuery
    filterResults(); // 重新过滤
  },
  { immediate: true } // 页面加载时就调用一次过滤
);
filterResults(); // 初始时进行过滤

</script>

<template>
  <div class="search-result-page">
    <div class="product-list">
      <el-card
        v-for="(item, index) in filteredResults"
        :key="item.id"
        class="product-card"
        :body-style="{ padding: '20px' }"
        @click="detail(item.id)"
      >
        <div class="card-header">
          <img :src="item.imageUrl" alt="product image" class="product-image" />
          <div class="product-info">
            <h3>{{ item.name }}</h3>
            <p>{{ item.description }}</p>
            <div class="price">￥{{ item.price }}</div>
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>

<style scoped>
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

.product-card {
  width: 265px;  /* 固定每个卡片的宽度 */
  margin-bottom: 20px;  /* 卡片底部间距 */
  transition: transform 0.2s ease, box-shadow 0.2s ease; /* 动画过渡效果 */
  cursor: pointer;  /* 显示点击手势 */
}

.product-card:hover {
  transform: scale(1.05);  /* 放大卡片 */
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);  /* 添加阴影效果 */
}

.product-image {
  width: 100%;
  height: auto;
  border-radius: 8px;
}

.product-info {
  margin-top: 10px;
}

.price {
  font-size: 18px;
  font-weight: bold;
  margin-top: 10px;
}
</style>
