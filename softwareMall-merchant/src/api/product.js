import request from '@/utils/request.js'

export const productAddService = (product) => {
    return request.post("/product/add", product)
}
export const productGetAllByMerchantIdService = (merchantId) => {
    return request.get(`/product/all/${merchantId}`)
}

export const productUpdateService = (product) => {
    return request.put("/product", product)
}