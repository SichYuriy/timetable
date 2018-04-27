console.log('loginFormController');
angular.module('authentication').controller('loginFormController',
    ['$scope', 'authenticationService', '$window', LoginFormController]);

function LoginFormController($scope, authenticationService, $window) {
    console.log('init login controller');
    $scope.username = '';
    $scope.password = '';

    $scope.submitLogin = submitLogin;

    function submitLogin() {
        var user = {
            username: $scope.username,
            password: $scope.password
        };
        authenticationService.login(user).then(loginSuccess, loginError);
    }

    function loginSuccess(response) {
        console.log('login success');
        console.log(response.data);
        $window.location.href = '#!/dashboard'
    }

    function loginError(response) {
        console.log(response);
        console.log('login failed. Wrong username or password')
    }
}