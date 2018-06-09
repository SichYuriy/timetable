(function () {
    angular.module('timetableApp.components').controller('LoginFormController',
        ['$window', 'authenticationService', LoginFormController]);

    function LoginFormController($window, authenticationService) {
        let vm = this;

        vm.username = '';
        vm.password = '';
        vm.alerts = {};

        vm.submitLogin = submitLogin;

        function submitLogin() {
            let user = getInputUser();
            authenticationService.login(user).then(redirectDashboard, showLoginFailed);
        }

        function redirectDashboard() {
            $window.location.href = '#!/dashboard'
        }

        function showLoginFailed() {
            vm.alerts.loginFailed = true;
        }

        function getInputUser() {
            return {
                username: vm.username,
                password: vm.password
            };
        }
    }
})();
