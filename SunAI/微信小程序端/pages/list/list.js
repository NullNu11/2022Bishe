// pages/list/list.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    comment:[true,false],
    list: [{
      "avator": "https://upload-avator.oss-cn-shanghai.aliyuncs.com/avatar/b1517e60f75a49d4ba57fef8f68002a7.jpg",
      "name": "枍安",
      "time": "一分钟前",
      "text": "这就是文案啊，就爱上快乐的火箭分离卡萨好过了就爱上了贵行萨拉",
      "count":3,
      "show":false,
      "image": ["https://upload-avator.oss-cn-shanghai.aliyuncs.com/avatar/b1517e60f75a49d4ba57fef8f68002a7.jpg",
        ]
     },
     {
       "avator": "https://upload-avator.oss-cn-shanghai.aliyuncs.com/avatar/b1517e60f75a49d4ba57fef8f68002a7.jpg",
       "name": "枍安",
       "time": "一分钟前",
       "text": "这就是文案啊，就爱上快乐的火箭分离卡萨好过了就爱上了贵行萨拉",
       "count":1,
       "show":false,
       "image": ["https://upload-avator.oss-cn-shanghai.aliyuncs.com/avatar/b1517e60f75a49d4ba57fef8f68002a7.jpg"]
     },
     {
       "avator": "https://upload-avator.oss-cn-shanghai.aliyuncs.com/avatar/b1517e60f75a49d4ba57fef8f68002a7.jpg",
       "name": "枍安",
       "time": "一分钟前",
       "text": "这就是文案啊，就爱上快乐的火箭分离卡萨好过了就爱上了贵行萨拉",
       "count":1,
       "show":false,
       "image": ["https://upload-avator.oss-cn-shanghai.aliyuncs.com/avatar/b1517e60f75a49d4ba57fef8f68002a7.jpg"]
     },
     {
       "avator": "https://upload-avator.oss-cn-shanghai.aliyuncs.com/avatar/b1517e60f75a49d4ba57fef8f68002a7.jpg",
       "name": "枍安",
      "time": "一分钟前",
       "text": "这就是文案啊，就爱上快乐的火箭分离卡萨好过了就爱上了贵行萨拉",
       "count":1,
       "show":false,
       "image": ["https://upload-avator.oss-cn-shanghai.aliyuncs.com/avatar/b1517e60f75a49d4ba57fef8f68002a7.jpg"]
     }
  ],
    zan:145646,
    show:false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
chat:function(res){

  console.log(res.currentTarget.dataset.id)
  let id=res.currentTarget.dataset.id
  let temp=this.data.list[id].show
  if(temp)
  {
    this.setData({
      ["list["+id+"].show"]:false
    })
  }
  else{
    this.setData({
      ["list["+id+"].show"]:true
    })
  }
  
},
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },
onclick:function(res)
{
console.log(res)
if(this.data.comment)
  {
    this.setData({
      comment:false
    })
  }
  else{
    this.setData({
      comment:true
    })
  }
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