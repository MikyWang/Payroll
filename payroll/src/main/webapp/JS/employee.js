var employeeModel = {
    employeeNumber: ko.observable(''),
    employeeName: ko.observable(''),
    sex: ko.observable(''),
    education: ko.observable(''),
    expectSalary: ko.observable(''),
    actuallySalary: ko.observable(''),
    fine: ko.observable(''),
    overtimeSalary: ko.observable(''),
    officeDay: ko.observable(''),
    overtime: ko.observable(''),
    departmentName: ko.observable(''),
    departmentManagerName: ko.observable(''),
    departmentSize: ko.observable(''),
}
employeeModel.saveEmployee = function () {
    initProcessBar()
    var employee = {
        employNumber: employeeModel.employeeNumber(),
        employName: employeeModel.employeeName(),
        sex: employeeModel.sex(),
        education: employeeModel.education()
    }
    $.ajax({
        type: 'POST',
        contentType: 'application/json; charset=utf-8',
        url: 'saveEmployeeOne',
        data: JSON.stringify(employee),
        success: function (response) {
            closeProcessbar()
            initModal('更新成功', '用户信息更新成功!')
        },
        error: function (response) {
            closeProcessbar()
            initModal('保存用户失败', response.responseText)
        }
    })
}



employeeModel.saveSalary = function () {
    var salary = {
        employNumber: employeeModel.employeeNumber(),
        expectSalary: employeeModel.expectSalary(),
        actuallySalary: employeeModel.actuallySalary(),
        fine: employeeModel.fine(),
        overtime: employeeModel.overtime(),
        overtimeSalary: employeeModel.overtimeSalary(),
        officeDay: employeeModel.officeDay(),
    }
    $.ajax({
        type: 'POST',
        contentType: 'application/json; charset=utf-8',
        url: 'saveSalary',
        data: JSON.stringify(salary),
        success: function (response) {
            closeProcessbar()
            initModal('更新成功', '工资信息更新成功!')
        },
        error: function (response) {
            closeProcessbar()
            initModal('更新工资信息失败', response.responseText)
        }
    })
}

employeeModel.requestSalary = function () {
    $.ajax({
        url: 'requestSalary',
        success: function (response) {
            oneButtonModal('申请加薪', response, '确定', function () {
                $.ajax({
                    type: 'POST',
                    url: 'requireRaise',
                    data: $('#requireRaise').serialize(),
                    success: function (response) {
                        initModal('发送成功', '发送成功')
                    },
                    error: function (response) {
                        initModal('发送失败', response.responseText)
                    }
                })
            })
        }
    })
}



function getInfomation() {
    initProcessBar()
    $.ajax({
        type: 'POST',
        url: 'getEmployee',
        success: function (response) {
            employeeModel.employeeNumber(response.employNumber)
            employeeModel.employeeName(response.employName)
            employeeModel.sex(response.sex)
            employeeModel.departmentName(response.departmentName)
            employeeModel.education(response.education)
            $.ajax({
                type: 'POST',
                url: 'getDepartment?departmentName=' + response.departmentName,
                success: function (response) {
                    employeeModel.departmentSize(response.department.departmentSize)
                    employeeModel.departmentManagerName(response.departmentManagerName)
                    $.ajax({
                        type: 'POST',
                        url: 'getSalary?employNumber=' + employeeModel.employeeNumber(),
                        success: function (response) {
                            employeeModel.expectSalary(response.expectSalary)
                            employeeModel.actuallySalary(response.actuallySalary)
                            employeeModel.fine(response.fine)
                            employeeModel.overtime(response.overtime)
                            employeeModel.overtimeSalary(response.overtimeSalary)
                            employeeModel.officeDay(response.officeDay)
                            closeProcessbar()
                            $('#close').click()
                        },
                        error: function (response) {
                            closeProcessbar()
                            initModal('获取工资信息出错', response.responseText)
                        }
                    })
                },
                error: function (response) {
                    closeProcessbar()
                    initModal('获取部门信息错误', response.responseText)
                }
            })
        },
        error: function (response) {
            closeProcessbar()
            initModal('初始化错误', response.responseText)
        }
    })
}

$(document).ready(function () {
    getInfomation()
})

ko.attach('employeeModel', employeeModel)
