(function () {
    angular.module('timetableApp.repositories').service('subscriptionRepository',
        ['$http', SubscriptionRepository]);

    function SubscriptionRepository($http) {
        this.getOwnSubscription = getOwnSubscription;
        this.createSubscriptionOnPublicTimetable = createSubscriptionOnPublicTimetable;

        function getOwnSubscription(timetableId) {
            return $http.get('/timetables/'+ timetableId + '/subscription/own');
        }

        function createSubscriptionOnPublicTimetable(timetableId) {
            return $http.post('/timetables/public/' + timetableId + '/subscription}');
        }
    }
})();