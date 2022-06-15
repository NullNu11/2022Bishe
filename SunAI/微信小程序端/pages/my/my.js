// pages/owner.js
import Dialog from '@vant/weapp/dialog/dialog';
let app = getApp();


Page({

  /**
   * 页面的初始数据
   */
  data: {
    point: "",
    name: "",
    introduction: "",
    image: '',
    defaultUserHead: '/assets/default-user.png',
    defaultmessage: ["用户名", "个人说明"]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // wx.request({
    //   url: app.globalData.oldurl + 'login',
    //   data: {
    //     open_id: app.globalData.openid
    //   },
    //   header: { 'content-type': 'application/json' },
    //   method: 'POST',
    //   dataType: 'json',
    //   responseType: 'text',
    //   success: (result) => {
    //     console.log(result.data.data.image),
    //     this.setData({
    //       point: result.data.data.point,
    //       name: result.data.data.user_name,
    //       introduction: result.data.data.introduction,
    //       image:result.data.data.avatar,
    //     })
    //   },
    //   fail: () => { console.log("后端登录  error") },
    //   complete: () => { }
    // });
    console.log(app.globalData);
   
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
    this.setData({
      point: app.globalData.point,
      name: app.globalData.user_name,
      introduction: app.globalData.intro,
      image: app.globalData.avatar,
    })
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
  registerBtn: function () {
    wx.navigateTo({
      url: '/pages/register/register',
      success: (result) => {

      },
      fail: () => { },
      complete: () => { }
    });
  },
  pointsBtn: function () {
    Dialog.alert({
      title: '恭喜您',
      message: '您现在的积分为  ' + this.data.point,
      theme: 'round-button',
    }).then(() => {
      // on close
    });

  },
  giftBtn: function () {
    wx.navigateTo({
      url: '/pages/show_gift/show_gift',
    })
  },
  aboutBtn: function () {
    wx.navigateTo({
      url: '/pages/my/about/about',
    })
  },
  helpBtn: function () {
    wx.navigateTo({
      url: '/pages/my/feedback/feedback',
    })
  },
  protocolBtn: function () {
    wx.navigateTo({
      url: '/pages/statement/statement',
      success: (result) => { },
      fail: (res) => { console.log(res) },
      complete: (res) => { },
    })
  },
  otherBtn:function(){
       
  },
  changeInfoBtn: function () {
    wx.navigateTo({
      url: '/pages/my/mychange/mychange',
    })
  },
})