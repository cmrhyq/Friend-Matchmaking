<template>
  <van-nav-bar
      v-model:title="navbarTitle"
      right-text="按钮"
      left-arrow
      @click-left="onClickLeft"
      @click-right="onClickRight">
    <template #right>
      <van-icon name="search" size="18" />
    </template>
  </van-nav-bar>
  <div id="content">
    <template v-if="active === 'index'">
      <Index/>
    </template>
    <template v-if="active === 'team'">
      <Team/>
    </template>
    <template v-if="active === 'user'">
      <User/>
    </template>
  </div>
  <van-tabbar v-model="active" @change="tabBarChange">
    <van-tabbar-item icon="home-o" name="index">主页</van-tabbar-item>
    <van-tabbar-item icon="friends-o" name="team">队伍</van-tabbar-item>
    <van-tabbar-item icon="manager-o" name="user">我的</van-tabbar-item>
  </van-tabbar>
</template>

<script setup lang="ts">
import { showToast } from 'vant';
import { ref } from "vue";
import ConversionTabBarTitle from "../../utils/CommonUtils"
import Index from "../../pages/Index/index.vue"
import Team from "../../pages/Team/index.vue"
import User from "../../pages/User/index.vue"

const active = ref("index");
const navbarTitle = ref("主页")

const tabBarChange = (index) => {
  navbarTitle.value = ConversionTabBarTitle(index)
}

const onClickLeft = () => {
  history.back()
  showToast('返回')
};
const onClickRight = () => {
  showToast('搜索')
};

</script>

<style scoped>

</style>
