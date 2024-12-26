<template>
  <div class="main-container">
    <van-card
        v-for="user in props.userList"
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
    <van-empty v-if="!props.userList || props.userList.length < 1" image="search" description="没有数据" />
  </div>
</template>

<script setup lang="ts">
import {UserType} from "../../models/user";

interface UserCardProps {
  userList: UserType[]
}

const props = withDefaults(defineProps<UserCardProps>(),{
  userList: []
});
</script>

<style scoped>

</style>
