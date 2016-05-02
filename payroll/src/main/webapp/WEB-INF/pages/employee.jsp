<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html lang="zh-CN">

    <head>
        <%@ include file="header.jsp" %>
            <script src="JS/employee.js"></script>
    </head>

    <body>
        <%@ include file="navbar.jsp" %>

            <div class="container" data-model="employeeModel">

                <div class="row">
                    <div class="col-sm-offset-1 col-sm-10">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">我的信息</h3>
                            </div>
                            <div class="panel-body">
                                <div class="col-sm-6">
                                    <div class="panel panel-warning">
                                        <div class="panel-heading">
                                            <span class="panel-title">基本信息</span>
                                            <div class="pull-right">
                                                <a href="javascript:void(0);" class="text-primary" data-toggle="tooltip" title="保存修改" data-bind="click: saveEmployee"> <span class="glyphicon glyphicon-floppy-saved"></span>&nbsp;</a>
                                            </div>
                                        </div>
                                        <div class="panel-body">

                                            <form class="form-horizontal" role="form">
                                                <div class="form-group">
                                                    <label for="input-id" class="col-sm-3 control-label">员工号:</label>
                                                    <div class="col-sm-9">
                                                        <input type="text" class="form-control" data-bind="value: employeeNumber,valueUpdate:'afterkeydown'" disabled>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="input-Name" class="col-sm-3 control-label">姓名:</label>
                                                    <div class="col-sm-9">
                                                        <input type="text" class="form-control" data-bind="value: employeeName,valueUpdate:'afterkeydown'">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="input-sex" class="col-sm-3 control-label">性别:</label>
                                                    <div class="col-sm-9">
                                                        <select class="form-control" data-bind="value: sex,valueUpdate:'afterkeydown'">
                                                            <option value="男">男</option>
                                                            <option value="女">女</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="input-education" class="col-sm-3 control-label">学历:</label>
                                                    <div class="col-sm-9">
                                                        <input type="text" class="form-control" data-bind="value: education,valueUpdate:'afterkeydown'" disabled>
                                                    </div>
                                                </div>
                                            </form>

                                        </div>
                                    </div>
                                </div>
                                <div class=" col-sm-6">
                                    <div class="panel panel-danger">
                                        <div class="panel-heading">
                                            <span class="panel-title">工资信息</span>
                                            <div class="pull-right">
                                                <a href="javascript:void(0);" class="text-primary" data-toggle="tooltip" title="保存工资信息" data-bind="click: saveSalary"> <span class="glyphicon glyphicon-floppy-saved"></span>&nbsp;</a>
                                            </div>
                                        </div>
                                        <div class="panel-body">
                                            <form class="form-horizontal" role="form">
                                                <div class="form-group">
                                                    <label for="input-id" class="col-sm-4 control-label">基本工资:</label>
                                                    <div class="col-sm-8">
                                                        <div class="input-group">
                                                            <div class="input-group-addon">￥</div>
                                                            <input type="text" class="form-control" data-bind="value: expectSalary,valueUpdate:'afterkeydown'" disabled>
                                                            <a href="javascript:void(0);" class="text-primary input-group-addon" data-toggle="tooltip" title="申请加薪" data-bind="click: requestSalary">
                                                                <span class="glyphicon glyphicon-arrow-up text-success"></span>
                                                            </a>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="input-id" class="col-sm-4 control-label">罚款:</label>
                                                    <div class="col-sm-8">
                                                        <div class="input-group">
                                                            <div class="input-group-addon">￥</div>
                                                            <input type="text" class="form-control" data-bind="value: fine,valueUpdate:'afterkeydown'" disabled>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="input-id" class="col-sm-4 control-label">加班工资:</label>
                                                    <div class="col-sm-8">
                                                        <div class="input-group">
                                                            <div class="input-group-addon">￥</div>
                                                            <input type="text" class="form-control" data-bind="value: overtimeSalary,valueUpdate:'afterkeydown'" disabled>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="input-id" class="col-sm-4 control-label">实际工资:</label>
                                                    <div class="col-sm-8">
                                                        <div class="input-group">
                                                            <div class="input-group-addon">￥</div>
                                                            <input type="text" class="form-control" data-bind="value: actuallySalary,valueUpdate:'afterkeydown'" disabled>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="input-id" class="col-sm-4 control-label">上班天数:</label>
                                                    <div class="col-sm-8">
                                                        <div class="input-group">
                                                            <input type="number" class="form-control" data-bind="value: officeDay,valueUpdate:'afterkeydown'">
                                                            <div class="input-group-addon">天</div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="input-id" class="col-sm-4 control-label">加班时长:</label>
                                                    <div class="col-sm-8">
                                                        <div class="input-group">
                                                            <input type="number" class="form-control" data-bind="value: overtime,valueUpdate:'afterkeydown'">
                                                            <div class="input-group-addon">小时</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6" style="margin-top:-10%;">

                                    <div class="panel panel-info">
                                        <div class="panel-heading">
                                            <h3 class="panel-title">部门信息</h3>
                                        </div>
                                        <div class="panel-body">

                                            <form class="form-horizontal" role="form">
                                                <fieldset disabled>
                                                    <div class="form-group">
                                                        <label for="inputNumber" class="col-sm-4 control-label">部门名称:</label>
                                                        <div class="col-sm-8">
                                                            <input type="text" class="form-control " required="required" data-bind="value: departmentName,valueUpdate:'afterkeydown'">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="inputManager" class="col-sm-4 control-label">部门管理人:</label>
                                                        <div class="col-sm-8">
                                                            <input type="text" class="form-control" data-bind="value: departmentManagerName ,valueUpdate:'afterkeydown'">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                    <label for="input-id" class="col-sm-4 control-label">部门人数:</label>
                                                    <div class="col-sm-8">
                                                        <div class="input-group">
                                                            <input type="number" class="form-control" data-bind="value: departmentSize,valueUpdate:'afterkeydown'">
                                                            <div class="input-group-addon">人</div>
                                                        </div>
                                                    </div>
                                                </div>
                                                </fieldset>
                                            </form>

                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>

                    </div>
                </div>

            </div>


    </body>

    </html>