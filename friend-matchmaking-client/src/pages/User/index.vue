<template>
  <div class="user-content-box">
    <van-row>
      <van-col span="8" offset="8">
        <van-image
            round
            width="8rem"
            height="8rem"
            :src="user.userAvatar"
        />
      </van-col>
      <van-col span="24">
        <van-cell-group inset>
          <van-cell title="用户名" :value="user.username" is-link @click="toEdit('username', '用户名', user.username)"/>
          <van-cell title="账号名" :value="user.userAccount" is-link @click="toEdit('userAccount', '账号名', user.userAccount)"/>
          <van-cell title="手机号" :value="user.phone" is-link  @click="toEdit('phone', '手机号', user.phone)"/>
          <van-cell title="邮箱" :value="user.email" is-link  @click="toEdit('email', '邮箱', user.email)"/>
          <van-cell title="性别" :value="user.gender === 1 ? '男' : '女'" is-link  @click="toEdit('gender', '性别', user.gender)"/>
          <van-cell title="角色" :value="user.userRole"/>
          <van-cell title="加入时间" :value="user.createTime.toLocaleString()"/>
        </van-cell-group>
      </van-col>
      <van-col span="24" style="margin-top: 2px;padding: 0 1% 0 1%">
        <van-cell-group inset>
          <van-cell title="设置" is-link url=""/>
          <van-cell title="关于开发者" is-link url="https://github.com/cmrhyq"/>
        </van-cell-group>
      </van-col>
    </van-row>
  </div>
</template>

<script setup lang="ts">
import {useRouter} from "vue-router";

const user = {
  id: 1,
  username: "黄昱桥",
  userAccount: "AlanHuang",
  userAvatar: "https://fastly.jsdelivr.net/npm/@vant/assets/cat.jpeg",
  gender: 1,
  phone: "18164038469",
  email: "cmr@163.com",
  userRole: "用户",
  createTime: new Date(),
}

const router = useRouter()

/**
 * 进入编辑页面的方法
 * TODO 封装参数的字典，调用方法时不用写很长的参数
 * @param editKey
 * @param editName
 * @param editValue
 */
const toEdit = (editKey: string, editName: string, editValue: string) => {
  router.push({
    path: `/user/edit`,
    query: {
      key: editKey,
      name: editName,
      value: editValue,
    }
  })
}
</script>

<style scoped>
.user-content-box {
  border-radius: 15px;
  margin: 3% 1% 0 3%;
  padding: 2% 0 1% 0;
  background-image: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%);
  box-shadow: 0 1px 1px 0 rgb(255 255 255 / 20%), 0 2px 13px 0 rgb(146 146 146 / 19%);
}
</style>
