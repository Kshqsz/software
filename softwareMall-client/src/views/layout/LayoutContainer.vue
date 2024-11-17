<script setup>
import { ref } from 'vue'
import { Search } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router';
import { ArrowDown } from '@element-plus/icons-vue'
import { useUserStore } from '@/stores';
import { ElMessage } from 'element-plus';
const searchInfo = ref('')
// 顶部导航文字
const navItems = ref(["Xiaomi手机", "Redmi手机", "电视", "笔记本", "平板", "家电", "服务中心", "社区"])
// const userStore = useUserStore();
// if (userStore.token != '') {
//   navItems.value.push("个人中心")
// }
const router = useRouter()
const userStore = useUserStore()
const username = userStore.username
const search = () => {
  router.push({ path: '/searchResult', query: { query: searchInfo.value } })
}
const logout = () => {
  userStore.removeToken();
  userStore.removeUsername();
  router.push("/");
  ElMessage.success("退出登录成功~")
}
const goToUserCenter = () => {
  router.push("/userCenter")
} 
</script>

<template>
  <div class="page-layout">
    <!-- 顶部导航栏 -->
    <el-header height="60px" class="top-nav">
      <!-- Logo -->
      <h1 class="title">Xiaomi</h1>
      <!-- 导航文字居中 -->
      <div class="nav-items">
        <span v-for="(item, index) in navItems" :key="index">{{ item }}</span>
        <el-dropdown class="user-dropdown">
          <span>
            {{ username}}
            <el-icon>
              <arrow-down />
            </el-icon>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="goToUserCenter">个人中心</el-dropdown-item>
              <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
      <!-- 搜索框 -->
      <el-input
        v-model="searchInfo"
        placeholder="搜索商品"
        :prefix-icon="Search"
        @keydown.enter="search"
        class="search-box"
        size="small"
      ></el-input>
    </el-header>
    <div>
      <router-view></router-view>
    </div>
  </div>
</template>

<style scoped>
.top-nav {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;
  background-color: #ffffff;
  margin-bottom: 10px;
}

.title {
  padding-top: 15px;
  margin-left: 120px;
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
  margin-right: 120px;
  width: 250px;
  height: 45px;
}

/* 去除个人中心按钮的边框和轮廓 */
.user-dropdown span {
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  outline: none !important;
  border: none !important;
  box-shadow: none !important;
}

.user-dropdown span:focus,
.user-dropdown span:active {
  outline: none !important;
  border: none !important;
  box-shadow: none !important;
}
</style>
