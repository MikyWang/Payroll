
var modalModel = {
    title : ko.observable(''),
    body : ko.observable('')
};

function initModal(title, body) {
    modalModel.title(title);
    modalModel.body(body);
    $('#myModal').modal('show');
}

ko.attach("modalModel", modalModel);
