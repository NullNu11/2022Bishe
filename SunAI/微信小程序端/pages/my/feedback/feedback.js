// pages/my/feedback/feedback.js
import Toast from '@vant/weapp/toast/toast';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    comment: '',
    value:{
      feel:"2.5",
      music:"2.5",
      improve:"2.5"
    },
    music_name:'春江潮水'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  onChange_feel:function(m){
    
    this.setData({
      ['value.feel']:m.detail,
       comment:this.data.comment+'feel'+m.detail
    }),
    console.log(this.data.value.feel);
    
  },
  onChange_music:function(m){
    this.setData({
      ['value.music']:m.detail,
      comment:this.data.comment+'music'+m.detail
    }),
    console.log(this.data.value.music);
  },
  onChange_improve:function(m){
    this.setData({
      ['value.improve']:m.detail,
      comment:this.data.comment+'improve'+m.detail
    }),
    console.log(this.data.comment);
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

  inputData: function (e) {
    this.setData({
      comment: e.detail.value
    })
  },
  submitBtn: function () {
    console.log(this.data.comment+1);
    if (this.data.comment != '')
      wx.request({
        url: getApp().globalData.oldurl + 'add_comment',
        data: {
          "user_id": getApp().globalData.user_id,
          "comment": this.data.comment,
          "music_id": 1
        },
        dataType: "json",
        header: "contentType:application/json",
        method: 'POST',
        responseType: 'text',
        timeout: 0,
        success: (result) => {
          console.log(result.data);
          if(result.data.state)
          wx.showToast({
            title: '提交反馈成功!',
            icon: 'success ',
            duration: 1000//持续的时间
          })
          else[
            Toast.fail('提交失败，请稍后重试')
          ]
        },
        fail: (res) => {
          
      },
        complete: (res) => { },
    })
    else{
      Toast.fail("建议不能为空")
    }

setTimeout(() => {
  wx.switchTab({
    url: '/pages/home/home',
  })
}, 1000);
    //---上传反馈---
    //*********** */
    //*********** */

    
  }



})