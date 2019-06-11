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

	(function () {
	    //仅在不支持 placeholder 的时候执行
	    if (!('placeholder' in document.createElement('input'))) {
	        $(document).ready(function () {
	            var doc = document,
	                inputs = doc.getElementsByTagName('input'),
	                supportPlaceholder = 'placeholder' in doc.createElement('input'),
	                placeholder = function placeholder(input) {
	                var text = input.getAttribute('placeholder'),
	                    defaultValue = input.defaultValue;

	                if (defaultValue == '') {
	                    input.value = text;
	                }
	                input.onfocus = function () {
	                    if (input.value === text) {
	                        this.value = '';
	                        $(input).removeClass("placeholder-ie");
	                    }
	                };
	                input.onblur = function () {
	                    if (input.value === '') {
	                        $(input).addClass("placeholder-ie");
	                        this.value = text;
	                        '';
	                    }
	                };
	            };

	            if (!supportPlaceholder) {
	                for (var i = 0, len = inputs.length; i < len; i++) {
	                    var input = inputs[i],
	                        text = input.getAttribute('placeholder');
	                    if (input.type === 'text' && text) {
	                        placeholder(input);
	                    }
	                }
	            }

	            //对password框的特殊处理
	            $("input[type='password']").each(function () {
	                //1.创建一个text框 2获取焦点和失去焦点的时候切换
	                var pwdField = $(this);
	                var pwdVal = pwdField.attr('placeholder');
	                pwdField.after('<input  class="login-input-ie" type="text" value=' + pwdVal + ' autocomplete="off" />');
	                var pwdPlaceholder = $(this).siblings('.login-input-ie');
	                pwdPlaceholder.show();
	                pwdField.hide();

	                pwdPlaceholder.focus(function () {
	                    pwdPlaceholder.hide();
	                    pwdField.show();
	                    pwdField.focus();
	                });

	                pwdField.blur(function () {
	                    if (pwdField.val() == '') {
	                        pwdPlaceholder.show();
	                        pwdField.hide();
	                    }
	                });
	            });
	        });
	    }
	})();

/***/ })
/******/ ]);