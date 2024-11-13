import { createRouter, createWebHistory } from 'vue-router'

import login from '../views/LoginIndex.vue'
import Audit from '../views/ProductAudit.vue'
import Product from '../views/ProductManage.vue'
import Merchant from '../views/MerchantManger.vue'
import User from '../views/UserManager.vue'
import Home from '../views/HomeIndex.vue'
import Order from '../views/OrderIndex.vue'


const routes = [
  { path:'/', component:login },
  {  
    path: '/home',   
    component: Home,  
    redirect: '/home/audit',
    children: [  
      { path: 'audit', component: Audit },  
      { path: 'product', component: Product },  
      { path: 'merchant', component: Merchant },  
      { path: 'user', component: User },
      { path: 'order',component: Order },
    ],  
  },  

]
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
