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
import {useRoute} from "vue-router";
import {ref} from "vue";

const route = useRoute()
const editUser = ref({
  key: route.query.key,
  name: route.query.name,
  value: route.query.value
})

const onSubmit = (values) => {
  console.log('submit', values);
  // TODO 把key，name，value提交到后台
};
</script>

<style scoped>

</style>
