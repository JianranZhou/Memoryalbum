<template>
  <div class="flex flex-col">
    <div class="post-header">
      <Breadcrumb style="font-size: 22px;" :current="t('menu.tags')" />
      <h1 class="post-title text-white uppercase">{{ t('menu.tags') }}</h1>
    </div>
    <div class="bg-ob-deep-800 px-14 py-16 rounded-2xl shadow-xl block" style="position: relative;">
      <el-button @click="add" type="error" plain v-if='userInfo'
        style="position: absolute;top: 30px;right: 250px;width: 100px;">Add Tags</el-button>
      <el-button @click="modifyShow" type="primary" plain v-if='userInfo && tags.length'
        style="position: absolute;top: 30px;right: 150px;width: 100px;" >Modify</el-button>
      <el-button @click="delShow" type="danger" plain v-if='userInfo && tags && tags.length'
        style="position: absolute;top: 30px;right: 50px;width: 100px;" >Delete</el-button>
      <TagList>
        <template v-if="tags != '' && tags.length > 0">
          <div v-for="tag in tags" :key="tag.id" style="display: flex;flex-direction: column;position: relative;">
            <TagItem :id="tag.id" :name="tag.tagName" :count="tag.count" size="xl" />
            <div style="position: absolute;top: -5px;right: 0;">
              <div v-if="flagModify">
                <el-button @click="modify(tag.id)" type="primary" plain v-if='userInfo'
                  style="width: 20px;height: 20px;padding: 2px;">
                  <el-icon style="width: 20px;">
                    <Edit />
                  </el-icon>
                </el-button>
              </div>
              <div v-if="flagDelete">
                <el-button @click="del(tag.id)" type="danger" plain v-if='userInfo'
                  style="width: 20px;height: 20px;padding: 2px;">
                  <el-icon style="width: 20px;">
                    <Delete />
                  </el-icon>
                </el-button>
              </div>
            </div>
          </div>
        </template>
      </TagList>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, onUnmounted, toRef, ref } from 'vue'
import Breadcrumb from '@/components/Breadcrumb.vue'
import { useI18n } from 'vue-i18n'
import { useTagStore } from '@/stores/tag'
import { TagList, TagItem } from '@/components/Tag'
import { useCommonStore } from '@/stores/common'
import api from '@/api/api'
import router from '@/router'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useUserStore } from '@/stores/user'
import axios from 'axios'

export default defineComponent({
  name: 'Tag',
  components: { Breadcrumb, TagList, TagItem },
  setup() {
    // 删除
    const del = (id: any) => {
      axios.post('/api/delete/tags', [id]).then(({ data }) => {
        fetchTags()
      })
    }
    const flagModify = ref(false)
    const flagDelete = ref(false)
    const modifyShow = () => {
      flagModify.value = true
      flagDelete.value = false
    }
    const delShow = () => {
      flagModify.value = false
      flagDelete.value = true
    }
    // 添加
    const add = () => {
      ElMessageBox.prompt('Please input your label', 'add tags', {
        confirmButtonText: 'OK',
        cancelButtonText: 'Cancel',
        inputErrorMessage: 'Invalid label',
        showCancelButton: false
      })
        .then(({ value }) => {
          ElMessage({
            type: 'success',
            message: `Your label is:${value}`,
          })
          axios.post('/api/admin/tags', { tagName: value }).then(({ data }) => {
            fetchTags()
          })
        })
        .catch(() => {
          ElMessage({
            type: 'info',
            message: 'Input canceled',
          })
        })

    }
    const commonStore = useCommonStore()
    const userStore = useUserStore()
    const { t } = useI18n()
    const tagStore = useTagStore()
    onMounted(() => {
      fetchTags()
    })
    onUnmounted(() => {
      commonStore.resetHeaderImage()
    })
    const fetchTags = () => {
      api.getAllTags().then(({ data }) => {
        tagStore.tags = data.data
      })
      // tagStore.tags = [
      //   {
      //     tagName: '123',
      //     count: "1"
      //   }
      // ]
    }
    // 修改
    const modify = (id: any) => {
      ElMessageBox.prompt('Please input your label', 'label', {
        confirmButtonText: 'OK',
        cancelButtonText: 'Cancel',
        inputErrorMessage: 'Invalid label',
        showCancelButton: false
      })
        .then(({ value }) => {
          ElMessage({
            type: 'success',
            message: `Your label is:${value}`,
          })
          axios.post('/api/admin/tags', { id: id, tagName: value }).then(({ data }) => {
            fetchTags()
          })
        })
        .catch(() => {
          ElMessage({
            type: 'info',
            message: 'Input canceled',
          })
        })
    }
    return {
      userInfo: toRef(userStore.$state, 'userInfo'),
      tags: toRef(tagStore.$state, 'tags'),
      t, modify, del, add, flagModify,flagDelete, modifyShow, delShow
    }
  }
})

</script>

<style lang="scss" scoped>
.el-message-box {
  width: 650px;
}
</style>
