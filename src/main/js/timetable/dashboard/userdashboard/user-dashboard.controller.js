(function () {
    angular.module('timetableApp.dashboard').controller('UserDashboardController',
        ['$window', 'authenticationService', 'timetableService', UserDashboardController]);

    function UserDashboardController($window, authenticationService, timetableService) {
        let vm = this;

        vm.currentUser = {};
        vm.alerts = {};
        vm.ownAndNotActiveTimetables = [];

        activate();

        function activate() {
            authenticationService.getCurrentUser()
                .then(u => vm.currentUser = u);

            timetableService.getFewMyNotActiveTimetables()
                .then(timetables => vm.ownAndNotActiveTimetables = timetables);
        }

    }
})();
