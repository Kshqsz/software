import request from '@/utils/request.js'

// login api
export const adminLoginService = ({ username, password }) => {
    return request.post("/admin/login", { username, password });
}

//查询需要审核的软件


//处理审核，同意或拒绝



// 分页查询所有商品


//下架商品

//分页条件查询商品

//分页分类查询商品











//查询商家拥有商品


//查询所有订单

//根据订单编号查询订单