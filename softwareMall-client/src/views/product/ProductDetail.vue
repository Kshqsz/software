<script setup>
import { useRoute } from 'vue-router';  // 导入 useRoute
import { productGetByIdService } from '@/api/product';
import { onMounted, ref } from 'vue';
import { categoryGetAllService } from '@/api/category'

// 使用 useRoute 获取当前路由信息
const route = useRoute();
const categories = ref([]);
// 定义响应式变量
const product = ref({});
const productId = route.params.id;
const merchantInfo = ref({
  username: '商家名称',  // 假设商家的用户名
  avatar: 'https://via.placeholder.com/100'  // 假设商家的头像
});

// 保存已喜欢商品的 ID 集合
const likedProducts = ref(new Set());

// 获取商品分类名称
const getCategoryName = (id) => {
  const category = categories.value.find(item => item.id === id);
  return category ? category.name : "未知分类";
};

// 获取商品信息和分类
onMounted(async () => {
  const res = await productGetByIdService(productId);
  product.value = res.data.data;
  const category_res = await categoryGetAllService();
  categories.value = category_res.data.data;
});

// 切换商品的喜欢状态
const toggleLike = (productId) => {
  if (likedProducts.value.has(productId)) {
    likedProducts.value.delete(productId);
  } else {
    likedProducts.value.add(productId);
  }
};
</script>

<template>
  <div class="product-detail-page">
    <!-- 商品展示区域 -->
    <div class="product-main">
      <div class="product-image">
        <img :src="product.image" alt="Product Image" class="product-main-image" />
      </div>

      <div class="product-info">
        <h2 class="product-title">{{ product.name }}</h2>
        <p class="category">{{ getCategoryName(product.categoryId) }}</p>
        <p class="product-price">￥{{ product.price }}</p>
        <p class="product-description-title">商品描述</p>
        <p class="product-description">{{ product.description }}</p>

        <div class="product-actions">
          <button class="buy-button">立即购买</button>

          <!-- 喜欢按钮，修改样式使其与立即购买按钮一致 -->
          <button 
            :class="{'like-button': true, 'liked': likedProducts.has(productId)}"
            @click.stop="toggleLike(productId)"
          >
            <i
              :class="likedProducts.has(productId) ? 'fas fa-heart' : 'far fa-heart'"
              class="like-icon"
            ></i>
            喜欢
          </button>
        </div>
      </div>
    </div>

    <hr style="margin-top: 50px;">
    <div class="merchant-info">
      <h3>商家:</h3>
      <img :src="merchantInfo.avatar" alt="Merchant Avatar" class="merchant-avatar" />
      <p class="merchant-username">{{ merchantInfo.username }}</p>
    </div>

    <!-- 视频播放器 -->
    <div v-if="product.video" class="video-player-section">
      <h3>演示视频</h3>
      <video controls class="video-player">
        <source :src="product.video" type="video/mp4" />
        您的浏览器不支持视频播放。
      </video>
    </div>
  </div>
</template>

<style scoped>
.category {
  font-size: 18px;
  color: #777;
  margin-top: 5px;
  font-style: italic;
}

.product-detail-page {
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}

.product-main {
  display: flex;
  gap: 100px;
  margin-top: 20px;
  margin-left: 100px;
}

.product-image {
  flex: 1;
  display: flex;
  justify-content: center;
  width: 100%;
}

.product-main-image {
  width: 550px;
  height: 550px;
  object-fit: cover;
}

.product-info {
  flex: 2;
  display: flex;
  flex-direction: column;
  margin-top: 20px;  /* 增加商品描述与图片的间距 */
}

.product-title {
  font-size: 28px;
  font-weight: bold;
  margin-bottom: 10px;
  align-self: flex-start;  /* 对齐到左侧 */
}

.product-price {
  font-size: 24px;
  color: #f44336;
  font-weight: 600;
  align-self: flex-start;  /* 对齐到左侧 */
}

.product-description-title {
  font-size: 22px;
  font-weight: 600;
  align-self: flex-start;  /* 对齐到左侧 */
}

.product-description {
  font-size: 16px;
  color: #555;
  margin-bottom: 20px;
  align-self: flex-start;  /* 对齐到左侧 */
}

.product-actions {
  display: flex;
  gap: 20px;
  margin-top: 155px;
}

.buy-button,
.like-button {
  padding: 12px 30px;
  font-size: 18px;
  cursor: pointer;
  border: none;
  border-radius: 6px;
  transition: background-color 0.3s;
}

/* 立即购买按钮样式 */
.buy-button {
  background-color: #ff9800;  /* 橙色背景 */
  color: white;
}

.buy-button:hover {
  background-color: #f57c00;  /* 橙色悬浮 */
}

/* 喜欢按钮样式 */
.like-button {
  background-color: #d3d3d3;  /* 默认灰色 */
  color: white;
}

.like-button:hover {
  background-color: #9e9e9e;  /* 灰色悬浮 */
}

/* 喜欢按钮变红的样式 */
.like-button.liked {
  background-color: #f44336;  /* 红色背景 */
}

.like-button.liked:hover {
  background-color: #d32f2f;  /* 红色悬浮 */
}

/* 爱心图标的颜色 */
.like-icon {
  margin-right: 8px;
  font-size: 18px;
}

.like-button.liked .like-icon {
  color: red;  /* 红色爱心 */
}

.merchant-info {
  display: flex;
  align-items: center;
  gap: 15px;
  margin-top: 20px;
  margin-bottom: 40px;
  margin-left: 100px;
}

.merchant-avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  object-fit: cover;
}

.merchant-username {
  font-size: 20px;
  font-weight: 600;
  color: #333;
}
.video-player-section {
  text-align: center;
  margin-top: 40px;
}

.video-player {
  width: 80%;
  max-width: 800px;
  height: 450px;
  border-radius: 8px;
  background-color: #000;
}
</style>
