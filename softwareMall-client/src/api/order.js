import request from '@/utils/request.js'

export const orderAddService = ({ userId, productId, merchantId}) => {
    return request.post("/order/add", {userId, productId, merchantId})
}

export const orderCancelService = (orderNumber) => {
    return request.post(`/order/cancel/${orderNumber}`)
}

