(function () {
    angular.module('timetableApp.timetables').controller('CreateTimetableFormController',
        ['$window', 'timetableService', 'authenticationService', CreateTimetableController]);

    function CreateTimetableController($window, timetableService, authenticationService) {
        var vm = this;

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
            authenticationService.getCurrentUser().then(updateCurrentUser);
        }

        function submitCreation() {
            timetableService.create(vm.timetable).then(redirectTimetable);
        }

        function redirectTimetable(response) {
            console.log(response.data);
        }

        function updateCurrentUser(response) {
            vm.currentUser = response.data;
        }
    }
})();