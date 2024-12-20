<template>
  <form action="/">
    <van-search
        v-model="searchValue"
        show-action
        placeholder="请输入搜索关键词"
        @search="onSearch"
        @clear="onClean">
      <template #action>
        <div @click="onSearchToResult">搜索</div>
      </template>
    </van-search>
  </form>
  <van-row class="content">
    <van-col span="24" style="padding: 0 2% 0 2%">
      <van-row justify="space-around" align="center" wrap>
        <van-col span="4" v-for="tag in activeIds">
          <van-tag closeable size="medium" type="primary" @close="doClose(tag)">
            {{ tag }}
          </van-tag>
        </van-col>
      </van-row>
    </van-col>
    <van-col span="24">
      <van-divider content-position="left">请选择标签</van-divider>
    </van-col>
    <van-col span="24" style="padding: 0 2% 2% 2%">
      <van-tree-select
          v-model:active-id="activeIds"
          v-model:main-active-index="activeIndex"
          :items="tagList"
          style="background-color: white"/>
    </van-col>
  </van-row>

</template>

<script setup lang="ts">
import { ref } from "vue";
import {useRouter} from "vue-router";

const router = useRouter()
const searchValue = ref('');
// 已选标签
const activeIds = ref([]);
const activeIndex = ref();

let originTagList = [
  {
    text: '浙江',
    children: [
      { text: '杭州', id: '杭州' },
      { text: '温州', id: '温州' },
      { text: '宁波', id: '宁波' },
    ],
  },
  {
    text: '江苏',
    children: [
      { text: '南京', id: '南京' },
      { text: '无锡', id: '无锡' },
      { text: '徐州', id: '徐州' },
    ],
  },
]
let tagList = ref(originTagList);

/**
 * 按下enter按钮事件
 * 重置标签下方的树形选择器
 * @param val
 */
const onSearch = (val) => {
  tagList.value = tagList.value.map(item => {
    const children = item.children.filter(row => row.text === searchValue.value)
    return {
      ...item,
      children: children,
    }
  });
};

/**
 * 点击搜索按钮事件
 */
const onSearchToResult = () =>{
  router.push({
    path: "/search/result",
    query: {
      tags: activeIds.value
    }
  })
}

/**
 * 点击标签关闭事件
 * @param tag
 */
const doClose = (tag) => {
  activeIds.value =  activeIds.value.filter((item) => {
    return item !== tag;
  });
};

/**
 * 点击搜索框内清除按钮事件
 */
const onClean = () => {
  tagList.value = originTagList
};

</script>

<style scoped>
.content{
  background-color: white;
}
::v-deep div.van-sidebar-item--select, .van-sidebar-item--select:active {
  background-color: #f7f8fa !important;
}
</style>
