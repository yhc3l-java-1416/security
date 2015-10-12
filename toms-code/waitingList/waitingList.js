var items = [];
var lastId = 0;
// example object {id:2, name:"Lego"}
module.exports.getAll = function () {
    return items;
};
module.exports.get = function (id) {
    // Get a specific item from our items based on it's id
    for (var i = 0; i < items.length; i++) {
        if (items[i].id === id) {
            return items[i];
        }
    }
};
module.exports.create = function (obj) {
    // give our object an id and add it to our items
    var tempId = lastId + 1;
    obj.id = tempId;
    items.push(obj);
    lastId = tempId;
    return lastId;
};
module.exports.remove = function (id) {
    // Remove object with the given id from the items array
    var objectId;
    for (var i = 0; i < items.length; i++) {
        if (items[i].id === id) {
            objectId = i;
        }
    }
    if (objectId !== undefined) {
        items.splice(objectId, 1);
    }
};
module.exports.update = function (id, obj) {
    // Update the object with the given id.
    // Can delete original and then add the object 
    var objectId;
    obj.id = id;
    for (var i = 0; i < items.length; i++) {
        if (items[i].id === id) {
            objectId = i;
        }
    }
    if (objectId !== undefined) {
        items.splice(objectId, 1, obj);
    } else {
        items.push(obj);
    }
};