<template>
  <div class="main-container">
    <van-card
        v-for="user in userList"
        :key="user.id"
        :tag="user.userGender === 0 ? '男' : '女'"
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
    <van-empty v-if="userList.length < 1" image="search" description="没有找到内容" />
  </div>
</template>

<script setup lang="ts">
import {useRoute} from "vue-router";
import {onMounted, ref} from "vue";
import myAxios from "../../../plugin/myAxios";
import {UserType} from "../../../models/user";

const route = useRoute()
const tags = route.query.tags

const userList = ref([])

onMounted(async ()=>{
  const requestData: UserType[] = await myAxios.get("/user/search/tags", {
    params: {
      tags: tags
    }
  }).then(res => {
    console.log(res)
    return res.data?.data;
  }).catch(err => {
    console.log(err)
  })
  if (requestData) {
    requestData.forEach(user => {
      user.tags = JSON.parse(user.tags)
    })
    userList.value = requestData
  }
})

</script>

<style scoped>
.main-container {
  background-color: #ffffff;
  --van-card-background: #ffffff;
  margin: 2% 0 0 0;
}
</style>
