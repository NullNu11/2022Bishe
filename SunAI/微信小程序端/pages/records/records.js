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
        weather: '天气'
    },
    weather: function () {
        
        //ip获取位置adcode
        wx.request({
            url: 'https://restapi.amap.com/v3/ip?ioutput=json&key=22766543ce6215041608a759458e20c0',
            header: { 'content-type': 'application/json' },
            method: 'get',
            dataType: 'json',
            responseType: 'text',

            success: (result) => {
                console.log(result.data.adcode)
                //获取当前天气
                wx.request({
                    url: 'https://restapi.amap.com/v3/weather/weatherInfo?city=' + result.data.adcode + '&key=22766543ce6215041608a759458e20c0&output=json&extensions=base',
                    header: { 'content-type': 'application/json' },
                    method: 'get',
                    dataType: 'json',
                    responseType: 'text',
                    success: (result) => {
                    
                        console.log(result)
                        console.log(result.data.lives[0].weather)
                        
                        this.setData({
                            weather:result.data.lives[0].weather
                        })
                    },
                    fail: (res) => { this.setData({
                        weather:"weather error"
                    
                    })},
                    complete: (res) => { },
                })
            },
            fail: (res) => { console.log("位置获取失败")
        
            this.setData({
                weather:"ip error"
            
            })},
            complete: (res) => { },
        })
    
    },


    //点击提交按钮时间函数,若10个心情标签数据都为0，则提示用户提交失败
    savetag: function (res) {
        {
            var wea;
           //ip获取位置adcode
        wx.request({
            url: 'https://restapi.amap.com/v3/ip?ioutput=json&key=22766543ce6215041608a759458e20c0',
            header: { 'content-type': 'application/json' },
            method: 'get',
            dataType: 'json',
            responseType: 'text',

            success: (result) => {
                console.log(result.data.adcode)
                //获取当前天气
                wx.request({
                    url: 'https://restapi.amap.com/v3/weather/weatherInfo?city=' + result.data.adcode + '&key=22766543ce6215041608a759458e20c0&output=json&extensions=base',
                    header: { 'content-type': 'application/json' },
                    method: 'get',
                    dataType: 'json',
                    responseType: 'text',
                    success: (result) => {
                    
                        console.log(result.data.lives[0].weather)
                        wea=result.data.lives[0].weather
                        // this.setData({
                        //     weather:result.data.lives[0].weather
                        // })

                           //打卡记录
                           console.log(wea)
            wx.request({
                url: app.globalData.oldurl + 'add_record',
                data: {
                    "user_id": app.globalData.user_id,
                    "x_mood": this.data.x,
                    "y_mood":this.data.y,
                    "weather":wea
                },
                header: { 'content-type': 'application/json' },
                method: 'POST',
                dataType: 'json',
                responseType: 'text',
                success: (result) => {
                    console.log(result.data)
                    if (result.data.msg == "打卡信息上传成功") {
                        Toast.success("打卡成功，恭喜您已获得2积分");
                        setTimeout(() => {
                            wx.navigateBack({
                                delta: 300,
                            })
                        }, this.data.setTime);
                        //减少一次网络请求，直接在打卡完成后+2
                        app.globalData.point = app.globalData.point + 2
                    }
                    else if (result.data.msg == "打卡信息更新成功") {
                        Toast.fail("更新成功，今日打卡积分已达上限");
                        setTimeout(() => {
                            wx.navigateBack({
                                delta: 300,
                            })
                        }, this.data.setTime);
                    }
                    else {
                        Toast.fail("打卡失败");
                        setTimeout(() => {
                            wx.navigateBack({
                                delta: 300,
                            })
                        }, this.data.setTime);
                    }
                },
                fail: () => {
                    Toast.fail("网络错误")
                    setTimeout(() => {
                        wx.navigateBack({
                            delta: 300,
                        })
                    }, this.data.setTime);
                },
                complete: () => { }
            });








                    },
                    fail: (res) => { this.setData({
                        weather:"weather error"
                    
                    })},
                    complete: (res) => { },
                })
            },
            fail: (res) => { console.log("位置获取失败")
        
            this.setData({
                weather:"ip error"
            
            })},
            complete: (res) => { },
        })






           
            // //打卡记录
            // wx.request({
            //     url: app.globalData.oldurl + 'add_record',
            //     data: {
            //         "user_id": app.globalData.user_id,
            //         "x_mood": this.data.x,
            //         "y_mood":this.data.y,
            //         "weather":wea
            //     },
            //     header: { 'content-type': 'application/json' },
            //     method: 'POST',
            //     dataType: 'json',
            //     responseType: 'text',
            //     success: (result) => {
            //         console.log(result.data)
            //         if (result.data.msg == "打卡信息上传成功") {
            //             Toast.success("打卡成功，恭喜您已获得2积分");
            //             setTimeout(() => {
            //                 wx.navigateBack({
            //                     delta: 300,
            //                 })
            //             }, this.data.setTime);
            //             //减少一次网络请求，直接在打卡完成后+2
            //             app.globalData.point = app.globalData.point + 2
            //         }
            //         else if (result.data.msg == "打卡信息更新成功") {
            //             Toast.fail("更新成功，今日打卡积分已达上限");
            //             setTimeout(() => {
            //                 wx.navigateBack({
            //                     delta: 300,
            //                 })
            //             }, this.data.setTime);
            //         }
            //         else {
            //             Toast.fail("打卡失败");
            //             setTimeout(() => {
            //                 wx.navigateBack({
            //                     delta: 300,
            //                 })
            //             }, this.data.setTime);
            //         }
            //     },
            //     fail: () => {
            //         Toast.fail("网络错误")
            //         setTimeout(() => {
            //             wx.navigateBack({
            //                 delta: 300,
            //             })
            //         }, this.data.setTime);
            //     },
            //     complete: () => { }
            // });

        }

    },
    clear: function () {
        this.setData({
            Happy: 0,
            excited: 0,
            sad: 0,
            angry: 0,
            miserable: 0,
            afraid: 0,
            worried: 0,
            depressed: 0,
            frustrated: 0,
            satisfied: 0,
            ecstatic: 0,
            hinttext: "请重新选择",
            canvasHeight: 0,
            canvasWidth: 0
        })
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
                height = result.windowHeight;
                width = result.windowWidth;
            },
        })
        height += 200;
        let c = wx.createCanvasContext('canvas');
        console.log(width / 2, height * 0.3)
        c.strokeRect(width / 2 - 150, height * 0.3 - 150, 320, 300)
        //c.rect(width/2-150, height*0.3-150,width/2+130, height*0.3+130)   //框
        c.arc(width / 2, height * 0.3, 120, 0, 2 * Math.PI)  //球
        c.setStrokeStyle('#90A4AE')
        //十字
        c.moveTo(width / 2, height * 0.3)
        c.lineTo(width / 2, height * 0.3 + 140)

        c.moveTo(width / 2, height * 0.3)
        c.lineTo(width / 2, height * 0.3 - 140)

        c.moveTo(width / 2, height * 0.3)
        c.lineTo(width / 2 + 140, height * 0.3)

        c.moveTo(width / 2, height * 0.3)
        c.lineTo(width / 2 - 140, height * 0.3)

        //字
        c.setFontSize(13)
        c.fillText("Joy", width / 2 + 24, height * 0.3 - 45)
        c.fillText("Happy", width / 2 + 70, height * 0.3 - 55)
        c.fillText("Excited", width / 2 + 20, height * 0.3 - 80)
        c.fillText("Pleased", width / 2 + 40, height * 0.3 - 15)
        c.fillText("Valence", width / 2 + 120, height * 0.3 - 10)
        c.fillText("Interested", width / 2 + 5, height * 0.3 - 30)
        c.fillText("Arousal", width / 2 + 10, height * 0.3 - 120)

        c.fillText("Angry", width / 2 - 65, height * 0.3 - 80)
        c.fillText("Anger", width / 2 - 70, height * 0.3 - 50)
        c.fillText("Annoyed", width / 2 - 60, height * 0.3 - 30)
        c.fillText("Afraid", width / 2 - 115, height * 0.3 - 30)
        c.fillText("Terrified", width / 2 - 120, height * 0.3 - 75)
        c.fillText("Furious", width / 2 - 80, height * 0.3 - 105)
        c.fillText("Nervous", width / 2 - 70, height * 0.3 - 15)
        c.fillText("Alamed", width / 2 - 25, height * 0.3 - 70)

        c.fillText("Sad", width / 2 - 95, height * 0.3 + 35)
        c.fillText("Sadness", width / 2 - 60, height * 0.3 + 55)
        c.fillText("Depressed", width / 2 - 120, height * 0.3 + 75)
        c.fillText("Bored", width / 2 - 40, height * 0.3 + 80)
        c.fillText("Sleepy", width / 2 - 20, height * 0.3 + 110)

        c.fillText("Content", width / 2 + 40, height * 0.3 + 20)
        c.fillText("Relaxed", width / 2 + 30, height * 0.3 + 45)
        c.fillText("Clam", width / 2 + 10, height * 0.3 + 70)
        c.fillText("Serence", width / 2 + 80, height * 0.3 + 90)
        c.fillText("Neutral", width / 2 + 40, height * 0.3 + 60)


        c.stroke();
        c.draw();
    },
    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {

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