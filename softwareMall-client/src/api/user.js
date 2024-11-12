import request from '@/utils/request.js'

// login api
export const userLoginService = ({ username, password }) => {
    return request.post("/user/login", { username, password });
}