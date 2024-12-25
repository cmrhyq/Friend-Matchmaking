import {getCurrentUserState, setCurrentUserState} from "../utils/state/user.ts";
import myAxios from "../plugin/myAxios.ts";
import {showFailToast} from "vant";
import {useRouter} from "vue-router";

export const getCurrentUser = async () => {
    const router = useRouter()
    // 小系统可以不用缓存，用缓存会导致用户更新个人信息后返回个人信息页面时个人信息显示的旧数据
    // const currentUser = getCurrentUserState();
    //
    // if (currentUser) {
    //     return currentUser;
    // }

    const res = await myAxios.get(`/user/get/login`);
    if (res.code === 0){
        setCurrentUserState(res.data);
        return res.data;
    } else if (res.code === 40100){
        showFailToast(res.message);
        await router.push("/user/login")
    } else {
        showFailToast("系统异常")
        return null;
    }

}
