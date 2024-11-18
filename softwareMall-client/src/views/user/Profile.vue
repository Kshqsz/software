<template>
  <!-- 右侧内容 -->
  <el-card class="content" shadow="never">
    <!-- 用户信息和账号安全 -->
    <div class="user-info-container">
      <!-- 用户信息 -->
      <div class="user-info">
        <img class="avatar" src="https://via.placeholder.com/100" alt="用户头像" />
        <div>
          <h2>{{ userInfo.name }}</h2>
          <p>{{ userInfo.greeting }}</p>
          <a href="#" class="edit-link">修改个人信息 &gt;</a>
        </div>
      </div>

      <!-- 账号安全信息 -->
      <div class="account-security">
        <p>账户安全：<span class="security-level">{{ userInfo.security }}</span></p>
        <p>绑定手机：{{ userInfo.phone }}</p>
        <p>绑定邮箱：{{ userInfo.email }}</p>
      </div>
    </div>

    <hr style="margin-top: 40px; opacity: 0.4">
    
    <!-- 数据统计 -->
    <div class="stats">
      <div
        v-for="(stat, index) in userInfo.stats"
        :key="index"
        class="stat-item"
      >
        <el-card class="stat-icon" shadow = "never":style="{ backgroundColor: stat.color || getStatColor(index) }" >
          <i :class="getStatIcon(index)"></i>
        </el-card>
        <div class="stat-info">
          <h3>{{ stat.label }}</h3>
          <p>{{ stat.count }}</p>
          <a href="#" class="stat-link">查看{{ stat.label }}</a>
        </div>
      </div>
    </div>
  </el-card>
  </template>
  
<script setup>
import { ref } from 'vue'
// 用户信息
const userInfo = ref({
  name: 'Ksh',
  greeting: '晚上好~',
  security: '较高',
  phone: '185*****12',
  email: '24******8@qq.com',
  stats: [
    { label: '待支付的订单', count: 0 },
    { label: '待收货的订单', count: 0 },
    { label: '待评价商品数', count: 1 },
    { label: '喜欢的商品', count: 1 }
  ]
})
  
// 获取统计项颜色
const getStatColor = (index) => {
  const colors = ['#FF7F00', '#67C23A', '#409EFF', '#F7BA2A']
  return colors[index % colors.length]
}

// 获取统计项图标
const getStatIcon = (index) => {
  const icons = ['el-icon-wallet', 'el-icon-truck', 'el-icon-message', 'el-icon-star-on']
  return icons[index % icons.length]
}
</script>

<style scoped>
.content {
  flex: 1;
  padding: 20px;
  
}

/* 用户信息与账号安全水平排列 */
.user-info-container {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
  justify-content: space-between;
  padding-left: 120px;
}

.user-info {
  display: flex;
  align-items: center;
  
}

.user-info .avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  margin-right: 20px;
}

.user-info h2 {
  font-size: 24px;
  margin: 0 0 10px;
}

.user-info p {
  margin: 0 0 10px;
  color: #888;
}

.edit-link {
  color: #409eff;
  text-decoration: none;
  font-size: 14px;
}

.account-security p{
  margin: 5px 0;
  padding-right: 200px;
}

.security-level {
  color: #67c23a;
}

.stats {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  margin-top: 40px;
  margin-left: 100px;
}

.stat-item {
  cursor: pointer; 
  display: flex;
  align-items: center;
  width: calc(40% - 20px);
  padding: 20px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  height: 210px;
  padding-left: 20px;
}
.stat-item:hover {
  transform: scale(1.05);  /* 放大卡片 */
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);  /* 添加阴影效果 */
}

.stat-icon {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #fff;
  font-size: 24px;
  margin-right: 10px;
}

.stat-info h3 {
  margin: 0;
  font-size: 16px;
  color: #333;
}

.stat-info p {
  margin: 5px 0;
  font-size: 24px;
  color: #333;
}

.stat-link {
  font-size: 14px;
  color: #409eff;
  text-decoration: none;
}
</style>
