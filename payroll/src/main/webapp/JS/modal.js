window.userName = null;

var modalModel = {
    title : ko.observable(''),
    body : ko.observable('')
};

function initModal(title, body) {
    modalModel.title(title);
    modalModel.body(body);
}

ko.attach("modalModel", modalModel);
