import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

//集中管理多个组件共享的数据
export default new Vuex.Store({
  //集中定义共享数据
  state: {
    name: '未登录游客'
  },
  getters: {
  },
  //通过当前属性中定义的函数修改共享数据，必须都是同步操作
  mutations: {
    setName(state,newName) {
      state.name = newName
    }
  },
  //通过actions调用mutation，在actions中可以进行异步操作
  actions: {
    setNameByAxios(context){
      axios({
        url: '/api/admin/employee/login',
        method: 'post',
        data: {
          username: 'admin',
          password: '123456'
        }
      }).then(res => {
        if(res.data.code == 1){
          //异步请求后，需要修改共享数据
          //在actions中调用mutation中定义的setName函数
          context.commit('setName',res.data.data.name)
        }
      })
    }
  },
  modules: {
  }
})
