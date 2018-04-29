(function () {
    angular.module('dashboard').controller('loadDashboardController',
        ['$window', 'authenticationService', LoadDashboardController]);

    function LoadDashboardController($window, authenticationService) {

        authenticationService.getCurrentUser()
            .then(chooseAndRedirectDashboard, showError);


        function chooseAndRedirectDashboard(response) {

            if (response.data.username != null) {
                $window.location.href = '#!/userDashboard';
            } else {
                $window.location.href = '#!/anonymousDashboard';
            }
        }

        function showError() {
            console.log('Unable to load current user');
        }
    }
})();
