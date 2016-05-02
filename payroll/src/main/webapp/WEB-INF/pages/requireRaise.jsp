<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <form class="form-horizontal" id="requireRaise"  name="requireRaise" role="form">
        <div class="form-group">
            <label for="input-id" class="col-sm-4 control-label">申请加薪:</label>
            <div class="col-sm-8">
                <div class="input-group">
                    <div class="input-group-addon">￥</div>
                    <input type="text" name="requireMoney"  class="form-control" >
                </div>
            </div>
        </div>
        <div class="form-group">
            <label for="input-id" class="col-sm-4 control-label">加薪理由:</label>
            <div class="col-sm-8">
                    <input type="text" name="content"  class="form-control" >
            </div>
        </div>
    </form>