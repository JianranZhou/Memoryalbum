<template>
  <div>
    <el-row :gutter="30">

      <el-col :span="6">
        <el-card>
          <div class="card-icon-container">
            <i class="iconfont el-icon-myuser" style="color: #34bfa3" />
          </div>
          <div class="card-desc">
            <div class="card-title">Total User</div>
            <div class="card-count">{{ userCount }}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div class="card-icon-container">
            <i class="iconfont el-icon-mywenzhang-copy" style="color: #f4516c" />
          </div>
          <div class="card-desc">
            <div class="card-title">Total Memory</div>
            <div class="card-count">{{ articleCount }}</div>
          </div>
        </el-card>
      </el-col>

    </el-row>

  </div>
</template>

<script>
import '@/assets/js/china'
export default {
  created() {

    this.getData()
  },
  data: function () {
    return {
      loading: true,
      type: 1,
      userCount: 0,
      articleCount: 0,
      articleStatisticsDTOs: [],
      tagDTOs: [],
      viewCount: {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          }
        },
        color: ['#3888fa'],
        legend: {
          data: ['Visitors']
        },
        grid: {
          left: '0%',
          right: '0%',
          bottom: '0%',
          top: '10%',
          containLabel: true
        },
        xAxis: {
          data: [],
          axisLine: {
            lineStyle: {
              color: '#666'
            }
          }
        },
        yAxis: {
          axisLine: {
            lineStyle: {
              color: '#048CCE'
            }
          }
        },
        series: [
          {
            name: 'Visitors',
            type: 'line',
            data: [],
            smooth: true
          }
        ]
      },
      ariticleRank: {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          }
        },
        color: ['#58AFFF'],
        grid: {
          left: '0%',
          right: '0%',
          bottom: '0%',
          top: '10%',
          containLabel: true
        },
        xAxis: {
          data: []
        },
        yAxis: {},
        series: [
          {
            name: ['Views'],
            type: 'bar',
            data: []
          }
        ]
      },

    }
  },
  methods: {
    getData() {
      this.axios.get('/api/admin').then(({ data }) => {
        this.userCount = data.data.userCount
        this.articleCount = data.data.articleCount
        this.articleStatisticsDTOs = data.data.articleStatisticsDTOs
        if (data.data.uniqueViewDTOs != null) {
          data.data.uniqueViewDTOs.forEach((item) => {
            this.viewCount.xAxis.data.push(item.day)
            this.viewCount.series[0].data.push(item.viewsCount)
          })
        }



        if (data.data.articleRankDTOs != null) {
          data.data.articleRankDTOs.forEach((item) => {
            this.ariticleRank.series[0].data.push(item.viewsCount)
            this.ariticleRank.xAxis.data.push(item.memoryTitle)
          })
        }


        this.loading = false
      })
    },

  },
  watch: {
    type() {

    }
  }
}
</script>

<style scoped>
.card-icon-container {
  display: inline-block;
  font-size: 3rem;
}
.area-wrapper {
  display: flex;
  justify-content: center;
}
.card-desc {
  font-weight: bold;
  float: right;
}
.card-title {
  margin-top: 0.3rem;
  line-height: 18px;
  color: rgba(0, 0, 0, 0.45);
  font-size: 1rem;
}
.card-count {
  margin-top: 0.75rem;
  color: #666;
  font-size: 1.25rem;
}
.echarts {
  width: 100%;
  height: 100%;
}
.e-title {
  font-size: 13px;
  color: #202a34;
  font-weight: 700;
}
</style>
