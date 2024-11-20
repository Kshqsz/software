<script setup>
import { ref } from 'vue'
import { Search } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router';
import { ArrowDown } from '@element-plus/icons-vue'
import { useUserStore } from '@/stores';
import { ElMessage } from 'element-plus';
const searchInfo = ref('')
// 顶部导航文字
const dialogTableVisible = ref(false)
const navItems = ref([
  { name: "首页", path: "/home", isSearch: false },
  { name: "管理系统", path: "", isSearch: true },
  { name: "开发工具", path: "", isSearch: true },
  { name: "操作系统", path: "", isSearch: true },
  { name: "设计工具", path: "", isSearch: true },
  { name: "办公与协作", path: "", isSearch: true },
]);
// const userStore = useUserStore();
// if (userStore.token != '') {
//   navItems.value.push("个人中心")
// }
const router = useRouter()
const userStore = useUserStore()
const username = userStore.username
const search = (keyword = '') => {
  searchInfo.value = keyword || searchInfo.value;
  router.push({ path: '/searchResult', query: { query: searchInfo.value } });
};

const handleNavClick = (item) => {
  if (item.isSearch) {
    // 如果是搜索项，调用搜索方法
    search(item.name);
  } else if (item.path) {
    // 如果是普通导航项，跳转到指定路径
    router.push(item.path);
  }
};
// const navigateTo = (path) => {
//   if (path == '/about') {
//     dialogTableVisible.value = true;

//   } else {
//     router.push(path);
//   }
// };
const logout = () => {
  userStore.removeToken();
  userStore.removeUsername();
  router.push("/");
  ElMessage.success("退出登录成功~")
}
const goToUserCenter = () => {
  router.push("/userCenter")
} 
const goToHome = () => {
  router.push("/home")
}
const gridData = [
  { name: '韩守坤', from: '苏州科技大学'},
  { name: '郁竹一', from: '苏州科技大学'},
  { name: '吴纡怀', from: '苏州科技大学'},
  { name: '陈松',   from: '苏州科技大学'},
]
</script>

<template>
  <el-dialog v-model="dialogTableVisible" title="About us" width="800">
    <el-table :data="gridData">
      <el-table-column property="name" label="Name" width="200" />
      <el-table-column property="from" label="From" />
    </el-table>

    <!-- 指导老师信息 -->
    <div class="mentor-info">
      <span>
        指导老师:
        <a 
          class="director" 
          href="https://eie.usts.edu.cn/info/1120/2941.htm" 
          target="_blank" 
        >
          奚雪峰教授
        </a>
        （电子与信息工程学院）
      </span>
    </div>
  </el-dialog>
  <div class="page-layout">
    <!-- 顶部导航栏 -->
    <el-header height="60px" class="top-nav">
      <!-- Logo -->
      <h1 class="title" @click="goToHome">SoftWare</h1>
      <!-- 导航文字居中 -->
      <div class="nav-items">
        <span v-for="(item, index) in navItems" :key="index" @click="handleNavClick(item)">{{ item.name }}</span>
        <span @click="dialogTableVisible = true;">关于我们</span>
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
  cursor: pointer;
}

.nav-items {
  padding-top: 15px;
  display: flex;
  gap: 15px;
  font-size: 16px;
  justify-content: center;
  cursor: pointer;
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
.mentor-info {
  margin-right: 100px;
  margin-top: 10px;
  text-align: center;
  font-size: 16px;
  color: #333;
}
.director {
  color: #0073e6;
  cursor: pointer;
  text-decoration: none; /* 取消下划线 */
}
</style>
