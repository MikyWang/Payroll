<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <script src="JS/modal.js"></script>
    <div data-model="modalModel" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" data-bind="disabled: disableClose">
						<span aria-hidden="true">&times;</span>
					</button>
                    <h4 class="modal-title" id="myModalLabel" data-bind="text : title"></h4>
                </div>
                <div class="modal-body" data-bind="html : body"></div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-bind="visible: callFirst ,html : firstButton , click : firstClick"></button>
                    <button type="button" class="btn btn-default" data-bind="visible: callSecond ,html : secondButton , click : secondClick">关闭</button>
                    <button type="button" id="close" class="btn btn-default" data-dismiss="modal" data-bind="disabled: disableClose">关闭</button>
                </div>
            </div>
        </div>
    </div>