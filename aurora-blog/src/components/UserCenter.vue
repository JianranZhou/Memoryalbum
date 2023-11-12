<template>
  <el-drawer v-model="visible" direction="rtl" :with-header="false" :before-close="handleClose">
    <span class="text font-semibold text-2xl">Personal Center</span>
    <template v-if="userInfo !== ''">
      <span class="text font-medium"></span>
      <div class="max-w-full mt-10">
        <button id="pick-avatar" @click="showCropper = true">
          <el-avatar :size="110" :src="userInfo.avatar" class="ml-40" />
        </button>
        <avatar-cropper v-model="showCropper" @uploaded="handleSuccess" trigger="#pick-avatar" :request-options="options"
          upload-url="/api/users/avatar" />
        <el-form>
          <el-form-item model="userInfo" label="username:" class="mt-5">
            <el-input v-model="userInfo.nickname" />
          </el-form-item>
          <el-form-item model="userInfo" label="sex:" class="mt-5">
            <el-select v-model="userInfo.website" placeholder="please select your sex">
              <el-option label="Male" value="Male" />
              <el-option label="Female" value="Female" />
            </el-select>
            <!-- <el-input v-model="userInfo.website" placeholder="Please add https:// or http://" /> -->
          </el-form-item>
          <el-form-item model="userInfo" label="description:" class="mt-5">
            <el-input v-model="userInfo.intro" placeholder="Introduce youself" />
          </el-form-item>
          <button @click="commit" type="button" id="submit-button"
            class="mt-5 w-20 text-white p-2 rounded-lg transition transform hover:scale-105 flex float-right">
            <span class="text-center flex-grow commit">Submit</span>
          </button>
        </el-form>
      </div>
    </template>
    <br />
    <br />
  </el-drawer>

</template>

<script lang="ts">
import { defineComponent, toRef, ref, reactive, toRefs, getCurrentInstance, computed, onMounted } from 'vue'
import { useUserStore } from '@/stores/user'
import AvatarCropper from 'vue-avatar-cropper'
import api from '@/api/api'

export default defineComponent({
  name: 'UserCenter',
  components: { AvatarCropper },
  setup() {
    const proxy: any = getCurrentInstance()?.appContext.config.globalProperties
    const userStore = useUserStore()

    let showCropper = ref(false)
    const handleClose = () => {
      userStore.userVisible = false
    }


    const handleSuccess = (data: any) => {
      data.response.json().then((data: any) => {
        if (data.flag) {
          userStore.userInfo.avatar = data.data
          proxy.$notify({
            title: 'Success',
            message: 'Success',
            type: 'success'
          })
        } else {
          proxy.$notify({
            title: 'Error',
            message: data.message,
            type: 'error'
          })
        }
      })
    }

    const commit = () => {
      let params = {
        nickname: userStore.userInfo.nickname,
        website: userStore.userInfo.website,
        intro: userStore.userInfo.intro
      }
      api.submitUserInfo(params).then(({ data }) => {
        if (data.flag) {
          proxy.$notify({
            title: 'Success',
            message: 'Success',
            type: 'success'
          })
        } else {
          proxy.$notify({
            title: 'Error',
            message: data.message,
            type: 'error'
          })
        }
      })
    }


    return {
      userInfo: toRef(userStore.$state, 'userInfo'),
      visible: toRef(userStore.$state, 'userVisible'),
      showCropper,
      handleClose,
      handleSuccess,
      commit,
      options: computed(() => {
        return {
          method: 'POST',
          headers: {
            Authorization: 'Bearer ' + userStore.token
          }
        }
      })
    }
  }
})
</script>
<style lang="scss" scoped>
#submit-button {
  outline: none;
  background: #0fb6d6;
}

.text {
  color: var(--text-normal);
  cursor: pointer;
}

#pick-avatar {
  outline: none;
}
</style>
<style lang="scss">
.el-form-item__label {
  text-align: left;
  width: 70px;
  color: var(--text-normal) !important;
}

.el-input__inner {
  color: var(--text-normal) !important;
  background-color: var(--background-primary-alt) !important;
}

.el-input__wrapper {
  background: var(--background-primary-alt) !important;
}

.bangding-button {
  outline: none !important;
}
</style>
