<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <form id="employeeAppend" class="form-horizontal">
        <div class="form-group">
            <label for="inputName" class="col-sm-2 control-label">姓名:</label>
            <div class="col-sm-10">
                <input type="text" name="employeeName" class="form-control" placeholder="姓名">
            </div>
        </div>
        <div class="form-group">
            <label for="inputSex" class="col-sm-2 control-label">性别:</label>
            <div class="col-sm-10">
                <select class="form-control" name="sex">
                    <option value="男" >男</option>
                    <option value="女">女</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="input-education" class="col-sm-2 control-label">学历:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="education" required="required">
            </div>
        </div>
        <div class="form-group">
            <label for="input-education" class="col-sm-2 control-label">部门:</label>
            <div class="col-sm-10">
                <select id="department" name="departmentNumber" class="form-control">
                </select>
            </div>
        </div>
        <div class="form-group">

            <label for="input-Salary" class="col-sm-2 control-label">基本工资:</label>

            <div class="col-sm-10">
                <div class="input-group">
                    <div class="input-group-addon">￥</div>
                    <input type="text" class="form-control" name="expectSalary" placeholder="基本工资">
                </div>
            </div>
        </div>
    </form>