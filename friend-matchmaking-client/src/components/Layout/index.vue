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
    <router-view></router-view>
  </div>
  <van-tabbar v-model="active" @change="tabBarChange" route>
    <van-tabbar-item icon="home-o" name="index" to="/">主页</van-tabbar-item>
    <van-tabbar-item icon="friends-o" name="team" to="/team">队伍</van-tabbar-item>
    <van-tabbar-item icon="manager-o" name="user" to="/user">我的</van-tabbar-item>
  </van-tabbar>
</template>

<script setup lang="ts">
import { ref } from "vue";
import {ConversionTabBarTitle} from "../../utils/CommonUtils"
import { useRouter } from "vue-router";

const active = ref("index");
const navbarTitle = ref("主页")
const router = useRouter()

const tabBarChange = (index) => {
  navbarTitle.value = ConversionTabBarTitle(index)
}

const onClickLeft = () => {
  history.back()
};
const onClickRight = () => {
  router.push("/search")
};

</script>

<style scoped>

</style>
