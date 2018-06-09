(function () {
    angular.module('timetableApp.services').service('subscriptionService',
        ['subscriptionRepository', SubscriptionService]);

    function SubscriptionService(subscriptionRepository) {
        this.getOwnSubscription = getOwnSubscription;
        this.createSubscriptionOnPublicTimetable = createSubscriptionOnPublicTimetable;

        function getOwnSubscription(timetableId) {
            return subscriptionRepository.getOwnSubscription(timetableId)
                .then(r => r.data);
        }

        function createSubscriptionOnPublicTimetable(timetableId) {
            return subscriptionRepository
                .createSubscriptionOnPublicTimetable(timetableId)
                .then(r => r.data);
        }
    }
})();