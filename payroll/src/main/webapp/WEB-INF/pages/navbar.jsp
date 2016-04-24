<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="Modal.jsp" %>
        <nav class="navbar navbar-default" role="navigation" data-model="navModel">
            <div class="container">
                <div class="row">
                    <div class="navbar-header">
                        <a class=" navbar-brand" href="index">
                            <span class="glyphicon glyphicon-home"></span>&nbsp;工资管理系统
                        </a>
                    </div>
                    <p class="navbar-text navbar-right" data-bind="visible : isLogin">
                        <span class="glyphicon glyphicon-user"></span> &nbsp; 欢迎回来,&nbsp;
                        <a class="text-primary" href="admin" data-bind="text : userName"></a>
                        &nbsp;<a class="text-success" href="javascript:void(0)" data-bind="click : logout">注销</a> &nbsp; </p>
                </div>
            </div>
            <script src="JS/navbar.js"></script>
        </nav>