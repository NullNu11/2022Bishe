// pages/rank/rank.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    count:8,
    rank: [{
        head: 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83ergeibviaCPEVjC7ykicnk0jcOQNNIqCpJNQxeiaic23Fqun0KjorTdvAcNnTmYKnic9orqC5nQUoAZTsqw/132',
        nickname: '用户1',
        currency: 1000
      },
      {
        head: 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83ergeibviaCPEVjC7ykicnk0jcOQNNIqCpJNQxeiaic23Fqun0KjorTdvAcNnTmYKnic9orqC5nQUoAZTsqw/132',
        nickname: '用户2',
        currency: 1000
      },
      {
        head: 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83ergeibviaCPEVjC7ykicnk0jcOQNNIqCpJNQxeiaic23Fqun0KjorTdvAcNnTmYKnic9orqC5nQUoAZTsqw/132',
        nickname: '用户3',
        currency: 1000
      },
      {
        head: 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83ergeibviaCPEVjC7ykicnk0jcOQNNIqCpJNQxeiaic23Fqun0KjorTdvAcNnTmYKnic9orqC5nQUoAZTsqw/132',
        nickname: '用户4',
        currency: 1000
      }
    ],
    top_rank: [{
        head: 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83ergeibviaCPEVjC7ykicnk0jcOQNNIqCpJNQxeiaic23Fqun0KjorTdvAcNnTmYKnic9orqC5nQUoAZTsqw/132',
        nickname: '用户1',
        currency: 1000
      },
      {
        head: 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83ergeibviaCPEVjC7ykicnk0jcOQNNIqCpJNQxeiaic23Fqun0KjorTdvAcNnTmYKnic9orqC5nQUoAZTsqw/132',
        nickname: '用户2',
        currency: 1000
      },
      {
        head: 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83ergeibviaCPEVjC7ykicnk0jcOQNNIqCpJNQxeiaic23Fqun0KjorTdvAcNnTmYKnic9orqC5nQUoAZTsqw/132',
        nickname: '用户3',
        currency: 1000
      }
    ],
    userInfo: {
         head:'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83ergeibviaCPEVjC7ykicnk0jcOQNNIqCpJNQxeiaic23Fqun0KjorTdvAcNnTmYKnic9orqC5nQUoAZTsqw/132',
         nickname:'lovely',
         currency:1000,
         rank:1999
    },
    ranks:{},
    top3:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      wx.request({
        url: getApp().globalData.oldurl+'top?count='+this.data.count,
        header: "application/json",
        method: "POST",
        responseType: "text",
        timeout: 0,
        success: (result) => {
          console.log(result.data);
          this.setData({
            ranks:result.data.data,
            //cao nmai
          })
          this.setData({
            'top_rank[0]':this.data.ranks[1],
            'top_rank[1]':this.data.ranks[0],
            'top_rank[2]':this.data.ranks[2],
          })
          console.log(this.data.top_rank);
          
        },
        fail: (res) => {},
        complete: (res) => {},
      }),
      
      //当前用户
      this.setData({
        ["userInfo.head"]:getApp().globalData.avatar,
        ["userInfo.nickname"]:getApp().globalData.user_name,
        ["userInfo.currency"]:getApp().globalData.point,
      })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

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

  }
})