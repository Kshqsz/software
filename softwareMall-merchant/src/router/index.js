import { createRouter, createWebHistory } from 'vue-router'

import login from '../views/login/LoginIndex.vue'

import Product from '../views/product/ProductManage.vue'
import Home from '../views/home/HomeIndex.vue'
import Order from '../views/order/OrderIndex.vue'
import Merchant from '../views/merchant/MerchantIndex.vue'
import Count from '../views/count/CountIndex.vue'

const routes = [
  {path:'/',component:login},
  {  
    path: '/home',   
    component: Home,  
    redirect: '/home/product',
    children: [        
      { path: 'product', component: Product },       
      { path: 'order',component: Order },
      {path: 'merchant',component:Merchant},
      {path:'count',component:Count}

    ],  
  },  

]
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
