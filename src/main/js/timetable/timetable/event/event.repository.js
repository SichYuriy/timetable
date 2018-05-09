(function () {
    angular.module('timetableApp.timetables').service('eventRepository',
        ['$http', EventRepository]);

    function EventRepository($http) {
        this.create = create;
        this.findEventsForPeriod = findFroEventsPeriod;

        function create(event) {
            return $http.post('/timetables/' + event.timetableId + '/events', event);
        }

        function findFroEventsPeriod(timetableId, startDate, endDate) {
            return $http.get('/timetables/' + timetableId + '/events', {
                params: {
                    startDate: startDate,
                    endDate: endDate
                }
            });
        }
    }
})();