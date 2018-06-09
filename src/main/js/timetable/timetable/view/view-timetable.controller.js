(function () {
    angular.module('timetableApp.components').controller('ViewTimetableController',
        ['$scope', '$routeParams', 'timetableService', 'timeUtilService',
            'authenticationService', 'eventService', ViewTimetableController]);

    function ViewTimetableController($scope, $routeParams, timetableService,
                                     timeUtilService, authenticationService, eventService) {
        let vm = this;

        vm.timetable = {};
        vm.currentUser = {};
        vm.dayTimetableList = [];
        vm.fromDate = timeUtilService.getFirstDayOfCurrentWeekFromMonday();
        vm.toDate = timeUtilService.getLastDayOfCurrentWeekFromMonday();
        vm.currentMonth = new Date().getMonth();
        vm.moveBack = moveBack;
        vm.moveForward = moveForward;
        vm.isCurrentUserOwner = isCurrentUserOwner;

        activate();

        function activate() {
            timetableService.getById($routeParams.id)
                .then(t => vm.timetable = t)
                .then(t => eventService.findEventsForPeriod(t.id, vm.fromDate, vm.toDate))
                .then(initDailyEvents);
            authenticationService.getCurrentUser()
                .then(u => vm.currentUser = u);
        }

        function initDailyEvents(events) {
            vm.dayTimetableList = initDayTimetableList(vm.fromDate);
            for (event of events) {
                let index = timeUtilService.getDaysBetween(event.startDate, vm.fromDate);
                vm.dayTimetableList[index].events.push(event);
            }
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

        function moveBack() {
            vm.fromDate.setDate(vm.fromDate.getDate() - 7);
            vm.toDate.setDate(vm.toDate.getDate() - 7);
            vm.currentMonth = vm.fromDate.getMonth();
            updateEvents();
        }

        function moveForward() {
            vm.fromDate.setDate(vm.fromDate.getDate() + 7);
            vm.toDate.setDate(vm.toDate.getDate() + 7);
            vm.currentMonth = vm.fromDate.getMonth();
            updateEvents();
        }

        function updateEvents() {
            eventService.findEventsForPeriod(vm.timetable.id, vm.fromDate, vm.toDate)
                .then(initDailyEvents);
        }

        function isCurrentUserOwner() {
            if (!vm.currentUser) {
                return false;
            }
            return vm.currentUser.id === vm.timetable.ownerId;
        }
    }
})();