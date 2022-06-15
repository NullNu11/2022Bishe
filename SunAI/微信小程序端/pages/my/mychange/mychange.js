// pages/my/mychange/mychange.js
import Toast from '@vant/weapp/toast/toast';
let app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    intro: ""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  inputData: function (e) {
    this.setData({
      intro: e.detail.value
    })
  },
  submitBtn: function () {
    if (this.data.intro != '' && this.data.intro != null)
      wx.request({
        url: getApp().globalData.oldurl + 'update_intro?' + "id=" + getApp().globalData.user_id +
          "&intro=" + this.data.intro,
        dataType: "json",
        header: "contentType:multipart/form-data",
        method: 'POST',
        responseType: 'text',
        timeout: 0,
        success: (result) => {
          console.log(result.data);
          if (result.data.state) {
            wx.showToast({
              title: '提交反馈成功!',
              icon: 'success ',
              duration: 3000//持续的时间
            }),
            app.globalData.intro=this.data.intro;
            setTimeout(() => {
             wx.switchTab({
               url: '/pages/my/my',
               success: (res) => {},
               fail: (res) => {},
               complete: (res) => {},
             })
          }, 1000);
          }
          else[
            Toast.fail('提交失败，请稍后重试')
          ]
        },
        fail: (res) => {

        },
        complete: (res) => { },
      })
    else {
      Toast.fail('签名不能为空，请输入后重试')
    }


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