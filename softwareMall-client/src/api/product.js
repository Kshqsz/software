import request from '@/utils/request.js'

// search api
export const productSearchService = (searchInfo) => {
    return request.get("/product/search", {params: {searchInfo: searchInfo }});
}

export const productGetByIdService = (id) => {
    return request.get(`/product/${id}`)
}
