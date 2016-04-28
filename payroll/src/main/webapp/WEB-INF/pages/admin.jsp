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
                        <a class="btn btn-primary btn-lg" href="#department">立即开始</a>
                    </p>
                </div>
            </div>
            <div id="department" class="container" data-model="adminModel">
                <div class="row">
                    <div class=" col-sm-offset-2 col-sm-8">
                        <div class="panel panel-primary ">
                            <div class="panel-heading">
                                <h3 class="panel-title">所属部门成员</h3>
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
                                                性别
                                            </th>
                                            <th>
                                                所属部门
                                            </th>
                                            <th>
                                                本月工资
                                            </th>
                                            <th>
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody data-bind="foreach: employList">
                                        <tr>
                                            <td>
                                                <span data-bind="html: employNumber"></span>
                                            </td>
                                            <td>
                                                <a class="text-primary" href="javascript:void(0);" data-bind="html: employName , click : userDetail"></a>
                                            </td>
                                            <td>
                                                <span data-bind="html: sex"></span>
                                            </td>
                                            <td>
                                                <a class="text-primary" href="javascript:void(0);" data-bind="html: departmentName,click : departDetail"></a>
                                            </td>
                                            <td class="dropdown ">
                                                <a href="javascript:void(0);" class="dropdown-toggle" id="moneyDrop" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                                    <span class="text-warning">￥</span>
                                                    <span class="text-danger " data-bind="html: actMoney"></span>
                                                    <span class="glyphicon glyphicon-chevron-down text-danger "></span>
                                                </a>
                                                <table class="dropdown-menu  table table-hover table-striped" aria-labelledby="moneyDrop">
                                                    <tbody>
                                                        <tr>
                                                            <td>
                                                                基本工资:
                                                            </td>
                                                            <td>
                                                                <span class="text-warning">￥&nbsp;</span>
                                                                <span class="text-danger " data-bind="html: expMoney"></span>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                迟到罚款:
                                                            </td>
                                                            <td>
                                                                <span class="text-warning">￥&nbsp;</span>
                                                                <span class="text-danger " data-bind="html: fine"></span>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                加班时长:
                                                            </td>
                                                            <td>
                                                                <span class="text-warning">￥&nbsp;</span>
                                                                <span class="text-danger " data-bind="html: overtime"></span>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                加班工资:
                                                            </td>
                                                            <td>
                                                                <span class="text-warning">￥&nbsp;</span>
                                                                <span class="text-danger " data-bind="html: overtimeSalary"></span>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                上班天数:
                                                            </td>
                                                            <td>
                                                                <span class="text-warning">￥&nbsp;</span>
                                                                <span class="text-danger " data-bind="html: officeDay"></span>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </td>
                                            <td>
                                                <a href="javascript:void(0);" class="btn btn-primary"> <span class="glyphicon glyphicon-pencil"></span>&nbsp; 编辑</a>
                                                <a href="javascript:void(0);" class="btn btn-danger"> <span class="glyphicon glyphicon-minus"></span>&nbsp;删除</a>
                                                <a href="javascript:void(0);" class="btn btn-info"> <span class="glyphicon glyphicon-user"></span>&nbsp;管理员</a>
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