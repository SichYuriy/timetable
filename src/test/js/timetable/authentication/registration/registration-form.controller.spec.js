describe('RegistrationFormController', function () {
    beforeEach(module('timetableApp.components'));

    let $rootScope;
    let $controller;
    let $q;

    let deferredRegistration;

    let $scope;
    let authenticationService;
    let subject;

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
        subject = $controller('RegistrationFormController', {
            $scope: $scope,
            authenticationService: authenticationService
        });
    });

    describe('initialization', function () {
        it('should init registration form', function () {
            expect(subject.username).toEqual('');
            expect(subject.password).toEqual('');
            expect(subject.repeatPassword).toEqual('');
        });

        it('should init registrationSucceed', function () {
            expect($scope.registrationSucceed).toBeFalsy();
        });
    });

    describe('submit registration', function () {
        beforeEach(function () {
            subject.username = "John_Snow";
            subject.password = "123";
            subject.repeatPassword = "123";

            subject.submitRegistration();
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
                expect(subject.username).toEqual('');
                expect(subject.password).toEqual('');
                expect(subject.repeatPassword).toEqual('');
            });

            it('should set registrationSucceed equal TRUE', function () {
                expect(subject.alerts.registrationSucceed).toBeTruthy();
            });
        });

        describe('when registration failed', function () {
            beforeEach(function () {
                deferredRegistration.reject();
                $scope.$apply();
            });

            it('data should still be in the form', function () {
                expect(subject.username).toEqual('John_Snow');
                expect(subject.password).toEqual('123');
                expect(subject.repeatPassword).toEqual('123');
            });

            it('should set userIsAlreadyExists equal TRUE', function () {
                expect(subject.alerts.userIsAlreadyExists).toBeTruthy();
            });
        });
    });
});