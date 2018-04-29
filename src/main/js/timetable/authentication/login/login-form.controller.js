(function () {
    angular.module('timetableApp.authentication').controller('loginFormController',
        ['$scope', 'authenticationService', '$window', LoginFormController]);

    function LoginFormController($scope, authenticationService, $window) {

        $scope.username = '';
        $scope.password = '';
        $scope.loginFailed = false;

        $scope.submitLogin = submitLogin;

        function submitLogin() {
            var user = {
                username: $scope.username,
                password: $scope.password
            };
            authenticationService.login(user).then(redirectDashboard, showLoginFailed);
        }

        function redirectDashboard() {
            $window.location.href = '#!/dashboard'
        }

        function showLoginFailed() {
            $scope.loginFailed = true;
        }
    }
})();
