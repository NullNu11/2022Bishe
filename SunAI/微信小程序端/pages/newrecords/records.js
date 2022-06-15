// pages/home/home.js

import Toast from '@vant/weapp/toast/toast';
var bmap = require('../../libs/bmap-wx.js');
let app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        //心情标签，按下为1，未按下为0
        weatherData: '',
        x: 0,
        y: 0,
        hinttext: "请选出符合当下心情的标签",
        setTime: 1000,
        weather: ['晴朗', '多云', '阴', '雨', '雪'],
        mood: '',
        effect: '',
        awake: '',
        awake_img: [{'name':'../../assets/img/awake/1.png'},
        {'name':'../../assets/img/awake/2.png'},
        {'name':'../../assets/img/awake/3.png'},
        {'name':'../../assets/img/awake/4.png'},
        {'name':'../../assets/img/awake/6.png'},
        {'name':'../../assets/img/awake/8.png'},
        {'name':'../../assets/img/awake/9.png'},],

        effect_img: [{'name':'../../assets/img/effect/1.png'},
        {'name':'../../assets/img/effect/2.png'},
        {'name':'../../assets/img/effect/3.png'},
        {'name':'../../assets/img/effect/4.png'},
        {'name':'../../assets/img/effect/5.png'},
        {'name':'../../assets/img/effect/6.png'},
        {'name':'../../assets/img/effect/7.png'},],
        itemlist:[  {'name':'op'},
        {'name':'io'},
        {'name':'kl'},
        {'name':'yu'},
        {'name':'m,j'},]
    },

nima:function(e)
{
    console.log(e)
},

    //点击提交按钮时间函数,若10个心情标签数据都为0，则提示用户提交失败
    savetag: function (res) {
        {
            wx.request({
                url: getApp().globalData.oldurl + 'add_comment',
                data: {
                    "user_id": getApp().globalData.user_id,
                    "comment": this.data.awake+"||"+this.data.effect,
                    "music_id": getApp().globalData.music_id
                },
                dataType: "json",
                header: "contentType:application/json",
                method: 'POST',
                responseType: 'text',
                timeout: 0,
                success: (result) => {
                    console.log(result.data);
                    if (result.data.state)
                        wx.showToast({
                            title: '提交反馈成功!',
                            icon: 'success ',
                            duration: 1000//持续的时间
                        })
                    else
                        Toast.fail('提交失败，请稍后重试')



                       
                },
                fail: (res) => {
                },
                complete: (res) => {
                    
                 },
            })
        }
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
        let height, width;
        wx.getSystemInfo({         // get system weight and hight
            success: (result) => {
                height = result.windowHeight / 750 * 690;
                width = result.windowWidth / 750 * 350;
                console.log(width + "" + height)
            },
        })
        // try {
        //     var res = wx.getSystemInfoSync(); //试图获取屏幕宽高数据
        //     windowWidth = res.windowWidth / 750 * 690; //以设计图750为主进行比例算换
        //     windowHeight = res.windowWidth / 750 * 350 //以设计图750为主进行比例算换
        //     console.log(windowWidth+""+windowHeight)
        //   } catch (e) {
        //     console.error('getSystemInfoSync failed!'); //如果获取失败
        // }
        let c = wx.createCanvasContext('canvas');
        c.setLineWidth(3)
        c.arc(width * 1.05, height * 0.4, 100, 0, 2 * Math.PI)
        c.moveTo(width * 0.88, height * 0.35)

        c.arc(width * 0.8, height * 0.35, 15, 0, 2 * Math.PI)
        c.moveTo(width * 1.38, height * 0.35)

        c.arc(width * 1.3, height * 0.35, 15, 0, 2 * Math.PI)

        c.moveTo(width * 1.05 + 44, height * 0.4 + 48)

        c.arc(width * 1.05, height * 0.37, 80, 1, Math.PI - 1)


        c.stroke();
        c.draw();

    },
    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {

    },
    //标签改变
    onChange: function (e) {
        console.log(e.detail.value)
        var a = e.detail.value
        a /= 14
        a = Math.round(a);
        if (a == 0)
            a = 1
        this.setData({
            awake: a
        })
        //console.log(this.data.awake)
    },
    onChange_effect: function (e) {
        console.log(e.detail.value)
        var a = e.detail.value
        a /= 14
        a = Math.round(a);
        if (a == 0)
            a = 1
        this.setData({
            effect: a
        })
        //console.log(this.data.effect)
    },
    move: function (e) {
        this.setData({
            x: e.touches[0].x - 187.5,
            y: e.touches[0].y - 241
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

    }
})