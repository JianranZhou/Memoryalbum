<template>
  <el-card class="main-card">
    <div class="title">{{ this.$route.name }}</div>
    <div class="operation-container">
      <div style="margin-left: auto">

        <el-input
          v-model="keywords"
          prefix-icon="el-icon-search"
          size="small"
          style="width: 200px"
          @keyup.enter.native="searchUsers" />
        <el-button type="primary" size="small" icon="el-icon-search" style="margin-left: 1rem" @click="searchUsers">
          Search
        </el-button>
      </div>
    </div>
    <el-table border :data="userList" v-loading="loading">
      <el-table-column prop="linkAvatar" label="avatar" align="center" width="100">
        <template slot-scope="scope">
          <img :src="scope.row.avatar" width="40" height="40" />
        </template>
      </el-table-column>
      <el-table-column prop="nickname" label="name" align="center" width="140" />

      <el-table-column prop="roles" label="role" align="center">
        <template slot-scope="scope">
          <el-tag v-for="(item, index) of scope.row.roles" :key="index" style="margin-right: 4px; margin-top: 4px">
            {{ item.roleName }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="memoryCount" label="Memory Count" align="center" width="140" />
      <el-table-column prop="sex" label="Sex" align="center" width="140" />
      <el-table-column prop="createTime" label="create time" width="130" align="center">
        <template slot-scope="scope">
          <i class="el-icon-time" style="margin-right: 5px" />
          {{ scope.row.createTime | date }}
        </template>
      </el-table-column>

      <el-table-column label="option" align="center" width="100">
        <template slot-scope="scope">
          <el-popconfirm style="margin-left: 10px" title="Are you sure delete？" @confirm="deleteRow(scope.row.id)">
            <el-button size="mini" type="danger" slot="reference"> delete </el-button>
          </el-popconfirm>
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
    this.current = this.$store.state.pageState.user
    this.listUsers()
    this.listRoles()
  },
  data: function () {
    return {
      loading: true,
      userForm: {
        userInfoId: null,
        nickname: ''
      },
      loginType: null,
      userRoles: [],
      roleIds: [],
      userList: [],
      keywords: null,
      current: 1,
      size: 10,
      count: 0
    }
  },
  methods: {
    searchUsers() {
      this.current = 1
      this.listUsers()
    },
    sizeChange(size) {
      this.size = size
      this.listUsers()
    },
    currentChange(current) {
      this.current = current
      this.$store.commit('updateUserPageState', current)
      this.listUsers()
    },
    changeDisable(user) {
      this.axios.put('/api/admin/users/disable', {
        id: user.userInfoId,
        isDisable: user.isDisable
      })
    },
    deleteRow(id) {
      var param = { data: [id] }
      this.axios.delete('/api/admin/user/delete', param).then(({ data }) => {
        if (data.flag) {
          this.$notify.success({
            title: 'success',
            message: data.message
          })
          this.listUsers()
        } else {
          this.$notify.error({
            title: 'error',
            message: data.message
          })
        }
        this.remove = false
      })
    },

    listUsers() {
      this.axios
        .get('/api/admin/users', {
          params: {
            current: this.current,
            size: this.size,
            keywords: this.keywords,
            loginType: this.loginType
          }
        })
        .then(({ data }) => {
          this.userList = data.data.records
          this.count = data.data.count
          this.loading = false
        })
    },
    listRoles() {
      this.axios.get('/api/admin/users/role').then(({ data }) => {
        this.userRoles = data.data
      })
    }
  },
  watch: {
    loginType() {
      this.current = 1
      this.listUsers()
    }
  }
}
</script>
