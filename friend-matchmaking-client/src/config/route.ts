import Index from "../pages/Index/index.vue";
import Team from "../pages/Team/index.vue";
import User from "../pages/User/index.vue";
import UserEdit from "../pages/User/Edit/index.vue";
import UserLogin from "../pages/User/Login/index.vue";
import Search from "../pages/Search/index.vue";
import SearchResult from "../pages/Search/Result/index.vue";

const routes = [
    {path: '/', component: Index},
    {path: '/team', component: Team},
    {path: '/user', component: User},
    {path: '/user/edit', component: UserEdit},
    {path: '/user/login', component: UserLogin},
    {path: '/search', component: Search},
    {path: '/search/result', component: SearchResult},
]

export default routes;
