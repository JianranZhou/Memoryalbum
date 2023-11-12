<template>
  <el-form :model="form" label-width="120px" style="background-color: #fff;padding: 30px;border-radius: 30px;">
    <el-form-item label="Memory Title">
      <el-input style="width: 20vw;" v-model="form.memoryTitle" />
    </el-form-item>

    <!-- 上传图片 -->
    <el-form-item label="Memory Image">
      <el-upload :limit="1" v-model:file-list="fileList1" action="/api/admin/memory/images" list-type="picture-card"
        :on-success="uploadImage1" :on-remove="handleRemove1">
        <el-icon>
          <Plus />
        </el-icon>
      </el-upload>
      <el-dialog v-model="dialogVisible1">
        <img w-full :src="dialogImageUrl1" alt="Preview Image" />
      </el-dialog>
    </el-form-item>


    <el-form-item label="Tags">
      <el-select multiple v-model="form.tagNames" placeholder="please select">
        <el-option v-for="item in tagStore.tags" :key="item.tagName" :label="item.tagName" :value="item.tagName" />
      </el-select>

      <el-button @click="add" type="error" plain style="width: 100px;margin-left: 10px">Add Tags</el-button>
    </el-form-item>

    <!-- 上传视频 -->
    <el-form-item label="Memory Video">
      <el-upload accept="*video/mp4" v-model:file-list="fileList2" class="upload-demo" action="/api/admin/memory/images"
        multiple :on-success="uploadImage2" :on-remove="handleRemove2" :before-remove="beforeRemove1"
        :on-exceed="handleExceed1">
        <el-button type="primary">Click to upload</el-button>
        <template #tip>
          <div class="el-upload__tip">
            video files with a size less than 50M.
          </div>
        </template>
      </el-upload>
    </el-form-item>

    <!-- 上传音频 -->
    <el-form-item label="Memory Audio">
      <el-upload accept="*video/mp3" v-model:file-list="fileList3" class="upload-demo" action="/api/admin/memory/images"
        multiple :on-success="uploadImage3" :on-remove="handleRemove3" :before-remove="beforeRemove2"
        :on-exceed="handleExceed2">
        <el-button type="primary">Click to upload</el-button>
        <template #tip>
          <div class="el-upload__tip">
            audio files with a size less than 20M.
          </div>
        </template>
      </el-upload>
    </el-form-item>


<!--    <el-form-item label="Location">
      <el-select v-model="form.region" placeholder="please select">
        <el-option label="LOCATION_A" value="shanghai" />
        <el-option label="LOCATION_B" value="beijing" />
      </el-select>
    </el-form-item>-->
    <el-form-item label="Detail">
      <el-input style="width: 20vw;" v-model="form.memoryContent" :rows="3" type="textarea" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">Create</el-button>
      <el-button>Cancel</el-button>
    </el-form-item>
  </el-form>
</template>

<script lang="ts" setup>
import { defineComponent, onMounted, reactive } from 'vue'
import { ref } from 'vue'
import { Plus } from '@element-plus/icons-vue'
import axios from 'axios'

import { ElMessage, ElMessageBox, UploadProps, UploadUserFile } from 'element-plus'
import api from '@/api/api'
import { useTagStore } from '@/stores/tag'

// do not use same name with ref
const form = reactive({
  memoryTitle: '',
  region: '',
  tagNames: '',
  memoryContent: '',
  memoryCover: ''
})

let tagStore = useTagStore()

const fetchTags = () => {
  api.getAllTags().then(({ data }) => {
    tagStore.tags = data.data
  })
}
onMounted(() => {
  fetchTags()
})

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

const onSubmit = () => {
  let params = {
    memoryTitle: form.memoryTitle,
    region: form.region,
    tagNames: form.tagNames,
    memoryContent: form.memoryContent,
    memoryCover: dialogImageUrl1 && dialogImageUrl1.value,
    audioUrl: dialogImageUrl2 && dialogImageUrl2.value,
    videoUrl: dialogImageUrl3 && dialogImageUrl3.value,
  }
  axios.post('/api/admin/memory', params).then(({ data }) => {
    if (data.flag) {
      ElMessage.success(
        `success`)
    } else {
      ElMessage.warning(
        data.msg)
    }
  })
  //console.log(form)
  console.log('submit!')
}

const fileList1 = ref<UploadUserFile[]>([

])
const fileList2 = ref<UploadUserFile[]>([

])
const fileList3 = ref<UploadUserFile[]>([

])
const handleExceed1: UploadProps['onExceed'] = (files, uploadFiles) => {
  ElMessage.warning(
    `The limit is 3, you selected ${files.length} files this time, add up to ${files.length + uploadFiles.length
    } totally`
  )
}
const beforeRemove1: UploadProps['beforeRemove'] = (uploadFile, uploadFiles) => {
  return ElMessageBox.confirm(
    `Cancel the transfer of ${uploadFile.name} ?`
  ).then(
    () => true,
    () => false
  )
}
const handleExceed2: UploadProps['onExceed'] = (files, uploadFiles) => {
  ElMessage.warning(
    `The limit is 3, you selected ${files.length} files this time, add up to ${files.length + uploadFiles.length
    } totally`
  )
}
const beforeRemove2: UploadProps['beforeRemove'] = (uploadFile, uploadFiles) => {
  return ElMessageBox.confirm(
    `Cancel the transfer of ${uploadFile.name} ?`
  ).then(
    () => true,
    () => false
  )
}
const dialogImageUrl1 = ref('')
const dialogImageUrl2 = ref('')
const dialogImageUrl3 = ref('')
const dialogVisible1 = ref(false)
const dialogVisible2 = ref(false)
const dialogVisible3 = ref(false)

const handleRemove1: UploadProps['onRemove'] = (uploadFile, uploadFiles) => {
  console.log(uploadFile, uploadFiles)
}
const handleRemove2: UploadProps['onRemove'] = (uploadFile, uploadFiles) => {
  console.log(uploadFile, uploadFiles)
}
const handleRemove3: UploadProps['onRemove'] = (uploadFile, uploadFiles) => {
  console.log(uploadFile, uploadFiles)
}

const uploadImage1: UploadProps['onSuccess'] = (response) => {
  dialogImageUrl1.value = response.data
  dialogVisible1.value = true
}

const uploadImage2: UploadProps['onSuccess'] = (response) => {
  dialogImageUrl2.value = response.data
  dialogVisible2.value = true
}

const uploadImage3: UploadProps['onSuccess'] = (response) => {
  dialogImageUrl3.value = response.data
  dialogVisible3.value = true
}



</script>

<style lang="css" scoped></style>
