<script setup>
import { ref, onMounted } from 'vue';
import { useUserStore } from '@/stores'; // 用户store（可根据需要修改）
import { productGetByIdsService } from '@/api/product'; // 获取产品详情的API（此处模拟）

// 用户信息和订单数据
const userStore = useUserStore();
const user = userStore.user;
const orders = ref([]);  // 存储订单数据
const filteredOrders = ref([]);  // 用于存储筛选后的订单
const currentPage = ref(1);  // 当前分页页码
const pageSize = ref(5);  // 每页展示数量（改为5）

// 筛选条件
const orderStatus = ref('all');  // 默认显示所有订单

// 静态订单数据（模拟接口数据）
const staticOrders = [
  { orderNumber: '001', productId: 1, productImage: 'https://via.placeholder.com/80', productName: '软件A', merchantName: '商家1', status: 0 },
  { orderNumber: '002', productId: 2, productImage: 'https://via.placeholder.com/80', productName: '软件B', merchantName: '商家2', status: 1 },
  { orderNumber: '003', productId: 3, productImage: 'https://via.placeholder.com/80', productName: '软件C', merchantName: '商家3', status: -1 },
  { orderNumber: '004', productId: 4, productImage: 'https://via.placeholder.com/80', productName: '软件D', merchantName: '商家4', status: 0 },
  { orderNumber: '005', productId: 5, productImage: 'https://via.placeholder.com/80', productName: '软件E', merchantName: '商家5', status: 1 },
  { orderNumber: '006', productId: 6, productImage: 'https://via.placeholder.com/80', productName: '软件F', merchantName: '商家6', status: -1 },
  { orderNumber: '007', productId: 7, productImage: 'https://via.placeholder.com/80', productName: '软件G', merchantName: '商家7', status: 0 },
];

// 获取订单列表
const getOrders = async () => {
  orders.value = staticOrders;  // 使用静态数据作为订单列表
  console.log('Orders:', orders.value);  // 调试输出所有订单
  applyFilter();  // 获取订单后应用筛选条件
};

// 筛选订单状态
const applyFilter = () => {
  if (orderStatus.value === 'all') {
    filteredOrders.value = orders.value;
  } else {
    filteredOrders.value = orders.value.filter(order => order.status === parseInt(orderStatus.value));
  }
  console.log('Filtered Orders:', filteredOrders.value);  // 调试输出筛选后的订单
};

// 处理状态变化，更新筛选后的订单
const handleStatusChange = () => {
  applyFilter();
};

// 分页改变时触发
const handlePageChange = (page) => {
  currentPage.value = page;
};

// 支付操作
const handlePay = (order) => {
  console.log('支付订单', order);
  // 支付订单逻辑
};

// 下载操作
const handleDownload = (order) => {
  console.log('下载软件', order);
  // 下载软件逻辑
};

// 重新查看商品操作
const handleViewProduct = (order) => {
  console.log('查看商品', order);
  // 重新查看商品逻辑
};

// 获取订单状态的文本描述
const getOrderStatus = (status) => {
  switch(status) {
    case 0:
      return '待支付';
    case 1:
      return '已完成';
    case -1:
      return '已取消';
    default:
      return '未知状态';
  }
};

// 页面加载时获取数据
onMounted(() => {
  getOrders();
});
</script>

<template>
  <el-card class="content" shadow="never">
    <h3>我的订单</h3>
    <hr style="margin-top: 40px; opacity: 0.4">
    
    <!-- 筛选订单 -->
    <el-select v-model="orderStatus" placeholder="筛选订单" @change="handleStatusChange" style="width: 200px; margin-bottom: 20px;">
      <el-option label="全部" value="all"></el-option>
      <el-option label="待支付" value="0"></el-option>
      <el-option label="已完成" value="1"></el-option>
      <el-option label="已取消" value="-1"></el-option>
    </el-select>

    <!-- 订单列表 -->
    <div v-if="filteredOrders.length === 0" class="no-data">
      <p>暂无订单，快去购物吧~</p>
    </div>
    
    <el-table :data="filteredOrders.slice((currentPage-1) * pageSize, currentPage * pageSize)" style="width: 100%">
      <el-table-column label="商品图片" :width="100">
        <template #default="{ row }">
          <img :src="row.productImage" alt="商品图片" style="width: 80px; height: 80px; object-fit: cover;" />
        </template>
      </el-table-column>
      <el-table-column label="订单号" prop="orderNumber" />
      <el-table-column label="商品名" prop="productName" />
      <el-table-column label="商家名" prop="merchantName" />
      <el-table-column label="订单状态" :formatter="(row) => getOrderStatus(row.status)" />
      <el-table-column label="操作">
        <template #default="{ row }">
          <!-- 根据订单状态显示不同的操作按钮 -->
          <el-button v-if="row.status === 0" @click="handlePay(row)" size="mini" type="primary">去支付</el-button>
          <el-button v-if="row.status === 1" @click="handleDownload(row)" size="mini" type="success">下载软件</el-button>
          <el-button v-if="row.status === -1" @click="handleViewProduct(row)" size="mini" type="warning">查看商品</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <!-- 分页居中 -->
    <div style="text-align: center; margin-top: 20px;">
      <el-pagination
        :current-page="currentPage"
        :page-size="pageSize"
        :total="filteredOrders.length"
        layout="prev, pager, next"
        @current-change="handlePageChange"
      ></el-pagination>
    </div>
  </el-card>
</template>

<style scoped>
.no-data {
  text-align: center;
  color: #999;
  font-size: 18px;
  margin-top: 50px;
}
</style>
