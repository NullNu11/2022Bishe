// pages/Motiontest/Motiontest.js
let app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
       type:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  //预获取问卷类型
  onLoad: function (options) {
      wx.request({
        url: app.globalData.oldurl+"search_questionnaire",
        header: "application/json",
        method: "GET",
        responseType: "text",
        timeout: 0,
        success: (result) => {
           
           this.setData({
              type:result.data.data
           });
           console.log(this.data.type);
         },
        fail: (res) => {},
        complete: (res) => {},
      })
  },

  itemClick:function(item){ 
     app.globalData.type=item.currentTarget.dataset.item.questionnaire_id;
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
  goQuestion(item) {
    app.globalData.type=item.currentTarget.dataset.item.questionnaire_id;
    wx.navigateTo({
       url:"./Questions/Questions"
    })
 }

})