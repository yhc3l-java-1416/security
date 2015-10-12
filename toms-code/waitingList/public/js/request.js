/*jslint browser:true */
/*global ActiveXObject: false, alert: false, confirm: false, console: false, Debug: false, opera: false, prompt: false, WSH: false */
var request = function (obj, callback) {
		"use strict";
		var body, xhReq, header;
		if (!window.XMLHttpRequest) {
			window.XMLHttpRequest = function () {
				try {
					return new ActiveXObject('MSXML2.XMLHTTP.3.0');
				} catch (ex) {
					return null;
				}
			};
		}
		// Tidy up the input
		if (typeof obj === "object") {
			if (obj.url) {
				obj.uri = obj.url;
			}
			if (obj.method && typeof obj.method === "string" && obj.method.length > 0) {
				obj.method = obj.method.toUpperCase();
			} else {
				obj.method = "GET";
			}
			if (obj.body && typeof obj.body === "object") {
				obj.body = JSON.stringify(obj.body);
			}
			if (obj.body === undefined) {
				obj.body = null;
			}
		}
		xhReq = new XMLHttpRequest();
		xhReq.open(obj.method, obj.uri, true);
		if (obj.headers && typeof obj.headers === "object") {
			for (header in obj.headers) {
				if (obj.headers.hasOwnProperty(header)) {
					xhReq.setRequestHeader(header, obj.headers[header]);
				}
			}
		}
		if ((obj.json && obj.method === "POST") || obj.method === "PUT") {
			xhReq.setRequestHeader("Content-type", "application/json");
		}
		xhReq.onreadystatechange = function () {
			if (xhReq.readyState === 4) {
				var firstStatusNumber = Number(String(xhReq.status)[0]);
				if (firstStatusNumber === 0 || firstStatusNumber === 4 || firstStatusNumber === 5) {
					if (callback) {
						callback("Server returned status code " + xhReq.status, xhReq, null);
					}
				} else {
					if (obj.json) {
						body = JSON.parse(xhReq.responseText);
					} else {
						body = xhReq.responseText;
					}
					if (callback) {
						callback(null, xhReq, body);
					}
				}
			}
		};
		xhReq.send(obj.body);
	};