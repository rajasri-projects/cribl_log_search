$(document).ready(function(){


});

function callServer(url, type, data, processData, contentType) {
    return $.ajax({
        url: url,
        type: type,
        data: data,
        processData: processData,
        contentType: contentType,
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true
    });
}

function getURL(endPoint) {
    switch (endPoint) {
        case 'SEARCH':
            return '/search';
        default:
            break;
    }
}

function hideMessage(id) {
    $(id).css('display', 'none');
}

function displayMessage(id, messageType, message) {
    $(id).css('display', 'block');
    if (messageType === 1) {
        $(id).addClass('message-green');
        $(id).removeClass('message-red');
        $(id).html(message);
    } else if (messageType === 2) {
        $(id).addClass('message-red');
        $(id).removeClass('message-green');
        $(id).html(message);
    }
}

function isString(val) {
    if (val === "" || val == null || val === undefined || $.trim(val) === "") {
        return false;
    }
    return true;
}

function getDefaultCustomValidityMessage() {
    return "Input is invalid";
}
