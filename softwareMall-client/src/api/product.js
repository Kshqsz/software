import request from '@/utils/request.js'

// search api
export const searchResultService = (searchInfo) => {
    return request.get("/product/search", {params: searchInfo});
}

