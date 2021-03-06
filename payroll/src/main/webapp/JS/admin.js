var adminModel = {
  employList: ko.observableArray([]),
  selectDepartEmployees: ko.observableArray([]),
  ownDepartShow: ko.observable(true),
  departments: ko.observableArray([]),
  selectDepartment: ko.observable(''),
  departmentsNumber: ko.observableArray([]),
  raises: [],
}

adminModel.sumarySalary = ko.computed(calculateSumSalary, this);
adminModel.avgSalary = ko.computed(calculateAvgSalary, this);
function calculateSumSalary() {
  var sumarySalary = 0.00;
  if (adminModel.selectDepartEmployees && !isEmptyArray(adminModel.selectDepartEmployees())) {
    adminModel.selectDepartEmployees().forEach(function (employee) {
      sumarySalary = sumarySalary + parseFloat(employee.actMoney());
    }, this)
  }
  return sumarySalary;
}

function calculateAvgSalary() {
  return (parseFloat(adminModel.sumarySalary()) / adminModel.selectDepartEmployees().length).toFixed(2);
}

adminModel.addEmployee = function () {
  clearModal()
  $.ajax({
    url: 'addEmployee',
    success: function (response) {
      oneButtonModal('添加员工', response, '确定', function () {
        var addEmployee = $('#employeeAppend').serialize();
        clearModal()
        initProcessBar('正在添加')
        $.ajax({
          type: 'POST',
          url: 'employeeAppend',
          data: addEmployee,
          success: function (response) {
            closeProcessbar()
            initModal('添加成功', '员工号为<span class="text-primary"> '
              + response.userId
              + ' </span>初始密码为<span class="text-danger"> '
              + response.password + ' </span>请告知员工尽快修改密码!')
            getEmployees()
          },
          error: function (response) {
            closeProcessbar()
            initModal('添加失败', response.responseText)
          }
        })
      })
      for (var i = 0; i < adminModel.departments().length; i++) {
        $('#department').append('<option value='
          + adminModel.departmentsNumber()[i]
          + '>'
          + adminModel.departments()[i] + '</option>')
      }
    }
  })
}

adminModel.departmentToggle = function (param) {
  this.ownDepartShow(!this.ownDepartShow())
}

adminModel.selectDepartment.extend({
  notify: 'always'
})

adminModel.departmentDetail = function (param) { }

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

var addDepartmentModel = {
  departmentName: ko.observable(''),
  departmentManager: ko.observable(''),
  departmentVisible: ko.observable(false),
  departmentBaseSalary: ko.observable(''),
  seniorityBaseSalary: ko.observable(''),
  levelBaseSalary: ko.observable('')
}

addDepartmentModel.createDepartment = function () {
  var department = ko.toJSON(addDepartmentModel);
  initProcessBar('正在添加')
  $.ajax({
    type: 'POST',
    contentType: 'application/json; charset=utf-8',
    url: 'createDepartment',
    data: department,
    success: function (response) {
      closeProcessbar()
      initModal('创建成功', '部门<span class="text-primary">' + department.departmentName + '</span>成功创建!')
    },
    error: function (response) {
      closeProcessbar()
      initModal('创建失败', response.responseText)
    }
  })
}

function getDepartments() {
  adminModel.departments.removeAll()
  $.ajax({
    url: 'getDepartments',
    success: function (response) {
      response.forEach(function (department) {
        adminModel.departments.push(department.departmentName)
        adminModel.departmentsNumber.push(department.departmentNumber)
      })
    }
  })
}

function getEmployees() {
  $.ajax({
    url: 'getEmployees',
    success: function (response) {
      adminModel.employList.removeAll()
      adminModel.selectDepartEmployees.removeAll()
      response.forEach(function (element) {
        adminModel.employList.push({
          employNumber: ko.observable(element.employee.employNumber),
          employName: ko.observable(element.employee.employName),
          education: element.employee.education,
          sex: element.employee.sex,
          departmentName: ko.observable(element.employee.departmentName),
          departmentNumber: ko.observable(element.employee.departmentNumber),
          userDetail: function (param) { },
          saveEmployee: function () {
            var self = this
            initProcessBar('正在保存')
            var employeeInfo = ko.toJSON(this)
            $.ajax({
              type: 'POST',
              contentType: 'application/json; charset=utf-8',
              url: 'saveEmployee',
              data: employeeInfo,
              success: function (response) {
                self.actMoney(calculateSalary(self.expMoney(), self.overtimeSalary(), self.fine()))
                closeProcessbar()
                initModal('保存', '保存成功')
              },
              error: function (response) {
                closeProcessbar()
                initModal('保存', response.responseText)
              }
            })
          },
          deleteEmployee: function () {
            var self = this
            initProcessBar('正在删除')
            $.ajax({
              type: 'POST',
              url: 'deleteEmployee?employeeNumber=' + this.employNumber(),
              success: function (response) {
                closeProcessbar()
                initModal('删除成功', '员工<span class="text-danger">' + self.employName() + '</span>成功被炒鱿鱼!')
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
          actMoney: ko.observable(element.salary.actuallySalary),
          officeDay: ko.observable(element.salary.officeDay),
        })
      })
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
  getDepartments()
  getEmployees()
  $('#showDepartment').bind('click', function () {
    addDepartmentModel.departmentVisible(true);
  })
})
ko.attach('adminModel', adminModel)
ko.attach('addDepartmentModel', addDepartmentModel)
