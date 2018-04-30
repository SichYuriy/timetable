(function () {
    angular.module('timetableApp.authentication').controller('LogoHeaderController',
        ['$scope', '$interval', "$window", 'authenticationService', LogoHeaderController]);

    function LogoHeaderController($scope, $interval, $window, authenticationService) {
        var vm = this;

        vm.currentTime = Date.now();
        vm.logout = logout;

        activate();
        
        function activate() {
            console.log($scope.currentUser);
            $interval(updateCurrentTime, 1000);
        }

        function updateCurrentTime() {
            vm.currentTime = Date.now();
        }

        function logout() {
            authenticationService.logout().then(redirectDashboard);
        }

        function redirectDashboard() {
            $window.location.href = '#!/dashboard'
        }
    }
})();