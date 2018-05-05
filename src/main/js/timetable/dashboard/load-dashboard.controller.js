(function () {
    angular.module('timetableApp.dashboard').controller('LoadDashboardController',
        ['$window', 'authenticationService', LoadDashboardController]);

    function LoadDashboardController($window, authenticationService) {
        let vm = this;

        vm.alerts = {};

        activate();

        function activate() {
            authenticationService.getCurrentUser()
                .then(chooseAndRedirectDashboard);
        }

        function chooseAndRedirectDashboard(user) {
            if (user.username != null) {
                $window.location.href = '#!/userDashboard';
            } else {
                $window.location.href = '#!/anonymousDashboard';
            }
        }
    }
})();
