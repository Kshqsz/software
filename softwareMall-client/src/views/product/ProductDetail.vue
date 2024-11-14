<script setup>
import { useRoute } from 'vue-router';  // 导入 useRoute

// 使用 useRoute 获取当前路由信息
const route = useRoute();

// 从路由参数中获取 id
const productId = route.params.id;  // 获取动态参数 :id
</script>

<template>
  <div>
    <h1>Product Detail</h1>
    <p>商品 ID: {{ productId }}</p>  <!-- 显示获取到的 id -->
  </div>
</template>

<style scoped>
/* 样式可以根据需要自定义 */
</style>
