var HRModel = {
    raises: [],
    employeeSalarys: ko.observableArray([])
}

function EmployeeSalary(employNumber, employName, departmentBaseSalary, seniority, seniorityBaseSalary, level, levelBaseSalary, overtimeSalary, fine) {
    this.employNumber = ko.observable(employNumber);
    this.employName = ko.observable(employName);
    this.departmentBaseSalary = ko.observable(departmentBaseSalary);
    this.seniorityBaseSalary = seniorityBaseSalary;
    this.seniority = ko.observable(seniority);
    this.senioritySalary = ko.computed(function () {
        return parseFloat(this.seniority()) * parseFloat(this.seniorityBaseSalary);
    }, this);
    this.level = ko.observable(level);
    this.levelBaseSalary = levelBaseSalary;
    this.levelSalary = ko.computed(function () {
        return parseFloat(this.level()) * parseFloat(this.levelBaseSalary);
    }, this);
    this.overtimeSalary = ko.observable(overtimeSalary);
    this.fine = ko.observable(fine);
    this.expectSalary = ko.computed(function () {
        return parseFloat(this.departmentBaseSalary())
            + parseFloat(this.senioritySalary())
            + parseFloat(this.levelSalary());
    }, this);
    this.actuallySalary = ko.computed(function () {
        return parseFloat(this.expectSalary()) + parseFloat(this.overtimeSalary()) - parseFloat(this.fine());
    }, this);

    this.saveSalary = function () {
        var employeeSalaryJson = ko.toJSON(this);
        initProcessBar('保存薪资')
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "updateEmployeeSalary",
            data: employeeSalaryJson,
            success: function (response) {
                closeProcessbar()
                initModal('保存薪资', '保存成功');
            },
            error: function (response) {
                closeProcessbar()
                initModal('保存薪资', response.responseText)
            }
        });
    };
}


function getRaise() {
    $('#raisesHeader').html('请求加薪信息 &nbsp;')
    initProcessBar('获取加薪信息')
    $.ajax({
        url: 'getRaises',
        success: function (response) {
            HRModel.raises = response
            $('#raisesHeader').append('<span class="badge">' + HRModel.raises.length + '</span>')
            closeProcessbar()
            $('#close').click()
        },
        error: function (response) {
            closeProcessbar()
            $('#close').click()
        }
    })
}

function acceptRaise() {
    var index = $(this).index();
    HRModel.raises[index].raise.status = RaiseStatus.Accept;
    initProcessBar('正在加薪')
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "updateRaise",
        data: JSON.stringify(HRModel.raises[index].raise),
        success: function (response) {
            initModal('加薪', '加薪成功');
            location.reload()
        },
        error: function (response) {
            initModal('加薪', response.responseText);
            getRaise()
        }
    });
}

function cancelRaise() {
    var index = $(this).index();
    HRModel.raises[index].raise.status = RaiseStatus.Cancel;
    initProcessBar('正在驳回')
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "updateRaise",
        data: JSON.stringify(HRModel.raises[index].raise),
        success: function (response) {
            initModal('加薪', '驳回成功');
            location.reload()
        },
        error: function (response) {
            initModal('加薪', response.responseText);
            getRaise()
        }
    });
}


function showRaises() {
    $('#raises').html('')
    $.ajax({
        url: 'showRaises',
        success: function (response) {
            initModal('请求信息', response)
            HRModel.raises.forEach(function (raise) {
                var html = ' <div class="panel panel-default"><div class="panel-heading"><h3 class="panel-title">' + raise.raise.formId + "&nbsp;&nbsp;&nbsp;工号<span class='text-info'>" + raise.employee.employNumber + "</span>的<span class='text-primary'>" + raise.employee.employName + "</span>申请加薪:" + '</h3> </div><div class="panel-body"> <form class="form-horizontal" role="form"> <fieldset disabled><div class="form-group"><label for="input-id" class="col-sm-2 control-label">申请加薪:</label><div class="col-sm-10"><div class="input-group"><div class="input-group-addon">￥</div><input type="text" value="' + raise.raise.requireMoney + '"  class="form-control"> </div></div></div><div class="form-group"><label for="input-id" class="col-sm-2 control-label">加薪理由:</label><div class="col-sm-10"><input type="text" value="' + raise.raise.content + '"  class="form-control"></div></div>   </fieldset> <div class="form-group"><div class="col-sm-offset-1 col-sm-5"><button type="button" class="btn btn-primary btn-block acceptRaise">同意</button></div><div class="col-sm-offset-1 col-sm-5"><button type="button" class="btn btn-danger btn-block cancelRaise">驳回</button></div></div></form> </div></div>'
                $('#raises').append(html)
            })
            $('.acceptRaise').bind('click', acceptRaise)
            $('.cancelRaise').bind('click', cancelRaise)
        },
        error: function (response) {
            initModal('读取失败', response.responseText)
        }
    })
}

function getEmployeeSalary() {
    $.ajax({
        url: "getEmployeeSalarys",
        success: function (response) {
            response.forEach(function (employeeSalaryT) {
                var employeeSalary = new EmployeeSalary(employeeSalaryT.employee.employNumber, employeeSalaryT.employee.employName
                    , employeeSalaryT.departmentBaseSalary.departmentBaseSalary, employeeSalaryT.employeeWork.employeeSeniority
                    , employeeSalaryT.departmentBaseSalary.seniorityBaseSalary, employeeSalaryT.employeeWork.employeeLevel,
                    employeeSalaryT.departmentBaseSalary.levelBaseSalary, employeeSalaryT.salary.overtimeSalary, employeeSalaryT.salary.fine)
                HRModel.employeeSalarys.push(employeeSalary);
            })
        },
        error: function (response) {
            initModal('获取失败', response.responseText);
        }
    });
}


$(document).ready(function () {
    getRaise()
    getEmployeeSalary()
    $('#raisesHeader').bind('click', showRaises)
});

ko.attach("HRModel", HRModel);