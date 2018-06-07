(function () {
    angular.module('timetableApp.timetables').controller('ViewTimetableController',
        ['$scope', '$routeParams', 'timetableService', 'timeUtilService',
            'authenticationService', 'eventService', ViewTimetableController]);

    function ViewTimetableController($scope, $routeParams, timetableService,
                                     timeUtilService, authenticationService, eventService) {

        const DATE_FORMAT = 'DD/MM/YYYY, HH:mm:ss';

        let vm = this;

        vm.timetable = {};
        vm.currentUser = {};
        vm.dayTimetableList = [];
        vm.fromDate = timeUtilService.getFirstDayOfCurrentWeekFromMonday();
        vm.toDate = timeUtilService.getLastDayOfCurrentWeekFromMonday();
        vm.currentMonth = new Date().getMonth();
        vm.newEvent = {};
        vm.initNewEvent = initNewEvent;
        vm.submitNewEventCreation = submitNewEventCreation;
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

        function initNewEvent() {
            let defaultPeriodUnits = vm.timetable.usePeriod && vm.timetable.periodDays > 0 ? 'days' : 'weeks';
            let defaultPeriodLength = vm.timetable.usePeriod
                ? Math.max(vm.timetable.periodDays, vm.timetable.periodWeeks) : 1;
            vm.newEvent = {
                title: '',
                description: '',
                location: '',
                startDate: '',
                endDate: '',
                usePeriod: vm.timetable.usePeriod,
                periodUnits: defaultPeriodUnits,
                periodLength: defaultPeriodLength
            };
        }

        function submitNewEventCreation() {
            let inputEvent = getInputEvent();
            eventService.create(inputEvent);
            $('#create-event-modal').modal('hide');
        }

        function getInputEvent() {
            return {
                timetableId: vm.timetable.id,
                title: vm.newEvent.title,
                location: vm.newEvent.location,
                description: vm.newEvent.description,
                startDate: moment(vm.newEvent.startDate, DATE_FORMAT).toDate(),
                endDate: moment(vm.newEvent.endDate, DATE_FORMAT).toDate(),
                usePeriod: vm.newEvent.usePeriod,
                periodDays: vm.newEvent.periodUnits === 'days' ? vm.newEvent.periodLength : 0,
                periodWeeks: vm.newEvent.periodUnits === 'weeks' ? vm.newEvent.periodLength : 0
            }
        }

        function isCurrentUserOwner() {
            if (!vm.currentUser) {
                return false;
            }
            return vm.currentUser.id === vm.timetable.ownerId;
        }
    }
})();