<template>
  <el-card class="main-card">
    <div class="title">{{ this.$route.name }}</div>
    <div class="operation-container">


      <div style="margin-left: auto">
        <el-select
          clearable
          size="small"
          v-model="tagId"
          filterable
          placeholder="select tag"
          style="margin-right: 1rem; width: 180px">
          <el-option label="All" value="" />
          <el-option v-for="item in tags" :key="item.id" :label="item.tagName" :value="item.id" />
        </el-select>
        <el-input
          clearable
          v-model="keywords"
          prefix-icon="el-icon-search"
          size="small"
          placeholder="input title"
          style="width: 200px"
          @keyup.enter.native="searchArticles" />
        <el-button type="primary" size="small" icon="el-icon-search" style="margin-left: 1rem" @click="searchArticles">
          search
        </el-button>
      </div>
    </div>
    <el-table border :data="articles" @selection-change="selectionChange" v-loading="loading">
      <el-table-column type="selection" width="55" />
      <el-table-column prop="memoryTitle" label="title" align="center" width="200"/>
      <el-table-column prop="userName" label="user" align="center" width="200"/>

      <el-table-column prop="tagDTOs" label="tag"align="center">
        <template slot-scope="scope">
          <el-tag v-for="item of scope.row.tagDTOs" :key="item.tagId" style="margin-right: 0.2rem; margin-top: 0.2rem">
            {{ item.tagName }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column prop="createTime" label="create time" width="130" align="center">
        <template slot-scope="scope">
          <i class="el-icon-time" style="margin-right: 5px" />
          {{ scope.row.createTime | date }}
        </template>
      </el-table-column>


    </el-table>
    <el-pagination
      class="pagination-container"
      background
      @size-change="sizeChange"
      @current-change="currentChange"
      :current-page="current"
      :page-size="size"
      :total="count"
      :page-sizes="[10, 20]"
      layout="total, sizes, prev, pager, next, jumper" />


  </el-card>
</template>

<script>
export default {
  created() {
    this.current = this.$store.state.pageState.articleList
    this.listArticles()
    this.listTags()
  },
  data: function () {
    return {
      uploadHeaders: { Authorization: 'Bearer ' + sessionStorage.getItem('token') },
      loading: true,
      articles: [],
      articleIds: [],
      tags: [],
      keywords: null,
      tagId: null,
      isDelete: 0,
      current: 1,
      size: 10,
      count: 0
    }
  },
  methods: {
    selectionChange(articles) {
      this.articleIds = []
      articles.forEach((item) => {
        this.articleIds.push(item.id)
      })
    },
    searchArticles() {
      this.current = 1
      this.listArticles()
    },


    sizeChange(size) {
      this.size = size
      this.listArticles()
    },
    currentChange(current) {
      this.current = current
      this.$store.commit('updateArticleListPageState', current)
      this.listArticles()
    },

    listArticles() {
      this.axios
        .get('/api/admin/memory', {
          params: {
            current: this.current,
            size: this.size,
            keywords: this.keywords,
            tagId: this.tagId,
            isDelete: this.isDelete
          }
        })
        .then(({ data }) => {
          this.articles = data.data.records
          this.count = data.data.count
          this.loading = false
        })
    },

    listTags() {
      this.axios.get('/api/admin/tags/search').then(({ data }) => {
        this.tags = data.data
      })
    }
  },
  watch: {
    type() {
      this.current = 1
      this.listArticles()
    },
    tagId() {
      this.current = 1
      this.listArticles()
    },
    isDelete() {
      this.current = 1
      this.listArticles()
    }
  }
}
</script>

<style scoped>
.operation-container {
  margin-top: 1.5rem;
}
.article-status-menu {
  font-size: 14px;
  margin-top: 40px;
  color: #999;
}
.article-status-menu span {
  margin-right: 24px;
}
.status {
  cursor: pointer;
}
.active-status {
  cursor: pointer;
  color: #333;
  font-weight: bold;
}
.article-cover {
  position: relative;
  width: 100%;
  height: 90px;
  border-radius: 4px;
}
.article-cover::after {
  content: '';
  background: rgba(0, 0, 0, 0.3);
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
}
.article-status-icon {
  color: #fff;
  font-size: 1.5rem;
  position: absolute;
  right: 1rem;
  bottom: 1.4rem;
}
</style>
