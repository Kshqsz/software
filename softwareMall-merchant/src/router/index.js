import { createRouter, createWebHistory } from 'vue-router'

import login from '../views/LoginIndex.vue'

import Product from '../views/ProductManage.vue'
import Home from '../views/HomeIndex.vue'
import Order from '../views/OrderIndex.vue'
import Merchant from '../views/MerchantIndex.vue'


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
    ],  
  },  

]
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
