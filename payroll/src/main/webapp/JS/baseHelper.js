function isNullOrEmpty(object) {
    return object == null || object == '' || object == undefined;
}

ko.bindingHandlers.showVisible = {
    init: function (element, valueAccessor) {
        var value = ko.utils.unwrapObservable(valueAccessor());
        if (value) {
            $(element).show();
        } else {
            $(element).hide();
        };
    },
    update: function (element, valueAccessor) {
        var value = ko.utils.unwrapObservable(valueAccessor());
        if (value) {
            $(element).show(200).animate({
                opacity: '1'
            });
        } else {
            $(element).animate({
                opacity: '0.2'
            }).hide(200);
        };
    }
};

ko.bindingHandlers.disabled = {
    update: function (element, valueAccessor) {
        ko.bindingHandlers.disable.update(element, valueAccessor);
        var value = ko.utils.unwrapObservable(valueAccessor());
        ko.utils.toggleDomNodeCssClass(element, 'disabled', value);
    }
}

$(document).ready(function () {
    var c = "jscookietest=valid";
    document.cookie = c;
    if (document.cookie.indexOf(c) == -1) {
        alert('请开启cookie,否则无法正常浏览本网页!');
    }
})