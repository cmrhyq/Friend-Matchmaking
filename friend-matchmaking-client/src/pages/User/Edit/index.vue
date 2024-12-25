<template>
  <van-form @submit="onSubmit">
    <van-cell-group inset>
      <!-- 通过 pattern 进行正则校验 -->
      <van-field
          v-model="editUser.value"
          :name="editUser.key"
          :label="editUser.name"
          :placeholder="`请输入${editUser.name}`"
          :rules="[{ required: true, message: '请填写内容' }]"/>
    </van-cell-group>
    <div style="margin: 16px;">
      <van-button round block type="primary" native-type="submit">
        提交
      </van-button>
    </div>
  </van-form>
</template>

<script setup lang="ts">
import {useRoute, useRouter} from "vue-router";
import {ref} from "vue";
import myAxios from "../../../plugin/myAxios.ts";
import {showFailToast, showSuccessToast} from "vant";

const route = useRoute()
const router = useRouter()
const editUser = ref({
  key: route.query.key,
  name: route.query.name,
  value: route.query.value
})

const onSubmit = async () => {
  const updateKey = editUser.value.key
  const updateValue = editUser.value.value
  myAxios.post('/user/update/my', {
    [updateKey as string]: updateValue
  }).then(res => {
    if (res.code === 0) {
      showSuccessToast("修改成功")
      router.back()
    } else {
      showFailToast(res.message)
    }
  }).catch(err => {
    console.log(err)
  })
};
</script>

<style scoped>

</style>
