(function () {
    angular.module('timetableApp.timetables').controller('EditTimetableController',
        ['$routeParams', 'timetableService', 'authenticationService', ViewTimetableController]);

    function ViewTimetableController($routeParams, timetableService, authenticationService) {
        let vm = this;

        vm.timetable = {};
        vm.currentUser = {};

        activate();

        function activate() {
            timetableService.getById($routeParams.id)
                .then(t => vm.timetable = t);

            authenticationService.getCurrentUser()
                .then(u => vm.currentUser = u);
        }
    }
})();