(function () {
    angular.module('timetableApp.timetables').service('eventService',
        ['eventRepository', EventService]);

    function EventService(eventRepository) {
        this.create = eventRepository.create;
        this.findEventsForPeriod = findEventsForPeriod;

        function findEventsForPeriod(timetableId, startDate, endDate) {
            return eventRepository.findEventsForPeriod(timetableId, startDate, endDate)
                .then(r => r.data)
                .then(parseStartEndDates);
        }

        function parseStartEndDates(events) {
            return events.map(e => {
                e.startDate = adjustForTimezone(new Date(e.startDate));
                e.endDate = adjustForTimezone(new Date(e.endDate));
                return e;
            });
        }

        function adjustForTimezone(date) {
            let timeOffsetInMS = date.getTimezoneOffset() * 60000;
            date.setTime(date.getTime() - timeOffsetInMS);
            return date;
        }
    }
})();