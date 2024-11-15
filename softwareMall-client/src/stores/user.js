import { defineStore } from 'pinia'
import { ref } from 'vue'
export const useUserStore = defineStore('user', () => {
    const token = ref('')
    const username = ref('')
    const setToken = (newToken) => {
        token.value = newToken
    }
    const setUsername = (newUsername) => {
        username.value = newUsername
    }
    const removeToken = () => {
        token.value = ''
    }
    const removeUsername = () => {
        username.value = ''
    }
    return {
        token,
        username,

        setToken,
        setUsername,
        removeUsername,
        removeToken
        
    }
}, {
    persist: true
})