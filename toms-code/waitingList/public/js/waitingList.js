/*jslint browser:true */
/*global alert: false, confirm: false, console: false, Debug: false, opera: false, prompt: false, WSH: false */
var host = window.location.origin,
    waitingList = {};
waitingList.getAll = function (callback) {
    request({
        url: host + "/waitinglist",
        method: "GET",
        json: true
    }, function (error, response, body) {
        if (error === null) {
            callback(null, body);
        }
    });
};
waitingList.item = {};
waitingList.item.get = function (id, callback) {
    request({
        url: host + "/waitinglist/" + id,
        method: "GET",
        json: true
    }, function (error, response, body) {
        if (error === null) {
            callback(null, body);
        }
    });
};
waitingList.item.create = function (obj, callback) {
    request({
        url: host + "/waitinglist",
        method: "POST",
        body: JSON.stringify(obj),
        json: true
    }, function (error, response, body) {
        if (error === null) {
            callback(null, body);
        }
    });
};
waitingList.item.update = function (id, obj, callback) {
    request({
        url: host + "/waitinglist/" + id,
        method: "PUT",
        body: JSON.stringify(obj),
        json: true
    }, function (error, response, body) {
        if (error === null) {
            callback(null, body);
        }
    });
};
waitingList.item.remove = function (id, callback) {
    request({
        url: host + "/waitinglist/" + id,
        method: "DELETE",
        json: true
    }, function (error, response, body) {
        if (error === null) {
            callback(null, body);
        }
    });
};