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

//查询分类
export const admingetAllCategory = () =>{
    return request.get("/admin/category", {})
}
//添加分类
export const adminInsertCategory = (name) =>{
    return request.post("/admin/category",{name})
}

//编辑分类
export const adminUpdateCategory = ({id,name}) =>{
    return request.put("/admin/category",{id,name})
}
//删除分类
export const admindeleteCategory = (id) => {
    return request.delete(`/admin/category/${id}`); // 注意这里的模板字符串
}
//查询所有用户
export const admingetalluser = () =>{
    return request.get("/admin/user", {})
}

//修改用户状态
export const adminChangeStatus = ({id,status}) =>{
    return request.put("/admin/user", {id,status})
}

//查询所有商家

//查询商家拥有商品


//查询所有订单

//根据订单编号查询订单