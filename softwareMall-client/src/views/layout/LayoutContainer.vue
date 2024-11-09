<script setup>
import { ref } from 'vue'
import { Search } from '@element-plus/icons-vue'
import logo1 from '@/assets/logo1.png'
import logo3 from '@/assets/logo3.png'
import car from '@/assets/xiaomi_car.jpg'
import ultra from '@/assets/su7_ultra.jpg'

// 图片轮播数据
const imageList = ref([
  {id: 0, url: car},
  {id: 1, url: ultra}
])
const searchInfo = ref('')

// 顶部导航文字
const navItems = ref(["Xiaomi手机", "Redmi手机", "电视", "笔记本", "平板", "家电", "路由器", "服务中心", "社区"])

// 侧边栏菜单数据
const menuItems = ref([
  { id: 0, title: '手机' },
  { id: 1, title: '电视' },
  { id: 2, title: '家电' },
  { id: 3, title: '笔记本 平板 显示器' },
  { id: 4, title: '出行 穿戴' },
  { id: 5, title: '耳机 音箱' },
  { id: 6, title: '健康 儿童' },
  { id: 7, title: '生活 箱包' },
  { id: 8, title: '智能 路由器' },
])
</script>

<template>
  <div class="page-layout">
    <!-- 顶部导航栏 -->
    <el-header height="60px" class="top-nav">
      <!-- Logo -->
      <h1 class="title">Xiaomi</h1>
      <!-- 导航文字居中 -->
      <div class="nav-items">
        <span v-for="(item, index) in navItems" :key="index" class="nav-item">{{ item }}</span>
      </div>
      
      <!-- 搜索框 -->
      <el-input
        v-model="searchInfo"
        placeholder="搜索商品"
        :prefix-icon="Search"
        class="search-box"
        size="small"
      ></el-input>
    </el-header>
    <el-card class="box-card" shadow="never">
      <el-container>
      <!-- 左侧分类菜单 -->
      <el-aside width="200px" class="menu-bar">
        <el-menu default-active="0" class="el-menu-vertical-demo">
          <el-sub-menu
            v-for="item in menuItems"
            :key="item.id"
            :index="item.id.toString()"
            class="sub_menu"
          >
            <template #title>{{ item.title }}</template>
          </el-sub-menu>
        </el-menu>
      </el-aside>

      <!-- 主内容区域 -->
      <el-main class="main-content">
        <!-- 图片轮播 -->
        <el-carousel height="400px" indicator-position="outside">
          <el-carousel-item v-for="item in imageList" :key="item.id">
            <div class="carousel-image-container">
              <img :src="item.url" alt="" class="carousel-image" />
            </div>
          </el-carousel-item>
        </el-carousel>

        <!-- 产品展示区示例 -->
        <div class="product-showcase">
          <el-row :gutter="20">
            <el-col :span="8">
              <div class="product-item">产品1</div>
            </el-col>
            <el-col :span="8">
              <div class="product-item">产品2</div>
            </el-col>
            <el-col :span="8">
              <div class="product-item">产品3</div>
            </el-col>
          </el-row>
        </div>
      </el-main>
    </el-container>      
    </el-card>
  </div>
</template>

<style scoped>
.sub_menu {
  background-color: #f5f5f5;
}
.box-card {
  margin: 0 80px;
  border: none; /* 取消边框 */
}
.top-nav {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;
  background-color: #ffffff;
}

.title {
  padding-top: 15px;
  margin-left: 80px;
}

.nav-items {
  padding-top: 15px;
  display: flex;
  gap: 15px;
  font-size: 16px;
  justify-content: center;
}

.search-box {
  margin-top: 15px;
  margin-right: 80px;
  width: 250px;
  height: 45px;
}

.menu-bar {
  background-color: #f5f5f5;
  height: 510px;
}

.main-content {
  padding: 0;
  background-color: #ffffff;
}

/* 图片轮播的图片容器，保证图片适应容器 */
.carousel-image-container {
  width: 100%;
  height: 100%;
}

.carousel-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.product-showcase {
  margin-top: 39px;
  width: 100%;
  overflow: hidden; 
}
.product-item {
  padding: 10px;
  background-color: #eeeeee;
  text-align: center;
}
</style>
