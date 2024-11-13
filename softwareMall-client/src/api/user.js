import request from '@/utils/request.js'

// login api
export const userLoginService = ({ username, password }) => {
    return request.post("/user/login", { username, password });
}

// register api
export const userRegisterService = ({ username, password, rePassword }) => {
    return request.post("/user/register", { username, password, rePassword})
}