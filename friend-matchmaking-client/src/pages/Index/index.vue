<template>
  <user-card :user-list="userList" />
</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";
import myAxios from "../../plugin/myAxios.ts";
import UserCard from '../../components/UserCard/index.vue'

const userList = ref([])

onMounted(async ()=>{
  const requestData = await myAxios.get("/user/recommend", {
    params: {
      pageSize: 8,
      pageNumber: 1
    }
  }).then(res => {
    console.log(res)
    return res.data?.records;
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
