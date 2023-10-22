<template>
  <div>
    <div class="post-header" style="font-size: 22px;">
      <div style="color: #fff;">Home> Memorylist</div>
      <h1 class="post-title text-white uppercase">memorylist</h1>
    </div>

    <div style="padding: 30px;background-color: #fff;border-radius: 30px;">

      <div style="display: inline-block;padding-bottom: 6px;border-bottom: 5px solid orange;font-size: 26px;">Memory List
      </div>
      <el-button @click="memory" type="primary" v-if='userInfo'
        style="position: absolute;top: 160px;right: 30px;width: 100px;">Add
        Memory</el-button>
      <div style="display: flex;margin-top: 30px;width: 20vw;" v-if='userInfo'>
        <el-input v-model="input" placeholder="Please input" />
        <el-button style="width: 60px;" type="primary" @click="search">Search</el-button>
      </div>
      <div style="display: flex;margin-top: 30px;flex-wrap: wrap;justify-content: space-between;">
        <div v-for="(item, index) in articles" :key="index"
          style="width: 20vw;background-color: #fff;border-radius: 10px;overflow: hidden;">
          <img style="width: 100%;height: 40%;" src="../assets/default-cover.jpg" alt="">
          <div style="font-size: 26px;font-weight: 700;margin: 10px 0;" @click="toArticle(item.id)">{{ item.memoryTitle }}
          </div>
          <div>
            {{ item.memoryContent }}
          </div>
          <div style="display: flex;margin-top: 10px;">
            <img style="width: 40px;height: 40px;border-radius: 999px;margin: 10px;" src="../assets/default-cover.jpg"
              alt="">
            <div style="line-height: 60px;font-size: 20px;margin: 0 10px;">{{ item.author.nickname }}</div>
            <div style="line-height: 60px;font-size: 18px;color: #ccc;">{{ item.createTime }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import router from '@/router'
import { ref, reactive, onMounted, toRef } from 'vue'
import api from '@/api/api'
import markdownToHtml from '@/utils/markdown'
import { useArticleStore } from '@/stores/article'
import { useUserStore } from '@/stores/user'

export default ({
  name: 'memoryList',
  setup() {
    const input = ref('')
    const articleStore = useArticleStore()
    const userStore = useUserStore()
    onMounted(() => {
      search()
    })
    const toArticle = (id: any) => {
      let isAccess = false
      router.push({ path: '/articles/' + id })
    }
    const search = () => {
      api.getArticles({ keywords: input.value })
        .then(({ data }) => {
          if (data.flag) {
            data.data.records.forEach((item: any) => {
              item.memoryContent = markdownToHtml(item.memoryContent)
                .replace(/<\/?[^>]*>/g, '')
                .replace(/[|]*\n/, '')
                .replace(/&npsp;/gi, '')
            })
            articleStore.articles = data.data.records
            console.log(articleStore.articles)
          }
        })
    }
    const memory = () => {
      router.push("/memory")
    }
    return {
      userInfo: toRef(userStore.$state, 'userInfo'),
      articles: toRef(articleStore.$state, 'articles'),
      input,
      search,
      toArticle,
      memory
    }
  }
})
</script>
<style></style>