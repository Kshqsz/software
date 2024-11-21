import request from '@/utils/request.js'

export const productAddService = (product) => {
    return request.post("/product/add", product)
}