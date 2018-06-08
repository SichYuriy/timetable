(function () {
    angular.module('timetableApp.components').controller('LogoHeaderController',
        ['$scope', '$interval', '$window', '$translate', 'authenticationService', LogoHeaderController]);

    function LogoHeaderController($scope, $interval, $window, $translate, authenticationService) {
        let vm = this;

        vm.currentTime = Date.now();
        vm.currentLanguage = $translate.use();
        vm.logout = logout;
        vm.changeLanguage = changeLanguage;

        activate();
        
        function activate() {
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

        function changeLanguage() {
            if (vm.currentLanguage === 'en') {
                vm.currentLanguage = 'ua';
            } else {
                vm.currentLanguage = 'en';
            }
            $translate.use(vm.currentLanguage);
        }
    }
})();