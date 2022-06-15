// pages/learn.js
import Toast from '@vant/weapp/toast/toast';
let app = getApp();
Page({
  data: {
    user_id: "",
    user_name: "",
    phone_Number: "",
    username: "",
    studentnumber: "",
    radio: '0',
    registerBtn: '1',
    institude: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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

  },
  personal: function () {
    wx.navigateTo({
      url: '/pages/protocol/protocol',
      success: (result) => { },
      fail: (res) => { console.log(res) },
      complete: (res) => { },
    })
  },
  onChange(event) {
    var that = this
    if (this.data.radio == 1) {
      this.setData({
        radio: '0',
        registerBtn: 1
      });
    }
    else {
      that.setData({
        radio: '1',
        registerBtn: 0
      });
    }
  },
  goStatement() {
    wx.navigateTo({
      url: '/pages/statement/statement',
    })
  },
  getPhoneNumber: function (e) {
    if (this.data.radio != 1) {
      Toast("请阅读并同意下方的协议，然后完成注册")
    }
    else {
      //get user_id
      wx.request({
              //url: 'http://3007h50y18.qicp.vip/register',
              url: app.globalData.oldurl + 'register',
              data: {
                user_number: this.data.user_id,
                user_name: this.data.user_name,
                open_id: app.globalData.openid,
                user_id: app.globalData.user_id,
                institude: this.data.institude
              },
              header: { 'content-type': 'application/json' },
              method: 'POST',
              dataType: 'json',
              responseType: 'text',
              success: (result) => {
                //注册成功的信息返会
                app.globalData.is_register=true
                
                console.log(result.data)
                if (result.data.state) {
                  Toast.success(result.data.msg);

                  setTimeout(() => {
                    wx.navigateBack({
                        delta: 0,
                    })
                }, 1000);
                }
                else {
                  Toast.fail(result.data.msg);
                }
              },
              fail: () => {
                console.log(" register error")
              },
              complete: () => {
               
              }
            });
    }
  },
})