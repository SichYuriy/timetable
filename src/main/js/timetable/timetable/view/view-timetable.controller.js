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
        vm.dayTimetableList = [];
        vm.fromDate = timeService.getFirstDayOfCurrentWeekFromMonday();
        vm.toDate = timeService.getLastDayOfCurrentWeekFromMonday();
        vm.currentMonth = new Date().getMonth() + 1;
        vm.startFromMonday = true;

        activate();

        function activate() {
            timetableService.getById($routeParams.id)
                .then(t => vm.timetable = t);

            authenticationService.getCurrentUser()
                .then(u => vm.currentUser = u);

            vm.dayTimetableList = initDayTimetableList(vm.fromDate);
            vm.dayTimetableList[1].events.push({
                startHour: 8,
                startMinute: 0,
                duration: {
                    hours: 1,
                    minutes: 0
                }

            });
            vm.dayTimetableList[0].events.push({
                startHour: 6,
                startMinute: 0,
                duration: {
                    hours: 1,
                    minutes: 0
                }

            });
        }

        function initDayTimetableList(startDate) {
            let days = [];
            for (let i = 0; i < 7; i++) {
                days.push({
                    dayIndex: (startDate.getDay() + i) % 7,
                    events: []
                });
            }
            return days;
        }
    }
})();