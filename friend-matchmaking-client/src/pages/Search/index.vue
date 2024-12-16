<template>
  <form action="/">
    <van-search
        v-model="searchValue"
        show-action
        placeholder="请输入搜索关键词"
        @search="onSearch"
        @cancel="onCancel"
    />
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

const doClose = (tag) => {
  activeIds.value =  activeIds.value.filter((item) => {
    return item !== tag;
  });
};

const onSearch = (val) => {
  tagList.value = originTagList.map(parentTag => {
    const tempChildren = [...parentTag.children]
    const tempParentTag = cloneDeep(parentTag)
    console.log(tempParentTag)
    tempParentTag.children = tempChildren.filter(item => {
      console.log(item)
      item.text.includes(searchValue.value)
    })
    return tempParentTag;
  })
};

const onCancel = () => {
  showToast('取消')
};

/**
 * 深拷贝
 * @param source
 */
const cloneDeep = (source) => {
  let target = {};
  for(let key in source) {
    if (Object.prototype.hasOwnProperty.call(source, key)) {
      if (typeof source[key] === 'object') {
        target[key] = cloneDeep(source[key]); // 注意这里
      } else {
        target[key] = source[key];
      }
    }
  }
  return target;
}

</script>

<style scoped>
.content{
  background-color: white;
}
::v-deep div.van-sidebar-item--select, .van-sidebar-item--select:active {
  background-color: #f7f8fa !important;
}
</style>
