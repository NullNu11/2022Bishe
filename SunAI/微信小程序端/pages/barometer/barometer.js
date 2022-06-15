// pages/barometer/barometer.js
var wxCharts = require('../../utils/wxcharts.js');
var utils = require('../../utils/util.js');
let app = getApp();
var lineChart = null;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    textcolor1: '#014f8e',
    textcolor2: '#bfbfbf',
    max: 100,
    time: '',
    good: ''
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
    //获取前后数据信息
    var x_data = []
    var y_data = []
    x_data = app.globalData.x
    y_data = app.globalData.y
    console.log(x_data)
    console.log(y_data)
    let height, width;
    wx.getSystemInfo({         // get system weight and hight
      success: (result) => {
        height = result.windowHeight;
        width = result.windowWidth;
      },
    })
    let c = wx.createCanvasContext('newCanvas');
    height /= 111
    width /= 75
    console.log(width, height)
    c.strokeRect(width * 4, height, width * 64, height * 40)
    c.setFontSize(20)
    c.fillText('y', 2, height * 4)
    c.fillText('x', width * 65, height * 44)
    var i = 0;
    
    c.globalCompositeOperation = 'lighter'
    for (i = 0; i < x_data.length; i++) {
      //c.setStrokeStyle('#ffffff')
      c.arc(x_data[i],y_data[i], 5, 0, 2 * Math.PI)
      c.setFillStyle('rgb(42,83,205)')
      c.globalCompositeOperation = 'lighter'
      c.moveTo(x_data[i+1],y_data[i+1])
    }
    c.fill()
    c.stroke();
    c.draw();
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var x_data = []
    var y_data = ["15", "15", "15", "15", "15", "15", "30"]
    this.data.max = Math.max.apply(null, y_data) + 10
    // var i=0;
    // for(i=0;i<7;i++)
    // {
    //   var timestamp = Date.parse(new Date());  
    //   timestamp-=24*3600*(6-i)*1000
    //   var date = new Date(timestamp); 
    //   var M = date.getMonth()+1;  
    //   var D = date.getDate();  
    //   //console.log("当前时间：" +M+D);  
    //   x_data[i]=M+'.'+D
    // }
    x_data = app.globalData.x
    var i;
    for (i = 0; i < 7; i++) {
      //y_data:app.globalData.y
      switch (app.globalData.y[i]) {
        case "快乐":
          y_data[i] = 30;
          break;
        case "激动":
          y_data[i] = 20;
          break;
        case "难过":
          y_data[i] = 10;
          break;
        case "愤怒":
          y_data[i] = 5;
          break;
        case "平和":
          y_data[i] = 25;
          break;
        case "害怕":
          y_data[i] = 10;
          break;
        case "忧愁":
          y_data[i] = 13;
          break;
        case "失意":
          y_data[i] = 10;
          break;
        case "满足":
          y_data[i] = 25;
          break;
        case "狂喜":
          y_data[i] = 25;
          break;
        default:
          y_data[i] = 15;

      }
    }
    var aaa = 0;
    for (i = 0; i < 7; i++) {
      if (y_data[i] >= 15)
        aaa++;
    }
    var good = aaa / 7;
    good = good.toString().substring(2, 4)
    good += "%";
    this.setData({
      good: good
    })
    //console.log(good);
    this.OnWxChart(x_data, y_data, '图表一')
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
  OnWxChart: function (x_data, y_data, name) {
    var windowWidth = '',
      windowHeight = ''; //定义宽高
    try {
      var res = wx.getSystemInfoSync(); //试图获取屏幕宽高数据
      windowWidth = res.windowWidth / 750 * 690; //以设计图750为主进行比例算换
      windowHeight = res.windowWidth / 750 * 350 //以设计图750为主进行比例算换
    } catch (e) {
      console.error('getSystemInfoSync failed!'); //如果获取失败
    }
    lineChart = new wxCharts({
      canvasId: 'lineCanvas', //输入wxml中canvas的id
      type: 'line',
      legend: false,
      categories: x_data, //模拟的x轴横坐标参数
      animation: true, //是否开启动画
      series: [{
        name: name,
        data: y_data,
        format: function (val, name) {
          return val + '分';
        }
      }],
      xAxis: { //是否隐藏x轴分割线
        disableGrid: true,
        fontColor: '#898c97'
      },
      yAxis: { //y轴数据
        title: '', //标题
        fontColor: '#ffffff',
        format: function (val) { //返回数值
          return val;
        },
        min: 0, //最小值
        max: this.data.max,
        gridColor: '#ffffff',
      },
      width: windowWidth, //图表展示内容宽度
      height: windowHeight, //图表展示内容高度
      dataLabel: true, //是否在图表上直接显示数据
      dataPointShape: true, //是否在图标上显示数据点标志
      extra: {
        lineStyle: 'Broken' //曲线
      },
    });
  }
})