(function () {
    angular.module('timetableApp.services').service('subscriptionService',
        ['subscriptionRepository', 'responseUtil', SubscriptionService]);

    function SubscriptionService(subscriptionRepository, responseUtil) {
        this.getOwnSubscription = responseUtil.extractData(subscriptionRepository.getOwnSubscription);
        this.createSubscriptionOnPublicTimetable =
            responseUtil.extractData(subscriptionRepository.createSubscriptionOnPublicTimetable);
    }
})();