(function () {
    angular.module('timetableApp.services').service('eventService',
        ['eventRepository', 'timeUtil', EventService]);

    function EventService(eventRepository, timeUtil) {
        this.create = eventRepository.create;
        this.findEventsForPeriod = findEventsForPeriod;

        function findEventsForPeriod(timetableId, startDate, endDate) {
            return eventRepository.findEventsForPeriod(timetableId, startDate, endDate)
                .then(r => r.data)
                .then(parseStartEndDates);
        }

        function parseStartEndDates(events) {
            return events.map(e => {
                e.startDate = timeUtil
                    .convertFromGreenwichToLocalTime(new Date(e.startDate));
                e.endDate = timeUtil
                    .convertFromGreenwichToLocalTime(new Date(e.endDate));
                return e;
            });
        }
    }
})();