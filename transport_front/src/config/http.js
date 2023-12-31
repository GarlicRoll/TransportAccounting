import axios from "axios"

export default {
    install(Vue, options) {
        Vue.prototype.$http = axios.create({
            baseUrl: "https://localhost:8080",
            headers: options.headers || null,
            withCredentials: true // gives all cookies to all methods
        })
    }
}