$(document).ready(function(){

});

$("#searchInputTextId").keyup(function() {
  isValidSearchInput();
});

function validSearch() {
    var searchInputIsValid = isValidSearchInput();
    return searchInputIsValid;
}

function isValidSearchInput() {
    var isValidSearch = true;

    if (!isString($('#searchInputTextId').val())) {
        $('#searchInputTextId').get(0).setCustomValidity(getDefaultCustomValidityMessage());
        isValidSearch = false;
    } else {
        $('#searchInputTextId').get(0).setCustomValidity('');
    }

    return isValidSearch;
}

var xhr;
var seenBytes = 0;
var interval;
$('.container').on('click', '.btn', function(event) {
    event.preventDefault();

    var action = this.id;
    switch (action) {
        case 'searchSubmitBtnId':
            if (validSearch()) {
                hideMessage('#messageSearchFormId');
                $('.searchForm').removeClass('was-validated');

                $('#searchSubmitBtnId').attr('disabled', 'disabled');
                $('.searchFormSpinner').show();

                var url = getURL('SEARCH') + "?query=" + encodeURIComponent($('#searchInputTextId').val());

                if (xhr) {
                    xhr.abort();
                }
                xhr = new XMLHttpRequest();
                xhr.open("GET", url, true);
                xhr.send();

                seenBytes = 0;

                if (interval) {
                    clearInterval(interval);
                }
                interval = setInterval(displayData, 1000);
            } else {
                $('.searchForm').addClass('was-validated');
            }
            break;

        default:
            break;
    }
});

function displayData() {
    var newData = xhr.response.substr(seenBytes);
    console.log("Display");
    $("#searchDataId").append(newData);
    seenBytes = xhr.responseText.length;
}