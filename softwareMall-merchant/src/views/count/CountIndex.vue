<template>
  <div class="stats-container">
    <h1>数据统计</h1>
    <div class="stats-grid">
      <div class="stats-card">
        <h2>商品统计</h2>
        <p>{{ productStats.count }}</p>
      </div>
      <div class="stats-card">
        <h2>商家统计</h2>
        <p>{{ merchantStats.count }}</p>
      </div>
      <div class="stats-card">
        <h2>用户统计</h2>
        <p>{{ userStats.count }}</p>
      </div>
      <div class="stats-card">
        <h2>销售额统计</h2>
        <p>{{ salesStats.amount }}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

// 商品统计数据
const productStats = ref({
  count: 0
});

// 商家统计数据
const merchantStats = ref({
  count: 0
});

// 用户统计数据
const userStats = ref({
  count: 0
});

// 销售额统计数据
const salesStats = ref({
  amount: 0
});

// 假设这些数据是从API获取的，这里我们使用静态数据作为示例
productStats.value.count = 100; // 假设有100个商品
merchantStats.value.count = 50;  // 假设有50个商家
userStats.value.count = 1000;  // 假设有1000个用户
salesStats.value.amount = 100000; // 假设销售额为100000元
</script>

<style>
.stats-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
}

.stats-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 20px;
  margin-top: 20px;
}

.stats-card {
  background-color: #f7f7f7;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0,0,0,0.2);
}

.stats-card h2 {
  margin: 0 0 10px 0;
  color: #333;
}

.stats-card p {
  margin: 0;
  font-size: 1.5em;
  color: #666;
}
</style>