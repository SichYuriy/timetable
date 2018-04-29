describe('registrationFormController', function () {
    beforeEach(module('authentication'));

    var $rootScope;
    var $controller;
    var $q;

    var deferredRegistration;

    var $scope;
    var authenticationService;
    var ctrl;

    beforeEach(inject(function (_$rootScope_, _$controller_, _$q_) {
        $rootScope = _$rootScope_;
        $controller = _$controller_;
        $q = _$q_;
    }));

    beforeEach(function () {
        deferredRegistration = $q.defer();

        $scope = $rootScope.$new();
        authenticationService = {register: jasmine.createSpy().and.returnValue(deferredRegistration.promise)};
    });

    beforeEach(function () {
        ctrl = $controller('registrationFormController', {
            $scope: $scope,
            authenticationService: authenticationService
        });
    });

    describe('initialization', function () {
        it('should init registration form', function () {
            expect($scope.username).toEqual('');
            expect($scope.password).toEqual('');
            expect($scope.repeatPassword).toEqual('');
        });

        it('should init registrationSucceed', function () {
            expect($scope.registrationSucceed).toBeFalsy();
        });
    });

    describe('submit registration', function () {
        beforeEach(function () {
            $scope.username = "John_Snow";
            $scope.password = "123";
            $scope.repeatPassword = "123";

            $scope.submitRegistration();
        });

        it('should call authentication service', function () {
            expect(authenticationService.register).toHaveBeenCalledWith({
                username: "John_Snow",
                password: "123"
            });
        });

        describe('when registration succeed', function () {
            beforeEach(function () {
                deferredRegistration.resolve();
                $scope.$apply();
            });

            it('should clear registration form', function () {
                expect($scope.username).toEqual('');
                expect($scope.password).toEqual('');
                expect($scope.repeatPassword).toEqual('');
            });

            it('should set registrationSucceed equal TRUE', function () {
                expect($scope.registrationSucceed).toBeTruthy();
            });
        });

        describe('when registration failed', function () {
            beforeEach(function () {
                deferredRegistration.reject();
                $scope.$apply();
            });

            it('data should still be in the form', function () {
                expect($scope.username).toEqual('John_Snow');
                expect($scope.password).toEqual('123');
                expect($scope.repeatPassword).toEqual('123');
            });

            it('should set userIsAlreadyExists equal TRUE', function () {
                expect($scope.userIsAlreadyExists).toBeTruthy();
            });
        });
    });
});