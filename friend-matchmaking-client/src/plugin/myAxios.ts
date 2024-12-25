import axios, {AxiosInstance} from "axios";
import qs from "qs";

const myAxios:AxiosInstance = axios.create({
    baseURL: "http://localhost:8101/api",
    // timeout: 1000,
    // 让请求携带会话cookie
    withCredentials: true,
    // paramsSerializer序列化，解决传递的参数是list时，实际请求时参数名后带一个[]的问题
    paramsSerializer: params => {
        return qs.stringify(params, {indices: false})
    }
});

myAxios.interceptors.request.use(function (config) {
    // Do something before request is sent
    return config;
}, function (error) {
    // Do something with request error
    return Promise.reject(error);
});

myAxios.interceptors.response.use(function (response) {
    // Any status code that lie within the range of 2xx cause this function to trigger
    // Do something with response data
    return response.data;
}, function (error) {
    // Any status codes that falls outside the range of 2xx cause this function to trigger
    // Do something with response error
    return Promise.reject(error);
})

export default myAxios;
