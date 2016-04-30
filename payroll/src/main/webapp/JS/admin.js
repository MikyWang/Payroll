var adminModel = {
  employList: ko.observableArray([]),
  selectDepartEmployees: ko.observableArray([]),
  ownDepartShow: ko.observable(true),
  departments: ko.observableArray([]),
  selectDepartment: ko.observable(''),

}

adminModel.addEmployee = function () {
  $.ajax({
    url: 'addEmployee',
    success: function (response) {
      oneButtonModal('添加员工', response, '确定')
    }
  })
}

adminModel.departmentToggle = function (param) {
  this.ownDepartShow(!this.ownDepartShow())
}

adminModel.selectDepartment.extend({
  notify: 'always'
})

adminModel.departmentDetail = function (param) {}

adminModel.selectDepartment.subscribe(function (newValue) {
  if (!isEmptyArray(adminModel.employList())) {
    adminModel.selectDepartEmployees.removeAll()
  }
  var selectEmployees = adminModel.employList()
  if (!isNullOrEmpty(newValue)) {
    selectEmployees = adminModel.employList().filter(function (value, index) {
      return value.departmentName() == newValue
    }, newValue)
  }
  selectEmployees.forEach(function (employee) {
    adminModel.selectDepartEmployees.push(employee)
  })
})

adminModel.isSelectDepartment = ko.computed(function (param) {
  return !isNullOrEmpty(adminModel.selectDepartment())
}, this)

function getEmployees () {
  $.ajax({
    url: 'getEmployees',
    success: function (response) {
      adminModel.employList.removeAll()
      adminModel.departments.removeAll()
      adminModel.selectDepartEmployees.removeAll()
      response.forEach(function (element) {
        if (adminModel.departments().length < 1 || isEmptyArray(adminModel.departments().filter(function (value, index) {
            return value == this
          }, element.employee.departmentName))) {
          adminModel.departments.push(element.employee.departmentName)
        }

        adminModel.employList.push({
          employNumber: ko.observable(element.employee.employNumber),
          employName: ko.observable(element.employee.employName),
          sex: element.employee.sex,
          departmentName: ko.observable(element.employee.departmentName),
          actMoney: ko.observable(element.salary.actuallySalary),
          userDetail: function (param) {},
          deleteEmployee: function () {
            var self = this
            initProcessBar('正在删除')
            $.ajax({
              type: 'POST',
              url: 'deleteEmployee?employeeNumber=' + this.employNumber(),
              success: function (response) {
                closeProcessbar()
                adminModel.selectDepartEmployees.remove(self)
              },
              error: function (response) {
                initModal('删除失败', response.responseText)
                resetButton()
              }
            })
          },
          togglePower: function () {
            if (this.power() == window.LoginType.admin) {
              oneButtonModal('权限管理', '是否要将<span class="text-primary">'
                + this.employName() + '</span>降级为<span class="text-danger">'
                + window.LoginType.employee + '</span>', '确定', function (thisArgs) {
                  clearModal()
                  initProcessBar('正在进行中...')
                  $.ajax({
                    type: 'POST',
                    url: 'powerDown?employeeNumber=' + thisArgs.employNumber(),
                    success: function (response) {
                      closeProcessbar()
                      location.reload()
                    },
                    error: function (response) {
                      initModal('降级失败', response.responseText)
                      resetButton()
                    }
                  })
                }, this)
            }
            if (this.power() == window.LoginType.employee) {
              oneButtonModal('权限管理', '是否要将<span class="text-primary">'
                + this.employName() + '</span>升级为<span class="text-danger">'
                + window.LoginType.admin + '</span>', '确定', function (thisArgs) {
                  clearModal()
                  initProcessBar('正在进行中...')
                  $.ajax({
                    type: 'POST',
                    url: 'powerUp?employeeNumber=' + thisArgs.employNumber(),
                    success: function (response) {
                      closeProcessbar()
                      location.reload()
                    },
                    error: function (response) {
                      initModal('升级失败', response.responseText)
                      resetButton()
                    }
                  })
                }, this)
            }
          },
          isAdmin: ko.computed(function (param) {
            if (isNullOrEmpty(this.power)) {
              return element.account.power == window.LoginType.admin
            } else {
              return this.power() == window.LoginType.admin
            }
          }, this),
          power: ko.observable(element.account.power),
          powerStyle: ko.computed(function (param) {
            if (isNullOrEmpty(this.power)) {
              return element.account.power == window.LoginType.admin ? 'label-primary' : 'label-default'
            } else {
              return this.power() == window.LoginType.admin ? 'label-primary' : 'label-default'
            }
          }, this),
          expMoney: ko.observable(element.salary.expectSalary),
          fine: ko.observable(element.salary.fine),
          overtime: ko.observable(element.salary.overtime),
          overtimeSalary: ko.observable(element.salary.overtimeSalary),
          officeDay: ko.observable(element.salary.officeDay),
        })
      }, this)
      adminModel.employList().forEach(function (employee) {
        adminModel.selectDepartEmployees.push(employee)
      })
    },
    error: function (param) {
      initModal('获取员工信息失败', param.responseText)
    }
  })
}

$(document).ready(function () {
  getEmployees()
})
ko.attach('adminModel', adminModel)
