import Index from "../pages/Index/index.vue";
import Team from "../pages/Team/index.vue";
import User from "../pages/User/index.vue";
import Edit from "../pages/User/Edit/index.vue";
import Search from "../pages/Search/index.vue";

const routes = [
    {path: '/', component: Index},
    {path: '/team', component: Team},
    {path: '/user', component: User},
    {path: '/user/edit', component: Edit},
    {path: '/search', component: Search},
]

export default routes;
