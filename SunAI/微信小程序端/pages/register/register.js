// pages/register/register.js
const app = getApp()
const defaultAvatarUrl = 'https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0'
// const db = wx.cloud.database()
// const userInfoCollection = db.collection('userInfo')

Page({

      /**
       * 页面的初始数据
       */
      data: {
        avatarUrl: defaultAvatarUrl,
        account: '',
        nickName: '',
        imgUrl: ''
      },
      onChooseAvatar(e) {
        const {
          avatarUrl
        } = e.detail
        this.setData({
          avatarUrl,
        })
      },
      handlerSubmit: function (event) {
        let flag = true
        this.setData({
          account: event.detail.value.account,
          nickName: event.detail.value.nickName
        })
        userInfoCollection.get({
              success: res => {
                let user = res.data
                console.log(user)
                for (let i = 0; i < user.lenth; i++) {
                  if (this.data.account === user[i]._id) {
                    flag = true;
                    break;
                  }
                }
                // if(flag === true){
                //   wx.showToast({
                //     title: '用户已存在',
                //     icon: 'error',
                //     duration: 2500
                //   })
                //   console.log("账号已存在")
                // }
                // else {
                var that = this;
                wx.cloud.uploadFile({
                  cloudPath: (new Date().valueOf() + '.png'),
                  filePath: this.data.avatarUrl,
                  success: res => {
                    console.log(res.fileID)
                    that.setData({
                      imgUrl: res.fileID
                    })
                    that.upload(res.fileID)
                  },
                  fail: err => {
                    // handle error
                  }
                })
              }
            }
            // })

            // console.log(event.detail.value)
            // let account_id = event.detail.value.account
            // let nickName = event.detail.value.nickName
            // let openid = app.globalData.openid
            // console.log('openid是' + openid)
            // const db = wx.cloud.database()
            // const userInfoCollection = db.collection('userInfo')
            // userInfoCollection.add({
            //   data:{
            //     _id: account_id,
            //     nickName: nickName,
            //   }
            // })
          )},
          /**
           * 生命周期函数--监听页面加载
           */
          onLoad(options) {

          },
          upload(filePath) {
            console.log(filePath)
            db.collection("userInfo").add({
              data: {
                nickName: this.data.nickName,
                _id: this.data.account,
                avatar: filePath
              }
            }).then(res => {
              wx.showToast({
                title: '注册成功',
                icon: 'success',
                duration: 2000
              })
            })
          },

          /**
           * 生命周期函数--监听页面初次渲染完成
           */
          onReady() {

          },

          /**
           * 生命周期函数--监听页面显示
           */
          onShow() {

          },

          /**
           * 生命周期函数--监听页面隐藏
           */
          onHide() {

          },

          /**
           * 生命周期函数--监听页面卸载
           */
          onUnload() {

          },

          /**
           * 页面相关事件处理函数--监听用户下拉动作
           */
          onPullDownRefresh() {

          },

          /**
           * 页面上拉触底事件的处理函数
           */
          onReachBottom() {

          },

          /**
           * 用户点击右上角分享
           */
          onShareAppMessage() {

          }
      })