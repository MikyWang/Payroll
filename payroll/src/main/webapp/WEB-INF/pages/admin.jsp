<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html lang="zh-CN">

    <head>
        <%@ include file="header.jsp" %>
            <script src="JS/admin.js"></script>
    </head>

    <body>
        <%@ include file="navbar.jsp" %>
            <div class="jumbotron" style="margin-top: -20px;">
                <div class="container">
                    <h1>管理员界面</h1>
                    <p>管理员能够对员工进行编辑,查看,删除,添加操作.</p>
                    <p>
                        <a class="btn btn-primary btn-lg" href="#employeeManage">管理员工</a>
                        <a id="showDepartment" class="btn btn-primary btn-lg">添加部门</a>
                    </p>
                </div>
            </div>
            <div id="employeeManage" class="container" data-model="adminModel">
                <div class="row">
                    <div class=" col-sm-offset-1 col-sm-10 col-xs-12">
                        <div class="panel panel-default ">
                            <div class="panel-heading col-xs-12">
                                <button style="margin-top : -5px" class="btn btn-default" data-toggle="tooltip" title="查看部门信息" data-bind="click: departmentDetail , disabled : !isSelectDepartment()">
                                    <span class="glyphicon glyphicon-user text-primary"></span>
                                </button>
                                <select class="form-control form-inline" data-bind="options: departments , value : selectDepartment, optionsCaption : '选择部门'"></select>
                                <div class=" pull-right">
                                    <a href="javascript:void(0);" data-toggle="tooltip" title="添加员工" data-bind="click: addEmployee"> <span class="glyphicon glyphicon-plus"></span>&nbsp;</a>
                                    <a href="javascript:void(0);" data-toggle="tooltip" title="缩小" data-bind="click: departmentToggle , visible : ownDepartShow"> <span class="glyphicon glyphicon-resize-small"></span></a>
                                    <a href="javascript:void(0);" data-toggle="tooltip" title="放大" data-bind="click: departmentToggle , visible : !ownDepartShow()"> <span class="glyphicon glyphicon-resize-full"></span></a>
                                </div>

                            </div>
                            <div class="panel-body" data-bind="showVisible: ownDepartShow">
                                <table class="table table-striped table-hover">
                                    <thead>
                                        <tr class=" text-info">
                                            <th>
                                                员工编号
                                            </th>
                                            <th>
                                                员工姓名
                                            </th>
                                            <th>
                                                学历
                                            </th>
                                            <th>
                                                性别
                                            </th>
                                            <th>
                                                所属部门
                                            </th>
                                            <th>
                                                本月工资
                                            </th>
                                            <th>
                                                操作
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody data-bind="foreach: selectDepartEmployees">
                                        <tr>
                                            <td>
                                                <a href="javascript:void(0);" data-bind="html: employNumber , click : userDetail"></a>
                                            </td>
                                            <td>
                                                <input type="text" id="inputName" class="form-control form-inline" required="required" data-bind="value: employName, valueUpdate : 'afterkeydown'"> &nbsp;
                                                <span class="label " data-bind="html: power, css : powerStyle"></span>
                                            </td>
                                            <td>

                                                <input type="text" class="form-control form-inline" data-bind="value: education, valueUpdate : 'afterkeydown'">

                                            </td>
                                            <td>
                                                <span data-bind="html: sex"></span>
                                            </td>
                                            <td class="dropdown">
                                                <select id="inputDepartment" class="form-control" data-bind="options: $root.departments ,value : departmentName ,disabled : isAdmin"></select>
                                            </td>
                                            <td class="dropdown ">
                                                <a href="javascript:void(0);" class="dropdown-toggle " id="moneyDrop" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                                    <span class="text-warning">￥</span>
                                                    <span class="text-danger " data-bind="text: actMoney"></span>
                                                    <span class="caret"></span>
                                                </a>
                                                <table class="dropdown-menu  table table-hover table-striped" style="min-width:200px" aria-labelledby="moneyDrop">
                                                    <tbody>
                                                        <tr>
                                                            <td>
                                                                基本工资:
                                                            </td>
                                                            <td>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon">￥</div>
                                                                    <input type="text text-danger" class="form-control" id="exampleInputAmount" placeholder="Amount" data-bind="value: expMoney, valueUpdate:'afterkeydown'" disabled>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                迟到罚款:
                                                            </td>
                                                            <td>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon">￥</div>
                                                                    <input type="text text-danger" class="form-control" placeholder="罚款" data-bind="value: fine, valueUpdate:'afterkeydown'" disabled>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                加班时长:
                                                            </td>
                                                            <td>
                                                                <div class="input-group">
                                                                    <input type="text" class="form-control" placeholder="小时" data-bind="value: overtime,valueUpdate:'afterkeydown'">
                                                                    <div class="input-group-addon">小时</div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                加班工资:
                                                            </td>
                                                            <td>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon">￥</div>
                                                                    <input type="text text-danger" class="form-control" placeholder="加班工资" data-bind="value: overtimeSalary, valueUpdate:'afterkeydown'" disabled>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                上班天数:
                                                            </td>
                                                            <td>
                                                                <div class="input-group">
                                                                    <input type="text" class="form-control" placeholder="天" data-bind="value: officeDay,valueUpdate:'afterkeydown'">
                                                                    <div class="input-group-addon">天</div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </td>
                                            <td>
                                                <a href="javascript:void(0);" data-toggle="tooltip" title="保存修改" data-bind="click: saveEmployee"> <span class="glyphicon glyphicon-floppy-saved"></span>&nbsp;</a>
                                                <a href="javascript:void(0);" data-toggle="tooltip" title="删除" data-bind="visible: !isAdmin(), click : deleteEmployee"> <span class="glyphicon glyphicon-remove"></span>&nbsp;</a>
                                                <a href="javascript:void(0);" data-bind="visible: !isAdmin() ,click :togglePower">
                                                    <span class="glyphicon glyphicon-arrow-up text-success"></span>
                                                </a>
                                                <a href="javascript:void(0);" data-bind="visible: isAdmin,click : togglePower">
                                                    <span class="glyphicon glyphicon-arrow-down text-danger"></span>
                                                </a>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="department" class="container" data-model="addDepartmentModel">
                <div class="row" data-bind="showVisible : departmentVisible">
                    <div class="col-xs-12 col-sm-offset-1  col-sm-4">
                        <div class="panel panel-success">
                            <div class="panel-heading">
                                <h3 class="panel-title">添加部门</h3>
                            </div>
                            <div class="panel-body">
                                <form class="form-horizontal" role="form">
                                    <div class="form-group">
                                        <label for="inputNumber" class="col-sm-4 control-label">部门名称:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control " required="required" data-bind="value: departmentName,valueUpdate:'afterkeydown'">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputManager" class="col-sm-4 control-label">部门管理人ID:</label>
                                        <div class="col-sm-8">
                                            <input type="text" placeholder="可留空" class="form-control" data-bind="value: departmentManager ,valueUpdate:'afterkeydown'">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputManager" class="col-sm-4 control-label">部门基本工资:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" data-bind="value: departmentBaseSalary ,valueUpdate:'afterkeydown'">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputManager" class="col-sm-4 control-label">工龄基本工资:</label>
                                        <div class="col-sm-8">
                                            <div class="input-group-addon">￥</div>
                                            <input type="text text-danger" class="form-control" data-bind="value: seniorityBaseSalary, valueUpdate:'afterkeydown'" >
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputManager" class="col-sm-4 control-label">级别基本工资:</label>
                                        <div class="col-sm-8">
                                           <div class="input-group-addon">￥</div>
                                            <input type="text text-danger" class="form-control" data-bind="value: levelBaseSalary, valueUpdate:'afterkeydown'" >
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-4 col-sm-4">
                                            <button type="button" class="btn btn-primary" data-bind="click: createDepartment">创建部门</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
    </body>

    </html>