<template>  
  <div class="order-management-container">  
    <h2>订单管理</h2>  
    <el-table :data="orderList" style="width: 100%">  
      <el-table-column prop="orderNo" label="订单编号" width="180"></el-table-column>  
      <el-table-column prop="product" label="交易商品" width="180"></el-table-column>  
      <el-table-column prop="tradeTime" label="交易时间" width="180"></el-table-column>  
      <el-table-column prop="buyer" label="买家" width="120"></el-table-column>  
      <el-table-column prop="seller" label="卖家" width="120"></el-table-column>  
      <el-table-column prop="status" label="订单状态" width="120">  
        <template #default="scope">  
          <el-tag :type="getStatusTagType(scope.row.status)">{{ scope.row.status }}</el-tag>  
        </template>  
      </el-table-column>  
      <el-table-column label="操作" width="180">  
        <template #default="scope">  
          <el-button type="primary" size="small" @click="handleApprove(scope.row)">审核通过</el-button>  
          <el-button type="danger" size="small" @click="handleReject(scope.row)">审核拒绝</el-button>  
        </template>  
      </el-table-column>  
    </el-table>  
  </div>  
</template>  

<script setup>  
import { ref } from 'vue';  

const orderList = ref([  
  {  
    orderNo: '202301010001',  
    product: '苹果手机',  
    tradeTime: '2023-01-01 10:30:00',  
    buyer: '张三',  
    seller: '李四',  
    status: '待审核',  
  },  
  {  
    orderNo: '202301010002',  
    product: '电脑显示器',  
    tradeTime: '2023-01-01 14:20:00',  
    buyer: '王五',  
    seller: '赵六',  
    status: '已审核',  
  },  
  {  
    orderNo: '202301020001',  
    product: '耳机',  
    tradeTime: '2023-01-02 09:00:00',  
    buyer: '小明',  
    seller: '小红',  
    status: '已拒绝',  
  },  
]);  

const getStatusTagType = (status) => {  
  switch (status) {  
    case '待审核':  
      return 'warning';  
    case '已审核':  
      return 'success';  
    case '已拒绝':  
      return 'danger';  
    default:  
      return '';  
  }  
};  

const handleApprove = (order) => {  
  // 处理审核通过逻辑  
  console.log('审核通过:', order);  
};  

const handleReject = (order) => {  
  // 处理审核拒绝逻辑  
  console.log('审核拒绝:', order);  
};  
</script>  

<style scoped>  
.order-management-container {  
  padding: 20px;  
}  
</style>