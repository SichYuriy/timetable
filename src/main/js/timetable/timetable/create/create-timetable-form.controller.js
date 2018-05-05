(function () {
    angular.module('timetableApp.timetables').controller('CreateTimetableFormController',
        ['$window', 'timetableService', 'authenticationService', CreateTimetableController]);

    function CreateTimetableController($window, timetableService, authenticationService) {
        let vm = this;

        vm.currentUser = {};
        vm.timetable = {};
        vm.submitCreation = submitCreation;

        activate();

        function activate() {
            vm.timetable = {
                title: '',
                description: '',
                isPrivate: false,
                usePeriod: false,
                periodDays: 0,
                periodWeeks: 0
            };
            authenticationService.getCurrentUser()
                .then(u => vm.currentUser = u);
        }

        function submitCreation() {
            timetableService.create(vm.timetable).then(redirectTimetable);
        }

        function redirectTimetable(response) {
            console.log(response.data);
        }
    }
})();