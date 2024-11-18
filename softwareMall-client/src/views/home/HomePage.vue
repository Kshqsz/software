<script setup>
import { ref } from 'vue'
import car from '@/assets/xiaomi_car.jpg'
import ultra from '@/assets/su7_ultra.jpg'

// 图片轮播数据
const imageList = ref([
  {id: 0, url: car},
  {id: 1, url: ultra}
])
// 侧边栏菜单数据
const menuItems = ref([
  { id: 0, title: '开发工具', subItems: ['代码编辑器', 'IDE集成开发环境', '版本控制工具'] },
  { id: 1, title: '设计工具', subItems: ['平面设计', 'UI/UX设计', '3D建模'] },
  { id: 2, title: '办公与协作', subItems: ['文档编辑', '项目管理', '在线会议'] },
  { id: 3, title: '教育与学习', subItems: ['语言学习', '编程课程', '在线教育平台'] },
  { id: 4, title: '安全与优化', subItems: ['杀毒软件', '系统清理工具', '密码管理器'] },
  { id: 5, title: '操作系统', subItems: ['Windows', 'Linux', 'macOS'] },
  { id: 6, title: '企业软件', subItems: ['ERP系统', 'CRM系统', '数据分析工具'] },
  { id: 7, title: '游戏与娱乐', subItems: ['PC游戏', '模拟器', '多媒体工具'] },
  { id: 8, title: '其他', subItems: ['开源项目支持', 'API接口工具', '定制开发服务'] },
]);
</script>


<template>
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
            <!-- 展开子菜单项 -->
            <el-menu-item
              v-for="(subItem, subIndex) in item.subItems"
              :key="subIndex"
              :index="`${item.id}-${subIndex}`"
            >
              {{ subItem }}
            </el-menu-item>
          </el-sub-menu>
        </el-menu>
      </el-aside>

      <!-- 主内容区域 -->
      <el-main class="main-content">
        <!-- 图片轮播 -->
        <el-carousel height="510px" indicator-position="outside">
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
</template>


<style scoped>
.sub_menu {
  background-color: #f5f5f5;
}
.box-card {
  padding-left: 40px;
  padding-right: 40px;
  margin: 0 80px;
  border: none; /* 取消边框 */
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