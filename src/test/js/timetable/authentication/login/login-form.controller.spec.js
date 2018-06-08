describe('LoginFormController', function () {
    beforeEach(module('timetableApp.components'));

    let $rootScope;
    let $controller;
    let $q;

    let deferredLogin;

    let $scope;
    let $window;
    let authenticationService;
    let subject;

    beforeEach(inject(function (_$rootScope_, _$controller_, _$q_) {
        $rootScope = _$rootScope_;
        $controller = _$controller_;
        $q = _$q_;
    }));

    beforeEach(function () {
        deferredLogin = $q.defer();

        $scope = $rootScope.$new();
        $window = {location: {}};
        authenticationService = {login: jasmine.createSpy().and.returnValue(deferredLogin.promise)};
    });

    beforeEach(function () {
        subject = $controller('LoginFormController', {
            $scope: $scope,
            $window: $window,
            authenticationService: authenticationService
        });
    });

    describe('initialization', function () {
        it('should init default username and password', function () {
            expect(subject.username).toEqual('');
            expect(subject.password).toEqual('');
        });

        it('should init login failed', function () {
            expect(subject.alerts.loginFailed).toBeFalsy();
        });
    });

    describe('submit login', function () {
        beforeEach(function () {
            subject.username = 'John_Snow';
            subject.password = '123';

            subject.submitLogin();
        });

        it('should call authentication service' , function () {
            expect(authenticationService.login).toHaveBeenCalledWith({
                username: 'John_Snow',
                password: '123'
            });
        });

        describe('when login successful', function () {
            beforeEach(function () {
                deferredLogin.resolve();
                $scope.$apply();
            });

            it('should redirect to the dashboard', function () {
                expect($window.location.href).toEqual('#!/dashboard');
            });
        });

        describe('when user is not found', function () {
            beforeEach(function () {
                deferredLogin.reject();
                $scope.$apply();
            });

            it('should set loginFailed', function () {
                expect(subject.alerts.loginFailed).toBeTruthy();
            })
        });
    });
});