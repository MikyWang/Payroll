var navModel = {
  userNumber: ko.observable(''),
  userName: ko.observable(''),
  sex: ko.observable(''),
  education: ko.observable(''),
  departmentNumber: ko.observable(''),
  departmentName: ko.observable(''),
  power: ko.observable('')
}

navModel.isLogin = ko.computed(function () {
  return !isNullOrEmpty(navModel.userNumber())
}, this)

function clearUser () {
  navModel.userNumber('')
  navModel.userName('')
  navModel.sex('')
  navModel.education('')
  navModel.departmentNumber('')
  navModel.departmentName('')
  navModel.power('')
}

navModel.logout = function () {
  $.ajax({
    url: 'logout',
    cache: false,
    success: function (response) {
      clearUser()
      location.href = 'loginPage'
    }
  })
}

function getUser () {
  $.ajax({
    url: 'getUser',
    success: function (response) {
      if (!response.user) {
        setTimeout(getUser, 100)
      } else {
        navModel.userNumber(response.user.employNumber)
        navModel.userName(response.user.employName)
        navModel.sex(response.user.sex)
        navModel.education(response.user.education)
        navModel.departmentNumber(response.user.departmentNumber)
        navModel.departmentName(response.user.departmentName)
        navModel.power(response.account.power)
      }
    }
  })
}

$(document).ready(function () {
  clearUser()
  getUser()
})

ko.attach('navModel', navModel)
