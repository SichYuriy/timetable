(function () {
    angular.module('timetableApp.dashboard').controller('UserDashboardController',
        ['$window', 'authenticationService', UserDashboardController]);

    function UserDashboardController($window, authenticationService) {
        var vm = this;

        vm.logout = logout;
        vm.currentUser = {};
        vm.alerts = {};

        activate();

        function activate() {
            authenticationService.getCurrentUser()
                .then(updateCurrentUser, showError);
        }

        function updateCurrentUser(response) {
            vm.currentUser = response.data;
        }

        function showError() {
            vm.alerts.userLoadError = true;
        }

        function logout() {
            authenticationService.logout().then(redirectDashboard);
        }

        function redirectDashboard() {
            $window.location.href = '#!/dashboard'
        }
    }
})();
