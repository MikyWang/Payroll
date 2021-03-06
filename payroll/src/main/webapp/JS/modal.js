var modalModel = {
  title: ko.observable(''),
  body: ko.observable(''),
  disableClose: ko.observable(false),
  callFirst: ko.observable(false),
  callSecond: ko.observable(false),
  firstButton: ko.observable(''),
  secondButton: ko.observable(''),
  firstClick: buttonClick,
  secondClick: buttonClick,
  callback: function () { },
  thisArgs: ko.observable(),
}

function buttonClick() {
  modalModel.callback(this.thisArgs())
}

function oneButtonModal(title, body, buttonName, buttonFunc, thisArgs) {
  clearModal()
  modalModel.title(title)
  modalModel.body(body)
  modalModel.firstButton(buttonName)
  modalModel.thisArgs(thisArgs)
  modalModel.callback = buttonFunc
  modalModel.callFirst(true)
  $('#myModal').modal('show')
}

function initModal(title, body) {
  clearModal()
  modalModel.title(title)
  modalModel.body(body)
  $('#myModal').modal('show')
}

function resetButton() {
  modalModel.disableClose(false)
}

function initProcessBar(title) {
  clearModal()
  $.ajax({
    url: 'processbar',
    success: function (response) {
      modalModel.title(title)
      modalModel.body(response)
      modalModel.disableClose(true)
      $('#myModal').modal(
        {
          backdrop: 'static',
          keyboard: false,
          show: true
        })
    }
  })
}

function clearModal() {
  modalModel.title('')
  modalModel.body('')
  modalModel.firstButton('')
  modalModel.secondButton('')
  modalModel.callFirst(false)
  modalModel.callSecond(false)
  modalModel.callback = function () { }
  modalModel.disableClose(false)
}

function closeProcessbar() {
  clearModal()
}

ko.attach('modalModel', modalModel)
