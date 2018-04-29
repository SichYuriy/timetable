angular.module('dashboard').controller('userDashboardController',
    ['$scope', '$window', 'authenticationService', UserDashboardController]);

function UserDashboardController($scope, $window, authenticationService) {

    $scope.logout = logout;

    authenticationService.getCurrentUser()
        .then(updateCurrentUser, showError);

    function updateCurrentUser(response) {
        $scope.currentUser = response.data;
    }

    function showError() {
        console.log('fail to load current user');
    }

    function logout() {
        authenticationService.logout().then(redirectDashboard);
    }

    function redirectDashboard() {
        console.log('logout success');
        $window.location.href = '#!/dashboard'
    }
}