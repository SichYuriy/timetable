(function () {
    angular.module('timetableApp.timetables').service('timeUtilService',
        TimeUtilService);

    function TimeUtilService() {

        const MILLISECONDS_IN_DAY = 1000 * 60 * 60 * 24;

        this.getFirstDayOfCurrentWeekFromMonday = getFirstDayOfCurrentWeekFromMonday;
        this.getLastDayOfCurrentWeekFromMonday = getLastDayOfCurrentWeekFromMonday;
        this.convertFromGreenwichToLocalTime = convertFromGreenwichToLocalTime;
        this.getDaysBetween = getDaysBetween;

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

        function convertFromGreenwichToLocalTime(date) {
            let timeOffsetInMS = date.getTimezoneOffset() * 60000;
            date.setTime(date.getTime() - timeOffsetInMS);
            return date;
        }

        function getDaysBetween(from, to) {
            return Math.floor(Math.abs(from.getTime() - to.getTime()) / MILLISECONDS_IN_DAY);
        }
    }
})();