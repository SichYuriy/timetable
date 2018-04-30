(function () {
    angular.module('timetableApp.authentication').component('logoHeader', {
        templateUrl: 'common/header/logo-header.html',
        controller: 'LogoHeaderController',
        controllerAs: 'vm',
        bindings: {
            currentUser: '<'
        }
    })
})();