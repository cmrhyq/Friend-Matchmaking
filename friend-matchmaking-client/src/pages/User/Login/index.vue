<template>
  <van-row :gutter="[20, 20]">
    <van-col span="24">
    </van-col>
    <van-col span="24">
      <van-form @submit="onSubmit">
        <van-cell-group inset>
          <van-field
              v-model="userAccount"
              name="账号"
              label="账号"
              placeholder="账号"
              :rules="[{ required: true, message: '请填写账号' }]"
          />
          <van-field
              v-model="userPassword"
              type="password"
              name="密码"
              label="密码"
              placeholder="密码"
              :rules="[{ required: true, message: '请填写密码' }]"
          />
        </van-cell-group>
        <div style="margin: 16px;">
          <van-button round block type="primary" native-type="submit">
            提交
          </van-button>
        </div>
      </van-form>
    </van-col>
  </van-row>
</template>

<script setup lang="ts">
import {ref} from "vue";
import myAxios from "../../../plugin/myAxios.ts";
import {showFailToast, showSuccessToast} from "vant";
import {useRouter} from "vue-router";

let userAccount = ref('')
let userPassword = ref('')
const router = useRouter()

const onSubmit = () => {
  myAxios.post('/user/login', {
    userAccount: userAccount.value,
    userPassword: userPassword.value
  }).then(res => {
    console.log(res)
    if (res.code === 0) {
      showSuccessToast("登录成功")
      router.back()
    } else {
      showFailToast(res.message)
    }
  }).catch(err => {
    console.log(err)
  })
}
</script>

<style scoped>

</style>
