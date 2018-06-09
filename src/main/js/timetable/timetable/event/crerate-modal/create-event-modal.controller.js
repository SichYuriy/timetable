(function () {
    angular.module('timetableApp.components').controller('CreateEventModalController',
        ['eventService', CreateEventModalController]);

    function CreateEventModalController(eventService) {
        const DATE_FORMAT = 'DD/MM/YYYY, HH:mm:ss';

        let vm = this;

        vm.newEvent = {};
        vm.initNewEvent = initNewEvent;
        vm.submitNewEventCreation = submitNewEventCreation;

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
    }
})();