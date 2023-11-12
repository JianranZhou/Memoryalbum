export default {

  routes: [
    {
      name: 'Home',
      path: '/',
      i18n: {
        cn: 'Home',
        en: 'Home'
      },
      children: []
    },

    {
      name: 'About',
      path: '/about',
      i18n: {
        cn: 'About',
        en: 'About'
      },
      children: []
    },
    {
      name: 'Archives',
      path: '/archives',
      i18n: {
        cn: 'Memory Journey',
        en: 'Memory Journey'
      },
      children: []
    },
    {
      name: 'memoryList',
      path: '/memorylist',
      i18n: {
        cn: 'Memory List',
        en: 'Memory List'
      },
      children: []
    },
    {
      name: 'Tags',
      path: '/tags',
      i18n: {
        cn: 'Tags',
        en: 'Tags'
      },
      children: []
    },

  ]
}
