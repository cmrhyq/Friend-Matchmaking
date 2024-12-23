import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite';
import Components from 'unplugin-vue-components/vite';
import { VantResolver } from '@vant/auto-import-resolver';

// https://vitejs.dev/config/
export default defineConfig({
  // 项目构建过程配置
  build: {
    // 打包输出目录
    outDir: 'dist',
    // 是否压缩代码
    minify: false,
    // 是否生成sourcemap
    sourcemap: true,
  },

  plugins: [
    vue(),
    AutoImport({
      resolvers: [VantResolver()],
    }),
    Components({
      resolvers: [VantResolver()],
    }),
  ],
  // 配置服务端
  server: {
    // 端口号
    port: 4000,
    // 是否自动打开浏览器
    open: true,
    // 是否开启https
    https: false,
    // 监听所有地址
    host: true,
    // 跨域代理，为开发服务器配置CORS
    cors: true,
    // 代理配置
    proxy: {
      // '/api': {
      //     target: 'URL_ADDRESS',
      //     changeOrigin: true,
      //     rewrite: (path) => path.replace(/^\/api/, ''),
      // },
    },
  },
})
