(function () {
    angular.module('timetableApp.components').component('createEventModal', {
        templateUrl: 'timetable/event/create-event-modal.html',
        controller: 'CreateEventModalController',
        controllerAs: 'vm',
        bindings: {
            timetable: '<'
        }
    });
})();