<template>
  <div class="main-container">
    <van-card
        v-for="user in userList"
        :key="user.id"
        :tag="user.gender === 0? '男' : '女'"
        :desc="user.userProfile"
        :title="user.username"
        :thumb="user.userAvatar">
      <template #tags>
        <van-tag plain type="primary" v-for="tag in user.tags" style="margin: 1% 1% 0 0">{{ tag }}</van-tag>
      </template>
      <template #footer>
        <van-button size="mini">联系他</van-button>
      </template>
    </van-card>
  </div>
</template>

<script setup lang="ts">
import {useRoute} from "vue-router";
import {onMounted, ref} from "vue";
import myAxios from "../../../plugin/myAxios";
import qs from "qs";

const route = useRoute()
const tags = route.query.tags

const mockUser = {
  id: 1,
  username: "黄昱桥",
  userAccount: "AlanHuang",
  userAvatar: "https://fastly.jsdelivr.net/npm/@vant/assets/cat.jpeg",
  gender: 1,
  phone: "18164038469",
  email: "cmr@163.com",
  userProfile: "Software Developer",
  userRole: "用户",
  tags: ["前端", "后端", "数据库"],
  createTime: new Date(),
}

const userList = ref([mockUser])

onMounted(()=>{
  myAxios.get("/user/search/tags", {
    params: {
      tags: tags
    }
  }).then(res => {
    console.log(res)
  }).catch(err => {
    console.log(err)
  })
})

</script>

<style scoped>
.main-container {
  background-color: #ffffff;
  --van-card-background: #ffffff;
  margin: 2% 0 0 0;
}
</style>
