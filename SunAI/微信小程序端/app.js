App({

  data: {
    login_code: "",
    session_key: "",
    encryptedData: "",
    iv: "",
    openid: ""
  },
  onLaunch() {
    // 展示本地存储能力
    var that = this
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
  },


  check_open_id: function () {
    var that = this;

    return new Promise((resolve, reject) => {
      //1. 第一次wx登录获取login-code
      //logincode是每次微信登录生成的随机数
      wx.login({       
        timeout: 1000,
        success: (result) => {
          console.log("1. get login_code success"),
          that.globalData.user_id = result.code
          that.data.login_code = result.code,
        
          //2.后端解析获取session-id/openid        
          //openid  微信赋予每个用户的唯一凭证
            wx.request({
              url: this.globalData.oldurl + 'open_id',
              data: {
                login_code: that.data.login_code
              },
              header: { 'content-type': 'application/x-www-form-urlencoded' },
              method: 'POST',
              dataType: 'json',
              responseType: 'text',
              success: (result) => {
                //console.log(result.data)
                console.log("2. get open_id success"),
                that.globalData.openid = result.data.openid
                //3. 后端登录请求  获取个人信息
                wx.request({
                  url: this.globalData.oldurl + 'login',
                  data: {
                    open_id: that.globalData.openid
                  },
                  header: { 'content-type': 'application/json' },
                  method: 'POST',
                  dataType: 'json',
                  responseType: 'text',
                  success: (result) => {
                    //非登录的时候初始化，在登录完成了初始化
                    //this.globalData.point = result.data.data.point,
                    //this.globalData.user_id = result.data.data.user_id,
                    console.log(result)
                    if(result.data.state)
                    {
                      console.log('3. 登录成功')
                      resolve(result.data.data)
                    }
                    else{
                      console.log('3. 登录失败')
                      reject("error")
                    }
                  },
                  fail: (err) => {
                    console.log("3. 登录请求失败")
                    //登录的网络请求失败，并非登录失败    
                  },
                  complete: () => { }
                });

              },
              fail: () => { 
                console.log("2. get open_id error") },
              complete: () => { }
            });
        },
        fail: () => { 
          console.log("1. get login_code  error") 
        },
        complete: () => { }
      });
    })
  },
  globalData: {
    global: "hello",
    is_register: true,
    user_id: "",
    user_name: "",
    session_key: "",
    openid: "",
    type: "",
    point: 0,
    oldurl1: "http://127.0.0.1:4863/",
    oldurl: "https://yianya.xyz/",
    avatar: '', 
    x: [],
    y:[],
    music_id:''
  }
})
//appid
//wx994b10352d81f4a9
//wx9d880530b072f0fa