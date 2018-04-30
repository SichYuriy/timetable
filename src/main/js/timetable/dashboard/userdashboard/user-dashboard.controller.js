(function () {
    angular.module('timetableApp.dashboard').controller('UserDashboardController',
        ['$window', 'authenticationService', UserDashboardController]);

    function UserDashboardController($window, authenticationService) {
        var vm = this;

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
    }
})();
