import data from "../../utils/data.js";
Page({

  /**
   * 页面的初始数据
   */
  data: {
    playStatus: true,
    audioIndex: -1,//如果直接播放则改为对应下标
    progress: 0,
    duration: 0,
    audioList: [],
    showList: true,
    music: [],
    order: 0,
    color: "",
    first: false,
   
    mood: [{
      id: 1,
      data: "二胡",
      choise:false
    },
    {
      id: 2,
      data: "古筝",
      choise:false
      
    },
    {
      id: 3,
      data: "琵琶",
      choise:false
    },
    {
      id: 4,
      data: "笛子",
      choise:false
    },
    {
      id: 5,
      data: "活泼",
      choise:false
    },
    {
      id: 6,
      data: "庄重",
      choise:false
    },
    {
      id: 7,
      data: "轻盈",
      choise:false
    },
    {
      id: 8,
      data: "柔和",
      choise:false
    },
    {
      id: 9,
      data: "轻松",
      choise:false
    },
    {
      id: 10,
      data: "欢快",
      choise:false
    },
    {
      id: 11,
      data: "平静",
      choise:false
    },
    {
      id: 12,
      data: "悠长",
      choise:false
    },]
  },

  /*"二胡",
        "活泼",
          "庄重",
            "轻盈",
          "琵琶",
        "古筝",
        "柔和",
        "清脆",
        "欢快",
        "平静",
        "悠长",
        "轻松"*/

  order: function () {
    console.log("dianji")
    let a = this.data.order;
    if (a) {
      this.setData({
        order: 0
      })
    }
    else {
      this.setData({
        order: 1
      })
    }
  },
  choise_sure: function () {
    this.setData({
      first: true
    })
  },
  choise_mood: function (res) {
    console.log(res);
    var effect= res.currentTarget.dataset.id;
    var  _k2 = `mood[${effect}].choise` // 拼接动态属性
    this.setData({
      [_k2]:true
    })
    console.log(effect);
  },
  pass: function () {
    var i=0;
    for(i;i<this.data.mood.length;i++){
      var temp=`mood[${i}].choise`;
      this.setData({
        [temp]:false
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //旧版
    // this.setData({
    //   audioList: data
    // })
    //初始化时默认不播放
    // this.playMusic();
    wx.request({
      url: getApp().globalData.oldurl + 'eduoss/fileoss/music?id=' + getApp().globalData.user_id,
      header: 'contentType:application/json',
      method: 'POST',
      responseType: 'text',
      timeout: 0,
      success: (result) => {
        console.log(result.data);
        this.setData({
          audioList: result.data.data
        })
      },
      fail: (res) => { console.log("get music error"); },
      complete: (res) => { },
    })
  },

  icon: function () {
    // this.setData({
    //   color:"blue"
    // })
  },

  playMusic: function () {
    let audio = this.data.audioList[this.data.audioIndex];
    let manager = wx.getBackgroundAudioManager();
    manager.title = audio.name || "音频标题";
    manager.epname = audio.epname || "专辑名称";
    manager.singer = audio.author || "歌手名";
    manager.coverImgUrl = audio.image_url;
    // 设置了 src 之后会自动播放
    manager.src = audio.music_url;
    manager.currentTime = 0;
    let that = this;
    manager.onPlay(function () {
      console.log("======onPlay======");
      getApp().globalData.music_id += 5
      that.setData({
        playStatus: true
      })
      that.countTimeDown(that, manager);
    });
    manager.onPause(function () {
      that.setData({
        playStatus: false
      })
      console.log("======onPause======");
    });
    manager.onEnded(function () {
      console.log("======onEnded======");
      that.setData({
        playStatus: false
      })
      setTimeout(function () {
        that.nextMusic();
      }, 1500);
    });

  },

  //循环计时
  countTimeDown: function (that, manager, cancel) {
    if (that.data.playStatus) {
      setTimeout(function () {
        if (that.data.playStatus) {
          // console.log("duration: " + manager.duration);
          // console.log(manager.currentTime);
          that.setData({
            progress: Math.ceil(manager.currentTime),
            progressText: that.formatTime(Math.ceil(manager.currentTime)),
            duration: Math.ceil(manager.duration),
            durationText: that.formatTime(Math.ceil(manager.duration))
          })
          that.countTimeDown(that, manager);
        }
      }, 1000)
    }
  },

  //拖动事件
  sliderChange: function (e) {
    let manager = wx.getBackgroundAudioManager();
    manager.pause();
    manager.seek(e.detail.value);
    this.setData({
      progressText: this.formatTime(e.detail.value)
    })
    setTimeout(function () {
      manager.play();
    }, 1000);
  },

  //列表点击事件
  listClick: function (e) {
    let pos = e.currentTarget.dataset.pos;
    if (pos != this.data.audioIndex) {
      this.setData({
        audioIndex: pos,
        showList: false
      })
      this.playMusic();
    } else {
      this.setData({
        showList: false
      })
      //更改播放状态
      this.playOrpause();
    }
  },

  //上一首
  lastMusic: function () {
    let audioIndex = this.data.audioIndex > 0 ? this.data.audioIndex - 1 : this.data.audioList.length - 1;
    this.setData({
      audioIndex: audioIndex,
      playStatus: false,
      progress: 0,
      progressText: "00:00",
      durationText: "00:00"
    })
    setTimeout(function () {
      this.playMusic();
    }.bind(this), 1000);
  },

  //播放按钮
  playOrpause: function () {
    let manager = wx.getBackgroundAudioManager();
    if (this.data.playStatus) {
      manager.pause();
    } else {
      manager.play();
    }
  },

  //下一首
  nextMusic: function () {
    let audioIndex = this.data.audioIndex < this.data.audioList.length - 1 ? this.data.audioIndex + 1 : 0;
    this.setData({
      audioIndex: audioIndex,
      playStatus: false,
      progress: 0,
      progressText: "00:00",
      durationText: "00:00"
    })
    setTimeout(function () {
      this.playMusic();
    }.bind(this), 1000);
  },

  //界面切换
  pageChange: function () {
    this.setData({
      showList: true
    })
  },

  //时长
  formatTime: function (s) {
    let t = '';
    s = Math.floor(s);
    if (s > -1) {
      let min = Math.floor(s / 60) % 60;
      let sec = s % 60;
      if (min < 10) {
        t += "0";
      }
      t += min + ":";
      if (sec < 10) {
        t += "0";
      }
      t += sec;
    }
    return t;
  },
  comment: function () {
    wx.navigateTo({
      url: '/pages/my/feedback/feedback',
    })
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
    wx.navigateTo({
      url: '../newrecords/records',
    })
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