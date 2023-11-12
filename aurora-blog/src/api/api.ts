import axios from 'axios'
import { app } from '@/main'

// Common methods interact with backend using axios //

axios.interceptors.request.use((config: any) => {
  config.headers['Authorization'] = 'Bearer ' + sessionStorage.getItem('token')
  return config
})

axios.interceptors.response.use(
  (response) => {
    switch (response.data.code) {
      case 50000:
        app.config.globalProperties.$notify({
          title: 'Error',
          message: 'System error, please contact the manager',
          type: 'error'
        })
        break
      case 40001:
        app.config.globalProperties.$notify({
          title: 'Error',
          message: 'User is not logged in',
          type: 'error'
        })
        break
    }
    return response
  },
  (error) => {
    return Promise.reject(error)
  }
)
export default {
  getTopAndFeaturedArticles: () => {
    return axios.get('/api/memory/topAndFeatured')
  },
  getArticles: (params: any) => {
    return axios.get('/api/memory/all', { params: params })
  },
  getArticlesByCategoryId: (params: any) => {
    return axios.get('/api/memory/categoryId', { params: params })
  },
  getArticeById: (articleId: any) => {
    return axios.get('/api/memory/' + articleId)
  },
  getAllCategories: () => {
    return axios.get('/api/categories/all')
  },
  getAllTags: () => {
    return axios.get('/api/tags/all')
  },
  deleteMemory: (id: any) => {
    return axios.delete('/api/memory/'+id)
  },
  getTopTenTags: () => {
    return axios.get('/api/tags/topTen')
  },
  getArticlesByTagId: (params: any) => {
    return axios.get('/api/memory/tagId', { params: params })
  },
  getAllArchives: (params: any) => {
    return axios.get('/api/archives/all', { params: params })
  },
  login: (params: any) => {
    return axios.post('/api/users/login', params)
  },
  saveComment: (params: any) => {
    return axios.post('/api/comments/save', params)
  },
  getComments: (params: any) => {
    return axios.get('/api/comments', { params: params })
  },
  getTopSixComments: () => {
    return axios.get('/api/comments/topSix')
  },
  getAbout: () => {
    return axios.get('/api/about')
  },
  submitUserInfo: (params: any) => {
    return axios.put('/api/users/info', params)
  },
  getUserInfoById: (id: any) => {
    return axios.get('/api/users/info/' + id)
  },
  updateUserSubscribe: (params: any) => {
    return axios.put('/api/users/subscribe', params)
  },
  sendValidationCode: (username: any) => {
    return axios.get('/api/users/code', {
      params: {
        username: username
      }
    })
  },
  bindingEmail: (params: any) => {
    return axios.put('/api/users/email', params)
  },
  register: (params: any) => {
    return axios.post('/api/users/register', params)
  },
  searchArticles: (params: any) => {
    return axios.get('/api/memory/search', {
      params: params
    })
  },
  getAlbums: () => {
    return axios.get('/api/photos/albums')
  },
  getPhotosBuAlbumId: (albumId: any, params: any) => {
    return axios.get('/api/albums/' + albumId + '/photos', {
      params: params
    })
  },
  getWebsiteConfig: () => {
    return axios.get('/api')
  },
  qqLogin: (params: any) => {
    return axios.post('/api/users/oauth/qq', params)
  },
  report: () => {
    axios.post('/api/report')
  },
  getTalks: (params: any) => {
    return axios.get('/api/talks', {
      params: params
    })
  },
  getTalkById: (id: any) => {
    return axios.get('/api/talks/' + id)
  },
  logout: () => {
    return axios.post('/api/users/logout')
  },
  getRepliesByCommentId: (commentId: any) => {
    return axios.get(`/api/comments/${commentId}/replies`)
  },
  updatePassword: (params: any) => {
    return axios.put('/api/users/password', params)
  },
  accessArticle: (params: any) => {
    return axios.post('/api/memory/access', params)
  }
}
