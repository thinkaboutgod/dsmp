/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};

/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {

/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;

/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};

/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);

/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;

/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}


/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;

/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;

/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";

/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ (function(module, exports) {

	"use strict";

	(function ($) {
		$.extend({
			tipsBox: function tipsBox(options) {
				options = $.extend({
					obj: null, //jq对象，要在那个html标签上显示
					str: "+1", //字符串，要显示的内容;也可以传一段html，如: "<b style='font-family:Microsoft YaHei;'>+1</b>"
					startSize: "12px", //动画开始的文字大小
					endSize: "25px", //动画结束的文字大小
					interval: 600, //动画时间间隔
					color: "red", //文字颜色
					callback: function callback() {} //回调函数
				}, options);
				$("body").append("<span class='nums'>" + options.str + "</span>");
				var box = $(".nums");
				var left = options.obj.offset().left + options.obj.width() / 2;
				var top = options.obj.offset().top - options.obj.height();
				box.css({
					"position": "absolute",
					"left": left + "px",
					"top": top + "px",
					"z-index": 9999,
					"font-size": options.startSize,
					"line-height": options.endSize,
					"color": options.color
				});
				box.animate({
					"font-size": options.endSize,
					"opacity": "0",
					"top": top - parseInt(options.endSize) + "px"
				}, options.interval, function () {
					box.remove();
					options.callback();
				});
			}
		});
	})(jQuery);

	var myPlayer = videojs('my-video', {
		"controls": true,
		"autoplay": false,
		"preload": "auto",
		"loop": false,
		controlBar: {
			captionsButton: false,
			chaptersButton: false,
			playbackRateMenuButton: true,
			LiveDisplay: true,
			subtitlesButton: false,
			remainingTimeDisplay: true,

			progressControl: true,

			volumeMenuButton: {
				inline: false,
				vertical: true
			}, //竖着的音量条
			fullscreenToggle: true
		}
	}, function () {});
	videojs("my-video").ready(function () {
		var myPlayer = this;
		this.play();
	});
	$('.vjs-styles-defaults').remove();
	$('.vjs-styles-dimensions').remove();

	$("#textarea").keyup(function (ev) {
		if ($(this).val().length >= 1 && $(this).val().length <= 200) {
			$(".send-comment").attr("disabled", false);
			$(".send-comment").css({
				"backgroundColor": "#00C356",
				"cursor": "pointer"
			});
		} else {
			$(".send-comment").attr("disabled", true);
			$(".send-comment").css({
				"backgroundColor": "#C0C0C0",
				"cursor": "default"
			});
		}
	});

	function niceIn(prop) {
		prop.find('i').addClass('niceIn');
		setTimeout(function () {
			prop.find('i').removeClass('niceIn');
		}, 1000);
	}

	//计数器
	var videoCount = 0;

	/**
	 * 右侧菜单点击功能
	 */
	var clickBind = function clickBind() {
		$(".detail-content ul li").click(function (ev) {
			var self = this;
			myPlayer.src($(self).find("a").attr("mediasrc"));
			myPlayer.poster($(self).find("a").attr("photom"));
			var str = "位置：<a href='http://tv.jxedt.com/'>学车视频</a>   &gt; ";
			switch ($(self).find("a").attr("subjecttype")) {
				case "1":
					$(".video-detail-page p").html(str + " 科目一：" + $(self).find("a").text());
					break;
				case "2":
					$(".video-detail-page p").html(str + " 科目二：" + $(self).find("a").text());
					break;
				case "3":
					$(".video-detail-page p").html(str + " 科目三：" + $(self).find("a").text());
					break;
				case "4":
					$(".video-detail-page p").html(str + " 科目四：" + $(self).find("a").text());
					break;
				case "5":
					$(".video-detail-page p").html(str + " 拿本：" + $(self).find("a").text());
					break;
				default:
					break;
			}
			$(".video-down").attr("vediosrc", $(self).find("a").attr("vediosrc"));
			$(".play .play-num").text($(self).find("a").attr("playcount"));
			$(".fabulous .fabulous-num").text($(self).find("a").attr("likecount"));
			$(".detail-content ul li").map(function (i, v) {
				$(v).find("div").removeClass("selected-circle").addClass("circle");
				$(v).find("a").removeClass("selected-text");
			});
			$(self).find("div").addClass("selected-circle");
			$(self).find("a").addClass("selected-text");
			window.urlId = $(self).find("a").attr("urlid");
			myPlayer.play();
			videoCount = $(".detail-content ul li a").index($(".selected-text"));
			sendAjax($(self).find("a").attr("urlid"), 1, false);
			videoCountNum($(self).find("a").attr("urlid"));
		});
	};

	/**
	 * 视频播放结束事件
	 */
	myPlayer.on("ended", function () {
		videoCount = videoCount + 1;
		if (videoCount > Number($(".detail-content ul #RecDetailTotal").val()) * 11) {
			videoCount = 0;
		}
		if ($(".detail-content ul li .selected-text").offset().top > 700) {
			// var timer = setInterval(function () {
			// 	var top = $(".detail-content ul li .selected-text").scrollTop();
			// 	if (top >= $(".detail-content ul").offset().top - 20) {
			// 		clearInterval(timer);
			// 	}
			// 	$(".detail-content ul").scrollTop(top += 10);
			// }, 20);
			$(".detail-content ul").animate({
				scrollTop: $(".detail-content ul li .selected-text").offset().top + $(".detail-content ul").scrollTop() - 400
			}, 1500);
		}
		$($(".detail-content ul li")[videoCount]).trigger("click");
	});

	var videoTime = 0;
	/**
	 * 视频点赞
	 */
	$(".fabulous").click(function () {
		//点击事件避免触发多次
		var self = this;
		if (!$(".comment-login-2").length) {
			$(self).next().next().css("right", "146px").show();
			isLogin($($(self).next()));
			return false;
		}
		if (videoTime == 0) {
			videoTime = 1; //设定间隔时间（秒）
			//启动计时器，倒计时time秒后自动关闭计时器。
			var index = setInterval(function () {
				videoTime--;
				if (videoTime == 0) {
					clearInterval(index);
				}
			}, 1000);
			if ($(self).find(".light-up").length == 0) {
				$.ajax({
					type: "GET",
					url: "updateMediaLikeCount.htm?topicId=" + urlId,
					dataType: 'json',
					error: function error(data) {
						if (data.statusText == "OK") {
							$.tipsBox({
								obj: $(self),
								str: "+1",
								callback: function callback() {}
							});
							niceIn($(self));
							$(self).find(".fabulous-img").addClass("light-up");
							$(self).find(".fabulous-num").css("color", "#FFA630");
							$(self).find(".fabulous-num").text(Number($(self).find(".fabulous-num").text()) + 1);
							//将用户点赞视频取出
							var videoLike = JSON.parse(localStorage.getItem("video-like"));
							//将新的视频放入
							videoLike.push(urlId);
							localStorage.setItem("video-like", JSON.stringify(videoLike));
						}
					}
				});
			}
		}
	});

	/**
	 * 评论点赞
	 */
	var triggerBind = function triggerBind() {
		var time = 0;
		$(".user").click(function () {
			//点击事件避免触发多次
			var self = this;
			if (!$(".comment-login-2").length) {
				$(self).next().show();
				isLogin(self);
				return false;
			}
			if (time == 0) {
				time = 1; //设定间隔时间（秒）
				//启动计时器，倒计时time秒后自动关闭计时器。
				var index = setInterval(function () {
					time--;
					if (time == 0) {
						clearInterval(index);
					}
				}, 1000);
				if ($(self).find(".light-up").length == 0) {
					$.ajax({
						type: "GET",
						url: "updateCommentLikeCount.htm?tiebaReplyId=" + $(self).parent().parent().attr("commentid"),
						dataType: 'json',
						error: function error(data) {
							if (data.statusText == "OK") {
								$.tipsBox({
									obj: $(self),
									str: "+1",
									callback: function callback() {}
								});
								niceIn($(self));
								$(self).find(".aggrement").addClass("light-up");
								$(self).find(".num").css("color", "#FFA630");
								$(self).find(".num").text(Number($(self).find(".num").text()) + 1);
								// 将用户点赞评论取出
								var userLike = JSON.parse(localStorage.getItem("user-like"));
								//将新的评论放入
								userLike.push($(self).parent().parent().attr("commentid"));
								localStorage.setItem("user-like", JSON.stringify(userLike));
							}
						}
					});
				}
			}
		});
	};

	function isLogin(self) {
		setTimeout(function () {
			$(self).next().hide();
		}, 5000);
	}

	function sendAjax(id, currPage, flag) {
		$.ajax({
			type: "GET",
			url: "detailCommentPage/" + currPage + ".htm?topicId=" + id,
			dataType: 'json',
			error: function error(data) {
				if (data.statusText == "OK") {
					$(".comment-list .comment-list-detail").remove();
					$(".comment-list input").remove();
					$(".comment-list #no-data").remove();
					$(".video-list-page").before(data.responseText);
					$("#pagination_video_detail").jlPaging("setPage", {
						currPage: currPage,
						totalPage: $($(".comment-list input")[0]).val()
					});
					$($(".comment-list input")[0]).val() == 1 ? $(".video-list-page").hide() : $(".video-list-page").show();
					triggerBind();
					if ($("#commentListTag").length > 0 && flag) {
						var t = $("#commentListTag").offset().top;
						$(window).scrollTop(t);
					}
					setTimeout(function () {
						renderVideoNum();
						renderCommentNum();
					}, 300);
				}
			}
		});
	}

	var flagContent = 1;
	$(".detail-content ul").on('scroll', function (ev) {
		var viewHeight = $(this).height(); //可见高度  
		var contentHeight = $(".detail-content ul").get(0).scrollHeight; //内容高度  
		var scrollHeight = $(this).scrollTop(); //滚动高度  
		if (contentHeight - viewHeight <= scrollHeight) {
			var urlId = $(".detail-content ul li ").find(".selected-circle").next().attr("urlid");
			var realTopicId = $($(".detail-content ul li a")[0]).attr("urlid");
			urlId = urlId == undefined ? realTopicId : urlId;
			if (flagContent < Number($(".detail-content ul #RecDetailTotal").val())) {
				flagContent++;
				$.ajax({
					type: "GET",
					url: "detailRecPage/" + $($(".detail-content ul input")[1]).val() + ".htm?realTopicId=" + realTopicId + "&topicId=" + urlId,
					dataType: 'json',
					error: function error(data) {
						if (data.statusText == "OK") {
							$(".detail-content ul input").remove();
							$(".detail-content ul").append(data.responseText);
							clickBind();
						}
					}
				});
			}
		}
	});

	$(document).ready(function () {

		String.prototype.trim = function () {
			return this.replace(/(^\s*)|(\s*$)/g, "");
		};
		// 初始化视频播放选中
		$($(".detail-content ul li")[0]).find("div").addClass("selected-circle");
		$($(".detail-content ul li")[0]).find("a").addClass("selected-text");
		triggerBind();
		clickBind();
		window.urlId = $($(".detail-content ul li a")[0]).attr("urlid");
		videoCountNum(urlId);
		$("#pagination_video_detail").jlPaging({
			css: 'css-2',
			totalPage: $($(".comment-list input")[0]).val(),
			previousPage: '<',
			nextPage: '>',
			isShowFL: false,
			isResetPage: true,
			isShowPageSizeOpt: false,
			isShowSkip: false,
			showPageNum: 10,
			isShowRefresh: false,
			isShowTotalPage: false,
			isShowTotalSize: false,
			callBack: function callBack(currPage) {
				sendAjax(urlId, currPage, true);
			}
		});
		if (!localStorage.getItem("user-like")) {
			localStorage.setItem("user-like", JSON.stringify([]));
		}
		if (!localStorage.getItem("video-like")) {
			localStorage.setItem("video-like", JSON.stringify([]));
		}
		//判断分页是否展示 
		$($(".comment-list input")[0]).val() == 1 ? $(".video-list-page").hide() : $(".video-list-page").show();

		$(".detail-video").bind("contextmenu", function (e) {
			return false;
		});

		renderVideoNum();
		renderCommentNum();

		//动态修改下载页面视频地址
		$(".video-down").attr("vediosrc", $($(".detail-content ul li")[0]).find("a").attr("vediosrc"));
	});

	$(".video-down").click(function () {
		var self = this;
		if (!$(".comment-login-2").length) {
			$(self).next().css("right", "48px").show();
			isLogin(self);
			return false;
		}
		download($(".video-down").attr("vediosrc"));
		// window.location.href=$(".video-down").attr("vediosrc");
	});

	function download(url) {
		var iframe = document.createElement("iframe");
		iframe.style.display = "none";
		iframe.src = url;
		document.body.appendChild(iframe);
	}

	// $(".video-down").click(function(){
	// 	var iframe = document.createElement("iframe")
	//     iframe.style.display = "none";
	//     iframe.src = $(".video-down").attr("data-src");
	//     document.body.appendChild(iframe);
	// });

	/**
	 * 视频播放量
	 * @param {*} urlId 视频ID
	 */
	function videoCountNum(urlId) {
		$.ajax({
			type: "GET",
			url: "updateMediaPlayCount.htm?topicId=" + urlId,
			dataType: 'json',
			error: function error(data) {
				if (data.statusText == "OK") {
					$(".detail-video-num .play .play-num").text(data.responseText);
				}
			}
		});
	}

	function commentAlert(self, text) {
		$(self).next().show();
		$(self).next().text(text);
		setTimeout(function () {
			$(self).next().hide();
		}, 5000);
	}

	$(".comment-textarea .send-comment").click(function () {
		var self = this;
		var content = $(".comment-textarea #textarea").val();
		if (content.trim() == "") {
			commentAlert(self, "评论信息不可以为空哦~");
			return false;
		}
		$.ajax({
			type: "post",
			url: "addMediaReply.htm",
			dataType: 'json',
			data: {
				topicId: urlId,
				content: content
			},
			error: function error(data) {
				if (data.statusText == "OK") {
					if (data.responseText == "cantComment") {
						commentAlert(self, "您暂时不能评论哦~");
						return false;
					} else if (data.responseText == "false") {
						commentAlert(self, "系统错误，请联系管理员~");
						return false;
					} else if (data.responseText == "isCommented") {
						commentAlert(self, "不能重复评论哦~");
						return false;
					}
					$(".comment-list .comment-list-detail").remove();
					$(".comment-list input").remove();
					$(".comment-list #no-data").remove();
					$(".comment-textarea #textarea").val("");
					$(".video-list-page").before(data.responseText);

					$(".send-comment").attr("disabled", true);
					$(".send-comment").css({
						"backgroundColor": "#C0C0C0",
						"cursor": "default"
					});
				}
			}
		});
	});

	/**
	 * 查看用户是否对评论点赞
	 */
	function renderCommentNum() {
		var userLike = JSON.parse(localStorage.getItem("user-like"));
		var userList = $(".comment-list .comment-list-detail");
		for (var j = 0; j < userLike.length; j++) {
			for (var i = 0; i < userList.length; i++) {
				if (userLike[j] == $(userList[i]).attr("commentid")) {
					$($(".comment-list .comment-list-detail")[i]).find(".aggrement").addClass("light-up");
					$($(".comment-list .comment-list-detail")[i]).find(".num").css("color", "#FFA630");
					// $(userList[i]).find(".aggrement").addClass("light-up");
				}
			}
		}
	}

	/**
	 * 查看用户是否对视频点赞
	 */
	function renderVideoNum() {
		var videoLike = JSON.parse(localStorage.getItem("video-like"));
		for (var i = 0; i < videoLike.length; i++) {
			if (videoLike[i] == $(".detail-content ul li .selected-text").attr("urlid")) {
				$(".fabulous .fabulous-img").addClass("light-up");
				$(".fabulous .fabulous-num").css("color", "#FFA630");
			} else {
				$(".fabulous .fabulous-img").removeClass("light-up");
				$(".fabulous .fabulous-num").css("color", "#999999");
			}
		}
	}

/***/ })
/******/ ]);