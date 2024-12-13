import { createApp } from 'vue'
import './style.css'
import 'vant/lib/toast/style'
import App from './App.vue'
import { Button, Tabbar, TabbarItem, NavBar, Icon, Toast } from 'vant';

const app = createApp(App);
app.use(Button);
app.use(Tabbar);
app.use(TabbarItem);
app.use(NavBar);
app.use(Toast);
app.use(Icon);

app.mount('#app')
