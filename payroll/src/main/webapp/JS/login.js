var loginModel = {
    Power: ko.observableArray([{
        value: ko.observable("管理员"),
        displayName: "管理员",
    }, {
            value: ko.observable("员工"),
            displayName: "员工"
        }, {
            value: ko.observable('人事部管理员'),
            displayName: '人事部'
        }]),
    userId: ko.observable(),
    password: ko.observable(),
    autoLogin: ko.observable(false),
    selectPower: ko.observable('员工')
}

loginModel.logindisable = ko.computed(function () {
    return isNullOrEmpty(loginModel.userId())
        || isNullOrEmpty(loginModel.password());
}, this);


loginModel.loginHandler = function () {
    var url = "login?autoLogin=" + loginModel.autoLogin();

    var user = {
        userId: loginModel.userId(),
        password: loginModel.password(),
        power: loginModel.selectPower()
    };

    $.ajax({
        type: "POST",
        url: url,
        cache: false,
        data: JSON.stringify(user),
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            getUser();
            location.href = response;
        },
        error: function (param) {
            initModal("登陆失败!", param.responseText);
        }
    });
}

ko.attach('loginModel', loginModel);