<template>
  <div class="hello">
    {{name}}
    {{age > 60 ? '老年' : '青年'}}

    <input type="text" v-bind:value="name"/>
    <input type="text" :value="age"/>
    <img :src="src">

    <input type="button" value="保存" v-on:click="handleSave"/>
    <input type="button" value="保存" @click="handleSave"/>

    <input type="text" v-model="name"/>

    <input type="button" value="修改name的值" @click="handleChange"/>

    <div v-if="sex == 1">
      男
    </div>
    <div v-else-if="sex == 2">
      女
    </div>
    <div v-else>
      未知
    </div>

  <input type="button" value="发送POST请求" @click="handleSendPOST"/>
  <input type="button" value="发送GET请求" @click="handleSendGET"/>
  <input type="button" value="统一请求方式" @click="handleSend"/>
  </div>
</template>

<script>
import axiox from 'axios'

export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  data() {
    return {
      name: '张三',
      age: 70,
      src: 'https://www.itcast.cn/2018czgw/images/logo2.png',
      sex: 3
    }
  },
  methods: {
    handleSave() {
      alert('你点击了保存按钮')
    },
    handleChange() {
      this.name = '李四'
    },
    handleSendPOST() {
      //通过axios发送post请求
      axiox.post('/api/admin/employee/login',{
        username: 'admin',
        password: '123456'
      }).then(res => {
        console.log(res.data)
      }).catch(error => {
        console.log(error.response)
      })
    },
    handleSendGET() {
      //通过axios发送get方式请求
      axiox.get('/api/admin/shop/status',{
        headers: {
          token: 'eyJhbGciOiJIUzI1NiJ9.eyJlbXBJZCI6MSwiZXhwIjoxNjkyODUxNDAzfQ.ZIW5nSyuSE6LE2UGJ2rVZ-sLV6RFrPM5rkZU_jeyI8U'
        }
      }).then(res => {
        console.log(res.data)
      })
    },
    handleSend() {
      //使用axios提供的统一调用方式发送请求
      axiox({
        url: '/api/admin/employee/login',
        method: 'post',
        data: { //data表示通过请求体传参
          username: 'admin',
          password: '123456'
        }
      }).then(res => {
        console.log(res.data.data.token)
        axiox({
          url: '/api/admin/shop/status',
          method: 'get',
          headers: {
            token: res.data.data.token
          }
        })
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
