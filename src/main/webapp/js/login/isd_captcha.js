"use strict";
! function(e) {
	function c(e, c) {
		310 === e.type ? (t(c.urlSlideCss), r(c.urlSlideJs, function() {
			new CaptchaSlide(e).bootstrap()
		})) : 320 === e.type ? (t(c.urlClickCss), r(c.urlClickJs, function() {
			new CaptchaClick(e).bootstrap()
		})) : (t(c.urlGestureCss), r(c.urlGestureJs, function() {
			new CaptchaGesture(e).bootstrap()
		})), navigator.userAgent.indexOf("MSIE") >= 0 && navigator.userAgent.indexOf("Opera") < 0 && t(c.urlCaptchaIECss)
	}

	function t(c) {
		for (var t = !1, r = document.getElementsByTagName("link"), s = 0; s < r.length; s++) r[s].href === e.location.protocol +
			c && (t = !0);
		if (!t) {
			var n = document.createElement("link");
			n.rel = "stylesheet", n.type = "text/css", n.href = c;
			var a = document.getElementsByTagName("script")[0];
			a.parentNode.insertBefore(n, a)
		}
	}

	function r(c, t) {
		for (var r = !1, s = null, n = document.getElementsByTagName("script"), a = 0; a < n.length; a++) n[a].src === e.location
			.protocol + c && (s = n[a], r = !0);
		var o = document.createElement("script");
		o.src = c;
		var l = document.getElementsByTagName("script"),
			u = l[l.length - 1];
		u.parentNode.insertBefore(o, u), o.onload = o.onreadystatechange = function() {
			o.readyState && !/complete|loaded/.test(o.readyState) || (t(), o.onload = null, o.onreadystatechange = null)
		}, r && u.parentNode.removeChild(s)
	}
	var s = function(t) {
		return new function() {
			var s = {
				scriptCount: 0,
				urlClickJs: "//j1.58cdn.com.cn/resource/xxzl/captcha/js/click.js?v=20190306193135",
				urlClickCss: "//c.58cdn.com.cn/resource/xxzl/captcha/css/click.css?v=201901160336",
				urlSlideJs: "//j1.58cdn.com.cn/resource/xxzl/captcha/js/slide.js?v=20180726165831",
				urlSlideCss: "//c.58cdn.com.cn/resource/xxzl/captcha/css/slide.css?v=105",
				urlGestureJs: "//j1.58cdn.com.cn/resource/xxzl/captcha/js/gesture.js?v=20190322190120",
				urlGestureCss: "//c.58cdn.com.cn/resource/xxzl/captcha/css/gesture.css?v=105",
				urlCaptchaIECss: "//c.58cdn.com.cn/resource/xxzl/captcha/css/dvc_captcha_for_ie.css?v=99",
				urlJquery: "//j1.58cdn.com.cn/resource/xxzl/captcha/js/captcha.jquery.min.1.9.1.js?v=33"
			};
			this.init = function() {
				e.jQuery ? c(t, s) : r(s.urlJquery, function() {
					c(t, s)
				})
			}
		}
	};
	e.ISDCaptcha = e.ISDCaptcha = s
}(window);
