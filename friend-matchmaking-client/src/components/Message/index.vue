<template>
  <div>
    <h1>实时消息</h1>
    <button @click="connect" class="btn btn-primary">连接</button>
    <button @click="disconnect" class="btn btn-danger">断开</button>
    <ul class="list-group mt-3">
      <li v-for="(msg, index) in messages" :key="index" class="list-group-item">{{ msg }}</li>
    </ul>
  </div>
</template>

<script setup lang="ts">
import {ref} from "vue";

const messages = ref([]);
let eventSource = null;

const connect = () => {
  eventSource = new EventSource("http://localhost:8101/api/message/connect/199891")
  eventSource.onmessage = (event) => {
    const data = JSON.parse(event.data);
    messages.value.push(data.message);
    console.log(data.message);
  };
};

const disconnect = () => {
  if (eventSource) {
    eventSource.close();
  }
};

</script>

<style scoped>

</style>
