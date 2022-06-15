// pages/home/home.js
let app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    value: 100,
    gradientColor: {
      '0%': '#ffd01e',
      '100%': '#ee0a24',
    },
    arr: ["a", "b"],
    defaultUserHead: '/assets/default-user.png',
    integralInfo: {
      accumulateDay: 0,
      persistentDay: 0
    },
    count: 0,//累计打卡
    maxContinue: 0,//连续打卡
    count_users: 0,//参与人数
    record_times: 0,//打卡次数
    reg:true,//本地注册数据
    rankHeads: [
      'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83ergeibviaCPEVjC7ykicnk0jcOQNNIqCpJNQxeiaic23Fqun0KjorTdvAcNnTmYKnic9orqC5nQUoAZTsqw/132',
      'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83ergeibviaCPEVjC7ykicnk0jcOQNNIqCpJNQxeiaic23Fqun0KjorTdvAcNnTmYKnic9orqC5nQUoAZTsqw/132',
      'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83ergeibviaCPEVjC7ykicnk0jcOQNNIqCpJNQxeiaic23Fqun0KjorTdvAcNnTmYKnic9orqC5nQUoAZTsqw/132',
      'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83ergeibviaCPEVjC7ykicnk0jcOQNNIqCpJNQxeiaic23Fqun0KjorTdvAcNnTmYKnic9orqC5nQUoAZTsqw/132',
      'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83ergeibviaCPEVjC7ykicnk0jcOQNNIqCpJNQxeiaic23Fqun0KjorTdvAcNnTmYKnic9orqC5nQUoAZTsqw/132'
    ],
    isClock: false,//控制是否打卡,
    point: 0,
    nickName: "",
    avatar: "",
    recent_time: "",
    x_data: [],
    y_data: [],
    psr_x:[],
    psr_y:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  // onLoad: function (options) {


  // },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onLoad: function () {
    var date = new Date(Date.parse(new Date()));
    //年  
    var Y = date.getFullYear();
    //月  
    var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1);
    //日  
    var D = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();

    if (M == 1 || M == 3 || M == 5 || M == 7 || M == 8 || M == 10 || M == 12) {
      D = 31;
    }
    else if (M == 20) {
      if (Y % 4 || (Y % 400) && !(Y % 100)) {
        D = 29;
      }
      else
        D = 28;
    }
    else {
      D = 30;
    }

    this.setData({
      recent_time: Y + '.' + M + ".1-" + Y + '.' + M + "." + D
    })

    //let name=userInfo.nickName
    app.check_open_id().then((res)=>{
      //验证promise成功
      app.globalData.user_id = res.user_id;
      app.globalData.point = res.point;
      app.globalData.user_name = res.user_name;
      app.globalData.avatar = res.avatar;
      app.globalData.intro = res.introduction;
      //console.log(res + '99999');
      // console.log(res);
      this.setData({
        point: app.globalData.point,
        nickName: app.globalData.user_name,
        avatar: res.avatar
      })
      
      var i = 0;
      wx.request({
        url: app.globalData.oldurl + '/search_record?id=' + app.globalData.user_id,
        header: { 'content-type': 'application/json' },
        method: 'GET',
        dataType: 'json',
        responseType: 'text',
        success: (result) => {
          console.log(result.data),
            //console.log(result.data.data.maxContinue)
            //console.log(app.globalData.user_id)
            this.setData({
              count: result.data.data.count,
              maxContinue: result.data.data.maxContinue
            })
          result = result.data.data
          var i = result.record.length;
          
          var timestamp = Date.parse(new Date());
          timestamp -= 24 * 3600 * (6 - i) * 1000
          var date = new Date(timestamp);
          var M = date.getMonth() + 1;
          var D = date.getDate();
          M = 1;
          D = 1;
          i>60?60:i
          console.log(i);
          var a,b=0;
          for (a=0;a<i;a++) {
           
            this.setData({
              ['psr_x[' + a + ']']: result.record[result.record.length - i + b].x_mood+150,
              ['psr_y[' + a + ']']: result.record[result.record.length - i + b].y_mood+50
            })
            b++;
            //第一版的晴雨表数据
            // if ((result.record.length - 7 + i) >= 0) {
            //   this.setData({
            //     ['x_data[' + i + ']']: result.record[result.record.length - 7 + i].record_data,
            //     ['y_data[' + i + ']']: result.record[result.record.length - 7 + i].update_time.substring(5, 10)
            //   })
            // }
            // else {
            //   //不够7条
            //   this.setData({
            //     ['y_data[' + i + ']']: M + '.' + (D++)
            //   })

            //   this.setData({
            //     ['x_data[' + i + ']']: ""
            //   })

            // }
          }
          // console.log(this.data.psr_x);
          // console.log(this.data.psr_y);
          app.globalData.x = this.data.psr_x;
          app.globalData.y = this.data.psr_y;
        },
        fail: () => { console.log("查询打卡记录  error") },
        complete: () => { }
      });
      wx.request({
        url: app.globalData.oldurl + '/find_all_user',
        header: { 'content-type': 'application/json' },
        method: 'GET',
        dataType: 'json',
        responseType: 'text',
        success: (result) => {
          //console.log(result.data),
          this.setData({
            count_users: result.data.data
          })
        },
        fail: () => { console.log("查询打卡记录  error") },
        complete: () => { }
      });
      wx.request({
        url: app.globalData.oldurl + '/find_all_record',
        header: { 'content-type': 'application/json' },
        method: 'GET',
        dataType: 'json',
        responseType: 'text',
        success: (result) => {
          //console.log(result.data),
          //console.log(result.data)
          this.setData({
            record_times: result.data.data
          })
        },
        fail: () => { console.log("查询打卡记录  error") },
        complete: () => { }
      });
    })//promise验证失败，说明用户没有注册过信息
    .catch((e)=>{ 
      //维护全局变量控制
      //用户登录
      app.globalData.is_register=false
      console.log("promise fail")
    })
    
    
  },

  //一下两个函数，如果未登录，用户点击跳转到登录界面
  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    if(!app.globalData.is_register)
    wx.navigateTo({
      url: '../../pages/register/register',
    })
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    if(!app.globalData.is_register)
    wx.navigateTo({
      url: '../../pages/register/register',
    })
  },

  my_point(){
   wx.switchTab({
     url: '/pages/integral/integral',
   })
  },
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  onGetUserProfile() {
    wx.navigateTo({
      url: '/pages/records/records'
    })
  },
  goRank() {
    wx.navigateTo({
      url: '/pages/rank/rank',
    })
  }

})