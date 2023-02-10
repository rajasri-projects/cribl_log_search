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

                var request = getURL('SEARCH') + "?query=" + encodeURIComponent($('#searchInputTextId').val());
                var promise = callServer(request, 'GET', null, true,
                    'application/json; charset=utf-8');
                promise.done(function(data, textStatus, jqXHR) {
                    $('.searchFormSpinner').hide();
                    $('#searchSubmitBtnId').removeAttr('disabled');
                });
                promise.fail(function(jqXHR, textStatus, errorThrown) {
                    $('.searchFormSpinner').hide();
                    $('#searchSubmitBtnId').removeAttr('disabled');
                    displayMessage('#messageSearchFormId', 2, jqXHR.responseJSON.errors[0]);
                });
            } else {
                $('.searchForm').addClass('was-validated');
            }
            break;

        default:
            break;
    }
});