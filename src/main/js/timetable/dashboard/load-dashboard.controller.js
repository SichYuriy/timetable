(function () {
    angular.module('timetableApp.dashboard').controller('LoadDashboardController',
        ['$window', 'authenticationService', LoadDashboardController]);

    function LoadDashboardController($window, authenticationService) {
        var vm = this;

        vm.alerts = {};

        activate();

        function activate() {
            authenticationService.getCurrentUser()
                .then(chooseAndRedirectDashboard);
        }

        function chooseAndRedirectDashboard(response) {
            if (response.data.username != null) {
                $window.location.href = '#!/userDashboard';
            } else {
                $window.location.href = '#!/anonymousDashboard';
            }
        }
    }
})();
