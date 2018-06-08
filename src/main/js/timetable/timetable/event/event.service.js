(function () {
    angular.module('timetableApp.services').service('eventService',
        ['eventRepository', 'timeUtilService', EventService]);

    function EventService(eventRepository, timeUtilService) {
        this.create = eventRepository.create;
        this.findEventsForPeriod = findEventsForPeriod;

        function findEventsForPeriod(timetableId, startDate, endDate) {
            return eventRepository.findEventsForPeriod(timetableId, startDate, endDate)
                .then(r => r.data)
                .then(parseStartEndDates);
        }

        function parseStartEndDates(events) {
            return events.map(e => {
                e.startDate = timeUtilService
                    .convertFromGreenwichToLocalTime(new Date(e.startDate));
                e.endDate = timeUtilService
                    .convertFromGreenwichToLocalTime(new Date(e.endDate));
                return e;
            });
        }
    }
})();