import request from '@/utils/request.js'


// 查询所有商品

export const getAllProduct = () =>{
  return request.get("/product/all")
}
//下架商品

export const updateStatus = (id) => {
  return request.put("/product",{id})
}

