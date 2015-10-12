/*jslint browser:true */
/*global localStorage: false, $:false, waitingList: false, alert: false, confirm: false, console: false, Debug: false, opera: false, prompt: false, WSH: false */
$(function () {
    "use strict";
    var updateList,
        oldNames = [],
        audio = document.querySelector("audio"),
        playSound = function () {
            audio.load();
            audio.play();
        },
        block = false;
    if (localStorage.username === undefined) {
        $("#userInfo").show();
        $("#list").hide();
    } else {
        $("#user").text(localStorage.username);
        $("#userInfo").hide();
        $("#list").show();
    }
    $("#save").click(function () {
        localStorage.username = $("#username").val();
        $("#user").text(localStorage.username);
        $("#userInfo").hide();
        $("#list").show();
    });
    updateList = function (cb) {
        waitingList.getAll(function (error, items) {
            var list, createListItem, deleteListItem, moveListItem, i, currentIndex,
                newNames = [];
            // Remove any duplicates just incase. Duplicates can really mess everything up.
            items = items.filter((function () {
                var seen = {};
                return function (elem) {
                    var k = elem.name;
                    return (seen[k] === 1) ? 0 : seen[k] = 1;
                };
            }()));
            items.forEach(function (val) {
                newNames.push(val.name);
            });
            deleteListItem = function (index) {
                $("#waitingList li:nth-child(" + (index + 1) + ")").remove();
                oldNames.splice(index, 1);
            };
            moveListItem = function (from, to) {
                var output = oldNames.splice(from, 1);
                oldNames.splice(to, 0, output[0]);
                if (to === 0) {
                    $("#waitingList li:nth-child(" + (from + 1) + ")").insertBefore($("#waitingList li:nth-child(1)"));
                } else {
                    $("#waitingList li:nth-child(" + (from + 1) + ")").insertAfter($("#waitingList li:nth-child(" + to + ")"));
                }
            };
            createListItem = function (index, obj) {
                var listItem = $('<li class="new-item">'),
                    listText = $("<span>"),
                    deleteItemButton = $('<button class="icon-trash delete-button">');
                listText.text(obj.name);
                listItem.append(listText);
                if (localStorage.username === obj.name || localStorage.username === "Tom") {
                    deleteItemButton.click(function () {
                        waitingList.item.remove(obj.id, function () {
                            updateList();
                        });
                    });
                    listItem.append(deleteItemButton);
                }
                if (index < oldNames.length) {
                    listItem.insertAfter($("#waitingList li:nth-child(" + index + ")"));
                } else {
                    list.append(listItem);
                }
                if (localStorage.username === "Tom") {
                    playSound();
                }
                oldNames.splice(index, 0, obj.name);
            };
            if (error === null) {
                list = $("#waitingList");
                oldNames.forEach(function (val) {
                    currentIndex = newNames.indexOf(val);
                    if (currentIndex === -1) {
                        deleteListItem(oldNames.indexOf(val));
                    }
                });
                for (i = 0; i < items.length; i = i + 1) {
                    currentIndex = oldNames.indexOf(newNames[i]);
                    if (currentIndex === -1) {
                        createListItem(i, items[i]);
                    } else if (currentIndex !== i) {
                        moveListItem(currentIndex, i);
                    }
                }
            }
            if (typeof cb === "function") {
                cb();
            }
        });
    };
    $("#addButton").click(function () {
        if (oldNames.indexOf(localStorage.username) === -1 && !block) {
            var obj = {};
            obj.name = localStorage.username;
            block = true;
            waitingList.item.create(obj, function (error) {
                if (error === null) {
                    updateList(function () {
                        block = false;
                    });
                }
            });
        } else {
            $("#alreadyListed").show();
            setTimeout(function () {
                $("#alreadyListed").hide();
            }, 3000);
        }
    });
    $("#resetUser").click(function () {
        $("#userInfo").show();
        $("#list").hide();
    });
    setInterval(function () {
        updateList();
    }, 4000);
    updateList();
});