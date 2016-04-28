<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html lang="zh-CN">

    <head>
        <%@ include file="header.jsp" %>
            <script src="JS/login.js"></script>
    </head>

    <body>
          <%@ include file="navbar.jsp" %>
            <div class="container" data-model="loginModel">

                <div class="row">

                    <div class="col-sm-offset-8 col-sm-4">

                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">身份登陆</h3>
                            </div>
                            <div class="panel-body">
                                <form class="form-horizontal">
                                    <div class="form-group">
                                        <label for="inputId" class="col-sm-4 control-label">请输入员工号:</label>
                                        <div class="col-sm-8">
                                            <input type="email" class="form-control" data-bind="value: userId, valueUpdate : 'afterkeydown' " id="inputId" placeholder="请输入员工号">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="radio col-sm-offset-4 col-sm-8" data-bind="foreach: Power">
                                            <label class="col-sm-offset-1">
                                                <input type="radio"  name="power" id="inputpower" data-bind="checked: selectPower , value: value, valueUpdate:'afterkeydown'">
                                                <span data-bind="html: displayName"></span>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputPassword3" class="col-sm-4 control-label">请输入密码:</label>
                                        <div class="col-sm-8">
                                            <input type="password" class="form-control" data-bind="value: password, valueUpdate : 'afterkeydown' " id="inputPassword3" placeholder="请输入密码">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-4 col-sm-8">
                                            <div class="checkbox">
                                                <label>
                                                <input type="checkbox" data-bind="checked: autoLogin">自动登录1天
                                            </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-4 col-sm-8">
                                            <button type="button" class="btn btn-primary" data-bind="click: loginHandler ,disabled :logindisable">登录系统</button>
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