<template>  
  <div class="category-management">  
    <h1>分类管理</h1>  
    <el-card class="new-category-card">  
      <h2>新增分类</h2>  
      <el-input v-model="newCategoryName" placeholder="输入分类名称" />  
      <el-button type="primary" @click="addCategory">添加</el-button>  
    </el-card>  

    <el-card class="category-list-card">  
      <h2>分类列表</h2>  
      <el-table :data="categories" style="width: 100%">  
        <el-table-column prop="id" label="ID" width="100" />  
        <el-table-column prop="name" label="名称" />  
        <el-table-column label="操作" width="200">  
          <template #default="scope">  
            <el-button type="primary" @click="editCategory(scope.row)">编辑</el-button>  
            <el-button type="danger" @click="deleteCategory(scope.row.id)">删除</el-button>  
          </template>  
        </el-table-column>  
      </el-table>  
    </el-card>  
  </div>  
</template>  

<script>  
export default {  
  data() {  
    return {  
      newCategoryName: '',  
      categories: [  
        { id: 1, name: '分类 1' },  
        { id: 2, name: '分类 2' },  
        { id: 3, name: '分类 3' },  
      ],  
    };  
  },  
  methods: {  
    addCategory() {  
      const newCategory = { id: this.categories.length + 1, name: this.newCategoryName };  
      this.categories.push(newCategory);  
      this.newCategoryName = '';  
    },  
    editCategory(category) {  
      // 在这里实现编辑分类的逻辑  
      console.log('Edit category:', category);  
    },  
    deleteCategory(id) {  
      this.categories = this.categories.filter((category) => category.id !== id);  
    },  
  },  
};  
</script>  

<style>  
.category-management {  
  padding: 50px;  
}  

.new-category-card,  
.category-list-card {  
  margin-bottom: 20px;  
}  

.new-category-card .el-input,  
.new-category-card .el-button {  
  margin-top: 10px;  
}  
</style>