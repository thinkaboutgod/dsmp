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

	'use strict';

	// 需要动态传入信息
	$(document).ready(function () {

	    var reg = /^http:\/\/[A-Za-z]{2,}.jxedt.com\/[0-9]{3,}\/.*$/;
	    var regInfo = /^http:\/\/zhinan.jxedt.com\/info\/[0-9]{3,}.*$/;
	    var regTv = /^http:\/\/tv.jxedt.com\/info\/[0-9]{3,}.*$/;
	    var regZhinan = /^http:\/\/zhinan.jxedt.com\/target\/.*$/;
	    if (reg.test(document.location.href) || regInfo.test(document.location.href) || regZhinan.test(document.location.href) || regTv.test(document.location.href)) {
	        /*$("a.nh").hide();*/
	        $("#footer").hide();
	    }

	    // $('.right-nav-common').hide(); //隐藏go to top按钮
	    $(window).scroll(function () {
	        if (reg.test(document.location.href) || regInfo.test(document.location.href) || regZhinan.test(document.location.href) || regTv.test(document.location.href)) {
	            /*$("a.nh").hide();*/
	            $(".right-nav-common").hide();
	        } else {
	            if ($(this).scrollTop() > 100) {
	                $('.right-nav-common').fadeIn();
	            } else {
	                $('.right-nav-common').fadeOut();
	            }
	        }
	    });

	    $('.gotop').click(function () {
	        $('html ,body').animate({
	            scrollTop: 0
	        }, 500);
	        return false;
	    });
	});
	var flag = true;

	$('.external-header span').each(function () {
	    $(this).click(function () {
	        $(".external-header").find("a").removeClass("active_footer");
	        $(this).find("a").addClass('active_footer');
	        $('.external-centext').eq($(this).index()).show().siblings(".external-centext").hide();
	    });
	});
/***/ })
/******/ ]);