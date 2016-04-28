var adminModel = {
  employList: ko.observableArray([]),
}

$(document).ready(function () {
  $.ajax({
    url: 'getEmployees',
    success: function (response) {
      response.forEach(function (element) {
        adminModel.employList.push({
          employNumber: ko.observable(element.employee.employNumber),
          employName: ko.observable(element.employee.employName),
          sex: element.employee.sex,
          departmentName: ko.observable(element.employee.departmentName),
          actMoney: ko.observable(element.salary.actuallySalary),
          userDetail: function (param) {},
          departDetail: function (param) {},
          expMoney: ko.observable(element.salary.expectSalary),
          fine: ko.observable(element.salary.fine),
          overtime: ko.observable(element.salary.overtime),
          overtimeSalary: ko.observable(element.salary.overtimeSalary),
          officeDay: ko.observable(element.salary.officeDay),
        })
      }, this)
    },
    error: function (param) {
      initModal('获取员工信息失败', param.responseText)
    }
  })
})
ko.attach('adminModel', adminModel)
