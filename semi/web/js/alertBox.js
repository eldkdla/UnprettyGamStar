/**
 * 
 * @authors JiangDing (jiangdingjd@gmail.com)
 * @date    2017-11-06 14:10:05
 * @version 0.1
 */

;(function($, window, document){

  // 定义插件名字
  var pluginName = "alertBox";
  // 设置插件默认参数
  var defaults = {
    zIndex: 99999,  //弹出层定位层级
    title: '',  //标题文字
    lTxt: '',   //左边按钮文字内容
    lBgColor: "#D4D4D4",  //左边按钮背景颜色
    lFontColor: "#333",   //左边按钮文字颜色
    lCallback: function(){},    //左边按钮回调函数
    rTxt: '',   //右边按钮文字内容
    rBgColor: "#ED6465",  //右边按钮背景颜色
    rFontColor: "#fff",   //右边按钮文字颜色
    rCallback: function(){}   //右边按钮回调函数
  };

  function AlertBox(element, options){
    this.element = element;
    this.settings = $.extend({}, defaults, options);
    this.init();
  }

  AlertBox.prototype = {
    // 初始化弹窗
    init: function(){
      var that = this;
      var element = this.element;
        
      that.render(element);
      that.setStyle();
      that.show();
      that.trigger(element);
    },

    // 创建弹窗
    create: function(element){
      var that = this,
      $this = $(element),
      title = that.settings.title,
      zIndex = that.settings.zIndex,
      lTxt = that.settings.lTxt,
      rTxt = that.settings.rTxt,
      alertHTML = [];

      alertHTML[0] = '<div class="alert_panel"><h3 class="alert_title">' + title + '</h3>';
      alertHTML[1] = '<div class="alert_btn_group"><span class="alert_left_btn">'+ lTxt + '</span>';
      alertHTML[2] = '<span class="alert_right_btn">' + rTxt + '</span></div>';
      alertHTML[3] = '<div class="alert_close_btn"><p style="margin:0;">&#10005</p></div></div>';
      alertHTML[4] = '<div id="alert_mask"></div>';
      return alertHTML;
    },

    // 渲染弹窗
    render: function(element){
      var that = this,
        $this = $(element),
        alertHTML = that.create($this);
      $('body').append('<div id="alert_box"></div>');

      $('#alert_box').replaceWith(alertHTML[0] + alertHTML[1] + alertHTML[2] + alertHTML[3]);
      $('body').append(alertHTML[4]);
    },

    // 显示弹窗
    show: function(){
      setTimeout(function(){
        $('.alert_panel').addClass('show');
      },50)

      $('#alert_mask').show();
    },

    // 隐藏弹窗
    hide: function(element){
      var $this = $(element),
        $alertBox = $('.alert_panel');
      
      // 优化处理（如果不remove掉，多次触发弹窗会生成很多新的DOM）
      $alertBox.remove();
      setTimeout(function(){
        $('#alert_mask').remove();
      },150)
    },

    // 设置弹窗样式
    setStyle: function(){
      var that = this;

      // 设置弹窗定位层级
      $('.alert_panel').css({
        'z-index': that.settings.zIndex
      });

      //遮罩层样式
      $('#alert_mask').css({
        'height': $(window).height() + 'px',
        'z-index': that.settings.zIndex - 1
      });

      // 按钮样式
      $('.alert_left_btn').css({
        'color': that.settings.lFontColor,
        'backgroundColor': that.settings.lBgColor
      })

      $('.alert_right_btn').css({
        'color': that.settings.rFontColor,
        'backgroundColor': that.settings.rBgColor
      })
    },

    // 弹窗系列事件
    trigger: function(element, event){
      var that = this,
        $this = $(element);

      // 关闭弹窗事件触发
      $('.alert_close_btn, .alert_left_btn, .alert_right_btn').on('click',function(){
        that.hide();
        $('.alert_panel').removeClass('show');
        $('#alert_mask').remove();
      });

      // 左边按钮回调处理
      if($.isFunction(that.settings.lCallback)){
        $('.alert_left_btn').on('click',function(){
          that.settings.lCallback();
        });
      }

      // 右边按钮回调处理
      if($.isFunction(that.settings.rCallback)){
        $('.alert_right_btn').on('click',function(){
          that.settings.rCallback();
        });
      }
    }
  };

  // 调用
  $.fn[pluginName] = function(options) {
    this.each(function() {
      if (!$.data(this, "plugin_" + pluginName)) {
        $.data(this, "plugin_" + pluginName, new AlertBox(this, options));     
      }
      new AlertBox(this, options);
    });
   return this;
  };

})(jQuery, window, document)
