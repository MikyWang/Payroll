<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html lang="zh-CN">

    <head>
        <%@ include file="header.jsp" %>
            <script src="JS/HR.js"></script>
    </head>

    <body>
        <%@ include file="navbar.jsp" %>
            <div class="jumbotron" style="margin-top: -20px;">
                <div class="container">
                    <h1>人事部界面</h1>
                    <p>管理工资</p>
                    <p>
                        <a id="raisesHeader" class="btn btn-primary btn-lg" href="javascript:void(0);">
                              请求加薪信息 &nbsp;
                        </a>
                    </p>
                </div>
            </div>

            <div class="container" data-model="HRModel">
                <div class="row">
                    <div class=" col-sm-112 col-xs-12">
                        <div class="panel panel-default ">
                            <div class="panel-heading col-xs-12">
                                <h3 class="panel-title">工资管理</h3>
                            </div>
                            <div class="panel-body">
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
                                                基本工资
                                            </th>
                                            <th>
                                                奖金
                                            </th>
                                            <th>
                                                罚款
                                            </th>
                                            <th>
                                                五险一金
                                            </th>
                                            <th>
                                                实际工资
                                            </th>
                                            <th>
                                                操作
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody data-bind="foreach: employeeSalarys">
                                        <tr>
                                            <td>
                                                <a href="javascript:void(0);" data-bind="html: employNumber"></a>
                                            </td>
                                            <td>
                                                <input type="text" id="inputName" class="form-control form-inline" required="required" data-bind="value: employName, valueUpdate : 'afterkeydown'" disabled>
                                            </td>
                                            <td class="dropdown">
                                                <a href="javascript:void(0);" class="dropdown-toggle " id="moneyDrop" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                                    <span class="text-warning">￥</span>
                                                    <span class="text-danger " data-bind="text: expectSalary"></span>
                                                    <span class="caret"></span>
                                                </a>
                                                <table class="dropdown-menu  table table-hover table-striped" style="min-width:200px" aria-labelledby="moneyDrop">
                                                    <tbody>
                                                        <tr>
                                                            <td>
                                                                部门基础工资:
                                                            </td>
                                                            <td>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon">￥</div>
                                                                    <input type="text text-danger" class="form-control" id="exampleInputAmount" placeholder="Amount" data-bind="value: departmentBaseSalary, valueUpdate:'afterkeydown'" disabled>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                工龄:
                                                            </td>
                                                            <td>
                                                                <div class="input-group">
                                                                    <input type="text text-danger" class="form-control" placeholder="工龄" data-bind="value: seniority, valueUpdate:'afterkeydown'">
                                                                    <div class="input-group-addon">年</div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                工龄工资加成:
                                                            </td>
                                                            <td>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon">￥</div>
                                                                    <input type="text text-danger" class="form-control" placeholder="工龄工资提成" data-bind="value: senioritySalary, valueUpdate:'afterkeydown'" disabled>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                级别:
                                                            </td>
                                                            <td>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon">level</div>
                                                                    <input type="text" class="form-control" placeholder="level" data-bind="value: level,valueUpdate:'afterkeydown'">
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                级别工资加成:
                                                            </td>
                                                            <td>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon">￥</div>
                                                                    <input type="text text-danger" class="form-control" placeholder="级别工资加成" data-bind="value: levelSalary, valueUpdate:'afterkeydown'" disabled>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </td>
                                            <td>
                                                <div class="input-group">
                                                    <div class="input-group-addon">￥</div>
                                                    <input type="text text-danger" class="form-control" id="exampleInputAmount" placeholder="Amount" data-bind="value: overtimeSalary, valueUpdate:'afterkeydown'">
                                                </div>
                                            </td>
                                            <td>
                                                <div class="input-group">
                                                    <div class="input-group-addon">￥</div>
                                                    <input type="text text-danger" class="form-control" id="exampleInputAmount" placeholder="fine" data-bind="value: fine, valueUpdate:'afterkeydown'">
                                                </div>
                                            </td>
                                            <td class="dropdown">
                                                <a href="javascript:void(0);" class="dropdown-toggle " id="insuranceDrop" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                                    <span class="text-warning">￥</span>
                                                    <span class="text-danger " data-bind="text: insurance"></span>
                                                    <span class="caret"></span>
                                                </a>
                                                <table class="dropdown-menu  table table-hover table-striped" style="min-width:200px" aria-labelledby="insuranceDrop">
                                                    <tbody>
                                                        <tr>
                                                            <td>
                                                                医疗保险:
                                                            </td>
                                                            <td>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon">￥</div>
                                                                    <input type="text text-danger" class="form-control" placeholder="医疗保险" id="exampleInputAmount" placeholder="Amount" data-bind="value: medical, valueUpdate:'afterkeydown'" disabled>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                养老保险:
                                                            </td>
                                                            <td>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon">￥</div>
                                                                    <input type="text text-danger" class="form-control" placeholder="养老保险" data-bind="value: endowment, valueUpdate:'afterkeydown'" disabled>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                失业保险:
                                                            </td>
                                                            <td>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon">￥</div>
                                                                    <input type="text text-danger" class="form-control" placeholder="失业保险" data-bind="value: unemployment, valueUpdate:'afterkeydown'" disabled>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                工伤保险:
                                                            </td>
                                                            <td>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon">￥</div>
                                                                    <input type="text text-danger" class="form-control" placeholder="工伤保险" data-bind="value: inductriaInjury, valueUpdate:'afterkeydown'" disabled>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                生育保险:
                                                            </td>
                                                            <td>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon">￥</div>
                                                                    <input type="text text-danger" class="form-control" placeholder="生育保险" data-bind="value: birth, valueUpdate:'afterkeydown'" disabled>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                住房公积金:
                                                            </td>
                                                            <td>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon">￥</div>
                                                                    <input type="text text-danger" class="form-control" placeholder="住房公积金" data-bind="value: accumulationFund, valueUpdate:'afterkeydown'" disabled>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </td>
                                            <td>
                                                <div class="input-group">
                                                    <div class="input-group-addon">￥</div>
                                                    <input type="text text-danger" class="form-control" id="exampleInputAmount" placeholder="fine" data-bind="value: actuallySalary, valueUpdate:'afterkeydown'" disabled>
                                                </div>
                                            </td>
                                            <td>
                                                <a href="javascript:void(0);" data-toggle="tooltip" title="保存修改" data-bind="click: saveSalary"> <span class="glyphicon glyphicon-floppy-saved"></span>&nbsp;</a>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

    </body>

    </html>