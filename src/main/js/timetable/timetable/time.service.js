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
            currentDate.setHours(0);
            currentDate.setMinutes(0);
            currentDate.setSeconds(0);
            currentDate.setMilliseconds(0);
            return currentDate;
        }

        function getLastDayOfCurrentWeekFromMonday() {
            let currentDate = new Date();
            let dayIndex = (7 + currentDate.getDay() - 1) % 7;
            currentDate.setDate(currentDate.getDate() + (6 - dayIndex));
            currentDate.setHours(23);
            currentDate.setMinutes(59);
            currentDate.setSeconds(59);
            currentDate.setMilliseconds(999);
            return currentDate;
        }
    }
})();