import { createApp } from 'vue'
import './style.css'
import 'vant/lib/toast/style'
import App from './App.vue'
import routes from "./config/route.ts";
import * as VueRouter from 'vue-router';
import {
    Button,
    Tabbar,
    TabbarItem,
    NavBar,
    Icon,
    Toast,
    Search,
    Tag,
    Grid,
    GridItem,
    Divider,
    Col,
    Row,
    TreeSelect
} from 'vant';

const app = createApp(App);
app.use(Button);
app.use(Tabbar);
app.use(TabbarItem);
app.use(NavBar);
app.use(Toast);
app.use(Icon);
app.use(Search);
app.use(Tag);
app.use(Grid);
app.use(GridItem);
app.use(Divider);
app.use(Col);
app.use(Row);
app.use(TreeSelect);

const router = VueRouter.createRouter({
    history: VueRouter.createWebHashHistory(),
    routes
})

app.use(router)
app.mount('#app')
