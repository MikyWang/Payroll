<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <form class="form-horizontal" data-bind="foreach: addEmployeeModel">
        <div class="form-group">
            <label for="inputName" class="col-sm-2 control-label">姓名:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" placeholder="姓名" data-bind="value: employeeName">
            </div>
        </div>
        <div class="form-group">
            <label for="inputSex" class="col-sm-2 control-label">性别</label>
            <div class="col-sm-10">
                <select class="form-control">
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="input-education" class="col-sm-2 control-label">学历</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" required="required">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Sign in</button>
            </div>
        </div>
    </form>