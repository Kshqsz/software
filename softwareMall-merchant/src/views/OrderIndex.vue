<template>  
  <div class="order-management-container">  
    <h2>订单管理</h2>  
    <div class="filters">  
      <div class="filter-item">  
        <span>订单编号:</span>  
        <el-input v-model="orderNo" placeholder="输入订单编号" />  
        <el-button type="primary" @click="searchOrders">查询</el-button>  
      </div>  
    </div>  
    <el-table :data="paginatedOrders" style="width: 100%">  
      <el-table-column prop="id" label="订单ID" width="120"></el-table-column>  
      <el-table-column prop="orderNo" label="订单编号" width="180"></el-table-column>  
      <el-table-column prop="product" label="交易商品" width="180"></el-table-column>  
      <el-table-column prop="productLink" label="商品链接" width="180">  
        <template #default="scope">  
          <a :href="scope.row.productLink" target="_blank">{{ scope.row.productLink }}</a>  
        </template>  
      </el-table-column>  
      <el-table-column prop="tradeTime" label="交易时间" width="180"></el-table-column>  
      <el-table-column prop="buyer" label="买家" width="120"></el-table-column>  
      <el-table-column prop="status" label="订单状态" width="120">  
        <template #default="scope">  
          <el-tag :type="getStatusTagType(scope.row.status)">{{ scope.row.status }}</el-tag>  
        </template>  
      </el-table-column>  
    </el-table>  
    <div class="pagination">  
      <el-pagination  
        v-model:current-page="currentPage"  
        v-model:page-size="pageSize"  
        :total="filteredOrders.length"  
        layout="prev, pager, next"  
        @current-change="handlePageChange"  
      />  
    </div>  
  </div>  
</template>  

<script setup>  
import { ref, computed } from 'vue';  

const orderList = ref([  
  {  
    id: 1,  
    orderNo: '202301010001',  
    product: '苹果手机',  
    productLink: 'https://example.com/apple-phone',  
    tradeTime: '2023-01-01 10:30:00',  
    buyer: '张三',  
    status: '已完成',  
  },  
  {  
    id: 2,  
    orderNo: '202301010002',  
    product: '电脑显示器',  
    productLink: 'https://example.com/computer-monitor',  
    tradeTime: '2023-01-01 14:20:00',  
    buyer: '王五',  
    status: '未支付',  
  },  
  {  
    id: 3,  
    orderNo: '202301020001',  
    product: '耳机',  
    productLink: 'https://example.com/headphones',  
    tradeTime: '2023-01-02 09:00:00',  
    buyer: '小明',  
    status: '已取消',  
  },  
]);  

const currentPage = ref(1);  
const pageSize = ref(10);  
const orderNo = ref('');  

const filteredOrders = computed(() => {  
  let orders = [...orderList.value];  
  if (orderNo.value) {  
    orders = orders.filter((order) =>  
      order.orderNo.toLowerCase().includes(orderNo.value.toLowerCase())  
    );  
  }  
  return orders;  
});  

const paginatedOrders = computed(() => {  
  const start = (currentPage.value - 1) * pageSize.value;  
  const end = start + pageSize.value;  
  return filteredOrders.value.slice(start, end);  
});  

const getStatusTagType = (status) => {  
  switch (status) {  
    case '已完成':  
      return 'success';  
    case '未支付':  
      return 'warning';  
    case '已取消':  
      return 'danger';  
    default:  
      return '';  
  }  
};  

const searchOrders = () => {  
  currentPage.value = 1;  
};  

const handlePageChange = () => {  
  // 处理分页切换  
};  
</script>  

<style scoped>  
.order-management-container {  
  padding: 20px;  
}  
.filters {  
  display: flex;  
  justify-content: space-between;  
  align-items: center;  
  margin-bottom: 20px;  
}  
.filter-item {  
  display: flex;  
  align-items: center;  
}  
.filter-item span {  
  margin-right: 10px;  
  width: 120px;  
}  
.filter-item .el-input,  
.filter-item .el-select {  
  margin-right: 10px;  
}  
.pagination {  
  display: flex;  
  justify-content: center;  
  margin-top: 20px;  
}  
</style>