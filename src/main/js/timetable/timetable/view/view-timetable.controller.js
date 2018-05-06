(function () {
    angular.module('timetableApp.timetables').controller('ViewTimetableController',
        ['$routeParams', 'timetableService', 'timeService',
            'authenticationService', ViewTimetableController]);

    function ViewTimetableController($routeParams,
                                     timetableService,
                                     timeService,
                                     authenticationService) {
        let vm = this;

        vm.timetable = {};
        vm.currentUser = {};
        vm.days = [];
        vm.fromDate = timeService.getFirstDayOfCurrentWeekFromMonday();
        vm.toDate = timeService.getLastDayOfCurrentWeekFromMonday();
        vm.currentMonth = 5;
        vm.startFromMonday = true;

        activate();

        function activate() {
            timetableService.getById($routeParams.id)
                .then(t => vm.timetable = t);

            authenticationService.getCurrentUser()
                .then(u => vm.currentUser = u);


            vm.days = ['Monday', 'Tuesday', 'Wednesday',
                'Thursday', 'Friday', 'Saturday', 'Sunday'];
        }
    }
})();