(function () {
    angular.module('timetableApp.timetables').service('timeService',
        TimeService);

    function TimeService() {
        this.getFirstDayOfCurrentWeekFromMonday = getFirstDayOfCurrentWeekFromMonday;
        this.getLastDayOfCurrentWeekFromMonday = getLastDayOfCurrentWeekFromMonday;

        function getFirstDayOfCurrentWeekFromMonday() {
            let currentDate = new Date();
            let dayIndex = (7 + currentDate.getDay() - 1) % 7;
            currentDate.setDate(currentDate.getDate() - dayIndex);
            return currentDate;
        }

        function getLastDayOfCurrentWeekFromMonday() {
            let currentDate = new Date();
            let dayIndex = (7 + currentDate.getDay() - 1) % 7;
            currentDate.setDate(currentDate.getDate() + (6 - dayIndex));
            return currentDate;
        }
    }
})();