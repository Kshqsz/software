import request from '@/utils/request.js'

// login api
export const adminLoginService = ({ username, password }) => {
    return request.post("/admin/login", { username, password });
}